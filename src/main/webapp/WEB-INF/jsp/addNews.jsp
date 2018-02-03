<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADM Tools</title>
    </head>
    <body>
        <div>
            <fieldset>
                <form action="addNews.html" method="POST">
                    <input type="text" placeholder="Subject" name="subject" required/><br>
                    <input type="text" value="${login}" name="author" required/><br>
                    <textarea required name="text" maxlength="250" placeholder="Enter Your text here..."></textarea><br>
                    <input type="submit" value="Send"/>                    
                </form>
            </fieldset>
            <a href="admTools.html">Back to main</a>
        </div>
        <div>
            <c:forEach items="${allnews}" var="a">
                <div style="width: 400px; margin: 0px auto; border: 1px solid bisque; padding: 10px">
                    <p style="text-align: center">
                        <b style="color: yellow; font-size: 12pt">${a.timein} by ${a.author}</b><br>
                    <h3 style="text-align: center">${a.subject}</h3>
                    </p>
                    <p style="text-align: justify; color: gainsboro">${a.text}</p>
                </div><br>
            </c:forEach>
        </div>
    </body>
</html>
