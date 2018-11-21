package JavaException;

/**
 * InsufficientFundsException
 */
public class InsufficientFundsException extends Exception {

    private static final long serialVersionUID = 649717142336031471L;

    private double balance;

    public InsufficientFundsException(double balance) {
        this.setBalance(balance);
    }

    @Override
    public String getMessage() {
        System.out.println("\nAmount exceeds Account balance\nCurrent balance: " + getBalance());
        return super.getMessage();
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }
}