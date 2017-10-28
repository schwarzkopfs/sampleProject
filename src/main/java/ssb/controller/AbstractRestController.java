package ssb.controller;


import java.util.concurrent.RejectedExecutionException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import ssb.model.BarUserResponse;
import ssb.service.exception.APIException;
import ssb.service.status.APIExceptionCode;

public class AbstractRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractRestController.class);
	
	@ExceptionHandler(APIException.class)
	public BarUserResponse handlerAPIException(APIException e, HttpServletResponse response) {
		logDetailAPIException(e);
		response.setStatus(e.getHttpStatus());
		return e.getErrorMessage();
	}
	
	private void logDetailAPIException(APIException e) {
		BarUserResponse errorMessage = e.getErrorMessage();
		if(e.getHttpStatus() == 500 && errorMessage != null) {
			// if 500 printStackTrace
			logger.error(e.getMessage(), e);
		} else {
			logger.error(e.getMessage());
		}
		
		logger.debug(e.getMessage(), e);
	}
	
	/**
	 * Default handler exception from Spring MVC for HTTP Status 400 (Bad Request)<br/>
	 * @see <a href="http://docs.spring.io/spring/docs/4.1.x/spring-framework-reference/html/mvc.html#mvc-ann-rest-spring-mvc-exceptions">Handling Standard Spring MVC Exceptions</a> <br/>
	 * @param e
	 * @return
	 */
//	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
//	@ExceptionHandler({BindException.class, 
//		MethodArgumentNotValidException.class,
//		MissingServletRequestParameterException.class,
//		MissingServletRequestPartException.class,
//		ServletRequestBindingException.class})
//	public BarUserResponse handlerDefaultBadRequestException(Exception e) {
//		logger.error(e.getMessage());
//		logger.debug(e.getMessage(), e);
//		BarUserResponse errorMessage = new BarUserResponse(APIExceptionCode.STATUS_MISSING.getResultCode(), APIExceptionCode.STATUS_MISSING.getResultDescription() +", "+ e.getMessage());
//		return errorMessage;
//	}
	
	/**
	 * Default handler exception from Spring MVC for HTTP Status 500 (Internal Server Error)<br/>
	 * @see <a href="http://docs.spring.io/spring/docs/4.1.x/spring-framework-reference/html/mvc.html#mvc-ann-rest-spring-mvc-exceptions">Handling Standard Spring MVC Exceptions</a> <br/>
	 * @param e
	 * @return
	 */
//	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
//	@ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
//	public ErrorMessage handlerDefaultServerErrorException(Exception e) {
//		logger.error(e.getMessage(), e);
//		ErrorMessage errorMessage = new ErrorMessage(APIExceptionCode.STATUS_UNKNOWN_ERROR.getResultCode(), APIExceptionCode.STATUS_UNKNOWN_ERROR.getResultDescription());
//		return errorMessage;
//	}
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({RejectedExecutionException.class})
	public BarUserResponse handlerRejectedExecutionException(RejectedExecutionException e) {
		logger.error(e.getMessage());
		logger.debug(e.getMessage(), e);
		BarUserResponse errorMessage = new BarUserResponse(APIExceptionCode.STATUS_INTERNAL_SERVER_ERROR.getResultCode(), APIExceptionCode.STATUS_INTERNAL_SERVER_ERROR.getResultDescription());
		return errorMessage;
	}
	
//	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
//	@ExceptionHandler(RuntimeException.class)
//	public ErrorMessage handlerRuntimeException(RuntimeException e) {
//		logger.error(e.getMessage(), e);
//		ErrorMessage errorMessage = new ErrorMessage(APIExceptionCode.STATUS_SYSTEM_ERROR.getResultCode(), APIExceptionCode.STATUS_SYSTEM_ERROR.getResultDescription());
//		return errorMessage;
//	}
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public BarUserResponse handlerUnknowException(Exception e) {
		logger.error(e.getMessage(), e);
		BarUserResponse errorMessage = new BarUserResponse(APIExceptionCode.STATUS_INTERNAL_SERVER_ERROR.getResultCode(), APIExceptionCode.STATUS_INTERNAL_SERVER_ERROR.getResultDescription());
		return errorMessage;
	}
	
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler({HttpMediaTypeException.class,
		NoHandlerFoundException.class})
	public BarUserResponse handlerHttpMediaTypeException(Exception e) {
		logger.error(e.getMessage());
		logger.debug(e.getMessage(), e);
		//String message =  e.getMessage().split("\n")[0];
		BarUserResponse errorMessage = new BarUserResponse(APIExceptionCode.STAUTUS_UNKNOWN_SYNTAX.getResultCode(), APIExceptionCode.STAUTUS_UNKNOWN_SYNTAX.getResultDescription());
		return errorMessage;
	}
	
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler({TypeMismatchException.class})
	public BarUserResponse handlerTypeMismatchException(Exception e) {
		logger.error(e.getMessage());
		logger.debug(e.getMessage(), e);
		
		Throwable cause = e.getCause();
		String message = cause.getMessage();
		
		//String message =  e.getMessage().split("\n")[0];
		BarUserResponse errorMessage = new BarUserResponse(APIExceptionCode.STAUTUS_UNKNOWN_SYNTAX.getResultCode(), APIExceptionCode.STAUTUS_UNKNOWN_SYNTAX.getResultDescription());
		return errorMessage;
	}
//	
	
	@ResponseStatus(value=HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public BarUserResponse handleHttpRequestMethodNotSupportedException(Exception e) {

		logger.error(e.getMessage(), e);
		BarUserResponse errorMessage = new BarUserResponse(APIExceptionCode.STATUS_METHOD_NOT_ALLOWED.getResultCode(), APIExceptionCode.STATUS_METHOD_NOT_ALLOWED.getResultDescription());
		return errorMessage;
	}
	
	
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public BarUserResponse handlerHttpMessageNotReadableException(Exception e) {
		logger.error(e.getMessage());
		logger.debug(e.getMessage(), e);
		String message =  e.getMessage().split(":")[0];
		BarUserResponse errorMessage = new BarUserResponse(APIExceptionCode.STAUTUS_UNKNOWN_SYNTAX.getResultCode(), APIExceptionCode.STAUTUS_UNKNOWN_SYNTAX.getResultDescription());
		return errorMessage;
	}
	
//	public void validXorderRef(String xOrderRef) throws APIException {
//		if("".equals(xOrderRef)){
//			logger.error("Invalid header x-orderRef");
//			throw APIException.error400BadRequest(APIExceptionCode.STATUS_INVALID.getResultCode(), APIExceptionCode.STATUS_INVALID.getResultDescription() +", Invalid header x-orderRef");
//		}
//	}
}
