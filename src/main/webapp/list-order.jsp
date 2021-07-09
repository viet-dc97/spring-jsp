
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<h3>Danh sách hóa đơn đã đặt hàng</h3><br/>
<table class="table table-striped">
    <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col"><a href="?page=${page}&type-sort=${typeSort}&col-sort=id">Mã Hóa đơn</a></th>
            <th scope="col"><a href="?page=${page}&type-sort=${typeSort}&col-sort=time">Ngày mua hàng</a></th>
            <th scope="col">Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${listOrders}" var="order" varStatus="status">
            <tr>
                <th scope="row">${status.index+1}</th>
                <td>${order.id}</td>
                <td>
                    ${order.time} 
                </td>
                <td><button type="button" onclick="window.location.href='?view-detail=${order.id}&page=${page}'" class="btn btn-primary btn-sm">Xem chi tiết</button></td>
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
<c:if test="${not empty idOrder}">
    <h3>Chi tiết hóa đơn #${idOrder}</h3>
    <br/>
    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Mã đơn hàng</th>
                <th scope="col">Tên sản phẩm</th>
                <th scope="col">Giá tiền</th>
                <th scope="col">Số lượng</th>
                <th scope="col">Tổng tiền</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listOrderDetails}" var="orderDetail" varStatus="status">
                <tr>
                    <th scope="row">${status.index+1}</th>
                    <td>${orderDetail.id}</td>
                    <td>${orderDetail.product.name}</td>
                    <td>${orderDetail.price} tr VND</td>
                    <td>${orderDetail.quantity}</td>
                    <td>${orderDetail.quantity * orderDetail.price} tr VND</td>
                </tr>
            </c:forEach>
            <tr>
                <th scope="row">Tổng tiền:</th>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>${totalMoneyOrder} tr VND</td>
            </tr>
        </tbody>
    </table>
</c:if>

