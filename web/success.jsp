<%--
  Created by IntelliJ IDEA.
  User: chenlin
  Date: 2018.10.16
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>successs</title>
</head>
<body>
<table border="1px">
    <tr>
        <th>编号</th>
        <th>文件名</th>
        <th>文件大小</th>
        <th>文件类型</th>
        <th>上传时间</th>
        <th>文件路径</th>
        <th>描述</th>
        <th>操作</th>
    </tr>

    <c:forEach items="${requestScope.fileModels}" var="file" varStatus="fileStatus">
        <tr>
            <td>${fileStatus.count}</td>
            <td>${file.fileName}</td>
            <td>${file.size}</td>
            <td>${file.contentType}</td>
            <td>${file.uploadTime}</td>
            <td>${file.file_path}</td>
            <td>${file.information}</td>
            <td><a href="${pageContext.request.contextPath}/DownLoadServlet1?id=${file.id}">下载该文件</a> </td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
