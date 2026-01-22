package projects.ebank;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Bank test = new Bank();
        while (true){
            System.out.println("1 pour ajouter un client");
            System.out.println("2 pour creer un compte bancaire");
            System.out.println("3 pour afficher les comptes");
            System.out.println("4 pour consulter le solde");
            System.out.println("5 pour deposer de l'argent");
            System.out.println("6 pour retirer de l'argent");
            System.out.println("7 pour supprimer un compte");
            System.out.println("8 pour creer un compte d'epargne");
            System.out.println("9 pour avance un moi");
            System.out.println("10 pour afficher les comptes d'epargne");
            System.out.println("11 pour transferer compte to compte ");
            System.out.println("0 pour quitter le programme");
            int choice = scan.nextInt();
            if (choice == 0){
                break;
            }
            switch(choice){
                case 1 : test.addClient(); break;
                case 2 : test.createAccount(); break;
                case 3 : test.displayAccounts();break;
                case 4 : test.consultBalance();break;
                case 5 : test.deposit();break;
                case 6 : test.withdraw();break;
                case 7 : test.deleteAccount();break;
                case 8 : test.createSavingAccount();break;
                case 9 : test.monthLater();break;
                case 10 : test.showSavingAccounts();break;
                case 11 : test.transaction();break;
                default: System.out.println("ce choix n'existe pas !!!");
            }
        }
    }
}
