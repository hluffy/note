package org.tarena.note.dao.test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteBook;

public class NoteMapperDaoTest {
	private NoteMapperDao dao;
	@Before
	public void init(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		dao=ac.getBean("noteMapperDao",NoteMapperDao.class);
	}
	@Test
	public void test(){
		List<Map<String,Object>> list=dao.findByBookId("6d763ac9-dca3-42d7-a2a7-a08053095c08");
		for(Map<String,Object> map:list){
			System.out.println(map.get("cn_note_id"));
		}
	}
	@Test
	public void test1(){
		Note note=dao.findByNoteId("046b0110-67f9-48c3-bef3-b0b23bda9d4e");
		System.out.println(note.getCn_note_body());
	}
	@Test
	public void test2(){
		String[] args={"ss19055-30e8-4cdc-bfac-97c6bad9518f","fsaf-as-df-asdf-as-df-dsa","ffc2cf21-78ed-4647-adb4-3e545613ef26"};
		int i=dao.deleteNotes(args);
		System.out.println(i);
	}
	@Test
	public void test3(){
		List<Note> list=dao.findAll();
		for(Note note:list){
			NoteBook book=note.getBook();
			System.out.println(note.getCn_note_title()+"   "+book.getCn_notebook_name());
		}
	}

}
