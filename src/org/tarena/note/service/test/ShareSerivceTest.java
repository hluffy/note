package org.tarena.note.service.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.ShareService;

public class ShareSerivceTest {
	
	private ShareService service;
	@Before
	public void init(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		service=ac.getBean("shareService",ShareService.class);
	}
	@Test
	public void test(){
		NoteResult result=service.execute("003ec2a1-f975-4322-8e4d-dfd206d6ac0c");
		Assert.assertEquals(1, result.getStatus());
	}

}
