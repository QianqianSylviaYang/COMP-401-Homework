package a7;


public class ProfitCounter implements BeltObserver{

	private double countProfit;
	private int count;
	/*
	 * Accept Belt as parameter
	 */
	public ProfitCounter(Belt b) {
		/*
		 * if the belt is null
		 * throw IllegalArgument
		 */
		if(b==null) {
			throw new IllegalArgumentException("The belt cannot be null");
		}
		//add this ProfitCounter to the belt's BeltObserver list
		b.addBeltObserver(this);
		/*
		 * loop through the belt, from 0 to size-1
		 * as long as the position is not null
		 * count add one
		 * countProfit add the profit of the plate at this position
		 * The final amount of count is the number of plate on the belt
		 * The final countProfit is the total profit of the plate on the belt
		 */
		for(int i=0; i<b.getSize(); i++) {
			if(b.getPlateAtPosition(i)==null) {
				continue;
			}
			count++;
			countProfit = countProfit+b.getPlateAtPosition(i).getProfit();
		}
		
	}
	@Override
	/*
	 * Accept PlateEvent as parameter
	 * check for the type of the event 
	 * if placed 
	 * add one to the count
	 * add the profit of this plate being placed to the total countProfit
	 * if removed
	 * minus one from the count
	 * minus the profit of this plate from the countProfit
	 */
	public void handlePlateEvent(PlateEvent e) {
		switch(e.getType()) {
		case PLATE_PLACED:
			count++;
			countProfit = countProfit + e.getPlate().getProfit();
			break;
		case PLATE_REMOVED:
			count--;
			countProfit = countProfit - e.getPlate().getProfit();
			break;
		}
	}
	// return the instance field countProfit
	public double getTotalBeltProfit() {
		return countProfit;
	}
	/*
	 * return 0.0 if the belt is empty
	 * return countProfit over count if else
	 */
	public double getAverageBeltProfit() {
		if(count==0) {
			return 0.0;
		}else {
			return countProfit/count;
		}
	}

}
