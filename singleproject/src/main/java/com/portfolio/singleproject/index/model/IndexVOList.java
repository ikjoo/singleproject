package com.portfolio.singleproject.index.model;

import java.util.List;

public class IndexVOList {
	List<String> filenamelist;

	public List<String> getFilenamelist() {
		return filenamelist;
	}

	public void setFilenamelist(List<String> filenamelist) {
		this.filenamelist = filenamelist;
	}

	@Override
	public String toString() {
		return "IndexVOList [filenamelist=" + filenamelist + "]";
	}
	
	
}
