<%--
  Created by IntelliJ IDEA.
  User: chenlin
  Date: 2018.10.14
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
      <table border="1px">
          <tr>
              <th>编号</th>
              <th>文件名</th>
              <th>文件大小</th>
              <th>文件类型</th>
              <th>上传时间</th>
          </tr>

          <c:forEach items="${requestScope.upLoadFiles}" var="file" varStatus="fileStatus">
          <tr>
              <td>${fileStatus.count}</td>
              <td>${file.fileName}</td>
              <td>${file.size}</td>
              <td>${file.contentType}</td>
              <td>${file.upLoadTime}</td>
          </tr>
          </c:forEach>

      </table>
</body>
</html>
