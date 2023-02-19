package server.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import server.payload.response.MessageResponse;

@SpringBootTest
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler exceptionHandler;

    @Test
    void handleExceptionTest() {

        Exception e = new Exception("message");

        ResponseEntity<MessageResponse> handleException = exceptionHandler.handleException(e);
        assertEquals(ResponseEntity.class, handleException.getClass());
        assertEquals("500 INTERNAL_SERVER_ERROR", handleException.getStatusCode().toString());
    }

}
