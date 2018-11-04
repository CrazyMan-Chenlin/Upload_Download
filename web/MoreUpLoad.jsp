<%--
  Created by IntelliJ IDEA.
  User: chenlin
  Date: 2018.10.14
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload</title>
</head>
<body>
   <form enctype="multipart/form-data" action="${pageContext.request.contextPath}/MoreUpLoadServlet" method="post">
       <input type="file" name="file1">
       <input type="file" name="file2">
       <input type="file" name="file3">
       <input type="submit" value="上传"/>
   </form>
</body>
</html>
