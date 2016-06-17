var labelType, useGradients, nativeTextSupport, animate;

(function() {
  var ua = navigator.userAgent,
      iStuff = ua.match(/iPhone/i) || ua.match(/iPad/i),
      typeOfCanvas = typeof HTMLCanvasElement,
      nativeCanvasSupport = (typeOfCanvas == 'object' || typeOfCanvas == 'function'),
      textSupport = nativeCanvasSupport 
        && (typeof document.createElement('canvas').getContext('2d').fillText == 'function');
  //I'm setting this based on the fact that ExCanvas provides text support for IE
  //and that as of today iPhone/iPad current text support is lame
  labelType = (!nativeCanvasSupport || (textSupport && !iStuff))? 'Native' : 'HTML';
  nativeTextSupport = labelType == 'Native';
  useGradients = nativeCanvasSupport;
  animate = !(iStuff || !nativeCanvasSupport);
})();

var Log = {
  elem: false,
  write: function(text){
    if (!this.elem) 
      this.elem = document.getElementById('log');
    this.elem.innerHTML = text;
    this.elem.style.left = (500 - this.elem.offsetWidth / 2) + 'px';
  }
};


function init(){
	//init Spacetree
    //Create a new ST instance
    var st = new $jit.ST({
        //id of viz container element
        injectInto: 'infovis',
        Navigation: {
            enable:true,
            panning:true
          },
        //multitree
    	  multitree: true,
        //set duration for the animation
        duration: 800,
        //set animation transition type
        transition: $jit.Trans.Quart.easeInOut,
        //set distance between node and its children
        levelDistance: 60,
        levelsToShow: 3,
        //sibling and subtrees offsets
        siblingOffset: 3,
        subtreeOffset: 3,
        //set node and edge styles
        //set overridable=true for styling individual
        //nodes or edges
        Node: {
            height: 55,
            width: 120,
            type: 'ellipse',
            color: '#aaa',
            overridable: true,
            //set canvas specific styles
            //like shadows
            CanvasStyles: {
              shadowColor: '#ccc',
              shadowBlur: 10
            }
        },
        Edge: {
            type: 'line',
            overridable: true
        },
        Events: {
        	enable: true,
        },
        onBeforeCompute: function(node){
            Log.write("loading " + node.name);
        },
        
        onAfterCompute: function(){
            Log.write("done");
        },
        
        //This method is called on DOM label creation.
        //Use this method to add event handlers and styles to
        //your node.
        onCreateLabel: function(label, node){
            label.id = node.id;            
            label.innerHTML = node.name;
            label.onclick = function(){
            	/*if(normal.checked) {
                st.onClick(node.id);
            	} else {
                st.setRoot(node.id, 'animate');
            	}*/
            	st.onClick(node.id);
            };
            label.oncontextmenu=function(){
          		if(node.data.type=="DbObj"||node.data.type=="Method"){
					$("#mm")
					.html('').append($("<div class='menu-item'><div class='menu-text' style='height: 20px; line-height: 20px;'>编辑</div></div>").click(function(){
						if(node.data.type=="Method"){
							Util.win.open({
								url:"MethodEditor.html?id="+node.data.nodeID,
								width: 500,
								height:380
							});
						}else{
							Util.openUrl("DbObjDetail.html?ObjID="+node.data.nodeID);
						}
					})).append("<div class='menu-sep'></div>")
					.append($("<div class='menu-item'><div class='menu-text' style='height: 20px; line-height: 20px;'>查看"+(node.data.type=="DbObj"?"字段":"参数")+"</div></div>")
					.click(function(){
						if(node.data.type=="Method"){
							Util.openUrl("MethodDetail.html?MethodID="+node.data.nodeID);
						}else{
							Util.openUrl("DbObjDetail.html?ObjID="+node.data.nodeID);
						}
					}))
					.menu("show",{
						left: 60+parseInt(label.style.left.substring(label.style.left,label.style.left.indexOf('px')),10),
	      				top: 40+parseInt(label.style.top.substring(label.style.top,label.style.top.indexOf('px')),10)
					});
				}
            };
            //set label styles
            var style = label.style;
            style.width = 120 + 'px';
            style.height = 55 + 'px';            
            style.cursor = 'pointer';
            style.color = '#333';
            style.fontSize = '0.8em';
            style.textAlign= 'center';
            style.paddingTop = '10px';
        },
        
        //This method is called right before plotting
        //a node. It's useful for changing an individual node
        //style properties before plotting it.
        //The data properties prefixed with a dollar
        //sign will override the global node style properties.
        onBeforePlotNode: function(node){
            //add some color to the nodes in the path between the
            //root node and the selected node.
            if (node.selected) {
                node.data.$color = "#ff7";
            }
            else {
                delete node.data.$color;
                //if the node belongs to the last plotted level
                if(!node.anySubnode("exist")) {
                    //count children number
                    var count = 0;
                    node.eachSubnode(function(n) { count++; });
                    //assign a node color based on
                    //how many children it has
                    node.data.$color = ['#aaa', '#baa', '#caa', '#daa', '#eaa'][count%5];                    
                }
            }
        },
        
        //This method is called right before plotting
        //an edge. It's useful for changing an individual edge
        //style properties before plotting it.
        //Edge data proprties prefixed with a dollar sign will
        //override the Edge global style properties.
        onBeforePlotLine: function(adj){
            if (adj.nodeFrom.selected && adj.nodeTo.selected) {
                adj.data.$color = "#eed";
                adj.data.$lineWidth = 3;
            }
            else {
                delete adj.data.$color;
                delete adj.data.$lineWidth;
            }
        }
    });
    $.ajax({
		url : '../space/getDbData?dbID='+Util.param('dbID'),
		type : 'post',
		success : function(data) {
			var json = data;
			//preprocess subtrees orientation
		    var arr = json.children, len = arr.length;
		    for(var i=0; i < len; i++) {
		    	arr[i].data.$orn = 'left';
	    		$jit.json.each(arr[i], function(n) {
	    			n.data.$orn = 'left';
	    		});
		      /*if(arr[i].id=='T_App') {
		    		arr[i].data.$orn = 'right';
		    		$jit.json.each(arr[i], function(n) {
		    			n.data.$orn = 'right';
		    		});
		    	} else {
		    	//half right
		    		arr[i].data.$orn = 'left';
		    		$jit.json.each(arr[i], function(n) {
		    			n.data.$orn = 'left';
		    		});
		    	}*/
		    }
		    //end
		    st.loadJSON(json);
		    //compute node positions and layout
		    st.compute('end');
		    st.select(st.root);
		}
	});
}
