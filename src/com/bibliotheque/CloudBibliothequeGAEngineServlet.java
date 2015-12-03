package com.bibliotheque;

import static com.googlecode.objectify.ObjectifyService.ofy;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.googlecode.objectify.ObjectifyService;

@SuppressWarnings("serial")
public class CloudBibliothequeGAEngineServlet extends HttpServlet {
	
	/* Faire connaître la classe-entité Livre à Objectify */
		static {
			ObjectifyService.register(Livre.class);
		}

		public void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws IOException, ServletException {
			
				/* recuperation du service Blobstore */
				BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
				
				/* telechargement de livre */
				if (req.getParameter("livre") != null) {
					BlobKey blobKey = new BlobKey(req.getParameter("livre"));
					BlobInfoFactory bi = new BlobInfoFactory();
					String fname = bi.loadBlobInfo(blobKey).getFilename();
					resp.setContentType("application/pdf");
					resp.setHeader("Content-Disposition", "attachment; filename=" + fname);
					blobstoreService.serve(blobKey, resp);
				}
				
			/* Récupère les derniers uploads */
				List<Livre> listBook = ofy().load().type(Livre.class).list();
				
			/*	envoyer la liste des livre au JSP */
				req.setAttribute("listBook", listBook);			
				this.getServletContext().getRequestDispatcher("/WEB-INF/biblio.jsp").forward(req, resp);			
		}
		
		 public void doPost(HttpServletRequest req, HttpServletResponse resp)
		            throws IOException {
		        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		       
		        /* retourner tous les livres qui ont été uploadé */
		        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		        
		        /* sauvegarder le nouveau livre ajouté
		         le fichier ==> blobStore (google cloud storage) */
		        List<BlobKey> blobKeys = blobs.get("uploadedFile");
		        
		        /* creation et sauvegarder de l'objet livre qui contient l'url de livre 
		         et son nom ==> DataStore (google cloud NoSql)*/
		        Livre book = new Livre(blobKeys.get(0), req.getParameter("nom"));
		        ofy().save().entity(book).now();
		        resp.sendRedirect("/");
		    }
}
