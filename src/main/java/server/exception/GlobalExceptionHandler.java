package server.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import server.payload.response.MessageResponse;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.internalServerError().body(new MessageResponse(exception.getMessage()));
    }
}
