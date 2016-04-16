package org.tarena.note.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteService;
import org.tarena.note.service.ShareService;
@Controller
@RequestMapping("/note")
public class NoteController {
	@Resource
	private NoteService service;
	@Resource
	private ShareService shareService;
	@RequestMapping("/loadnote.do")
	@ResponseBody
	public NoteResult loadNote(String bookId){
		NoteResult result=service.execute(bookId);
		return result;
	}
	
	
	
	@RequestMapping("/loadnotebody.do")
	@ResponseBody
	public NoteResult loadNoteBody(String noteId){
		NoteResult result=service.noteexecute(noteId);
		return result;
	}
	
	@RequestMapping("/addnote.do")
	@ResponseBody
	public NoteResult save(String userId,String noteBookId,String noteTitle){
		NoteResult result=service.add(userId, noteBookId, noteTitle);
		return result;
	}
	
	@RequestMapping("/update.do")
	@ResponseBody
	public NoteResult saveNoteBody(String noteId,String noteBody,String noteTitle,String bookId){
		NoteResult result=service.update(noteId, noteBody,noteTitle,bookId);
		return result;
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public NoteResult delete(String noteId){
		NoteResult result=service.updateStatus(noteId);
		return result;
	}
	
	@RequestMapping("/finddelete.do")
	@ResponseBody
	public NoteResult loadDelete(String userId){
		NoteResult result=service.findDelete(userId);
		return result;
	}
	
	@RequestMapping("/share.do")
	@ResponseBody
	public NoteResult shareNote(String noteId){
		NoteResult result=shareService.execute(noteId);
		return result;
	}
	
	@RequestMapping("/movenote.do")
	@ResponseBody
	public NoteResult moveNote(String noteId,String bookId){
		NoteResult result=service.updateById(noteId, bookId);
		return result;
	}
	
	
	@RequestMapping("/deletenote.do")
	@ResponseBody
	public NoteResult deleteNote(String noteId){
		NoteResult result=service.deleteNote(noteId);
		return result;
	}
	
	@RequestMapping("/recover.do")
	@ResponseBody
	public NoteResult recover(String noteId,String bookId){
		NoteResult result=service.updateBookId(noteId, bookId);
		return result;
	}

}
