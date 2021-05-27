<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>ユーザー情報入力</h1>


		<form action="<%= request.getContextPath() %>/main" method="post">
			<input type="hidden" name="id">
			<br>
			名前:<input type="text" name="name">
			<br>
			性別:<input type="radio" name="sex" value="男">男
			<input type="radio" name="sex" value="女">女
			<br>
			生年月日:<input type="text" name="born"> 
			<input type="submit" value="送信">
		</form>

	</body>
</html>