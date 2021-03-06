<%@include file="../jspf/headerIsLogin.jspf" %>
<div class="block_parent">
    <div class="clear"></div>
    <div class="block_left">
        Cards
    </div>
    <div class="block_right">
        <%@include file="../jspf/navigationIsLogin.jspf" %>
    </div>
    <div class="clear"></div>
</div>
<div>
    <p>Select a deck of cards and a hero. Maximum of cards in a deck of 10. Carefully 
        choose the cards in order not to lose =)
    </p>
    <div style="margin: 0px auto; width: 700px; font-size: 14pt; text-align: center">
        <div style="clear: both"></div>
        <div class="class_set" <c:if test="${heroClass.equals('Hunter')}">style="border: 2px solid firebrick; border-radius: 10px"</c:if>><a href="cards.html?idclass=Hunter"><img src="src/img/icon-hunter.png"/><br>Hunter</a></div>
        <div class="class_set" <c:if test="${heroClass.equals('Mage')}">style="border: 2px solid firebrick; border-radius: 10px"</c:if>><a href="cards.html?idclass=Mage"><img src="src/img/icon-mage.png"/><br>Mage</a></div>         
        <div class="class_set" <c:if test="${heroClass.equals('Priest')}">style="border: 2px solid firebrick; border-radius: 10px"</c:if>><a href="cards.html?idclass=Priest"><img src="src/img/icon-priest.png"/><br>Priest</a></div>
        <div class="class_set" <c:if test="${heroClass.equals('Thief')}">style="border: 2px solid firebrick; border-radius: 10px"</c:if>><a href="cards.html?idclass=Thief"><img src="src/img/icon-rogue.png"/><br>Thief</a></div>
        <div class="class_set" <c:if test="${heroClass.equals('Shaman')}">style="border: 2px solid firebrick; border-radius: 10px"</c:if>><a href="cards.html?idclass=Shaman"><img src="src/img/icon-shaman.png"/><br>Shaman</a></div> 
        <div class="class_set" <c:if test="${heroClass.equals('Warrior')}">style="border: 2px solid firebrick; border-radius: 10px"</c:if>><a href="cards.html?idclass=Warrior"><img src="src/img/icon-warrior.png"/><br>Warrior</a></div>
            <div style="clear: both"></div>
        </div>
    <%@include file="../jspf/classCardSet.jspf" %>
</div>
<div style="margin: 20px auto; width: 1044px">
    <div style="clear: both"></div>
    <div class="allCards">
        <c:forEach items="${level0Cards}" var="c">
            <a href="cards.html?id=${c.id}"><%@include file="../jspf/cardModel.jspf" %></a>
        </c:forEach>
        <hr>
        <c:forEach items="${level1Cards}" var="c">
            <a href="cards.html?id=${c.id}"><%@include file="../jspf/cardModel.jspf" %></a>
        </c:forEach>
        <hr>
        <c:forEach items="${level2Cards}" var="c">
            <a href="cards.html?id=${c.id}"><%@include file="../jspf/cardModel.jspf" %></a>
        </c:forEach>
        <hr>
        <c:forEach items="${level3Cards}" var="c">
            <a href="cards.html?id=${c.id}"><%@include file="../jspf/cardModel.jspf" %></a>
        </c:forEach>
        <hr>
        <c:forEach items="${level4Cards}" var="c">
            <a href="cards.html?id=${c.id}"><%@include file="../jspf/cardModel.jspf" %></a>
        </c:forEach>
        <hr>
        <c:forEach items="${level5Cards}" var="c">
            <a href="cards.html?id=${c.id}"><%@include file="../jspf/cardModel.jspf" %></a>
        </c:forEach>
        <hr>
        <c:forEach items="${level6Cards}" var="c">
            <a href="cards.html?id=${c.id}"><%@include file="../jspf/cardModel.jspf" %></a>
        </c:forEach>
        <hr>
        <c:forEach items="${level7Cards}" var="c">
            <a href="cards.html?id=${c.id}"><%@include file="../jspf/cardModel.jspf" %></a>
        </c:forEach>
        <hr>
        <c:forEach items="${level8Cards}" var="c">
            <a href="cards.html?id=${c.id}"><%@include file="../jspf/cardModel.jspf" %></a>
        </c:forEach>
        <hr>
        <c:forEach items="${level9Cards}" var="c">
            <a href="cards.html?id=${c.id}"><%@include file="../jspf/cardModel.jspf" %></a>
        </c:forEach>
        <hr>
        <c:forEach items="${level10Cards}" var="c">
            <a href="cards.html?id=${c.id}"><%@include file="../jspf/cardModel.jspf" %></a>
        </c:forEach>
    </div>
    <div class="deckCardscard">
        <c:forEach items="${deckCards}" var="c">
            <a href="cards.html?id=-${c.id}">
                <form action="http://localhost:8084/CardGame/">
                    <%@include file="../jspf/cardModel.jspf" %>
                </form>
            </a>
        </c:forEach>
        <a href="/CardGame/cards.html?getCards=1" style="margin-left: 80px">Get Cards</a>
    </div>
    <div style="clear: both"></div>
</div>
<%@include file="../jspf/footer.jspf" %>