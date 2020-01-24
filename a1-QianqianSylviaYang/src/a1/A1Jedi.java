package a1;

import java.util.Scanner;

public class A1Jedi {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// Your code goes here.
		//The total types of items 
		int typesOfItemsTotal;
		
		String[] itemNames;
		double[] itemPrices;
		int[] itemTotalNumber;
		int[] itemTotalBuyers;
		int[] searchTimes;
		
		int numberOfCustomers;
		
		String firstName;
		String lastName;
		int typesOfItemsEach;
		
		int itemNumber;
		String itemName;
		
		typesOfItemsTotal = scan.nextInt();
		itemNames = new String[typesOfItemsTotal];
		itemPrices = new double[typesOfItemsTotal];
		itemTotalNumber = new int[typesOfItemsTotal];
		itemTotalBuyers = new int[typesOfItemsTotal];
		searchTimes = new int[typesOfItemsTotal];
		/*
		 * Repeats for each type of item on the list
		 * The name, price, total number bought, total number of buyers
		 *  of each item are stored on the corresponding position
		 *  in each array.
		 *  Initialize the totol number of buyers and number bought 
		 *  as 0.
		 */
		for(int m = 0; m < typesOfItemsTotal; m++) {
			itemNames[m] = scan.next();
			itemPrices[m] = scan.nextDouble();
			itemTotalNumber[m] = 0;
			itemTotalBuyers[m] = 0;
		}
		
		numberOfCustomers = scan.nextInt();
		//Repeat for each customer.
		for(int n = 0; n < numberOfCustomers; n++) {
			firstName = scan.next();
			lastName = scan.next();
			for(int i=0; i<typesOfItemsTotal; i++) {
				searchTimes[i] = 0;
			}
			
			typesOfItemsEach = scan.nextInt();
			//Repeat for each kind of item that a person bought
			for(int p = 0; p < typesOfItemsEach; p++) {
				itemNumber = scan.nextInt();
				itemName = scan.next();
				/*
				 * Searched for the matched item 
				 * by comparing the name in the array.
				 */
				for(int q = 0; q < itemNames.length; q++) {
					/*
					 * If there is a match,
					 * the total number of this item bought is going to add on 
					 * the number of this item that this person bought,
					 * the total number of buyers is going to add one
					 */
					if(itemName.equals(itemNames[q])) {
						itemTotalNumber[q] = itemTotalNumber[q] + itemNumber;
						searchTimes[q] = searchTimes[q]+1;
						if(searchTimes[q]==1) {
						itemTotalBuyers[q] = itemTotalBuyers[q] + 1;
						}
					}
					
				}
			}
		}
		
		for(int s = 0; s < itemNames.length; s++) {
			if(itemTotalBuyers[s] > 0) {
				System.out.println(itemTotalBuyers[s] + " customers bought " + itemTotalNumber[s] + " " + itemNames[s]);
			}else {
				System.out.println("No customers bought " + itemNames[s]);
			}
		}
	}
	// You can define / use static helper methods here.
	
}
