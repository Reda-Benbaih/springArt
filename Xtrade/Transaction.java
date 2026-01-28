public class Transaction {
    private String operationType;
    private Asset assets;
    private String date;
    private int quantity;
    private double price;

    public Transaction(String operationType, Asset assets, String date, int quantity, double price) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
