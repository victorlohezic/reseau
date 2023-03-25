/**
 * This class store the model of a Fish for the view
 */
public class Fish {
    
    private String name;
    private int[] dimensions = new int[2]; 
    private int[] coordinates = new int[2]; 

    public Fish(String name, int[] dimensions, int[] coordinates) {
        this.name = name;
        this.dimensions[0] = dimensions[0];
        this.dimensions[1] = dimensions[1]; 
        this.coordinates[0] = coordinates[0];
        this.coordinates[1] = coordinates[1]; 
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

    /*
     * Change the attribute coordinates with the @newPosition int[2]
     */
    public void setPosition(int[] newPosition) {
        coordinates[0] = newPosition[0];
        coordinates[1] = newPosition[1];
    }

    /*
     * Return the name of the fish
     * @return @name String
     */
    public String getName() {
        return name;
    }
} 
