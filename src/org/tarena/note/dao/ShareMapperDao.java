package org.tarena.note.dao;

import java.util.List;
import java.util.Map;

import org.tarena.note.entity.SearchBean;
import org.tarena.note.entity.Share;

public interface ShareMapperDao {
	public int insertShare(Share share);
	public Share findById(String noteId);
	public List<Map<String,Object>> findShareNote(SearchBean bean);

}
