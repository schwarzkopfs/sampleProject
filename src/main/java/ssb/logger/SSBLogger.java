package ssb.logger;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ssb.filter.RestRequestWrapper;

@Aspect
@Component
public class SSBLogger {

    /** Handle to the log file */
    private static final Logger logger = LoggerFactory.getLogger("SSBLogger");
    public static final String LOG_BEFORE_FORMAT = "[{}][REQUEST][DATABASE][{}][{}]";
	public static final String LOG_AFTER_FORMAT =  "[{}][RESPONSE][DATABASE][{}][{}]";
	
	private static final String TRANSACTION_ID = "x-db-transactionid";
	
	public static final String DEFAULT_ENCODING = "UTF-8";
	
    public SSBLogger () {}

    @Before("execution(* th.co.ais.repository.*.*(..)) && args(obj,..)")
    public void logMethodAccessBefore(JoinPoint joinPoint, Object obj) throws IOException {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    	request = new RestRequestWrapper(request);
    	String transactionId = request.getHeader(TRANSACTION_ID);
    	String service = joinPoint.getSignature().getName();
    	logger.info(LOG_BEFORE_FORMAT, transactionId , service, obj);
    }
    
    @AfterThrowing(pointcut="execution(* th.co.ais.repository.*.*(..))")
    public void logMethodErrorException(JoinPoint joinPoint){
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    	request = new RestRequestWrapper(request);
    	String transactionId = request.getHeader(TRANSACTION_ID);
    	String service = joinPoint.getSignature().getName();
    	
    	logger.error(LOG_AFTER_FORMAT, transactionId , service, "Database Error");
    }
    
    @AfterReturning(pointcut="execution(* th.co.ais.repository.*.*(..))", returning="response")
    public void logMethodAccessAfter(JoinPoint joinPoint , Object response) throws Exception {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    	request = new RestRequestWrapper(request);
    	String transactionId = request.getHeader(TRANSACTION_ID);
    	String service = joinPoint.getSignature().getName();
    	if(response != null){
    		logger.info(LOG_AFTER_FORMAT, transactionId , service, response.toString());
    	}
    	else{
    		logger.info(LOG_AFTER_FORMAT, transactionId , service, "");
    	}

    }

}