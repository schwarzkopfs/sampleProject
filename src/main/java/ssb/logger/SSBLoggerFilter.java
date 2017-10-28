package ssb.logger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import ssb.api.APIService;
import ssb.filter.RestRequestWrapper;
import ssb.filter.RestResponseWrapper;
import ssb.model.BarUserResponse;
import ssb.service.status.APIExceptionCode;
import ssb.util.GeneralUtil;

@Component
public class SSBLoggerFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger("SSBLogger");
	private static final Logger logStats = LoggerFactory.getLogger("StatsLogger");
	public static final String LOG_RERQUEST_FORMAT = "[{}][REQUEST][{}][{}][{}][{}][{}]";
	public static final String LOG_RESPONSE_FORMAT = "[{}][RESPONSE][{}][{}][{}][{}ms][{}][{}]";
	public static final String LOG_STATS_FORMAT = "[ {} | {} ][{}][{}ms][{}][{}]";
	
	private static final SimpleDateFormat LOG_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	// APIGW (API Gateway) Header Parameters 
	private static final String TRANSACTION_ID = "x-db-transactionid";
	private static final String CONTENT_TYPE = "content-type";
	public static final String DEFAULT_ENCODING = "UTF-8";
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		request = new RestRequestWrapper(request);
		response = new RestResponseWrapper(response);
		
		// Set Response Header (Must put before chain.doFilter)
		this.setAPTGWResponseHeader(request, response);
		
//		Date startTime = Calendar.getInstance().getTime();
		long startTime = System.currentTimeMillis();
		String headers = GeneralUtil.getRequestHeadersInfo(request);
//		String params = GeneralUtil.getRequestParameters(request);
		String body = IOUtils.toString(request.getInputStream(), DEFAULT_ENCODING);
		String method = request.getMethod();
		String transactionId = request.getHeader(TRANSACTION_ID);
		String uri = request.getRequestURI();
		APIService apiService = APIService.getAPIServiceByUriAndMethod(uri, RequestMethod.valueOf(method));
		String service = "";
		if(apiService != null){
			service = apiService.getService();
		}
		logger.info(LOG_RERQUEST_FORMAT, transactionId , method, uri,service,headers,body);
		
//		logger.info(LOG_RERQUEST_FORMAT, transactionId , method, uri,service,headers,params,body);

		
		chain.doFilter(request, response);
		

		RestResponseWrapper res = (RestResponseWrapper) response;
		String resheaders = GeneralUtil.getResponseHeadersInfo(res);
		String resBody = new String(res.toByteArray(), DEFAULT_ENCODING);
		long endTime = System.currentTimeMillis();
		long responseTime = endTime - startTime;
		
		String startDate = (LOG_DATE_FORMAT.format(new Date(startTime)));
		String endDate = (LOG_DATE_FORMAT.format(new Date(endTime)));
		logger.info(LOG_RESPONSE_FORMAT,transactionId,method,uri,service, responseTime,resheaders, resBody);
		logStats(startDate,endDate,responseTime, service,method,resBody);
	}
	
	private void setAPTGWResponseHeader(HttpServletRequest req, HttpServletResponse res) {
		res.setHeader(TRANSACTION_ID, req.getHeader(TRANSACTION_ID));
		res.setHeader(CONTENT_TYPE, req.getHeader(CONTENT_TYPE));
	}
	
	private void logStats(String startDate, String endDate, long responseTime,String service, String method, String resBody) throws UnknownHostException {
		String resultCode = null;
		try{
			JSONObject jsonObj = new JSONObject(resBody);
			resultCode = (String)jsonObj.get("resultCode");
			
		}catch(JSONException ex){
			logger.error(ex.getMessage());
		}
		String host = GeneralUtil.getLocalHostName();
		logStats.info(LOG_STATS_FORMAT,startDate,endDate,host,responseTime,service,resultCode);

	}

}
