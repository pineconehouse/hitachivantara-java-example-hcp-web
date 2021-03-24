<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hitachivantara.example.hcp.*"%>
<%@ page import="com.hitachivantara.hcp.exts.post.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body>
	<c:set var="form" scope="session" value="<%=PostDemo.generateV2PostFormForFileUpload() %>"/>

    <form action="http://<c:out value="${form.bucket}"/>.tenant1.hcp-demo.hcpdemo.com" method="post" enctype="multipart/form-data">
		<c:forEach var="data" items="${form.elements}" varStatus="status">
			<input type="hidden" name="<c:out value="${data.name}"/>" value="<c:out value="${data.stringValue}" />" />
		</c:forEach> 
		
        <label>Choose file: </label><br>
        <input type="file" id="file" name="file" />

         <br/><br/>
         <input type="submit" id="submit" name="submit" value="Upload to HCP" />
    </form>
	<script>
	     var submitBtn = document.getElementById("submit");
	     
	     submitBtn.onclick = function (event) {
	     	var path=document.getElementById("file").value;
			console.log(path);
			
			if (path == '') {
		      	alert("Select file for upload first!");
		      	return false;
			}
	     };
	</script>

</html>