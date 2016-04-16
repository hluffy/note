package org.tarena.note.aopbean;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component//1.ɨ�赽Spring����
@Aspect//2.�������ָ��Ϊ�������
public class LogControllerBean {
	//3.ָ������Ŀ���ʵ��
	//��ִ��Controller����ǰִ�������߼�
	@Before("within(org.tarena.note.controller..*)")
	public void logController(){
		System.out.println("ִ�е���Controller");
	}
	//�ڵ���Dao�������ǰִ�������߼�
	@Before("bean(*Dao)")
	public void logDao(){
		System.out.println("ִ�е���Dao");
	}

}
