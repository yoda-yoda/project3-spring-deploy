package org.durcit.be.system.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.durcit.be.log.domain.Log;
import org.durcit.be.log.repository.LogRepository;
import org.durcit.be.security.util.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final LogRepository logRepository;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex, HttpServletRequest request) {
        String memberId = SecurityUtil.getCurrentMemberIdOrNull();
        String endpoint = request.getRequestURI();
        String method = request.getMethod();
        String controller = "UnknownController";
        String requestPayload = "Error extracting request body";

        Log log = Log.builder()
                .memberId(memberId)
                .method(method)
                .endpoint(endpoint)
                .controller(controller)
                .requestPayload(requestPayload)
                .responsePayload(ex.getMessage())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        logRepository.save(log);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
    }

}
