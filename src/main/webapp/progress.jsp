<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="nav.home" var="home"/>
<fmt:message bundle="${locale}" key="nav.about" var="about"/>
<fmt:message bundle="${locale}" key="nav.recipes" var="recipes"/>
<fmt:message bundle="${locale}" key="nav.sign" var="sign"/>
<fmt:message bundle="${locale}" key="nav.admin.page" var="adminpage"/>
<fmt:message bundle="${locale}" key="nav.daily.eating" var="dailyeating"/>
<fmt:message bundle="${locale}" key="nav.progress" var="progress"/>
<fmt:message bundle="${locale}" key="nav.edit" var="edit"/>
<fmt:message bundle="${locale}" key="nav.logout" var="logout"/>
<fmt:message bundle="${locale}" key="nav.contact" var="contact"/>
<fmt:message bundle="${locale}" key="progress.left" var="left"/>
<fmt:message bundle="${locale}" key="progress.cal" var="cal"/>
<fmt:message bundle="${locale}" key="progress.daily.rate" var="daily_rate"/>
<fmt:message bundle="${locale}" key="progress.protein" var="protein"/>
<fmt:message bundle="${locale}" key="progress.fats" var="fats"/>
<fmt:message bundle="${locale}" key="progress.carbohydrates" var="carbohydrates"/>

<html class="no-js">
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
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
    <link rel="stylesheet" href="/static/css/diagram.css">
    <link rel="stylesheet" href="/static/css/progressbar.css">
    <link rel="stylesheet" href="/static/css/circle.scss">
    <link rel="stylesheet" href="/static/css/statistic.css">
</head>

<body>
<!--[if lte IE 9]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade
    your browser</a> to improve your experience and security.</p>
<![endif]-->

<%@ include file="header.jsp" %>

<!-- slider_area_start -->
<div class="slider_area">
    <div class="progress_area">
        <div class="container">
            <div class="row align-items-center justify-content-center">
                <div class="col align-center">
                    <p class="text-cal text-center">${max_calories - curr_calories}</p>
                    <br>
                    <p class="sub-text-cal text-center">${left}</p>
                </div>
                <div class="col-xl-5 ">
                    <div class="single-chart">
                        <svg viewBox="0 0 36 36" class="circular-chart orange">
                            <path class="circle-bg"
                                  d="M18 2.0845
          a 15.9155 15.9155 0 0 1 0 31.831
          a 15.9155 15.9155 0 0 1 0 -31.831"
                            />
                            <path class="circle"
                                  stroke-dasharray="${curr_percentage}, 100"
                                  d="M18 2.0845
          a 15.9155 15.9155 0 0 1 0 31.831
          a 15.9155 15.9155 0 0 1 0 -31.831"
                            />
                            <text x="18" y="20.35" class="percentage">${curr_calories}</text>
                            <text x="18" y="26.35" class="elem">${cal}</text>
                        </svg>
                    </div>
                </div>
                <div class="col align-center">
                    <p class="text-cal text-center">${max_calories}</p>
                    <br>
                    <p class="sub-text-cal text-center">${daily_rate}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="single_slider align-items-center progress-bar-height">
        <div class="container">
            <div class="row align-items-center justify-content-center">
                <div class="mom">
                    <div class="row">
                        <div class="col-4 child container">
                            <div class="meterContainer childinner">
                                <div aria-hidden="true" class="meterBackground">
                                    <div aria-hidden="true" class="meterBar">
                                        <label class="progressValue">
                                            <span class="progressNumber">${curr_protein}</span>
                                            <meter max="${max_protein}" value="${curr_protein}" class="leaseMeter"></meter>
                                        </label><!--progressValue-->
                                    </div><!--meterBar-->
                                    <p class="sub-text-cal text-center">${protein}</p>
                                </div> <!--meterBackground-->
                            </div><!--meterContainer-->
                        </div>
                        <div class="col-4 child container">
                            <div class="meterContainer childinner">
                                <div aria-hidden="true" class="meterBackground">
                                    <div aria-hidden="true" class="meterBar">
                                        <label class="progressValue">
                                            <span class="progressNumber">${curr_fats}</span>
                                            <meter max="${max_fats}" value="${curr_fats}" class="leaseMeter"></meter>
                                        </label><!--progressValue-->
                                    </div><!--meterBar-->
                                    <p class="sub-text-cal text-center">${fats}</p>
                                </div> <!--meterBackground-->
                            </div><!--meterContainer-->
                        </div>
                        <div class="col-4 child container">
                            <div class="meterContainer childinner">
                                <div aria-hidden="true" class="meterBackground">
                                    <div aria-hidden="true" class="meterBar">
                                        <label class="progressValue">
                                            <span class="progressNumber">${curr_carbohydrates}</span>
                                            <meter max="${max_carbohydrates}" value="${curr_carbohydrates}" class="leaseMeter"></meter>
                                        </label><!--progressValue-->
                                    </div><!--meterBar-->
                                    <p class="sub-text-cal text-center">${carbohydrates}</p>
                                </div> <!--meterBackground-->
                            </div><!--meterContainer-->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="content">
        <div class="chart1">
            <canvas id="chart2"></canvas>
        </div>
    </div>

</div>
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
<script src="/static/js/progressbar.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<script>
    var chart2el = document.getElementById("chart2").getContext('2d');

    var info = '${weekInfo}';
    //alert(Object.keys(JSON.parse(info)));

    var chart2 = new Chart(chart2el, {
        type: 'bar',
        data: {
            labels: Object.keys(JSON.parse(info)).reverse(),
            datasets: [
                {
                    label: "Calories: ",
                    backgroundColor: ["#E289FF", "#FD96FF","#FF89A6","#FFA789", "#E289FF", "#FD96FF","#FF89A6"],
                    data: Object.values(JSON.parse(info)).reverse()
                },
                {
                    label: 'Daily norma calories',
                    fill: false,
                    data: [1500, 1500, 1500, 1500, 1500, 1500, 1500],
                    type: 'line'
                }
            ]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            legend: { display: false },
            title: {
                display: true,
                text: 'A month chart'
            }
        }
    });
</script>
</body>

</html>