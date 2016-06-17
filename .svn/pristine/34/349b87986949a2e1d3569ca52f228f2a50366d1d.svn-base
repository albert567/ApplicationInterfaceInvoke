var _node = null;
var rootNode=null;
$(function(){
	$("#list").panel({
				"onCollapse" : function() {
					outer.attr("width", $("#cola").width()).attr("height",
							$("#cola").height());
				},
				"onExpand" : function() {
					outer.attr("width", width).attr("height", height);
				}
			});
	if(Util.param("type")=="APPLICATION"&&Util.empty(Util.param("id"))){
		showView({"type":"APPLICATIONS","id":0});
	}else if(Util.param("type")=="DATABASE"&&Util.empty(Util.param("id"))){
		showView({"type":"DATABASES","id":0});
	}else{
		showView(Util.options({"type":"APPLICATIONS","id":0},{"type":Util.param("type"),"id":Util.param("id")},true));
	}
});


/*[start] 右侧列表操作*/
function doSearch(text) {
	if (_node == null) {
		return;
	}
	if (typeof text == "undefined") {
		text = "";
	}
	var data = {};
	data.rows = [];
	var node=_node.data;
	node.group="当前节点";
	data.rows.push(node);
	if(typeof _node.cast!="undefined"&&_node.cast!=null){
		$.each(_node.cast, function(index, item) {
					if (text.length == 0
							|| item.name.toLowerCase().indexOf(text.toLowerCase()) >= 0) {
						data.rows.push(item);
					}
				});
		$("#list").datalist('loadData', data);
		$(".datagrid-cell-c1-name").width($(".datagrid-cell-c1-name").width()+60);
	}
}

function listClick(index, row) {
	listOut(index,row);
	var focus = modelgraph.getNode(row);
	$.when(focus).then(function(node){
		click(node,true);
	});

}
function listMenu(e,index,row){
	var focus = modelgraph.getNode(row);
	$.when(focus).then(function(node){
		showMenu(node,e);
	});
}
function listOver(rowIndex, rowData){
	d3.select("#"+rowData.type+rowData.id).style("fill",seColor);
}
function listOut( rowIndex, rowData){
	if(typeof rowData!=="undefined"){
		$.each(viewgraph.nodes,function(i,n){
			if(n.data.type==rowData.type&&n.data.id==rowData.id){
				if(rowData.type==_node.data.type&&rowData.id==_node.id){
					d3.select("#"+rowData.type+rowData.id).style("fill",nowColor);
				}else{
					d3.select("#"+rowData.type+rowData.id).style("fill",n.colour);
				}
			}
		});

	}
}
var undoArray = [];
var redoArray = [];

function clearHis(){
	undoArray = [];
	redoArray = [];
	$(".undo").css("color", "gray");
	$(".redo").css("color", "gray");
}

function listUndo() {
	if (undoArray.length > 1) {
		redoArray.push(undoArray.pop());
		var p = undoArray.pop();
		if (undoArray.length < 1) {
			$(".undo").css("color", "gray");
		}
		if (redoArray.length > 0) {
			$(".redo").css("color", "steelblue");
		}
		var focus = modelgraph.getNode(p);
		$.when(focus).then(function(node){
			click(node);
		});
	}
}
function listRedo() {
	if (redoArray.length > 0) {
		var p = redoArray.pop();
		if (redoArray.length < 1) {
			$(".redo").css("color", "gray");
		}
		var focus = modelgraph.getNode(p);
		$.when(focus).then(function(node){
			click(node);
		});
	}
}
function listDblClick(index, row) {}
/*[end]*/

var width = window.innerWidth - 210, height = window.innerHeight - 25, imageScale = 0.1;//画布尺寸
var margin = 10, pad = 12;
var d3cola = cola.d3adaptor().linkDistance(100).size([width, height]);//cola的D3插件
var outer = d3.select("#cola").append("svg").attr("width", width).attr("height", height).attr("pointer-events", "all");
var zoom = d3.behavior.zoom();//缩放控件
outer.append('rect').attr('class', 'background').attr('width', "100%").attr('height', "100%").call(zoom.on("zoom", redraw)).on("dblclick.zoom",function(){});
var defs = outer.append("svg:defs");
addGradient("SpikeGradient", "red", 1, "red", 0);
addGradient("EdgeGradient", red, 1, "darkgray", 1);
addGradient("ReverseEdgeGradient", "darkgray", 1, red, 1);
var vis = outer.append('g');
var edgesLayer = vis.append("g");//连接线图层
var nodesLayer = vis.append("g");//节点图层
var nodeMouseDown = false;//节点鼠标状态
var nodeWidth = 60, nodeHeight = 70;//节点尺寸
var red = "rgb(254, 137, 137)";
var blue = "rgb(238, 182, 25)";
var nowColor = "rgb(255,239,148)";
var seColor = "rgb(0, 204, 153)";

