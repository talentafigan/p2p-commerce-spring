package p2p.commerce.commerceapi.configuration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BussinesException.class)
    public ResponseEntity<?> notInternalAdviceController(BussinesException bussinesException, HttpServletRequest request) {
        System.out.println(bussinesException.getMessage());
        return ResponseHelper.err(bussinesException.getMessage(), HttpStatus.OK, request);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> notBadAdviceController(BadCredentialsException badCredentialsException, HttpServletRequest request) {
        return ResponseHelper.err(badCredentialsException.getMessage(), HttpStatus.OK, request);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<?> parseExeption(ParseException parseExeption, HttpServletRequest request) {
        return ResponseHelper.err(parseExeption.getMessage(), HttpStatus.OK, request);
    }
}
