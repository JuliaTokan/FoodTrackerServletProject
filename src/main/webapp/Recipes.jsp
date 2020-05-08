<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="recipes.name.recipe1" var="recipe1"/>
<fmt:message bundle="${locale}" key="recipes.name.recipe2" var="recipe2"/>
<fmt:message bundle="${locale}" key="recipes.name.recipe3" var="recipe3"/>
<fmt:message bundle="${locale}" key="recipes.type.appetizer" var="type"/>
<fmt:message bundle="${locale}" key="recipes.time" var="time"/>
<fmt:message bundle="${locale}" key="recipes.btn.full" var="btn_full"/>
<fmt:message bundle="${locale}" key="recipes.btn.all" var="btn_all"/>
<fmt:message bundle="${locale}" key="recipes.text.h2" var="text_h2"/>
<fmt:message bundle="${locale}" key="recipes.text.h1" var="text_h1"/>
<fmt:message bundle="${locale}" key="recipes.download.app" var="download_app"/>
<html class="no-js" lang="zxx">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Eat&Fit</title>
    <link rel="shortcut icon" href="/static/img/favicon.ico" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- CSS here -->
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/static/css/magnific-popup.css">
    <link rel="stylesheet" href="/static/css/font-awesome.min.css">
    <link rel="stylesheet" href="/static/css/themify-icons.css">
    <link rel="stylesheet" href="/static/css/nice-select.css">
    <link rel="stylesheet" href="/static/css/flaticon.css">
    <link rel="stylesheet" href="/static/css/gijgo.css">
    <link rel="stylesheet" href="/static/css/animate.min.css">
    <link rel="stylesheet" href="/static/css/slick.css">
    <link rel="stylesheet" href="/static/css/slicknav.css">
    <link rel="stylesheet" href="/static/css/style.css">
    <!-- <link rel="stylesheet" href="css/responsive.css"> -->
</head>

