<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="/public/head.jspf" %>
  </head>
  
  <body>
    
    <!-- 产品列表 -->
    <c:forEach items="${applicationScope.cList }" var="clist" varStatus="st">  
        <div class="products_list">
        <!-- 显示类别名称 -->
        	<h2 class="sub_title">${clist.type }</h2>
        	<ul class="products_column">
        	<!-- 显示具体商品 -->
        		<c:forEach items="${applicationScope.bigList[st.count-1] }" var="product">
        			<li>
        				<a href="#" class=""><img src="${shop }/image/${product.pic }" width="200px"/></a>
        				<div class="product_info">
        					<h3>名称：${product.name }</h3>
        					<small>描述：${product.remark }</small>
        				</div>
        				<div class="price_info">
        					<span class="product_price">价格：￥${product.price }</span>
        					<%-- <button class="price_add" title="" type="button">
        						<span class="">￥${product.price }</span>
        					</button> --%>
        					
        					<%-- <button class="price_add" title="" type="button">
        						<!-- <span class="pr_price">添加购物车</span> -->
        						<a class="pr_price" href="${shop }/sorder_addSorder.action?id=${product.id}">添加购物车</a>
        					</button> --%>
        					<a href="${shop }/sorder_addSorder.action?id=${product.id}">
        						<button class="add_cart">添加购物车</button>
        					</a>
        				</div>
        			</li>
        		</c:forEach>
        	</ul>
        </div>
    </c:forEach>
    
    
  </body>
</html>
