package pl.ecommerce.backend.user.exceptions;

public class CreateUserException  extends RuntimeException{
    public CreateUserException(String s) {
        super(s);
    }
}
