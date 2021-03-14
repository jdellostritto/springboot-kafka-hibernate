package com.acme.unified.user.web.error;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;

/**
 * The Class ExceptionMessage.
 *
 * @author Jim.DelloStritto
 * @project shared-service-user
 * @created Mar 13, 2021
 * @references
 * @credits
 */
public class DefaultExceptionMessage {

  /** The http status. */
  private HttpStatus httpStatus;

  /** The errors. */
  private List<String> errors;

  /** The message. */
  private String message;

  /**
   * MessageError: Constructor.
   *
   * @param httpStatus the http status
   * @param message the message
   */
  public DefaultExceptionMessage(HttpStatus httpStatus, String message) {
    super();
    this.httpStatus = httpStatus;
    this.message = message;
    this.errors = new ArrayList<>();
  }

  /**
   * getHttpStatus: Returns the value of httpStatus.
   * 
   * @return the httpStatus
   */
  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  /**
   * getErrors: Returns the value of errors.
   * 
   * @return the errors
   */
  public List<String> getErrors() {
    return errors;
  }

  /**
   * getMessage: Returns the value of message.
   * 
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * setMessage: Sets the value of message.
   * 
   * @param message the message to set
   */
  public void setMessage(String message) {
    this.message = message;
  }

}
