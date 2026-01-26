public  abstract class Asset {
    private String type;
    private int quantity;
    private double price;
    private int code;
    private String name;
    public Asset(String type, int quantity, double price, int code, String name){
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.code = code;
        this.name = name;
    }
}
