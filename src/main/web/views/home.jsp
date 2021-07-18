<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<title> </title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://unpkg.com/tachyons/css/tachyons.min.css">
<body class="w-100 sans-serif">
    <section class="flex items-center">
        <div class="pa4 lh-copy">
            <a class="f4 fw6 db black link dim" href="${pageContext.request.contextPath}/register">회원 가입</a>
            <a class="f4 fw6 db near-black link dim" href="${pageContext.request.contextPath}/login">로그인</a>
            <a class="f4 fw6 db dark-gray link dim" href="#0">Dim on hover </a>
            <a class="f4 fw6 db mid-gray link dim" href="#0">Dim on hover </a>
            <a class="f4 fw6 db gray link dim" href="#0">Dim on hover </a>
            <a class="f4 fw6 db dark-red link dim" href="#0">Dim on hover </a>
            <a class="f4 fw6 db red link dim" href="#0">Dim on hover </a>
            <a class="f4 fw6 db purple link dim" href="#0">Dim on hover </a>
            <a class="f4 fw6 db light-purple link dim" href="#0">Dim on hover </a>
            <a class="f4 fw6 db dark-pink link dim" href="#0">Dim on hover </a>
            <a class="f4 fw6 db light-pink link dim" href="#0">Dim on hover </a>
            <a class="f4 fw6 db dark-green link dim" href="#0">Dim on hover </a>
            <a class="f4 fw6 db green link dim" href="#0">Dim on hover </a>
            <a class="f4 fw6 db navy link dim" href="#0">Dim on hover </a>
            <a class="f4 fw6 db dark-blue link dim" href="#0">Dim on hover </a>
            <sec:authorize access="isAnonymous()">
                <a class="f4 fw6 db blue link dim" href="#0">Dim on hover </a>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <a class="f4 fw6 db blue link dim" onclick="e.preventDefault(); document.querySelector('#logoutForm').submit();">로그아웃</a>
                <form id="logoutForm" action="${pageContext.request.contextPath}/logout" method="post" style="visibility: hidden;">
                    <sec:csrfInput/>
                </form>
            </sec:authorize>
        </div>
    </section>
</body>
</html>