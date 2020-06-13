<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="ml.h2.food" var="mlfood"/>
<fmt:message bundle="${locale}" key="ml.h2.weight" var="mlweight"/>
<fmt:message bundle="${locale}" key="ml.h2.eat.period" var="mleatperiod"/>
<fmt:message bundle="${locale}" key="ml.btn.add" var="mlbtnadd"/>
<fmt:message bundle="${locale}" key="ml.btn.delete" var="mlbtndelete"/>
<fmt:message bundle="${locale}" key="ml.add.new.food" var="mladdnewfood"/>
<fmt:message bundle="${locale}" key="ml.name" var="mlname"/>
<fmt:message bundle="${locale}" key="ml.calories" var="mlcalories"/>
<fmt:message bundle="${locale}" key="ml.protein" var="mlprotein"/>
<fmt:message bundle="${locale}" key="ml.fats" var="mlfats"/>
<fmt:message bundle="${locale}" key="ml.carbohydrates" var="mlcarbohydrates"/>
<fmt:message bundle="${locale}" key="ml.public" var="mlpublic"/>
<fmt:message bundle="${locale}" key="ml.btn.close" var="mlbtnclose"/>
<fmt:message bundle="${locale}" key="ml.btn.add.food" var="mlbtnaddfood"/>
<fmt:message bundle="${locale}" key="invalid.oh" var="oh"/>
<fmt:message bundle="${locale}" key="invalid.name.product" var="error_name"/>
<fmt:message bundle="${locale}" key="invalid.calories" var="error_calories"/>
<fmt:message bundle="${locale}" key="invalid.nutritions" var="error_nutritions"/>
<fmt:message bundle="${locale}" key="invalid.error" var="error"/>
<fmt:message bundle="${locale}" key="enter.error.name" var="enter_error_name"/>
<fmt:message bundle="${locale}" key="enter.error.cal" var="enter_error_cal"/>
<fmt:message bundle="${locale}" key="enter.error.nutr" var="enter_error_nutr"/>
<fmt:message bundle="${locale}" key="enter.error.num" var="enter_error_num"/>
<fmt:message bundle="${locale}" key="enter.empty" var="enter_error_empty"/>
<fmt:message bundle="${locale}" key="ml.add.new.food" var="new_food"/>

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
    <link rel="stylesheet" href="/static/css/message.css">
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
    <link rel="stylesheet" href="/static/css/meals.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/style.css">
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
    <div class="single_slider  d-flex slider_bg_4">
        <div class="container main-container">
            <form action="/user/meals" method="POST">
                <div class="row">
                    <div class="col-xl-4 col-lg-4 col-md-6">
                        <div class="ml-container">
                            <div class="ml-main">
                                <h3>${mlfood}</h3>
                                <select class="select-food" name="food">
                                    <c:forEach var="product" items="${products}">
                                        <option value="${product.getId()}">${product.getName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-2 col-lg-4 col-md-6">
                        <div class="ml-container">
                            <div class="ml-main">
                                <h3>${mlweight}</h3>
                                <input type="number" name="weight" required=' ' min="1" max="2000" placeholder="grams" onfocus="this.placeholder = ''"
                                       onblur="this.placeholder = 'grams'" class="dropdown-select wide">
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-4 col-lg-4 col-md-6">
                        <div class="ml-container">
                            <div class="ml-main">
                                <h3>${mleatperiod}</h3>
                                <select class="select-time" name="eat_period">
                                    <c:forEach var="eatPeriod" items="${eatPeriods}">
                                        <option value="${eatPeriod.getId()}">${eatPeriod.getPeriod()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-2 col-lg-4 col-md-6">
                        <div class="ml-container">
                            <div class="ml-main">
                                <button type="submit" class="genric-btn success-border circle btn-main">${mlbtnadd}</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <table class="table" style="background-color: whitesmoke">
                <thead>
                <tr class="d-flex">
                    <th class="col-4">${mleatperiod}</th>
                    <th class="col-3">${mlfood}</th>
                    <th class="col-3">${mlweight}</th>
                    <th class="col-2">
                        <button class="genric-btn success-border circle btn-del" onclick="Delete()">${mlbtndelete}</button>
                    </th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="mealTime" items="${ meals }">
                    <c:if test = "${fn:length(mealTime.value) == 0}">
                    <tr class="d-flex">
                        <th class="col-4">${ mealTime.key.getPeriod() }</th>
                        <td class="col-3"></td>
                        <td class="col-3"></td>
                        <td class="col-2"></td>
                    </tr>
                    </c:if>
                    <c:if test = "${fn:length(mealTime.value) == 1}">
                        <c:forEach var="mealsTime" items="${mealTime.value}" end="0">
                            <tr class="d-flex">
                                <th class="col-4">${ mealTime.key.getPeriod() }</th>
                                <td class="col-3">${ mealsTime.getProduct().getName() }</td>
                                <td class="col-3">${ mealsTime.getWeight() }</td>
                                <td class="col-2">
                                    <input class="cl_td mr-check" type="checkbox" name="toDelete" value="${ mealsTime.getId() }">
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test = "${fn:length(mealTime.value) > 1}">
                        <c:forEach var="mealsTime" items="${mealTime.value}" end="0">
                            <tr class="d-flex ">
                                <th class="col-4"> ${ mealTime.key.getPeriod() } </th>
                                <td class="col-3">${ mealsTime.getProduct().getName() }</td>
                                <td class="col-3">${ mealsTime.getWeight() }</td>
                                <td class="col-2">
                                    <input class="cl_td mr-check" type="checkbox" name="toDelete" value="${ mealsTime.getId() }">
                                </td>
                            </tr>
                        </c:forEach>
                        <c:forEach begin = "1" var="mealsTime" items="${mealTime.value}">
                            <tr class="d-flex">
                                <td  class="col-4"></td>
                            <td class="col-3">${ mealsTime.getProduct().getName() }</td>
                            <td class="col-3">${ mealsTime.getWeight() }</td>
                            <td class="col-2">
                                <input class="cl_td mr-check" type="checkbox" name="toDelete" value="${ mealsTime.getId() }">
                            </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- slider_area_end -->

<%@ include file="footer.jsp" %>

<!-- Modal -->
<div class="modal fade <c:if test="${sessionScope.modal}">show</c:if>" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">${mladdnewfood}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form class="fr-add" action="/user/product" method="POST">

                <c:if test="${sessionScope.error_name}">
                    <div class="alert alert-danger">
                        <a href="#" class="close">x</a>
                        ${error_name}
                    </div>
                </c:if>
                <c:if test="${sessionScope.error_calories}">
                    <div class="alert alert-danger">
                        <a href="#" class="close">x</a>
                            ${error_calories}
                    </div>
                </c:if>
                <c:if test="${sessionScope.error_protein || sessionScope.error_fats || sessionScope.error_carbohydrates}">
                    <div class="alert alert-danger">
                        <a href="#" class="close">x</a>
                            ${error_nutritions}
                    </div>
                </c:if>
                <c:if test="${sessionScope.error}">
                    <div class="alert alert-danger">
                        <a href="#" class="close">x</a>
                        <strong>${oh}</strong> ${error}
                    </div>
                </c:if>

                <div class="modal-body">
                    <div class="form-group">
                        <label for="name" class="col-form-label">${mlname}:</label>
                        <input type="text" class="form-control" id="name" name="name">
                        <p class="invalid-p"></p>
                    </div>
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label for="calories" class="col-form-label">${mlcalories}:</label>
                                <input type="text" class="form-control" id="calories" name="calories">
                                <p class="invalid-p"></p>
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="protein" class="col-form-label">${mlprotein}:</label>
                                <input type="text" class="form-control" id="protein" name="protein">
                                <p class="invalid-p"></p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label for="fats" class="col-form-label">${mlfats}:</label>
                                <input type="text" class="form-control" id="fats" name="fats">
                                <p class="invalid-p"></p>
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="carbohydrates" class="col-form-label">${mlcarbohydrates}:</label>
                                <input type="text" class="form-control" id="carbohydrates" name="carbohydrates">
                                <p class="invalid-p"></p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">${mlbtnclose}</button>
                    <button type="submit" class="btn btn-warning add-sbm">${mlbtnaddfood}</button>
                </div>
            </form>
        </div>
    </div>
</div>
<p hidden id="error_name">${enter_error_name}</p>
<p hidden id="error_cal">${enter_error_cal}</p>
<p hidden id="error_nutr">${enter_error_nutr}</p>
<p hidden id="error_num">${enter_error_num}</p>
<p hidden id="error_empty">${enter_error_empty}</p>
<p hidden id="btn_add_new_food">${new_food}</p>
<%
    session.removeAttribute("error_name");
    session.removeAttribute("error_calories");
    session.removeAttribute("error_protein");
    session.removeAttribute("error_fats");
    session.removeAttribute("error_carbohydrates");
    session.removeAttribute("error");
    session.removeAttribute("modal");
%>
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

<script src="https://code.jquery.com/jquery-3.4.0.min.js"
        integrity="sha256-BJeo0qm959uMBGb65z40ejJYGSgR7REI4+CW1fNKwOg=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

<script src="/static/js/meals.js"></script>
<script src="/static/js/validator.js"></script>
<script src="/static/js/message.js"></script>

<script>
    function Delete(){
        var data = { 'toDelete' : []};
        $(".cl_td:checkbox:checked").each(function() {
            data['toDelete'].push($(this).val());
        });
        $.post("/user/meals/delete", data, function(data, status) {
            window.location.reload();
        });
    };
</script>

</body>

</html>