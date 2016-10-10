<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="/assets/pages/error/error.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html>
<head>
    <title>Добавить новый автомобиль?</title>
</head>
<body>
<form name="addСarForm" method="POST" action="controller">
    <input type="hidden" name="command" value="addСar" />
    Введите данные автомобиля:<br/>
    <table>
        <tr>
            <td>Номер:</td>
            <td><input type="text" name="number" value="" size="20"/></td>
        </tr>
        <tr>
            <td>Модель:</td>
            <td><input type="text" name="model" value="" size="20" /></td>
        </tr>
        <tr>
            <td>Год выпуска:</td>
            <td><input type="text" name="year" value="" size="20"/></td>
        </tr>
        <tr>
            <td>Цена:</td>
            <td><input type="text" name="price" value="" size="20" /></td>
        </tr>
        </table>
    ${operationMessage}
    ${errorUserExists} <br />
    <input type="submit" value="Добавить в базу" />
    <a href="controller?command=backadmin">Вернуться обратно</a>
    <a href="controller?command=logout">Вернуться обратно</a>
</form>


</body>
</html>