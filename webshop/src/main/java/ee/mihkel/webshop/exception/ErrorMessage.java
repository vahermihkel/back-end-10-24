package ee.mihkel.webshop.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorMessage {
    String message;
    String code; // 404, 400
    Date date;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
