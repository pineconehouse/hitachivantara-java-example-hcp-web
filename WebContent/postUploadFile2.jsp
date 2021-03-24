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

    <form action="http://tenant1.hcp-demo.hcpdemo.com/s3bucket" method="post" enctype="multipart/form-data">
    	<br/><br/>
        <label>Save position: </label><br>
         
        <input type="text"   name="key" value="<c:out value="${form.key}"/>" size="100" readonly /><br/><br/>
		<input type="hidden" name="AWSAccessKeyId" value="<c:out value="${form.accessKey}"/>" />
		<input type="hidden" name="Policy" value="<c:out value="${form.encodedPolicy}"/>" />
		<input type="hidden" name="Signature" value="<c:out value="${form.signature}"/>" />
        <input type="hidden" name="success_action_status" value="<c:out value="${form.successActionStatus}"/>"/>
		<input type="hidden" name="success_action_redirect" value="<c:out value="${form.successActionRedirect}"/>" />
         
		<c:if test="${not empty form.acl}">
		<input type="hidden" name="acl" value="<c:out value="${form.acl}"/>"/>
		</c:if>
		
<!-- 		<input type="hidden" name="content-type" value="plain/text" /> -->

		<c:forEach var="meta" items="${form.metadatas}" varStatus="status">
		<input type="hidden" name="<c:out value="${meta.name}"/>" value="<c:out value="${meta.value}"/>" />
		</c:forEach> 
		
        <label>Choose file: </label><br>
        <input type="file" name="file" />

         <br/><br/>
         <input type="submit" name="submit" value="Upload to HCP" />
    </form>

</html>