var modelgraph,viewgraph;




/*[start] 重绘*/
function showView(node){
	if(typeof node.data!=="undefined"){
		node=node.data;
	}
	d3.selectAll(".node").remove();
	d3.selectAll(".link").remove();
	rootNode=node;
	modelgraph = new tmdb.Graph();//模型图
	viewgraph = {nodes : [],links : []};//视图
	var d = modelgraph.getNode(node,addViewNode);//根节点
	$.when(d).then(function(startNode) {
		addViewNode(startNode);
		refocus(startNode);
		if (startNode != _node) {
			undoArray.push(startNode);
			if (undoArray.length > 1) {
				$(".undo").css("color", "steelblue");
			}
		}
		_node = startNode;
		doSearch();
	});
}

/*[end]*/

/*[start] 节点展开折叠**/
//添加节点视图
function addViewNode(v, startpos) {
	if(v.method!=1){
		v.viewgraphid = viewgraph.nodes.length;
		var d = v.getImage();
		$.when(d).then(function(node) {
			d3.select("#" + node.name()).append("image").attr("transform",
					"translate(2,2)").attr("xlink:href", function(v) {
						var url = v.imgurl;
						var simg = this;
						var img = new Image();
						img.onload = function() {
							simg.setAttribute("width", nodeWidth - 4);
							simg.setAttribute("height", nodeHeight - 4);
						};
						return img.src = url;
					});
		});
		if (typeof startpos !== 'undefined') {
			v.x = startpos.x;
			v.y = startpos.y;
		}
		viewgraph.nodes.push(v);
	}
}

var type=function(node){
	var type=node.type;
	if (typeof type!= "string") {
		type = type.type;
	}
	return type;
}

//获取视图节点
function getViewNode(node){
	for(var i=0;i<viewgraph.nodes.length;i++){
		var item=viewgraph.nodes[i];
		if(item.type.type==type(node)&&item.id==node.id){
			return item;
		}
	}
	return null;
}
//删除节点视图
function removeViewNode(v) {
	d3.select("#" + type(v)+ v.id).remove();
	for(var i=0;i<viewgraph.nodes.length;i++){
		var item=viewgraph.nodes[i];
		if(viewEq(item,v)){
			//console.log(v.data.uid,v);
			delete v.viewgraphid;
			//viewgraph.nodes.splice(i,1);
			//delete modelgraph.nodes[v.data.uid];
		}
	}
}

/*移除节点  从模型层删除然后重绘*/
function removeSubNodes(node,refresh,path) {
	if(typeof path=="undefined"){
		if(typeof node.parent=="undefined"){
			showView(node);
			return;
		}
		path=[];
		path.push(node.parent.data.uid);
		for(var i=0;i<node.parent.cast.length;i++){
			path.push(node.parent.cast[i].uid);
		}
	}
	path.push(node.data.uid);
	var collapseNeighbours = modelgraph.collapseNeighbours(path,node, function(v) {
				v=getViewNode(v);
				if(v!=null){
					removeSubNodes(v,false,path);
					if (inView(v))
						removeViewNode(v);
				}
			});
	if(refresh){
		refreshViewGraph();
		$.when(collapseNeighbours).then(function f() {refreshViewGraph();});
	}
}

//视图是否存在
function inView(v) {
	return typeof v !== "undefined" && typeof v.viewgraphid !== 'undefined';
}
//获取焦点
function refocus(focus) {
	var neighboursExpanded = modelgraph.expandNeighbours(focus, function(v) {
				if (!inView(v))
					addViewNode(v, focus);
			});
	refreshViewGraph();
	$.when(neighboursExpanded).then(function f() {
				refreshViewGraph();
			});
}


function inLinks(link){
	if(typeof link.source=="undefined"||typeof link.target=="undefined"){
		return true;
	}
	for(var i=0;i<viewgraph.links.length;i++){
		var item=viewgraph.links[i];
		if(viewEq(item.source,link.source)&&viewEq(item.target,link.target)){
			return true;
		}else if(viewEq(item.target,link.source)&&viewEq(item.source,link.target)){
			return true;
		}
	}
	return false;
}
function viewEq(n1,n2){
	return n1.data.uid==n2.data.uid;
}

