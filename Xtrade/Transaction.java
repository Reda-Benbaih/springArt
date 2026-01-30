import java.time.LocalDateTime;

public class Transaction {
    private String operationType;
    private Asset assets;
    private LocalDateTime date;
    private double quantity;
    private double price;

    public Transaction(String operationType, Asset assets, LocalDateTime date, double quantity, double price) {
        this.operationType = operationType;
        this.assets = assets;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Asset getAssets() {
        return assets;
    }

    public void setAssets(Asset assets) {
        this.assets = assets;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "operationType='" + operationType + '\'' +
                ", assets=" + assets +
                ", date=" + date +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
