<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="/public/head.jspf" %>
    <% response.setHeader("cache-control", "no-store"); %>
  </head>
  
  <body>
  	<c:if test="${empty sessionScope.order.id }">
  		<!-- 如果购物车为空，则跳转到首页 -->
  		<c:redirect url="/index.jsp"></c:redirect>
  	</c:if>
	<div class="container">
		<div class="confirm_order_info">
			<p class="order_info_title">确认订单信息</p>
			<table class="cart_table" cellpadding="0" cellspacing="0" width="100%" >
	  			<thead>
		  			<tr>
		  				<th class="align-center" width="10%">商品编号</th>
		  				<th class="align-center" width="35%" colspan="2">商品名称</th>
		  				<th class="align-center" width="10%">销售价格</th>
		  				<th class="align-center" width="20%">数量</th>
		  				<th class="align-center" width="15%">小计</th>
		  				<!-- <th class="align-center" width="10%">删除</th> -->
		  			</tr>
	  			</thead>
	  			<tbody>
		  			<c:forEach items="${sessionScope.sorders }" var="sorder" varStatus="num">
		  				<tr>
			  				<td class="align-center">${num.count }</td>
			  				<td class="align-center"><img src="${shop }/images/cart1.jpg" width="100px"/></td>
			  				<td class="align-center">${sorder.productname }</td>
			  				<td class="align-center">${sorder.price }</td>
			  				<td class="align-center">
			  					<div>
			  						<input style="width:30px;text-align:center;" value="${sorder.number }">
			  					</div>
			  				</td>
			  				<td class="align-center">￥${sorder.price*sorder.number }</td>
			  				<!-- <td class="align-center"><a href="#" class="remove">删除</a></td> -->
		  				</tr>
		  			</c:forEach>
		  		</tbody>
	  		</table>
	  		<div class="confirm_order_info_total">
	  			<p>总计：<span>￥${sessionScope.order.total }</span></p>
	  		</div>
		</div>
		<form action="${shop }/order_save.action" method="post">
			<div class="user_order_info">
				<p class="order_info_title">填写订购人信息</p>
				<div>
					<label for="receiver">配送姓名：</label>
					<input type="text" name="receiver">
				</div>
				<div>
					<label for="phone">联系方式：</label>
					<input type="text" name="phone">
				</div>
				<div>
					<label for="post">区域邮编：</label>
					<input type="text" name="post">
				</div>
				<div>
					<label for="address">配送地址：</label>
					<input type="text" name="address">
				</div>
			</div>
			<div class="buyer_order_info">
				<p class="order_info_title">买家留言</p>
				<textarea rows="5" cols="" style="width:100%;" name="remark" >输入留言信息</textarea>
			</div>
			<div class="submit_order_info">
				<a>
					<button class="submit_order_info_btn" >提交订单</button>
				</a>
			</div>
		</form>
	</div>
    
    
  </body>
</html>
