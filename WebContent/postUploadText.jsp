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
	<c:set var="form" scope="session" value="<%=PostDemo.generateV2PostFormForDirectTextSave() %>"/>

    <form action="http://<c:out value="${form.bucket}"/>.tenanta.hcp1.hitachi.lab" method="post" enctype="multipart/form-data">
		<c:forEach var="data" items="${form.elements}" varStatus="status">
			<input type="hidden" name="<c:out value="${data.name}"/>" value="<c:out value="${data.stringValue}" />" />
		</c:forEach> 
		
        <label>Comment: </label><br>
        <textarea name="file" cols="60" rows="10">这里的输入的文本信息将被保存至HCP</textarea>
    	
        <br/><br/>
        <input type="submit" id="submit" name="submit" value="Save to HCP" />
    </form>
	<script>
	     var submitBtn = document.getElementById("submit");
	     
	     submitBtn.onclick = function (event) {
	     	var text=document.getElementById("file").value;
			console.log(text);
			
			if (text == '') {
		      	alert("Type some words please!");
		      	return false;
			}
	     };
	</script>

</html>