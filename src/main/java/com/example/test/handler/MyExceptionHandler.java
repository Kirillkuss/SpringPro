package com.example.test.handler;

import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.example.test.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class MyExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<BaseResponse> errorBaseResponse( Throwable ex ){
        ex.printStackTrace();
        log.error( "Throwable >>> " + ex.getMessage());
        return ResponseEntity.internalServerError().body( BaseResponse.error( 500, ex ));
    }

    @ExceptionHandler( NoSuchElementException.class )
    public ResponseEntity errorBaseResponse( NoSuchElementException ex ){
        log.error( "NoSuchElementException >>> " +  ex.getMessage() );
        return ResponseEntity.status( HttpStatus.NOT_FOUND )
                             .body( new BaseResponse<>( 404, ex.getMessage() ));
    }

    @ExceptionHandler( IllegalArgumentException.class )
    public ResponseEntity errorBaseResponse( IllegalArgumentException ex ){
        log.error( "IllegalArgumentException >>> " +  ex.getMessage() );
        return ResponseEntity.status( HttpStatus.BAD_REQUEST )
                             .body( new BaseResponse<>( 400, ex.getMessage() ));
    }

    @ExceptionHandler( UsernameNotFoundException.class )
    public ResponseEntity errorBaseResponse( UsernameNotFoundException ex ){
        log.error( "UsernameNotFoundException >>> " +  ex.getMessage() );
        return ResponseEntity.status( HttpStatus.UNAUTHORIZED )
                             .body( new BaseResponse<>( 401, "Invalid username or password" ));
    }
    

    
}
