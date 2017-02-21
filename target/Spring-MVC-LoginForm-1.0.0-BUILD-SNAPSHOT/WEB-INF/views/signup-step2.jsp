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
					<td>
						<select name="question1">
							<option value="What is your first pet name?">What is your first pet name?</option>
							<option value="What is last name of teacher who failed you?">What is last name of teacher who failed you?</option>
							<option value="In what city does your nearest sibling live?">In what city does your nearest sibling live?</option>
						</select>
					</td>
					<td><input type="text" name="answer1" /></td>
				</tr>
				<tr>
					<td>
						<select name="question2">
							<option value="What is your age?">What is your age?</option>
							<option value="What is name of your first elementary school?">What is name of your first elementary school?</option>
							<option value="What is your favorite team?">What is your favorite team?</option>
						</select>
					</td>
					<td><input name="answer2" /></td>
				</tr>
				<tr>
					<td>
						<select name="question3">
							<option value="What is your favorite color?">What is your favorite color?</option>
							<option value="What was your favorite sport in high school?">What was your favorite sport in high school?</option>
							<option value="In what town was your first job?">In what town was your first job?</option>
						</select>
					</td>
					<td><input name="answer3" /></td>
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