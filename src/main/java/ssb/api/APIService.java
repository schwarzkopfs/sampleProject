package ssb.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

public enum APIService {

	FIND_BY_MSISDN(SSBRestURI.REST_BAR_USER + "/check", "findByMsisdn", RequestMethod.POST), 
	SAVE(SSBRestURI.REST_BAR_USER + "/replace", "save", RequestMethod.POST),
	DELETE(SSBRestURI.REST_BAR_USER + "/delete", "delete", RequestMethod.POST);
	
	private APIService(String uri, String service, RequestMethod method) {
		this.uri = uri;
		this.service = service;
		this.method = method;
	}

	private static Map<String, APIService> map = new HashMap<String, APIService>();
	static {
		for (APIService e : values()) {
			map.put(e.uri, e);
		}
	}

	public static final APIService getUri(String uri) {
		return map.get(uri);
	}

	public static Map<String, APIService> getMap() {
		return map;
	}

	public static APIService getAPIServiceByUriAndMethod(String uri, RequestMethod method) {
		for (APIService apiService : APIService.values()) {
			if (apiService.getUri().equals(uri) && apiService.getMethod().equals(method))
				return apiService;
		}
		return null;
	}

	private final String uri;
	private final String service;
	private final RequestMethod method;

	public String getUri() {
		return uri;
	}

	public String getService() {
		return service;
	}

	public RequestMethod getMethod() {
		return method;
	}

}
