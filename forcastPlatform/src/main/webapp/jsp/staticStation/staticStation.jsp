<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>静态GPS监测站</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="${basePath }static/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${basePath }static/ztree/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${basePath }static/ztree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${basePath }static/ztree/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="${basePath }static/ztree/js/jquery.ztree.exedit-3.5.js"></script>
		<script type="text/javascript" src="${basePath }static/js/common.js"  charset="gb2312"></script>
	<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	</style>
	<script type="text/javascript">
	var setting = {
			view: {
				addHoverDom: addHoverDom,
				removeHoverDom: removeHoverDom,
				selectedMulti: false
			},
			edit: {
				enable: true,
				editNameSelectAll: true,
				showRemoveBtn: showRemoveBtn,
				showRenameBtn: showRenameBtn
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			check: {
				enable: true
			},
			callback: {
   				onClick: onClick,
   				onCheck: onCheck,
   				beforeEditName: beforeEditName,
				beforeRemove: beforeRemove,
				beforeRename: beforeRename,
				onRemove: onRemove,
				onRename: onRename
   			}
		};
	var className = "dark";
	function addHoverDom(treeId, treeNode) {
		if(!treeNode.isParent){
			return false;
		}
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='add node' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.addNodes(treeNode, {id:-100, pId:treeNode.id, name:"new node" });
			var node = zTree.getNodeByParam("id",-100, treeNode);
			className = (className === "dark" ? "":"dark");
			zTree.selectNode(node);
			setTimeout(function() {
				zTree.editName(node);
			}, 0);
			//while(editNameFlag){}
			beforeRename(treeId, node, node.name, false);
			return false;
		});
	}
	
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_"+treeNode.tId).unbind().remove();
	}
	
	function beforeEditName(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.selectNode(treeNode);
		setTimeout(function() {
			if (confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？")) {
				setTimeout(function() {
					zTree.editName(treeNode);
				}, 0);
			}
		}, 0);
		return false;
	}
	
	function beforeRemove(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.selectNode(treeNode);
		return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
	}
	
	function onRemove(e, treeId, treeNode) {
		$.ajax({  
	        type: "POST",  
	        url: "${basePath}staticStation/remove/"+treeNode.id,
	        async: false,  
	        cache:false,  
	        success:function(data){
	            alert(data);
	        }  
 		}); 
	}
	
	function beforeRename(treeId, treeNode, newName, isCancel) {
		className = (className === "dark" ? "":"dark");
		if (newName.length == 0) {
			setTimeout(function() {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.cancelEditName();
				alert("节点名称不能为空.");
			}, 0);
			return false;
		}
		return true;
	}
	
	function onRename(e, treeId, treeNode, isCancel) {
		if(treeNode.id==-100){
			alert("${basePath}staticStation/add/"+treeNode.name+"/"+treeNode.id);
			$.ajax({  
		        type: "POST",  
		        url: "${basePath}staticStation/add/"+treeNode.name,
		        async: false,  
		        cache:false,  
		        success:function(data){
		            alert(data);
		        }  
	 		}); 
		}else{
			alert("${basePath}staticStation/update/"+treeNode.id+"/"+treeNode.name);
			$.ajax({  
		        type: "POST",  
		        url: "${basePath}staticStation/update/"+treeNode.id+"/"+treeNode.name,
		        async: false,  
		        cache:false,  
		        success:function(data){
		            alert(data);
		        }  
	 		}); 
		}
		
	}
	
	function showRemoveBtn(treeId, treeNode) {
		return !treeNode.isParent;
	}
	function showRenameBtn(treeId, treeNode) {
		return !treeNode.isParent;
	}
	
	function onCheck(e,treeId,treeNode){
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = zTree.getCheckedNodes(true);
		var nodeStr="";
		for(var i=0;i<nodes.length;i++){
			nodeStr += nodes[i].id+",";
		}
		document.getElementById("dataFrame").src="${basePath}staticStation/toData/"+nodeStr.substring(0,nodeStr.length-1);
	}
	
	function onClick(event, treeId, treeNode, clickFlag) {
		if (treeNode.isParent) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.expandNode(treeNode);
			return false;
		}else{
			//alert(treeNode.id);
		}
	}
		$(document).ready(function(){
			$.ajax({  
                type: "POST",  
                url: "${basePath}staticStationList",
                async : false,  
                cache:false,  
                dataType: "json",
                success:function(data){
                    $.fn.zTree.init($("#treeDemo"), setting, data);
            		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            		var node = zTree.getNodeByParam("id", 0, null);
	                zTree.selectNode(node);
                    onClick(event, "treeDemo", node, 1);
                }  
         	});  
		});
		
		function refreshTree(nodeId){
			$.ajax({  
	               type: "POST",  
	               url: "${basePath}staticStationList",
	               async: false,  
	               cache: false,  
	               dataType: "json",
	               success:function(data){
	                   $.fn.zTree.init($("#treeDemo"), setting, data);
	                   if(nodeId!=null){
	                   	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	                       var node = zTree.getNodeByParam("id", nodeId, null);
	                       zTree.selectNode(node);
	                       onClick(event, "treeDemo", node, 1);
	                   }
	               }
	        	});
		}
    </script>
</head>
  
  <body style="padding: 0;margin: 0;">
  
    <div id="treeDiv" style="width:20%;float:left">
  		<ul id="treeDemo" class="ztree"></ul>
  	</div>
  	<div id="tableDiv" style="width:80%;float:left;" >
  	<iframe id="dataFrame" src="" width="100%" height="100%" frameborder="0" scrolling="no"></iframe>
  	</div>
  </body>
</html>
