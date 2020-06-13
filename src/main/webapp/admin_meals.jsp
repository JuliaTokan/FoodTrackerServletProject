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
<fmt:message bundle="${locale}" key="ml.search" var="mlsearch"/>
<fmt:message bundle="${locale}" key="pr.edit.food" var="preditfood"/>
<fmt:message bundle="${locale}" key="pr.edit" var="predit"/>
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
    <link rel="stylesheet" href="/static/css/message.css">
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

<%@ include file="/header.jsp" %>

<!-- slider_area_start -->
<div class="slider_area">
    <div class="single_slider  d-flex slider_bg_4 ">
        <div class="container main-container">
            <div class="row">
                <div class="col-xl-5 col-lg-4 col-md-6">
                    <form action="/admin/products" method="get">
                    <div class="input-group">
                        <input type="text" name="name" class="form-control" placeholder="${mlsearch}">
                        <div class="input-group-append">
                            <button type="submit" class="btn btn-secondary">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>
                    </div>
                    </form>
                </div>
                <div class="col-xl-7 col-lg-4 col-md-6">
                    <button type="button" class="mb-3 float-right genric-btn success-border circle btn-del"
                            data-toggle="modal" data-target="#addModal">${mladdnewfood}
                    </button>
                </div>
            </div>
            <table class="table" style="background-color: whitesmoke">
                <thead>
                <tr>
                    <th scope="col">${mlname}</th>
                    <th scope="col">${mlcalories}</th>
                    <th scope="col">${mlprotein}</th>
                    <th scope="col">${mlfats}</th>
                    <th scope="col">${mlcarbohydrates}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="product" items="${products}">
                    <tr class="item" id="${product.getId()}" data-public="${product.getCommon()}">
                        <td id="item-name">${product.getName()}</td>
                        <td id="item-calories">${product.getCalories()}</td>
                        <td id="item-protein">${product.getProtein()}</td>
                        <td id="item-fats">${product.getFats()}</td>
                        <td id="item-carbohydrates">${product.getCarbohydrates()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <c:forEach var = "i" begin = "1" end = "${pages}">
                        <li class="page-item"><a class="page-link text-warning" href="/admin/products?page=${i}">${i}</a></li>
                    </c:forEach>
                    <!--<li class="page-item"><a class="page-link" href="/admin/products?page=1">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>-->
                </ul>
            </nav>
        </div>
    </div>
</div>

<!-- slider_area_end -->

<%@ include file="/footer.jsp" %>

<!-- Modal Add-->
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
                    <div class="row">
                        <div class="col">
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" id="privateCheck" name="public">
                                <label class="form-check-label" for="privateCheck">${mlpublic}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">${mlbtnclose}</button>
                    <button type="submit" class="btn btn-warning add-sbm">${mladdnewfood}</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Modal Edit-->
<div class="modal fade <c:if test="${sessionScope.modaledit}">show</c:if>" id="editModal" tabindex="-1" role="dialog" aria-labelledby="ed_exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="ed_exampleModalLabel">${preditfood}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form class="fr-edit" action="/admin/product/edit" method="POST">

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
                    <input type="text" class="form-control" id="ed_id" name="id" hidden>
                    <div class="form-group">
                        <label for="ed_name" class="col-form-label">${mlname}:</label>
                        <input type="text" class="form-control" id="ed_name" name="name">
                        <p class="invalid-p"></p>
                    </div>
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label for="ed_calories" class="col-form-label">${mlcalories}:</label>
                                <input type="text" class="form-control" id="ed_calories" name="calories">
                                <p class="invalid-p"></p>
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="ed_protein" class="col-form-label">${mlprotein}:</label>
                                <input type="text" class="form-control" id="ed_protein" name="protein">
                                <p class="invalid-p"></p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label for="ed_fats" class="col-form-label">${mlfats}:</label>
                                <input type="text" class="form-control" id="ed_fats" name="fats">
                                <p class="invalid-p"></p>
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="ed_carbohydrates" class="col-form-label">${mlcarbohydrates}:</label>
                                <input type="text" class="form-control" id="ed_carbohydrates" name="carbohydrates">
                                <p class="invalid-p"></p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" id="ed_privateCheck" name="public">
                                <label class="form-check-label" for="ed_privateCheck">${mlpublic}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">${mlbtnclose}</button>
                    <button type="submit" class="btn btn-warning edit-sbm">${predit}</button>
                    <button type="button" class="btn btn-danger">${mlbtndelete}</button>
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
<%
    session.removeAttribute("error_name");
    session.removeAttribute("error_calories");
    session.removeAttribute("error_protein");
    session.removeAttribute("error_fats");
    session.removeAttribute("error_carbohydrates");
    session.removeAttribute("error");
    session.removeAttribute("modal");
    session.removeAttribute("modaledit");
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
<script src="/static/js/admin_product.js"></script>
<script src="/static/js/validator.js"></script>
<script src="/static/js/message.js"></script>

</body>

</html>