<%@include file="../jspf/headerNoIsLogin.jspf" %>
<div class="block_parent">
    <div class="clear"></div>
    <div class="block_left">
        <h1>Write a message to the support team</h1>
        <p>
            If you have any problems with the game, write to us. Specify the name 
            of the problem and briefly describe its essence.<br>
            We will try to solve it in the shortest possible time.
        </p>
        <fieldset>
            <form action="support.html" method="POST">
                <input type="text" placeholder="Your problem" required name="problem"/><br>
                <input type="text" placeholder="Subject" required name="subject"/><br>
                <input type="email" placeholder="Your E-mail" name="email" required/><br>
                <textarea required name="message" maxlength="250" placeholder="Enter Your message..."></textarea><br>             
                <input type="submit" value="Send" onclick="playAudio();"/>
            </form>
        </fieldset>
    </div>
    <div class="block_right">
        <%@include file="../jspf/navigationNoIsLogin.jspf" %>
    </div>
    <div class="clear"></div>
</div>
<%@include file="../jspf/footer.jspf" %>