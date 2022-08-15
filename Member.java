package natukshitijproject3;

/**
 * This class creates a member for the bookstore. There is a constructor that
 * sets the values of the parameters to the fields in the class and getter and
 * setters for each of the fields in this class
 * 
 * @author kshitij
 *
 */
public class Member {
    private boolean premium, hasPaid, guest = false;
    private double fee, totalPurchaseAmount;
    private String cardNum, cardType, phoneNum, name;

    /**
    * This constructor sets the parameters to the fields.
    * 
    * @param name
    * @param isPremium
    * @param cardNum
    * @param cardType
    * @param phoneNum
    * @param hasPaid
    */

    public Member(String name, boolean isPremium, String cardNum, String cardType, String phoneNum, boolean hasPaid) {
	this.name = name;
	this.cardNum = cardNum;
	this.fee = 7.99;
	this.premium = isPremium;
	this.cardType = cardType;
	this.phoneNum = phoneNum;
	this.hasPaid = hasPaid;
    }

    /**
    * Returns the name of the member
    * 
    * @return name
    */
    public String getName() {
	return name;
    }

    /**
     * Sets the String parameter name to the String field name
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
    * Returns whether the member is a premium member or not.
    * 
    * @return premium
    */
    public boolean isPremium() {
        return premium;
    }

    /**
    * Sets the boolean parameter premium to the boolean field premium
    * 
    * @param isPremium
    */
    public void setPremium(boolean isPremium) {
	this.premium = isPremium;
    }

    /**
    * Returns the fee of a premium member
    * 
    * @return fee
    */
    public double getFee() {
	return fee;
    }

    /**
    * Returns the card number of a member
    * 
    * @return cardNum
    */
    public String getCardNum() {
	return cardNum;
    }

    /**
    * Sets the card number of a member
    * 
    * @param cardNum
    */
    public void setCardNum(String cardNum) {
	this.cardNum = cardNum;
    }

    /**
    * Returns the phone number of a member
    * 
    * @return phoneNum
     */
    public String getPhoneNum() {
	return phoneNum;
    }

    /**
    * Returns the card type of a member
    * 
    * @return cardType
    */
    public String getCardType() {
	return cardType;
    }

    /**
    * Sets the card type of a member
    * 
    * @param cardType
    */  
    public void setCardType(String cardType) {
	this.cardType = cardType;
    }

    /**
    * Returns whether a premium member has paid their fees
    * 
    * @return
    */
    public boolean hasPaid() {
	return hasPaid;
    }

    /**
    * Returns whether the member is a guest or not
    *   
    * @return guest
     */
    public boolean isGuest() {
	return guest;
    }

    /**
    * Sets whether the user is a guest or not
    * 
    * @param guest
    */
    public void setGuest(boolean guest) {
	this.guest = guest;
    }

    /**
    * Returns the total purchase amount of a member or a guest
    * 
    * @return totalPurchaseAmount
    */
    public double getTotalPurchaseAmount() {
	return totalPurchaseAmount;
    }

    /**
    * Increments the total purchase amount each time a member or guest user buys
    * items from the bookstore
    * 
    * @param totalPurchaseAmount
    */
    public void setTotalPurchaseAmount(double totalPurchaseAmount) {
	this.totalPurchaseAmount += totalPurchaseAmount;
    }

    /**
     * This method prints out a premium member's last four digits of their card
     * number
     * 
     * @param cardNum
     */
    public void displayCardNum(String cardNum) {
	if (getCardType().equalsIgnoreCase("Amex")) {
            System.out.print("***-****-****-" + cardNum.substring(11));
	} else {
            System.out.print("****-****-****-" + cardNum.substring(12));
	}
    }

    /**
    * This method prints out the users name and the amount they have spent each
    * time they go to the store.
    * 
    * @return output   
    */
    public String print() {
	String output = name + ": $" + totalPurchaseAmount;
	if (premium) {
            output = name + " (Premium): $" + totalPurchaseAmount;
	} else if (guest) {
            output = name + " (Guest): $" + totalPurchaseAmount;
	}
	return output;
    }

}
