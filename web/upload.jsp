<%--
  Created by IntelliJ IDEA.
  User: chenlin
  Date: 2018.10.14
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload</title>
</head>
<body>
        <form method="post" action="${pageContext.request.contextPath}/UpLoadServlet" enctype="multipart/form-data">
            <input type="file" name="file1"/>
            <input type="submit" value="上传">
            <span style="color: red">${requestScope.Error}</span>
        </form>

</body>
</html>
