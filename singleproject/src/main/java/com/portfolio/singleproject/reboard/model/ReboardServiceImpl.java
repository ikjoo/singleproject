package com.portfolio.singleproject.reboard.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReboardServiceImpl implements ReboardService{

	@Autowired
	private ReboardDAO reboardDao;

	@Override
	public int reboardWrite(ReboardVO reboardVo) {
		return reboardDao.reboardWrite(reboardVo);
	}
	
}
