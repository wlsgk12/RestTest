package org.koreait.restControllers;

import org.koreait.commons.rest.JSONResult;
import org.koreait.models.board.CommonRestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("org.koreait.restControllers")
public class CommonRestController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<JSONResult<Object>> errorHandler(Exception e){

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if(e instanceof CommonRestException){
            CommonRestException commonRestException= (CommonRestException) e;
            status = commonRestException.getStatus();
        }

        JSONResult<Object> jsonResult = new JSONResult<>();
        jsonResult.setSuccess(false);
        jsonResult.setMessage(e.getMessage());
        jsonResult.setStatus(status);

        return ResponseEntity.status(status).body(jsonResult);
    }
}
