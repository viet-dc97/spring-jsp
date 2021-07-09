
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  

<div class="row">
    <div class="col-sm-5 col-xs-12">
        <div style="padding: 50px 0 0 0;" class="text-center">
            <img src="${accountForm.photo}" class="img-fluid border" alt="chưa có avatar." style="width: 300px;"/>
        </div>
    </div>
    <div class="col-sm-7 col-xs-12">
        <form:form method="POST" action="/account/update" modelAttribute="accountForm" enctype="multipart/form-data">
            <form:hidden path="username" cssClass="form-control" />
            <div class="form-group">
                <label >Password: </label>
                <form:input path="password" cssClass="form-control" /> <form:errors path="password" element="li" delimiter="; " cssClass="error"/>
            </div>    
            <div class="form-group">
                <label >Full name:</label>
                <form:input path="fullname" cssClass="form-control"/> <form:errors path="fullname" element="li" delimiter="; " cssClass="error"/>
            </div>
            <div class="form-group">
                <label >Email:</label>
                <form:input path="email" cssClass="form-control" /> <form:errors path="email" element="li" delimiter="; " cssClass="error"/>
            </div><br/>
            <div class="custom-file">
                <input type="file" name="file" class="custom-file-input" id="validatedCustomFile">
                <label class="custom-file-label" >Chọn file ảnh...</label>
            </div><br/><br/>
            <div class="text-center">
                <button name="update" class="btn btn-primary">Cập nhật</button>
            </div>
        </form:form><br/>
        <c:forEach items="${error}" var="err">
            <div class="alert alert-danger" role="alert">
                ${err}
            </div>
        </c:forEach>
        <c:if test="${not empty success}">
            <div class="alert alert-success" role="alert">
                ${success}
            </div>
        </c:if>
    </div>
</div>
<script>
    let pathName = window.location.pathname;
    if(!pathName.endsWith("account/") && !pathName.endsWith("account")){
        setInterval(function(){
            window.location.href = "";
        }, 1500);
    }
</script>


