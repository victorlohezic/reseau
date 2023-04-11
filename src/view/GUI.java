import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GUI extends JFrame {//implements ActionListener{

    private int nbFishes=0;
    private ImageIcon[] icons;
    private JLabel[] labels;
    private JLabel[] myLabel;
    private int dx = 0;
    //private Timer timer;

    public GUI(View newView) {
        super("View");
        setLayout(null); // creates the window


        //Create the timer to update the button position
        //timer = new Timer(1000, this);
        //timer.start();


        setLocation(newView.getPosition()[0], newView.getPosition()[1]); //sets the location of the window on the screen at
        setSize(newView.getDimensions()[0], newView.getDimensions()[1]); // sets the size of the window at 500x537 (500+height of stripe)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the window when exit
        setResizable(false); // prevents from resizing the window
        setVisible(true); // displays the window
        getContentPane().setBackground(Color.BLUE); // set the bg to blue

    }




    public void updateGUI(View newView) {

        System.out.print("update GUIIIIIIIII");

        
        for (int i=0; i<this.nbFishes; i++) {
            this.remove(labels[i]);
        }

        dx+=10;
        this.nbFishes = newView.getFishes().size();
        this.icons = new ImageIcon[nbFishes];
        this.labels = new JLabel[nbFishes];

        // Iterate over the keys using a for-each loop
        int count = 0;
        for (String name : newView.getFishes().keySet()) {
            System.out.print("affiche un poisson");
            Fish fish = newView.getFishes().get(name);
            icons[count] = new ImageIcon("clown.jpeg");
            labels[count] = new JLabel(icons[count]);
            labels[count].setBounds(0, 0, fish.getSize()[0], fish.getSize()[1]);
            this.add(labels[count]);
            labels[count].setLocation(fish.getPosition()[0], fish.getPosition()[1]);
            System.out.print("Positions: "+  fish.getPosition()[0] +" "+fish.getPosition()[1]);
            count++;
        }
    }

    
/*
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == timer) {
            
            System.out.print("time a 0");
        }
    
    }

*/

    public static void main(String[] args) {
        //GUI myGui = new GUI();
    }
}
