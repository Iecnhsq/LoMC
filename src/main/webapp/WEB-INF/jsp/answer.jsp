<%@include file="../jspf/headerNoIsLogin.jspf" %>
<%@include file="../jspf/navigationNoIsLogin.jspf" %>
<div class="comdiv">
    <h1>Password Recovery</b></h1>
    <p>We just sent you to E-mail letter with validation code. 
        Please enter it in the box below.</p>
    <fieldset>
        <form action="answer.html" method="POST">
            <input type="text" placeholder="Code from E-mail" name="answer" required /><br>
            <input type="submit" value="Apply" onclick="playAudio();"/>
        </form>
    </fieldset>
</div>
<%@include file="../jspf/footer.jspf" %>