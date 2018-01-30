<%@include file="../jspf/headerNoIsLogin.jspf" %>
<%@include file="../jspf/navigationNoIsLogin.jspf" %>
<div class="comdiv">
    <h1>Password Recovery</h1>
    <p>If you have forgotten your password, enter the information below and you will receive a message 
        in the course of 5 minutes with the data to restore access to your account</p>
    <fieldset>
        <form action="recovery.html" method="POST">
            <input type="text" placeholder="Login" name="login" required /><br>
            <input type="email" placeholder="Your E-mail" name="email" required /><br>
            <input type="submit" value="Recovery" onclick="playAudio();"/>     
        </form>
    </fieldset>
</div>
<%@include file="../jspf/footer.jspf" %>
