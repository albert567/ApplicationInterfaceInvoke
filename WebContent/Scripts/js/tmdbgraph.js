var tmdb,msg=false;
function reLoad(){
	if(Util.param("err")!="true"){
		window.location.href+=(window.location.href.indexOf("?")>0?"&":"?")+"err=true";
	}else{
		if(!msg){
			msg=true;
			$.messager.alert("提示","数据请求错误,请联系系统管理员!");
		}
	}
}
(function(tmdb) {
	/*[start] NodeType*/
	var NodeType = (function() {
		function NodeType(type, credits, label, imagesarray) {
			this.type = type;
			this.credits = credits;
			this.label = label;
			this.imagesarray = imagesarray;
		}
		NodeType.prototype.toString = function() {
			return this.type;
		};
		NodeType.prototype.makeEdge = function(thisName, otherName) {
			return new Edge(thisName, otherName)
		};
		return NodeType;
	})();
	tmdb.NodeType = NodeType;
	tmdb.type = function(t) {
		if (typeof t == "string") {
			return new NodeType(t, "sub", "name", "");
		}
		return t;
	};
	/*[end]*/
	/*[start] Node*/
	var Node = (function() {
		function Node(data) {
			this.type = tmdb.type(data.type);
			this.id = data.id;
			this.degree = 0;
			this.method=data.method;
			this.data=data;
		}
		Node.prototype.name = function() {
			if(typeof this.id=="undefined"){
				return this.type+"0";
			}
			return this.type + this.id.toString();
		};
		Node.prototype.method = function() {
			return this.method;
		};
		Node.prototype.data = function() {
			return this.data;
		};
		Node.prototype.getImage = function() {
			var _this = this;
			var d = $.Deferred();
			_this.imgurl = "../Images/jtopo/" + _this.type.type.toLowerCase()
					+ ".png";
			return d.resolve(_this);
		};
		return Node;
	})();
	tmdb.Node = Node;
	/*[end]*/
	/*[start] Edge*/
	var Edge = (function() {
		function Edge(source, target) {
			this.source = source;
			this.target = target;
		}
		Edge.prototype.toString = function() {
			return this.source + '-' + this.target;
		};
		return Edge;
	})();
	tmdb.Edge = Edge;
	/*[end]*/
	function request(data) {
		var query = "../topo/sub/" + data.type + "/" + data.id;
		data.sub=[];
		var resp=$.post(query,data);
		$.when(resp).fail(function(r){
			reLoad();
		});
		return resp;
	}
	/*[start] Graph*/
	var Graph = (function() {
		function Graph() {
			this.nodes = {};
			this.edges = {};
		}
		Graph.prototype.expandNeighbours = function(node, f) {
			var _this = this;
			var dn = node.cast.map(function(c) {
						return _this.getNode(c, function(v) {
									v.label = c[v.type.label];
									v.parent=node;
									_this.addEdge(node, v);
									f(v);
								});
					});
			var d = $.Deferred();
			$.when.apply($, dn).then(function() {
						var neighbours = Array.prototype.slice.call(arguments);
						d.resolve(neighbours);
					});
			return d.promise();
		};
		Graph.prototype.collapseNeighbours = function(path,node, f) {
			var _this = this;
			node.cast.map(function(c) {
				var remove=true;
				for(var i=0;i<path.length;i++){
					if(c.uid==path[i]){
						remove=false;
					}
				}
				if(remove){
					_this.removeNode(c);
					f(c);
				}
			});
			return node;
		};
		Graph.prototype.fullyExpanded = function(node) {
			var _this = this;
			return node.cast && node.cast.every(function(v) {
						var type = tmdb.type(v.type);
						return (type + v.id) in _this.nodes;
					});
		};
		Graph.prototype.removeNode = function(node) {
			delete this.nodes[tmdb.type(node.type).type+node.id];
		};
		Graph.prototype.addNode = function(data) {
			var node = new Node(data);
			return this.nodes[node.name()] = node;
		};
		Graph.prototype.getNode = function(data, f) {
			var type=tmdb.type(data.type);
			var id=data.id;
			var _this = this;
			var name = type + id.toString();
			if (name in this.nodes) {
				return this.nodes[name];
			}
			var d = $.Deferred();
			data.type=type.type;
			if(data.method!=1){
				var cast = request(data);
				$.when(cast).then(function(c) {
					var node = _this.addNode(c);
					if (typeof f == "function") {
						f(node);
					}
					node.label = c[type.label];
					(node.cast = c[type.credits]).forEach(function(v) {
							var neighbourname = v.type + v.id.toString();
							if (neighbourname in _this.nodes) {
								_this.nodes[neighbourname].data=v;
							}
							_this.addEdge(node,{"name":function(){return neighbourname;},"method":v.method});

						});
					d.resolve(node);
				});
			}else{
				d.resolve(_this.addNode(data));
			}
			return d.promise();
		};
		Graph.prototype.addEdge = function(u, v) {
			if(v.method!=1){
				var edge = u.type.makeEdge(u.name(), v.name());
				var ename = edge.toString();
				if (!(ename in this.edges)) {
					this.edges[ename] = edge;
				}
				++u.degree, ++v.degree;
			}
		};
		Graph.prototype.removeEdge = function(u, v) {
			var edge = u.type.makeEdge(u.name(), v.type+v.id);
			var ename = edge.toString();
			if ((ename in this.edges)) {
				delete this.edges[ename];
			}
			--u.degree, --v.degree;
		};
		return Graph;
	})();
	tmdb.Graph = Graph;
	/*[end]*/
})(tmdb || (tmdb = {}));