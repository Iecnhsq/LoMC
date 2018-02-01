<%@include file="../jspf/headerNoIsLogin.jspf" %>
<div class="block_parent">
    <div class="clear"></div>
    <div class="block_left">
        <h1>Password Recovery</h1>
        <p>We just sent you to E-mail letter with validation code. 
            Please enter it in the box below.</p>
        <fieldset>
            <form action="answer.html" method="POST">
                <input type="text" placeholder="Code from E-mail" name="answer" required /><br>
                <input type="submit" value="Apply" onclick="playAudio();"/>
            </form>
        </fieldset>
    </div>
    <div class="block_right">
        <%@include file="../jspf/navigationNoIsLogin.jspf" %>
    </div>
    <div class="clear"></div>
</div>
<%@include file="../jspf/footer.jspf" %>