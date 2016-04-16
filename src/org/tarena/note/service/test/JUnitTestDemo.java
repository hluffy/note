package org.tarena.note.service.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitTestDemo {
	@BeforeClass//在测试类执行开始时只调用一次
	public static void mybefore(){
		System.out.println("--mybefore--");
	}
	@AfterClass
	public static void myafter(){
		System.out.println("--myafter--");
	}
	@Before//每个test方法执行前调用一次
	public void myinit(){
		System.out.println("--myinit--");
	}
	@After//每个test方法执行后调用一次
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
