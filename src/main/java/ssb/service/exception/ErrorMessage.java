package ssb.service.exception;

import java.io.Serializable;

public class ErrorMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	String resultCode;
	String resultDescription;

	public ErrorMessage() {
		super();
	}

	public ErrorMessage(String resultCode, String resultDescription) {
		super();
		this.resultCode = resultCode;
		this.resultDescription = resultDescription;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultDescription() {
		return resultDescription;
	}

	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}

	@Override
	public String toString() {
		return "ErrorMessage [resultCode=" + resultCode
				+ ", resultDescription=" + resultDescription + "]";
	}
	
	

}

