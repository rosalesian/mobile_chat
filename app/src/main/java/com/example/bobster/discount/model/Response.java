package com.example.bobster.discount.model;

/**
 * Created by bobster on 3/17/2016.
 */
public class Response
{
    public String status_code;
    public String error;
    public String message;
    public String id;
    public String found;

    public Response(String status_code, String error, String message, String id, String found) {
        this.status_code = status_code;
        this.error = error;
        this.message = message;
        this.id = id;
        this.found = found;
    }

    public Response() {
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status_code='" + status_code + '\'' +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", id='" + id + '\'' +
                ", found='" + found + '\'' +
                '}';
    }
}
