$(function() {
	$.ajaxSetup({cache : false,
		dataFilter:function(data,type){
			if(type=="html"){
				return data;
			}
				try{
					$(data).find("html");
					window.location.reload();
				}catch(e){

				}
				return data;
		}});
	if (Util.ie(6,11)&&window.PIE) {
		$('.rounded').each(function() {
			PIE.attach(this);
		});
	}

	Util.css(document,"../Scripts/jquery-easyui-1.4.1/themes/IconExtension.css");
	/*给所有的数据列表添加滚动条*/
	if($("#dg").length>0){
		$('#dg').datagrid({multiSort:true,rowStyler : function(index, row) {return 'font-size:18pt;';}});

		var cFields = $('#dg').datagrid('getColumnFields');
		$.each(cFields,function(index,item){
			if(item!="do"){
				var x =$('#dg').datagrid('getColumnOption',item);
				x.sortable=true;
			}
		});


		/*
		Util.css(document,"../Styles/jquery.mCustomScrollbar.min.css");
		$.getScript("../Scripts/jquery-easyui-1.4.1/jquery.mCustomScrollbar.concat.min.js",function(){
			if(Util.ie(1,8)){
				$.getScript("../Scripts/html5.min.js",function(){
					$.getScript("../Scripts/css3-mediaqueries.js",function(){
						var ele=$("#dg").parent().parent().parent();
						ele.parent().append($('<div id="dtable" style="width: 100%;height: '+ele.height()+'px;">').append(ele));
						$("#dtable").mCustomScrollbar({theme:"minimal-dark",axis:"yx",scrollbarPosition:"inside"});
					});
				});
			}else{
				var ele=$("#dg").parent().parent().parent();
				ele.parent().append($('<div id="dtable" style="width: 100%;height: '+ele.height()+'px;">').append(ele));
				$("#dtable").mCustomScrollbar({theme:"minimal-dark",axis:"yx",scrollbarPosition:"inside"});
			}
		});
		*/
		Util.loadBtn();
	}
});

function validCbox(data){
	if(data.constructor!= Array){
		data=[data];
	}
	for(var i=0;i<data.length;i++){
		if($(data[i]).combobox('getValue')==$(data[i]).combobox('getText')){
			$(data[i]).combobox('setValue','');
		}else if($(data[i]).combobox('getValue')==''){
			$(data[i]).combobox('setValue','');
		}
	}
}

