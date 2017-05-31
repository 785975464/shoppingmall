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
    <form id="ff" method="post"  enctype="multipart/form-data">
    	<input type="hidden" id="id" name="id"/>
    	<input type="hidden" id="date" name="date"/>
    	<input type="hidden" id="pic" name="pic"/>
    	<div>  
            <label>商品名称:</label> <input type="text" id="name" name="name" />  
        </div>  
  
        <div>  
            <label>商品价格:</label> <input type="text" id="price" name="price" />  
        </div>  
        <div>  
            <label>图片上传:</label> <input type="file" id="fileImage.upload" name="fileImage.upload" />  
        </div>  
  
        <div>  
            <label>所属类别：</label> <input type="text" id="cid" name="cid"/>  
        </div>  
          
        <div>  
            <label>加入推荐:</label> 
			            推荐:<input type="radio" name="commend" checked="checked" value="true" />
			           不推荐:<input type="radio" name="commend" value="false" />   
        </div>  
        <div>  
            <label>是否有效:</label>  
			            上架:<input type="radio" name="open" checked="checked"value="true" />  
			            下架:<input type="radio" name="open" value="false" />  
        </div>  
           
        <div>  
            <label>简单描述:</label>  
            <textarea name="remark" id="remark" cols="40" rows="4"></textarea>  
        </div>  
        <div>  
            <label>详细描述:</label>  
            <textarea name="xremark" id="xremark" cols="40" rows="8"></textarea>  
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
		url:'product_edit.action',
		data: {"id": id},
	    type: "post",
	    success: function (data) {
	    	//console.log(data);
	    	//var obj = eval('('+data+')');
	    	$("#id").val(id);
	    	$("#name").val(data.name);
	    	$("#price").val(data.price);
	    	$("#pic").val(data.pic);
	    	$("#cid").val(data.cid);
	    	$("input[name=commend][value=" + data.commend + "]").attr("checked", true);
	    	$("input[name=open][value=" + data.open + "]").attr("checked", true);
	    	$("#remark").val(data.remark);
	    	$("#xremark").val(data.xremark);
	    	$("#date").val(data.date);
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
                url: 'product_update.action', //将请求提交给categoryAction中的save方法处理  
                success: function(data){ //成功后  
                	if(data=="success"){
                		console.log("更新成功！");
                		//如果成功了，关闭当前窗口  
                    	parent.$("#win").window("close");  
                    	parent.frames.$("#myProductTable").DataTable().ajax.reload(null, false);				// 刷新表格数据，分页信息不会重置
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
