package Bank.Bank;

public class CheckingAccount {
    private Double balance;
    private String name;



    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public Double getBalance() {
        return balance;
    }
    public void DisplayAccount(){
        System.out.println("Nama : " + name);
        System.out.println("Balance: "+ balance);
    }

    public void Deposit(Double amount){
        if (amount > 0){
            balance += amount;
            System.out.println("Deposit sebesar " + amount + " Berhasil. Saldo sekarang : " + balance);
        }
        else{
            System.out.println("Jumlah deposit harus lebih dari 0");
        }
    }

    public void withdrawl(double amount){
        if (amount > 0 && balance >= amount){
            balance -= amount;
            System.out.println("Penarikan sebesar " + amount + " Berhasil. Saldo sekarang " + balance);
        }
        else if (amount <= 0){
            System.out.println("Jumlah penarikan harus lebih dari 0");
        }
        else{
            System.out.println("Saldo tidak mencukupi, untuk penarikan sebesar: " + amount);
        }
    }

    public void applyInterest(){
        double interest = balance * 0.01;
        balance += interest;
        System.out.println("Bunga 1% telah ditambahkan. Saldo sekarang sebesar: " + balance);
    }
}

//saving bond//