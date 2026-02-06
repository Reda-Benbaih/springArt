
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        System.out.println("\u001B[31m Access denied \u001B[0m");
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
            System.out.println("5  - Historique d'un trader");
            System.out.println("6  - Filtrer l'historique");
            System.out.println("7  - Trier l'historique");
            System.out.println("8  - Statistiques d'un actif");
            System.out.println("9  - Volume échangé par trader");
            System.out.println("10 - Nombre total des échanges");
            System.out.println("11 - Top N traders");
            System.out.println("12 - Montant total par actif");
            System.out.println("13 - Actif le plus échangé");
            System.out.println("14 - Total BUY / SELL");
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
                case 5 : showTraderHistory();break;
                case 6 : filterHistory();break;
                case 7 : sortHistory();break;
                case 8 : AssetChanged();break;

                case 9 : valueQuantityTrader();break;
                case 10 : changesPassed();break;
                case 11 : showTop();break;
                case 12 : totalPriceAsset();break;
                case 13 : topAsset();break;
                case 14 : lastTask();break;
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

                Transaction newTrasaction = new Transaction("buy", a,now,assetnum,total,buyer);
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

                Transaction newTrasaction = new Transaction("buy", a,now,canBuy,sold,buyer);
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

            Transaction newTrasaction = new Transaction("sell", item,now,itemQuantity,total,seller);
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
    public boolean historyEmpty(){
        if(history.isEmpty()){
            System.out.println("il y'a aucun transaction");
            return false;
        }
        return true;
    }

    public void showTraderHistory(){
        if(!historyEmpty()){
            return;
        }
        System.out.println("entrer le nom de trader ");
        String name = scan.nextLine();

        List<Transaction> result = history.stream()
                .filter(n -> n.getTrader().getName().equals(name))
                .collect(Collectors.toList());
        System.out.println(result);
    }

    public void filterHistory(){
        if(!historyEmpty()){
            return;
        }
        System.out.println("filter par type (buy/sell)");
        String typeTrasaction = scan.nextLine();

        System.out.println("filter par nom d'actif");
        String  assetName = scan.nextLine();

        System.out.print("Enter start datetime (yyyy-MM-ddTHH:mm): ");
        LocalDateTime start = LocalDateTime.parse(scan.nextLine());

        System.out.print("Enter end datetime (yyyy-MM-ddTHH:mm): ");
        LocalDateTime end = LocalDateTime.parse(scan.nextLine());

        List<Transaction> result = history.stream()
                .filter(n -> n.getOperationType().equals(typeTrasaction) && n.getAssets().getName().equals(assetName) && !n.getDate().isAfter(start) && !n.getDate().isBefore(end))
                .collect(Collectors.toList());
        System.out.println(result);

    }
    public void sortHistory(){
        System.out.println("entrer le type de triage d'historique (montant/date)");
        String sortType = scan.nextLine();
        if (sortType.equals("montant")){
            List<Transaction> sortedHistory = history.stream()
                    .sorted(Comparator.comparing(t -> t.getPrice()))
                    .collect(Collectors.toList());
            System.out.println(sortedHistory);
        } else if (sortType.equals("date")) {
            List<Transaction> sortedHistory = history.stream()
                    .sorted(Comparator.comparing(t -> t.getDate()))
                    .collect(Collectors.toList());
            System.out.println(sortedHistory);
        }else{
            System.out.println("ce methode invalide");
        }
    }

    public void AssetChanged(){
        System.out.println("entre le nom d'actif");
        String assetname = scan.nextLine();

        double assetChanged = history.stream()
                .filter(t -> t.getAssets().getName().equals(assetname))
                .map(t -> t.getQuantity())
                .reduce(0.0,(a,b)-> a + b );
        System.out.println("il y'a "+ assetChanged +" d'echange");

        double totalBuyed = history.stream()
                .filter(t -> t.getAssets().getName().equals(assetname) && t.getOperationType().equals("buy"))
                .map(t -> t.getPrice())
                .reduce(0.0,(a,b)-> a + b );
        System.out.println("montant total des achats "+totalBuyed);

        double totalSelled = history.stream()
                .filter(t -> t.getAssets().getName().equals(assetname) && t.getOperationType().equals("sell"))
                .map(t -> t.getPrice())
                .reduce(0.0,(a,b)-> a + b );
        System.out.println("montant total des ventes "+totalSelled);
    }

    public void valueQuantityTrader(){
        System.out.println("entrer l'id de trader");
        int num = scan.nextInt();
        scan.nextLine();

        double valueTotal = history.stream()
                .filter(t -> t.getTrader().getTraderId() == num)
                .map(n -> n.getQuantity())
                .reduce(0.0, (a,b) -> a + b);
        System.out.println("valeur total echange de ce trader est "+valueTotal);
    }

    public void changesPassed(){
        long changePassed = history.stream()
                .map(t -> t != null)
                        .count();
        System.out.println("les echanges passes "+changePassed);
    }
    public void showTop(){
        System.out.println("entrer le nombre de classement ");
        int num = scan.nextInt();
        scan.nextLine();

        List<Map.Entry<Trader, Double>> topTraders = history.stream()
                .collect(Collectors.groupingBy(t ->t.getTrader(),Collectors.summingDouble(t-> t.getPrice()) ) )
                .entrySet().stream()
                .sorted(Map.Entry.<Trader, Double>comparingByValue().reversed())
                .limit(num)
                .collect(Collectors.toList());

        System.out.println(topTraders);
    }

    public void totalPriceAsset(){

        List<Map.Entry<Asset, Double>> total = history.stream()
                .collect(Collectors.groupingBy(t -> t.getAssets(),Collectors.summingDouble(t-> t.getPrice()) ) )
                .entrySet().stream()
                .sorted(Map.Entry.<Asset, Double>comparingByValue().reversed())
                .collect(Collectors.toList());

        System.out.println(total);
    }

    public void topAsset (){
        List<Map.Entry<Asset, Double>> total = history.stream()
                .collect(Collectors.groupingBy(t -> t.getAssets(),Collectors.summingDouble(t-> t.getQuantity()) ) )
                .entrySet().stream()
                .sorted(Map.Entry.<Asset, Double>comparingByValue().reversed())
                .collect(Collectors.toList());

        System.out.println(total.getFirst());
    }
    public void lastTask(){
        double totalBuy = history.stream()
                .filter(t -> "BUY".equalsIgnoreCase(t.getOperationType()))
                .mapToDouble(t -> t.getPrice() * t.getQuantity())
                .sum();

        double totalSell = history.stream()
                .filter(t -> "SELL".equalsIgnoreCase(t.getOperationType()))
                .mapToDouble(t -> t.getPrice() * t.getQuantity())
                .sum();
        System.out.println("Montant total des BUY  = " + totalBuy);
        System.out.println("Montant total des SELL = " + totalSell);
    }
}
