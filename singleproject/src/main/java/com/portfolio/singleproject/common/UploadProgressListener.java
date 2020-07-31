package com.portfolio.singleproject.common;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

public class UploadProgressListener implements ProgressListener {

	private long num100Ks = 0;

	private long theBytesRead = 0;
	private long theContentLength = -1;
	private int whichItem = 0;
	private int percentDone = 0;
	private long startTime = System.currentTimeMillis();
	private long timeInSeconds = 0;
	private double uploadRate = 0;
	private double kbps = 0;	
	private boolean contentLengthKnown = false;	
	private HttpSession prvSession;

	public void update(long bytesRead, long contentLength, int items) {

		if (contentLength > -1) {
			contentLengthKnown = true;
		}
		theBytesRead = bytesRead;
		theContentLength = contentLength;
		whichItem = items;

		long nowNum100Ks = bytesRead / 100000;
		// Only run this code once every 100K
		if (nowNum100Ks > num100Ks) {
			num100Ks = nowNum100Ks;
			if (contentLengthKnown) {
				percentDone = (int) Math.round(100.00 * bytesRead / contentLength);
				//kbps
				timeInSeconds = (System.currentTimeMillis() - startTime) / 1000;
				uploadRate = (double) (bytesRead / (timeInSeconds + 0.00001));				
				kbps = (long)Math.round(uploadRate / 1024);				
			}
		}
				
		//컨트롤러에서 현재 사용자 세션자체를 셋팅해줘서 넘어온 
		//세션에 프로그레스 상태를 셋팅한다. ProgressListener의 update()가
		//어떤 작동을 하는지 정확하진 않으나 파일 업로드시 반복적으로 프로그레스가
		//업데이트 되는것으로 봐서 세션에 계속해서 새로 담겨진 값을 가져가서
		//사용하면 된다.
		prvSession.setAttribute("percent", percentDone);
		prvSession.setAttribute("bytesread", theBytesRead);
		prvSession.setAttribute("contentlength", theContentLength);
		prvSession.setAttribute("kbps", kbps);
		//System.out.println(getMessage());
	}
	
	
	public String getMessage() {
		if (theContentLength == -1) {
			return "" + theBytesRead + " of Unknown-Total bytes have been read.";
		} else {
			return "" + theBytesRead + " of " + theContentLength + " bytes have been read (" + percentDone + "% done)";
		}
	}

	public long getNum100Ks() {
		return num100Ks;
	}

	public void setNum100Ks(long num100Ks) {
		this.num100Ks = num100Ks;
	}

	public long getTheBytesRead() {
		return theBytesRead;
	}

	public void setTheBytesRead(long theBytesRead) {
		this.theBytesRead = theBytesRead;
	}

	public long getTheContentLength() {
		return theContentLength;
	}

	public void setTheContentLength(long theContentLength) {
		this.theContentLength = theContentLength;
	}

	public int getWhichItem() {
		return whichItem;
	}

	public void setWhichItem(int whichItem) {
		this.whichItem = whichItem;
	}

	public int getPercentDone() {
		return percentDone;
	}

	public void setPercentDone(int percentDone) {
		this.percentDone = percentDone;
	}

	public boolean isContentLengthKnown() {
		return contentLengthKnown;
	}

	public void setContentLengthKnown(boolean contentLengthKnown) {
		this.contentLengthKnown = contentLengthKnown;
	}

	public void setHttpSession(HttpSession session) {
		this.prvSession = session;
	}
	public HttpSession getHttpSession(){
		return prvSession;
	}
}
