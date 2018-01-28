<%@include file="../jspf/headerNoIsLogin.jspf" %>
<%@include file="../jspf/navigationNoIsLogin.jspf" %>
<div class="comdiv">
    <h1>Registration</h1>
    <fieldset>
        <form action="register.html" method="POST">
            <input type="text" placeholder="Login" name="login" required/><br>
            <input type="password" placeholder="Password" name="password" required/><br>
            <input type="password" placeholder="Repeat Password" name="password2" required/><br>
            <input type="text" placeholder="City" name="city"/><br>
            <input type="tel" placeholder="Phone" name="phone"/><br>
            <input type="email" placeholder="E-Mail" name="email" required/><br>
            <input type="submit" value="Sign Up" onclick="playAudio();" />
        </form>
    </fieldset>
</div>
<%@include file="../jspf/footer.jspf" %>