<body>
    <!--[if lte IE 9]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
        <![endif]-->

    <%@ include file="header.jsp" %>

    <!-- recepie_area_start  -->
    <div class="recepie_area plus_padding">
        <div class="container">
            <div class="row">
                <div class="col-xl-4 col-lg-4 col-md-6">
                    <div class="single_recepie text-center">
                        <div class="recepie_thumb">
                            <img src="/static/img/recepie/recpie_1.png" alt="">
                        </div>
                        <h3>${recipe1}</h3>
                        <span>${type}</span>
                        <p>${time}</p>
                        <a href="#" class="line_btn">${btn_full}</a>
                    </div>
                </div>
                <div class="col-xl-4 col-lg-4 col-md-6">
                    <div class="single_recepie text-center">
                        <div class="recepie_thumb">
                            <img src="/static/img/recepie/recpie_2.png" alt="">
                        </div>
                        <h3>${recipe2}</h3>
                        <span>${type}</span>
                        <p>${time}</p>
                        <a href="#" class="line_btn">${btn_full}</a>
                    </div>
                </div>
                <div class="col-xl-4 col-lg-4 col-md-6">
                    <div class="single_recepie text-center">
                        <div class="recepie_thumb">
                            <img src="/static/img/recepie/recpie_3.png" alt="">
                        </div>
                        <h3>${recipe3}</h3>
                        <span>${type}</span>
                        <p>${time}</p>
                        <a href="#" class="line_btn">${btn_full}</a>
                    </div>
                </div>
                <div class="col-xl-4 col-lg-4 col-md-6">
                    <div class="single_recepie text-center">
                        <div class="recepie_thumb">
                            <img src="/static/img/recepie/recpie_1.png" alt="">
                        </div>
                        <h3>${recipe1}</h3>
                        <span>${type}</span>
                        <p>${time}</p>
                        <a href="#" class="line_btn">${btn_full}</a>
                    </div>
                </div>
                <div class="col-xl-4 col-lg-4 col-md-6">
                    <div class="single_recepie text-center">
                        <div class="recepie_thumb">
                            <img src="/static/img/recepie/recpie_2.png" alt="">
                        </div>
                        <h3>${recipe2}</h3>
                        <span>${type}</span>
                        <p>${time}</p>
                        <a href="#" class="line_btn">${btn_full}</a>
                    </div>
                </div>
                <div class="col-xl-4 col-lg-4 col-md-6">
                    <div class="single_recepie text-center">
                        <div class="recepie_thumb">
                            <img src="/static/img/recepie/recpie_3.png" alt="">
                        </div>
                        <h3>${recipe3}</h3>
                        <span>${type}</span>
                        <p>${time}</p>
                        <a href="#" class="line_btn">${btn_full}</a>
                    </div>
                </div>
                <div class="col-xl-4 col-lg-4 col-md-6">
                    <div class="single_recepie text-center">
                        <div class="recepie_thumb">
                            <img src="/static/img/recepie/recpie_1.png" alt="">
                        </div>
                        <h3>${recipe1}</h3>
                        <span>${type}</span>
                        <p>${time}</p>
                        <a href="#" class="line_btn">${btn_full}</a>
                    </div>
                </div>
                <div class="col-xl-4 col-lg-4 col-md-6">
                    <div class="single_recepie text-center">
                        <div class="recepie_thumb">
                            <img src="/static/img/recepie/recpie_2.png" alt="">
                        </div>
                        <h3>${recipe2}</h3>
                        <span>${type}</span>
                        <p>${time}</p>
                        <a href="#" class="line_btn">${btn_full}</a>
                    </div>
                </div>
                <div class="col-xl-4 col-lg-4 col-md-6">
                    <div class="single_recepie text-center">
                        <div class="recepie_thumb">
                            <img src="/static/img/recepie/recpie_3.png" alt="">
                        </div>
                        <h3>${recipe3}</h3>
                        <span>${type}</span>
                        <p>${time}</p>
                        <a href="#" class="line_btn">${btn_full}</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /recepie_area_start  -->

    <!-- latest_trand     -->
    <div class="latest_trand_area">
        <div class="container">
            <div class="row">
                <div class="col-xl-12">
                    <div class="trand_info text-center">
                        <p>${text_h2}</p>
                        <h3>${text_h1}</h3>
                        <a href="#" class="boxed-btn3">${btn_all}</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--/ latest_trand     -->

    <!-- download_app_area -->
    <div class="download_app_area plus_padding">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-xl-6 col-md-6">
                    <div class="download_thumb">
                        <div class="big_img">
                            <img src="/static/img/video/big_1.png" alt="">
                        </div>
                        <div class="small_01">
                            <img src="/static/img/video/small_sm1.png" alt="">
                        </div>
                        <div class="small_02">
                            <img src="/static/img/video/sm2.png" alt="">
                        </div>
                    </div>
                </div>
                <div class="col-xl-6 col-md-6">
                    <div class="download_text">
                        <h3>${download_app}</h3>
                        <div class="download_android_apple">
                            <a class="active" href="#">
                                <div class="download_link d-flex">
                                    <i class="fa fa-apple"></i>
                                    <div class="store">
                                        <h5>Available</h5>
                                        <p>on App Store</p>
                                    </div>
                                </div>
                            </a>
                            <a href="#">
                                <div class="download_link d-flex">
                                    <i class="fa fa-android"></i>
                                    <div class="store">
                                        <h5>Download</h5>
                                        <p>from Play Store</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--/ download_app_area -->

    <%@ include file="footer.jsp" %>

    <!-- JS here -->
    <script src="/static/js/vendor/modernizr-3.5.0.min.js"></script>
    <script src="/static/js/vendor/jquery-1.12.4.min.js"></script>
    <script src="/static/js/popper.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
    <script src="/static/js/owl.carousel.min.js"></script>
    <script src="/static/js/isotope.pkgd.min.js"></script>
    <script src="/static/js/ajax-form.js"></script>
    <script src="/static/js/waypoints.min.js"></script>
    <script src="/static/js/jquery.counterup.min.js"></script>
    <script src="/static/js/imagesloaded.pkgd.min.js"></script>
    <script src="/static/js/scrollIt.js"></script>
    <script src="/static/js/jquery.scrollUp.min.js"></script>
    <script src="/static/js/wow.min.js"></script>
    <script src="/static/js/nice-select.min.js"></script>
    <script src="/static/js/jquery.slicknav.min.js"></script>
    <script src="/static/js/jquery.magnific-popup.min.js"></script>
    <script src="/static/js/plugins.js"></script>
    <script src="/static/js/gijgo.min.js"></script>

    <!--contact js-->
    <script src="/static/js/contact.js"></script>
    <script src="/static/js/jquery.ajaxchimp.min.js"></script>
    <script src="/static/js/jquery.form.js"></script>
    <script src="/static/js/jquery.validate.min.js"></script>
    <script src="/static/js/mail-script.js"></script>

    <script src="/static/js/main.js"></script>
</body>

</html>