//刷新视图
function refreshViewGraph() {
	viewgraph.links = [];
	viewgraph.nodes.forEach(function(v) {
				var fullyExpanded = modelgraph.fullyExpanded(v);
				v.colour = fullyExpanded ? "darkgrey" : red;
				v.display="none";
				if (!v.cast)
					return;
			});
			viewgraph.nodes[0].display="block";
	Object.keys(modelgraph.edges).forEach(function(e) {
				var l = modelgraph.edges[e];
				var u = modelgraph.nodes[l.source],
					v = modelgraph.nodes[l.target];
				if (inView(u) && inView(v))
					if(!inLinks({source : u,target :v})){
						u.display="block";
						v.display="block";
						viewgraph.links.push({
									source : u,
									target : v
								});
					}
				if (inView(u) && !inView(v))
					u.colour = red;
				if (!inView(u) && inView(v))
					v.colour = red;
			});
	update();
}
/*[end]*/

/*[start] 绘制视图*/
var _update=false;
var _updateCount;
function update() {
	d3cola.nodes(viewgraph.nodes).links(viewgraph.links).start();
	var link = edgesLayer.selectAll(".link").data(viewgraph.links);
	link.enter().append("rect").attr("x", 0).attr("y", 0).attr("height", 2)
			.attr("class", "link");
	link.exit().remove();
	link.attr("fill", function(d) {
				if (d.source.colour === red && d.target.colour === red)
					return red;
				if (d.source.colour !== red && d.target.colour !== red)
					return "darkgray";
				return d.source.colour === red
						? "url(#ReverseEdgeGradient)"
						: "url(#EdgeGradient)";
			});
	//console.log(link);
	link.style("display",function(l){
		return (l.source.method==1||l.source.method==-1)?"none":"block";
	});
	var node = nodesLayer.selectAll(".node").data(viewgraph.nodes, function(d) {
				return d.viewgraphid;
			});
	var nodeEnter = node.enter().append("g").attr("id", function(d) {
				return d.name()
			}).attr("class", "node").on("mousedown", function() {
				nodeMouseDown = true;
				//console.log("update","nodeMouseDown");
			}).on("mouseup", function() {
				nodeMouseDown = false;
				//console.log("update","nodeMouseUp");
			}).on("touchmove", function() {
				d3.event.preventDefault()
			}).on("mouseenter", function(d) {
				hintNeighbours(d)
			}).on("mouseleave", function(d) {
						unhintNeighbours(d)
			}).call(d3cola.drag);
	nodeEnter.append("g").attr("id", function(d) {
				return d.name() + "_spikes"
			}).attr("transform", "translate(3,3)");
	nodeEnter.append("rect").attr("rx", 5).attr("ry", 5).style("stroke-width",
			"0").attr("width", nodeWidth).attr("height", nodeHeight)
		/*.style("display",function(n){
			return n.display;
			})*/.on(
			"click", function(d) {
				click(d)
			}).on("contextmenu", function(d) {
				showMenu(d,d3.event)
			}).on("touchend", function(d) {
				click(d)
			});
	d3.select(".node").style("display","none");
	nodeEnter.append("image").attr("transform", "translate(1,2)").attr(
			"xlink:href", function(v) {
				var url = v.imgurl;
				var simg = this;
				var img = new Image();
				img.onload = function() {
					simg.setAttribute("width", nodeWidth - 4);
					simg.setAttribute("height", nodeHeight - 25);
				};
				return img.src = url;
			})/*.style("display",function(n){
			return n.display;
			})*/.on("click", function(d) {
				click(d)
			}).on("contextmenu", function(d) {
				showMenu(d,d3.event)
			}).on("touchend", function(d) {
				click(d)
			});
	var text = nodeEnter.append("text").attr("class", "label").style(
			"stroke-width", "0").text(function(d) {
				return d.label;
			}).attr("transform", function(d) {
				return "translate(25,45)";
			}).on(
			"click", function(d) {
				click(d)
			}).on("contextmenu", function(d) {
				showMenu(d,d3.event)
			}).on("touchend", function(d) {
				click(d)
			});

	text.selectAll("tspan").data(function() {
		var str =$(this.parentNode).text();// $(this.parentNode).context.__data__.display=="none"?"":$(this.parentNode).text();
		var max = nodeWidth - 4;
		var fontsize = 10;
		var curLen = 0;
		var result = [];
		var start = 0, end = 0;
		for (var i = 0; i < str.length; i++) {
			if (result.length == 2) {
				result[1] = result[1].substring(0, result[1].length - 3)
						+ "...";
				break;
			}
			var code = str.charCodeAt(i);
			var pixelLen = code > 255 ? fontsize : fontsize / 2;
			curLen += pixelLen;
			if (curLen > max) {
				end = i;
				result.push(str.substring(start, end));
				start = i;
				curLen = pixelLen;
			}
			if (i === str.length - 1) {
				end = i;
				result.push(str.substring(start, end + 1));
			}
		}
		$(this.parentNode).text("");
		return result;
	}).enter().append("tspan").attr("x", "0").attr("dy", "1em")
		.style("fontSize", "10")
		.text(
		function(d) {
			return d;
		});
	nodeEnter.append("title").text(function(d) {
				return d.label;
			});
	node.style("fill", function(d) {
			if(_node==null){
				_node=rootNode;
				if(typeof _node.data=="undefined"){
					_node.data=rootNode;
				}
			}
			if(d.type==_node.data.type&&d.id==_node.id){
				return nowColor;
			}else{
				return d.colour;
			}
	});

	viewgraph.nodes.forEach(function(v){
		if($(".node[id='"+v.data.uid+"']").length>1){
			d3.select(d3.select("#"+v.data.uid+"")[0][0]).remove();
		}
	});

	node.style("display",function(d){
		return ((d.method==1||d.method==-1||d.display!="block")?"none":"block");
	});


	//_update=0;
	d3cola.on("tick", function() {
		//if(++_update<(viewgraph.nodes.length>10?25:300)){
			link.attr("transform", function(d) {
				var dx = d.source.x - d.target.x, dy = d.source.y
						- d.target.y;
				var r = 180 * Math.atan2(dy, dx) / Math.PI;
				return "translate(" + d.target.x + "," + d.target.y
						+ ") rotate(" + r + ") ";
			}).attr("width", function(d) {
				var dx = d.source.x - d.target.x, dy = d.source.y
						- d.target.y;
				return Math.sqrt(dx * dx + dy * dy);
			});


			node.attr("transform", function(d) {
						return "translate(" + (d.x - nodeWidth / 2) + ","
								+ (d.y - nodeHeight / 2) + ")";
					});


		//}
	});

	//_updateCount=0;
	d3cola.on("start",function(){
		_update=true;
		$("#msg").html("加载中...");
	});
	d3cola.on("end",function(){
		if(_update){
			_update=false;
			//_updateCount=0;
			$("#msg").html("加载完成!");
		}else{
			d3cola.start();
		}
	});
}
/*[end]*/

