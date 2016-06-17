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
  var fd = new $jit.ForceDirected({
    //id of the visualization container
    injectInto: 'infovis',
	
    //Enable zooming and panning
    //with scrolling and DnD
    Navigation: {
      enable: true,
      type: 'Native',
      //Enable panning events only if we're dragging the empty
      //canvas (and not a node).
      panning: 'avoid nodes',
      zooming: 10 //zoom speed. higher is more sensible
    },
    // Change node and edge styles such as
    // color and width.
    // These properties are also set per node
    // with dollar prefixed data-properties in the
    // JSON structure.
    Node: {
      overridable: true,
      dim: 7
    },
    Edge: {
      overridable: true,
      color: '#23A4FF',
      lineWidth: 0.4
    },
    // Add node events
    Events: {
      enable: true,
      type: 'Native',
      //Change cursor style when hovering a node
      onMouseEnter: function() {
        fd.canvas.getElement().style.cursor = 'move';
      },
      onMouseLeave: function() {
        fd.canvas.getElement().style.cursor = '';
      },
      //Update node positions when dragged
      onDragMove: function(node, eventInfo, e) {
        var pos = eventInfo.getPos();
        node.pos.setc(pos.x, pos.y);
        fd.plot();
      },
      //Implement the same handler for touchscreens
      onTouchMove: function(node, eventInfo, e) {
        $jit.util.event.stop(e); //stop default touchmove event
        this.onDragMove(node, eventInfo, e);
      }
    },
    //Number of iterations for the FD algorithm
    iterations: 200,
    //Edge length
    levelDistance: 130,
    // This method is only triggered
    // on label creation and only for DOM labels (not native canvas ones).
    onCreateLabel: function(domElement, node){
      // Create a 'name' and 'close' buttons and add them
      // to the main node label
      var nameContainer = document.createElement('span'),
		  funcSpan = document.createElement('span'),
          style = nameContainer.style;
      nameContainer.className = 'name';
      nameContainer.innerHTML = node.name;
	  
      domElement.appendChild(nameContainer);
	  domElement.appendChild(funcSpan);
	  
      style.fontSize = "0.8em";
      style.color = "#ddd";
	  
      //Toggle a node selection when clicking
      //its name. This is done by animating some
      //node styles like its dimension and the color
      //and lineWidth of its adjacencies.
      nameContainer.onclick = function() {
        //set final styles
        fd.graph.eachNode(function(n) {
          if(n.id != node.id) delete n.selected;
          n.setData('dim', 7, 'end');
          n.eachAdjacency(function(adj) {
             adj.setDataset('end', {
	             lineWidth: 0.4,
	             //color: '#23a4ff'
             });
          });
        });
        if(!node.selected) {
          node.selected = true;
          node.setData('dim', 17, 'end');
          node.eachAdjacency(function(adj) {
            adj.setDataset('end', {
              lineWidth: 3,
              //color: '#36acfb',
			  //alpha:1
            });
			//adj.nodeTo.setData('alpha', 1, 'end');
          });
        } else {
          delete node.selected;
		 
		  /*node.eachAdjacency(function(adj) {
				adj.setData('alpha', 0, 'end');
				adj.nodeTo.setData('alpha', 0, 'end');
		  });*/
        }
		
		fd.fx.animate({
          modes: ['node-property:dim',
                  'edge-property:lineWidth:color'],
          duration: 500,
		  onComplete: function() {
			fd.fx.animate({
			  modes: ['node-property:alpha',
					  'edge-property:alpha'],
			  duration: 500
			});
		  }
        });
		
      };
    },
    // Change node styles when DOM labels are placed
    // or moved.
    onPlaceLabel: function(domElement, node){
      var style = domElement.style;
      var left = parseInt(style.left);
      var top = parseInt(style.top);
      var w = domElement.offsetWidth;
      style.left = (left - w / 2) + 'px';
      style.top = (top + 10) + 'px';
      style.display = '';
    }
  });
  
  $.ajax({
		//url : '../application/getForceData?appID=36&appName=',
	  	url : '../database/getForceData?dbID='+Util.param('dbID'),
		type : 'post',
		success : function(data) {
			// load JSON data.
			fd.loadJSON(data);
			fd.canvas.scale(1.5, 1.5);
			// compute positions incrementally and animate.
			fd.computeIncremental({
			  iter: 40,
			  property: 'end',
			  onStep: function(perc){
			    Log.write(perc + '% loaded...');
			  },
			  onComplete: function(){
			    Log.write('ok');
				  fd.animate({
						modes: ['linear'],
						transition: $jit.Trans.Elastic.easeOut,
						duration: 2500
			    });
				}
			});
		}
	});
  // end
}
