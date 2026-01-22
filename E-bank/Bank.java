package projects.ebank;

import java.util.ArrayList;
import java.util.Scanner;

public class Bank{

    ArrayList<Client> clientList = new ArrayList<>();
    ArrayList<Account> accountList = new ArrayList<>();
    ArrayList<SavingAccount> savingAccountsList = new ArrayList<>();
    ArrayList<Integer> memory = new ArrayList<>();
//    public Bank (){
//        clientList = new ArrayList<>();
//        accountList = new ArrayList<>();
//    }

    Scanner scan = new Scanner(System.in);

    public void addClient(){
        System.out.println("entrer votre nom :c");
        String fullname = scan.next();

        System.out.println("entrer votre age :c     ");
        int age = scan.nextInt();
        scan.nextLine();

        if (age < 18){
            System.out.println("tu ne peux pas etre a client .");
            return;
        }

        System.out.println("entrer votre id :");
        String id = scan.next();

        Client newclient = new Client(fullname,age,id);

        clientList.add(newclient);
        System.out.println(clientList);
    }

    public void createAccount(){
        if(clientList.isEmpty()){
            System.out.println("il y'a aucun client .");
            return;
        }

        System.out.println("entrer votre number : ");
        int searchNumber = scan.nextInt();
        Client owner = null;

        for (Client ele : clientList){
            if(ele.getClientNumber() == searchNumber){
                owner = ele;
                break;
            }
        }

        if (owner == null){
            System.out.println("ce client n'existe pas.");
            return;
        }

        System.out.println("entrer votre solde :");
        double sold = scan.nextDouble();

        if (sold < 100){
            System.out.println("le solde est insuffisant ");
            return;
        }


        int accountNumber ;
        do {
            accountNumber = (int) (Math.random()*9000) +1000;
        }while (memory.contains(accountNumber));
        memory.add(accountNumber);

        Account newAccount = new Account(sold,accountNumber,"normal account",owner);
        accountList.add(newAccount);
        System.out.println(accountList);
    }

    public void displayAccounts() {
        if (accountList.isEmpty()) {
            System.out.println("Aucun compte bancaire.");
            return;
        }
//        for (Account acc : accountList) {
//            System.out.println(acc.getAccountNumber() + " | " +
//                    acc.getTypeAccount() + " | " +
//                    acc.getSold());
//        }
        System.out.println(accountList);
    }

    public void consultBalance() {
        System.out.println("Numéro du compte : ");
        int num = scan.nextInt();

        for (Account acc : accountList) {
            if (acc.getAccountNumber() == num) {
                System.out.println("Solde : " + acc.getSold());
                return;
            }
        }
        System.out.println("Compte introuvable.");
    }

    public void deposit() {
        System.out.println("Numéro du compte : ");
        int num = scan.nextInt();

        for (Account acc : accountList) {
            if (acc.getAccountNumber() == num) {
                System.out.println("Montant : ");
                double amount = scan.nextDouble();
                if (amount > 0) {
                    acc.setSold(acc.getSold() + amount);
                    System.out.println("Dépôt réussi.");
                }
                return;
            }
        }
    }

    public void withdraw() {
        System.out.println("Numéro du compte : ");
        int num = scan.nextInt();

        for (Account acc : accountList) {
            if (acc.getAccountNumber() == num) {
                System.out.println("Montant : ");
                double amount = scan.nextDouble();
                if (amount <= acc.getSold()) {
                    acc.setSold(acc.getSold() - amount);
                    System.out.println("Retrait réussi.");
                } else {
                    System.out.println("Solde insuffisant.");
                }
                return;
            }
        }
    }

    public void deleteAccount() {
        System.out.println("Numéro du compte : ");
        int num = scan.nextInt();
        accountList.removeIf(acc -> acc.getAccountNumber() == num && acc.getSold() == 0);
        savingAccountsList.removeIf(acc -> acc.getAccountNumber() == num * 10  && acc.getSold() == 0);
        System.out.println("Compte supprimé.");
    }

    public void createSavingAccount(){
     System.out.println("entrer votre nombre : ");
     int num = scan.nextInt();

     Client owner = null;

     for(Client ele : clientList){
         if(ele.getClientNumber() == num){
             owner = ele ;
             break;
         }
     }

     if (owner == null){
         System.out.println("ce client n'existe pas");
         return;
     }

     System.out.println("entrer votre numero de compte :");
     int num1 = scan.nextInt();

     Account haveAcc = null;

     for (Account ele : accountList){
         if (ele.getAccountNumber() == num1){
             haveAcc = ele;
             break;
         }
     }
     if (haveAcc == null){
         System.out.println("ce client n'a pas cree un compte ");
         return;
     }
      System.out.println("entrer le solde : ");
     double sold = scan.nextDouble();

     if (sold < 300){
         System.out.println("solde insufisant !!");
         return;
     }

     SavingAccount newSaveAccount = new SavingAccount(sold, haveAcc.getAccountNumber()*10, "SavingAccount",owner);

     savingAccountsList.add(newSaveAccount);

    }

    public void monthLater(){
        for (SavingAccount ele : savingAccountsList){
            ele.setSold(ele.getSold() + ele.getSold() * 0.03d);
        }
    }

    public void showSavingAccounts(){
        if (savingAccountsList.isEmpty()) {
            System.out.println("Aucun compte bancaire.");
            return;
        }
        System.out.println(savingAccountsList);
    }

    public void transaction(){
        if (accountList.isEmpty()) {
            System.out.println("Aucun compte bancaire.");
            return;
        }
        System.out.println("entrer numero de compte : ");
        int num = scan.nextInt();

        boolean found = false;
        boolean found1 = false;
        Account sender = null;
        Account reciever = null;

        for (Account acc : accountList) {
            if (acc.getAccountNumber() == num){
               found = true;
               sender = acc;
               break;
            }
        }
        if (!found){
            System.out.println("ce compte n'existe pas ");
            return;
        }
        System.out.println("entrer numero de autre compte : ");
        int num1 = scan.nextInt();

        if (num == num1){
            System.out.println("tu peux pas le droit ");
            return;
        }

        for (Account acc : accountList) {
            if (acc.getAccountNumber() == num1){
                found1 = true;
                reciever = acc;
                break;
            }
        }
        if (!found1){
            System.out.println("ce compte n'existe pas ");
            return;
        }
        System.out.println("entre le solde qui tu veux donne");
        double sold = scan.nextDouble();

        if(sender.getSold() >= sold && sold > 0 ){
            sender.setSold(sender.getSold() - sold );
            reciever.setSold(reciever.getSold() + sold);
            System.out.println("transaction reussi : ");
        }else{
            System.out.println("Impossible de faire cet operation : ");

        }

    }

}
