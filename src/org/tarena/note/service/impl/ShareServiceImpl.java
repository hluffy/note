package org.tarena.note.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.dao.ShareMapperDao;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.SearchBean;
import org.tarena.note.entity.Share;
import org.tarena.note.service.ShareService;
import org.tarena.note.util.NoteUtil;
@Service("shareService")
public class ShareServiceImpl implements ShareService{
	@Resource
	private ShareMapperDao dao;
	@Resource
	private NoteMapperDao noteDao;

	public NoteResult execute(String noteId) {
		NoteResult result=new NoteResult();
		Share s=dao.findById(noteId);
		if(s!=null){
			result.setStatus(1);
			result.setMsg("已经分享过了哦");
			return result;
		}
		String shareId=NoteUtil.createId();
		Note note=noteDao.findByNoteId(noteId);
		
		Share share=new Share();
		share.setCn_note_id(noteId);
		share.setCn_share_id(shareId);
		
		
		share.setCn_share_title(note.getCn_note_title());
		share.setCn_share_body(note.getCn_note_body());
		
		dao.insertShare(share);
		result.setStatus(0);
		result.setMsg("分享成功");
		return result;
	}

	public NoteResult findShareNote(String title,String page) {
		NoteResult result=new NoteResult();
		if(title==null||"".equals(title.trim())){
			title="%";
		}else{
			title="%"+title+"%";
		}
		SearchBean bean=new SearchBean();
		int begin=(Integer.parseInt(page)-1)*10;
		bean.setBegin(begin);
		bean.setTitle(title);
		List list=dao.findShareNote(bean);
		result.setData(list);
		result.setStatus(0);
		result.setMsg("查询成功");
		return result;
	}

}
