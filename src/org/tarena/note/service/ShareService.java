package org.tarena.note.service;

import org.tarena.note.entity.NoteResult;

public interface ShareService {
	public NoteResult execute(String noteId);
	public NoteResult findShareNote(String title,String page);

}
