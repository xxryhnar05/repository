package Bank.Bank;

public class SavingBond {
    private Double balance;
    private String name;
    private Double interestRate;

    public SavingBond(String name, Double balance) {
        this.name = name;
        this.balance = balance;
        this.interestRate = 0.05; // Default interest rate of 5%
    }

    public void payInstallment(Double installmentAmount, CheckingAccount account) {
        // Mengambil saldo dari akun cek
        Double checkingBalance = account.getBalance();

        if (installmentAmount > 0 && checkingBalance >= installmentAmount) {
            checkingBalance -= installmentAmount;
            account.setBalance(checkingBalance); // Mengupdate saldo di akun cek
            balance -= installmentAmount;
            System.out.println("Pembayaran cicilan sebesar " + installmentAmount + " Berhasil. Saldo tabungan sekarang " + balance);
        } else if (installmentAmount <= 0) {
            System.out.println("Jumlah cicilan harus lebih dari 0");
        } else {
            System.out.println("Saldo di akun cek tidak mencukupi untuk pembayaran cicilan sebesar: " + installmentAmount);
        }
    }
}
