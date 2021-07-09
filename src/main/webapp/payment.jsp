
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  

<div>
    <div class="row">
        <h4>Danh sách sản phẩm</h4>
    </div>
    <table class="table table-striped"> 
        <thead>
            <tr>            
                <th>Tên sản phẩm</th>
                <th>Số lượng</th>
                <th>Tổng tiền</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listCarts}" var="cart">
                <tr>
                    <td>${cart.value.name}</td>
                    <td>x${cart.value.qty}</td>
                    <td>${cart.value.price * cart.value.qty} tr VND</td>
                </tr>
            </c:forEach>
            <tr>
                <td>Tổng tiền:</td>
                <td></td>
                <td>${totalPayment} tr VND</td>
            </tr>
        </tbody>
    </table>
</div>
<div>
    <div class="row">
        <h4>Xác nhận đơn hàng</h4>
    </div>
    <form:form action="/payment/add" method="POST" modelAttribute="paymentForm">
        <div class="form-group">
            <label for="">Họ và tên nhận hàng:</label>
            <form:input path="name" cssClass="form-control" id="" placeholder="Enter name"/>
            <form:errors path="name" element="li" delimiter="; " cssClass="error"/> 
        </div>
        <div class="form-group">
            <label for="">Số điện thoại:</label>
            <form:input  path="phone" cssClass="form-control" id="" placeholder="Enter Phonenumber"/>
            <form:errors path="phone" element="li" delimiter="; " cssClass="error"/> 
        </div>
        <div class="form-group">
            <label for="">Địa chỉ nhận hàng:</label>
            <form:input path="andress" class="form-control" id="" placeholder="Enter andress"/>
            <form:errors path="andress" element="li" delimiter="; " cssClass="error"/> 
        </div>
        <div class="form-group form-check">
            <input type="checkbox" class="form-check-input" id="">
            <label class="form-check-label" >Thanh toán khi nhận hàng</label>
        </div>
        <div class="text-center">
            <button type="submit" class="btn btn-primary">Đặt hàng</button>
        </div>
    </form:form>
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
<script>
    let pathName = window.location.pathname;
    if(!pathName.endsWith("payment") && !pathName.endsWith("payment/")){
        setInterval(function(){
            window.location.href = "";
        }, 1500);
    }
</script>
