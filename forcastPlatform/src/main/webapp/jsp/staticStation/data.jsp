<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>数据表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${basePath }static/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${basePath }static/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${basePath }static/css/common.css">
	<script type="text/javascript" src="${basePath }static/easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${basePath }static/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${basePath }static/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${basePath }static/js/common.js"  charset="gb2312"></script>
	</style>
	<script>
		$(function(){
			$('#test').datagrid({
				title:'数据',
				iconCls:'icon-save',
				width:859,
				height:530,
				fitColumns: true,
				url:"staticStation/data/${stationId}",
				columns:[[
					{field:'datadate',title:'日期',width:65},
					{field:'day',title:'累计天数',width:35},
					{field:'x',title:'水平位移',width:250},
					{field:'y',title:'垂直位移',width:80},
					{field:'xy',title:'合位移',width:30}
				]],
				rownumbers:true,
				pagination:true
			});
			var p = $('#test').datagrid('getPager');
			$(p).pagination({
				pageSize:10,
				onBeforeRefresh:function(){
					alert('before refresh');
				}
			});
		});
	</script>
  </head>
  
  <body style="padding: 5px;">
	<table id="test"></table>
	
  </body>
</html>
