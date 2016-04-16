$(function(){
			$("#login").click(function(){
				//清空先前span的提示信息
				$("#count_msg").html("");
				$("#password_msg").html("");
				//获取用户名和密码
				var name=$("#count").val().trim();
				var password=$("#password").val().trim();
				//数据格式检查
				var ok=true;//是否通过检查
				if(name==""){
					$("#count_msg").html("用户名为空");
					ok=false;
				}
				if(password==""){
					$("#password_msg").html("密码为空");
					ok=false;
				}
				
				//发送Ajax请求
				if(ok){
					$.ajax({
						url:"http://localhost:8080/note/user/login.do",
						type:"post",
						data:{"name":name,"pwd":password},
						dataType:"json",
						success:function(result){
							if(result.status==0){
								//获取登录者ID
								var userId=result.data;
								//放入Cookie
								addCookie("userId",userId,2);
								window.location.href="edit.html";//成功
							}else if(result.status==1){
								$("#count_msg").html(result.msg);
							}else if(result.status==2){
								$("#password_msg").html(result.msg);
							}
						},
						error:function(){
							alert("登录发生异常");
						}
					});
				}
			});
		});
		
		
		
		
		/* $(function(){
			$("#regist_username").blur(function(){
				var name=$("#regist_username").val().trim();
				var pwd="";
				var nickname="";
				$.ajax({
					url:"http://localhost:8080/note/user/regist.do",
					type:"post",
					data:{"name":name,"pwd":pwd,"nickname":nickname},
					dataType:"json",
					success:function(result){
						if(result.status==0){
							window.location.href="http://localhost:8080/note/log_in.html";
						}
						if(result.status==1){
							$("#warning_1").css("display","inline");
						}
					}
				});
			}); 
		});*/