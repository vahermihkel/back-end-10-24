package ee.mihkel.webshop.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice // <--- iga kord kui juhtub Controlleris error (Exception),
//                  siis pannakse oma veateade
public class ExceptionCatcher {

    @ExceptionHandler  // kui juhtub RunTimeException mõnes Controlleris, siis tagastatakse
    //  hoopis ErrorMessae
    public ResponseEntity<ErrorMessage> handleException(RuntimeException e) {
        ErrorMessage message = new ErrorMessage();
        message.setDate(new Date());
        message.setCode("400"); // 400 on üldine viga
        message.setMessage(e.getMessage()); // message tuleb see, mis ma controlleris
        // throw new RunTimeException("SIIA_SISSE_PANIN");
        return ResponseEntity.badRequest().body(message);
    }
}
