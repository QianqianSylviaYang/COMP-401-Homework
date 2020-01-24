package a1;

import java.util.Scanner;

public class A1Adept {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// Your code goes here.
		//the lists that contains the name and money spent that will be printed in the result
		String[] names = new String[2];
		double[] money = new double[2];
		//the list of all the prices and items in the system
		String[] items;
		double[] prices;
		//name of customer
		String customer;
		String firstName;
		String lastName;
		//the money spent by a customer
		double sum = 0;
		//the total money spent
		double sums = 0;
		//the average amount of money
		double average;
		//number of customers 
		int numberOfCustomers;
		//the types of objects that a customer bought
		int typesOfObjects;
		//types of objects in the system
		int typesOfItems;
		double amount;
		String itemName;
		double price = 0;
				
		typesOfItems = scan.nextInt();
		items = new String[typesOfItems];
		prices = new double[typesOfItems];
		
		/*
		 * Repeat for each item on the menu.
		 * The name of the item and the price of the item
		 * is stored at the corresponding position in the two arrays.
		 */
		for(int i = 0; i < typesOfItems; i++) {
			items[i] = scan.next();
			prices[i] = scan.nextDouble();
		}
				
		
		numberOfCustomers = scan.nextInt();
		//Loop for each customer.
		for(int j = 0; j < numberOfCustomers; j++) {
			firstName = scan.next();
			lastName = scan.next();
			customer = firstName + " " + lastName;
					
			//find the total amount spent by a customer
			typesOfObjects = scan.nextInt();
			//If not object bought, then the money spent is 0.
			if(typesOfObjects==0) {
				sum = 0;
			}
			//Loop for each kind of item a person bought.
			for(int k = 0; k < typesOfObjects; k++) {
				amount = scan.nextDouble();
				itemName = scan.next();
				/*
				 * Find the price.
				 * Loop for each item in the item array,
				 * to find the item with the same name.
				 * The price of this kind of item
				 * is at the corresponding position in the price array.
				 */
				for(int p = 0; p < items.length; p++) {
					if(itemName.equals(items[p])) {
						price = prices[p];
					}
				}
				
				sum = sum + price*amount;
			}
					
			//initialize the arrays so that if there is only one customer, the largest and the smallest will be the same
			if(j==0) {
				money[0] = sum;
				money[1] = sum;
				names[0] = customer;
				names[1] = customer;
			}
					
			//compare the money spent, so that if it is the largest it will be in position 0 in both arrays
			//and if it is the smallest it will be in position 1
			if(sum > money[0]) {
				money[0] = sum;
				names[0] = customer;
			}else if(sum < money[0]) {
				if(sum < money[1]) {
					money[1] = sum;
					names[1] = customer;
				}
			}
					
			//find the total amount spent by all the customers
			sums = sums + sum;
			//initialize sum
			sum = 0;
		}
				
		//find the average 
		average = sums/numberOfCustomers;
				
		//print out the final result
		System.out.println("Biggest: " + names[0] + " (" + String.format("%.2f", money[0]) + ")");
		System.out.println("Smallest: "+ names[1] + " (" + String.format("%.2f", money[1]) + ")");
		System.out.println("Average: " + String.format("%.2f", average));
		
	}
	
	// You can define / use static helper methods here.
	
}
