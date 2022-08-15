/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package natukshitijproject3;

/**
 *
 * @author kshit
 */

/**
 * This class is a subclass of the Products class and create an object called Book and store the name, type, quantity, price, and id
 * @author kshit
 */
public class Books extends Product{

    public static final String TYPE_OF_ITEM = "BOOK";
    public Books(String name, String type, String author, int quantity, double price, int id) {
        super(name, type, author, quantity, price, id);
    }
    
    /**
     * Returns the name of the book
     * @return name
     */
    @Override    
    public String getName() {
        return name;
    }

    /**
     * Returns the type of the item
     * @return type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Returns the price of the book
     * @return price
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Returns the quantity of the book
     * @return quantity
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the id of the book
     * @return id
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * This method increases the quantity of a specific product
     * 
     * @param quantity
     */

    @Override
    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }
    /**
     * This method removes the quantity of a specific product
     * 
     * @param quantity
     */

    /**
     * Removes the number of books of that particular item by the parameter amount
     * @param quantity 
     */
    @Override
    public void removeItems(int quantity) {
        this.quantity -= quantity;
    }

    /**
     * Returns the name of the author of that particular book
     * @return 
     */
    @Override
    public String getAuthor() {
        return author;
    }
    
}
