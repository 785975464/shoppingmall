<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="/public/head.jspf" %>
  </head>
  
  <body>
	<div class="container">
		<form action="${shop }/user_login.action" method="post">
			<div>
				<label for="username">帐号</label>
				<input type="text" name="username">
			</div>
			<div>
				<label for="password">密码</label>
				<input type="text" name="password">
			</div>
			<div>
				${sessionScope.error }
			</div>
			<input type="submit" value="login">
		</form>
	
	</div>
    
    
  </body>
</html>
