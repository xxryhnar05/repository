package Bank.Bank;

public class AccountTest {
    public static void main(String[] args) {
        CheckingAccount account = new CheckingAccount();

        account.setName("Royhan");
        account.setBalance(10000.0);
        account.DisplayAccount();
        account.Deposit(1500.0);
        account.withdrawl(500);
        account.applyInterest();

        SavingBond savings = new SavingBond(account.getName(), account.getBalance());

        savings.payInstallment(500.0, account);

    }
}
