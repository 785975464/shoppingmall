<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="/public/head.jspf" %>
  </head>
  
  <body>
  <div class="login_main">
	<div class="login_container">
		<div class="login_title">
			<img alt="" src="${shop }/images/logo.png" width="100%">
			<p class="login_subtitle center">东京商城 想你所想</p>
		</div>
		<div class="login_body">
			<form action="${shop }/user_login.action" method="post">
				<div class="login_input_groups">
					<div>
						<!-- <label for="username">帐号</label> -->
						<input type="text" class="login_input" name="username" placeholder="帐号">
					</div>
					<div>
						<!-- <label for="password">密码</label> -->
						<input type="text" class="login_input" name="password" placeholder="密码">
					</div>
					<%-- <div>
						${sessionScope.error }
					</div> --%>
				</div>
				<div class="login_submit_btn">
					<input type="submit" class="login_btn" value="登录">
				</div>
			</form>
			<div >
				<button class="register_btn">
					<span class="register_info">注册</span>
				</button>
			</div>
		</div>
		<div>
		</div>
	</div>
    
  </div>
  </body>
</html>
