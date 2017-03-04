<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main</title>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>
<body>

	<a href="javascript:void(0);" onclick="openDialog();">Login</a>
	<a href="signup-step1">Signup</a>

	<div style="color: red">${sessionScope.loginError} </div>
	
	<div id="dialog-form" title="Login">

		<form:form name="submitForm" method="POST">

			<div id="loginDiv" align="center" style="display: none;">
				<table>
					<tr>
						<td>User Name</td>
						<td><input type="text" name="userName" required /></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Submit" /></td>
					</tr>
				</table>

			</div>
		</form:form>
	</div>

</body>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script type="text/javascript">
	$(document).ready(function() {		
		$("#dialog-form").dialog({
			autoOpen : false
		});
		
		$('#loginDiv').show();
	});

	function openDialog() {
		$("#dialog-form").dialog('open');
		return false;
	}
</script>

</html>