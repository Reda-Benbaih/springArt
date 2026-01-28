import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Market newMarket = new Market();
        newMarket.addAdmin();
        while (true){
            System.out.println("1 pour admin");
            System.out.println("2 pour trader");
            System.out.println("0 pour quitter");
            int choice = scan.nextInt();
            if(choice == 0 ){
                System.out.println("programme terminee");
                break;
            }
            switch (choice){
                case 1 :
                    Boolean check = newMarket.adminCheck();
                    if(check == true ){
                        newMarket.adminMenu();
                    }
                    break;
                case 2 : newMarket.traderMenu();break;
                default:
                    System.out.println("ce choix n'existe pas !!");
            }
        }
    }
}
