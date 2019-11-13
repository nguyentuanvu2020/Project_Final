
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main>
    <div class="header-navigate clearfix mb15">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 pd5">
                    <ol class="breadcrumb breadcrumb-arrow">
                        <li><a href="home" target="_self">Trang chủ</a></li>
                        <li><i class="fa fa-angle-right"></i></li>
                        <li><a href="collection" target="_self">Giày Chính Hãng</a></li>
                        <li><i class="fa fa-angle-right"></i></li>
                        <li class="active"><span>${product.name}</span></li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
    <section id="product" class="clearfix">
        <div class="container">
            <div class="row">
                <div id="surround" class="col-lg-6 col-md-6">
                    <div id="slider-menu" class="slider-menu">
                        <div class="owl-carousel">
                            <c:forEach var="image" items="${product.listImageProductDetail}">
                                <div class="item active">
                                    <picture>
                                        <source media="(max-width: 800px)" srcset="${pageContext.request.contextPath}/resources/image/${image.name}">
                                        <source media="(min-width: 600px)" srcset="${pageContext.request.contextPath}/resources/image/${image.name}">
                                        <img src="${pageContext.request.contextPath}/resources/image/${image.name}" alt="slide 1" title="slide 1">
                                    </picture>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 pd5 information-product">
                    <div class="product-title">
                        <h1>${product.name}</h1>
                    </div>
                    <div class="product-price" id="price-preview"><span><fmt:formatNumber type = "number" 
                                                                                          maxFractionDigits = "3" value = "${product.price}" />₫</span><del><fmt:formatNumber type = "number" 
                                                                                                            maxFractionDigits = "3" value = "${product.price}" />₫</del></div>
                    <form  action="buynow" method="post" class="variants clearfix variant-style">
                        <input type="hidden" value="${product.id}" name="productId">
                        <div class="select clearfix">
                            <div class="selector-wrapper opt1">
                                <style>
                                    .radio-toolbar {
                                        margin-top: 10px;
                                    }

                                    .radio-toolbar input[type="radio"] {
                                        opacity: 0;
                                        position: fixed;
                                        width: 0;
                                    }

                                    .radio-toolbar label {
                                        display: inline-block;
                                        background-color: white;
                                        padding: 10px 20px;
                                        font-family: sans-serif, Arial;
                                        font-size: 16px;
                                        border: 1px solid grey;
                                        border-radius: 4px;
                                    }

                                    .radio-toolbar label:hover {
                                        /*background-color: #7dc558;*/
                                        border: 1px solid red;
                                    }

                                    .radio-toolbar input[type="radio"]:focus + label {
                                        border: 1px solid #444;
                                    }
                                    .radio-toolbar input[type="radio"]:disabled + label {
                                        border: 1px solid #444;
                                        opacity: 0.2;
                                    }
                                    .radio-toolbar input[type="radio"]:checked + label {
                                        border: 1px solid red;
                                        background: url(${pageContext.request.contextPath}/resources/image/brand/bg-variant-checked.png?v=151) no-repeat right bottom #fff;
                                    }
                                </style>
                                <div class="radio-toolbar">
                                    <div class="flex items-center _25DJo1" id="checkFavorite"onclick="checkFavorite()" style="display: flex">
                                        <svg width="24" height="20" class="_10K0Ee">
                                        <path d="M19.469 1.262c-5.284-1.53-7.47 4.142-7.47 4.142S9.815-.269 4.532 1.262C-1.937 3.138.44 13.832 12 19.333c11.559-5.501 13.938-16.195 7.469-18.07z" 
                                              stroke="#FF424F" stroke-width="1.5" fill="${style}" fill-rule="evenodd" stroke-linejoin="round" id="on"></path>
                                        </svg><div class="_1-aYcb"> Đã thích (<span id="totalF">${favoriteTotal}</span>)</div></div>
                                    <sec:authorize access="isAuthenticated()">    
                                        <script>
                                            let a = document.getElementById('on');
                                            let check = a.style.fill;
                                            function checkFavorite() {
                                                if (check === "none") {
                                                    var xhttp;
                                                    xhttp = new XMLHttpRequest();
                                                    xhttp.open("GET", "add-favorite?&productId=${product.id}", true);
                                                    xhttp.send();
                                                    xhttp.onreadystatechange = function () {
                                                        if (this.readyState === 4 && this.status === 200) {
                                                            document.getElementById('totalF').innerHTML = this.responseText;
                                                        }
                                                    };
                                                    a.style.fill = "#FF424F";
                                                    check = "#FF424F";

                                                } else {
                                                    var xhttp;
                                                    xhttp = new XMLHttpRequest();
                                                    xhttp.open("GET", "remove-favorite?&productId=${product.id}", true);
                                                    xhttp.send();
                                                    xhttp.onreadystatechange = function () {
                                                        if (this.readyState === 4 && this.status === 200) {
                                                            document.getElementById('totalF').innerHTML = this.responseText;
                                                        }
                                                    };
                                                    a.style.fill = "none";
                                                    check = "none";
                                                }
                                            }
                                        </script>
                                    </sec:authorize>
                                </div>
                                <div class="radio-toolbar">
                                    <span>(${avgRate})</span>
                                    <c:forEach var="x" begin="1" end="5" >
                                        <c:if test="${x<=Math.round(avgRate)}">
                                            <span class="fa fa-star checked"></span>     
                                        </c:if>
                                        <c:if test="${x>Math.round(avgRate)}">
                                            <span class="fa fa-star"></span>     
                                        </c:if>
                                    </c:forEach>    
                                    <span>Đánh giá(${reviewTotal})</span>

                                    <style>
                                        .checked {
                                            color: orange;
                                        }
                                    </style>
                                </div> 
                                <div class="radio-toolbar">
                                    <p>Chọn màu</p>
                                    <c:forEach var="colors" items="${listColor}">
                                        <input required="" type="radio" id="color${colors.productColor}" name="colorId" value="${colors.id}" class="productColor"onclick="checkColor(${colors.id})">
                                        <label for="color${colors.productColor}">${colors.productColor}</label>
                                    </c:forEach>
                                </div>
                                <div class="radio-toolbar">
                                    <p>Chọn size</p>
                                    <c:forEach var="sizes" items="${listSize}">
                                        <input required="" type="radio" id="color${sizes.productSize}" name="sizeId" value="${sizes.id}" class="productSize" onclick="checkSize(${sizes.id})">
                                        <label for="color${sizes.productSize}">${sizes.productSize}</label>
                                    </c:forEach>
                                </div>
                                <div class="radio-toolbar">
                                    <p>Chọn Số lượng</p>
                                    <input type="number" style="height: 40px;width: 100px;border: 1px solid grey;
                                           border-radius: 4px 4px;text-align: center;" max="100" min="1" value="1" 
                                           name="quantity" required="" id="quantity9" 
                                           class="quantityvalidate"><span id="quantity-remaining"></span>
                                </div>
                            </div>
                        </div>
                        <div class="clearfix">
                            <button class="btn-style-add add-to-cart" onclick="addCart()" type="button">
                                <span class="icon_cart_btn"></span>
                                <span>Thêm vào giỏ</span>
                            </button>
                            <button class="btn-style-buynow addnow" type="sumit" onclick="buyNow()">
                                <span class="icon_cart_btn"></span>
                                <span>Mua ngay</span>
                            </button>
                        </div>
                        <div class="alert alert-danger" role="alert" id="alertcheck" style="display: none">
                        </div>
                        <div class="alert alert-success" role="alert" id="alertcheck2" style="display: none">
                        </div>
                        <style>
                            .variant-style .btn-style-add .icon_cart_btn, .variant-style .btn-style-buynow .icon_cart_btn {
                                background: url('${pageContext.request.contextPath}/resources/image/brand/icon_cart_btn.png') center center no-repeat #7dc558;
                                width: 48px;
                                height: 48px;
                                position: absolute;
                                left: 0;
                                top: 0;
                                border-radius: 3px 0 0 3px;
                            }
                            .variant-style .btn-style-buynow {
                                background: #05b2e9;
                                margin-right: 0;
                            }
                            .variant-style .btn-style-buynow .icon_cart_btn {
                                background: url('${pageContext.request.contextPath}/resources/image/brand/icon-checkout.png') center center no-repeat #05b2e9;
                            }
                            .variant-style button.btn-style-add:hover {
                                background: #49aa47;
                                color: #ffffff;
                            }
                            .variant-style button.btn-style-buynow:hover {
                                background: #067dc2;
                                color: #ffffff;
                            }
                        </style>
                    </form>

                </div>
                <div class="col-lg-2 col-xs-12 pd-none-box-service mb15">
                    <div class="box-service-product">
                        <div class="header-box-service-product text-center">
                            <div class="title">Sẽ có tại nhà bạn</div>
                            <div class="content">từ 1-3 ngày làm việc</div>
                        </div>
                        <div class="content-box-service-product row">

                            <div class="col-lg-12 col-sm-3 col-xs-12">
                                <div class="border-service-product">
                                    <div class="flexbox-grid-default">
                                        <div class="flexbox-auto-45px flexbox-align-self-center">
                                            <img src="//theme.hstatic.net/1000243581/1000361905/14/icon-service-1.png?v=157">
                                        </div>
                                        <div class="flexbox-content des-service-product">
                                            <div class="title">Giao hàng nhanh</div>
                                            <div class="content">Nhận hàng trong ngày ở nội thành</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12 col-sm-3 col-xs-12">
                                <div class="border-service-product">
                                    <div class="flexbox-grid-default">
                                        <div class="flexbox-auto-45px flexbox-align-self-center">
                                            <img src="//theme.hstatic.net/1000243581/1000361905/14/icon-service-3.png?v=157">
                                        </div>
                                        <div class="flexbox-content des-service-product">
                                            <div class="title">Thanh toán</div>
                                            <div class="content">Nhận hàng và kiểm tra hàng trước khi thanh toán</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12 col-sm-3 col-xs-12">
                                <div class="border-service-product">
                                    <div class="flexbox-grid-default">
                                        <div class="flexbox-auto-45px flexbox-align-self-center">
                                            <img src="//theme.hstatic.net/1000243581/1000361905/14/icon-service-4.png?v=157">
                                        </div>
                                        <div class="flexbox-content des-service-product">
                                            <div class="title">Hỗ trợ online</div>
                                            <div class="content">Goi 0986 888 888 <br>
                                                Từ 7h30-21h30</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-xs-12 pd5">
                    <div class="product-comment">
                        <!-- Nav tabs -->
                        <ul class="product-tablist nav nav-tabs" id="tab-product-template"><li class="dropdown pull-right tabdrop hide"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-bars"></i> <b class="caret"></b></a><ul class="dropdown-menu"></ul></li>
                            <li class="active">
                                <a data-toggle="tab" data-spy="scroll" href="#description">
                                    <span>Mô tả sản phẩm</span>
                                </a>
                            </li>
                        </ul>
                        <!-- Tab panes -->
                        <div id="description">										
                            <div class="container-fluid product-description-wrapper">
                                <p>${product.description}</p>
                            </div>
                        </div>

                        <div id="comment">
                            <div class="group-index mb15">
                                <div class="title-block">
                                    <h3 id="quantitymaxxx" class="title-group"></h3>
                                </div>
                                <div class="title-block">
                                    <h3 class="title-group">Bình luận</h3>
                                </div>
                            </div>
                            <c:forEach var="review" items="${listReview}">
                                <div class="container-fluid">
                                    <div class="row">
                                        <h4>Khách hàng: ${review.account.name}</h4>
                                        <p>Phân loại hàng: ${review.typeOfShoes}    ${review.dateReview}</p>
                                        <p></p>
                                        <p><c:forEach begin="1" end="${review.rate}"><span class="fa fa-star checked"></span></c:forEach></p>

                                            <p>${review.content}</p>
                                        <hr>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>	
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>