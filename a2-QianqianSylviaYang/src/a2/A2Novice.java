package a2;

import java.util.Scanner;

public class A2Novice {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// Your code here.
		int numberOfItems;
		
		String[] itemName;
		double[] itemPrice;
		String[] itemVeg;
		double[] itemCal;
		
		int numberOfVeg = 0;
		
		String[] name = new String[2];
		double[] calPerPrice = new double[2];
		
		numberOfItems = scan.nextInt();
		
		itemName = new String[numberOfItems];
		itemPrice = new double[numberOfItems];
		itemVeg = new String[numberOfItems];
		itemCal = new double[numberOfItems];
		
		for(int m = 0; m < numberOfItems; m++) {
			itemName[m] = scan.next();
			itemPrice[m] = scan.nextDouble();
			itemVeg[m] = scan.next();
			itemCal[m] = scan.nextDouble();
			
			if(itemVeg[m].equals("true")) {
				numberOfVeg = numberOfVeg + 1;
			}
			
			if(m==0) {
				name[0] = itemName[0];
				name[1] = itemName[0];
				calPerPrice[0] = itemCal[0]/itemPrice[0];
				calPerPrice[1] = itemCal[0]/itemPrice[0];
			}else {
				if(itemCal[m]/itemPrice[m] > calPerPrice[0]) {
					name[0] = itemName[m];
					calPerPrice[0] = itemCal[m]/itemPrice[m];
				}else if(itemCal[m]/itemPrice[m] < calPerPrice[1]) {
					name[1] = itemName[m];
					calPerPrice[1] = itemCal[m]/itemPrice[m];
				}
			}
		}
		
		/*System.out.println("Number of vegetarian ingrediants: " + numberOfVeg);
		System.out.println("Highest cals/$: " + name[0]);
		System.out.println("Lowest cals/$: " + name[1]);
		*/
		
		System.out.println("Number of vegetarian ingredients: " + numberOfVeg 
				             + "\n" + "Highest cals/$: " + name[0] 
				            		 + "\n" + "Lowest cals/$: " + name[1]);
	}
	
	// You can define helper methods here if needed.
	
}
