function getnotelist(){
		$("#pc_part_2").show();
		$("#pc_part_6").hide();
		$("#pc_part_4").hide();
		$("#pc_part_3").show();
		$("#pc_part_5").hide();
		$("#note_list").empty();
		$("#book_list li a").removeClass("checked");
		$(this).find("a").addClass("checked");
		//alert($(this).data("bookId"));
		var bookId=$(this).data("bookId");
		$.ajax({
			url:"http://localhost:8080/note/note/loadnote.do",
			type:"post",
			data:{"bookId":bookId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					var notes=result.data;
					var noteId=null;
					for(var i=0;i<notes.length;i++){
						noteId=notes[i].cn_note_id;
						var noteName=notes[i].cn_note_title;
						var n_li="<li class='online'>"+
							"<a>"+
								"<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>"+noteName+"<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'><i class='fa fa-chevron-down'></i></button>"+
							"</a>"+
							"<div class='note_menu' tabindex=''-1'>"+
								"<dl>"+
									"<dt><button type='button' class='btn btn-default btn-xs btn_move' title='移动至...'><i class='fa fa-random'></i></button></dt>"+
									"<dt><button type='button' class='btn btn-default btn-xs btn_share' title='分享'><i class='fa fa-sitemap'></i></button></dt>"+
									"<dt><button type='button' class='btn btn-default btn-xs btn_delete' title='删除'><i class='fa fa-times'></i></button></dt>"+
								"</dl>"+
							"</div>"+
						"</li>";
						var $li=$(n_li);
						$li.data("noteId",noteId);
						$("#note_list").append($li);
					}
					if(noteId==null){
						$("#input_note_title").val("");
						um.setContent("");
					}
					$("#note_list li").eq(0).click();
				}
			}
		});
}


function shownotebody(){
		var noteId=$(this).data("noteId");
		$("#note_list li a").removeClass("checked");
		$(this).find("a").addClass("checked");
		$.ajax({
			url:"http://localhost:8080/note/note/loadnotebody.do",
			type:"post",
			data:{"noteId":noteId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					var note_title=result.data.cn_note_title;
					var note_body=result.data.cn_note_body;
					$("#input_note_title").val(note_title);
					//$("#myEditor").html(note_body);
					um.setContent(note_body);
				}
			},
			error:function(){
				alert("错了哦");
			}
		});
}


function addnote(){
		var noteTitle=$("#input_note").val().trim();
		var $li=$("#book_list li a.checked").parent();
		var bookId=$li.data("bookId");
		var ok=true;
		if(noteTitle==""){
			alert("笔记名为空");
			ok=false;
		}
		if(ok){
			$.ajax({
				url:"http://localhost:8080/note/note/addnote.do",
				type:"post",
				data:{"userId":id,"noteBookId":bookId,"noteTitle":noteTitle},
				dataType:"json",
				success:function(result){
					if(result.status==1){
						alert(result.msg);
					}
					if(result.status==0){
					 	var noteId=result.data;
						var n_li="<li class='online'>"+
									"<a>"+
									"<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>"+noteTitle+"<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'><i class='fa fa-chevron-down'></i></button>"+
									"</a>"+
									"<div class='note_menu' tabindex=''-1'>"+
						"<dl>"+
							"<dt><button type='button' class='btn btn-default btn-xs btn_move' title='移动至...'><i class='fa fa-random'></i></button></dt>"+
							"<dt><button type='button' class='btn btn-default btn-xs btn_share' title='分享'><i class='fa fa-sitemap'></i></button></dt>"+
							"<dt><button type='button' class='btn btn-default btn-xs btn_delete' title='删除'><i class='fa fa-times'></i></button></dt>"+
						"</dl>"+
					"</div>"+
					"</li>";
					var $li=$(n_li);
					$li.data("noteId",noteId);
					$("#note_list").prepend($li);
					$(".opacity_bg").hide();
					$("#can").empty();
					}
					$("#book_list li a.checked").parent().click();
				},
				error:function(){
					alert("出错了哦");
				}
			});
		}
}


function savenote(){
	  	var noteTitle=$("#input_note_title").val().trim();
	  	var bookId=$("#book_list li a.checked").parent().data("bookId");
		var noteBody=um.getContent();
		var noteId=$("#note_list li a.checked").parent().data("noteId");
		if(noteId==null){
			alert("请添加笔记");
			return;
		}
		$.ajax({
			url:"http://localhost:8080/note/note/update.do",
			type:"post",
			data:{"noteId":noteId,"noteBody":noteBody,"noteTitle":noteTitle,"bookId":bookId},
			dataType:"json",
			success:function(result){
				alert(result.status);
				if(result.status==1){
					alert(result.msg);
				}
				if(result.status==0){
					var str="<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>"+noteTitle+"<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'><i class='fa fa-chevron-down'></i></button>";
					$("#note_list li a.checked").html(str);
					alert(result.msg);
				}
			},
			error:function(){
				alert("保存失败");
			}
		});
}


function deletenote(){
		var noteId=$("#note_list li a.checked").parent().data("noteId");
		$.ajax({
			url:"http://localhost:8080/note/note/delete.do",
			type:"post",
			data:{"noteId":noteId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					$("#note_list li a.checked").parent().remove();
					closewindow();
					$("#note_list li:first").click();
					alert(result.msg);
				}
			},
			error:function(){
				alert("出错了哦");
			}
		});
}


