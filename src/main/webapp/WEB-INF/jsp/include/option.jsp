
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-lg-3 col-md-3 col-sm-4 col-xs-12">
    <div class="wrap-filter clearfix mb15">
        <div class="group-collection" aria-expanded="true">
            <div class="title-block dropdown-filter">
                <h3 class="title-group">Thương hiệu</h3>
                <i class="fa fa-minus flexbox-grid-default flexbox-justifyContent-center flexbox-alignItems-center" aria-hidden="true"></i>
            </div>
            <div class="filter-box" id="filter-vendor1">
                <ul>
                    <li>
                        <label data-filter="nike" class="nike">
                            <input type="checkbox" value="1" class="categrx" onclick="myFunction1x()">
                            <span>NIKE</span>
                        </label>
                    </li>
                    <li>
                        <label data-filter="adidas" class="adidas">
                            <input type="checkbox" value="2" class="categrx" onclick="myFunction1x()">
                            <span>ADIDAS</span>
                        </label>
                    </li>
                    <li>
                        <label data-filter="khac" class="khac">
                            <input type="checkbox" value="3" class="categrx" onclick="myFunction1x()">
                            <span>BALENCIAGA</span>
                        </label>
                    </li>
                    <li>
                        <label data-filter="asics" class="asics">
                            <input type="checkbox" value="4" class="categrx" onclick="myFunction1x()">
                            <span>RICK OWEN</span>
                        </label>
                    </li>
                    <li>
                        <label data-filter="reebok" class="reebok">
                            <input type="checkbox" value="5" class="categrx" onclick="myFunction1x()">
                            <span>CONVERSE</span>
                        </label>
                    </li>
                    <li>
                        <label data-filter="puma" class="puma">
                            <input type="checkbox" value="6" class="categrx" onclick="myFunction1x()">
                            <span>ASIC</span>
                        </label>
                    </li>
                    <li>
                        <label data-filter="new-balance" class="new-balance">
                            <input type="checkbox" value="7" class="categrx" onclick="myFunction1x()">
                            <span>ZARA</span>
                        </label>
                    </li>
                    <li>
                        <label data-filter="under-armour" class="under-armour">
                            <input type="checkbox" value="8" class="categrx" onclick="myFunction1x()">
                            <span>DOMPA</span>
                        </label>
                    </li>
                </ul>
            </div>
        </div>

        <div class="group-collection" aria-expanded="true">
            <div class="title-block dropdown-filter">

                <h3 class="title-group">Kích thước</h3>

                <i class="fa fa-minus flexbox-grid-default flexbox-justifyContent-center flexbox-alignItems-center" aria-hidden="true"></i>
            </div>
            <div class="filter-box" id="filter-size">

                <ul class="filter-size clearfix">
                    <li>
                        <label data-filter="36" class="36">
                            <input type="checkbox" value="(variant:product**36)">
                            <span>36</span>
                        </label>
                    </li>

                    <li>
                        <label data-filter="37" class="37">
                            <input type="checkbox" value="(variant:product**37)">
                            <span>37</span>
                        </label>
                    </li>

                    <li>
                        <label data-filter="38" class="38">
                            <input type="checkbox" value="(variant:product**38)">
                            <span>38</span>
                        </label>
                    </li>

                    <li>
                        <label data-filter="39" class="39">
                            <input type="checkbox" value="(variant:product**39)">
                            <span>39</span>
                        </label>
                    </li>

                    <li>
                        <label data-filter="40" class="40">
                            <input type="checkbox" value="(variant:product**40)">
                            <span>40</span>
                        </label>
                    </li>
                </ul>
            </div>
        </div>

        <div class="group-collection" aria-expanded="true">
            <div class="title-block dropdown-filter">
                <h3 class="title-group">Màu Sắc</h3>
                <i class="fa fa-minus flexbox-grid-default flexbox-justifyContent-center flexbox-alignItems-center" aria-hidden="true"></i>
            </div>
            <div class="filter-box" id="filter-vendor">
                <ul>
                    <li>
                        <label data-filter="nike" class="nike">
                            <input type="checkbox" value="(vendor:product**Nike)">
                            <span>Trắng</span>
                        </label>
                    </li>
                    <li>
                        <label data-filter="adidas" class="adidas">
                            <input type="checkbox" value="(vendor:product**Adidas)">
                            <span>Đen</span>
                        </label>
                    </li>
                    <li>
                        <label data-filter="khac" class="khac">
                            <input type="checkbox" value="(vendor:product**Khác)">
                            <span>Đỏ</span>
                        </label>
                    </li>
                    <li>
                        <label data-filter="asics" class="asics">
                            <input type="checkbox" value="(vendor:product**Asics)">
                            <span>Navy</span>
                        </label>
                    </li>
                    <li>
                        <label data-filter="asics" class="asics">
                            <input type="checkbox" value="(vendor:product**Asics)">
                            <span>Xanh lá</span>
                        </label>
                    </li>
                    <li>
                        <label data-filter="asics" class="asics">
                            <input type="checkbox" value="(vendor:product**Asics)">
                            <span>Vàng</span>
                        </label>
                    </li>
                    <li>
                        <label data-filter="asics" class="asics">
                            <input type="checkbox" value="(vendor:product**Asics)">
                            <span>Xám</span>
                        </label>
                    </li>
                </ul>
            </div>
        </div>
        <div class="group-collection" aria-expanded="true">
            <div class="title-block dropdown-filter">
                <h3 class="title-group">Giá</h3>
                <i class="fa fa-minus flexbox-grid-default flexbox-justifyContent-center flexbox-alignItems-center" aria-hidden="true"></i>
            </div>
            <div class="filter-box" id="filter-price">
                <ul>
                    <li>
                        <label>
                            <input type="radio" name="price-filter" data-price="0:1000000" value="find-by-money1" onclick="location.href='find-by-money1'">
                            <span>Nhỏ hơn 500,000₫</span>
                        </label>
                    </li>
                    <li>
                        <label>
                            <input type="radio" name="price-filter" data-price="1000000:2000000" value="find-by-money2" onclick="location.href='find-by-money2'">
                            <span>Từ 500,000₫ - 1,000,000₫</span>
                        </label>
                    </li>
                    <li>
                        <label>
                            <input type="radio" name="price-filter" data-price="2000000:3000000" value="find-by-money3" onclick="location.href='find-by-money3'">
                            <span>Từ 1,500,000₫ - 2,000,000₫</span>
                        </label>
                    </li>
                    <li>
                        <label>
                            <input type="radio" name="price-filter" data-price="3000000:4000000" value="find-by-money4" onclick="location.href='find-by-money4'">
                            <span>Từ 2,000,000₫ - 2,500,000₫</span>
                        </label>
                    </li>
                    <li>
                        <label>
                            <input type="radio" name="price-filter" data-price="4000000:max" value="find-by-money5" onclick="location.href='find-by-money5'">
                            <span>Lớn hơn 2,500,000₫</span>
                        </label>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>