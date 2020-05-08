<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="sign.up" var="signup"/>
<fmt:message bundle="${locale}" key="sign.in" var="signin"/>
<fmt:message bundle="${locale}" key="h1.sign.up" var="h1signup"/>
<fmt:message bundle="${locale}" key="span.email" var="spanemail"/>
<fmt:message bundle="${locale}" key="span.pass" var="spanpass"/>
<fmt:message bundle="${locale}" key="span.conf.pass" var="spanconfpass"/>
<fmt:message bundle="${locale}" key="button.start" var="buttonstart"/>
<fmt:message bundle="${locale}" key="h1.sign.in" var="h1signin"/>
<fmt:message bundle="${locale}" key="span.pass.simpl" var="spanpasssimpl"/>
<fmt:message bundle="${locale}" key="button.login" var="buttonlogin"/>
<fmt:message bundle="${locale}" key="invalid.oh" var="oh"/>
<fmt:message bundle="${locale}" key="invalid.email" var="error_email_info"/>
<fmt:message bundle="${locale}" key="invalid.password" var="error_password_info"/>
<fmt:message bundle="${locale}" key="invalid.error.user" var="error_user_info"/>
<fmt:message bundle="${locale}" key="invalid.error" var="error_info"/>
<fmt:message bundle="${locale}" key="invalid.error.user.exist" var="error_exist_user_info"/>
<fmt:message bundle="${locale}" key="enter.error.email" var="enter_error_email"/>
<fmt:message bundle="${locale}" key="enter.error.pass" var="enter_error_pass"/>
<fmt:message bundle="${locale}" key="enter.error.conf.pass" var="enter_error_conf_pass"/>
<fmt:message bundle="${locale}" key="enter.empty" var="enter_error_empty"/>

<html class="no-js" lang="zxx">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Eat&Fit</title>
    <link rel="shortcut icon" href="/static/img/favicon.ico" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- <link rel="manifest" href="site.webmanifest"> -->
    <!-- Place favicon.ico in the root directory -->

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
    <link rel="stylesheet" href="/static/css/first_registration.scss">
    <link rel="stylesheet" href="/static/css/message.css">
    <!-- <link rel="stylesheet" href="css/responsive.css"> -->
</head>

<body>
<!--[if lte IE 9]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade
    your browser</a> to improve your experience and security.</p>
<![endif]-->

<%@ include file="header.jsp" %>

