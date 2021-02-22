package com.investment.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler{
	
	private static Logger logger = LoggerFactory.getLogger(GenericExceptionHandler.class);
	
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ExceptionBody> recordNotFoundException(RecordNotFoundException ex){
		logger.info("inside Exception Handler: message:"+ ex.getMessage());
		return new ResponseEntity<ExceptionBody>(new ExceptionBody(ex.getMessage()), HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(InvalidArgumentException.class)
	public ResponseEntity<ExceptionBody> invalidArgumentException(InvalidArgumentException ex){
		logger.info("inside Exception Handler: message:"+ ex.getMessage());
		return new ResponseEntity<ExceptionBody>(new ExceptionBody(ex.getMessage()),HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(MappingAlreadyExistException.class)
	public ResponseEntity<ExceptionBody> mappingAlreadyExistException(MappingAlreadyExistException ex){
		logger.info("inside Exception Handler: message:"+ ex.getMessage());
		return new ResponseEntity<ExceptionBody>(new ExceptionBody(ex.getMessage()),HttpStatus.FOUND);
		
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ExceptionBody> runtimeException(RuntimeException ex){
		logger.info("inside Exception Handler: message:"+ ex.getMessage());
		return new ResponseEntity<ExceptionBody>(new ExceptionBody(ex.getMessage()),HttpStatus.NOT_ACCEPTABLE);
		
	}

}
