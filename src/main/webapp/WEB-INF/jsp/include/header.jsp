
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header class="hidden-xs">
    <div class="container">
        <div class="row">
            <a href="${pageContext.request.contextPath}/home">
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
                                <a href="${pageContext.request.contextPath}/collection" title="Sản phẩm">Sản phẩm <i class="fa fa-angle-down"></i></a>
                                <ul class="dropdown-menu submenu-level1-children" role="menu">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/collection" title="Giày Chính Hãng">Giày Theo Hãng <i class="fa fa-angle-right"></i></a>
                                        <ul class="dropdown-menu submenu-level2-children">
                                            <li class="">
                                                <a href="${pageContext.request.contextPath}/find-by-category?category=nike" title="Giày Nike">Giày NIKE</a>
                                            </li>
                                            <li class="">
                                                <a href="${pageContext.request.contextPath}/find-by-category?category=adidas" title="Giày Adidas">Giày ADIDAS</a>
                                            </li>
                                            <li class="">
                                                <a href="${pageContext.request.contextPath}/find-by-category?category=BALENCIAGA" title="Giày Timberland">Giày BALENCIAGA</a>
                                            </li>
                                            <li class="">
                                                <a href="${pageContext.request.contextPath}/find-by-category?category=RICK OWEN" title="Giày New Balance">Giày RICK OWEN</a>
                                            </li>
                                            <li class="">
                                                <a href="${pageContext.request.contextPath}/find-by-category?category=CONVERSE" title="Giày Zara">Giày CONVERSE</a>
                                            </li>
                                            <li class="">
                                                <a href="${pageContext.request.contextPath}/find-by-category?category=ASIC" title="Giày Domba">Giày ASIC</a>
                                            </li>
                                            <li class="">
                                                <a href="${pageContext.request.contextPath}/find-by-category?category=ZARA" title="Giày Fila">Giày ZARA</a>
                                            </li>
                                            <li class="">
                                                <a href="${pageContext.request.contextPath}/find-by-category?category=DOMPA" title="Giày Fila">Giày DOMPA</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/" title="Dép Chính Hãng">Giày Theo Size<i class="fa fa-angle-right"></i></a>
                                        <ul class="dropdown-menu submenu-level2-children">
                                            <li class="">
                                                <a href="${pageContext.request.contextPath}/find-by-sizeId?sizeId=1" title="36">36</a>
                                            </li>
                                            <li class="">
                                                <a href="${pageContext.request.contextPath}/find-by-sizeId?sizeId=2" title="37">37</a>
                                            </li>
                                            <li class="">
                                                <a href="${pageContext.request.contextPath}/find-by-sizeId?sizeId=3" title="38">38</a>
                                            </li>
                                            <li class="">
                                                <a href="${pageContext.request.contextPath}/find-by-sizeId?sizeId=4" title="39">39</a>
                                            </li>
                                            <li class="">
                                                <a href="${pageContext.request.contextPath}/find-by-sizeId?sizeId=5" title="40">40</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="">
                                        <a href="${pageContext.request.contextPath}/collection" title="Dép Chính Hãng">Giày Theo Màu<i class="fa fa-angle-right"></i></a>
                                        <ul class="dropdown-menu submenu-level2-children">
                                            <li class="">
                                                <a href="${pageContext.request.contextPath}/find-by-colorId?colorId=1" title="while-color">Trắng</a>
                                            </li>
                                            <li class="">
                                                <a href="${pageContext.request.contextPath}/find-by-colorId?colorId=2" title="black-color">Đen</a>
                                            </li>
                                            <li class="">
                                                <a href="${pageContext.request.contextPath}/find-by-colorId?colorId=3" title="red-color">Đỏ</a>
                                            </li>
                                            <li class="">
                                                <a href="${pageContext.request.contextPath}/find-by-colorId?colorId=4" title="navy-color">Navy</a>
                                            </li>
                                            <li class="">
                                                <a href="${pageContext.request.contextPath}/find-by-colorId?colorId=5" title="green-color">Xanh lá</a>
                                            </li>
                                            <li class="">
                                                <a href="${pageContext.request.contextPath}/find-by-colorId?colorId=6" title="yellow-color">Vàng</a>
                                            </li>
                                            <li class="">
                                                <a href="${pageContext.request.contextPath}/find-by-colorId?colorId=7" title="grey-color">Xám</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <sec:authorize access="isAuthenticated()">
                                        <li class="">
                                            <a href="${pageContext.request.contextPath}/my-favorite-product" title="MyFavorite">Sản phẩm yêu thích của tôi</a>
                                        </li>  
                                    </sec:authorize>
                                </ul>
                            </li>
                            <li class="">
                                <a href="${pageContext.request.contextPath}/howtochoosesize" title="Hướng dẫn chọn size giày">Hướng dẫn chọn size giày</a>
                            </li>
                            <li class="">
                                <a href="${pageContext.request.contextPath}/#" title="SALE UP TO 70%">Khuyến mãi</a>
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
                                <li>
                                    <a href="${pageContext.request.contextPath}/account">Thành viên
                                        <svg class="svg-next-icon svg-next-icon-size-24">
                                        <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#icon-user"></use>
                                        </svg>
                                    </a>
                                </li>
                                <li class="search-header">
                                    <div class="dropdown btn-group">
                                        <a href="#" data-toggle="dropdown"style="margin-top: 9px;">TÌM KIẾM... 
                                            <svg class="svg-next-icon svg-next-icon-size-24" >
                                            <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#icon-search-filter"></use>
                                            </svg>
                                        </a>
                                        <div class="dropdown-menu">
                                            <form action="${pageContext.request.contextPath}/search-product-home">
                                            <input type="hidden" name="type" value="product">
                                            <input type="text" class="form-control" name="search" placeholder="Tìm kiếm...">
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
