<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Разблокировка счета</title>
	</head>
	<body>
		<form name="unblockForm" method="POST" action="controller">
			<input type="hidden" name="command" value="unblock" />
			Выберите номер кабинета клиента для разблокировки
			<table border="1">
				<tr bgcolor="#CCCCCC">
					<td align="center"><strong>Номер личного кабинета</strong></td>
				</tr>
				<c:forEach var="account" items="${accountsList}">
					<tr>
						<td><c:out value="${ account.id }" /></td>
						<td><input type="radio" name="toUnblock" value="${ account.id }"/></td>
					</tr>
				</c:forEach>
			</table>
			${errorEmptyChoice}
			${errorEmptyList} <br/>
			<input type="submit" value="Разблокировать"/>  <br/>
		</form>
		<a href="controller?command=backadmin">Вернуться обратно</a>
		<a href="controller?command=logout">Выйти из системы</a>
	</body>
</html>

