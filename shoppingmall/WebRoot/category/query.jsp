<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%-- <base href="<%=basePath%>">
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>My JSP 'index.jsp' starting page</title> --%>
    <%@ include file="/public/head.jspf" %>
    
  </head>
  <body>
  	<button onclick="_addCategoryFun()">添加类别</button>
    <table id="myCategoryTable"></table>
    
<script type="text/javascript">
var table;	//申明全局变量
$(document).ready( function () {
	table = $('#myCategoryTable').DataTable({
		"paging":true,
		"pagingType":"full_numbers",
		"lengthMenu":[10,25,50],
		"processing": true,
		//"bStateSave": true,	//状态保存
		"searching": true, //是否开启搜索
        //"serverSide": true,//开启服务器获取数据，true代表后台处理分页，false代表前台处理分页  
        // "order": [[ 0, "desc" ]], //默认排序
		"ajax":{ // 获取数据
        	"url":"category_query.action",
        	"dataType":"json" //返回来的数据形式
        },
        "columns":[ //定义列数据来源
			{'title':"用户名",'data':"id",'class':"align-center"},
			{'title':"勾选",'data':null,'class':"align-center"},
			{'title':"类型",'data':"type",'class':"align-center"},
			{'title':"是否热销",'data':"hot",'class':"align-center"},
			{'title':"操作",'data':null,'class':"align-center"} // 自定义列
			
			// {'title':"AddressID",'data':"a_id",'class':"myhiddenelement"},
		],

  		"columnDefs": [
  		// 	{
	   //      	"targets":[3,4,5,6,7],
	   //      	"visible":false //隐藏列
	   //      },
  			{
	        	"targets": [1],
	        	"render": function(data, type, row ,full) {
// 	        		return  row.community+"("+row.province+","+row.city+","+row.county+","+row.street+")";
	        		return "<input type='checkbox' id='"+row.id+"' name='downloadbox' value='"+row.id+"' class='mycheckbox'/><label for='"+row.id+"' ></label>";
	        	}
	        },
	        {
	        	"targets": [4],
	        	"render": function(data, type, row ,full) {
	        		return  "<a class='btn-sm' href='#' onclick='_editCategoryFun("+row.id+")'>修改</a>"+
	        				//"<a class='btn-sm' href='#' onclick='show()'>添加</a>"+
	        				"<a class='btn-sm' href='#' onclick='_deleteCategoryFun("+row.id+")'>删除</a>";
	        				//"<a class='btn-sm' href='stuVisionInfo.action?id="+row.id+"'>档案</a>";
	        	}
	        }
	    ],

  		"language":{ // 定义语言
        	"sProcessing":"加载中...",
        	"sLengthMenu":"每页显示 _MENU_ 条记录",
        	"sZeroRecords":"没有匹配的结果",
        	"sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix": "",
            "sSearch": "搜索:",
            "sUrl": "",
            "sEmptyTable": "表中数据为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上一页",
                "sNext": "下一页",
                "sLast": "末页"
            },
            "oAria": {
                "sSortAscending": ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        }
	});
});

//删除操作
function _deleteCategoryFun(id) {
	//var tr = obj.parentNode.parentNode;	// 按钮的父节点的父节点是tr
	//var id = tr.cells[0].innerText;
	//var type = tr.cells[2].innerText;
	//var hot = tr.cells[3].innerText;
	
 	//console.log("要删除的用户id为："+id+"type为："+type+"hot为："+hot);
	if(!confirm('确定要删除吗？')){
		return;
	}
	$.ajax({
	    url: "category_delete.action",
	    data: {"id": id},
	    type: "post",
	    success: function (data) {
 		    //console.log(data);
	        if (data=="success") {
	            start = $("#myCategoryTable").dataTable().fnSettings()._iDisplayStart; 
				total = $("#myCategoryTable").dataTable().fnSettings().fnRecordsDisplay(); 

				table.ajax.reload(null, false);				// 刷新表格数据，分页信息不会重置
				
				if((total-start)==1){ 
					if (start > 0) { 
						$("#myCategoryTable").dataTable().fnPageChange( 'previous', true ); 
						// table.fnPageChange( 'previous', true );
					}
				}
	        } 
	        //Materialize.toast(obj.msg, 2000);
	    },
	    error: function (error) {
	        console.log(error);
	    }
	});
}

function _addCategoryFun(){
	$('#win').window({
		title:"添加类别", 
	    width:600,
	    height:400,
	    //modal:true
	    content:'<iframe src="send_category_save.action" frameborder="0" width="100%" height="100%"/>'
	}); 
}

function _editCategoryFun(id){
	$('#win').window({
		title:"编辑类别", 
	    width:600,
	    height:400,
	    //modal:true
	    content:'<iframe src="${shop}/category/edit.jsp?id='+id+'" frameborder="0" width="100%" height="100%"/>'
	}); 
}
/* function add(){
    iconCls: 'icon-add',  
    text:'添加类别',  
    handler: function(){  
        parent.$("#win").window({ //因为<div>放在了aindex.jsp中，所以这里创建窗口要先调用parent  
            title:"添加类别",  
            width:350,  
            height:150,  
            content:'<iframe src="send_category_save.action" frameborder="0" width="100%" height="100%"/>'  
        });  
    }  
} */
</script>
  </body>
</html>
