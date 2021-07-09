
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<table class="table table-striped">
    <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Sản phẩm</th>
            <th scope="col">Số lượng</th>
            <th scope="col">Tổng tiền</th>
            <th scope="col">Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${listCarts}" var="cart">
            <tr>
                <th scope="row">1</th>
                <td>${cart.value.name}</td>
                <td>
                    <button type="button" onclick="window.location.href='/shopping-cart/down/${cart.value.id}/count/1'" class="btn btn-danger btn-sm">-</button> 
                    ${cart.value.qty} 
                    <button type="button" onclick="window.location.href='/shopping-cart/up/${cart.value.id}/count/1'" class="btn btn-success btn-sm">+</button><!-- comment -->
                </td>
                <td>${cart.value.qty * cart.value.price} tr VND</td>
                <td><button type="button" onclick="window.location.href='/shopping-cart/remove/${cart.value.id}'" class="btn btn-danger btn-sm">Xóa</button></td>
            </tr>
        </c:forEach>
        <tr>
            <th scope="row"></th>
            <td></td>
            <td></td>
            <td>Tổng: ${totalPayment} tr VND</td>
            <td><button type="button" onclick="window.location.href='/payment'" class="btn btn-primary btn-sm">Thanh toán</button></td>
        </tr>
    </tbody>
</table>
