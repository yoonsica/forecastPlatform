<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="<%=basePath%>">
	<title>欢迎使用</title>
	<link rel="stylesheet" type="text/css" href="static/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="static/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="static/nav/style.css">
	<script type="text/javascript" src="static/easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="static/nav/IE-fix.js"></script>
	<script type="text/javascript" src="${basePath }static/js/common.js"  charset="gb2312"></script>
	<script type="text/javascript">
	function addTab(title,href,icon){
		if(getSelectedHref()=="${basePath}jsp/welcome.jsp"){
			var tab = $('#tt').tabs('getSelected');
			var index = $('#tt').tabs('getTabIndex',tab);
			$('#tt').tabs('close',index);
		}
		var tt = $('#tt');  
	    if (tt.tabs('exists', title)){//如果tab已经存在,则选中并刷新该tab          
	        tt.tabs('select', title);  
	        refreshTab({tabTitle:title,url:href});  
	    } else { 
			var content = '<iframe scrolling="no" frameborder="0"  src="'+href+'" style="width:1200px;height:530px;"></iframe>'; 
			tt.tabs('add',{
				title:title ,
				content:content,
				iconCls:'icon-'+icon,
				closable:true
			});
		}
	}
	function getSelectedTitle(){
		var tab = $('#tt').tabs('getSelected');
		if(null!=tab){
			return tab.panel('options').title;
		}else{
			return null;
		}
	}
	
	function getSelectedHref(){
		var tab = $('#tt').tabs('getSelected');
		if(null!=tab){
			return tab.find('iframe')[0].src;
		}else{
			return null;
		}
	}
	
	function update(title,href){
		var tab = $('#tt').tabs('getSelected');
		var content = '<iframe scrolling="no" frameborder="0"  src="'+href+'" style="width:100%;height:100%;"></iframe>'; 
		$('#tt').tabs('update', {
			tab: tab,
			options:{
				title:title,
				content:content,
				closable:true
			}
		});
	}

	/**     
	 * 刷新tab 
	 * @cfg  
	 *example: {tabTitle:'tabTitle',url:'refreshUrl'} 
	 *如果tabTitle为空，则默认刷新当前选中的tab 
	 *如果url为空，则默认以原来的url进行reload 
	 */  
	function refreshTab(cfg){  
	    var refresh_tab = cfg.tabTitle?$('#tt').tabs('getTab',cfg.tabTitle):$('#tt').tabs('getSelected');  
	    if(refresh_tab && refresh_tab.find('iframe').length > 0){  
	    var _refresh_ifram = refresh_tab.find('iframe')[0];  
	    var refresh_url = cfg.url?cfg.url:_refresh_ifram.src;  
	    _refresh_ifram.src = refresh_url;
	    //_refresh_ifram.contentWindow.location.href=refresh_url;  
	    }  
	}  
	 
	function refreshWestDiv(url){
		//$("#westDiv").load(url);
		if(url==null){
			$("#westFrame").attr("src",$("#westFrame").attr("src"));
		}else{
			$("#westFrame").attr("src",url);
		}
	}
	
	$(function() {
		var myDate = new Date();
		$("#southDiv").html("${user.code }&nbsp;"+myDate.toLocaleDateString());
		$("#nav_slim a").click(function(){
			addTab("欢迎使用","jsp/welcome.jsp","129");
			//addTab($(this).text(),$(this).attr("href"));//调用south.jsp页面的addTab()方法，直接在这里添加tab会报错“option对象为空”
		//refreshWestDiv("${basePath}moduleChild/"+$(this).attr("id"));
			refreshWestDiv("${basePath}moduleIdPass/"+$(this).attr("id"));
			return false;
		});
		
		$("#logout").click(function(){
			top.window.location.href = "${basePath}logout";
		});
		
		$("#changePassword").click(function(){
			addTab("修改密码","${basePath}jsp/changePassword.jsp");
		});
	})
	</script>

</head>
<body class="easyui-layout">
	<div region="north"  style="height:91px;overflow: hidden;">
		<div style="height:59px;width:100%;background:url('static/images/banner3.jpg') no-repeat;"><a id="logout" style="position:absolute ;left:1245px ;top:40px; color: white;font-weight:800 ;font-size:12px ; text-decoration: none;" href="javascript:void(0);">注销</a><a id="changePassword" style="position:absolute ;left:1290px ;top:40px; color: white;font-weight:800 ;font-size:12px ; text-decoration: none;" href="javascript:void(0);">修改密码</a></div>
		<div id="container">
			<div id="nav_slim">
				<ul>
					<li><a href="" id="1">监测数据</a></li>
					<li><a href="" id="2">预测成果</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="westDiv" region="west" split="true" title="功能列表" style="width:160px;padding:0;margin: 0;height: 530px;">
		<%--<jsp:include page="westDiv.jsp"></jsp:include>
		--%>
		<iframe id="westFrame" src="" frameborder="0" width="100%" height="530px" style="padding: 0;margin: 0;"></iframe>
	</div>
	
	<div id="southDiv" region="south" border="false" style="height:20px;background: url('static/easyui/themes/default/images/panel_title.png') repeat-x;padding:1px 25px;text-align: right;font-weight:bold;color: #000000;">
	</div>
	
	<div region="center">
		<div id="tt" class="easyui-tabs" fit="true" border="false" style="width:100%;height:566px;">
			
		</div>
	</div>
</body>
</html>