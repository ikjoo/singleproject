package com.portfolio.singleproject.login.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.portfolio.singleproject.register.model.RegisterVO;

@Component
public class LoginDAOMybatis implements LoginDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	String namespace="com.mybatis.mapper.userlogin.";
	
	@Override
	public String saltByuserid(String userid) {
		return sqlSession.selectOne(namespace+"saltByuserid", userid);
	}

	@Override
	public RegisterVO userInfoByuserid(String userid) {
		return sqlSession.selectOne(namespace+"userInfoByuserid", userid);
	}
	
	

}
