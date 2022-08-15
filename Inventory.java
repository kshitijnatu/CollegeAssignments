package natukshitijproject3;

//import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * This class maintains the inventory of the bookstore, from creating the
 * inventory to adding and removing items from the inventory when the customer
 * chooses the specific amount of a specific item from the bookstore. Uses an
 * private ArrayList of Product objects called items to store the items created
 * in the inventory class. Uses an private int field called totalQuantity to
 * keep track of the total quantity of all of the items in the book store.
 * 
 * @author kshitij
 *
 */
public class Inventory implements BookstoreSpecification{
    private static ArrayList<Product> items = new ArrayList<>();
    private int totalQuantity = 0;

    /**
     * Default constructor that creates the items of the bookstore and adds those
     * items to the items field
     * @throws java.io.FileNotFoundException
     */
    public Inventory() throws FileNotFoundException {
        Scanner input = new Scanner(new File("ProductInventory.csv"));;
        input.nextLine();
        while (input.hasNext()) {
            String next = input.nextLine();
            next = next.trim();
            int firstComma = next.indexOf(",");
            int id = Integer.parseInt(next.substring(0, firstComma));
            
            int startType = firstComma + 1;
            int endType = next.indexOf(",", startType);
            String type = next.substring(startType, endType).toUpperCase();
            
            int startName = endType + 1;
            int endName = next.indexOf(",", startName);
            String name = next.substring(startName, endName).toUpperCase();
            
            
            int startAuthor = endName + 1;
            int endAuthor = next.indexOf(",", startAuthor);
            String author = next.substring(startAuthor, endAuthor);
            
            int startQuantity = endAuthor + 1;
            int endQuantity = next.indexOf(",", startQuantity);
            int quantity = Integer.parseInt(next.substring(startQuantity, endQuantity));
            
            double price = Double.parseDouble(next.substring(endQuantity + 1));
            
            switch (type) {
                case "BOOK":
                    Books book = new Books(name, type, author, quantity, price, id);
                    totalQuantity += book.getQuantity();
                    items.add(book);
                    break;
                case "CD":
                    CDs cd = new CDs(name, type, author, quantity, price, id);
                    totalQuantity += cd.getQuantity();
                    items.add(cd);
                    break;
                case "DVD":
                    DVDs dvd = new DVDs(name, type, author, quantity, price, id);
                    totalQuantity += dvd.getQuantity();
                    items.add(dvd);
                    break;
            }
        }
    }

    /**
    * This method removes items from the ArrayList by subtracting the amount
    * requested by the user from the total amount that specific item has. It also
    * subtracts from the total quantity of all of the items and returns the new
    * total quantity
    * 
    * @param name
    * @param amount
    */
    public void removeItems(String name, int amount) {
        //iterates through the ArrayList of items 
        for (int i = 0; i < items.size(); i++) {
            //if the name of the item matches any of the names of the items
            if (name.equals(items.get(i).getName())) {
                items.get(i).removeItems(amount);
                totalQuantity = getTotalQuantity() - amount;
                break;
            }
        }
    }

    /**
    * This method prints the items of the bookstore. It shows the name, type, price, and
    * quantity of all of the items
    */
    public void printItems() {
	Collections.sort(items);
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getName() + " (" + items.get(i).getType() + ") - $"
                    + items.get(i).getPrice() + " (" + items.get(i).getQuantity() + ")");
            }
            System.out.println("Current Inventory Value: $" + inventoryValue());
	}
        
    /**
    * Returns the total quantity of all of the items in the bookstore
    * 
    * @return totalQuantity
    */
    public int getTotalQuantity() {
	return totalQuantity;
    }

    /**
    * Returns the ArrayList of Product objects (items of the bookstore)
    * 
    * @return items
    */
    public ArrayList<Product> getItems() {
        Collections.sort(items);
        return items;
    }

    /**
    * This method adds new or preexisting items inside the ArrayList items field.
    */
    public void addInventory() {
	Scanner input = new Scanner(System.in);

	System.out.print("Enter the name of your item: ");
	// takes in name of product
	String name = input.nextLine();
	System.out.print("Enter the type of item it is: ");
	// takes in type of product
	String type = input.nextLine();
        System.out.println("Enter the author of the item: ");
        String author = input.nextLine();
	System.out.print("Enter the price of your item: ");
	// takes in price of product
	double price = input.nextDouble();
	System.out.print("Enter the amount of the item you want to add: ");
	// takes in quantity of product
	int quantity = input.nextInt();
        System.out.print("Enter the id of the item: ");
        int id = input.nextInt();

	// if restock returns 0, the method could not find the item
        boolean found = (0 < restockProduct(id, quantity));
	// if this item is not inside the list, create a new product and add it into the
	// ArrayList
	if (!found) {
            //Product newProduct = new Product(name, type, quantity, price);
            if(type.equalsIgnoreCase(Books.TYPE_OF_ITEM)){
                Books newProduct = new Books(name, type, author, quantity, price, id);
                items.add(newProduct);
            }
            else if(type.equalsIgnoreCase(CDs.TYPE_OF_ITEM)){
                CDs newProduct = new CDs(name, type, author, quantity, price, id);
                items.add(newProduct);
            }
            else {
                DVDs newProduct = new DVDs(name, type, author, quantity, price, id);
                items.add(newProduct);
            }
			
        }
        // prints out the new list of items
	printItems();
	System.out.println();
    }

    /**
     * This method allows the user (owner) to input the number of items they want to restock in their bookstore. 
     * @param productID
     * @param amount
     * @return the quantity of the item the owners wants restocked 
     */
    @Override
    public int restockProduct(int productID, int amount) {
        int quantity = 0;
        for(Product i : items){
            if(i.getId() == productID){
                i.increaseQuantity(amount);
                quantity = i.getQuantity();
                break;
            }
        }
        return quantity;
    }

    /**
     * 
     * @return the total amount of all of the items in the bookstore 
     */
    @Override
    public double inventoryValue() {
        double total = 0;
        for(Product i : items){
            total += i.getPrice() * i.getQuantity();
        }
        return roundNum(total);
    }
    
    public double roundNum(double num){
        num *= 1000;
        int lastNum = (int) (num) % 10;
        num /= 10;
        if(lastNum >= 5){
            num += 1;
        }
        num = (int) num;
        num = (double) num / 100;
        return num;
    }
    
    /**
     * This method allows the user (owner) to remove a certain item from the bookstore using the name and product id
     */
    public void removeInventory(){
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter the item you want to remove: ");
        String name = input.nextLine();
        System.out.print("Enter the item's id: ");
        int id = input.nextInt();
        boolean found = false;
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getName().equalsIgnoreCase(name) && items.get(i).getId() == id){
                found = true;
                items.remove(i);
                printItems();
                break;
            }
        }
        if(!found){
            System.out.println("This item is not part of the inventory!");
        }
    }
    
    /**
     * This method updates the inventory list at the end of the day (when the user exits the program)
     * Writes to a new file called "UpdatedInventory.csv"
     * @param updatedInventory
     * @throws FileNotFoundException 
     */
    public void updatedInventory(File updatedInventory) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(updatedInventory);
        writer.println("productID,type,title,author/artist,numInStock,price");
        for (Product item : items) {
            writer.println(item.getId() + "," + item.getType().toLowerCase() + "," + item.getName().toLowerCase() + "," + item.getAuthor().toLowerCase() + "," + item.getQuantity() + "," + item.getPrice());
        }
        writer.close();   
    }
}
