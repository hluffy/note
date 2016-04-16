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
	//����e��������Ŀ�귽���׳����쳣����
	@AfterThrowing(pointcut="within(org.tarena.note.controller..*)",throwing="e")
	public void logException(Exception e) throws IOException{
		//��¼�쳣��Ϣ
		System.out.println("�������쳣:"+e);
		FileWriter fw=new FileWriter("E:\\error.log",true);
		PrintWriter out=new PrintWriter(fw);
		out.println("===============");
		e.printStackTrace(out);//���쳣ջ��Ϣд��error.log
		out.flush();
		out.close();
		fw.close();
	}

}
