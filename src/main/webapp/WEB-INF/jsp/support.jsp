<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="../jspf/headerNoIsLogin.jspf" %>
<div class="block_parent">
    <div class="clear"></div>
    <div class="block_left">
        <h1>Write a message to the support team</h1>
        <p>
            If you have any problems with the game, write to us. Specify the name 
            of the problem and briefly describe its essence.<br>
            We will try to solve it in the shortest possible time.
        </p>
        <fieldset>
            <table>
                <form:form action="support" commandName="supportForm">
                    <tr>
                        <td>Problem: </td>
                        <td><form:input path="problem" size="30"/></td>
                        <td><form:errors path="problem" cssClass="error"/></td>
                    </tr>
                    <tr>
                        <td>Subject: </td>
                        <td><form:input path="subject" size="30"/></td>
                        <td><form:errors path="subject" cssClass="error"/></td>
                    </tr>
                    <tr>
                        <td>E-mail: </td>
                        <td><form:input path="email" size="30"/></td>
                        <td><form:errors path="email" cssClass="error"/></td>
                    </tr>
                    <tr>
                        <td>Message: </td>
                        <td><form:textarea path="message" maxlength="250"/></td>
                        <td><form:errors path="message" cssClass="error"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td align="center"><input type="submit" value="Send" onclick="playAudio();"/></td>
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