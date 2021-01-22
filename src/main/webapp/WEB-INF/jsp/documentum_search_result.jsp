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
  <link href="<c:url value="/css/jquery.dataTables.min.css" />" rel="stylesheet">
  <link href="<c:url value="/css/main.css" />" rel="stylesheet">

  <title>Documentum REST API</title>

  <jsp:include page="header.jsp"/>

</head>
<body>
<div class="container">

	<h4 class="text-center text-primary mt-5 mb-3">Application Type: &emsp; ${type}</h4>
	
	<h4 class="text-center text-primary mb-5">Application Number: &emsp; <c:out value="${applications[0].appId}"/></h4>

	<div class="card">

		<div class="card-body">

			<h3 class="text-center my-3">Below is a list of available document(s) for download</h3>

    		<div class="table mb-5">
                <table id="myTable" class="table table-striped table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <!-- <th class="text-center">Registration ID</th> -->
                            <th class="text-center">Documentum ID</th>
                            <th class="text-center">Document Name</th>
                            <th class="text-center">Operations</th>
                        </tr>
                    </thead>
                    <tbody>
            	        <c:forEach items="${applications}" var="object" varStatus="myIndex">
                        <tr>
                            <%-- <td class="text-center">${object.appId}</td> --%>
                            <td class="text-center">${object.objectId}</td>
                            <td class="text-center">${object.docName}</td>
                            <th class="text-center" scope="row">
                    	<%-- <a class="btn btn-success" href="documentum_download.html?id=${myIndex.index}">Download</a> --%>
                    	       <a class="btn btn-info mx-3" href="documentum_view/${myIndex.index}.html">View</a>
                    	       <a class="btn btn-success" href="documentum_download/${myIndex.index}.html">Download</a>
					        </th>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

		</div>

	</div>

    <div class="my-5"></div>

</div><!-- container -->

<script src="<c:url value="/js/jquery.min.js" />"></script>
<script src="<c:url value="/js/popper.min.js" />"></script>
<script src="<c:url value="/js/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="/js/bootstrap.min.js" />"></script>

<script>

$(document).ready(function() {
	$('.btn-info').on('click', function() {
	    var $this = $(this);
	    var loadingText = `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Displaying...`;

	    if ($(this).html() !== loadingText) {
	      $this.data('original-text', $(this).html());
	      $this.html(loadingText);
	    }
	});
});

$(document).ready(function() {
	$('.btn-success').on('click', function() {
	    var $this = $(this);
	    var loadingText = `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Downloading...`;

	    if ($(this).html() !== loadingText) {
	      $this.data('original-text', $(this).html());
	      $this.html(loadingText);
	    }
	    
	    setTimeout(function() {
			$this.html($this.data('original-text'));
		}, 5000);
	});
});
 
$(document).ready(function() {
    $('#myTable').dataTable();
});

</script>

<jsp:include page="footer.jsp"/>

</body>
</html>
