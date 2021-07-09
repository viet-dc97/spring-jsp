
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<div class="row justify-content-md-center">
    <div class="col-6">
        <form:form method="POST" modelAttribute="account-register">
            <div class="form-group">
                <label >Username:</label>
                <form:input path="username" cssClass="form-control" /> <form:errors path="username" element="li" delimiter="; " cssClass="error"/>
            </div>
            <div class="form-group">
                <label >Full name:</label>
                <form:input path="fullname" cssClass="form-control"/> <form:errors path="fullname" element="li" delimiter="; " cssClass="error"/>
            </div>
            <div class="form-group">
                <label >Email:</label>
                <form:input path="email" cssClass="form-control" /> <form:errors path="email" element="li" delimiter="; " cssClass="error"/>
            </div>
            <div class="form-group">
                <label >Password: </label>
                <form:input path="password" cssClass="form-control" /> <form:errors path="password" element="li" delimiter="; " cssClass="error"/> <br/> 
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-primary">Register</button>
            </div>
        </form:form>
    </div>
</div>
<br/>
<c:forEach items="${error}" var="err">
    <div class="alert alert-danger" role="alert">
        ${err}
    </div>
</c:forEach>
<c:forEach items="${success}" var="succ">
    <div class="alert alert-success" role="alert">
        ${succ}
    </div>
</c:forEach>
