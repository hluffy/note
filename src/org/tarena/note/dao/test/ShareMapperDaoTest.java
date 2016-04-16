package org.tarena.note.dao.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.ShareMapperDao;
import org.tarena.note.entity.Share;

public class ShareMapperDaoTest {
	private ShareMapperDao dao;
	@Before
	public void init(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		dao=ac.getBean("shareMapperDao",ShareMapperDao.class);
	}
	@Test
	public void test(){
		Share share=new Share();
		share.setCn_share_id("sdfsafd");
		int i=dao.insertShare(share);
		System.out.println(i);
	}
	@Test
	public void test1(){
		List list=dao.findShareNote(null);
		System.out.println(list);
	}

}
