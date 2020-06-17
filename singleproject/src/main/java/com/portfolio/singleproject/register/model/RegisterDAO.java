package com.portfolio.singleproject.register.model;

public interface RegisterDAO {

	int userIdChk(String userid);
	int userRegister(RegisterVO registerVo);
}
