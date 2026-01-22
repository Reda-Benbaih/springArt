package projects.ebank;

public class Account {
    private int accountNumber;
    private double sold;
    private String typeAccount;
    private Client owner;

    public Account(double sold, int accountNumber, String typeAccount, Client owner) {
        this.sold = sold;
        this.accountNumber = accountNumber;
        this.typeAccount = typeAccount;
        this.owner = owner;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getSold() {
        return sold;
    }

    public void setSold(double sold) {
        this.sold = sold;
    }

    public String getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(String typeAccount) {
        this.typeAccount = typeAccount;
    }

    public Client getOwner() {
        return owner;
    }

    @Override
    public String toString(){
        return "Account{"+
                "sold : "+ getSold()+
                ", Accountnumber : " + getAccountNumber()+
                ", typeAccount : "+ getTypeAccount()+
                ", owner : "+getOwner()+
                "}";
    }


}