package org.tarena.note.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.UserService;
@Controller//É¨Ãè
@RequestMapping("/user")
public class UserRegistController {
	@Resource//×¢Èë
	private UserService service;
	@RequestMapping("/regist.do")
	@ResponseBody
	public NoteResult execute(String name,String pwd,String nickname){
		NoteResult result=service.regist(name, pwd, nickname);
		return result;
	}

}
