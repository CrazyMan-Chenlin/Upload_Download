<%--
  Created by IntelliJ IDEA.
  User: chenlin
  Date: 2018.10.14
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upLoad</title>
</head>
<body>
<form enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/MoreUpLoadServlet" onsubmit="return checkFile()">
        <table border="1px" align="center">
            <thead>
            <tr>
               <th colspan="2" align="center">文件上传</th>
            </tr>
            </thead>
            <tbody>
            <tr>
             <td> <input type="file" name="attachment"/></td>
                <td>  <input type="button" value="删除" onclick="deleteItem(this)"/></td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="2"><input type="button" value="添加" onclick="addItem()" /></td>
            </tr>
            <tr>
              <td colspan="2"><input type="submit" value="上传"/>
                  <sapn style="color: red" id="message">${requestScope.Error}</sapn></td>
            </tr>
            </tfoot>
        </table>
</form>
</body>
<script type="text/javascript">
    var count=1;
    function addItem() {
        var tr=document.createElement("tr");
        var td=document.createElement("td");
        var td2=document.createElement("td");
        var input=document.createElement("input");
        input.setAttribute("type","file");
        input.setAttribute("name","attachment");
        var input2 = document.createElement("input");
        input2.setAttribute("type","button");
        input2.setAttribute("value","删除");
        input2.setAttribute("onclick","deleteItem(this)");
        var tbody = document.getElementsByTagName("tbody")[0];
        td.appendChild(input);
        td2.appendChild(input2);
        tr.appendChild(td);
        tr.appendChild(td2);
        tbody.appendChild(tr);
        count++;
    }
    function deleteItem(input) {
        if(count>1){
        var parentNode = input.parentNode.parentNode;
        var tbody = document.getElementsByTagName("tbody")[0];
        tbody.removeChild(parentNode);
        }
        count--;
    }
    function checkFile(){
         var attachmentList = document.getElementsByName("attachment");
         for(var i=0;i<attachmentList.length;i++){
            if(attachmentList[i].value==null||attachmentList[i].value==""){
              document.getElementById("message").innerText="第"+(i+1)+"个文件未选！"
                 return false;
             }
         }
         return true;
    }
</script>
</html>
