package natukshitijproject3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class maintains the members of the book store. The types of members
 * include guest, regular, and premium.
 * 
 * @author kshitij
 *
 */
public class Members {
    private static ArrayList<Member> members = new ArrayList<>();

    /**
    * Default constructor that create 3 pre-exisiting members of the book store (2
    * premium and 1 regular)
    */
    public Members() {
	// premium member
	Member john = new Member("John", true, "123456789876543", "Amex", "7043573355", false);
        john.setGuest(false);
	members.add(john);
	// regular member
	Member suzie = new Member("Suzie", false, "9876543212365498", "Mastercard", "9804365514", true);
        suzie.setGuest(false);
	members.add(suzie);
	// premium member
	Member jack = new Member("Jack", true, "7539517412589630", "Visa", "7046541234", false);
        jack.setGuest(false);
	members.add(jack);
    }

    /**
    * Returns the ArrayList of Member objects.
    * 
    * @return members
    */
    public ArrayList<Member> getMembers() {
	return members;
    }

    /**
    * This method finds if the user is a pre-existing member of this bookstore and
    * returns null if they are a new customer
    * 
    * @param name   
    * @param phoneNum
    * @return member
    */
    public Member find(String name, String phoneNum) {
	// iterates through the list of members
	for (int i = 0; i < members.size(); i++) {
            // if the name and phone number matches anything in the list, then return the
            // member. Else, return null to when not found
            if (members.get(i).getName().equalsIgnoreCase(name) && members.get(i).getPhoneNum().equals(phoneNum)) {
                return members.get(i);
            }
	}
	return null;
    }

    /**
    * This method allows new members to register to be a premium member of the
    * bookstore. It also allows pre-existing regular members to upgrade to a
    * premium membership by paying a membership fee.
    * 
    * @param allowGuestCheckout
    * @return found
    */
    public Member registerMember(boolean allowGuestCheckout) {
	String response1 = null;
        String cardType;
        String cardNum;
        String validCardNum;

	Scanner input = new Scanner(System.in);
	System.out.print("Please enter your name: ");
	// takes in the user's name
	String name = input.nextLine();
	System.out.print("Please enter your phone number: ");
	// takes in the user's phone number
	String phoneNum = input.nextLine();

	Member found = find(name, phoneNum);
        if (found != null) {
            if (!found.isPremium()) {
                System.out.print("Do you want to upgrade to a premium membership (y/n): ");
                response1 = input.nextLine();
                if (response1.equalsIgnoreCase("y")) {
                    found.setPremium(true);
                    System.out.print("Please enter your card type (Amex, Mastercard, Visa, etc.): ");
                    cardType = input.nextLine();
                    if(cardType.equalsIgnoreCase("Amex")){
                        System.out.print("Please enter your 15 digit card number: ");
                        cardNum = input.nextLine();
                        validCardNum = checkCarNum(cardType, cardNum);
                    }
                    else{
                        System.out.print("Please enter your 16 digit card number: ");
                        cardNum = input.nextLine();
                        validCardNum = checkCarNum(cardType, cardNum);
                    }
                    found.setCardType(cardType);
                    found.setCardNum(validCardNum);
                }
            }
        } else {
            String payAsGuest = "2";
            if (allowGuestCheckout) {
                System.out.println("1. Pay as guest \n2. Registar as member");
                payAsGuest = input.nextLine();
            }

            if (!allowGuestCheckout || payAsGuest.equalsIgnoreCase("2")) {
                cardType = "";
                cardNum = "";
                validCardNum = "";
                System.out.print("Do you want to have a premium membership (y/n): ");
                response1 = input.nextLine();
                boolean isPreimum = response1.equalsIgnoreCase("y");
                if (isPreimum) {
                    System.out.print("Please enter your card type (Amex, Mastercard, Visa, etc.): ");
                    // ask for card type
                    cardType = input.nextLine();
                    if (cardType.equalsIgnoreCase("Amex")) {
                        System.out.print("Please enter your 15 digit card number: ");
                        // ask for card number
                        cardNum = input.nextLine();
                        // validates whether the user entered a valid card number (checks for cardnumber length)
                        validCardNum = checkCarNum(cardType, cardNum);

                    } else {
                        System.out.print("Please enter your 16 digit card number: ");
                        cardNum = input.nextLine();
                        validCardNum = checkCarNum(cardType, cardNum);
                    }

                }
                found = new Member(name, (response1.equalsIgnoreCase("y")), validCardNum, cardType, phoneNum, false);
            } else {
                found = new Member(name, false, "", "", phoneNum, false);
                found.setGuest(true);
            }
            members.add(found);
        }
        return found;
    }

    /**
    * This method prints of the list of members that registered with the bookstore.
    * It also list the guests that don't have a membership with the bookstore
    */
    public void printMembers() {
	for (Member member : members) {
            System.out.println(member.print());
	}
    }

    /**
    * This method validates the card number the user input to see if the length of
    * the card number entered is the acceptable length for the different card
    * brands
    * 
    * @param cardType
    * @param cardNum
    * @return cardNum
    */
    public String checkCarNum(String cardType, String cardNum) {
	Scanner input = new Scanner(System.in);
	// checks whether the user entered Amex as their card type
	if (cardType.equalsIgnoreCase("Amex")) {
            // asks the user to enter a valid card length until the user enters a valid card length
            while (cardNum.length() != 15) {
                System.out.print("Please enter the correct amount of numbers: ");
                cardNum = input.nextLine();
            }
	} else {
            while (cardNum.length() != 16) {
                System.out.print("Please enter the correct amount of numbers: ");
		cardNum = input.nextLine();
            }
	}
	return cardNum;
    }
    
    /**
     * This methods prints out new members that have created an account with the bookstore
     * @param endOfDayReport
     * @throws FileNotFoundException 
     */
    public void printNewMembers(File endOfDayReport) throws FileNotFoundException{
        PrintWriter writer = new PrintWriter(new FileOutputStream(endOfDayReport, true));
        writer.println("Here's the members that have created an account: ");
        writer.println("-------------------------------------------------------");
        
        for (Member member : members) {
            if(!member.isGuest()){
                writer.println(member.getName() + ": Premium Account - " + member.isPremium());
            }
        }
        writer.close();
    }
}
