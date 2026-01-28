import java.util.List;

public class Portfolio<B,A,AV> {
    private B balence;
    private List<A> assets;
    private AV assetValue;

    public Portfolio(B balence, List<A> assets,AV assetvalue) {
        this.balence = balence;
        this.assets = assets;
        this.assetValue = assetvalue;
    }

    public B getBalence() {
        return balence;
    }

    public void setBalence(B balence) {
        this.balence = balence;
    }

    public List<A> getAssets() {
        return assets;
    }

    public void setAssets(List<A> assets) {
        this.assets = assets;
    }

    public AV getAssetvalue() {
        return assetValue;
    }

    public void setAssetvalue(AV assetvalue) {
        this.assetValue = assetvalue;
    }
}
