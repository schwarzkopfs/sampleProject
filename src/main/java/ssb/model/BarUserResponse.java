package ssb.model;

import ssb.service.status.ResponseCode;

public class BarUserResponse {
	private String resultCode;
	private String resultDesc;
	
	public BarUserResponse() {
		super();
	}
	public BarUserResponse(String resultCode, String resultDesc) {
		super();
		this.resultCode = resultCode;
		this.resultDesc = resultDesc;
	}
	public String getResultCode() {
		return resultCode;
	} 
	public void setResultCode(ResponseCode responseCode) {
		this.resultCode = responseCode.getHttpCode();
	}
	public String getResultDesc() {
		return resultDesc;
	}
	public void setResultDesc(ResponseCode responseCode) {
		this.resultDesc = responseCode.getHttpStatusMsg();
	}
	
	public void setResultResponse(ResponseCode statusOkWithCondition){
		this.resultCode = statusOkWithCondition.getHttpCode();
		this.resultDesc = statusOkWithCondition.getHttpStatusMsg();
	}
	
	
}
