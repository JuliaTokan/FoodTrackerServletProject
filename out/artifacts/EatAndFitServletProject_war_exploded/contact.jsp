<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="label.dish" var="dish"/>
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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css"
          integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
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

  <!-- ================ contact section start ================= -->
  <section class="contact-section section_padding">
    <div class="container">
        <div style="min-height: 65vh;">
            <div class="container">
                <h1 class="text-center contact-h">Contact Address</h1><br>
                <div class="row text-center">
                    <div class="col-sm-3 col-xs-6 first-box">
                        <h1><i class="fas fa-phone fa-2x"></i></h1>
                        <h3>Phone</h3>
                        <p>+380-669-216-422</p><br>
                    </div>
                    <div class="col-sm-3 col-xs-6 second-box">
                        <h1><i class="fas fa-home fa-2x"></i></h1>
                        <h3>Location</h3>
                        <p>Ukraine, Kiev</p><br>
                    </div>
                    <div class="col-sm-3 col-xs-6 third-box">
                        <h1><i class="far fa-envelope fa-2x"></i></h1>
                        <h3>E-mail</h3>
                        <p>eat.and.fit.system@gmail.com</p><br>
                    </div>
                    <div class="col-sm-3 col-xs-6 fourth-box">
                        <h1><i class="fab fa-envira fa-2x"></i></h1>
                        <h3>Web</h3>
                        <p>eatandfit.herokuapp.com</p><br>
                    </div>
                </div>
            </div>
        </div>
    </div>
  </section>
  <!-- ================ contact section end ================= -->

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