package com.portfolio.singleproject.reboard.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReboardDAOMybatis implements ReboardDAO{

	String namespace="com.mybatis.mapper.reBoard.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int reboardWrite(ReboardVO reboardVo) {
		return sqlSession.insert(namespace+"reboardWrite", reboardVo);
	}

	@Override
	public List<ReboardVO> reboardSearch(ReboardVO reboardVo) {
		return sqlSession.selectList(namespace+"reboardSearch", reboardVo);
	}

	@Override
	public int searchTotal(ReboardVO reboardVo) {
		return sqlSession.selectOne(namespace+"searchTotal", reboardVo);
	}
	
	
}