/*[start] 节点标题*/
//显示标题
function hintNeighbours(v) {
	if (!v.cast)
		return;
	var hiddenEdges = v.cast.length + 1 - v.degree;
	var r = 2 * Math.PI / hiddenEdges;
	for (var i = 0; i < hiddenEdges; ++i) {
		var w = nodeWidth - 6, h = nodeHeight - 6, x = w / 2 + 25
				* Math.cos(r * i), y = h / 2 + 30 * Math.sin(r * i), rect = new cola.vpsc.Rectangle(
				0, w, 0, h), vi = rect.rayIntersection(x, y);
		var dview = d3.select("#" + v.name() + "_spikes");
		if (vi != null) {
			dview.append("rect").attr("class", "spike").attr("rx", 1).attr(
					"ry", 1).attr("x", 0).attr("y", 0).attr("width", 10).attr(
					"height", 2).attr(
					"transform",
					"translate(" + vi.x + "," + vi.y + ") rotate("
							+ (360 * i / hiddenEdges) + ")").on("click",
					function() {
						click(v)
					});
		}
	}
}
//隐藏标题
function unhintNeighbours(v) {
	var dview = d3.select("#" + v.name() + "_spikes");
	dview.selectAll(".spike").remove();
}
/*[end]*/

/*[start] 节点点击事件*/

