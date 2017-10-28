package ssb.service.status;

import java.util.HashMap;
import java.util.Map;

public enum APIExceptionCode {	
	STATUS_OK("200", "OK", "200", "SUCCESS"),
	STAUTUS_UNKNOWN_SYNTAX("400", "Bad Request", "400", "Bad Request"),
//	STATUS_MISSING("400", "Bad Request", "40001", "Missing parameter"),
	STATUS_INVALID("400", "Bad Request", "400", "Invalid parameter"),
	STATUS_METHOD_NOT_ALLOWED("405","Method Not Allowed","40005","Method Not Allowed"),
//	STATUS_DATA_EXISTED("400", "Bad Request", "40003", "Data Existed"),
	STATUS_INTERNAL_SERVER_ERROR("500", "Internal Server Error", "500", "Internal Server Error"),
	STATUS_DB_ERROR("500", "Internal Server Error", "500", "Internal Server Error"),
	STATUS_CONNECTION_TIMEOUT("500", "Internal Server Error", "500", "Internal Server Error"),
	STATUS_SYSTEM_BUSY("500", "Internal Server Error", "500", "Internal Server Error");

	private final String httpCode;
	private final String httpStatusMsg;
	private final String resultCode;
	private final String resultDescription;	
	
	private APIExceptionCode(String httpCode, String httpStatusMsg,
			String resultCode, String resultDescription) {
		this.httpCode = httpCode;
		this.httpStatusMsg = httpStatusMsg;
		this.resultCode = resultCode;
		this.resultDescription = resultDescription;
	}
	
	private static Map<String, APIExceptionCode> httpMap = new HashMap<String, APIExceptionCode>();
	static {
		for (APIExceptionCode e : values()) {
			httpMap.put(e.httpCode, e);
		}
	}
	
	private static Map<String, APIExceptionCode> resultCodeMap = new HashMap<String, APIExceptionCode>();
	static {
		for (APIExceptionCode e : values()) {
			resultCodeMap.put(e.resultCode, e);
		}
	}
	
	public static final APIExceptionCode getHttpCode(String code) {
		return httpMap.get(code);
	}
	
	public static final APIExceptionCode getResultCode(String code) {
		return resultCodeMap.get(code);
	}

	public static Map<String, APIExceptionCode> gethttpMap() {
		return httpMap;
	}
	
	public static Map<String, APIExceptionCode> getResultCodeMap() {
		return resultCodeMap;
	}

	public static void setHttpMap(Map<String, APIExceptionCode> httpMap) {
		APIExceptionCode.httpMap = httpMap;
	}
	
	public static void setResultCodeMap(Map<String, APIExceptionCode> resultCodeMap) {
		APIExceptionCode.resultCodeMap = resultCodeMap;
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

