package file;

import model.Account;
import model.Purchase;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private String path;

    public FileManager(String path) {
        this.path = path;
    }

    public void savePurchases(ArrayList<Purchase> purchases) {
        File file = new File(path);
        ObjectOutputStream objectOutputStream = null;

        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            for (Purchase purchase : purchases) {
                objectOutputStream.writeObject(purchase);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error. File not found.");
        } catch (IOException e) {
            System.out.println("Error. I/O error.");
        }
        System.out.println("Purchses were saved!");
        System.out.println();
    }

    public ArrayList<Purchase> loadPurchases(ArrayList<Purchase> purchases) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))) {
            while (true) {
                try {
                    Purchase purchase = (Purchase) objectInputStream.readObject(); // Leer cada objeto Purchase
                    purchases.add(purchase); // Añadir a la lista
                } catch (EOFException e) {
                    break; // Fin del archivo alcanzado
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while loading the file: " + e.getMessage());
            System.out.println();
        }
        return purchases;
    }

    public void saveBalance(Account account) {
        File file = new File(path);
        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(String.valueOf(account.getBalance()));
        } catch (IOException e) {
            System.out.println("An error occurred while saving the balance: " + e.getMessage());
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred while closing the file: " + e.getMessage());
            }
        }
    }

    public void loadBalance(Account account) {
        File file = new File(path);
        double balance = 0;

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            balance = Double.parseDouble(bufferedReader.readLine());
        } catch (FileNotFoundException e) {
            System.out.println("Error. File not found." + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error. I/O error." + e.getMessage());
        }
        account.setBalance(balance);
    }

}
