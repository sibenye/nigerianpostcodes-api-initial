package com.elsynergy.nigerianpostcodes.web.exception;

import com.elsynergy.nigerianpostcodes.model.response.ErrorResponse;
import com.elsynergy.nigerianpostcodes.model.response.ApiResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler
{
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {

        return this.commonHandling(ex, headers, status, request, 100);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(
      final BindException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {

         return this.commonHandling(ex, headers, status, request, 100);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
      final MissingServletRequestParameterException ex, final HttpHeaders headers,
      final HttpStatus status, final WebRequest request) {

        return this.commonHandling(ex, headers, status, request, 100);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      final HttpRequestMethodNotSupportedException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {

        return this.commonHandling(ex, headers, status, request, 100);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
      final NoHandlerFoundException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

        return this.commonHandling(ex, headers, status, request, 404);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            final MethodArgumentTypeMismatchException ex, final WebRequest request){

        return this.commonHandling(ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, request, 100);

    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(
      final ConstraintViolationException ex, final WebRequest request) {

        return this.commonHandling(ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, request, 100);

    }

    @ExceptionHandler({ AuthenticationServiceException.class })
    public ResponseEntity<Object> handleAuthenticationException(
            final AuthenticationServiceException ex, final WebRequest request)
    {
        return this.commonHandling(ex, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request, 401);
    }

    @ExceptionHandler({ ApiException.class })
    public ResponseEntity<Object> handleApiException(
      final ApiException ex, final WebRequest request) {

        return this.commonHandling(ex, new HttpHeaders(), ex.getHttpStatus(), request, ex.getErrorCode());
    }

    private ResponseEntity<Object> commonHandling(
            final Exception ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request,
            final Integer errorCode) {

        final List<String> errors = this.getErrors(ex);

        final String localizedMessage = ex.getLocalizedMessage() == null ?
                CodeMessage.getMessage(errorCode) : ex.getLocalizedMessage();
       final ErrorResponse apiErrorResponse =
         new ErrorResponse(errorCode, localizedMessage, errors);
       final ApiResponse apiResponse = new ApiResponse(apiErrorResponse, false);
       return this.handleExceptionInternal(ex, apiResponse, headers, status, request);

    }

    private List<String> getErrors(final Exception ex)
    {
        final List<String> errors = new ArrayList<>();
        if (ex instanceof BindException || ex instanceof MethodArgumentNotValidException) {
            List<FieldError> fieldErrors = new ArrayList<>();
            List<ObjectError> objectErrors = new ArrayList<>();
            if (ex instanceof BindException) {
                fieldErrors = ((BindException) ex).getBindingResult().getFieldErrors();
                objectErrors = ((BindException) ex).getBindingResult().getGlobalErrors();
            } else {
                fieldErrors = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors();
                objectErrors = ((MethodArgumentNotValidException) ex).getBindingResult().getGlobalErrors();
            }
            for (final FieldError error : fieldErrors) {
                errors.add(error.getField() + ": " + error.getDefaultMessage());
            }
            for (final ObjectError error : objectErrors) {
                errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
            }
        } else if (ex instanceof NoHandlerFoundException) {
            final String error = "No handler found for " + ((NoHandlerFoundException) ex).getHttpMethod() + " " + ((NoHandlerFoundException) ex).getRequestURL();
            errors.add(error);
        } else if (ex instanceof MissingServletRequestParameterException ||
                ex instanceof MissingServletRequestPartException) {
            if (ex instanceof MissingServletRequestParameterException) {
                final String error = ((MissingServletRequestParameterException) ex).getParameterName() + " parameter is missing";
                errors.add(error);
            } else {
                final String error = ((MissingServletRequestPartException) ex).getRequestPartName() + " request part is missing";
                errors.add(error);
            }

        } else if (ex instanceof MethodArgumentTypeMismatchException) {
            final String error = ((MethodArgumentTypeMismatchException) ex).getName() + " should be of type " +
                    ((MethodArgumentTypeMismatchException) ex).getRequiredType().getName();
            errors.add(error);
        } else if (ex instanceof ConstraintViolationException) {
            for (final ConstraintViolation<?> violation : ((ConstraintViolationException) ex).getConstraintViolations()) {
                errors.add(violation.getRootBeanClass().getName() + " " +
                  violation.getPropertyPath() + ": " + violation.getMessage());
            }
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            final StringBuilder builder = new StringBuilder();
            builder.append(((HttpRequestMethodNotSupportedException) ex).getMethod());
            builder.append(" method is not supported for this request. Supported methods are ");
            ((HttpRequestMethodNotSupportedException) ex).getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

            errors.add(builder.toString());
        } else if (ex instanceof ApiException) {
            final String error = ((ApiException) ex).getMessageDetail();
            errors.add(error);
        }

        return errors;
    }

}
