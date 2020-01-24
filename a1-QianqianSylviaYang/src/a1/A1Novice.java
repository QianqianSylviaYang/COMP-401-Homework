package a1;

import java.util.Scanner;

public class A1Novice {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// Your code goes here.
		int numberOfPeople;
		int numberOfItems;
		String outPut = "";
		String firstName;
		String lastName;
		String initial;
		int number;
		double price;
		String name;
		double sum = 0;
		String sumFinal;
		
		//Find out how many customers there are
		numberOfPeople = scan.nextInt();
		
		//Loop for each person
		for(int i = 0; i < numberOfPeople; i++) {
			//initialize the sum for each person as everyone starts from 0
			sum = 0;
			
			firstName = scan.next();
			lastName = scan.next();
			numberOfItems = scan.nextInt();
			initial = firstName.substring(0,1);
			
			//Loop for each item
			for(int j = 0; j < numberOfItems; j++) {
				number = scan.nextInt();
				name = scan.next();
				price = scan.nextDouble();
				/*
				 * The total money a person spent on a item is 
				 * the sum of number of a item times the price of it.
				 * Since this loop for each item,
				 * the money spent for each item adds on.
				 * We get the total amount of money a person spent.
				 */
				sum = sum + number*price;
			}
			//Change to a string to change to two decimals
			sumFinal = String.format("%.2f", sum);
			/*
			 * Adds up all the strings.
			 * Since this loop for each person, 
			 * the final result will be an output
			 * contains all the information we needed.
			 */
			outPut = outPut + initial + ". " + lastName + ": " + sumFinal + "\n";
			
		}
		
		System.out.println(outPut);
		
	}
	
	
}
