package com.portfolio.singleproject.register.model;

public interface RegisterServices {
	
	int userIdChk(String userid);
	int userRegister(RegisterVO registerVo);
}
