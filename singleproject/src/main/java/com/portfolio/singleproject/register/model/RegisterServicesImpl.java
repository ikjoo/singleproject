package com.portfolio.singleproject.register.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServicesImpl implements RegisterServices{

	@Autowired
	private RegisterDAO registerDao;

	@Override
	public int userIdChk(String userid) {
		return registerDao.userIdChk(userid);
	}
}
