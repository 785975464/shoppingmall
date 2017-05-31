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
    <form title="添加商品" id="ff" method="post" enctype="multipart/form-data">  
        <div>  
            <label>商品名称:</label> <input type="text" name="name" />  
        </div>  
  
        <div>  
            <label>商品价格:</label> <input type="text" name="price" />  
        </div>  
        <div>  
            <label>图片上传:</label> <input type="file" name="fileImage.upload" />  
        </div>  
  
        <div>  
            <label>所属类别：</label>   
            <input id="cid" name="cid"/>  
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
            <textarea name="remark" cols="40" rows="4"></textarea>  
        </div>  
        <div>  
            <label>详细描述:</label>  
            <textarea name="xremark" cols="40" rows="8"></textarea>  
        </div>  
        <div>  
            <a id="submit" href="#" class="easyui-linkbutton">添 加</a>   
            <a id="reset" href="#" class="easyui-linkbutton">重 置</a>  
        </div>  
      </form>
    
    
<script type="text/javascript">
$(function(){  
    /* $("input[name=type]").validatebox({ //这里是“类别名称”的验证功能，如果用户没填好就提交的话，会有提示  
        required:true,  
        missingMessage:'请输入类别名称' //提示的内容  
    });  */      

    //对管理员的下拉列表框进行远程加载  
    /* $("#cc").combobox({     
        //将请求发送给accountAction中的query方法处理，这里需要将处理好的数据返回到这边来显示了 ，所以后台需要将数据打包成json格式发过来  
        url:'account_query.action',    
        valueField:'id',      
        textField:'login', //我们下拉列表中显示的是管理员的登录名  
        panelHeight:'auto', //自适应高度  
        panelWidth:120,//下拉列表是两个组件组成的  
        width:120, //要同时设置两个宽度才行  
        editable:false //下拉框不允许编辑  
     });   */

    //窗体弹出默认是禁用验证，因为刚弹出的窗口，用户还没填就显示的话，太丑  
    $("#ff").form("disableValidation");  
    //注册button的事件。即当用户点击“添加”的时候做的事  
    $("#submit").click(function(){  
        //开启验证  
        $("#ff").form("enableValidation");  
        //如果验证成功，则提交数据  
        if($("#ff").form("validate")) {  
        	//console.log(parent.$("iframe[title='类别管理']"));
        	//console.log(window.frames.$("#mytable").reload);
        	//console.log(parent.$("iframe[title='类别管理']").mytable);
            //调用submit方法提交数据  
            $("#ff").form('submit', {  
                url: 'product_save.action', //将请求提交给categoryAction中的save方法处理  
                success: function(data){ //成功后  
                	if(data=="success"){
                		console.log("添加成功！");
                		//如果成功了，关闭当前窗口  
                    	parent.$("#win").window("close");  
                    	parent.frames.$("#myProductTable").DataTable().ajax.reload(null, false);
                    	//$("#mytable").dataTable().fnReloadAjax();
                	}
                    else{
                    	console.log("添加失败！");
                    	parent.$("#win").window("close"); 
                    }
                    //刷新页面，刚刚添加的就显示出来了。  
                    //获取aindex-->iframe-->datagrid  
                    //parent.$("iframe[title='类别管理']").get(0).contentWindow.$("#dg").datagrid("reload");  
                }  
            });  
        }  
    });  
    
    $("#reset").click(function(){  
        $("#ff").form("disableValidation");//重置不需要表单验证  
        //重置当前表单数据  
        $("#ff").form("reset");  
    });
});  

</script>
  </body>
</html>
