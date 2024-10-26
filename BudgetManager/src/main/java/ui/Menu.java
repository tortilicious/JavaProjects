package ui;

import file.FileManager;
import model.Account;
import model.Purchase;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private Account account;
    private ArrayList<Purchase> purchasesList;

    public Menu(Account account, ArrayList<Purchase> purchasesList) {
        this.account = account;
        this.purchasesList = purchasesList;
    }

    public void showMainMenu() {
        System.out.print("""
                Choose your action:
                1) Add income
                2) Add purchase
                3) Show list of purchases
                4) Balance
                5) Save
                6) Load
                0) Exit
                """);
    }

    public void showCategoryMenu() {
        System.out.print("""
                Choose the type of purchase
                1) Food
                2) Clothes
                3) Entertainment
                4) Other
                5) Back
                """);
    }

    public void showCategoryListMenu() {
        System.out.print("""
                Choose the type of purchases
                1) Food
                2) Clothes
                3) Entertainment
                4) Other
                5) All
                6) Back
                """);
    }

    public void addIncome() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter income:");
        double income = Double.parseDouble(scanner.nextLine());
        account.setIncome(income);
        System.out.println();
    }

    public void addPurchase() {
        Scanner scanner = new Scanner(System.in);
        boolean working = true;

        while (working) {
            Purchase purchase = new Purchase();
            showCategoryMenu();
            int option = Integer.parseInt(scanner.nextLine());
            System.out.println();

            switch (option) {
                case 1 -> purchase.setCategory("Food");
                case 2 -> purchase.setCategory("Clothes");
                case 3 -> purchase.setCategory("Entertainment");
                case 4 -> purchase.setCategory("Other");
                case 5 -> {
                    working = false;
                    continue; // Permite volver al menú anterior
                }
                default -> {
                    System.out.println("Invalid option");
                    System.out.println();
                    continue; // Vuelve a mostrar el menú hasta que se de una opción correcta
                }
            }

            System.out.println("Enter purchase name:");
            String purchaseName = scanner.nextLine();
            System.out.println("Enter its price:");
            double purchasePrice = Double.parseDouble(scanner.nextLine());
            System.out.println("Purchase was added!");
            System.out.println();

            account.setExpense(purchasePrice);
            purchase.setName(purchaseName);
            purchase.setPrice(purchasePrice);
            purchasesList.add(purchase);
        }
    }

    public void showListOfPurchases() {
        Scanner scanner = new Scanner(System.in);
        boolean working = true;
        while (working) {
            showCategoryListMenu();
            int option = Integer.parseInt(scanner.nextLine());
            System.out.println();
            switch (option) {
                case 1 -> listOfPurchasesByCategory("Food");
                case 2 -> listOfPurchasesByCategory("Clothes");
                case 3 -> listOfPurchasesByCategory("Entertainment");
                case 4 -> listOfPurchasesByCategory("Other");
                case 5 -> listOfAllPurchases();
                case 6 -> working = false;
                default -> System.out.println("Invalid option");
            }
        }
        System.out.println();
    }

    public void listOfPurchasesByCategory(String category) {
        ArrayList<Purchase> purchasesAux = new ArrayList<>();
        double totalSum = 0;

        System.out.printf("%s:\n", category);
        for (Purchase purchase : purchasesList) {
            if (purchase.getCategory().equals(category)) {
                purchasesAux.add(purchase);
            }
        }

        if (purchasesAux.isEmpty()) {
            System.out.println("The purchase list is empty!");
            System.out.println();
        } else {
            for (Purchase purchase : purchasesAux) {
                System.out.println(purchase);
                totalSum += purchase.getPrice();
            }
            System.out.printf("Total sum: $%.2f\n", totalSum);
            System.out.println();
        }
    }

    public void listOfAllPurchases() {
        double totalSum = 0;

        if (!purchasesList.isEmpty()) {
            for (Purchase purchase : purchasesList) {
                System.out.println(purchase);
                totalSum += purchase.getPrice();
            }
            System.out.printf("Total sum: $%.2f\n", totalSum);
            System.out.println();
        } else {
            System.out.println("The purchase list is empty!");
            System.out.println();
        }
    }

    public void showBalance() {
        System.out.printf("Balance: $%.2f%n", account.getBalance());
        System.out.println();
    }

    public void saveFile () {
        FileManager filePurchases = new FileManager("src/main/resources/purchases.txt");
        filePurchases.savePurchases(purchasesList);

        FileManager fileBalance = new FileManager("src/main/resources/balance.txt");
        fileBalance.saveBalance(account);
    }

    public void loadFile() {
        FileManager filePurchases = new FileManager("src/main/resources/purchases.txt");
        this.purchasesList = filePurchases.loadPurchases(purchasesList);

        FileManager fileBalance = new FileManager("src/main/resources/balance.txt");
        fileBalance.loadBalance(account);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            showMainMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            System.out.println();

            switch (choice) {
                case 1 -> addIncome();
                case 2 -> addPurchase();
                case 3 -> showListOfPurchases();
                case 4 -> showBalance();
                case 5 -> saveFile();
                case 6 -> loadFile();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice");
            }
        }
        System.out.println("Bye!");
    }
}
