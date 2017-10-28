package ssb.service.status;

import java.util.HashMap;
import java.util.Map;

public enum ResponseCode {	
	STATUS_OK("200", "Success", "200", "Success"),
	STATUS_OK_WITH_CONDITION("201", "Success With Condition", "201", "Success With Condition"),
	STATUS_OK_WITH_DATA_NOT_FOUND("202", "Data not found", "202", "Data not found"),
	STATUS_INTERNAL_SERVER_ERROR("500", "Internal Server Error", "500", "Internal Server Error");

	private final String httpCode;
	private final String httpStatusMsg;
	private final String resultCode;
	private final String resultDescription;	
	
	private ResponseCode(String httpCode, String httpStatusMsg,
			String resultCode, String resultDescription) {
		this.httpCode = httpCode;
		this.httpStatusMsg = httpStatusMsg;
		this.resultCode = resultCode;
		this.resultDescription = resultDescription;
	}
	
	private static Map<String, ResponseCode> httpMap = new HashMap<String, ResponseCode>();
	static {
		for (ResponseCode e : values()) {
			httpMap.put(e.httpCode, e);
		}
	}
	
	private static Map<String, ResponseCode> resultCodeMap = new HashMap<String, ResponseCode>();
	static {
		for (ResponseCode e : values()) {
			resultCodeMap.put(e.resultCode, e);
		}
	}
	
	public static final ResponseCode getHttpCode(String code) {
		return httpMap.get(code);
	}
	
	public static final ResponseCode getResultCode(String code) {
		return resultCodeMap.get(code);
	}

	public static Map<String, ResponseCode> gethttpMap() {
		return httpMap;
	}
	
	public static Map<String, ResponseCode> getResultCodeMap() {
		return resultCodeMap;
	}

	public static void setHttpMap(Map<String, ResponseCode> httpMap) {
		ResponseCode.httpMap = httpMap;
	}
	
	public static void setResultCodeMap(Map<String, ResponseCode> resultCodeMap) {
		ResponseCode.resultCodeMap = resultCodeMap;
	}

	public String getHttpCode() {
		return httpCode;
	}
	
	public String getHttpStatusMsg() {
		return httpStatusMsg;
	}

	public String getResultCode() {
		return resultCode;
	}

	public String getResultDescription() {
		return resultDescription;
	}
	
	
}

