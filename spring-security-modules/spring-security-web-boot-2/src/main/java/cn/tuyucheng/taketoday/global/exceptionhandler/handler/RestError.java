package cn.tuyucheng.taketoday.global.exceptionhandler.handler;

public class RestError {
    String errorCode;
    String errorMessage;

    public RestError(String errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "RestError{" +
              "errorCode='" + errorCode + '\'' +
              ", errorMessage='" + errorMessage + '\'' +
              '}';
    }
}