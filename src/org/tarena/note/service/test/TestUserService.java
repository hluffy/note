package org.tarena.note.service.test;


import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.UserService;

public class TestUserService {
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
		NoteResult result=service.checkLogin("tom","123");
		Assert.assertEquals(1,result.getStatus());
		Assert.assertEquals("�û���������",result.getMsg());
		Assert.assertNull(result.getData());
	}
	@Test
	public void test2(){
		NoteResult result=service.checkLogin("demo", "111");
		Assert.assertEquals(2, result.getStatus());
		Assert.assertEquals("���벻��ȷ", result.getMsg());
		Assert.assertNull(result.getData());
	}
	@Test
	public void test3(){
		NoteResult result=service.checkLogin("demo","1234");
		Assert.assertEquals(0, result.getStatus());
		Assert.assertEquals("��¼�ɹ�", result.getMsg());
	}
	@Test
	public void test4(){
		NoteResult result=service.regist("demo","1234","null");
		Assert.assertEquals(1, result.getStatus());
		Assert.assertEquals("�û����ѱ�ռ��", result.getMsg());
	}
	@Test
	public void test5(){
		NoteResult result=service.regist("hahahaha", "123456789", "hahaha");
		Assert.assertEquals(0, result.getStatus());
		Assert.assertEquals("ע��ɹ�", result.getMsg());
	}
	
	@After
	public void end(){
		service=null;
		ac.close();
	}
	
		
}
