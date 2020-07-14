package com.portfolio.singleproject.reboard.model;

import java.sql.Timestamp;

public class ReboardVO {
	private int reboardNo;
	private String reboardTitle ;
	private String reboardContent;
	private Timestamp reboardReg;
	private int readcount;
	private int groupno;
	private int step;
	private int sortno;
	private String delflag;
	private String filename;
	private String filesize;
	private int downcount;
	private String originalfilename;
	private int no;
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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilesize() {
		return filesize;
	}
	public void setFilesize(String filesize) {
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
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	@Override
	public String toString() {
		return "ReboardVO [reboardNo=" + reboardNo + ", reboardTitle=" + reboardTitle + ", reboardContent="
				+ reboardContent + ", reboardReg=" + reboardReg + ", readcount=" + readcount + ", groupno=" + groupno
				+ ", step=" + step + ", sortno=" + sortno + ", delflag=" + delflag + ", filename=" + filename
				+ ", filesize=" + filesize + ", downcount=" + downcount + ", originalfilename=" + originalfilename
				+ ", no=" + no + "]";
	}
	
	
}
