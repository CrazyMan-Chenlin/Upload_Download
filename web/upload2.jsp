<%--
  Created by IntelliJ IDEA.
  User: chenlin
  Date: 2018.10.16
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload</title>
</head>
<body>
  <form enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/UpLoadServlet2">
      <input type="file" name="attachment"/>
      <input type="submit" value="上传"/>
      描述：<input type="text" name="text1"/>
  </form>
</body>
</html>
