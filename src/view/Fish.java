import java.util.ArrayList;
import java.util.ListIterator;

/**
 * This class store the model of a Fish for the view
 */
public class Fish {

    private String name;
    private int[] dimensions = new int[2];
    private int[] coordinates = new int[2];
    private ArrayList<int[]> coordinatesAndTimes = new ArrayList<int[]>(); // 0 : position x, 1 : position y, 2 : time
    private String mobility;
    private State state = State.STOPPED;

    public Fish(String name, int[] coordinates, int[] dimensions, String mobility) throws FishException {
        this.name = name;
  
        for (int i = 0; i < 2; ++i)  {
            if (dimensions[i] < 0) {
                throw new FishException(String.format("The %d dimension is negative", i));
            } else {
                this.dimensions[i] = dimensions[i];
            } 
        }
        for (int i = 0; i < 2; ++i)  {
            if (coordinates[i] < 0) {
                throw new FishException(String.format("The %d coordinate is negative", i));
            } else if (coordinates[i] > 100) {
                throw new FishException(String.format("The %d coordinate is over 100", i));
            } else {
                this.coordinates[i] = coordinates[i];
            } 
        }

        this.mobility = mobility;
    }

    /**
     * Return the position of the fish and the time
     * 
     * @return coordinates and time int[2]
     */
    public ArrayList<int[]> getPositionsAndTimes() {
        return coordinatesAndTimes;
    }

    /**
     * Return the current position of the fish 
     * 
     * @return int[2]
     */
    public int[] getPosition() {
        return coordinates;
    }

    /**
     * Return the size of the fish
     * 
     * @return dimension int[2]
     */
    public int[] getSize() {
        return dimensions;
    }

    /**
     * Return the string with the name of the mobility
     * 
     * @return dimension int[2]
     */
    public String getMobility() {
        return mobility;
    }

    /**
     * Return the state of a fish, either STARTED or STOPPED (enum)
     * 
     * @return state State
     */
    public State getState() {
        return state;
    }

    /*
     * Return the name of the fish
     * 
     * @return @name String
     */
    public String getName() {
        return name;
    }

    /**
     * Change the state of the fish
     * Current value : STARTED -> New value : STOPPED
     * Current value : STOPPED -> New value : STARTED
     * 
     * @return state State
     */
    public void setState(State newState) {
        state = newState;
    }

    /**
     * Change the ArrayList<int[]> with position and their time with a new ArrayList
     * Useful for ls command
     * 
     * @param newPositionAndTime
     */
    public void setPositionsAndTimes(ArrayList<int[]> newPositionsAndTimes) {
        coordinatesAndTimes = newPositionsAndTimes;
    }

     /**
     * Change the current position with the new current position
     * 
     * @param newPosition
     */
    public void setPosition(int[] newPosition) throws FishException {
        for (int i = 0; i < 2; ++i) {
            if (newPosition[i] < 0) {
                throw new FishException(String.format("The %d coordinate is negative", i));
            } else if (newPosition[i] > 100) {
                throw new FishException(String.format("The %d coordinate is over 100", i));
            } else {
                coordinates[i] = newPosition[i];
            }
        }
    }

    /**
     * Add the new Position and the time in coordinatesAndTimes ArrayList<int[]>
     * If there is already a position at this time, replace the position 
     * else the posiition is added in order to keep a list sorted by time ascending
     * @param newPositionAndTime int[] x, y, time
     * @throws FishException
     */
    public void addPositionAndTime(int[] newPositionAndTime) throws FishException {
        if (newPositionAndTime[2] == 0) {
            setPosition(newPositionAndTime);
            return;
        }
        int count = 0;
        boolean added = false;
        ListIterator<int[]> iterator = coordinatesAndTimes.listIterator();
        while (iterator.hasNext()) {
            int[] element = iterator.next();
            int elementTime = element[2];
            if (elementTime == newPositionAndTime[2]) {
                setPosition(element, newPositionAndTime);
                added = true;
            }
            if (elementTime <= newPositionAndTime[2]) {
                ++count;
            }
        }
        if (added == false) {
            coordinatesAndTimes.add(count, newPositionAndTime);
            added = true;
        } 
        if (added == false) {
            throw new FishException("The newPositionAndTime isnt added");
        }
    }

    /**
     * This method decrements the time of each position, if position time is negative, this position
     * is removed from coordinatesAndTimes, the last position position negative or equal to 0 replace
     * the current position
     * @throws FishException
     */
    public void decrementTime() throws FishException{
        System.out.print("Decrement time\n");

        ListIterator<int[]> iterator = coordinatesAndTimes.listIterator();
        int[] new_current_element = null;
        while (iterator.hasNext()) {
            int[] element = iterator.next();
            --element[2];
            if (element[2] <= 0){
                new_current_element = element;
                iterator.remove();
            } 
        }
        if (new_current_element != null) {
            setPosition(new_current_element);
            System.out.print("Changement de la position\n");
        }
    }

    /*
     * Change the attribute coordinates with the @newPosition int[2]
     */
    private void setPosition(int[] position, int[] newPosition) throws FishException {
        for (int i = 0; i < 2; ++i) {
            if (newPosition[i] < 0) {
                throw new FishException(String.format("The %d coordinate is negative", i));
            } else if (newPosition[i] > 100) {
                throw new FishException(String.format("The %d coordinate is over 100", i));
            } else {
                position[i] = newPosition[i];
            }
        }
    }

}
