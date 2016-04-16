package org.tarena.note.entity;

public class NoteBean {
	private String title;
	private String status;
	private Long begindate;
	private Long enddate;
	public String getTitle() {
		if(title!=null){
			return "%"+title+"%";
		}else{
			return null;
		}
		
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getBegindate() {
		return begindate;
	}
	public void setBegindate(Long begindate) {
		this.begindate = begindate;
	}
	public Long getEnddate() {
		return enddate;
	}
	public void setEnddate(Long enddate) {
		this.enddate = enddate;
	}

}