var _clickEvent={"time":new Date().getTime(),"node":{"type":"","id":0}};
function click(node,list) {
	if(typeof list=="undefined"){
		list=false;
	}
	if(!list&&(_clickEvent.node==null||
		new Date().getTime()-_clickEvent.time>500||
		!(_clickEvent.node.type==node.data.type&&
			_clickEvent.node.id==node.data.id))){
		_clickEvent.time=new Date().getTime();
		_clickEvent.node.type=node.data.type;
		_clickEvent.node.id=node.data.id;
	}else{
		if (node != _node) {
			undoArray.push(node);
			if (undoArray.length > 1) {
				$(".undo").css("color", "steelblue");
			}
		}
		_node = node;
		doSearch();
		if(node.method ==1 ||node.method==2){
			clearHis();
			node.data.method=0;
			showView(node);
		}
		if(node.colour == red){
			var focus = modelgraph.getNode(node);
			refocus(focus);
		}else{
			if(typeof node.cast!="undefined" && node.cast!=null&&node.cast.length>0){
				removeSubNodes(node,true);
			}
		}
	}
}
function addGradient(id, colour1, opacity1, colour2, opacity2){
	var gradient = defs.append("svg:linearGradient").attr("id", id).attr("x1",
		"0%").attr("y1", "0%").attr("x2", "100%").attr("y2", "0%").attr(
		"spreadMethod", "pad");
	gradient.append("svg:stop").attr("offset", "0%")
		.attr("stop-color", colour1).attr("stop-opacity", opacity1);
	gradient.append("svg:stop").attr("offset", "100%").attr("stop-color",
		colour2).attr("stop-opacity", opacity2);
}
/*[end]*/

/*[start] 全屏及缩放*/
function graphBounds() {var x = Number.POSITIVE_INFINITY, X = Number.NEGATIVE_INFINITY, y = Number.POSITIVE_INFINITY, Y = Number.NEGATIVE_INFINITY;	nodesLayer.selectAll(".node").each(function(v) {x = Math.min(x, v.x - nodeWidth / 2);X = Math.max(X, v.x + nodeWidth / 2);y = Math.min(y, v.y - nodeHeight / 2);Y = Math.max(Y, v.y + nodeHeight / 2);});return {x : x,X : X,y : y,Y : Y};}
//将图形缩放到画布大小
function zoomToFit() {
	var b = graphBounds();
	var w = b.X - b.x, h = b.Y - b.y;
	var cw = outer.attr("width"), ch = outer.attr("height");
	var s = Math.min(cw / w, ch / h);
	var tx = (-b.x * s + (cw / s - w) * s / 2), ty = (-b.y * s + (ch / s - h)
			* s / 2);
	zoom.translate([tx, ty]).scale(s);
	redraw(true);
}
function redraw(transition) {
	if (nodeMouseDown)
		return;
	(transition ? vis.transition(): vis).attr("transform", "translate("
					+ zoom.translate() + ") scale(" + zoom.scale() + ")");
}
//全屏
function full() {
	if(Util.ie(6,11)){
		$.messager.alert("提示","IE浏览器请按[F11]键进行全屏操作");
	}else{
		if ($('#fullS').html() == $('<i class="iconfont">&#xe6b8;</i>').html()) {
			$('#fullS').html($('<i class="iconfont">&#xe6b7;</i>').html());
		} else {
			$('#fullS').html($('<i class="iconfont">&#xe6b8;</i>').html());
		}
		fullScreen($('body').get(0), this.fullScreenCancel);
		zoomToFit();
	}
}
//取消全屏
function fullScreenCancel(){
	outer.attr("width", width).attr("height", height);
	zoomToFit();
}
/*[end]*/

/*[start] 右键菜单*/
$(document).on('contextmenu', function() {return false;});
function showMenu(node,e) {
	function mItem(text, click) {
		return $("<div class='menu-item'><div class='menu-text' style='height: 20px; line-height: 20px;'>"
				+ text + "</div></div>").click(click);
	}
	function mSep() {
		return "<div class='menu-sep'></div>";
	}
	var menu=$("#mm").html('');
	menu.append(mItem("以此为中心展开",function(){
			clearHis();
			node.data.method=0;
			showView(node);
			$("#mm").menu("hide");
	}));
	function title(type){
		switch(type){
			case "METHOD":return "参数";
			case "FUNCTION":return "源代码";
			default :return "字段";
		}
	}
	if ("|TABLE|VIEW|EXEC|METHOD|FUNCTION|".indexOf("|"+node.type.type+"|")>-1) {
		menu.append(mSep()).append(mItem("查看"
						+title(node.type.type) ,
				function() {
					switch (node.type.type) {
						case "METHOD":Util.openUrl("MethodDetail.html?MethodID=" + node.id);return;
						case "FUNCTION":Util.openUrl("FunctionDetail.html?funcID=" + node.id);return;
						default:Util.openUrl("ColObjDetail.html?ObjID=" + node.id);return;
					}
				}));
	}
	menu.menu("show", {
					top : e.pageY,
					left : e.pageX
				});
}
/*[end]*/