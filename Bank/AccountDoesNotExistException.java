public class AccountDoesNotExistException extends Exception {
    public AccountDoesNotExistException(String exceptionMessage)
    {
        super(exceptionMessage);
    }
}
