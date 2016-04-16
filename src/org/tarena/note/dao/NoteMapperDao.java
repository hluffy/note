package org.tarena.note.dao;

import java.util.List;
import java.util.Map;

import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteBean;

public interface NoteMapperDao {
	public List<Map<String,Object>> findByBookId(String bookId);
	public Note findByNoteId(String noteId);
	public int insertNote(Note note);
	public Note findByIdAndTitle(Note note);
	public int updateNoteBody(Note note);
	public Note findNoteByIdAndTitle(Note note);
	public int updateStatus(Note note);
	public List<Note> findHuiShouZhan(String userId);
	public int updateById(Note note);
	public int deleteNote(String noteId);
	public int updateBookId(Note note);
	public List<Note> findNotes(NoteBean bean);
	public int updateNote(Note note);
	public int deleteNotes(String[] args);
	public List<Note> findAll();

}
