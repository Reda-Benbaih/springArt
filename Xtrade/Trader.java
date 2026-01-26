public class Trader extends Person{
    private int traderId;
    private double balence ;

    public Trader (String name,String id,int age,int traderId,double balence){
        super(name,id,age);
        this.traderId = traderId;
        this.balence = balence;
    }

    public int getTraderId() {
        return traderId;
    }

    public void setTraderId(int traderId) {
        this.traderId = traderId;
    }

    public double getBalence() {
        return balence;
    }

    public void setBalence(double balence) {
        this.balence = balence;
    }
}