<!-- slider_area_start -->
<div class="slider_area">
    <div class="single_slider  d-flex align-items-center slider_bg_3">
        <div class="container">
            <div class="row align-items-center justify-content-center">
                <div class="col-xl-8 ">
                    <div class="slider_text text-center">
                        <div class="text">

                            <div class="rg-form">

                                <ul class="rg-tab-group">
                                    <li class="rg-tab <c:if test="${!signinform}">active</c:if>"><a class="rg-a"
                                                                                                    href="#signup">${signup}</a>
                                    </li>
                                    <li class="rg-tab <c:if test="${signinform}">active</c:if>"><a class="rg-a"
                                                                                                   href="#login">${signin}</a>
                                    </li>
                                </ul>

                                <div class="rg-tab-content">
                                    <div id="signup"
                                         <c:if test="${!signinform}">style="display: block;"</c:if>
                                         <c:if test="${signinform}">style="display: none;"</c:if>">
                                    <h1 class="rg-h1">${h1signup}</h1>

                                    <c:if test="${error_email}">
                                        <div class="alert alert-danger">
                                            <a href="#" class="close">x</a>
                                            <strong>${oh}</strong> ${error_email_info}
                                        </div>
                                    </c:if>
                                    <c:if test="${error_pass}">
                                        <div class="alert alert-danger">
                                            <a href="#" class="close">x</a>
                                            <strong>${oh}</strong> ${error_password_info}
                                        </div>
                                    </c:if>
                                    <c:if test="${error_user}">
                                        <div class="alert alert-danger">
                                            <a href="#" class="close">x</a>
                                            <strong>${oh}!</strong> ${error_exist_user_info}
                                        </div>
                                    </c:if>
                                    <c:if test="${error}">
                                        <div class="alert alert-danger">
                                            <a href="#" class="close">x</a>
                                            <strong>${oh}</strong> ${error_info}
                                        </div>
                                    </c:if>

                                    <form class="singup" action="/sign/up" method="POST" accept-charset="UTF-8">
                                        <div class="rg-field-wrap">
                                            <label class="rg-label">
                                                ${spanemail}<span class="req rg-span">*</span>
                                            </label>
                                            <input class="rg-input" type="email" name="email" required
                                                   autocomplete="off"/>
                                            <p class="invalid-p"></p>
                                        </div>

                                        <div class="rg-field-wrap">
                                            <label class="rg-label">
                                                ${spanpass}<span class="req rg-span">*</span>
                                            </label>
                                            <input class="rg-input" type="password" name="pass" required
                                                   autocomplete="off"/>
                                            <p class="invalid-p"></p>
                                        </div>

                                        <div class="rg-field-wrap">
                                            <label class="rg-label">
                                                ${spanconfpass}<span class="req rg-span">*</span>
                                            </label>
                                            <input class="rg-input" type="password" name="conf-pass" required
                                                   autocomplete="off"/>
                                            <p class="invalid-p"></p>
                                        </div>

                                        <button type="submit" class="rg-button rg-button-block signup-sbm">
                                            ${buttonstart}</button>

                                    </form>

                                </div>

                                <div id="login"
                                     <c:if test="${!signinform}">style="display: none;"</c:if>
                                     <c:if test="${signinform}">style="display: block;"
                                </c:if>">
                                <h1 class="rg-h1">${h1signin}</h1>

                                <c:if test="${error_email}">
                                    <div class="alert alert-danger">
                                        <a href="#" class="close">x</a>
                                        <strong>${oh}</strong> ${error_email_info}
                                    </div>
                                </c:if>
                                <c:if test="${error_pass}">
                                    <div class="alert alert-danger">
                                        <a href="#" class="close">x</a>
                                        <strong>${oh}</strong> ${error_password_info}
                                    </div>
                                </c:if>
                                <c:if test="${error_user}">
                                    <div class="alert alert-danger">
                                        <a href="#" class="close">x</a>
                                        <strong>${oh}!</strong> ${error_user_info}
                                    </div>
                                </c:if>
                                <c:if test="${error}">
                                    <div class="alert alert-danger">
                                        <a href="#" class="close">x</a>
                                        <strong>${oh}</strong> ${error_info}
                                    </div>
                                </c:if>

                                <form action="/sign/in" method="POST" accept-charset="UTF-8">

                                    <div class="rg-field-wrap">
                                        <label class="rg-label">
                                            ${spanemail}<span class="req rg-span">*</span>
                                        </label>
                                        <input class="rg-input" type="email" name="email" required autocomplete="off"/>
                                        <p class="invalid-p"></p>
                                    </div>

                                    <div class="rg-field-wrap">
                                        <label class="rg-label">
                                            ${spanpasssimpl}<span class="req rg-span">*</span>
                                        </label>
                                        <input class="rg-input" type="password" name="pass" required
                                               autocomplete="off"/>
                                        <p class="invalid-p"></p>
                                    </div>

                                    <p class="rg-forgot"><a class="rg-a" href="#">Forgot Password?</a></p>

                                    <button class="rg-button rg-button-block signin-sbm">
                                        ${buttonlogin}</button>
                                </form>
                            </div>
                        </div><!-- tab-content -->
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
<p hidden id="error_email">${enter_error_email}</p>
<p hidden id="error_pass">${enter_error_pass}</p>
<p hidden id="error_conf_pass">${enter_error_conf_pass}</p>
<p hidden id="error_empty">${enter_error_empty}</p>
<!-- slider_area_end -->
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

<script src="/static/js/first_registration.js"></script>
<script src="/static/js/validator.js"></script>
<script src="/static/js/message.js"></script>

</body>

</html>