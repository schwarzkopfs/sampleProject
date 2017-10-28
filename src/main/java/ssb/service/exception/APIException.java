package ssb.service.exception;

import org.springframework.http.HttpStatus;

import ssb.model.BarUserResponse;
import ssb.service.status.ResponseCode;

public class APIException extends Exception {

	private static final long serialVersionUID = 1L;

	private int httpStatus = HttpStatus.OK.value();
	private BarUserResponse errorMessage;

	public APIException(int httpStatus, BarUserResponse errorMessage) {
		super(errorMessage.getResultDesc());
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
	}

	public APIException(int httpStatus, BarUserResponse errorMessage,
			Throwable cause) {
		super(errorMessage.getResultDesc(), cause);
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
	}
	
	public BarUserResponse getErrorMessage() {
		return this.errorMessage;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public void setErrorMessage(BarUserResponse errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public static APIException error400BadRequest(BarUserResponse errorMessage) {
		return new APIException(HttpStatus.BAD_REQUEST.value(), errorMessage);
	}
	
	public static APIException error400BadRequest(BarUserResponse errorMessage, Throwable cause) {
		return new APIException(HttpStatus.BAD_REQUEST.value(), errorMessage, cause);
	}
	
	public static APIException error400BadRequest(String code, String description) {
		BarUserResponse errorMessage = new BarUserResponse(code, description);
		return new APIException(HttpStatus.BAD_REQUEST.value(), errorMessage);
	}
	
	public static APIException error400BadRequest(String code, String description, Throwable cause) {
		BarUserResponse errorMessage = new BarUserResponse(code, description);
		return new APIException(HttpStatus.BAD_REQUEST.value(), errorMessage, cause);
	}
	
	public static APIException error400BadRequest(ResponseCode apiExceptionCode, Throwable cause) {
		BarUserResponse errorMessage = new BarUserResponse(apiExceptionCode.getResultCode(), apiExceptionCode.getResultDescription());
		return new APIException(HttpStatus.BAD_REQUEST.value(), errorMessage, cause);
	}
	
	public static APIException error400BadRequest(ResponseCode apiExceptionCode) {
		BarUserResponse errorMessage = new BarUserResponse(apiExceptionCode.getResultCode(), apiExceptionCode.getResultDescription());
		return new APIException(HttpStatus.BAD_REQUEST.value(), errorMessage);
	}
	
	public static APIException error403Forbidden(BarUserResponse errorMessage) {
		return new APIException(HttpStatus.FORBIDDEN.value(), errorMessage);
	}
	
	public static APIException error403Forbidden(BarUserResponse errorMessage, Throwable cause) {
		return new APIException(HttpStatus.FORBIDDEN.value(), errorMessage, cause);
	}
	
	public static APIException error403Forbidden(String code, String description) {
		BarUserResponse errorMessage = new BarUserResponse(code, description);
		return new APIException(HttpStatus.FORBIDDEN.value(), errorMessage);
	}
	
	public static APIException error403Forbidden(String code, String description, Throwable cause) {
		BarUserResponse errorMessage = new BarUserResponse(code, description);
		return new APIException(HttpStatus.FORBIDDEN.value(), errorMessage, cause);
	}
	
	public static APIException error403Forbidden(ResponseCode apiExceptionCode, Throwable cause) {
		BarUserResponse errorMessage = new BarUserResponse(apiExceptionCode.getResultCode(), apiExceptionCode.getResultDescription());
		return new APIException(HttpStatus.FORBIDDEN.value(), errorMessage, cause);
	}
	
	public static APIException error403Forbidden(ResponseCode apiExceptionCode) {
		BarUserResponse errorMessage = new BarUserResponse(apiExceptionCode.getResultCode(), apiExceptionCode.getResultDescription());
		return new APIException(HttpStatus.FORBIDDEN.value(), errorMessage);
	}
	
	public static APIException error404NotFound(BarUserResponse errorMessage) {
		return new APIException(HttpStatus.NOT_FOUND.value(), errorMessage);
	}
	
	public static APIException error404NotFound(BarUserResponse errorMessage, Throwable cause) {
		return new APIException(HttpStatus.NOT_FOUND.value(), errorMessage, cause);
	}
	
	public static APIException error404NotFound(String code, String description) {
		BarUserResponse errorMessage = new BarUserResponse(code, description);
		return new APIException(HttpStatus.NOT_FOUND.value(), errorMessage);
	}
	
	public static APIException error404NotFound(String code, String description, Throwable cause) {
		BarUserResponse errorMessage = new BarUserResponse(code, description);
		return new APIException(HttpStatus.NOT_FOUND.value(), errorMessage, cause);
	}
	
	public static APIException error404NotFound(ResponseCode apiExceptionCode, Throwable cause) {
		BarUserResponse errorMessage = new BarUserResponse(apiExceptionCode.getResultCode(), apiExceptionCode.getResultDescription());
		return new APIException(HttpStatus.NOT_FOUND.value(), errorMessage, cause);
	}
	
	public static APIException error404NotFound(ResponseCode apiExceptionCode) {
		BarUserResponse errorMessage = new BarUserResponse(apiExceptionCode.getResultCode(), apiExceptionCode.getResultDescription());
		return new APIException(HttpStatus.NOT_FOUND.value(), errorMessage);
	}
	public static APIException error405MethodNotAllow(BarUserResponse errorMessage) {
		return new APIException(HttpStatus.METHOD_NOT_ALLOWED.value(), errorMessage);
	}
	
	public static APIException error500InternalServerError(BarUserResponse errorMessage) {
		return new APIException(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage);
	}
	
	public static APIException error500InternalServerError(BarUserResponse errorMessage, Throwable cause) {
		return new APIException(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage, cause);
	}
	
	public static APIException error500InternalServerError(String code, String description) {
		BarUserResponse errorMessage = new BarUserResponse(code, description);
		return new APIException(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage);
	}
	
	public static APIException error500InternalServerError(String code, String description, Throwable cause) {
		BarUserResponse errorMessage = new BarUserResponse(code, description);
		return new APIException(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage, cause);
	}
	
	public static APIException error500InternalServerError(ResponseCode apiExceptionCode, Throwable cause) {
		BarUserResponse errorMessage = new BarUserResponse(apiExceptionCode.getResultCode(), apiExceptionCode.getResultDescription());
		return new APIException(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage, cause);
	}
	
	public static APIException error500InternalServerError(ResponseCode apiExceptionCode) {
		BarUserResponse errorMessage = new BarUserResponse(apiExceptionCode.getResultCode(), apiExceptionCode.getResultDescription());
		return new APIException(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage);
	}

}

