package ssb.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

public class GeneralUtil {

	public static String generateUUID() {
		return UUID.randomUUID().toString();
	}

	public static String getRequestHeadersInfo(HttpServletRequest request) {

		Map<String, String> map = new HashMap<String, String>();

		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		return map.toString();
	}
	
	public static String getRemoteAddr(HttpServletRequest request) {
		// is client behind something?
		String address = request.getHeader("X-FORWARDED-FOR");
		if (address == null) {
			address = request.getRemoteAddr();
		}

		return address;
	}
	
	public static String getRequestParameters(HttpServletRequest request) {

		Map<String, String> map = new HashMap<String, String>();

		Enumeration<String> paramNames = request.getParameterNames();
		if (paramNames != null) {
			while (paramNames.hasMoreElements()) {
				String key = (String) paramNames.nextElement();
				String value = request.getParameter(key);
				map.put(key, value);
			}
		}
		return map.toString();
	}
	
	public static String getResponseHeadersInfo(HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		int status = response.getStatus();
		map.put("status code", status +" "+ HttpStatus.valueOf(status).getReasonPhrase());
		map.put("content-type", response.getContentType() +";charset="+ response.getCharacterEncoding());
		
		Collection<String> headerNames = response.getHeaderNames();
		for (String name : headerNames) {
			String headerVal = response.getHeader(name);
			map.put(name, headerVal);
		}

		return map.toString();
	}
	
	public static String getLocalHostName() {
		String hostName = null;
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			hostName = "UnknownHost";
			
			// try to run command print 'hostname' on unix server
			try {
				hostName = IOUtils.toString(Runtime.getRuntime().exec("hostname").getInputStream());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		// remove newline if exist
		return StringUtils.chomp(hostName);
	}
	
}