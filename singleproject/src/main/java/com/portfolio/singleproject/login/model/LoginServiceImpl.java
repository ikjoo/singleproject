package com.portfolio.singleproject.login.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.singleproject.common.SHA256Util;
import com.portfolio.singleproject.register.model.RegisterVO;

@Service
public class LoginServiceImpl implements LoginServices{
	
	@Autowired
	private LoginDAO loginDao;

	@Override
	public int userLogin(String userid, String userpw) {
		String salt=loginDao.saltByuserid(userid);
		
		int result=0;
		
		if(salt==null||salt.isEmpty()) {
			result=NONE_USERID;
		}else {
			String pw=SHA256Util.getEncrypt(userpw, salt);
			if(pw.equals(salt)) {
				result=LOGIN_OK;
			}else {
				result=DISAGREE_PWD;
			}
		}
		
		return result;
	}

	@Override
	public RegisterVO userInfoByuserid(String userid) {
		return loginDao.userInfoByuserid(userid);
	}

	
	
	
}
