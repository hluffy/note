package org.tarena.note.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.UserService;
@Controller//扫描
@RequestMapping("/user")
public class UserLoginController {
	@Resource//注入
	private UserService service;
	@RequestMapping("/login.do")
	@ResponseBody//将返回结果变成json输出
	public NoteResult execute(String name,String pwd){
		//接受请求调用Service业务组件处理
		NoteResult result=service.checkLogin(name, pwd);
		return result;
	}
	
}
