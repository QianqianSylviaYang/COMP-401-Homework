package a2;

import java.util.Scanner;

public class A2Adept {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// Your code here.
		int numberOfIngTotal;
		
		String[] ingName;
		double[] ingPrice;
		String[] ingVeg;
		double[] ingCal;
		
		int numberOfFood;
		int numberOfIng;
		
		String[] foodName;
		double[] foodPrice;
		double[] foodCal;
		boolean[] foodVeg;
		String[]foodPrice1;
		int[] foodCal1;
		
		String nameIng;
		double amountIng;
		
		numberOfIngTotal = scan.nextInt();
		
		ingName = new String[numberOfIngTotal];
		ingPrice = new double[numberOfIngTotal];
		ingVeg = new String[numberOfIngTotal];
		ingCal = new double[numberOfIngTotal];
		
		for(int m = 0; m < numberOfIngTotal; m++) {
			ingName[m] = scan.next();
			ingPrice[m] = scan.nextDouble();
			ingVeg[m] = scan.next();
			ingCal[m] = scan.nextDouble();
		}
		
		numberOfFood = scan.nextInt();
		
		foodName = new String[numberOfFood];
		foodPrice = new double[numberOfFood];
		foodCal = new double[numberOfFood];
		foodVeg = new boolean[numberOfFood];
		foodPrice1 = new String[numberOfFood];
		foodCal1 = new int[numberOfFood];
		
		for(int n = 0; n < numberOfFood; n++) {
			foodPrice[n] = 0;
			foodVeg[n] = true;
			
			foodName[n] = scan.next();
			
			numberOfIng = scan.nextInt();
			for(int p = 0; p < numberOfIng; p++) {
				nameIng = scan.next();
				amountIng = scan.nextDouble();
				
				for(int q = 0; q < numberOfIngTotal; q++) {
					if(nameIng.equals(ingName[q])) {
						foodPrice[n] = foodPrice[n] + ingPrice[q]*amountIng;
						foodCal[n] = foodCal[n] + ingCal[q]*amountIng;
						
						if(ingVeg[q].equals("true")&&foodVeg[n]) {
							foodVeg[n] = true;
						}else {
							foodVeg[n] = false;
						}
					}
				}
				
			}
			
			foodPrice1[n] = String.format("%.2f", foodPrice[n]);
			foodCal1[n] = ((int) (foodCal[n] + 0.5));
		}
		
		for(int s = 0; s < numberOfFood; s++) {
			System.out.println(foodName[s]+":");
			System.out.println("  " + foodCal1[s] + " calories");
			System.out.println("  " + "$" + foodPrice1[s]);
			
			if(foodVeg[s]) {
				System.out.println("  " + "Vegetarian");
			}else {
				System.out.println("  " + "Non-Vegetarian");
			}
			
			
		}
	}
	
	// You can define helper methods here if needed.
	
}
