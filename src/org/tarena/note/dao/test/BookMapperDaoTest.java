package org.tarena.note.dao.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.BookMapperDao;
import org.tarena.note.entity.Book;
import org.tarena.note.entity.Note;

public class BookMapperDaoTest {
	private BookMapperDao dao;
	@Before
	public void init(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		dao=ac.getBean("bookMapperDao",BookMapperDao.class);
	}
	@Test
	public void test(){
		Book book=dao.findById("6d763ac9-dca3-42d7-a2a7-a08053095c08");
		List<Note> list=book.getNotes();
		for(Note note:list){
			System.out.println(note.getCn_note_title());
		}
	}

}
