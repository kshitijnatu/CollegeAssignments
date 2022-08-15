package natukshitijproject3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * This is the bookstore test harness where my 3 functionalities will be tested.
 * These functionalities are purchasing, adding inventory, and adding members to
 * the bookstore
 * 
 * @author kshitij natu
 *
 */
public class BookstoreTestHarness {

    /**
    * The main method runs the three functionalities by taking a integer user input
    * and using if-else if conditions to run a specific part of the bookstore
    * 
    * @param args
    */
    public static void main(String[] args) {
        try {
            // creates a Scanner object called input which takes in user input
            Scanner input = new Scanner(System.in);
            // creates a Member object called member which allows the program to create new members
            Members members = new Members();
            // creates a Inventory object called inventory which allows the program to add
            // and print the list of items for sale
            Inventory inventory = new Inventory();
            // creates a Purchase object called purchase which allows the user to buy items and checkout
            Purchase purchase = new Purchase();
            
            File endOfDayReport = new File("EndOfDayReport.txt");
            File updatedInventory = new File("updatedInventory.csv");
            
            purchase.setInventory(inventory);
            purchase.setMembers(members);
            
            int response = 0;
            do {
                // List of functionalities the program can do
                System.out.println("1: Add Inventory");
                System.out.println("2: Remove Inventory");
                System.out.println("3: Display Inventory");
                System.out.println("4: Add Member");
                System.out.println("5: Display Members");
                System.out.println("6: Checkout");
                System.out.println("7: Restock Items");
                System.out.println("8: Exit");
                
                // reads the user input
                response = input.nextInt();
                
                switch (response) {
                    case 1:
                        System.out.println();
                        // calls the addInventory method to add new items to the inventory or to
                        // increase the amount of a pre-existing item
                        inventory.addInventory();
                        break;
                    case 2:
                        //Removes a particular item from the inventory
                        inventory.removeInventory();
                        break;
                    case 3:
                        System.out.println();
                        // prints the list of items the bookstore sells
                        inventory.printItems();
                        break;
                    case 4:
                        System.out.println();
                        // allows the program to create a new member of the bookstore
                        members.registerMember(false);
                        break;
                    case 5:
                        System.out.println();
                        // prints the list of members that have an membership with the bookstore
                        members.printMembers();
                        break;
                    case 6:
                        System.out.println();
                        // allows the user to buy items from the bookstore and checkout using a
                        // registered member or as a guest
                        purchase.checkout();
                        break;
                    case 7:
                        System.out.print("Enter the id of your item: ");
                        // takes in name of product
                        int id = input.nextInt();
                        System.out.print("Enter the amount of the item you want to add: ");
                        // takes in quantity of product
                        int quantity = input.nextInt();
                        int result = inventory.restockProduct(id, quantity);
                        if(result == 0){
                            System.out.println("Restock failed for id: " + id);
                        }
                        else{
                            System.out.println("Restock is successful. New quantity: " + result);
                        }
                        break;
                    case 8:
                        //creates an updated inventory list after the program exits to see what items are remaining
                        inventory.updatedInventory(updatedInventory);
                        //Lists what items were bought and how much of that item is gone
                        purchase.itemsBought(endOfDayReport);
                        //Lists what new members created an account with the bookstore. Does not list guest members
                        members.printNewMembers(endOfDayReport);
                        break;
                }
            } while (response != 8);
            
            input.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Uh oh the file could not be found. Please enter the correct pathname or make sure you put the file in th correct project folder!");
        } catch(IOException ex){
            System.out.println("Uh oh something went wrong with the file. Please try again!");
        } catch(Exception ex){
            System.out.println("Uh oh something went wrong. Please try again!");
        }
    }

}
