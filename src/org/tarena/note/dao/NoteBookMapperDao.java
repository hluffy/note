package org.tarena.note.dao;

import java.util.List;

import org.tarena.note.entity.NoteBook;

public interface NoteBookMapperDao {
	public List<NoteBook> findById(String id);
	public int save(NoteBook book);
	public NoteBook findByIdAndName(NoteBook book);
	public List<NoteBook> findByUserId(String userId);

}
