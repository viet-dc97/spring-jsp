
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<div class="col-8 offset-2">
<form:form method="POST" action="/admin/product-manager/add" modelAttribute="productForm" enctype="multipart/form-data">

    <div class="form-inline">
        <label class="my-1 mr-2" >Thể loại sản phẩm:</label>
        <form:select cssClass="custom-select" path="categoryId">
            <c:forEach items="${listCategory}" var="category">
                <c:choose>
                    <c:when test="${category.id == productForm.categoryId}">
                        <form:option selected="true" value="${category.id}">${category.name}</form:option>
                    </c:when>
                    <c:otherwise>
                        <form:option value="${category.id}">${category.name}</form:option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
    </div>
    <div class="form-group" hidden>
        <label>id:</label>
        <form:input path="id" cssClass="form-control"/> <form:errors path="id" element="li" delimiter="; " cssClass="error"/>
    </div>
    <div class="form-group">
        <label>Name:</label>
        <form:input path="name" cssClass="form-control"/> <form:errors path="name" element="li" delimiter="; " cssClass="error"/>
    </div>
    <div class="form-group">
        <label >Price:</label>
        <form:input path="price" cssClass="form-control" /> <form:errors path="price" element="li" delimiter="; " cssClass="error"/> 
    </div><br/>
    <div class="custom-file">
        <input type="file" name="file" class="custom-file-input">
        <label class="custom-file-label">Chọn file ảnh sản phẩm...</label>
    </div><br/><br/>
    <div class="form-inline">
        <label class="my-1 mr-2">Tình trạng:</label>
        <form:select path="status" cssClass="custom-select">
            <c:choose>
                <c:when test="${productForm.status}">
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
        <button class="btn btn-primary" name="add">Thêm</button>
        <button class="btn btn-danger" formaction="/admin/product-manager/delete" >Xóa</button>
        <button class="btn btn-warning" formaction="/admin/product-manager" formmethod="get">Reset</button>
    </div><br/>
</form:form>
</div>
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
            <th><a href="?col-sort=id&type-sort=${typeSort}&page=${page}">id</a></th>
            <th><a href="?col-sort=name&type-sort=${typeSort}&page=${page}">name</a></th>
            <th><a href="?col-sort=price&type-sort=${typeSort}&page=${page}">price</a></th>
            <th><a href="?col-sort=category&type-sort=${typeSort}&page=${page}">type</a></th>
            <th><a href="?col-sort=image&type-sort=${typeSort}&page=${page}">img</a></th>
            <th><a href="?col-sort=status&type-sort=${typeSort}&page=${page}">status</a></th>
            <th>action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${listProduct}" var="list">
            <tr>
                <th>${list.id}</th>
                <td>${list.name}</td>
                <td>${list.price}</td>
                <td>${list.category.name}</td>
                <td><img src="${list.image}" alt="avatar" style="width: 50px"/></td>
                <td>${list.status}</td>
                <td><a href="?edit=${list.id}&page=${page}"><i class="far fa-edit"></i>Edit</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<div class="col offset-5">
<nav aria-label="Page navigation example">
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
    if(!pathName.endsWith("product-manager/") && !pathName.endsWith("product-manager")){
        setInterval(function(){
            window.location.href = "";
        }, 1500);
    }
</script>
