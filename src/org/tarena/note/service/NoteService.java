package org.tarena.note.service;

import org.tarena.note.entity.NoteResult;

public interface NoteService {
	public NoteResult execute(String bookId);
	public NoteResult noteexecute(String noteId);
	public NoteResult add(String userId,String noteBookId,String noteTitle);
	public NoteResult update(String noteId,String noteBody,String noteTitle,String bookId);
	public NoteResult updateStatus(String noteId);
	public NoteResult findDelete(String userId);
	public NoteResult updateById(String noteId,String bookId);
	public NoteResult deleteNote(String noteId);
	public NoteResult updateBookId(String noteId,String bookId);

}
