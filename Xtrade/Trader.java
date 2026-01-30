import java.util.ArrayList;

public class Trader extends Person{
    final private int traderId;
    private double balence ;
    private static int idCounter = 1;
    private Portfolio<Asset> portfolio;

    public Trader (String name,String id,int age,double balence){
        super(name,id,age);
        this.traderId = idCounter++;
        this.balence = balence;
        this.portfolio = new Portfolio<>( new ArrayList<>(), 0.0);
    }

    public int getTraderId() {
        return traderId;
    }

    public double getBalence() {
        return balence;
    }

    public void setBalence(double balence) {
        this.balence = balence;
    }

    public Portfolio< Asset> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio<Asset> portfolio) {this.portfolio = portfolio;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "traderId=" + traderId +
                ", balence=" + balence +
                ", portfolio=" + portfolio +
                '}';
    }
}
