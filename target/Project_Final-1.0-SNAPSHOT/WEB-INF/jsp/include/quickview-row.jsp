
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="wrapper-quickview row">
    <div class="">
        <div class="quickview-image">
            <img src="" alt="">
        </div>
        <div id="quickview-sliderproduct">
            <div class="quickview-slider owl-carousel"></div>			
        </div>
    </div>
    <div class="">
        <form id="form-quickview" method="post" action="/cart/add">
            <div class="quickview-information">
                <div class="quickview-close">
                    <a href="javascript:void(0);"><i class="fa fa-times"></i></a>
                </div>
                <a href="#" class="quickview-title" title=""><h2></h2></a>
                <div class="hook-reviews">
                    <span class="rating">
                        <i class="icon star-full"></i>
                        <i class="icon star-full"></i>
                        <i class="icon star-full"></i>
                        <i class="icon star-full"></i>
                        <i class="icon star-empty"></i>
                    </span>
                </div>
                <div class="quickview-price">
                    <span></span><del></del>
                </div>

                <div class="quickview-variants variant-style clearfix">

                    <div class="selector-wrapper opt1-quickview flexbox-grid-default flexbox-alignItems-center">
                        <label></label>
                        <ul class="data-opt1-quickview clearfix style-variant-template">

                        </ul>
                    </div>
                    <div class="selector-wrapper opt2-quickview flexbox-grid-default flexbox-alignItems-center">
                        <label></label>
                        <ul class="data-opt2-quickview clearfix style-variant-template">

                        </ul>
                    </div>
                    <div class="selector-wrapper opt3-quickview flexbox-grid-default flexbox-alignItems-center">
                        <label></label>
                        <ul class="data-opt3-quickview clearfix style-variant-template">

                        </ul>
                    </div>
                    <style>
                        .selector-wrapper:not(.opt1):not(.opt1-quickview):not(.opt2):not(.opt2-quickview):not(.opt3):not(.opt3-quickview) {
                            display: none;
                        }
                    </style>

                    <select name="id" class="" id="quickview-select"></select>
                    <div class="clearfix">
                        <button class="btn-style-add">
                            <span class="icon_cart_btn"></span>
                            <span>Thêm vào giỏ</span>
                        </button>
                    </div>
                </div>
                <div class="quickview-description"></div>
            </div>
        </form>
    </div>
</div>
