
import java.util.*;

public class Market {
    List<Trader> traderList = new ArrayList<>();
    List<Admin> adminList = new ArrayList<>();
    List<Asset> assetsList = new ArrayList<>();
    Set<Integer> assetCodeList = new HashSet<>();

    Scanner scan = new Scanner(System.in);

    public void addAdmin(){
        Admin newAdmin = new Admin("admin","admin1234");
        adminList.add(newAdmin);
    }

    public void addTrader(){
        System.out.println("entrer votre nom : ");
        String name = scan.nextLine();

        System.out.println("entrer votre id : ");
        String id = scan.nextLine();

        System.out.println("entrer votre age : ");
        int age = scan.nextInt();
        if(age <18 ){
            System.out.println("tu ne peut pas etre un trader");
            return;
        }
        System.out.println("entrer votre solde :");
        double balence = scan.nextDouble();

        if (balence < 500){
            System.out.println("solde insuffisant");
            return;
        }
        Trader newTrader = new Trader(name,id, age, balence);

        traderList.add(newTrader);
    }
    public Boolean adminCheck(){
        System.out.println("entrer username");
        String username = scan.next();
        System.out.println("entrer password");
        String password = scan.next();
        for(Admin ad : adminList){
            if (ad.getUsername().equals(username) && ad.getPassword().equals(password)){
                System.out.println("access permitted");
                return true;
            }
        }
        return false;
    }
    public void adminMenu(){
        while (true) {
            System.out.println("1 pour ajouter assets ");
            System.out.println("2 pour modifier assets ");
            System.out.println("0 pour quitter ");
            int choice = scan.nextInt();
            if(choice == 0 ){
                break;
            }
        }
    }

    public void traderMenu(){
        System.out.println("test");
    }

    public void addAsset(){
        System.out.println("entrer le nom d'actif");
        String assetName = scan.next();

        System.out.println("entrer le prix d'actif");
        double assetprice = scan.nextDouble();

        System.out.println("entrer quantite d'actif");
        int assetQuantity = scan.nextInt();

        System.out.println("entrer le type d'actif(stock/crypto)");
        String assetType = scan.next();

        int assetCode;
        do{
            assetCode = (int) (Math.random()* 9000) +1000;
        }while (assetCodeList.contains(assetCode));


        assetType.toLowerCase().trim();

        if(assetType == "stock"){
            Stock newStock = new Stock(assetType,assetQuantity,assetprice,assetCode,assetName);
            assetsList.add(newStock);
        } else if (assetType == "crypto") {
            Crypto newCrypto = new Crypto(assetType,assetQuantity,assetprice,assetCode,assetName);
            assetsList.add(newCrypto);
        }else {
            System.out.println("ce choix n'existe pas !!");
        }
    }

    public void changeAssetValue(){
        if(assetsList.isEmpty()){
            System.out.println("il ya aucun asset");
            return;
        }
    }
}
