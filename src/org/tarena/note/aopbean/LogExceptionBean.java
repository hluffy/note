package org.tarena.note.aopbean;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogExceptionBean {
	//采用e参数接受目标方法抛出的异常对象
	@AfterThrowing(pointcut="within(org.tarena.note.controller..*)",throwing="e")
	public void logException(Exception e) throws IOException{
		//记录异常信息
		System.out.println("发生了异常:"+e);
		FileWriter fw=new FileWriter("E:\\error.log",true);
		PrintWriter out=new PrintWriter(fw);
		out.println("===============");
		e.printStackTrace(out);//将异常栈信息写入error.log
		out.flush();
		out.close();
		fw.close();
	}

}
