<%@include file="../jspf/headerNoIsLogin.jspf" %>
<div class="block_parent">
    <div class="clear"></div>
    <div class="block_left">
        <h1>Password Recovery</h1>
        <p>If you have forgotten your password, enter the information below and you will receive a message 
            in the course of 5 minutes with the data to restore access to your account</p>
        <fieldset>
        <!--    <div>
                <c:if test="${!isValid}">
                    <font style="color: red">Your Login or E-mail are incorrect! Please try again!</font>
                </c:if>
            </div>  -->
            <form action="recovery.html" method="POST">
                <input type="text" placeholder="Login" name="login" required /><br>
                <input type="email" placeholder="Your E-mail" name="email" required /><br>
                <input type="submit" value="Recovery" onclick="playAudio();"/>     
            </form>
        </fieldset>
    </div>
    <div class="block_right">
        <%@include file="../jspf/navigationNoIsLogin.jspf" %>
    </div>
    <div class="clear"></div>
</div>
<%@include file="../jspf/footer.jspf" %>