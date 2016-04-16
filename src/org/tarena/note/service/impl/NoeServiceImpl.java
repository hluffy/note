package org.tarena.note.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteService;
import org.tarena.note.util.NoteUtil;
@Service("noteService")
public class NoeServiceImpl implements NoteService{
	@Resource
	private NoteMapperDao dao;

	public NoteResult execute(String bookId) {
		NoteResult result=new NoteResult();
		List<Map<String,Object>> list=dao.findByBookId(bookId);
		result.setData(list);
		result.setStatus(0);
		result.setMsg("查询成功");
		return result;
	}

	public NoteResult noteexecute(String noteId) {
		NoteResult result=new NoteResult();
		Note note=dao.findByNoteId(noteId);
		result.setStatus(0);
		result.setMsg("查询成功");
		result.setData(note);
		return result;
	}

	public NoteResult add(String userId, String noteBookId, String noteTitle) {
		NoteResult result=new NoteResult();
		String noteId=NoteUtil.createId();
		Note note=new Note();
		note.setCn_user_id(userId);
		note.setCn_notebook_id(noteBookId);
		note.setCn_note_title(noteTitle);
		Note n=dao.findByIdAndTitle(note);
		if(n!=null){
			result.setStatus(1);
			result.setMsg("笔记名已存在");
			return result;
		}
		note.setCn_note_id(noteId);
		note.setCn_note_body("");
		note.setCn_note_create_time(System.currentTimeMillis());
		note.setCn_note_status_id("1");
		note.setCn_note_type_id("1");
		dao.insertNote(note);
		result.setStatus(0);
		result.setMsg("笔记添加成功");
		result.setData(noteId);
		return result;
	}

	public NoteResult update(String noteId, String noteBody,String noteTitle,String bookId) {
		NoteResult result=new NoteResult();
		Note note=new Note();
		note.setCn_notebook_id(bookId);
		note.setCn_note_title(noteTitle);
		Note n=dao.findNoteByIdAndTitle(note);
		if(n!=null&&(!noteId.equals(n.getCn_note_id()))){
			result.setStatus(1);
			result.setMsg("笔记名已存在");
			return result;
		}
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		note.setCn_note_id(noteId);
		note.setCn_note_body(noteBody);
		dao.updateNoteBody(note);
		result.setStatus(0);
		result.setMsg("保存成功");
		return result;
	}

	public NoteResult updateStatus(String noteId) {
		NoteResult result=new NoteResult();
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		dao.updateStatus(note);
		result.setStatus(0);
		result.setMsg("删除成功");
		return result;
	}

	public NoteResult findDelete(String userId) {
		NoteResult result=new NoteResult();
		List<Note> list=dao.findHuiShouZhan(userId);
		result.setStatus(0);
		result.setMsg("查询成功");
		result.setData(list);
		return result;
	}

	public NoteResult updateById(String noteId,String bookId) {
		NoteResult result=new NoteResult();
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_notebook_id(bookId);
		note.setCn_note_create_time(System.currentTimeMillis());
		dao.updateById(note);
		result.setStatus(0);
		result.setMsg("移动完成");
		return result;
	}

	public NoteResult deleteNote(String noteId) {
		NoteResult result=new NoteResult();
		dao.deleteNote(noteId);
		result.setStatus(0);
		result.setMsg("删除成功");
		return result;
	}

	public NoteResult updateBookId(String noteId, String bookId) {
		NoteResult result=new NoteResult();
		Note note=new Note();
		note.setCn_note_create_time(System.currentTimeMillis());
		note.setCn_note_id(noteId);
		note.setCn_notebook_id(bookId);
		dao.updateBookId(note);
		result.setStatus(0);
		result.setMsg("恢复成功");
		return result;
	}
	
	

}
