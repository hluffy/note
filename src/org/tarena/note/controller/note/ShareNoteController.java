package org.tarena.note.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.ShareService;
@Controller
@RequestMapping("/note")
public class ShareNoteController {
	@Resource
	private ShareService service;
	@RequestMapping("/search.do")
	@ResponseBody
	public NoteResult search(String title,String page){
		NoteResult result=service.findShareNote(title,page);
		return result;
	}

}
