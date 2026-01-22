package projects.ebank;

public class Client extends Person{
    int clientNumber;
    static int counter = 1;
    public Client(String fullname, int age, String id){
        super(fullname,age,id);
        this.clientNumber = counter++;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientNumber=" + clientNumber +
                ", fullname='" + getFullname() + '\'' +
                ", age=" + getAge() +
                ", id='" + getId() + '\'' +
                '}';
    }

}