function recycleshow(){
		if($("#pc_part_2").is(":visible")&&$("#pc_part_4").is(":hidden")){
			$("#pc_part_2").hide();
			$("#pc_part_4").show();
			$("#pc_part_3").hide();
			$("#pc_part_5").show();
			$("#pc_part_6").hide();
		
			$.ajax({
				url:"http://localhost:8080/note/note/finddelete.do",
				type:"post",
				data:{"userId":id},
				dataType:"json",
				success:function(result){
					var notes=result.data;
					$("#recycle_list").empty();
					for(var i=0;i<notes.length;i++){
						var recycleId=notes[i].cn_note_id;
						var noteName=notes[i].cn_note_title;
						var li='<li class="disable"><a ><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteName+'<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button><button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay"><i class="fa fa-reply"></i></button></a></li>';
						var $li=$(li);
						$li.data("recycleId",recycleId);
						$("#recycle_list").append($li);
					}
					$("#recycle_list li:first").click();
				},
				error:function(){
					alert("加载失败");
				}
			});
			}else{
				$("#pc_part_2").show();
				$("#pc_part_4").hide();
				$("#pc_part_3").show();
				$("#pc_part_5").hide();
			}
}
function preview(){
		$("#recycle_list li a").removeClass("checked");
		$(this).find("a").addClass("checked");
		var recycleId=$(this).data("recycleId");
		$.ajax({
			url:"http://localhost:8080/note/note/loadnotebody.do",
			type:"post",
			data:{"noteId":recycleId},
			dataType:"json",
			success:function(result){
				var noteTitle=result.data.cn_note_title;
				var noteBody=result.data.cn_note_body;
				if(result.status==0){
					$("#noput_note_title").html(noteTitle);
					$("#noput_note_title").next().html(noteBody);
				}
			},
			error:function(){
				alert("出错了哦");
			}
		});
}


function sharenote(){
		var noteId=$(this).parents("li").data("noteId");
		$.ajax({
			url:"http://localhost:8080/note/note/share.do",
			type:"post",
			data:{"noteId":noteId},
			dataType:"json",
			success:function(result){
					alert(result.msg);
					$(".note_menu").hide();
			},
			error:function(){
				alert("出错了哦");
			}
		});
}



function movenote(){
		var bookId=$("#moveSelect option:selected").data("optionId");
		var bookName=$("#moveSelect option:selected").text();
		if(bookName=="- 请选择 -"){
			return;
		}
		var noteId=$("#note_list li a.checked").parent().data("noteId");
		$.ajax({
			url:"http://localhost:8080/note/note/movenote.do",
			type:"post",
			data:{"noteId":noteId,"bookId":bookId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					$("#note_list li a.checked").parent().remove();
					$(".note_menu").hide();
					alert(result.msg);
				}
			},
			error:function(){
				alert("出错了");
			}
		});
}

function deleterollback(){
		var recycleId=$("#recycle_list li a.checked").parents("li").data("recycleId");
		$.ajax({
			url:"http://localhost:8080/note/note/deletenote.do",
			type:"post",
			data:{"noteId":recycleId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					$("#recycle_list li a.checked").parent().remove();
					closewindow();
					alert(result.msg);
					$("#recycle_list li:first").click();
				}
			},
			error:function(){
				alert("出错了");
			}
		});
}

function recover(){
		var bookId=$("#replaySelect option:selected").data("bookId");
		var noteId=$("#recycle_list li a.checked").parent().data("recycleId");
		$.ajax({
			url:"http://localhost:8080/note/note/recover.do",
			type:"post",
			data:{"noteId":noteId,"bookId":bookId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					$("#recycle_list li a.checked").parent().remove();
					closewindow();
					alert(result.msg);
					$("#recycle_list li:first").click();
				}
			},
			error:function(){
				alert("出错了");
			}
		});
}

function searchshow(page){
		
		
			var title=$("#search_note").val().trim();
			$.ajax({
				url:"http://localhost:8080/note/note/search.do",
				type:"post",
				data:{"title":title,"page":page},
				dataType:"json",
				success:function(result){
					if(result.status==0){
						$("#pc_part_2").hide();
						$("#pc_part_6").show();
						$("#pc_part_3").hide();
						$("#pc_part_5").show();
						$("#pc_part_4").hide();
						$("#search_list").empty();
						var titles=result.data;
						for(var i=0;i<titles.length;i++){
							var title=titles[i].cn_share_title;
							var shareId=titles[i].cn_share_id;
							var str="<li class='online'>"+
								"<a>"+
								"<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>"+title+"<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'><i class='fa fa-chevron-down'></i></button>"+
								"</a>"+
								"<div class='note_menu' tabindex=''-1'>"+
								"<dl>"+
								"<dt><button type='button' class='btn btn-default btn-xs btn_move' title='收藏...'><i class='fa fa-random'></i></button></dt>"+
								"</dl>"+
								"</div>"+
								"</li>";
							var $li=$(str);
							$li.data("shareId",shareId);
							$("#search_list").append($li);
							$("#search_list li:first").click();
						}
					}
				},
				error:function(){
					alert("出错了");
				}
			});
}
