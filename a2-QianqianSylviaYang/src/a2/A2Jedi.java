package a2;

import java.util.Scanner;

public class A2Jedi {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// Your code here.
int numberOfIngTotal;
		
		String[] ingName;
		double[] ingPrice;
		String[] ingVeg;
		double[] ingCal;
		double[] ingAmount;
		
		int numberOfFood;
		int numberOfIng;
		
		String[] foodName;
		double[][] foodIngAmount;
		int[] foodAmount;
		
		String nameIng;
		double amountIng;
		
		String command;
		String name;
		
		String[] ingAmountString;
		
		numberOfIngTotal = scan.nextInt();
		
		ingName = new String[numberOfIngTotal];
		ingPrice = new double[numberOfIngTotal];
		ingVeg = new String[numberOfIngTotal];
		ingCal = new double[numberOfIngTotal];
		ingAmount = new double[numberOfIngTotal];
		ingAmountString = new String[numberOfIngTotal];
		
		for(int m = 0; m < numberOfIngTotal; m++) {
			ingName[m] = scan.next();
			ingPrice[m] = scan.nextDouble();
			ingVeg[m] = scan.next();
			ingCal[m] = scan.nextDouble();
		}
		
		numberOfFood = scan.nextInt();
		foodName = new String[numberOfFood];
		foodIngAmount = new double[numberOfIngTotal][numberOfFood];
		foodAmount = new int[numberOfFood];
		
		for(int n = 0; n < numberOfFood; n++) {
			foodName[n] = scan.next();
			foodAmount[n] = 0;
			
			numberOfIng = scan.nextInt();
			for(int p = 0; p < numberOfIng; p++) {
				nameIng = scan.next();
				amountIng = scan.nextDouble();
				
				for(int q = 0; q < numberOfIngTotal; q++) {
					if(nameIng.equals(ingName[q])) {
						foodIngAmount[q][n] = amountIng;
					}
				}
			}
		}
		
		command = scan.next();
		while(!command.equals("EndOrder")) {
			name = command;
			for(int b = 0; b < numberOfFood; b++) {
				if(name.equals(foodName[b])) {
					foodAmount[b] = foodAmount[b] + 1;
				}
			}
			command = scan.next();
		}
		
		for(int i = 0; i < numberOfIngTotal; i++) {
			ingAmount[i] = 0;
			for(int h = 0; h < numberOfFood; h++) {
				ingAmount[i] = ingAmount[i] + foodIngAmount[i][h]*foodAmount[h];
			}
		}
		
		System.out.println("The order will require:");
		for(int k = 0; k < numberOfIngTotal; k++) {
			ingAmountString[k] = String.format("%.2f", ingAmount[k]);
			System.out.println(ingAmountString[k] + " ounces of " + ingName[k]);
		}
		
		
	}
	
	// You can define helper methods here if needed.
	
}
 