package com.portfolio.singleproject.reboard.model;

import java.util.List;

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

	@Override
	public List<ReboardVO> reboardSearch(ReboardVO reboardVo) {
		return reboardDao.reboardSearch(reboardVo);
	}

	@Override
	public int searchTotal(ReboardVO reboardVo) {
		return reboardDao.searchTotal(reboardVo);
	}

	@Override
	public int readcountUp(int reNo) {
		return reboardDao.readcountUp(reNo);
	}

	@Override
	public ReboardVO reboardSelByNo(int reboardNo) {
		return reboardDao.reboardSelByNo(reboardNo);
	}
	
}
