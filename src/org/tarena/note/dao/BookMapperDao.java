package org.tarena.note.dao;

import org.tarena.note.entity.Book;

public interface BookMapperDao {
	public Book findById(String bookId);

}
