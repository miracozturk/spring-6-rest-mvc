package mozt.springframework.spring6restmvc.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//ResponseStatus annotation in NotFoundException class or these Annotations.
//@ControllerAdvice
 public class ExceptionController {
//to use this method uncomment below annotation
    //ıt has been commented for the sake of more efficient method.
//    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(){
        return ResponseEntity.notFound().build();
    }

}
