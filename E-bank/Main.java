package projects.ebank;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Bank test = new Bank();
        while (true){
            System.out.print("\n1 pour ajouter un client\n");
            System.out.print("2 pour creer un compte bancaire\n");
            System.out.print("3 pour afficher les comptes\n");
            System.out.print("4 pour consulter le solde\n");
            System.out.print("5 pour deposer de l'argent\n");
            System.out.print("6 pour retirer de l'argent\n");
            System.out.print("7 pour supprimer un compte\n");
            System.out.print("8 pour creer un compte d'epargne\n");
            System.out.print("9 pour avance un moi\n");
            System.out.print("10 pour afficher les comptes d'epargne\n");
            System.out.print("11 pour transferer compte to compte ");
            System.out.print("0 pour quitter le programme\n");
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
                case 9 : test.monthLater();
                case 10 : test.showSavingAccounts();break;
                case 11 : test.transaction();break;
                default: System.out.println("ce choix n'existe pas !!!");
            }
        }
    }
}
