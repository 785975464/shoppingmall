<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="/public/head.jspf" %>
  </head>
  
  <body>
	  <div class="container">
	  	<div>
	  		<p class="order_info_title">您的订单已经生成</p>
	  		<div class="order_info_check">
	  			<div><span>订单号</span>：<span>${sessionScope.oldOrder.id }</span></div>
	  			<div><span>收货人</span>：<span>${sessionScope.oldOrder.receiver }</span></div>
	  			<div><span>送货地址</span>：<span>${sessionScope.oldOrder.address }</span></div>
	  			<div><span>邮政编码</span>：<span>${sessionScope.oldOrder.post }</span></div>
	  			<div><span>支付金额</span>：<span>${sessionScope.oldOrder.total }</span></div>
	  		</div>
	  	</div>
	  	<div class="select_bank">
	  		<p class="order_info_title">支付方式</p>
	  		<form action="${shop }/pay_goBank.action" method="post">
		  		<h2>请选择支付银行</h2>
			    <div class="bankinfo">
			    	<ul>
			    		<c:forEach items="${applicationScope.bankImageList }" var="bankimg">
			    			<li class="banklogo">
			    				<input type="radio" name="pd_FrpId" value="${fn:substring(bankImage,0,fn:indexOf(bankImage,'.')) }"/>
			    				<img src="${shop }/files/bankImages/${bankimg}" width="100px;"/>
			    			</li>
			    		</c:forEach>
			    	</ul>
			    </div>
			    <div class="submit_order_info">
					<input type="submit" class="submit_order_info_btn" value="支付" >
				</div>
			</form>
	  	</div>
		
	  </div>
  </body>
</html>
