package JavaException;

/**
 * InsufficientFundsException
 */
public class InsufficientFundsException extends Exception {

    private static final long serialVersionUID = 649717142336031471L;

    public InsufficientFundsException(String msg) {
        super(msg);
    }

    public InsufficientFundsException(double currentbal) {
        System.out.println("Insufficient funds. Current balance: " + currentbal);
    }

}