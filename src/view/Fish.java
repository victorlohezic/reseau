/**
 * This class store the model of a Fish for the view
 */
public class Fish {
    
    private String name;
    private int[] dimensions = new int[2]; 
    private int[] coordinates = new int[2]; 
    private int[] newCoordinates = new int[]{0, 0}; 
    private int time = 0; 
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
     * Return the position of the fish to be reached
     * @return coordinates int[2]
     */
    public int[] getNewPosition() {
        return newCoordinates;
    } 

    /**
     * Return the time to reach the new_position
     * @return time int
     */
    public int getTime() {
        return time;
    } 

    /**
     * Return the position of the fish
     * @return coordinates int[2]
     */
    public int[] getPosition() {
        return coordinates;
    } 

    /**
     * Return the size of the fish
     * @return dimension int[2]
     */
    public int[] getSize() {
        return dimensions;
    }

    /**
     * Return the string with the name of the mobility
     * @return dimension int[2]
     */
    public String getMobility() {
        return mobility;
    }

    /**
     * Return the state of a fish, either STARTED or STOPPED (enum)
     * @return state State
     */
    public State getState() {
        return state;
    }

    /*
     * Return the name of the fish
     * @return @name String
     */
    public String getName() {
        return name;
    }

    /**
     * Change the time to reach the new_position
     * @param newTime int
     * @return time int
     */
    public void setTime(int newTime) {
        time = newTime;
    } 

    /**
     * Change the state of the fish
     * Current value : STARTED -> New value : STOPPED
     * Current value : STOPPED -> New value : STARTED
     * @return state State
     */
    public void setState(State newState) {
        state = newState;
    }

    /*
     * Change the attribute newCoordinates with the @newPosition int[2]
     */
    public void setNewPosition(int[] newPosition) throws FishException {
        for (int i = 0; i < 2; ++i)  {
            if (newPosition[i] < 0) {
                throw new FishException(String.format("The %d coordinate is negative", i));
            } else if (newPosition[i] > 100) {
                throw new FishException(String.format("The %d coordinate is over 100", i));
            } else {
                newCoordinates[i] = newPosition[i];
            } 
        }
    }

    /*
     * Change the attribute coordinates with the @newPosition int[2]
     */
    public void setPosition(int[] newPosition) throws FishException {
        for (int i = 0; i < 2; ++i)  {
            if (newPosition[i] < 0) {
                throw new FishException(String.format("The %d coordinate is negative", i));
            } else if (newPosition[i] > 100) {
                throw new FishException(String.format("The %d coordinate is over 100", i));
            } else {
                coordinates[i] = newPosition[i];
            } 
        }
    }

} 
