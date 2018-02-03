<%@include file="../jspf/headerNoIsLogin.jspf" %>
<div class="block_parent">
    <div class="clear"></div>
    <div class="block_left">
        <h1>Authorization</h1>
        <fieldset>
            <form action="login.html" method="POST">
                <input type="text" placeholder="Login" name="login" required/><br>
                <input type="password" placeholder="Password" name="password" required/><br>
                <input type="submit" value="Sign in" onclick="playAudio();"/>
            </form>
        </fieldset>
    </div>
    <div class="block_right">
        <%@include file="../jspf/navigationNoIsLogin.jspf" %>
    </div>
    <div class="clear"></div>
</div>
<%@include file="../jspf/footer.jspf" %>