package org.tarena.note.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tarena.note.dao.UserMapperDao;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.User;
import org.tarena.note.service.UserService;
import org.tarena.note.util.NoteUtil;
@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource
	private UserMapperDao dao;

	public NoteResult checkLogin(String name, String pwd) {
		NoteResult result=new NoteResult();
		User user=dao.findByName(name);
//		String str=null;
//		str.length();
		if(user==null){
			result.setStatus(1);//1表示不正确
			result.setMsg("用户名不存在");
			return result;
		}
		//将用户输入的密码加密处理
		String md5_pwd=NoteUtil.md5(pwd);
		//将加密后的密码和数据库中加密的结果对比
		if(!user.getCn_user_password().equals(md5_pwd)){
			result.setStatus(2);
			result.setMsg("密码不正确");
			return result;
		}
		result.setStatus(0);
		result.setMsg("登录成功");
		result.setData(user.getCn_user_id());
		return result;
	}

	public NoteResult regist(String name, String pwd, String nickname) {
		NoteResult result=new NoteResult();
		//检测用户名是否唯一
		User has_user=dao.findByName(name);
		if(has_user!=null){
			result.setStatus(1);
			result.setMsg("用户名已被占用");
			return result;
		}
		//保存
		User user=new User();
		user.setCn_user_name(name);
		user.setCn_user_password(NoteUtil.md5(pwd));
		user.setCn_user_desc(nickname);
		user.setCn_user_id(NoteUtil.createId());
		int i=dao.save(user);
		//if(i!=-1){
			result.setStatus(0);
			result.setMsg("注册成功");
			return result;
		//}
		//return null;
	}

}
