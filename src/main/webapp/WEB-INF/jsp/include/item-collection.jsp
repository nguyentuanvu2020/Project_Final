
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!--    <div class="col-lg-4 col-md-4 col-sm-6 col-xs-6 product-wrapper product-resize fixheight">
        <div class="product-information">
            <div class="product-detail clearfix">
                <div class="product-image image-resize">
                    <a href="product-detail-view?productId=${product.id}">
                        <picture>
                            <source media="(max-width: 991px)" srcset="${pageContext.request.contextPath}/resources/image/${product.listImageProductDetail[0].name}" class="img-rounded">
                            <source media="(min-width: 992px)" srcset="${pageContext.request.contextPath}/resources/image/${product.listImageProductDetail[0].name}"class="img-rounded">
                            <img src="${pageContext.request.contextPath}/resources/image/${product.listImageProductDetail[0].name}" class="img-rounded"/>
                        </picture>
                    </a>
                    <div class="product-pricesale-percent">-27%</div>

                    <div class="product-icon-new countdown_1020439605" style="display: none;">
                        <svg class="svg-next-icon svg-next-icon-size-36" style="fill:#d80027">
                        <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#icon-new-product"></use>
                        </svg>
                    </div>
                    <div class="box-position-quickview">
                        <div class="wrap-btn-quickview">
                            <a href="javascript:void(0);" class="quickview" data-handle="/products/giay-nike-react-presto-brutal-honey" title="Xem nhanh">Xem nhanh</a>
                        </div>
                    </div>
                </div>
                <div class="product-info">
                    <a href="/collections/giay-nike/products/giay-nike-react-presto-brutal-honey" title="Giày Nike React Presto " brutal="" honey""="">
                       <h2>${product.name}</h2>
                    </a>
                    <p class="product-vendor">${product.category.name}</p>

                    <p class="product-box-price clearfix flexbox-grid-default">
                        <span class="price-new flexbox-content text-left"><fmt:formatNumber type = "number" 
                  maxFractionDigits = "3" value = "${product.price}" />₫</span>
<span class="price-old flexbox-content text-right"><fmt:formatNumber type = "number" 
                  maxFractionDigits = "3" value = "${product.price}" />₫</span>
</p>
</div>
</div>
</div>
</div>-->
<c:forEach var="product" items="${allProduct}">
    <div class="col-lg-4 col-md-4 col-sm-6 col-xs-6 product-wrapper product-resize fixheight" style="height: 325px;">
        <div class="product-information">
            <div class="product-detail clearfix">
                <div class="product-image image-resize" style="height: 197px;">
                    <a href="product-detail-view?productId=${product.id}" title="${product.name}" >
                        <picture>
                            <source media="(max-width: 991px)" srcset="${pageContext.request.contextPath}/resources/image/${product.listImageProductDetail[0].name}" class="img-rounded">
                            <source media="(min-width: 992px)" srcset="${pageContext.request.contextPath}/resources/image/${product.listImageProductDetail[0].name}"class="img-rounded">
                            <img src="${pageContext.request.contextPath}/resources/image/${product.listImageProductDetail[0].name}" class="img-rounded"/>
                        </picture>
                    </a>
                    <c:if test="${product.listPromotion[0].discount!=null}"><div class="product-pricesale-percent">-${product.listPromotion[0].discount}%</div></c:if>

<!--                    <div class="product-icon-new countdown_1022077768" style="">
                        <svg class="svg-next-icon svg-next-icon-size-36" style="fill:#d80027">
                        <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#icon-new-product"></use>
                        </svg>
                    </div>-->
<!--                    <div class="box-position-quickview">
                        <div class="wrap-btn-quickview">
                            <a href="javascript:void(0);" class="quickview" data-handle="/products/giay-adidas-continental-80-off-white" title="Xem nhanh">Xem nhanh</a>
                        </div>
                    </div>-->
                </div>
                <div class="product-info">
                    <a href="product-detail-view?productId=${product.id}" title="${product.name}">
                       <h2>${product.name}</h2>
                    </a>
                    <p class="product-vendor">${product.category.name}</p>
                    <p class="product-box-price clearfix flexbox-grid-default">
                        <c:if test="${product.listPromotion[0].discount!=null}">
                            <span class="price-new flexbox-content text-left"><fmt:formatNumber type = "number" 
                                              maxFractionDigits = "3" value = "${product.price-(product.price*product.listPromotion[0].discount/100)}" />₫</span>
                            <span class="price-old flexbox-content text-right"><fmt:formatNumber type = "number" 
                                              maxFractionDigits = "3" value = "${product.price}" />₫</span>
                            </c:if>
                            <c:if test="${product.listPromotion[0].discount==null}">
                            <span class="price-new flexbox-content text-left"><fmt:formatNumber type = "number" 
                                              maxFractionDigits = "3" value = "${product.price}" />₫</span></c:if>
                        </p>
                    </div>
                </div>
            </div>
        </div>
</c:forEach>
