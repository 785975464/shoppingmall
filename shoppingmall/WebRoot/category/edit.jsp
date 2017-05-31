<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="/public/head.jspf" %>
    <style type="text/css">  
        form div {  
            margin:5px;  
        }  
    </style>
  </head>
  <body>
    <form id="ff" method="post">
    	<input type="hidden" id="id" name="id" value=""/>
        <div>     
            <label for="name">商品名称:</label> <input type="text" id="type" name="type" value=""/>     
        </div>     
        <div>     
            <label for="hot">热点:</label>     
		               是<input type="radio" name="hot" value="true" />   
		               否 <input type="radio" name="hot" value="false" />  
        </div>    
        <div>  
            <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">修改</a>    
        </div>    
    </form>
    
    
<script type="text/javascript">
var url=window.location.href; //获取当前页面的url
var id=url.split("=")[1];//对获得的参数字符串按照“=”进行分割
$(function(){ 
	$.ajax({
		url:'category_edit.action',
		data: {"id": id},
	    type: "post",
	    success: function (data) {
	    	//console.log(data);
	    	//var obj = eval('('+data+')');
	    	$("#id").val(id);
	    	$("#type").val(data.type);
	    	$("input[name=hot][value=" + data.hot + "]").attr("checked", true);
	    },
	    error: function (error) {
	        console.log(error);
	    }
	});
});
$(function(){  

    $("#btn").click(function(){  
        //如果验证成功，则提交数据  
        	//console.log(parent.$("iframe[title='类别管理']"));
        	//console.log(window.frames.$("#mytable").reload);
        	//console.log(parent.$("iframe[title='类别管理']").mytable);
            //调用submit方法提交数据  
            $("#ff").form('submit', {  
                url: '${shop}/category_update.action', //将请求提交给categoryAction中的save方法处理  
                success: function(data){ //成功后  
                	if(data=="success"){
                		console.log("更新成功！");
                		//如果成功了，关闭当前窗口  
                    	parent.$("#win").window("close");  
                    	parent.frames.$("#myCategoryTable").DataTable().ajax.reload(null, false);				// 刷新表格数据，分页信息不会重置
                    	//$("#mytable").dataTable().fnReloadAjax();
                	}
                    else{
                    	console.log("更新失败！");
                    	parent.$("#win").window("close"); 
                    }
                }  
            });  
    });
});  

</script>
  </body>
</html>
