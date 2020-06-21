package com.portfolio.singleproject.register.model;

public class RegisterVO {
	private int no;
	private String userid;
	private String userpw;
	private String email1;
	private String email2;
	private String salt;
	private String path;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "RegisterVO [no=" + no + ", userid=" + userid + ", userpw=" + userpw + ", email1=" + email1 + ", email2="
				+ email2 + ", salt=" + salt + ", path=" + path + "]";
	}

	
	
}
