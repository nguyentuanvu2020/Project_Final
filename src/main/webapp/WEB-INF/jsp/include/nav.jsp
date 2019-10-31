
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar-main navbar navbar-default cl-pri">
    <!-- MENU MAIN -->
    <div class="container nav-wrapper">
        <div class="row">
            <div class="navbar-header">				
                <div class="flexbox-grid-default hidden-lg hidden-md hidden-sm">
                    <div class="flexbox-content text-center box-logo-mobile">
                        <div class="logo-mobile">
                            <a href="https://hangchinhhanggiare.com" title="Hàng Chính Hãng Giá Rẻ">
                                <img src="${pageContext.request.contextPath}/resources/image/logo/tvshop.jpg" alt="TV SHOP" style="height: 100px;"/>
                            </a>
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
                                    <a href="/cart" class="cart " title="Giỏ hàng">
                                        <svg class="svg-next-icon svg-next-icon-size-20">
                                        <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#icon-cart-header"></use>
                                        </svg>			
                                        <span id="cart-count">0</span>
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
                                <a href="/" title="Trang chủ">Trang chủ</a>
                            </li>
                            <li class="">
                                <a href="/collections/all" title="Sản phẩm">Sản phẩm <i class="fa fa-angle-down"></i></a>
                                <ul class="dropdown-menu submenu-level1-children" role="menu">
                                    <li>
                                        <a href="/collections/giay-chinh-hang" title="Giày Chính Hãng">Giày Chính Hãng <i class="fa fa-angle-right"></i></a>
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
                                        <a href="/collections/dep-chinh-hang" title="Dép Chính Hãng">Dép Chính Hãng <i class="fa fa-angle-right"></i></a>
                                        <ul class="dropdown-menu submenu-level2-children">
                                            <li class="">
                                                <a href="/collections/dep-fila" title="Dép Fila">Dép Fila</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="">
                                        <a href="/collections/balo-chinh-hang" title="Balo Chính Hãng">Balo Chính Hãng</a>
                                    </li>
                                    <li class="">
                                        <a href="/collections/ve-sinh-giay" title="Chăm Sóc Giày">Chăm Sóc Giày</a>
                                    </li>
                                </ul>
                            </li>
                            <li class="">
                                <a href="/pages/about-us" title="Giới thiệu">Giới thiệu</a>
                            </li>
                            <li class="">
                                <a href="/pages/huong-dan-chon-size-giay" title="Hướng dẫn chọn size giày">Hướng dẫn chọn size giày</a>
                            </li>
                            <li class="">
                                <a href="/pages/huong-dan-dat-hang" title="Hướng dẫn đặt hàng">Hướng dẫn đặt hàng</a>
                            </li>
                            <li class="">
                                <a href="/collections/giay-khuyen-mai" title="SALE UP TO 70%">SALE UP TO 70%</a>
                            </li>
                        </ul>
                    </div>
                </div>				
            </div>
        </div>
    </div>
</nav>