function validResp(data){
	try{
		var resp=eval('(' + data + ')');
		if(typeof resp.success=="undefined" || typeof resp.message=="undefined" ){
			return resp;
		}else{
			if(!resp.success){
				$.messager.alert("提示",resp.message);
			}
			return resp;
		}
	}catch(e){
		$.messager.alert("提示",data);
		throw e;
	}

}
function afterImport(uuid){
	Util.openUrl({
		url:"./AfterImport.html?from="+uuid
	});
}
var Util={
	defaults:{
		win:{url:'',height:720,width:1200,toolbar:'no',menubar:'no',scrollbars:'no',resizable:'no',location:'no',status:'no'}
	},
	win:{open:function(option){var opts = Util.options(Util.defaults.win, option);var url=opts.url.replace(/{\w*}/g,'');var winid=url.replace(/\W/g,'_');var iTop = (window.screen.availHeight-30-opts.height)/2;var iLeft = (window.screen.availWidth-10-opts.width)/2;window.open(url,winid,'height='+opts.height+',width='+opts.width+',top='+iTop+',left='+iLeft+',toolbar='+opts.toolbar+',menubar='+opts.menubar+',scrollbars='+opts.scrollbars+',resizable='+opts.resizable+',location='+opts.location+',status='+opts.status+'');},close:function(){window.opener=null;window.close();}},
	open:function(dgid,opt,onlyRow){var url;if(typeof opt=="string"){url=opt;opt={};}else{url=opt.url;}var sele = $("#dg").datagrid("getSelected");if (sele != null) {for(var key in sele){var reg=new RegExp("{"+key+"}","g");url=url.replace(reg,sele[key]);}}else{if(onlyRow){$.messager.alert("提示","请选择要操作的行!");return;}}	opt.url=url;Util.win.open(opt);},
  	ie:function(min,max){var ua = navigator.userAgent.toLowerCase();var ver= (s = ua.match(/rv:([\d.]+)\) like gecko/)) ? s[1]:(s = ua.match(/msie ([\d.]+)/)) ?s[1] :false;if(typeof min=="undefined"||min==null){return ver}else{if(typeof max=="undefined"||max==null){return ver==min}else{return ver>=min&&ver<=max;}}},
	css:function(doc,href){var sNode=doc.createElement('link');sNode.rel="stylesheet";sNode.type="text/css";sNode.media="screen";sNode.href=href;if(doc!=null&&doc.head!=null)doc.head.appendChild(sNode);else $(doc.getElementsByTagName('head')).append(sNode);},
	_on:new Array(),
  	rowOperator:function(a){},
  	openUrl:function(opt){var url;if(typeof opt=="string"){url=opt;opt={};}else{url=opt.url;}opt.url=url;Util.win.open(opt);},
  	param:function(name,def){var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");var r = window.location.search.substr(1).match(reg);if (r!=null)return unescape(r[2]);return def==undefined?null:def;},
  	encode:function(obj){return Base64.encodeURI(JSON.stringify(obj))},
  	paramObj:function(name){return JSON.parse(Base64.decode(Util.param(name,Base64.encodeURI("{}"))));},
  	empty:function(v){return v==null||v=='';},
  	options:function(def,opt,null2Def){if(typeof opt=="undefined"||opt==null){opt={};}for(var key in def){if(typeof opt[key]=="undefined"||(opt[key]==null&&null2Def)){opt[key]=def[key];}}return opt;},
  	loadOn:function(func,i){if(typeof i==="undefined"){i=0;}var val=0;if(typeof Util._on[func]!="undefined"){val=Util._on[func];}if(typeof func=="function"&&val==i){func();}Util._on[func]=val+1;},
  	loadBtn:function(){var strUrl=location.href;var arrUrl=strUrl.split("/");var strPage=arrUrl[arrUrl.length-1];var page=strPage.split(".")[0];var app=Util.param("appId");var db=Util.param("dbId");if(page=="Application"){app=-1;}if(page=="DataBase"){db=-1;}if((app==null&&db==null)||(app!=null&&db!=null)){return;}else{$.post("../rights/"+(app==null?"db":"app")+"/right/"+(app==null?db:app),function(data){if(data.a){$("#tb").link({text:'添加',icon:'icon-add',click:'append()'});}if(data.i){$("#tb").link({text:'导入',icon:'icon-page_excel',click:'imp()'});}var rf="Util.rowOperator=function(a){var x=$('<div>').css('float','left');";if(data.u){rf+="x.link({tip:'编辑',icon:'icon-edit',click:'edit('+a+')'});";	}if(data.d){rf+="x.link({tip:'删除',icon:'icon-remove',click:'removeit('+a+')'});";}if(data.u||data.d){rf+="x.separator();";}rf+="return x;}";eval(rf);if(typeof afterLoad=="function"){afterLoad(Base64.encodeURI(JSON.stringify(data)))}},"json");}}
};
/*separator&linkbutton*/
(function($) {$.fn.separator = function() {return $(this).append('<div class="datagrid-btn-separator" style="margin-left: 5px; margin-top: 5px;"></div>');};$.fn.link = function(options) {var opts = $.extend({}, $.fn.link.defaults, options);var func=options.click;if(typeof func=='undefined'){func='Util.open(\''+opts.dgid+'\',{url:\''+opts.url+'\',height:'+opts.height+',width:'+opts.width+'},'+opts.onlyRow+')';}var link=$('<a href="javascript:void(0)" style="margin-left: 5px;"' +' onClick="'+func+'" title="'+opts.tip+'">'+opts.text+'</a>').linkbutton({iconCls: opts.icon,plain:false});return $(this).append($('<div style="float: left; margin-top: 5px;">').append(link));};$.fn.link.defaults = {text: '',url: '',click:'',icon:'icon-application',dgid:'dg',tip:'',onlyRow:false,height:undefined,width:undefined};})(jQuery);
/*base64*/
(function(global){"use strict";var _Base64=global.Base64;var version="2.1.9";var buffer;if(typeof module!=="undefined"&&module.exports){try{buffer=require("buffer").Buffer}catch(err){}}var b64chars="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";var b64tab=function(bin){var t={};for(var i=0,l=bin.length;i<l;i++)t[bin.charAt(i)]=i;return t}(b64chars);var fromCharCode=String.fromCharCode;var cb_utob=function(c){if(c.length<2){var cc=c.charCodeAt(0);return cc<128?c:cc<2048?fromCharCode(192|cc>>>6)+fromCharCode(128|cc&63):fromCharCode(224|cc>>>12&15)+fromCharCode(128|cc>>>6&63)+fromCharCode(128|cc&63)}else{var cc=65536+(c.charCodeAt(0)-55296)*1024+(c.charCodeAt(1)-56320);return fromCharCode(240|cc>>>18&7)+fromCharCode(128|cc>>>12&63)+fromCharCode(128|cc>>>6&63)+fromCharCode(128|cc&63)}};var re_utob=/[\uD800-\uDBFF][\uDC00-\uDFFFF]|[^\x00-\x7F]/g;var utob=function(u){return u.replace(re_utob,cb_utob)};var cb_encode=function(ccc){var padlen=[0,2,1][ccc.length%3],ord=ccc.charCodeAt(0)<<16|(ccc.length>1?ccc.charCodeAt(1):0)<<8|(ccc.length>2?ccc.charCodeAt(2):0),chars=[b64chars.charAt(ord>>>18),b64chars.charAt(ord>>>12&63),padlen>=2?"=":b64chars.charAt(ord>>>6&63),padlen>=1?"=":b64chars.charAt(ord&63)];return chars.join("")};var btoa=global.btoa?function(b){return global.btoa(b)}:function(b){return b.replace(/[\s\S]{1,3}/g,cb_encode)};var _encode=buffer?function(u){return(u.constructor===buffer.constructor?u:new buffer(u)).toString("base64")}:function(u){return btoa(utob(u))};var encode=function(u,urisafe){return!urisafe?_encode(String(u)):_encode(String(u)).replace(/[+\/]/g,function(m0){return m0=="+"?"-":"_"}).replace(/=/g,"")};var encodeURI=function(u){return encode(u,true)};var re_btou=new RegExp(["[À-ß][-¿]","[à-ï][-¿]{2}","[ð-÷][-¿]{3}"].join("|"),"g");var cb_btou=function(cccc){switch(cccc.length){case 4:var cp=(7&cccc.charCodeAt(0))<<18|(63&cccc.charCodeAt(1))<<12|(63&cccc.charCodeAt(2))<<6|63&cccc.charCodeAt(3),offset=cp-65536;return fromCharCode((offset>>>10)+55296)+fromCharCode((offset&1023)+56320);case 3:return fromCharCode((15&cccc.charCodeAt(0))<<12|(63&cccc.charCodeAt(1))<<6|63&cccc.charCodeAt(2));default:return fromCharCode((31&cccc.charCodeAt(0))<<6|63&cccc.charCodeAt(1))}};var btou=function(b){return b.replace(re_btou,cb_btou)};var cb_decode=function(cccc){var len=cccc.length,padlen=len%4,n=(len>0?b64tab[cccc.charAt(0)]<<18:0)|(len>1?b64tab[cccc.charAt(1)]<<12:0)|(len>2?b64tab[cccc.charAt(2)]<<6:0)|(len>3?b64tab[cccc.charAt(3)]:0),chars=[fromCharCode(n>>>16),fromCharCode(n>>>8&255),fromCharCode(n&255)];chars.length-=[0,0,2,1][padlen];return chars.join("")};var atob=global.atob?function(a){return global.atob(a)}:function(a){return a.replace(/[\s\S]{1,4}/g,cb_decode)};var _decode=buffer?function(a){return(a.constructor===buffer.constructor?a:new buffer(a,"base64")).toString()}:function(a){return btou(atob(a))};var decode=function(a){return _decode(String(a).replace(/[-_]/g,function(m0){return m0=="-"?"+":"/"}).replace(/[^A-Za-z0-9\+\/]/g,""))};var noConflict=function(){var Base64=global.Base64;global.Base64=_Base64;return Base64};global.Base64={VERSION:version,atob:atob,btoa:btoa,fromBase64:decode,toBase64:encode,utob:utob,encode:encode,encodeURI:encodeURI,btou:btou,decode:decode,noConflict:noConflict};if(typeof Object.defineProperty==="function"){var noEnum=function(v){return{value:v,enumerable:false,writable:true,configurable:true}};global.Base64.extendString=function(){Object.defineProperty(String.prototype,"fromBase64",noEnum(function(){return decode(this)}));Object.defineProperty(String.prototype,"toBase64",noEnum(function(urisafe){return encode(this,urisafe)}));Object.defineProperty(String.prototype,"toBase64URI",noEnum(function(){return encode(this,true)}))}}if(global["Meteor"]){Base64=global.Base64}})(this);
/* Faux Console by Chris Heilmann http://wait-till-i.com */
if(!window.console){var console={init:function(){console.d=document.createElement('div');document.body.appendChild(console.d);var a=document.createElement('a');a.href='javascript:console.hide()';a.innerHTML='close';console.d.appendChild(a);var a=document.createElement('a');a.href='javascript:console.clear();';a.innerHTML='clear';console.d.appendChild(a);var id='fauxconsole';if(!document.getElementById(id)){console.d.id=id;}console.hide();},hide:function(){console.d.style.display='none';},show:function(){console.d.style.display='block';},log:function(o){console.d.innerHTML+='<br/>'+o;console.show();},clear:function(){console.d.parentNode.removeChild(console.d);console.init();console.show();},/*Simon Willison rules*/addLoadEvent:function(func){var oldonload=window.onload;if(typeof window.onload!='function'){window.onload=func;}else{window.onload=function(){if(oldonload){oldonload();}func();}};}};console.addLoadEvent(console.init);}
Date.prototype.format = function (fmt) {var o = {"M+": this.getMonth() + 1,"d+": this.getDate(),"H+": this.getHours(),"m+": this.getMinutes(),"s+": this.getSeconds(), "q+": Math.floor((this.getMonth() + 3) / 3),"S": this.getMilliseconds()};if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));for (var k in o)if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));return fmt;}


