package com.acme.unified.user.web.error;

import java.io.IOException;
import java.net.SocketTimeoutException;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.GenericJDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * The Class GlobalDefaultExceptionHandler.
 *
 * @author Jim.DelloStritto
 * @project simple-service-user
 * @created Mar 13, 2021
 * @references
 * @credits
 */
@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionHandler.class);

  
  /**
   *  Handle Hibernate not found exceptions.
   *  
   * @param response
   * @throws IOException
   * @return the response
   */
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> customHandleNotFound(RuntimeException ex, WebRequest request) {

    DefaultExceptionMessage apiError = new DefaultExceptionMessage(HttpStatus.NOT_FOUND, ex.getMessage());

      return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);

  }
  
  /**
   * Handle generic timeout exceptions.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(value = {SocketTimeoutException.class, GenericJDBCException.class})
  protected ResponseEntity<Object> handleGenericTimeoutExceptions(RuntimeException ex,
      WebRequest request) {

    LOGGER.error("Received request timeout exception {}", ex.getMessage());

    DefaultExceptionMessage apiError = new DefaultExceptionMessage(HttpStatus.REQUEST_TIMEOUT, ex.getMessage());
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getHttpStatus());
  }


  /**
   * Handle runtime exception.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler({RuntimeException.class})
  public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {

    LOGGER.error("Received Runtime exception {}", ex.getMessage());

    final DefaultExceptionMessage apiError = 
        new DefaultExceptionMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getHttpStatus());

  }

  /**
   * Handle default exception.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleDefaultException(Exception ex, WebRequest request) {

    LOGGER.error("Received Exception {}", ex.getMessage());
    
    final DefaultExceptionMessage apiError = new DefaultExceptionMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
    return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  /**
   * Handle and Map Response Status Exception.
   *
   * @param req the req
   * @param e the e
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(value = {ResponseStatusException.class})
  public @ResponseBody ResponseEntity<Object> checkResponseStatusException(HttpServletRequest req,
      ResponseStatusException e, WebRequest request) {

    LOGGER.error("Received Response Status Exception {}", e.getReason().toUpperCase());
    final DefaultExceptionMessage apiError =
        new DefaultExceptionMessage(HttpStatus.valueOf(e.getReason().toUpperCase()), e.getReason().toUpperCase());
    
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.valueOf(e.getReason().toUpperCase()));
  }



}
