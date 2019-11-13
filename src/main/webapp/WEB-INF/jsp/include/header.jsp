
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="hidden-xs">
    <div class="container">
        <div class="row">
            <a href="home">
                <img src="${pageContext.request.contextPath}/resources/image/logo/logomain.png" alt="TV SHOP"/>
            </a>
        </div>
    </div>
</header>
<nav class="navbar-main navbar navbar-default cl-pri">
    <!-- MENU MAIN -->
    <div class="container nav-wrapper">
        <div class="row">
            <div class="navbar-header">				
                <div class="flexbox-grid-default hidden-lg hidden-md hidden-sm">
                    <div class="flexbox-content text-center box-logo-mobile">
                        <div class="logo-mobile">
                            <img src="${pageContext.request.contextPath}/resources/image/logo/logomain.png" style="height: 80px;" alt="TV SHOP"/>
                        </div>
                    </div>
                    <div class="flexbox-auto">
                        <div class="mobile-menu-icon-wrapper">					
                            <ul class="mobile-menu-icon clearfix">
                                <li class="search">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default dropdown-toggle icon-search" data-toggle="dropdown" aria-expanded="false">
                                            <svg class="svg-next-icon svg-next-icon-size-20">
                                            <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#icon-search-filter"></use>
                                            </svg>
                                        </button>
                                        <div class="dropdown-menu" role="menu">
                                            <div class="search-bar">
                                                <div class="">
                                                    <form class="col-md-12" action="/search">
                                                        <input type="hidden" name="type" value="product" />
                                                        <input type="text" name="q" placeholder="Tìm kiếm..." />
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li id="cart-target" class="cart">
                                    <a href="${pageContext.request.contextPath}/cart" class="cart " title="Giỏ hàng">
                                        <svg class="svg-next-icon svg-next-icon-size-20">
                                        <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#icon-cart-header"></use>
                                        </svg>			
                                        <span id="cart-count">${cart.count}</span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <div id="navbar" class="navbar-collapse collapse">
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12">
                        <ul class="nav navbar-nav clearfix flexbox-grid flexbox-justifyContent-center">
                            <li class=" current">
                                <a href="${pageContext.request.contextPath}/home" title="Trang chủ">Trang chủ</a>
                            </li>
                            <li class="">
                                <a href="collection" title="Sản phẩm">Sản phẩm <i class="fa fa-angle-down"></i></a>
                                <ul class="dropdown-menu submenu-level1-children" role="menu">
                                    <li>
                                        <a href="/collections/giay-chinh-hang" title="Giày Chính Hãng">Giày Theo Hãng <i class="fa fa-angle-right"></i></a>
                                        <ul class="dropdown-menu submenu-level2-children">
                                            <li class="">
                                                <a href="/collections/giay-nike" title="Giày Nike">Giày Nike</a>
                                            </li>
                                            <li class="">
                                                <a href="/collections/giay-adidas" title="Giày Adidas">Giày Adidas</a>
                                            </li>
                                            <li class="">
                                                <a href="/collections/giay-jordan" title="Giày Jordan">Giày Jordan</a>
                                            </li>
                                            <li class="">
                                                <a href="/collections/giay-timberland" title="Giày Timberland">Giày Timberland</a>
                                            </li>
                                            <li class="">
                                                <a href="/collections/giay-new-balance" title="Giày New Balance">Giày New Balance</a>
                                            </li>
                                            <li class="">
                                                <a href="/collections/giay-zara" title="Giày Zara">Giày Zara</a>
                                            </li>
                                            <li class="">
                                                <a href="/collections/giay-domba-chinh-hang" title="Giày Domba">Giày Domba</a>
                                            </li>
                                            <li class="">
                                                <a href="/collections/giay-fila" title="Giày Fila">Giày Fila</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li>
                                        <a href="/collections/dep-chinh-hang" title="Dép Chính Hãng">Giày Theo Size<i class="fa fa-angle-right"></i></a>
                                        <ul class="dropdown-menu submenu-level2-children">
                                            <li class="">
                                                <a href="/collections/dep-fila" title="Dép Fila">35</a>
                                            </li>
                                            <li class="">
                                                <a href="/collections/dep-fila" title="Dép Fila">36</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="">
                                        <a href="/collections/dep-chinh-hang" title="Dép Chính Hãng">Giày Theo Màu<i class="fa fa-angle-right"></i></a>
                                        <ul class="dropdown-menu submenu-level2-children">
                                            <li class="">
                                                <a href="/collections/dep-fila" title="Dép Fila">Trắng</a>
                                            </li>
                                            <li class="">
                                                <a href="/collections/dep-fila" title="Dép Fila">Đen</a>
                                            </li>
                                            <li class="">
                                                <a href="/collections/dep-fila" title="Dép Fila">Đỏ</a>
                                            </li>
                                            <li class="">
                                                <a href="/collections/dep-fila" title="Dép Fila">Navy</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="">
                                        <a href="/collections/ve-sinh-giay" title="Chăm Sóc Giày">Chăm Sóc Giày</a>
                                    </li>
                                </ul>
                            </li>
                            <li class="">
                                <a href="/pages/huong-dan-chon-size-giay" title="Hướng dẫn chọn size giày">Hướng dẫn chọn size giày</a>
                            </li>
                            <li class="">
                                <a href="/collections/giay-khuyen-mai" title="SALE UP TO 70%">Khuyến mãi</a>
                            </li>
                            <li id="cart-target" class="cart">
                                <a href="${pageContext.request.contextPath}/cart" class="cart " title="Giỏ hàng">Giỏ Hàng
                                    <svg class="svg-next-icon svg-next-icon-size-24">
                                    <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#icon-cart-header"></use>
                                    </svg>					
                                    <c:if test="${cart!=null}"><span id="cart-count2" class="csscart">${cart.count}</span></c:if>
                                    <c:if test="${cart==null}"><span id="cart-count2" class="csscart">0</span></c:if>
                                </a>
                            </li>
                            <li class="">
                                <a href="#" title="Sản phẩm">Login<i class="fa fa-angle-down"></i></a>
                                <ul class="dropdown-menu submenu-level1-children" role="menu">
                                    <li>
                                        <a href="account">Thành viên
                                            <svg class="svg-next-icon svg-next-icon-size-24">
                                            <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#icon-user"></use>
                                            </svg>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="management/">Quản trị
                                            <svg class="svg-next-icon svg-next-icon-size-24">
                                            <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#icon-user"></use>
                                            </svg>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li class="search-header">
                                <div class="dropdown btn-group">
                                    <a href="#" data-toggle="dropdown"style="margin-top: 9px;">TÌM KIẾM... 
                                        <svg class="svg-next-icon svg-next-icon-size-24" >
                                        <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#icon-search-filter"></use>
                                        </svg>
                                    </a>
                                    <div class="dropdown-menu">
                                        <form action="/search">
                                            <input type="hidden" name="type" value="product">
                                            <input type="text" class="form-control" name="q" placeholder="Tìm kiếm...">
                                        </form>
                                    </div>
                                </div>
                            </li>

                        </ul>
                    </div>
                </div>				
            </div>
        </div>
    </div>
</nav>