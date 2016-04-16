function getbooklist(){
	$.ajax({
		url:"http://localhost:8080/note/notebook/loadbooks.do",
		type:"post",
		data:{"id":id},
		dataType:"json",
		//async:"false",
		success:function(result){
			if(result.status==0){
				var books=result.data;
				for(var i=0;i<books.length;i++){
					var book_name=books[i].cn_notebook_name;
					var bookId=books[i].cn_notebook_id;
					var s_li="<li class='online'><a><i class='fa fa-book' title='online' rel='tooltip-bottom'></i>"+book_name+"</a></li>";
					var $li=$(s_li);//将str对象转换成为JQuery对象
					$li.data("bookId",bookId);//将book_id绑定到li上
					$("#book_list").append($li);
				}
				$("#book_list li").eq(0).click();
			}
		},
		error:function(){
			alert("未知错误");
		}
	});
}




function addnotebook(){
	//显示背景色
	$(".opacity_bg").show();
	//显示添加框
	$("#can").load("alert/alert_notebook.html");
}


function closewindow(){
	//取消添加对话框
	$("#can").empty();
	//隐藏背景色
	$(".opacity_bg").hide();
}

function insertnotebook(){
		var bookName=$("#input_notebook").val().trim();
		var ok=true;
		if(bookName==""){
			alert("笔记名为空");
			ok=false;
		}
		if(ok){
			$.ajax({
			 	url:"http://localhost:8080/note/notebook/add.do",
			 	type:"post",
				data:{"userId":id,"bookName":bookName},
				dataType:"json",
				success:function(result){
					var bookId=result.data;
					if(result.status==0){
						var s_li="<li class='online'><a><i class='fa fa-book' title='online' rel='tooltip-bottom'></i>"+bookName+"</a></li>";
						var $li=$(s_li);
						$li.data("bookId",bookId);
						$("#book_list").prepend($li);
						$(".opacity_bg").hide();
						$("#can").empty();
					}
					if(result.status==1){
						alert(result.msg);
					}
				}
			}); 
		}
		 
}

function movenotebookshow(){
	$(".opacity_bg").show();
	$("#can").load("alert/alert_move.html");
	var activename=$("#book_list li a.checked").text();
	$.ajax({
		url:"http://localhost:8080/note/notebook/findbookname.do",
		type:"post",
		data:{"userId":id},
		dataType:"json",
		success:function(result){
			var books=result.data;
			for(var i=0;i<books.length;i++){
				var bookId=books[i].cn_notebook_id;
				var bookName=books[i].cn_notebook_name;
				if(activename==bookName){
					continue;
				}
				var str="<option value='none'>"+bookName+"</option>";
				var $option=$(str);
				$option.data("optionId",bookId);
				$("#moveSelect").append($option);
			}
		},
		error:function(){
			alert("出错了");
		}
	});
}

function findmovebookname(){
		$(".opacity_bg").show();
		$("#can").load("alert/alert_replay.html");
		$.ajax({
			url:"http://localhost:8080/note/notebook/findbookname.do",
			type:"post",
			data:{"userId":id},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					var books=result.data;
					for(var i=0;i<books.length;i++){
						var bookName=books[i].cn_notebook_name;
						var bookId=books[i].cn_notebook_id;
						var str="<option>"+bookName+"</option>";
						var $option=$(str);
						$option.data("bookId",bookId);
						$("#replaySelect").append($option);
					}
				}
			},
			error:function(){
				alert("出错了");
			}
		});
}