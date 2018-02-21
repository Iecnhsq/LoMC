<%@include file="../jspf/headerIsLogin.jspf" %>
<div class="block_parent">
    <div class="clear"></div>
    <div class="block_left">
        <h1 style="text-align: center">Waiting for battles...</h1>
        <p style="text-align: center">
            Who expects: <b>${login}</b><br>
            Your class: <b>${classs}</b><br>
            Your level: <b>${level}</b>
        <p style="text-align: center">
            <a href="main.html?exit=true" onmousedown="playAudio();">You want to quit?</a>&emsp; | &emsp;
            <a href="main.html" onmousedown="playAudio();">Back to Main</a>
        <p>
        <div id="refresh">
            <script>
                function refresh() {
                    window.location.href = "http://localhost:8084/CardGame/wait.html";
                }
                setInterval("refresh()", 5000);
            </script>
            <form action="wait.html">
                <input id="refresh" type="submit" value="Refresh" onclick="playAudio();"/>
            </form>
        </div>
        </p>
        </p>
        </p>
    </div>
    <div class="block_right">
        <%@include file="../jspf/navigationIsLogin.jspf" %>
    </div>
    <div class="clear"></div>
</div>
<%@include file="../jspf/footer.jspf" %>