<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>JAVA 5</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="/assets/css/css.css"/>
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
</head>
<body class="container">
<header class="bg-dark">
    <div id="carouselExampleIndicators" class="carousel" data-ride="carousel" style="height: 400px">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="d-block w-100" style="height: 400px" src="https://cdn.tgdd.vn/2021/05/banner/euro-oppo-830-300-830x300.png" alt="First slide">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" style="height: 400px" src="https://cdn.tgdd.vn/2021/06/banner/hotsale-830-300-830x300-1.png" alt="Second slide">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" style="height: 400px" src="https://cdn.tgdd.vn/2021/06/banner/830-300-830x300-1.png" alt="Third slide">
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</header>
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #fed100">
    <a class="navbar-brand" href="/">
        <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/f/fe/Shopee.svg/2560px-Shopee.svg.png" style="width: 100px" alt=""> </a>
    <div class="navbar navbar-collapse">
        <li class="navbar-nav ml-auto">
        <li class="nav-item">
            <c:if test="${countShoppingCart>0}">
        <li class="nav-item">
            <a class="nav-link" href="/shopping-cart" ><i class="bi bi-cart2"></i>
                Cart ${countShoppingCart}</a>
        </li>
        </c:if>
        <c:if test="${account.role==true}">
            <li class="nav-item">
                <a class="nav-link" href="/admin/category-manager"><i class="bi bi-bookmark-check"></i> Category</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/product-manager"><i class="bi bi-bookmark-heart"></i> Product</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/account-manager"><i class="bi bi-people"></i> Account</a>
            </li>
<%--            <li class="nav-item">--%>
<%--                <a class="nav-link" href="/admin/analysis-manager"><i class="bi bi-clipboard-data"></i> AnalysisMNG</a>--%>
<%--            </li>--%>
        </c:if>
        <c:if test="${account == null}">
            <li class="nav-item">
                <a class="nav-link" href="/login"><i class="bi bi-box-arrow-in-left"></i> Đăng nhập</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/register"><i class="bi bi-person-plus-fill"></i> Đăng kí</a>
            </li>
        </c:if>
        <c:if test="${account != null}">
            <li class="nav-item">
                <a class="nav-link" href="/list-order"><i class="bi bi-card-checklist"></i> Đơn hàng</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/account"><i class="bi bi-person-circle"></i> Tài khoản</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout"><i class="bi bi-box-arrow-right"></i> Đăng xuất</a>
            </li>
        </c:if>
        </li>

        </ul>
    </div>
</nav>
<%--<div style="padding: 20px 0">--%>
