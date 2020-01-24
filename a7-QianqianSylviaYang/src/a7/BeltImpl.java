package a7;

import java.util.ArrayList;
import java.util.List;

import a7.PlateEvent.EventType;
import comp401sushi.Plate;


public class BeltImpl implements Belt {

	private Plate[] _belt;
	
	private List<BeltObserver> observers;
	private Customer[] seats;
	
	public BeltImpl(int belt_size) {
		if (belt_size < 1) {
			throw new IllegalArgumentException("Illegal belt size");
		}
		
		_belt = new Plate[belt_size];
		//initialize observer list
		observers = new ArrayList<BeltObserver>();
		//initialize seat array
		seats = new Customer[belt_size];
	}

	@Override
	public int getSize() {
		return _belt.length;
	}

	@Override
	public Plate getPlateAtPosition(int position) {
		position = normalize_position(position);

		return _belt[normalize_position(position)];
	}

	@Override
	public void setPlateAtPosition(Plate plate, int position) throws BeltPlateException {
		if (plate == null) {
			throw new IllegalArgumentException();
		}

		position = normalize_position(position);

		if (getPlateAtPosition(position) != null) {
			throw new BeltPlateException(position, plate, this);
		}
		
		_belt[position] = plate;
		/*
		 * create a new PlateEvent with PLATE_PLACED, plate and position
		 * notify the observers with this PlateEvent
		 */
		PlateEvent e = new PlateEvent(EventType.PLATE_PLACED, plate, position);
		notifyObservers(e);
		
	}


	@Override
	public void clearPlateAtPosition(int position) {
		position = normalize_position(position);
		
		Plate plateTemp = _belt[position];
		
		_belt[position] = null;	
		/*
		 * if the plate is not null
		 * create a new PlateEvent with PLATE_REMOVED, plate and position
		 * notify the observers with this PlateEvent
		 */
		if(plateTemp!=null) {
			PlateEvent e = new PlateEvent(EventType.PLATE_REMOVED, plateTemp, position);
			notifyObservers(e);
		}
	}

	private int normalize_position(int position) {
		int size = getSize();
		return (((position % size) + size) % size);
	}
	
	
	@Override
	public int setPlateNearestToPosition(Plate plate, int position) throws BeltFullException {
		int offset = 0;
		position = normalize_position(position);

		while (offset < getSize()) {
			try {
				setPlateAtPosition(plate, position+offset);
				/*
				 * if the plate is not null
				 * create a new PlateEvent with PLATE_PLACED, plate and position+offset
				 * notify the observers with this PlateEvent
				 */
				if(plate!=null) { 
					PlateEvent e = new PlateEvent(EventType.PLATE_PLACED, plate, position+offset);
					notifyObservers(e);
				}

				return normalize_position(position+offset);
			}
			catch (BeltPlateException e) {
				offset += 1;
			}
		}
		throw new BeltFullException(this);
	}

	@Override
	public void rotate() {
		Plate last_plate = _belt[getSize()-1];
		
		for (int i=getSize()-1; i>0; i--) {
			_belt[i] = _belt[i-1];
		}
		_belt[0] = last_plate;
		/*
		 * loop through all the elements in seats
		 * from position 0 to length-1
		 * if the element at the current position is null on the belt or the seats
		 * continue to the next loop
		 * else notify the customer at this position with observerPlateOnBelt
		 */
		for(int i=0; i<seats.length; i++) {
			if(_belt[i]==null||seats[i]==null) {
				continue;
			}
			seats[i].observePlateOnBelt(this, _belt[i], i);
		}
	}

	@Override
	//Input BeltObserver o, put it into the observer list
	public void addBeltObserver(BeltObserver o) {
		observers.add(o);
	}

	@Override
	//Input BeltObserver o, remove it from the observer list
	public void removeBeltObserver(BeltObserver o) {
		observers.remove(o);
	}
	
	/*
	 * Input PlateEvent e
	 * Notify all the observers in the list observer with handlePlateEvent
	 */
	public void notifyObservers(PlateEvent e) {
		/*
		 * loop for each Beltobserver in observers
		 * if the Beltobserver at this position is null
		 * continue to the next loop
		 * else
		 * notify this observer by using handlePlateEvent to pass on the event from the parameter
		 */
		for(BeltObserver o: observers) {
			if(o==null) {
				continue;
			}
			o.handlePlateEvent(e);
		}
	}
	
	/*
	 * Accept Customer and int as parameter  
	 * let the seat at position passed in be the customer passed in
	 */
	public void registerCustomerAtPosition(Customer c, int position) {
		//normalized the int position passed in
		position = normalize_position(position);
		/*
		 *  if Customer passed in is null
		 * throw new IllegalArgumentException
		 */
		if(c==null) {
			throw new IllegalArgumentException("The customer cannot be null");
		}
		/*
		 * if the seat at the position is not empty/null
		 * throw RuntimeException
		 */
		if(seats[position]!=null) {
			throw new RuntimeException("The seat is taken.");
		}
		/*
		 * loop through the seat array from position 0 to its length-1
		 * if the seat at any position has a same customer as the customer passed in
		 * throw RuntimeException
		 */
		for(int i=0; i<seats.length; i++) {
			if(c.equals(seats[i])) {
				throw new RuntimeException("The customer is already seated");
			}
		}
		
		seats[position] = c;
	}
	
	/*
	 * Accept int as parameter
	 * set the seats at the position passed in to null (remove the customer at this position)
	 * return the customer originally at this position
	 */
	public Customer unregisterCustomerAtPosition(int position) {
		//normalized the position passed in
		position = normalize_position(position);
		//if it is null at the position provided, return null
		if(seats[position]==null) {
			return null;
		}else {
			Customer temp = seats[position];
			seats[position] = null;
			return temp;
		}
		
		
	}
}
