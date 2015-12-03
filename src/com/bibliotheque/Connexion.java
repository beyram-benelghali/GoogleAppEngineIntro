package com.bibliotheque;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/* Un servlet est un objet JAVA coté serveur qui reçoit des données HTTP 
  et qui opère un ou des traitements et devant respecter les contraintes de ce protocole HTTP.*/

public class Connexion extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		/* UserService fournit des informations pour forcer un utilisateur à se connecter 
		 et de récupérer ses informations qui est actuellement connecté en.*/
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		req.setAttribute("authent", userService.createLoginURL(req.getRequestURI()));
		
		/* si l'utilisateur est déja connecté sur google 
		   il va passer irectement a la bibliotheque*/
		if (user != null){
		resp.sendRedirect("/cloudbibliothequegaengine");
		
		}
		
		/* aller a la page de connexion s'il n'ya pas un compte google connecté */
		this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		/* naviguer vers la ressource biblio */
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/cloudbibliothequegaengine") ;
		requestDispatcher.forward(req, resp) ;
		
	}

}
