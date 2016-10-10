<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
	<head>
		<title>Добро пожаловать</title>
	</head>
	<body>
		<h2>${user.firstName} ${user.lastName}</h2>
		<h3>Вы вошли в систему</h3>
		<h4>Выберите операцию:</h4> 
		<a href="controller?command=balance">Зайти в личный кабинет</a> <br/>
		<a href="controller?command=viewcars">Список доступных автомобилей</a> <br/>
		<a href="controller?command=block">Заблокировать личный кабинет</a> <br/>
		<a href="controller?command=logout">Выйти из системы</a> <br/>
	</body>
</html>
