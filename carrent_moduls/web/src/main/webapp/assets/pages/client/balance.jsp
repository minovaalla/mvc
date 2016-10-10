<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Ваши заказы</title>
	</head>
	<body>
		<h3>${userName}</h3>

		<table border="1">
			<tr bgcolor="#CCCCCC">
				<td align="center"><strong>Номер автомобиля</strong></td>
				<td align="center"><strong>Марка и модель</strong></td>
				<td align="center"><strong>Год выпуска</strong></td>
				<td align="center"><strong>Цена</strong></td>
			</tr>
			<c:forEach var="car" items="${carList}">
				<tr>
					<td><c:out value="${ car.number }" /></td>
					<td><c:out value="${ car.model }" /></td>
					<td><c:out value="${ car.year }" /></td>
					<td><c:out value="${ car.price }" /></td>
				</tr>
			</c:forEach>
		</table>

		<a href="controller?command=backclient">Вернуться обратно</a>
		<a href="controller?command=logout">Выйти из системы</a>
	</body>
</html>
