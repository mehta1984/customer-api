package com.company.api.customer.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.company.api.customer.util.ValidationUtil;

/**
 * 
 * @author Bhavin Mehta
 *
 */
@ControllerAdvice
public class CustomRestExceptionHandler  {


	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFound(ResourceNotFoundException ex) {
		final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), "");
        return new ResponseEntity<Object>(apiError, HttpStatus.NOT_FOUND);
    }
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> invalidInput(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        ApiError apiError= new ApiError(HttpStatus.BAD_REQUEST,"Invalid inputs.",ValidationUtil.fromBindingErrors(result));
        return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
    }
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<ApiError> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex) {
	        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "");
	        return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
	}

}
