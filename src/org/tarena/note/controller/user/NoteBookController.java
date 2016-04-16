package org.tarena.note.controller.user;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteBookService;
@Controller
@RequestMapping("notebook")
public class NoteBookController {
	@Resource
	private NoteBookService service;
	@RequestMapping("loadbooks.do")
	@ResponseBody
	public NoteResult execute(String id){
		NoteResult result=service.execute(id);
		return result;
	}
	
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult add(String userId,String bookName){
		NoteResult result=service.add(userId, bookName);
		return result;
	}
	
	@RequestMapping("/findbookname.do")
	@ResponseBody
	public NoteResult findByUserId(String userId){
		NoteResult result=service.findBookName(userId);
		return result;
	}

}
