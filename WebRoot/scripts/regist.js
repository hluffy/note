$(function() {
	// 获取要提交的请求数据
	$("#regist_button").click(function() {
		$("#warning_1").hide();
		$("#warning_2").hide();
		$("#warning_3").hide();
		$("#warning_4").hide();
		var name = $("#regist_username").val().trim();
		var pwd = $("#regist_password").val().trim();
		var nickname = $("#nickname").val().trim();
		var repwd = $("#final_password").val().trim();
		// TODO检查数据格式
		var ok = true;
		if (name == "") {
			ok = false;
			$("#warning_1").show();
			$("#warning_1 sapn").html("用户名不能为空");
		}
		if (nickname == "") {
			ok = false;
			$("#warning_4").show();
		}
		if (pwd.length < 4) {
			$("#warning_2").show();
			ok = false;
		}
		if (pwd != repwd) {
			$("#warning_3").show();
			ok = false;
		}
		// 没问题发送ajax请求
		if (ok) {
			$.ajax({
				url : "http://localhost:8080/note/user/regist.do",
				type : "post",
				data : {
					"name" : name,
					"pwd" : pwd,
					"nickname" : nickname
				},
				dataType : "json",
				success : function(result) {
					// 成功，提示然后跳转到登录界面
					if (result.status == 0) {
						alert(result.msg);
						// 触发"返回"按钮的单击处理
						$("#back").click();
					} else if (result.status == 1) {
						// 用户名重复
						$("#warning_1").show();
						$("#warning_1 span").html(result.msg);
					}
				},
				error : function() {
					alert("注册发生异常");
				}
			});
		}

	});
});