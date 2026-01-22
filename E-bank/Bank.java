package projects.ebank;

import java.lang.reflect.Array;
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
        System.out.print("entrer votre nom complet :\n");
        String fullname = scan.next();

        System.out.print("entrer votre age : \n");
        int age = scan.nextInt();
        scan.nextLine();

        if (age < 18){
            System.out.print("tu ne peux pas etre a client .\n");
            return;
        }

        System.out.print("entrer votre id : \n");
        String id = scan.next();

        Client newclient = new Client(fullname,age,id);

        clientList.add(newclient);
        System.out.print(clientList);
    }

    public void createAccount(){
        if(clientList.isEmpty()){
            System.out.print("il y'a aucun client .\n");
            return;
        }

        System.out.print("entrer votre number : ");
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

        System.out.print("entrer votre solde : \n");
        double sold = scan.nextDouble();

        if (sold < 100){
            System.out.print("le solde est insuffisant ");
            return;
        }


        int accountNumber ;
        do {
            accountNumber = (int) (Math.random()*9000) +1000;
        }while (memory.contains(accountNumber));
        memory.add(accountNumber);

        Account newAccount = new Account(sold,accountNumber,"normal account",owner);
        accountList.add(newAccount);
        System.out.print(accountList);
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
        System.out.print(accountList);
    }

    public void consultBalance() {
        System.out.print("Numéro du compte : ");
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
        System.out.print("Numéro du compte : ");
        int num = scan.nextInt();

        for (Account acc : accountList) {
            if (acc.getAccountNumber() == num) {
                System.out.print("Montant : ");
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
        System.out.print("Numéro du compte : ");
        int num = scan.nextInt();

        for (Account acc : accountList) {
            if (acc.getAccountNumber() == num) {
                System.out.print("Montant : ");
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
        System.out.print("Numéro du compte : ");
        int num = scan.nextInt();
        accountList.removeIf(acc -> acc.getAccountNumber() == num);
        savingAccountsList.removeIf(acc -> acc.getAccountNumber() == num * 10);
        System.out.println("Compte supprimé.");
    }

    public void createSavingAccount(){
     System.out.print("entrer votre nombre : ");
     int num = scan.nextInt();

     Client owner = null;

     for(Client ele : clientList){
         if(ele.getClientNumber() == num){
             owner = ele ;
             break;
         }
     }

     if (owner == null){
         System.out.print("ce client n'existe pas");
         return;
     }

     System.out.print("entrer votre numero de compte :");
     int num1 = scan.nextInt();

     Account haveAcc = null;

     for (Account ele : accountList){
         if (ele.getAccountNumber() == num1){
             haveAcc = ele;
             break;
         }
     }
     if (haveAcc == null){
         System.out.print("ce client n'a pas cree un compte ");
         return;
     }
      System.out.print("entrer le solde : ");
     double sold = scan.nextDouble();

     if (sold < 300){
         System.out.print("solde insufisant !!");
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
        System.out.print(savingAccountsList);
    }

}