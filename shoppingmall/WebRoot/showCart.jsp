<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="/public/head.jspf" %>
  </head>
  <body>
  <div class="container">
  	<div class="cart_title">
  		<img alt="" src="${shop }/images/cart10.png" width="100px"/>
  		<div>我的购物车</div>
  	</div>
  	<div id="shopping_cart" style="position:relative;">
  		<div>
	  		<table class="cart_table" cellpadding="0" cellspacing="0" width="100%" >
	  			<thead>
		  			<tr>
		  				<th class="align-center" width="10%">商品编号</th>
		  				<th class="align-center" width="35%" colspan="2">商品名称</th>
		  				<th class="align-center" width="10%">销售价格</th>
		  				<th class="align-center" width="20%">数量</th>
		  				<th class="align-center" width="15%">小计</th>
		  				<th class="align-center" width="10%">删除</th>
		  			</tr>
	  			</thead>
	  			<tbody>
		  			<c:forEach items="${sessionScope.sorders }" var="sorder" varStatus="num">
		  				<tr lang="${sorder.id }">
			  				<td class="align-center">${num.count }</td>
			  				<td class="align-center"><img src="${shop }/images/cart1.jpg" width="100px"/></td>
			  				<td class="align-center">${sorder.productname }</td>
			  				<td class="align-center">${sorder.price }</td>
			  				<td class="align-center sorder-number">
			  					<div>
			  						<span onclick="minus_num(this);">-</span>
			  						<input style="width:30px;text-align:center;" value="${sorder.number }">
			  						<span onclick="add_num(this);">+</span>
			  					</div>
			  				</td>
			  				<td class="align-center">${sorder.price*sorder.number }</td>
			  				<td class="align-center"><a href="#" class="remove">删除</a></td>
		  				</tr>
		  			</c:forEach>
		  		</tbody>
	  		</table>
  		</div>
  		<div style="float:right;">
	  		<table class="showtotal" cellpadding="0" cellspacing="0">
	  			<tr>
		  			<td class="align-center">小计</td>
		  			<td class="align-center" id="subtotal">${sessionScope.order.total }</td>
		  		</tr>
		  		<tr>
		  			<td class="align-center">运费</td>
		  			<td class="align-center">￥0.00</td>
		  		</tr>
		  		<tr>
		  			<td class="align-center totalnum">总计</td>
		  			<td class="align-center totalnum" id="total">${sessionScope.order.total }</td>
		  		</tr>
	  		</table>
  		</div>
  		<div style="clear:both;"></div>
  	</div>
  	<div class="settlement">
  		<div class="settlementbtn">
	  		<a href="${shop }/user/confirm.jsp">
				<button class="settle_btn">继续购物</button>
			</a>
			<a href="${shop }/user/confirm.jsp">
				<button class="settle_btn">清空购物车</button>
			</a>
			<a href="${shop }/user/confirm.jsp">
				<button class="settle_btn settle" >结算</button>
			</a>
		</div>
 	</div>
 	<div class="cart_foot"></div>
  </div>
  
  <script type="text/javascript">
  	function minus_num(obj){
  		var input = $(obj).next("input");
  		if(parseInt(input.val())<=0){
  			return;
  		}
  		input.val( parseInt(input.val())-1 );
  		var td = $(obj).parent().parent();
  		var pid = td.parents().attr("lang");
  		var price = td.prev("td");
  		var total = td.next("td");
  		console.log(pid);
  		$.ajax({
  			url : "sorder_updateSorder.action",
  			data : {"id":pid, "number": -1},
  			type: "post",
		    success: function (data) {
		    	//console.log("minus "+data);
		    	total.text( (parseFloat(total.text())-parseFloat(price.text())).toFixed(2) );
		  		$("#subtotal").text( (parseFloat( $("#total").text() ) - parseFloat( price.text() )).toFixed(2) );
		  		$("#total").text( (parseFloat( $("#total").text() ) - parseFloat( price.text() )).toFixed(2) );
		    }
  		});
  		
  	}
  	function add_num(obj){
  		var input = $(obj).prev("input");
  		if(parseInt(input.val())>=5){
  			return;
  		}
  		input.val( parseInt(input.val())+1 );
  		var td = $(obj).parent().parent();
  		var pid = td.parents().attr("lang");
  		var price = td.prev("td");
  		var total = td.next("td");
  		
  		console.log(pid);
  		$.ajax({
  			url : "sorder_updateSorder.action",
  			data : {"id":pid, "number": 1},
  			type: "post",
		    success: function (data) {
		    	//console.log("add "+data);
		    	total.text( (parseFloat(total.text())+parseFloat(price.text())).toFixed(2));
		  		$("#subtotal").text( (parseFloat( $("#total").text() ) + parseFloat( price.text() )).toFixed(2) );
		  		$("#total").text( (parseFloat( $("#total").text() ) + parseFloat( price.text() )).toFixed(2) );
		    }
  		});
  		
  	}
  </script>
  </body>
</html>