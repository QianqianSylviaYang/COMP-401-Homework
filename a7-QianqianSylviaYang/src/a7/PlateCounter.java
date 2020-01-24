package a7;


public class PlateCounter implements BeltObserver{
	private int countRed;
	private int countGreen;
	private int countBlue;
	private int countGold;
	/*
	 * Accept Belt as parameter
	 * Initialize countRed, countGreen, countBlue, countGreen, countGold
	 */
	public PlateCounter(Belt b) {
		/*
		 *  if it is null
		 * throw IllegalArgumentException
		 */
		if(b==null) {
			throw new IllegalArgumentException("The belt cannot be null.");
		}
		//add this PlateCounter to the belt's Beltobserver list
		b.addBeltObserver(this);
		/*
		 * loop through each position on the belt, i start from 0 to its size-1
		 * as long as the position is not empty
		 * check its color and add 1 to the corresponding color count
		 * The final counts will be the number of plate in each color
		 */
		for(int i=0; i<b.getSize(); i++) {
			if(b.getPlateAtPosition(i)==null) {
				continue;
			}
			switch(b.getPlateAtPosition(i).getColor()) {
			case RED:
				countRed++;
				break;
			case GREEN:
				countGreen++;
				break;
			case BLUE:
				countBlue++;
				break;
			case GOLD:
				countGold++;
				break;
			}
		}
	}
	@Override
	/*
	 * Accept PlateEvent as parameter
	 * if the type of e is Plate Placed
	 * add one to the corresponding color count for the plate
	 * if the type e is Plate_Removed
	 * minus one to the corresponding color count for the plate
	 */
	public void handlePlateEvent(PlateEvent e) {
		switch(e.getType()) {
		case PLATE_PLACED:
			switch(e.getPlate().getColor()) {
			case RED:
				countRed++;
				break;
			case GREEN:
				countGreen++;
				break;
			case BLUE:
				countBlue++;
				break;
			case GOLD:
				countGold++;
				break;
			}
			break;
		case PLATE_REMOVED:
			switch(e.getPlate().getColor()) {
			case RED:
				countRed--;
				break;
			case GREEN:
				countGreen--;
				break;
			case BLUE:
				countBlue--;
				break;
			case GOLD:
				countGold--;
				break;
			}
			break;
		}
		
	}
	//return instance field countRed
	public int getRedPlateCount() {
		return countRed;
	}
	//return instance field countGreen
	public int getGreenPlateCount() {
		return countGreen;
	}
	//return instance field countBlue
	public int getBluePlateCount() {
		return countBlue;
	}
	//return instance field countGold
	public int getGoldPlateCount() {
		return countGold;
	}
	
	
}
