package natukshitijproject3;

/**
 *
 * @author kshit
 */

/**
 * This class creates the blueprint of products of the bookstore and has methods to remove
 * and increase the quantities of the products. It has methods that are implemented into their subclasses which return
 * key fields for each of the subclasses
 * 
 * @author kshitij
 *
 */
public abstract class Product implements Comparable<Product>{

    public final String name, type, author;
    public final double price;
    public int quantity, id;

    public Product(String name, String type, String author, int quantity, double price, int id) {
        this.name = name;
        this.type = type;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
        this.id = id;
    }

    /**
     * All of the methods that will be implemented into all of the subclasses
     * @return name, id, type, price, quantity,  
     */
    public abstract String getName();
    public abstract int getId();
    public abstract String getType();
    public abstract String getAuthor();
    public abstract double getPrice();
    public abstract int getQuantity();
    public abstract void increaseQuantity(int quantity);
    public abstract void removeItems(int quantity);

    
    @Override
    public int compareTo(Product o) {
        if(this.getQuantity() < o.getQuantity()){
            return 1;
        }
        else if(this.getQuantity() > o.getQuantity()){
            return -1;
        }
        else{
            return 0;
        }
    }

    
    
}
