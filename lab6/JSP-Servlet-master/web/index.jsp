<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 28.09.2019
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EnterMassPage</title>
</head>
<body>
<form action="Login" method="post">
    Enter Massive : <input type ="text" name = "massive"><br>
    <p><select name = "choice">
        <option value = "Up" selected>Ascending </option>
        <option value = "Down">Descending </option>
    </select>
    <input type ="submit" value = "Send"></p>
</form>
    <h3> Sorted massive: ${result}</h3>
</body>
</html>
