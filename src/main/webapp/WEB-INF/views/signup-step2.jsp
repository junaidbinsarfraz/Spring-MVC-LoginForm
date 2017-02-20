<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Singup Step2</title>
</head>
<body>
	
	<h2>Security Questions</h2>
	
	<form:form name="submitForm" method="POST">

		<div align="center">
			<table>
				<tr>
					<td>What is your pet name?</td>
					<td><input type="text" name="petName" /></td>
				</tr>
				<tr>
					<td>What is your age?</td>
					<td><input name="age" /></td>
				</tr>
				<tr>
					<td>What is your favorite color?</td>
					<td><input name="color" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Submit" /></td>
				</tr>
			</table>
			<div style="color: red">${error}</div>

		</div>
	</form:form>
</body>
</html>