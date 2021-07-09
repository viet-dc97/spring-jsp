
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  

<c:forEach items="${listCategories}" var="category">
    <div style="padding: 0 15px; margin-bottom: 20px; background: #e9f0f7">
        <div class="row" style="padding: 20px 20px 10px 20px; background: #ccd2d8" >
            <h3><i class="bi bi-tags"></i> ${category.name.toUpperCase()}</h3>
        </div>
        <div class="row" >
            <c:forEach items="${category.listProducts}" var="product">
                <div class="col-3 text-center" style="padding: 2px;">
                    <div class="col-md-12" style="padding: 10px">
                        <img class="img-fluid" src="${product.image}" style="width: 150px"/>
                        <h4><span class="small"><i class="bi bi-phone"></i></span> ${product.name.toUpperCase()}</h4>
                        <del style="color:red">Giá ${product.price+0.2*product.price}m VND </del>
                        <p style="color:blue; margin: 0; margin-bottom: 5px">Sale up to ${product.price}m VND</p>
                        <button type="button" onclick="window.location.href='/cart/add/${product.id}'" class="btn btn-outline-primary btn-sm">
                            <i class="bi bi-cart2"></i> Thêm vào giỏ hàng</button>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</c:forEach>   