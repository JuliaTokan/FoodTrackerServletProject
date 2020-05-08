<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="cl.person.details" var="clpersondetails"/>
<fmt:message bundle="${locale}" key="cl.param" var="clparam"/>
<fmt:message bundle="${locale}" key="cl.nutr.goal" var="clnutrgoal"/>
<fmt:message bundle="${locale}" key="cl.label.tell" var="cllabeltell"/>
<fmt:message bundle="${locale}" key="cl.label.name" var="cllabelname"/>
<fmt:message bundle="${locale}" key="cl.label.age" var="cllabelage"/>
<fmt:message bundle="${locale}" key="cl.label.gender" var="cllabelgender"/>
<fmt:message bundle="${locale}" key="cl.label.height" var="cllabelheight"/>
<fmt:message bundle="${locale}" key="cl.label.weight" var="cllabelweight"/>
<fmt:message bundle="${locale}" key="cl.label.activity" var="cllabelactivity"/>
<fmt:message bundle="${locale}" key="cl.label.nutr.goal" var="cllabelnutrgoal"/>
<fmt:message bundle="${locale}" key="cl.button.submit" var="clbuttonsubmit"/>
<fmt:message bundle="${locale}" key="cl.button.previous" var="clbuttonprevious"/>
<fmt:message bundle="${locale}" key="cl.button.next" var="clbuttonnext"/>
<fmt:message bundle="${locale}" key="invalid.oh" var="oh"/>
<fmt:message bundle="${locale}" key="invalid.name" var="error_name_info"/>
<fmt:message bundle="${locale}" key="invalid.age" var="error_age_info"/>
<fmt:message bundle="${locale}" key="invalid.height" var="error_height_info"/>
<fmt:message bundle="${locale}" key="invalid.weight" var="error_weight_info"/>
<fmt:message bundle="${locale}" key="invalid.error" var="error_info"/>
<fmt:message bundle="${locale}" key="enter.error.name" var="enter_error_name"/>
<fmt:message bundle="${locale}" key="enter.error.age" var="enter_error_age"/>
<fmt:message bundle="${locale}" key="enter.error.num" var="enter_error_num"/>
<fmt:message bundle="${locale}" key="enter.error.height" var="enter_error_height"/>
<fmt:message bundle="${locale}" key="enter.error.weight" var="enter_error_weight"/>
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
    <link rel="stylesheet" href="/static/css/client_info_registration.css">
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
    <div class="single_slider align-items-center slider_bg_3">
        <div class="container mscontainer">
            <div class="row align-items-center justify-content-center">
                <div class="col-xl-6 ">
                    <div class="slider_text text-center">
                        <form id="msform" action="/client/edit" method="POST">
                            <!-- progressbar -->
                            <ul id="progressbar">
                                <li class="active">${clpersondetails}</li>
                                <li>${clparam}</li>
                                <li>${clnutrgoal}</li>
                            </ul>
                            <!-- fieldsets -->
                            <fieldset>
                                <h1 class="rg-h1-cl">${clpersondetails}</h1>
                                <h1 class="fs-subtitle">${cllabeltell}</h1>

                                <c:if test="${sessionScope.error_name}">
                                    <div class="alert alert-danger">
                                        <a href="#" class="close">x</a>
                                        <strong>${oh}</strong> ${error_name_info}
                                    </div>
                                </c:if>
                                <c:if test="${sessionScope.error_age}">
                                    <div class="alert alert-danger">
                                        <a href="#" class="close">x</a>
                                        <strong>${oh}</strong> ${error_age_info}
                                    </div>
                                </c:if>
                                <c:if test="${sessionScope.error_height}">
                                    <div class="alert alert-danger">
                                        <a href="#" class="close">x</a>
                                        <strong>${oh}</strong> ${error_height_info}
                                    </div>
                                </c:if>
                                <c:if test="${sessionScope.error_weight}">
                                    <div class="alert alert-danger">
                                        <a href="#" class="close">x</a>
                                        <strong>${oh}</strong> ${error_weight_info}
                                    </div>
                                </c:if>
                                <c:if test="${sessionScope.error == true}">
                                    <div class="alert alert-danger">
                                        <a href="#" class="close">x</a>
                                        <strong>${oh}</strong> ${error_info}
                                    </div>
                                </c:if>

                                <div class="rg-field-wrap">
                                    <label class="rg-label active">
                                        ${cllabelname}<span class="req rg-span">*</span>
                                    </label>
                                    <input class="rg-input" type="text" name="name" required autocomplete="off"
                                           value="${cl_name}"/>
                                    <p class="invalid-p"></p>
                                </div>
                                <div class="rg-field-wrap">
                                    <label class="rg-label active">
                                        ${cllabelage}<span class="req rg-span">*</span>
                                    </label>
                                    <input class="rg-input" type="text" name="age" required autocomplete="off"
                                           value="${cl_age}"/>
                                    <p class="invalid-p"></p>
                                </div>
                                <div class="rg-field-wrap">
                                    <label class="rg-label active">
                                        ${cllabelgender}<span class="req rg-span">*</span>
                                    </label>
                                    <select class="rg-select" name="gender">
                                            <option style="display:none">
                                        <c:forEach var="gender" items="${genders}">
                                            <c:if test="${cl_gender == gender.getId()}">
                                                <option selected value="${gender.getId()}">${gender.getGender()}</option>
                                            </c:if>
                                            <c:if test="${cl_gender != gender.getId()}">
                                                <option value="${gender.getId()}">${gender.getGender()}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <p class="invalid-p"></p>
                                </div>
                                <button type="button" name="next" class="next rg-button rg-button-block">
                                    ${clbuttonnext}</button>
                            </fieldset>
                            <fieldset>
                                <h1 class="rg-h1-cl">${clparam}</h1>
                                <h1 class="fs-subtitle">${cllabeltell}</h1>
                                <div class="rg-field-wrap">
                                    <label class="rg-label active">
                                        ${cllabelheight}<span class="req rg-span">*</span>
                                    </label>
                                    <input class="rg-input" type="number" name="height" required autocomplete="off"
                                           value="${cl_height}"/>
                                    <p class="invalid-p"></p>
                                </div>
                                <div class="rg-field-wrap">
                                    <label class="rg-label active">
                                        ${cllabelweight}<span class="req rg-span">*</span>
                                    </label>
                                    <input class="rg-input" type="number" name="weight" required autocomplete="off"
                                           value="${cl_weight}"/>
                                    <p class="invalid-p"></p>
                                </div>
                                <div class="rg-field-wrap">
                                    <label class="rg-label active">
                                        ${cllabelactivity}<span class="req rg-span">*</span>
                                    </label>
                                    <select class="rg-select" name="activity">
                                            <option style="display:none">
                                        <c:forEach var="activity" items="${activities}">
                                            <c:if test="${cl_activity == activity.getId()}">
                                                <option selected value="${activity.getId()}">${activity.getActivity()}</option>
                                            </c:if>
                                            <c:if test="${cl_activity != activity.getId()}">
                                                <option value="${activity.getId()}">${activity.getActivity()}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <p class="invalid-p"></p>
                                </div>
                                <button type="button" name="previous" class="previous rg-button rg-button-block-other">
                                    ${clbuttonprevious}</button>
                                <button type="button" name="next" class="next rg-button rg-button-block-other">
                                    ${clbuttonnext}</button>
                            </fieldset>
                            <fieldset>
                                <h1 class="rg-h1-cl">${clnutrgoal}</h1>
                                <h1 class="fs-subtitle">${cllabeltell}</h1>
                                <div class="rg-field-wrap">
                                    <label class="rg-label active">
                                        ${cllabelnutrgoal}<span class="req rg-span">*</span>
                                    </label>
                                    <select class="rg-select" name="nutr_goal">
                                        <option style="display:none">
                                        <c:forEach var="nutrGoal" items="${nutritionGoals}">
                                            <c:if test="${cl_nutr_goal == nutrGoal.getId()}">
                                                <option selected value="${nutrGoal.getId()}">${nutrGoal.getGoal()}</option>
                                            </c:if>
                                            <c:if test="${cl_nutr_goal != nutrGoal.getId()}">
                                                <option value="${nutrGoal.getId()}">${nutrGoal.getGoal()}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <p class="invalid-p"></p>
                                </div>
                                <button type="button" name="previous" class="previous rg-button rg-button-block-other">
                                    ${clbuttonprevious}</button>
                                <button type="submit" name="submit" class="submit rg-button rg-button-block-other">
                                    ${clbuttonsubmit}</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<p hidden id="error_name">${enter_error_name}</p>
<p hidden id="error_num">${enter_error_num}</p>
<p hidden id="error_age">${enter_error_age}</p>
<p hidden id="error_height">${enter_error_height}</p>
<p hidden id="error_weight">${enter_error_weight}</p>
<p hidden id="error_empty">${enter_error_empty}</p>
<!-- slider_area_end -->
<%
    session.removeAttribute("error_name");
    session.removeAttribute("error_age");
    session.removeAttribute("error_height");
    session.removeAttribute("error_weight");
    session.removeAttribute("error");
%>

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
<script src="/static/js/jquery.ajaxchimp.min.js"></script>
<script src="/static/js/jquery.form.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script src="/static/js/mail-script.js"></script>

<script src="/static/js/main.js"></script>

<!--form js-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/static/js/jquery.easing.1.3.js"></script>
<script src="/static/js/validator.js"></script>
<script src="/static/js/client_info_registration.js"></script>

<script src="/static/js/first_registration.js"></script>
<script src="/static/js/message.js"></script>

</body>

</html>