package movie.payload.response;

import java.net.HttpURLConnection;
import java.time.LocalDate;

public class ResponseObject<T> {
    private int status;
    private String message;
    private int code;
    private LocalDate responseDate;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public LocalDate getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(LocalDate responseDate) {
        this.responseDate = responseDate;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseObject() {

    }

    public ResponseObject(int status, String message, int code, LocalDate responseDate, T data) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.responseDate = responseDate;
        this.data = data;
    }

    public ResponseObject<T> responseSuccess(String message, T data) {
        return new ResponseObject<T>(0, message, HttpURLConnection.HTTP_OK, LocalDate.now(), data);
    }

    public ResponseObject<T> responseError(int statusCode, String message, T data) {
        return new ResponseObject<T>(0, message, statusCode, LocalDate.now(), data);
    }
}