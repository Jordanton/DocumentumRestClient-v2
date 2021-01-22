<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta http-equiv="x-ua-compatible" content="ie=edge">

  <link href="<c:url value="/css/font-awesome.min.css" />" rel="stylesheet">
  <link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
  <link href="<c:url value="/css/main.css" />" rel="stylesheet">

  <title>Documentum REST API</title>

  <jsp:include page="header.jsp"/>

</head>
<body>
<div class="container">

	<%-- <h4 class="text-center text-primary mt-5 mb-3">Application Type: &emsp; ${type}</h4> --%>
	
	<%-- <h4 class="text-center text-primary mb-5">Application Number: &emsp; <c:out value="${applications[0].appId}"/></h4> --%>

    <div class="card">

        <div class="card-body">

	        <div>
		        <h4 class="text-center text-primary my-5">There were no documents found for this application!</h4>
		        <h4 class="text-center text-primary my-5">If it has not been more than a day since the application was submitted, then please check back later because the documents are still being processed.</h4>
		        <h4 class="text-center text-primary my-5">If it has been more than a day, then this application did not include a document.</h4>
		        <h5 class="text-center text-primary mb-5">Please close the browser to exit this page.</h5>    
	        </div>

        </div>
		<!--         
        <div class="row mt-3">

    		<div class="col text-center">    		       
      			<button class="btn btn-danger btn-lg mb-5" type="button" onclick="closeWin()">Exit</button>
    	 	</div>

 	     </div>
 		 -->
    </div>

    <div class="my-5"></div>

</div><!-- container -->

<script type="text/javascript">

  function closeWin() {
	  
	  window.open('', '_self', '');
	  window.close();
	  /* window.open('javascript:window.open("", "_self", "");window.close();', '_self'); */
  }
  
</script>

<script src="<c:url value="/js/jquery.min.js" />"></script>
<script src="<c:url value="/js/popper.min.js" />"></script>
<script src="<c:url value="/js/bootstrap.min.js" />"></script>

<jsp:include page="footer.jsp"/>

</body>
</html>
