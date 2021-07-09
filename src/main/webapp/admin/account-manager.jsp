
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="col-8 offset-2">
<form:form method="POST" action="/admin/account-manager/add" modelAttribute="accountForm" enctype="multipart/form-data">
    <div class="form-group">
        <label >Username:</label>
        <form:input path="username" cssClass="form-control" /> <form:errors path="username" element="li" delimiter="; " cssClass="error"/>
    </div>
    <div class="form-group">
        <label >Password: </label>
        <form:input path="password" cssClass="form-control" /> <form:errors path="password" element="li" delimiter="; " cssClass="error"/>
    </div>    
    <div class="form-group">
        <label>Full name:</label>
        <form:input path="fullname" cssClass="form-control"/> <form:errors path="fullname" element="li" delimiter="; " cssClass="error"/>
    </div>
    <div class="form-group">
        <label>Email:</label>
        <form:input path="email" cssClass="form-control" /> <form:errors path="email" element="li" delimiter="; " cssClass="error"/>
    </div><br/>
    <div class="custom-file">
        <input type="file" name="file" class="custom-file-input" id="validatedCustomFile">
        <label class="custom-file-label" >Chọn file ảnh...</label>
    </div><br/><br/>
    <div class="form-inline">
        <label class="my-1 mr-2">Quyền:</label>
        <form:select cssClass="custom-select my-1 mr-sm-2" path="role">
            <c:choose>
                <c:when test="${accountForm.status}">
                    <form:option value="true" selected="true">Admin</form:option>
                    <form:option value="false">Khách hàng</form:option>
                </c:when>
                <c:otherwise>
                    <form:option value="true" selected="true">Admin</form:option>
                    <form:option value="false">Khách hàng</form:option>
                </c:otherwise>
            </c:choose>
        </form:select>
    </div><br/>
    <div class="form-inline">
        <label class="my-1 mr-2">Tình trạng:</label>
        <form:select cssClass="custom-select my-1 mr-sm-2" path="status">
            <c:choose>
                <c:when test="${accountForm.status}">
                    <form:option value="true" selected="true">Kích hoạt</form:option>
                    <form:option value="false">Bỏ kích hoạt</form:option>
                </c:when>
                <c:otherwise>
                    <form:option value="true" selected="true">Kích hoạt</form:option>
                    <form:option value="false">Bỏ kích hoạt</form:option>
                </c:otherwise>
            </c:choose>
        </form:select>
    </div><br/>
    <div class="text-right">
        <button name="add" class="btn btn-primary">Thêm</button>
        <button formaction="/admin/action-manager/delete" class="btn btn-danger" >Xóa</button>
        <button formaction="/admin/action-manager" formmethod="GET" class="btn btn-warning">Reset</button>
    </div>
</form:form>
</div><br/>
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
<table class="table table-striped"> 
    <thead>
        <tr>
            <th><a href="?col-sort=username&type-sort=${typeSort}&page=${page}">username</a></th>
            <th><a href="?col-sort=password&type-sort=${typeSort}&page=${page}">password</a></th>
            <th><a href="?col-sort=fullname&type-sort=${typeSort}&page=${page}">fullname</a></th>
            <th><a href="?col-sort=email&type-sort=${typeSort}&page=${page}">email</a></th>
            <th><a href="?col-sort=photo&type-sort=${typeSort}&page=${page}">Photo</a></th>
            <th><a href="?col-sort=role&type-sort=${typeSort}&page=${page}">role</a></th>
            <th><a href="?col-sort=status&type-sort=${typeSort}&page=${page}">status</a></th>
            <th>action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${listAccounts}" var="account">
            <tr>
                <th>${account.username}</th>
                <td>${account.password}</td>
                <td>${account.fullname}</td>
                <td>${account.email}</td>
                <td><img src="${account.photo}" alt="avatar" style="width: 50px"/></td>
                <td>${account.role?"Admin":"Khách hàng"}</td>
                <td>${account.status}</td>
                <td><a href="?edit=${account.username}&page=${page}"><i class="far fa-edit"></i>Edit</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<div class="col offset-5">
<nav aria-label="Page navigation example ">
    <ul class="pagination">
        <li class="page-item">
            <a class="page-link" href="?page=${page-1}" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
                <span class="sr-only">Previous</span>
            </a>
        </li>
        <li class="page-item"><a class="page-link" href="?page=${page}">${page+1}</a></li>
        <li class="page-item"><a class="page-link" href="?page=${page+1}">${page+2}</a></li>
        <li class="page-item"><a class="page-link" href="?page=${page+1}">${page+3}</a></li>
        <li class="page-item">
            <a class="page-link" href="?page=${page+1}" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
                <span class="sr-only">Next</span>
            </a>
        </li>
    </ul>
</nav>
</div>
<script>
    let pathName = window.location.pathname;
    if(!pathName.endsWith("account-manager/") && !pathName.endsWith("account-manager")){
        setInterval(function(){
            window.location.href = "";
        }, 1500);
    }
</script>
