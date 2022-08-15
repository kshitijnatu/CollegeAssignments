package natukshitijproject3;

/**
 *
 * @author kshit
 */
public class CDs extends Product{

    public static final String TYPE_OF_ITEM = "CD";

    public CDs(String name, String type, String author, int quantity, double price, int id) {
        super(name, type, author, quantity, price, id);
    }
    /**
     * Returns the name of the cd
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }
    
    /**
     * Returns the id of the cd
     * @return name
     */

    @Override
    public int getId() {
        return id;
    }
    /**
     * Returns the type of the item
     * @return name
     */

    @Override
    public String getType() {
        return type;
    }
    /**
     * Returns the price of the cd
     * @return name
     */

    @Override
    public double getPrice() {
        return price;
    }
    /**
     * Returns the quantity of the cd
     * @return name
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

    /**
     * Removes the number of cds of that particular cd by the parameter amount
     * @param quantity 
     */
    @Override
    public void removeItems(int quantity) {
        this.quantity -= quantity;
    }

    /**
     * Returns the name of the musician of that particular cd
     * @return name of musician
     */
    @Override
    public String getAuthor() {
        return author;
    }
    
    
}
