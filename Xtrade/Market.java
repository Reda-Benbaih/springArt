
import java.time.LocalDateTime;
import java.util.*;

public class Market {
    List<Trader> traderList = new ArrayList<>();
    List<Admin> adminList = new ArrayList<>();
    List<Asset> assetsList = new ArrayList<>();
    Set<Integer> assetCodeList = new HashSet<>();
    List<Transaction> history = new ArrayList<>();

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
        scan.nextLine();
        if(age <18 ){
            System.out.println("tu ne peut pas etre un trader");
            return;
        }
        System.out.println("entrer votre solde :");
        double balence = scan.nextDouble();
        scan.nextLine();

        if (balence < 500){
            System.out.println("solde insuffisant");
            return;
        }
        Trader newTrader = new Trader(name,id, age, balence);

        traderList.add(newTrader);
    }
    public Boolean adminCheck(){
        System.out.println("entrer username");
        String username = scan.nextLine();
        System.out.println("entrer password");
        String password = scan.nextLine();
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
            System.out.println("1 pour ajouter les actifs ");
            System.out.println("2 pour modifier les actifs ");
            System.out.println("3 pour afficher les actifs");
            System.out.println("4 pour ajouter un trader");
            System.out.println("5 pour afficher les traders");
            System.out.println("6 pour voir historique");
            System.out.println("0 pour quitter ");
            int choice = scan.nextInt();
            scan.nextLine();
            if(choice == 0 ){
                break;
            }
            switch (choice){
                case 1 : addAsset();break;
                case 2 : changeAssetValue();break;
                case 3 : showAssets();break;
                case 4 : addTrader();break;
                case 5 : showTrader();break;
                case 6 : showHistory();break;
                default:
                    System.out.println("ce choix indisponible !!");
                    break;
            }
        }
    }

    public void traderMenu(){
        while (true) {
            System.out.println("1 pour acheter un actif ");
            System.out.println("2 pour vendre un actif ");
            System.out.println("3 pour afficher les actifs");
            System.out.println("4 pour voir portfolio");
            System.out.println("0 pour quitter ");
            int choice = scan.nextInt();
            scan.nextLine();
            if(choice == 0 ){
                break;
            }
            switch (choice){
                case 1 : buyAsset();break;
                case 2 : sellAsset();break;
                case 3 : showAssets();break;
                case 4 : showPortfolio();break;
                default:
                    System.out.println("ce choix indisponible !!");
                    break;
            }
        }
    }

    public void addAsset(){
        int assetCode;
        do{
            assetCode = (int) (Math.random()* 9000) +1000;
        }while (assetCodeList.contains(assetCode));
        assetCodeList.add(assetCode);

        System.out.println("entrer le nom d'actif");
        String assetName = scan.nextLine();

        System.out.println("entrer le prix d'actif");
        double assetprice = scan.nextDouble();
        scan.nextLine();

        System.out.println("entrer quantite d'actif");
        int assetQuantity = scan.nextInt();
        scan.nextLine();

        System.out.println("entrer le type d'actif(stock/crypto)");
        String assetType = scan.nextLine();

//        assetType.toLowerCase();

        if(assetType.equals("stock")){
            Stock newStock = new Stock(assetType,assetQuantity,assetprice,assetCode,assetName);
            assetsList.add(newStock);
        } else if (assetType.equals("crypto")) {
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

        for(Asset a : assetsList){
            double randomNum = 0.95f + (Math.random() * (1.10f - 0.95f));
            a.setPrice(a.getPrice()* randomNum);
        }
        for (Trader t : traderList) {
            t.getPortfolio().updateAssetValue();
        }

    }

    public void showAssets(){
        System.out.println(assetsList);
    }

    public void buyAsset(){
        if(traderList.isEmpty()){
            System.out.println("il y'a aucun trader");
            return;
        }
        if (assetsList.isEmpty()){
            System.out.println("il y'a aucun actif");
            return;
        }

        System.out.println("entrer votre trader id ");
        int num = scan.nextInt();
        scan.nextLine();

        boolean found = false;
        Trader buyer = null;

        for (Trader trad : traderList){
            if (num == trad.getTraderId()){
                found = true;
                buyer = trad;
                break;
            }
        }
        if(!found){
            System.out.println("ce id n'existe pas");
            return;
        }

        System.out.println("1 pour acheter par quantite");
        System.out.println("2 pour acheter par balence ");
        int choice = scan.nextInt();
        scan.nextLine();

        if( choice == 1){
            System.out.println("entrer le code d'actif ");
            int assetcode = scan.nextInt();
            scan.nextLine();
            Asset a = null;
            boolean found1 = false;
            for (Asset act : assetsList){
                if (act.getCode() == assetcode){
                    found1 = true;
                    a = act;
                    break;
                }
            }
            if (!found1){
                System.out.println("cet actif n'existe pas");
                return;
            }
            System.out.println("entrer la quantite ");
            double assetnum = scan.nextDouble();
            scan.nextLine();
            double total = a.getPrice() * assetnum;

            if(assetnum > 0 && assetnum <= a.getQuantity() && total <= buyer.getBalence()){
                LocalDateTime now = LocalDateTime.now();

                a.setQuantity(a.getQuantity() - assetnum);
                buyer.setBalence(buyer.getBalence() - total);

                Transaction newTrasaction = new Transaction("buy quantity", a,now,assetnum,total);
                history.add(newTrasaction);

                boolean found2 = false;
                for (Asset have : buyer.getPortfolio().getAssets()){
                    if(a.getCode() == have.getCode()){
                        found2 = true;
                        have.setQuantity(have.getQuantity() + assetnum);
                        break;
                    }
                }

                if(!found2){
                    Asset traderAsset;
                    if(a instanceof Stock){
                        traderAsset = new Stock(a.getType(), assetnum, a.getPrice(), a.getCode(), a.getName());
                    } else {
                        traderAsset = new Crypto(a.getType(), assetnum, a.getPrice(), a.getCode(), a.getName());
                    }
                    buyer.getPortfolio().getAssets().add(traderAsset);
                }
                buyer.getPortfolio().updateAssetValue();
                System.out.println("Achat réussi !");
            }else{
                System.out.println("tu ne peux pas ");
            }

        }else if (choice == 2){
            System.out.println("entrer le code d'actif ");
            int assetcode = scan.nextInt();
            scan.nextLine();
            Asset a = null;
            for (Asset act : assetsList){
                if (act.getCode() == assetcode){
                    a = act;
                    break;
                }
            }
            if (a == null){
                System.out.println("cet actif n'existe pas");
                return;
            }
            System.out.println("entrer la quantite ");
            double sold = scan.nextDouble();
            scan.nextLine();

            double canBuy = sold / a.getPrice();
            if(sold > 0 && sold <= buyer.getBalence() && canBuy <= a.getQuantity()){
                LocalDateTime now = LocalDateTime.now();
                a.setQuantity(a.getQuantity() - canBuy);
                buyer.setBalence(buyer.getBalence() - sold);

                Transaction newTrasaction = new Transaction("buy balence", a,now,canBuy,sold);
                history.add(newTrasaction);

                boolean found2 = false;
                for (Asset have : buyer.getPortfolio().getAssets()){
                    if(a.getCode() == have.getCode()){
                        found2 = true;
                        have.setQuantity(have.getQuantity() + canBuy);
                        break;
                    }
                }
                if(!found2){
                    Asset traderAsset;
                    if(a instanceof Stock){
                        traderAsset = new Stock(a.getType(), canBuy, a.getPrice(), a.getCode(), a.getName());
                    } else {
                        traderAsset = new Crypto(a.getType(), canBuy, a.getPrice(), a.getCode(), a.getName());
                    }
                    buyer.getPortfolio().getAssets().add(traderAsset);
                    buyer.getPortfolio().updateAssetValue();

                    System.out.println("Achat réussi !");
                }

                }else{
                    System.out.println("tu ne peux pas ");
                }
            }else {
                System.out.println("ce choix n'existe pas");
        }
    }
    public void sellAsset(){
        if(traderList.isEmpty()){
            System.out.println("il y'a aucun trader");
            return;
        }

        System.out.println("entrer votre trader id");
        int num = scan.nextInt();
        scan.nextLine();
        Trader seller = null;
        for(Trader trad : traderList){
            if (trad.getTraderId() == num){
                seller = trad;
                break;
            }
        }
        if(seller == null ){
            System.out.println("ce trader n'existe pas ");
            return;
        }
        if (seller.getPortfolio().getAssets().isEmpty()){
            System.out.println("tu n'a pas aucun asset");
            return;
        }

        System.out.println(seller.getPortfolio());

        System.out.println("entrer le code d'actif ");
        int assetcode = scan.nextInt();
        scan.nextLine();

        Asset item = null;
        Asset itemMarket = null;

        for (Asset a : seller.getPortfolio().getAssets()){
            if (assetcode == a.getCode()){
                item = a;
                break;
            }
        }
        for (Asset b : assetsList){
            if (assetcode == b.getCode()){
                itemMarket = b;
            }
        }
        if(itemMarket == null){
            System.out.println("n'a pas dans le stock");
            return;
        }

        if (item == null){
            System.out.println("tu n'a pas cet actif ");
            return;
        }

        System.out.println("entrer la quantite tu veux vendre ");
        double itemQuantity = scan.nextDouble();
        scan.nextLine();

        if (itemQuantity > 0 && item.getQuantity() >= itemQuantity){
            double total = itemMarket.getPrice()* itemQuantity;
            LocalDateTime now = LocalDateTime.now();
            item.setQuantity(item.getQuantity() - itemQuantity);
            seller.setBalence(seller.getBalence() + (itemQuantity * itemMarket.getPrice() ));
            itemMarket.setQuantity(itemMarket.getQuantity() + itemQuantity);

            Transaction newTrasaction = new Transaction(item.getType(), item,now,itemQuantity,total);
            history.add(newTrasaction);

        }else {
            System.out.println("tu ne peux pas");
        }
        seller.getPortfolio().getAssets().removeIf(s -> s.getQuantity() == 0);
        seller.getPortfolio().updateAssetValue();
    }
    public void showHistory(){
        if(history.isEmpty()){
            System.out.println("il y'a aucun transaction : ");
            return;
        }
        System.out.println(history);
    }

    public void showTrader(){
        if(traderList.isEmpty()){
            System.out.println("il y'a aucun transaction : ");
            return;
        }
        System.out.println(traderList);
    }

    public void showPortfolio(){
        System.out.println("entrer votre id ");
        int num = scan.nextInt();
        scan.nextLine();
        boolean found = false;
        for(Trader trad : traderList){
            if (num == trad.getTraderId()){
                found = true;
                System.out.println(trad.getPortfolio());
                break;
            }
        }
        if (!found){
            System.out.println("ce trader n'existe ");
            return;
        }
    }
}
