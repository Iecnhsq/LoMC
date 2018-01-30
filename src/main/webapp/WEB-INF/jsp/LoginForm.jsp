<!--
<%@include file="../jspf/headerNoIsLogin.jspf" %>
<%@include file="../jspf/navigationNoIsLogin.jspf" %>
<div class="comdiv">
    <h1>Authorization</h1>
    <fieldset>
        <form action="login.html" method="POST">
            <input type="text" placeholder="Login" name="login" required/><br>
            <input type="password" placeholder="Password" name="password" required/><br>
            <input type="submit" value="Sign in" onclick="playAudio();"/>
        </form>
    </fieldset>
</div>
<%@include file="../jspf/footer.jspf" %>
-->

<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <style>
            .error {
                color: red; font-weight: bold;
            }
        </style>
    </head>
    <body>
        <div align="center">
            <h2>Spring MVC Form Validation Demo - Login Form</h2>
            <table border="0" width="90%">
                <form:form action="login" commandName="userForm">
                    <tr>
                        <td align="left" width="20%">Login: </td>
                        <td align="left" width="40%"><form:input path="login" size="30"/></td>
                    <td align="left"><form:errors path="login" cssClass="error"/></td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td><form:password path="password" size="30"/></td>
                    <td><form:errors path="password" cssClass="error"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td align="center"><input type="submit" value="Login"/></td>
                        <td></td>
                    </tr>
                </form:form>
            </table>
        </div>
    </body>
</html>