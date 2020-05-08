<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:message bundle="${locale}" key="footer.mission" var="mission"/>
<fmt:message bundle="${locale}" key="about.body" var="mission_sub"/>
<fmt:message bundle="${locale}" key="footer.head" var="head"/>
<fmt:message bundle="${locale}" key="footer.subscribe" var="subscribe"/>
<fmt:message bundle="${locale}" key="footer.subtext" var="subtext"/>
<fmt:message bundle="${locale}" key="footer.home" var="home"/>
<fmt:message bundle="${locale}" key="footer.about" var="about"/>
<fmt:message bundle="${locale}" key="footer.recipes" var="recipes"/>
<fmt:message bundle="${locale}" key="footer.personal.info" var="personal_info"/>
<fmt:message bundle="${locale}" key="footer.contact" var="contact"/>

<!-- footer  -->
<footer class="footer">
    <div class="footer_top">
        <div class="container">
            <div class="row">
                <div class="col-xl-5 col-md-6 col-lg-5">
                    <div class="footer_widget text-center">
                        <h3 class="footer_title">
                            ${mission}
                        </h3>
                        <p>${mission_sub}</p>
                    </div>
                </div>

                <div class="col-xl-3 col-md-6 col-lg-3">
                    <div class="footer_widget text-center">
                        <h3 class="footer_title">
                            ${head}
                        </h3>
                        <ul>
                            <li><a href="/">${home}</a></li>
                            <li><a href="/info/about">${about}</a></li>
                            <li><a href="/info/recipes">${recipes}</a></li>
                            <li><a href="/user/meals">${personal_info}</a></li>
                            <li><a href="/user/contact">${contact}</a></li>
                        </ul>

                    </div>
                </div>
                <div class="col-xl-4 col-md-6 col-lg-4">
                    <div class="footer_widget">
                        <h3 class="footer_title">
                            ${subscribe}
                        </h3>
                        <p class="newsletter_text">${subtext}</p>
                        <form action="#" class="newsletter_form">
                            <input type="text" placeholder="Enter your mail">
                            <button type="submit"> <i class="ti-arrow-right"></i> </button>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="copy-right_text">
        <div class="container">
            <div class="footer_border"></div>
            <div class="row align-items-center">
                <div class="col-xl-8 col-md-8">
                </div>
                <div class="col-xl-4 col-md-4">
                    <div class="socail_links">
                        <ul>
                            <li>
                                <a href="#">
                                    <i class="ti-facebook"></i>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <i class="ti-twitter-alt"></i>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <i class="fa fa-dribbble"></i>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <i class="fa fa-behance"></i>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
<!--/ footer  -->
