package com.portfolio.singleproject.reboard.model;

import java.sql.Timestamp;

import com.portfolio.singleproject.common.SearchVO;

public class ReboardVO extends SearchVO{
	private int reboardNo;
	private String reboardTitle ;
	private String reboardContent;
	private Timestamp reboardReg;
	private int readcount;
	private int groupno;
	private int step;
	private int sortno;
	private String delflag;
	private String ckimgup;
	private String filename;
	private Long filesize;
	private int downcount;
	private String originalfilename;
	private String userid;
	public int getReboardNo() {
		return reboardNo;
	}
	public void setReboardNo(int reboardNo) {
		this.reboardNo = reboardNo;
	}
	public String getReboardTitle() {
		return reboardTitle;
	}
	public void setReboardTitle(String reboardTitle) {
		this.reboardTitle = reboardTitle;
	}
	public String getReboardContent() {
		return reboardContent;
	}
	public void setReboardContent(String reboardContent) {
		this.reboardContent = reboardContent;
	}
	public Timestamp getReboardReg() {
		return reboardReg;
	}
	public void setReboardReg(Timestamp reboardReg) {
		this.reboardReg = reboardReg;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getGroupno() {
		return groupno;
	}
	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getSortno() {
		return sortno;
	}
	public void setSortno(int sortno) {
		this.sortno = sortno;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	public String getCkimgup() {
		return ckimgup;
	}
	public void setCkimgup(String ckimgup) {
		this.ckimgup = ckimgup;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Long getFilesize() {
		return filesize;
	}
	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}
	public int getDowncount() {
		return downcount;
	}
	public void setDowncount(int downcount) {
		this.downcount = downcount;
	}
	public String getOriginalfilename() {
		return originalfilename;
	}
	public void setOriginalfilename(String originalfilename) {
		this.originalfilename = originalfilename;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "ReboardVO [reboardNo=" + reboardNo + ", reboardTitle=" + reboardTitle + ", reboardContent="
				+ reboardContent + ", reboardReg=" + reboardReg + ", readcount=" + readcount + ", groupno=" + groupno
				+ ", step=" + step + ", sortno=" + sortno + ", delflag=" + delflag + ", ckimgup=" + ckimgup
				+ ", filename=" + filename + ", filesize=" + filesize + ", downcount=" + downcount
				+ ", originalfilename=" + originalfilename + ", userid=" + userid + "]";
	}

	

	
	
}
