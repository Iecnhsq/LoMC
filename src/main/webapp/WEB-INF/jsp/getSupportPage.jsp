<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:forEach items="${listSupport}" var="ls">
            ${ls.id} || ${ls.ploblem} || ${ls.subject} || ${ls.email} ||${ls.message} || ${ls.date}<br>
        </c:forEach>
    </body>
</html>
