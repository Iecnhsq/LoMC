<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="../jspf/headerNoIsLogin.jspf" %>
<div class="block_parent">
    <div class="clear"></div>
    <div class="block_left">
        <h1>Authorization</h1>
        <fieldset>
            <table>
                <font style="color: red">${ex}</font>
                <form:form action="login" commandName="userForm">
                    <tr>
                        <td>Login: </td>
                        <td><form:input path="login" size="30"/></td>
                        <td><form:errors path="login" cssClass="error"/></td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td><form:password path="pass" size="30"/></td>
                        <td><form:errors path="pass" cssClass="error"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td align="center"><input type="submit" value="Sign in" onclick="playAudio();"/></td>
                        <td></td>
                    </tr>
                </form:form>
            </table>
        </fieldset>
    </div>
    <div class="block_right">
        <%@include file="../jspf/navigationNoIsLogin.jspf" %>
    </div>
    <div class="clear"></div>
</div>
<%@include file="../jspf/footer.jspf" %>