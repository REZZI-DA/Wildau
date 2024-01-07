package wildau.web.live.chat.exceptions;

public class UserEntityNotFoundException extends  RuntimeException{

    public UserEntityNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
