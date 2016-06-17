function reSize(){
	var doc= window.frames['frmRight'].document;
    var body=$(doc.body);
    var pouter=body.find("#pouter");
    if(pouter.length==0){
		return;
    }
    pw=$("#frmRight").parent().width();
    if(pw>4000){
    	var bw=window.innerWidth||document.documentElement.clientWidth||document.body.clientWidth;
    	pw=bw-236;
    }
    cw=pouter.width()+1;
    if(cw>4000){
    	width=pw>1000?pw:1000;
    	body.width(width);
    	pouter.width(width);
    	cw=pouter.width();
    }
    ph=$("#frmRight").parent().height()-5;
    ch=pouter.height()+1;
    $("#frmRight").width(pw>cw?pw:cw).height(ph>ch?ph:ch);
    pouter.css("overflow-x","hidden");
    body.css("overflow-x","hidden");
}
var defSize={};

//手风琴与菜单树结合
$(function () {
	$("#west").panel({"onCollapse":function(){
		defSize.width=$("#frmRight").width();
		defSize.height=$("#frmRight").height();
		reSize();
		},"onExpand":function(){
            $("#frmRight").width(defSize.width).height(defSize.height);
		}
	});
	$("#frmRight").on("load",function(){
		if(typeof $(this).attr("src")!="undefined"){
			window.location.href=window.location.href.substring(0,window.location.href.indexOf("#")>0
					?window.location.href.indexOf("#"):window.location.href.length)+"#"+ Base64.encodeURI($(this).attr("src"));
		}
		reSize();
	});
	jQuery("#RightAccordion").accordion({ //初始化accordion
        fillSpace: true,
        fit: true,
        border: false,
        animate: true
    });
    $.post("../tree/getSubList/0", //获取第一层目录
           function (data) {
               $.each(data, function (i, e) {//循环创建手风琴的项
                   var id = e.id;
               		var searchCmp=id==1?"<div><div class='easyui-panel' style='position:fixed;'>"
	                   	   +"<input id='searchText' class='easyui-textbox' data-options=\"prompt:'在这里输入!',iconWidth: 22,"
						   +"icons:[{iconCls:'icon-search',"
						   +"handler:function(e){var searchText = $(e.data.target).textbox('getValue');$('#tree1').tree('doFilter',searchText);}"
						   +"},{iconCls:'icon-reload',handler:reload}]\" style='width:95%;height:24px;'></input>"
	                       +"</div><div class='scrollbar' style='margin-top:25px;'><ul class='easyui-tree' id='tree"
	                       + id + "' style='padding 5px 0 5px;' url=''></ul></div></div>"
	                       :"<div><div class='scrollbar'><ul class='easyui-tree' id='tree"
	                       + id + "' style='padding 5px 0 5px' url=''></ul></div></div>";
					$('#RightAccordion').accordion('add', {
                           title: e.text,
                           content: searchCmp,
                           selected: id==1||data.length==1,
                           iconCls: e.iconCls//e.Icon
                       });

					 $.post("../tree/getSubList/"+id,function(resp){
	                 	   $("#tree"+id).tree({
	                 		   data:resp,
	                 		   animate:true,
	                 		   checkbox:false,
	                 		   lines:true,
	                 		   "onBeforeExpand":function(node){
	                 				$("#tree1").tree("options").url = "../leftMenu/getAppList?id="+node.dataID+"&type="+node.type+"&typeID="+node.typeID;
	                 			 	return true;
	                 		   },
	                       	   "onClick":function (node) {
	                       		   if(node.attributes!=null)
	            		              $("#frmRight").attr("src", node.attributes);
	            		        }
	                        });
	                 	  if(window.location.href.indexOf("#")>0){
	                 			var url=window.location.href.substring(window.location.href.indexOf("#")+1);
	                 			//console.log(Base64.decode(url));
	                 			$("#frmRight").attr("src",Base64.decode(url));
	                 			//console.log(_index);
	                 		}else{
	  	                 	  $("#_easyui_tree_1").click();
	                 		}
	                    },"json");
               });
              // $(".scrollbar").css("height",$("#RightAccordion .accordion-body").css("height").replace("px","")-25+"px");
             //  $(".scrollbar").mCustomScrollbar({theme:"minimal-dark",axis:"yx",scrollbarPosition:"outside"});
               $('#searchText').textbox().textbox('addClearBtn', 'icon-clear');
               $('#searchText').textbox('textbox').keydown(function(e){
            	   if(e.keyCode==13){
            		   var searchText = $('#searchText').textbox('getValue');
                	   $('#tree1').tree('doFilter',searchText);
            	   }
               });

    }, "json");
    //$(".layout-panel-center :first-child").mCustomScrollbar({theme:"minimal-dark",axis:"yx",scrollbarPosition:"outside"});


    $("#tempDown").on("click",function(){
    	$("#dialog").html("").dialog({
    		href:"./TemplateDownloadDialog.html",
    		title: '模板下载',
    	    width: 400,
    	    height: 550,
    	    closed: false,
    	    cache: false,
    	    modal: true,
    	    buttons:[{
				text:'下载所选模板',
				handler:function(){
					var form=$("<form id='subForm'>");//定义一个form表单
					form.attr("style","display:none");
					form.attr("target","");
					form.attr("method","post");
					form.attr("action","../template/zip");
					$("body").append(form);//将表单放置在web中
					var lines=$("#tempList").datalist("getSelections");
					$.each(lines,function(i,item){
						form.append($("<input>").attr("name","name").attr("value",item.id).attr("type","hidden"));
					});
					form.submit();
					$("#subForm").html("");
				}
			},{
				text:'取消',
				handler:function(){
					$("#dialog").dialog('close');
				}
			}]
    	});
    });

});

function reload(){
	var node = $('#tree1').tree('getSelected');
	$('#tree1').tree('doFilter','');
	if(node){
		$('#tree1').tree('reload',node.target);
	}else{
		$('#tree1').tree('reload');
	}
}

function doSearch(){
	$("#frmRight").attr("src", "Search.html?value="+escape($("#qSearch").searchbox("getValue")));
	//Util.openUrl("Search.html?value="+escape(value));
}

function appDisplay(){
	Util.openUrl('Cola.html?type=APPLICATION');
}

function dbDisplay(){
	Util.openUrl('Cola.html?type=DATABASE');
}