<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.appengine.api.blobstore.*"%>
<%@ page import="com.bibliotheque.Livre"%>

<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory
            .getBlobstoreService();
%>

<!DOCTYPE html>
<html>
<head>
<title>Cloud Uploader</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/style.css" rel="stylesheet" media="screen">
</head>
<body>

	<div class="container-narrow">

		<div class="masthead">
			<ul class="nav nav-pills pull-right">
				<li class="active"><a href="/">Accueil</a></li>
				<li><a href="#list">Liste des livres</a></li>
				<li><a href="#upload">Upload</a></li>
			</ul>
			<h3 class="muted">Bibliothéque</h3>
		</div>

		<hr />

		<div class="jumbotron">
		
			<h1>
				Vos Livres <br /> dans le cloud
			</h1>
			<p class="lead">En un clic, uploadez votre livre</p>
			<a class="btn btn-large btn-success" href="#upload">Uploader un
				livre</a>
		</div>

		<hr />

		<div id="list">

		<h2>Liste des livres</h2>
	<%
			    List<Livre> listBook = (List<Livre>) request.getAttribute("listBook");
				if (listBook.size() == 0) {
				    %>
		<p>
				<em>Aucune Livre uploadée</em>
			</p>
		
			<%
				}
				
				for (Livre livre : listBook) {
					%>
				<div id="elementttt"> <img src="icon.png"> 
				<b><a href="/cloudbibliothequegaengine?livre=<%=livre.getKeyString()%>" ><%=livre.getNom()%></a> </b> </div>	
		<%	} %> 	
	</div>

				
		</div>

		<hr />

	<center>	<div class="row-fluid iconlist" id="upload">

			<h2>Uploader un livre</h2>

			<form
				action="<%= blobstoreService.createUploadUrl("/") %>"
				method="post" enctype="multipart/form-data">
				<p>
					<label>Fichier à envoyer : <input type="file"
						name="uploadedFile" /></label>
				</p>
				<p>
					<label>Nom du fichier : <input maxlength="10" type="text"
						name="nom" /></label>
				</p>
				<div class="form-actions">
					<input type="submit" class="btn" />
				</div>
			</form>
		</div>
</center>
	</div>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>