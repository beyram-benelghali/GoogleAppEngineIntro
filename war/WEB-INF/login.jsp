<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connexion</title>
<link href="css/login.css" rel="stylesheet" media="screen">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body><br>
<p id="tit">
Bienvenue ! <br>
Veuillez vous connecter 
</p>
<div  id="imgg">
<img width="70%" height="70%" alt="cloud" src="<%=request.getContextPath()%>/css/back.png">
</div>
<div id="loginModal" class="modal show" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
  <div class="modal-content">
      <div class="modal-header">
          <h1 class="text-center">S'identifier</h1>
      </div>
	  
	  
	 
      <div class="modal-body">
          <form class="form col-md-12 center-block">
           <% String link = (String) request.getAttribute("authent");
			System.out.println(link);
			  %>
			 <a href="<%=link%>"> <img src="<%=request.getContextPath()%>/css/sign.png"></a>
          </form>
      </div>

  </div>
  </div>
</div>


	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>