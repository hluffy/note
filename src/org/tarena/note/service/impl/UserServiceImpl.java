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
			result.setStatus(1);//1��ʾ����ȷ
			result.setMsg("�û���������");
			return result;
		}
		//���û������������ܴ���
		String md5_pwd=NoteUtil.md5(pwd);
		//�����ܺ����������ݿ��м��ܵĽ���Ա�
		if(!user.getCn_user_password().equals(md5_pwd)){
			result.setStatus(2);
			result.setMsg("���벻��ȷ");
			return result;
		}
		result.setStatus(0);
		result.setMsg("��¼�ɹ�");
		result.setData(user.getCn_user_id());
		return result;
	}

	public NoteResult regist(String name, String pwd, String nickname) {
		NoteResult result=new NoteResult();
		//����û����Ƿ�Ψһ
		User has_user=dao.findByName(name);
		if(has_user!=null){
			result.setStatus(1);
			result.setMsg("�û����ѱ�ռ��");
			return result;
		}
		//����
		User user=new User();
		user.setCn_user_name(name);
		user.setCn_user_password(NoteUtil.md5(pwd));
		user.setCn_user_desc(nickname);
		user.setCn_user_id(NoteUtil.createId());
		int i=dao.save(user);
		//if(i!=-1){
			result.setStatus(0);
			result.setMsg("ע��ɹ�");
			return result;
		//}
		//return null;
	}

}
