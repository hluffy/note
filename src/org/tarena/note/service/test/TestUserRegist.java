package org.tarena.note.service.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.UserService;

public class TestUserRegist {
	private UserService service;
	private AbstractApplicationContext ac;
	@Before
	public void init(){
		String conf="applicationContext.xml";
		ac=new ClassPathXmlApplicationContext(conf);
		service=ac.getBean("userService",UserService.class);
	}
	@Test
	public void test1(){
		NoteResult result=service.regist("demo", "sdf", "sdf");
		Assert.assertEquals(1, result.getStatus());
		Assert.assertEquals("�û����ѱ�ռ��", result.getMsg());
	}
	@Test
	public void test2(){
		NoteResult result=service.regist("sdfsa","sdfs", "sdfs");
		Assert.assertEquals(0, result.getStatus());
		Assert.assertEquals("ע��ɹ�", result.getMsg());
	}
	@After
	public void destroy(){
		service=null;
		ac.close();
	}

}
