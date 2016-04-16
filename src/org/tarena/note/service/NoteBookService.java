package org.tarena.note.service;

import org.tarena.note.entity.NoteResult;

public interface NoteBookService {
	public NoteResult execute(String id);
	public NoteResult add(String userId,String bookName);
	public NoteResult findBookName(String userId);

}
