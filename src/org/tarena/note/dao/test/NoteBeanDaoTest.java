package org.tarena.note.dao.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteBean;

public class NoteBeanDaoTest {
	private NoteMapperDao dao;
	@Before
	public void init(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		dao=ac.getBean("noteMapperDao",NoteMapperDao.class);
	}
	@Test
	public void test(){
		NoteBean bean=new NoteBean();
		bean.setStatus("0");
		//bean.setTitle("22");
		//Date date=new Date();
		//bean.setEnddate(date.getTime());
		bean.setBegindate(1444893474693l);
		List<Note> list=dao.findNotes(bean);
		for(Note note:list){
			System.out.println(note.getCn_note_title());
		}
		System.out.println(list.size());
	}
	@Test
	public void test2(){
		Note note=new Note();
		note.setCn_note_id("003ec2a1-f975-4322-8e4d-dfd206d6ac0c");
		note.setCn_note_body("44444");
		int i=dao.updateNote(note);
		System.out.println(i);
	}

}
