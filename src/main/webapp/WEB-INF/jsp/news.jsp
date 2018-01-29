<%@include file="../jspf/headerNoIsLogin.jspf" %>
<%@include file="../jspf/navigationNoIsLogin.jspf" %>
<div class="comdiv">
    <h1>News on Lord Of Magic Cards</h1><br>
    <c:forEach items="${allnews}" var="a">
        <div style="width: 400px; margin: 0px auto; border: 1px solid bisque; padding: 10px">
            <p style="text-align: center">
                <b style="color: yellow; font-size: 12pt">${a.timein} by ${a.author}</b><br>
            <h3 style="text-align: center">${a.subject}</h3>
            </p>
            <p style="text-align: justify; color: gainsboro">${a.text}</p>
        </div><br>
    </c:forEach>
</div>
<%@include file="../jspf/footer.jspf" %>
