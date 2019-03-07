var xhr;

function creatXHR() {
	if(window.XMLHttpRequest) {
		//非IE内核
		return new XMLHttpRequest();

	} else {
		//IE内核
		return new ActiveXObject('Microsoft.XMLHTTP');

	}
}
document.getElementById('btnLogin').addEventListener('click', function() {

	var userName = document.getElementById('user').value;
	var psd = document.getElementById('password').value;

	xhr = creatXHR();
	xhr.open('GET', '../js/user.php?username=' + userName + "&password=" + psd, true);
	xhr.onreadystatechange = callback;
	xhr.send(null);

});

function callback() {

	if(xhr.readyState == 4) {

		if(xhr.status == 200) {
			/*var data = xhr.responseText;
			console.log(data);

			if(data == 1) {
				document.getElementById('showinfo').innerHTML = "用户名或密码错误";

			} else if(data == 2) {
				document.getElementById('showinfo').innerHTML = "登录成功";

			}*/

			window.location.assign("http://www.baidu.com.cn");
		}

	}

}