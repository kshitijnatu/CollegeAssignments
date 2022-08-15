package natukshitijproject3;

/**
 *
 * @author kshit
 */
public class DVDs extends Product{

    public static final String TYPE_OF_ITEM = "DVD";
    public DVDs(String name, String type, String author, int quantity, double price, int id) {
        super(name, type, author, quantity, price, id);
    }

    /**
     * Returns the name of the dvd
     * @return name
     */
    
    @Override
    public String getName() {
        return name;
    }
    /**
     * Returns the id of the dvd
     * @return id
     */

    @Override
    public int getId() {
        return id;
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
     * Returns the price of the dvd
     * @return price
     */

    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Returns the quantity of dvd
     * @return quantity
     */
    @Override
    public int getQuantity() {
        return quantity;
    }
    /**
     * Increases quantity of cd
     * @param quantity
     */

    @Override
    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }
    /**
     * Decreases quantity of cd
     * @param quantity
     */

    @Override
    public void removeItems(int quantity) {
        this.quantity -= quantity;
    }

    /**
     * Returns the name of the director of that particular movie
     * @return name
     */
    @Override
    public String getAuthor() {
        return author;
    }
    
    
}
