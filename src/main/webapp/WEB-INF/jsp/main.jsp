<%@include file="../jspf/headerIsLogin.jspf" %>
<div class="block_parent">
    <div class="clear"></div>
    <div class="block_left">
        <c:if test="${card.size()==0}">
            <h1 style="text-align: center">Alas, your deck is not complete... :(</h1>
        </c:if>
        <c:forEach items="${card}" var="c">
            <%@include file="../jspf/cardModel.jspf" %>
        </c:forEach>
    </div>
    <div class="block_right">
        <%@include file="../jspf/navigationIsLogin.jspf" %>
    </div>
    <div class="clear"></div>
</div>
<%@include file="../jspf/footer.jspf" %>