package org.tarena.note.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.UserService;
@Controller//ɨ��
@RequestMapping("/user")
public class UserLoginController {
	@Resource//ע��
	private UserService service;
	@RequestMapping("/login.do")
	@ResponseBody//�����ؽ�����json���
	public NoteResult execute(String name,String pwd){
		//�����������Serviceҵ���������
		NoteResult result=service.checkLogin(name, pwd);
		return result;
	}
	
}
