<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>

<head>
    <link href="<c:url value='/static/css/client_info_registration.css' />" rel="stylesheet"></link>
</head>

<body>
<form id="msform" action="/signup" method="POST">
    <!-- progressbar -->
    <ul id="progressbar">
        <li class="active">Account Setup</li>
        <li>Social Profiles</li>
        <li>Personal Details</li>
    </ul>
    <!-- fieldsets -->
    <fieldset>
        <h2 class="fs-title">Create your account</h2>
        <h3 class="fs-subtitle">This is step 1</h3>
        <input type="text" name="email" placeholder="Email"/>
        <input type="password" name="pass" placeholder="Password"/>
        <input type="password" name="cpass" placeholder="Confirm Password"/>
        <input type="button" name="next" class="next action-button" value="Next"/>
    </fieldset>
    <fieldset>
        <h2 class="fs-title">Social Profiles</h2>
        <h3 class="fs-subtitle">Your presence on the social network</h3>
        <input type="text" name="twitter" placeholder="Twitter"/>
        <input type="text" name="facebook" placeholder="Facebook"/>
        <input type="text" name="gplus" placeholder="Google Plus"/>
        <input type="button" name="previous" class="previous action-button" value="Previous"/>
        <input type="button" name="next" class="next action-button" value="Next"/>
    </fieldset>
    <fieldset>
        <h2 class="fs-title">Personal Details</h2>
        <h3 class="fs-subtitle">We will never sell it</h3>
        <input type="text" name="fname" placeholder="First Name"/>
        <input type="text" name="lname" placeholder="Last Name"/>
        <input type="text" name="phone" placeholder="Phone"/>
        <textarea name="address" placeholder="Address"></textarea>
        <input type="button" name="previous" class="previous action-button" value="Previous"/>
        <input type="submit" name="submit" class="submit action-button" value="Submit"/>
    </fieldset>
</form>
  
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://gsgd.co.uk/sandbox/jquery/easing/jquery.easing.1.3.js"></script>
<script src="<c:url value='/static/js/client_info_registration.js' />"></script>
</html>
