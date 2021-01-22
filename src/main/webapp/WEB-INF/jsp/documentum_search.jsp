<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <div class="card">

        <div class="card-body">

            <form:form class="main-form" modelAttribute="application">

                <fieldset class="form-group mt-5 mb-3">

                <div class="form-group">

                    <label class="form-control-label" for="Account Number">Account Number</label>
                    <form:input class="form-control" placeholder="0123456789" path="appId" /> <%-- <form:errors path="appId" /> --%>

                </div> <!-- form-group -->

                </fieldset> <!-- fieldset -->

    	        <div class="container">

  			        <div class="row">

    			        <div class="col text-center">

      				        <button class="btn btn-primary mb-5" type="Submit" name="button">Search</button>

    			        </div>

 			        </div>

		        </div>

            </form:form>

        </div>

    </div>

    <div class="my-5">
        <br>
    </div>

</div><!-- container -->

<script src="<c:url value="/js/jquery.min.js" />"></script>
<script src="<c:url value="/js/popper.min.js" />"></script>
<script src="<c:url value="/js/bootstrap.min.js" />"></script>

<script>

$(document).ready(function() {
	$('.btn-primary').on('click', function() {
	    var $this = $(this);
	    var loadingText = `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Loading...`;

	    if ($(this).html() !== loadingText) {
	      $this.data('original-text', $(this).html());
	      $this.html(loadingText);
	    }
	});
});

</script>

<jsp:include page="footer.jsp"/>

</body>
</html>
