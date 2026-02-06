import java.util.List;

public class Portfolio<A extends Asset > {
    private List<A> assets;
    private Double assetValue;


    public Portfolio(List<A> assets,Double assetvalue) {
        this.assets = assets;
        this.assetValue = assetvalue;
        updateAssetValue();
    }

    public List<A> getAssets() {
        return assets;
    }

    public void setAssets(List<A> assets) {
        this.assets = assets;
    }

    public Double getAssetvalue() {
        return assetValue;
    }

    public void setAssetvalue(Double assetvalue) {
        this.assetValue = assetvalue;
    }

    public void updateAssetValue() {
        double total = 0;
        for (A a : assets) {
            total += a.getPrice() * a.getQuantity();
        }
        this.assetValue = total;
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "assets=" + assets +
                ", assetValue=" + assetValue +
                '}';
    }
}
