<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<title>Sign Up</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://unpkg.com/tachyons/css/tachyons.min.css">
<body class="w-100 sans-serif">
    <section class="flex items-center">
        <article class="pa4 black-80 w-100">
            <form action="${pageContext.request.contextPath}/register" method="post" accept-charset="utf-8">
                <fieldset id="sign_up" class="ba b--transparent ph0 mh0">
                    <legend class="ph0 mh0 fw6 clip">Sign Up</legend>
                    <div class="mt3">
                        <label class="db fw4 lh-copy f6" for="email">Email address</label>
                        <input class="pa2 input-reset ba bg-transparent w-100 measure" type="email" name="email"  id="email">
                    </div>
                    <div class="mt3">
                        <label class="db fw4 lh-copy f6" for="password">Password</label>
                        <input class="pa2 input-reset ba bg-transparent w-100 measure" type="password" name="password"  id="password">
                    </div>
                    <div class="mt3">
                        <label class="db fw4 lh-copy f6" for="firstName">firstName</label>
                        <input class="pa2 input-reset ba bg-transparent w-100 measure" type="text" name="firstName"  id="firstName">
                    </div>
                    <div class="mt3">
                        <label class="db fw4 lh-copy f6" for="lastName">lastName</label>
                        <input class="pa2 input-reset ba bg-transparent w-100 measure" type="text" name="lastName"  id="lastName">
                    </div>
                </fieldset>
                <div class="mt3"><input class="b ph3 pv2 input-reset ba b--black bg-transparent grow pointer f6" type="submit" value="Sign Up"></div>
            </form>
        </article>
    </section>
</body>
</html>