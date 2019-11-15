<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en" class="flexbox">
    <head>
        <link rel="shortcut icon" href="//theme.hstatic.net/1000243581/1000361905/14/favicon.png?v=151" type="image/png" />
        <title>
            VH Sneaker - Thanh toán đơn hàng
        </title>
        <meta name="description" content="H&#224;ng Ch&#237;nh H&#227;ng Gi&#225; Rẻ - Thanh to&#225;n đơn h&#224;ng" />
        <link href='//hstatic.net/0/0/global/checkouts.css?v=1.1' rel='stylesheet' type='text/css'  media='all'  />
        <link href='//theme.hstatic.net/1000243581/1000361905/14/check_out.css?v=151' rel='stylesheet' type='text/css'  media='all'/>
        <style>
            .mainbar-qr h2 {
                text-align: center;
                font-size: 16px;
                margin-bottom: 30px;
                font-weight: 500;
                color: #212121;
            }
            .mainbar-info .mainbar-qr_section {
                text-align: center;
            }
            .mainbar-info .mainbar-qr_section .count_time {
                margin-bottom: 10px;
                margin-top: 30px;
            }
            .mainbar-info .mainbar-qr_section .count_time .time-out {
                color: #008fe5;
            }
            .mainbar-info .mainbar-qr_section .count_time .count_text {
                margin-bottom: 10px;
            }
            .mainbar-info .mainbar-qr_section .count_time .time {
                display: inline-block;
                position: relative;
                font-style: italic;
                font-size:12px;
            }
            .mainbar-info .mainbar-qr_section .count_time .time i {
                width: 10px;
                height: 10px;
                border: 1px solid #333;
                border-bottom-color: transparent;
                border-radius: 100%;
                position: absolute;
                top: 50%;
                left: -14px;
                margin-top: -6px;
                margin-left: -6px;
                opacity: 1;
                -ms-filter: "progid:DXImageTransdiv.Microsoft.Alpha(Opacity=" 0 ")";
                filter: alpha(opacity=0);
                -webkit-animation: rotate 0.5s linear infinite;
                animation: rotate 0.5s linear infinite;
                opacity: 1;
                -ms-filter: "progid:DXImageTransdiv.Microsoft.Alpha(Opacity=" 100 ")";
                filter: alpha(opacity=100);
            }
            .mainbar-info .mainbar-qr_instruction {
                max-width: 300px;
                margin: auto;
                text-align: center;
                color: #212121;
                line-height: 24px;
            }
            .redeem-login {
                display: flex;
                align-items: center;
                justify-content: space-between;
            }
            .redeem-login-title {
                position: relative;
                display: flex;
                flex-wrap: wrap;
            }
            .redeem-login-title h2 {
                color: #333;
                margin-right: 5px;
            }
            .redeem-login-btn a {
                display: inline-block;
                border-radius: 4px;
                font-weight: 500;
                padding: 13px 10px;
                background: #338dbc;
                color: #fff;
                width: 82px;
                text-align: center;
            }
            .redeem-div-used
            {
                padding-top : 10px;
            }
            .btn-redeem-loading .btn-redeem-spinner {
                -webkit-animation: rotate 0.5s linear infinite;
                animation: rotate 0.5s linear infinite;
                opacity: 1;
                -ms-filter: "progid:DXImageTransdiv.Microsoft.Alpha(Opacity=" 100 ")";
                filter: alpha(opacity=100);
            }
            .icon-redeem-button-spinner {
                position: absolute;
                top: 0;
                opacity: 0;
                right: -25px;
                width: 12px;
                height: 12px;
                border: 2px solid #999999;
                border-bottom-color: transparent;
                border-radius: 100%;
            }
            .total-line-table-footer {
                white-space: nowrap;
            }
            .row-align-top {
                vertical-align: top;
            }
        </style>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=2, user-scalable=no">
    </head>
    <body>
        <!--        logo phone
                <div class="banner">
                    <div class="wrap">
                        <a href="home" class="logo">
                            <h1 class="logo-text">V&H Sneaker</h1>
                        </a>
                    </div>
                </div>
                end
                <button class="order-summary-toggle order-summary-toggle-hide">
                    <div class="wrap">
                        <div class="order-summary-toggle-inner">
                            <div class="order-summary-toggle-icon-wrapper">
                                <svg width="20" height="19" xmlns="http://www.w3.org/2000/svg" class="order-summary-toggle-icon"><path d="M17.178 13.088H5.453c-.454 0-.91-.364-.91-.818L3.727 1.818H0V0h4.544c.455 0 .91.364.91.818l.09 1.272h13.45c.274 0 .547.09.73.364.18.182.27.454.18.727l-1.817 9.18c-.09.455-.455.728-.91.728zM6.27 11.27h10.09l1.454-7.362H5.634l.637 7.362zm.092 7.715c1.004 0 1.818-.813 1.818-1.817s-.814-1.818-1.818-1.818-1.818.814-1.818 1.818.814 1.817 1.818 1.817zm9.18 0c1.004 0 1.817-.813 1.817-1.817s-.814-1.818-1.818-1.818-1.818.814-1.818 1.818.814 1.817 1.818 1.817z"></path></svg>
                            </div>
                            <div class="order-summary-toggle-text order-summary-toggle-text-show">
                                <span>Hiển thị thông tin đơn hàng</span>
                                <svg width="11" height="6" xmlns="http://www.w3.org/2000/svg" class="order-summary-toggle-dropdown" fill="#000"><path d="M.504 1.813l4.358 3.845.496.438.496-.438 4.642-4.096L9.504.438 4.862 4.534h.992L1.496.69.504 1.812z"></path></svg>
                            </div>
                            <div class="order-summary-toggle-text order-summary-toggle-text-hide">
                                <span>Ẩn thông tin đơn hàng</span>
                                <svg width="11" height="7" xmlns="http://www.w3.org/2000/svg" class="order-summary-toggle-dropdown" fill="#000"><path d="M6.138.876L5.642.438l-.496.438L.504 4.972l.992 1.124L6.138 2l-.496.436 3.862 3.408.992-1.122L6.138.876z"></path></svg>
                            </div>
                            <div class="order-summary-toggle-total-recap" data-checkout-payment-due-target="360000000">
                                <span class="total-recap-final-price">3,600,000₫</span>
                            </div>
                        </div>
                    </div>
                </button>
                <div class="content content-second">
                    <div class="wrap">
                        <div class="sidebar sidebar-second">
                            <div class="sidebar-content">
                                <div class="order-summary">
                                    <div class="order-summary-sections">
                                        <div class="order-summary-section order-summary-section-discount" data-order-summary-section="discount">
                                            <div id="div_discount_add" accept-charset="UTF-8" method="post">
                                                <input name="utf8" type="hidden" value="✓">
                                                    <div class="fieldset">
                                                        <div class="field  ">
                                                            <div class="field-input-btn-wrapper">
                                                                <div class="field-input-wrapper">
                                                                    <label class="field-label" for="discount.code">Mã giảm giá</label>
                                                                    <input placeholder="Mã giảm giá" class="field-input" data-discount-field="true" autocomplete="off" autocapitalize="off" spellcheck="false" size="30" type="text" id="discount.code" name="discount.code" value="" />
                                                                </div>
                                                                <button type="submit" class="field-input-btn btn btn-default btn-disabled">
                                                                    <span class="btn-content">Sử dụng</span>
                                                                    <i class="btn-spinner icon icon-button-spinner"></i>
                                                                </button>
                                                            </div>
        
                                                        </div>
                                                    </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        -->
        <!--end-view mobile-->
        <div class="content">
            <div class="wrap">
                <div class="sidebar">
                    <div class="sidebar-content">
                        <div class="order-summary order-summary-is-collapsed">
                            <h2 class="visually-hidden">Thông tin đơn hàng</h2>
                            <div class="order-summary-sections">
                                <div class="order-summary-section order-summary-section-product-list" data-order-summary-section="line-items">
                                    <table class="product-table">
                                        <thead>
                                            <tr>
                                                <th scope="col"><span class="visually-hidden">Hình ảnh</span></th>
                                                <th scope="col"><span class="visually-hidden">Mô tả</span></th>
                                                <th scope="col"><span class="visually-hidden">Số lượng</span></th>
                                                <th scope="col"><span class="visually-hidden">Giá</span></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="item" items="${cart.cart}">
                                                <tr class="product" data-product-id="1021535535" data-variant-id="1043542392">
                                                    <td class="product-image">
                                                        <div class="product-thumbnail">
                                                            <div class="product-thumbnail-wrapper">
                                                                <img class="product-thumbnail-image" alt=" " src="${pageContext.request.contextPath}/resources/image/${item.product.listImageProductDetail[0].name}" class="img-rounded" />
                                                            </div>
                                                            <span class="product-thumbnail-quantity" aria-hidden="true">${item.productQuantity}</span>
                                                        </div>
                                                    </td>
                                                    <td class="product-description">
                                                        <span class="product-description-name order-summary-emphasis">${item.product.name}</span>
                                                        <span class="product-description-variant order-summary-small-text">${item.productSize.productSize} ${item.color.productColor}</span>
                                                    </td>
                                                    <td class="product-quantity visually-hidden">1</td>
                                                    <td class="product-price">
                                                        <span class="order-summary-emphasis"><fmt:formatNumber type = "number" 
                                                                          maxFractionDigits = "3" value = "${item.product.price*item.productQuantity}"/>₫</span>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="order-summary-section order-summary-section-discount" data-order-summary-section="discount">
                                    <div id="div_discount_add" accept-charset="UTF-8" method="post">
                                        <input name="utf8" type="hidden" value="✓">
                                            <div class="fieldset">
                                                <div class="field  ">
                                                    <div class="field-input-btn-wrapper">
                                                        <div class="field-input-wrapper">
                                                            <label class="field-label" for="discount.code">Mã giảm giá</label>
                                                            <input placeholder="Mã giảm giá" class="field-input" data-discount-field="true" autocomplete="off" autocapitalize="off" spellcheck="false" size="30" type="text" id="discount.code" name="discount.code" value="" />
                                                        </div>
                                                        <button type="submit" class="field-input-btn btn btn-default btn-disabled">
                                                            <span class="btn-content">Sử dụng</span>
                                                            <i class="btn-spinner icon icon-button-spinner"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                    </div>
                                </div>
                                <div class="order-summary-section order-summary-section-total-lines" data-order-summary-section="payment-lines">
                                    <table class="total-line-table">
                                        <thead>
                                            <tr>
                                                <th scope="col"><span class="visually-hidden">Mô tả</span></th>
                                                <th scope="col"><span class="visually-hidden">Giá</span></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr class="total-line total-line-subtotal">
                                                <td class="total-line-name">Tạm tính</td>
                                                <td class="total-line-price">
                                                    <span class="order-summary-emphasis" data-checkout-subtotal-price-target="${cart.total}">
                                                        <fmt:formatNumber type = "number" 
                                                                          maxFractionDigits = "3" value = "${cart.total}"/>₫
                                                    </span>
                                                </td>
                                            </tr>
                                            <tr class="total-line total-line-shipping">
                                                <td class="total-line-name">Phí vận chuyển</td>
                                                <td class="total-line-price">
                                                    <span class="order-summary-emphasis" data-checkout-total-shipping-target="0">40,000₫</span>
                                                </td>
                                            </tr>
                                        </tbody>
                                        <tfoot class="total-line-table-footer">
                                            <tr class="total-line">
                                                <td class="total-line-name payment-due-label">
                                                    <span class="payment-due-label-total">Tổng cộng</span>
                                                </td>
                                                <td class="total-line-name payment-due">
                                                    <span class="payment-due-currency">VND</span>
                                                    <span class="payment-due-price" data-checkout-payment-due-target="${includeShipping}">
                                                        <fmt:formatNumber type = "number" 
                                                                          maxFractionDigits = "3" value = " ${includeShipping}"/>₫
                                                    </span>
                                                </td>
                                            </tr>
                                        </tfoot>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="main">
                    <div class="main-header">
                        <a href="home" class="logo">
                            <h1 class="logo-text">V&H Sneaker</h1>
                        </a>
                        <ul class="breadcrumb">
                            <li class="breadcrumb-item">
                                <a href="cart">Giỏ hàng</a>
                            </li>
                            <li class="breadcrumb-item breadcrumb-item-current">
                                Thông tin giao hàng
                            </li>
                            <li class="breadcrumb-item ">
                                Phương thức thanh toán
                            </li>
                        </ul>
                    </div>
                    <div class="main-content">
                        <mvc:form modelAttribute="customer" action="${pageContext.request.contextPath}/${action}" class="step" method="post">
                            <div class="step-sections " step="1">
                                <div class="section">
                                    <div class="section-header">
                                        <h2 class="section-title">Thông tin giao hàng</h2>
                                    </div>
                                    <div class="section-content section-customer-indivation no-mb">
                                        <sec:authorize access="!isAuthenticated()">
                                            <p class="section-content-text">
                                                Bạn đã có tài khoản?
                                                <a href="account">Đăng nhập</a>
                                            </p>   
                                        </sec:authorize>
                                        <div class="fieldset">
                                            <div class="field   ">
                                                <div class="field-input-wrapper">
                                                    <label class="field-label" for="billing_address_full_name">Họ và tên</label>
                                                    <input  required=""placeholder="Họ và tên" autocapitalize="off" spellcheck="false" class="field-input" size="30" type="text" id="billing_address_full_name" name="name" value="${customer.name}" />
                                                </div>
                                            </div>
                                            <div class="field  field-two-thirds  ">
                                                <div class="field-input-wrapper">
                                                    <label class="field-label" for="checkout_user_email">Email</label>
                                                    <input placeholder="Email" autocapitalize="off" spellcheck="false" class="field-input" size="30" type="email" id="checkout_user_email" name="email" value="${customer.email}" />
                                                </div>
                                            </div>
                                            <div class="field field-required field-third  ">
                                                <div class="field-input-wrapper">
                                                    <label class="field-label" for="billing_address_phone">Số điện thoại</label>
                                                    <input required=""placeholder="Số điện thoại" autocapitalize="off" spellcheck="false" class="field-input" size="30" maxlength="11" type="tel" id="billing_address_phone" name="phoneNumber" value="${customer.phoneNumber}" />
                                                </div>
                                            </div>
                                            <div class="field   ">
                                                <div class="field-input-wrapper">
                                                    <label class="field-label" for="billing_address_address1">Địa chỉ</label>
                                                    <input required=""placeholder="Địa chỉ" autocapitalize="off" spellcheck="false" class="field-input" size="30" type="text" id="billing_address_address1" name="address" value="${customer.address}" />
                                                </div>
                                            </div>
                                            <div class="field   ">
                                                <div class="field-input-wrapper">
                                                    <label class="field-label" for="billing_address_address1">Ghi Chú</label>
                                                    <input placeholder="Ghi Chú" autocapitalize="off" spellcheck="false" class="field-input" size="30" type="text" id="billing_address_address2" name="note" value="" />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="change_pick_location_or_shipping">
                                </div>
                            </div>
                            <div class="step-footer">
                                <div id="div_next_step" accept-charset="UTF-8">
                                    <input name="utf8" type="hidden" value="✓">
                                        <button type="submit" class="step-footer-continue-btn btn">
                                            <span class="btn-content">Hoàn tất đơn hàng</span>
                                            <i class="btn-spinner icon icon-button-spinner"></i>
                                        </button>
                                </div>
                                <a class="step-footer-previous-link" href="cart">
                                    <svg class="previous-link-icon icon-chevron icon" xmlns="http://www.w3.org/2000/svg" width="6.7" height="11.3" viewBox="0 0 6.7 11.3"><path d="M6.7 1.1l-1-1.1-4.6 4.6-1.1 1.1 1.1 1 4.6 4.6 1-1-4.6-4.6z"></path></svg>
                                    Giỏ hàng
                                </a>
                            </div>
                        </mvc:form>                   
                    </div>
                </div>
            </div>
            <div class="main-footer">
            </div>
        </div>
    </body>
</html>
