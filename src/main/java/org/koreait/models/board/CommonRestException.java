package org.koreait.models.board;

import org.springframework.http.HttpStatus;

public class CommonRestException extends RuntimeException{
    private HttpStatus status;
    public CommonRestException(String message){
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public CommonRestException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }
    public HttpStatus getStatus(){
        return status;
    }

}
