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
