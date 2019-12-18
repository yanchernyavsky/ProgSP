<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Array Sort</title>
</head>
<body>
<form action="Login" method="post">
    Input array : <input type ="text" name = "massive"><br>
    <p><select name = "choice">
        <option value = "Up" selected>Ascending </option>
        <option value = "Down">Descending </option>
    </select>
    <input type ="submit" value = "Send"></p>
</form>
    <h3> Final array: ${result}</h3>
</body>
</html>
