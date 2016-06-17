$(function(){
	var user=Util.paramObj("u");
	if(user.user!=null){
		$("#user").val(user.user);
		$("#pass").val(user.pass);
		$("#login").trigger('click');
	}
});

function login(user){
	window.location.href="./login.html?u="+Util.encode({user:user,pass:user});
}