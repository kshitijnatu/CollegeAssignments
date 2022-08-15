package natukshitijproject3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class allows the user to purchase items from the bookstore and calculate
 * the total price of the items bought
 * 
 * @author kshitij
 *
 */
public class Purchase {
    private double total;
    private Inventory inventory = null;
    private Members members = null;
    private static final ArrayList<Product> itemsBought = new ArrayList<>();

    /**
    * This method calculates the prices of the items bought by the user
    * 
    * @param cartItems
    * @return total
    */
    public double calculateTotal(ArrayList<Product> cartItems) {
	for (var product : cartItems) {
		total += product.getPrice() * product.getQuantity();
	}
	return total;
    }

    /**
    * This method sets the inventory to the purchase class so that the inventory
    * can update as soon as a user buys items from the bookstore
    * 
    * @param inventory
     */
    public void setInventory(Inventory inventory) {
	this.inventory = inventory;
    }

    /**
    * This method sets the members to the purchase class so that the purchase class
    * can access an individual member to see if they are a guest, regular or
    * premium member
    * 
    * @param members
    */
    public void setMembers(Members members) {
	this.members = members;
    }

    /**
    * This method allows the user to pick out the items they would like to buy and
    * store it inside an ArrayList called cartItems. These items would then be
    * passed inside the calculateTotal method to find the total price of the items
    * bought
    */
    public void checkout() {
	Scanner input = new Scanner(System.in);
	System.out.println("");

	// creates an empty ArrayList for members to put their items in
	ArrayList<Product> cartItems = new ArrayList<>();
	ArrayList<Product> storeItems = inventory.getItems();
	total = 0;

	System.out.println("Welcome to my bookstore! Here's the list of items we sell: ");
	// prints out the list of items
	inventory.printItems();
	System.out.println("Press enter to get started");
	String response1 = input.nextLine();
	// checks to see if the user entered yes or no (y/n)
        int response;
	while (!response1.equalsIgnoreCase("n")) {
            System.out.print("Enter the item number of the item from the list: ");
            while (true) {                
                try {
                    // takes in the user's choice of which item they want and validates whether that
                    // item number is on the list
                    response = checkNums(input.nextInt(), storeItems.size() - (storeItems.size() - 1), storeItems.size());
                    break;
                } catch (InputMismatchException e) {
                    System.out.print("Please enter a valid integer number: ");
                    input.nextLine();
                }
            }
//            System.out.print("Enter the item number of the item from the list: ");    
//            // takes in the user's choice of which item they want and validates whether that
//            // item number is on the list
//            int response = checkNums(input.nextInt(), storeItems.size() - (storeItems.size() - 1), storeItems.size());
            Product storeItem = storeItems.get(response - 1);
            System.out.print("How many of that item do you want: ");
            int amount;
            while (true) {                
                try {
                    amount = checkNums(input.nextInt(), 0, storeItems.get(response - 1).getQuantity());
                    break;
                } catch (InputMismatchException e) {
                    System.out.print("Please enter a valid integer number: ");
                    input.nextLine();                    
                }
            }
            
            Product cartItem = getProduct(storeItem, amount);
            // adds the user's item to their shopping cart (ArrayList cartItems)
            cartItems.add(cartItem);
            itemsBought.add(cartItem);
            // removes the amount of items the user bought from the inventory
            inventory.removeItems(storeItem.getName(), amount);
            input.nextLine();
            System.out.print("Do you want to add more items? (y/n): ");
            response1 = input.nextLine();
        }

	Member found = members.registerMember(true);

	//double total = calculateTotal(cartItems);
        double total = inventory.roundNum(calculateTotal(cartItems));
	// checks to see if the member is premium and has paid their fees
	if (found.isPremium() && !found.hasPaid()) {
            System.out.println("Adding premium membership fees $" + found.getFee() + " to the total");
            total += found.getFee();
        }
	System.out.println("Your total is: $" + total);
	if (found.isGuest() || (!found.isPremium())) {
            // checks whether the user is a guest
            System.out.println("No Card on File");
            found.setTotalPurchaseAmount(total);
	} else {
            System.out.print("$" + total + " was subtracted using card on file: ");
            // displays the card number set by the user
            found.displayCardNum(found.getCardNum());
            found.setTotalPurchaseAmount(total);
            System.out.println();
	}
    }

    /**
     * 
     * @param response
     * @param lowerBound
     * @param upperBound
     * @return the correct integer that is within bounds 
     */
    public int checkNums(int response, int lowerBound, int upperBound) {
        Scanner input = new Scanner(System.in);
        // checks to see if the number the user enters is valid
        while (response < lowerBound || response > upperBound) {
            System.out.print("Please enter a valid number: ");
            // asks for user input
            response = input.nextInt();
        }
        return response;
    }
        
    public Product getProduct(Product storeItem, int amount){
        Product newItem = null;
        switch (storeItem.getType()) {
            case Books.TYPE_OF_ITEM:
                newItem = new Books(storeItem.getName(), storeItem.getType(), storeItem.getAuthor(), amount, storeItem.getPrice(), storeItem.getId());
                break;
            case CDs.TYPE_OF_ITEM:
                newItem = new CDs(storeItem.getName(), storeItem.getType(), storeItem.getAuthor(), amount, storeItem.getPrice(), storeItem.getId());
                break;
            case DVDs.TYPE_OF_ITEM:
                newItem = new DVDs(storeItem.getName(), storeItem.getType(), storeItem.getAuthor(), amount, storeItem.getPrice(), storeItem.getId());
                break;
            default:
                break;
        }
        return newItem;
    }
    
    /**
     * This method prints out the items that were bought and how many of that particular item was bought
     * @param endOfDayReport
     * @throws FileNotFoundException 
     */
    public void itemsBought(File endOfDayReport) throws FileNotFoundException{
        PrintWriter writer = new PrintWriter(new FileOutputStream(endOfDayReport));
        writer.println("Here's the items that were bought: ");
        writer.println("--------------------------------------------");
        for (Product product : itemsBought) {
            writer.println(product.getName() + ": (" + product.getQuantity() + ")");
        }
        writer.println();
        writer.close();
    }
}
