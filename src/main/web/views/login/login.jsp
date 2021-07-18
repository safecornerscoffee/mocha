<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<title>Sign Up</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://unpkg.com/tachyons/css/tachyons.min.css">
<body class="w-100 sans-serif">
<section class="flex items-center">
    <main class="pa4 black-80">
        <form action="${pageContext.request.contextPath}/login" method="post" class="measure center">
            <sec:csrfInput/>
            <fieldset id="sign_up" class="ba b--transparent ph0 mh0">
                <legend class="f4 fw6 ph0 mh0">Sign In</legend>
                <div class="mt3">
                    <label class="db fw6 lh-copy f6" for="email">Email</label>
                    <input class="pa2 input-reset ba bg-transparent hover-bg-black hover-white w-100" type="email" name="email"  id="email">
                </div>
                <div class="mv3">
                    <label class="db fw6 lh-copy f6" for="password">Password</label>
                    <input class="b pa2 input-reset ba bg-transparent hover-bg-black hover-white w-100" type="password" name="password"  id="password">
                </div>
                <label class="pa0 ma0 lh-copy f6 pointer"><input type="checkbox"> Remember me</label>
            </fieldset>
            <div class="">
                <input class="b ph3 pv2 input-reset ba b--black bg-transparent grow pointer f6 dib" type="submit" value="Sign in">
            </div>
            <div class="lh-copy mt3">
                <a href="#0" class="f6 link dim black db">Sign up</a>
                <a href="#0" class="f6 link dim black db">Forgot your password?</a>
            </div>
        </form>
    </main>
</section>
</body>
</html>