<%@include file="../jspf/headerIsLogin.jspf" %>
<div class="block_parent">
    <div class="clear"></div>
    <div class="block_left">
        <c:if test="${nameP1==login}">
            <div style="margin: 100px auto; width: 350px">
                <c:if test="${p1Win}">
                    <h1 style="text-align: center">${nameP1}Winner</h1>
                    <h5 style="text-align: center">You have more points</h5>
                </c:if>
                <c:if test="${!p1Win}">
                    <h1 style="text-align: center">Loser</h1>
                </c:if>
                <fieldset>
                    Your login: ${nameP1}<br>
                    Opponent login: ${nameP2}<br>
                    You was have: ${oldPointsP1}<br>
                    Previous lvl point: ${p1OldLvlPoints}<br>
                    You got: ${newPointsP1} points<br>
                    Next lvl point: ${p1NextLvlPoints}<br>
                    Your point now: ${pointNowP1}<br>
                    Your lvl: ${p1Lvl}<br>
                    Next lvl: ${p1Lvl+1}
                    <p><a href="finish.html?refresh=y" onmousedown="playAudio();">Go to main</a></p>
                </fieldset>
            </div>
        </c:if>
        <c:if test="${nameP2==login}">
            <div style="margin: 100px auto; width: 350px">
                <c:if test="${p2Win}">
                    <h1 style="text-align: center">${nameP2}Winner</h1>
                    <h5 style="text-align: center">You have more points</h5>
                </c:if>
                <c:if test="${!p2Win}">
                    <h1 style="text-align: center">Loser</h1>
                </c:if>
                <fieldset>
                    Your login: ${nameP2}<br>
                    Opponent login: ${nameP1}<br>
                    You was have: ${oldPointsP2}<br>
                    Previous lvl point: ${p2OldLvlPoints}<br>
                    You got: ${newPointsP2} points<br>
                    Next lvl point: ${p2NextLvlPoints}<br>
                    Your point now: ${pointNowP2}<br>
                    Your lvl: ${p2Lvl}<br>
                    Next lvl: ${p2Lvl+1}
                    <p><a href="finish.html?refresh=y" onmousedown="playAudio();">Go to main</a></p>
                </fieldset>
            </div>
        </c:if>
    </div>
    <div class="block_right">
        <%@include file="../jspf/navigationIsLogin.jspf" %>
    </div>
    <div class="clear"></div>
</div>
<%@include file="../jspf/footer.jspf" %>