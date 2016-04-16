package org.tarena.note.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tarena.note.dao.NoteBookMapperDao;
import org.tarena.note.entity.NoteBook;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteBookService;
import org.tarena.note.util.NoteUtil;
@Service("noteBookService")
public class NoteBookServiceImpl implements NoteBookService{
	@Resource
	private NoteBookMapperDao dao;

	public NoteResult execute(String id) {
		NoteResult result=new NoteResult();
		List<NoteBook> list=dao.findById(id);
		result.setData(list);
		result.setStatus(0);
		result.setMsg("成功");
		return result;
	}

	public NoteResult add(String userId, String bookName) {
		NoteResult result=new NoteResult();
		NoteBook book=new NoteBook();
		book.setCn_user_id(userId);
		book.setCn_notebook_name(bookName);
		NoteBook notebook=dao.findByIdAndName(book);
		if(notebook!=null){
			result.setStatus(1);
			result.setMsg("笔记本名已存在");
			return result;
		}
		book.setCn_notebook_id(NoteUtil.createId());
		book.setCn_notebook_desc("");
		book.setCn_notebook_type_id("5");
		Timestamp time=new Timestamp(System.currentTimeMillis());
		book.setCn_notebook_createtime(time);
		dao.save(book);
		result.setStatus(0);
		result.setMsg("添加笔记本成功");
		result.setData(book.getCn_notebook_id());
		return result;
	}

	public NoteResult findBookName(String userId) {
		NoteResult result=new NoteResult();
		List<NoteBook> list=dao.findByUserId(userId);
		result.setStatus(0);
		result.setMsg("查询成功");
		result.setData(list);
		return result;
	}

}
