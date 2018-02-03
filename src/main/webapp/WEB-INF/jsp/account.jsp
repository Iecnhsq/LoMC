<%@include file="../jspf/headerIsLogin.jspf" %>
<div class="block_parent">
    <div class="clear"></div>
    <div class="block_left">
        <h1>Change data</h1>
        <fieldset>
            <form action="account.html" method="POST">
                Enter pass:<input type="password" name="pass3" required/><br>
                Enter new pass:<input type="password" name="pass" required/><br>
                Enter pass again:<input type="password" name="pass2" required/><br>
                Enter city:<input type="text" placeholder="${u.city}" name="city" required/><br>
                Enter phone:<input type="tel" placeholder="${u.phone}" name="phone" required/><br>
                Enter e-mail:<input type="email" placeholder="${u.email}" name="email" required/><br>
                <input type="submit" value="Confirm changes" onclick="playAudio();"/>
            </form>
        </fieldset>
    </div>
    <div class="block_right">
        <%@include file="../jspf/navigationIsLogin.jspf" %>
    </div>
    <div class="clear"></div>
</div>
<%@include file="../jspf/footer.jspf" %>