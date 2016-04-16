package org.tarena.note.service.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitTestDemo {
	@BeforeClass//�ڲ�����ִ�п�ʼʱֻ����һ��
	public static void mybefore(){
		System.out.println("--mybefore--");
	}
	@AfterClass
	public static void myafter(){
		System.out.println("--myafter--");
	}
	@Before//ÿ��test����ִ��ǰ����һ��
	public void myinit(){
		System.out.println("--myinit--");
	}
	@After//ÿ��test����ִ�к����һ��
	public void mydestroy(){
		System.out.println("--mydestroy--");
	}
	@Test
	public void f1(){
		System.out.println("--f1--");
		Assert.assertEquals(1, 1);
		Assert.assertNull(null);
		Assert.assertNotNull(1);
		Assert.assertFalse(false);
		Assert.assertTrue(true);
		Assert.assertSame(1, 1);
	}
	@Test
	public void f2(){
		System.out.println("--f2--");
	}

}
