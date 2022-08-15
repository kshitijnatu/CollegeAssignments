package natukshitijproject3;

/**
 * This is the BookstoreSpecification interface which creates what behavior a bookstore should have
 * @author kshit
 */
public interface BookstoreSpecification {
    
    /**
     * This method restocks a item from the bookstore using the productID and the amount the user wants to restock
     * @param productID
     * @param amount
     * @return 
     */
    public int restockProduct(int productID, int amount);
    
    /**
     * 
     * @return total amount, in dollars, of the books, cds, and dvds a booksstore has
     */
    public double inventoryValue();
}
