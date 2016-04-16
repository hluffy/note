package org.tarena.note.service;

import org.tarena.note.entity.NoteResult;

public interface UserService {
	public NoteResult checkLogin(String name,String pwd);
	public NoteResult regist(String name,String pwd,String nickname);

}
