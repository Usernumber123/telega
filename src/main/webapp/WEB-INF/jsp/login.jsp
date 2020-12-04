
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>

    <style type="text/css">
        <%@include file="../../resources/css/common.css" %>
    </style>
</head>

<body>
<div class="container">

        <h2 class="form-heading">Log in</h2>
    <form method="POST" action="${contextPath}/login" class="form-signin">
        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="username" type="text" class="form-control" placeholder="Username"
                   autofocus="true"/>
            <input name="password" type="password" class="form-control" placeholder="Password"/>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button  class="form-group" href="${contextPath}/chat"  name="LogIn" value="LogIn" type="submit">Log In</button>
            <span>${error}</span>
            <h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>

        </div>
    </form>
</div>



</body>
</html>