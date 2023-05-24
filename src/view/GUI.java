import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit;
import java.time.LocalTime;
import java.util.ArrayList;


public class GUI extends JFrame implements ActionListener{

    private int nbFishes=0;
    private ImageIcon[] icons;
    private JLabel[] labels;
    private int[] screenSize;
    private ImageIcon bgIcon;
    private JLabel bgLabel; 
    private Timer timer;
    private ArrayList<float[]> futurePos = new ArrayList<float[]>(); // 0 : position x, 1 : position y, 2 : time
    private ArrayList<float[]> lastPos = new ArrayList<float[]>(); // 0 : position x, 1 : position y, 2 : time
    private String[] fishNames;
    private View myView;
    private String resources;
    private boolean isUpdatingFishes = false;



    public GUI(View newView, String resources) {
        super("View");
        setLayout(null); // creates the window
        this.resources = resources;
        
        //Create the timer to update the button position
        timer = new Timer(150, this);
        timer.start();
        Dimension getScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize = new int[2];
        screenSize[0] = (int) getScreenSize.getWidth();
        screenSize[1] = (int) getScreenSize.getHeight();


        //screenSize[0] = (int) screenSize.getWidth();
        //screenSize[1] = (int) screenSize.getHeight();
        //System.out.print("dimensions ecran : "+ screenSize[0] + " "+ screenSize[1]);

        setLocation(0,0); //sets the location of the window on the screen at
        setSize(screenSize[0], screenSize[1]); // sets the size of the window at 500x537 (500+height of stripe)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the window when exit
        //setResizable(false); // prevents from resizing the window
        setVisible(true); // displays the window

   

        bgIcon = new ImageIcon(new ImageIcon("./fishes/background.jpg").getImage().getScaledInstance(screenSize[0], screenSize[1], Image.SCALE_DEFAULT));

        bgLabel = new JLabel(bgIcon,SwingConstants.LEFT);
        bgLabel.setVerticalAlignment(SwingConstants.TOP);
        bgLabel.setBounds(-1, -1, screenSize[0], screenSize[1]);
        add(bgLabel);
        bgLabel.setLocation(0, 0);
        myView = newView;
        updateGUI(newView);
    }




    public void updateGUI(View newView) {

        this.isUpdatingFishes = true;
        for (int i=0; i<this.nbFishes; i++) {
            this.remove(labels[i]);
        }

        this.nbFishes = newView.getFishes().size();
        this.icons = new ImageIcon[nbFishes];
        this.labels = new JLabel[nbFishes];
        this.fishNames = new String[nbFishes];


        int count = 0;
        int direction;
        
        Dimension getScreenSize = getSize();
        screenSize[0] = (int) getScreenSize.width;
        screenSize[1] = (int) getScreenSize.height;

        for (String name : newView.getFishes().keySet()) {
            Fish fish = newView.getFishes().get(name);
            float[] currentPos = {(float) fish.getPosition()[0]*screenSize[0]/100, (float) fish.getPosition()[1]*screenSize[1]/100, getCurrentTime()};
            lastPos.add(count, currentPos);
            
            float[] nextPos = new float[3];
            if (fish.getPositionsAndTimes().size() == 0) {
                nextPos[0] = (float) fish.getPosition()[0]*screenSize[0]/100; 
                nextPos[1] = (float) fish.getPosition()[1]*screenSize[1]/100;
              
                nextPos[2] = getCurrentTime()+(float) 0.1;
            } else {
                nextPos[0] = (float) fish.getPositionsAndTimes().get(0)[0]*screenSize[0]/100; ;
                nextPos[1] = (float) fish.getPositionsAndTimes().get(0)[1]*screenSize[1]/100; ;
                nextPos[2] = (float) fish.getPositionsAndTimes().get(0)[2]+getCurrentTime();
                //System.out.print("\nfuture position : "+ nextPos[0]+ " " + nextPos[1]);

            }

            futurePos.add(count, nextPos);
            if (futurePos.get(count)[0] > lastPos.get(count)[0]) {
                direction = 1;
            } else {
                direction = -1;
            }
            icons[count] = new ImageIcon(new ImageIcon(getPathFish(name, direction)).getImage().getScaledInstance(fish.getSize()[0]*screenSize[0]/100, fish.getSize()[1]*screenSize[1]/100, Image.SCALE_DEFAULT));
            
            labels[count] = new JLabel(icons[count],SwingConstants.LEFT);
            labels[count].setVerticalAlignment(SwingConstants.TOP);
            labels[count].setBounds(-screenSize[0], -screenSize[1], screenSize[0], screenSize[1]);

            add(labels[count]);
            labels[count].setLocation((int) currentPos[0], (int) currentPos[1]);
            fishNames[count] = fish.getName();
            count++;
        }
        remove(bgLabel);
        add(bgLabel);
        myView = newView;
        this.isUpdatingFishes = false;

    }

    private float getCurrentTime() {
        LocalTime time = LocalTime.now();
        float hours = time.getHour();
        float minutes = time.getMinute(); 
        float seconds = time.getSecond();
        float nanoseconds = time.getNano();
        float ftime =  3600*hours +  60*minutes +  seconds +  nanoseconds/1000000000;
        return ftime;
    }


    private int interpolation(int i, int coord) { //coord = 0 pour x, 1 pour y
        float currentPos = (getCurrentTime() - lastPos.get(i)[2])*(futurePos.get(i)[coord] - lastPos.get(i)[coord])/(futurePos.get(i)[2]-lastPos.get(i)[2]) + lastPos.get(i)[coord];
        int intPos = (int) currentPos;

        return intPos;
    }
    

    private void changeFuturePos(int i) {
        int dt = (int) (futurePos.get(i)[2] - lastPos.get(i)[2]);
        Fish fish = this.myView.getFishes().get(fishNames[i]);

        int count = 0;
        while (count < fish.getPositionsAndTimes().size() && fish.getPositionsAndTimes().get(count)[2] < dt) {
            count++;
        }
        if (count < fish.getPositionsAndTimes().size()) {
            futurePos.get(i)[0] = (float) fish.getPositionsAndTimes().get(count)[0]*screenSize[0]/100; ;
            futurePos.get(i)[1] = (float) fish.getPositionsAndTimes().get(count)[1]*screenSize[1]/100; ;
            futurePos.get(i)[2] = (float) fish.getPositionsAndTimes().get(count)[2] + lastPos.get(i)[2];
        //} else {
        //    System.out.print("Pas de futures positions" + fish.getPositionsAndTimes().size());
        }
        

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == timer && !this.isUpdatingFishes) {
            
            Dimension getScreenSize = getSize();
            screenSize[0] = (int) getScreenSize.width;
            screenSize[1] = (int) getScreenSize.height;
            
            for (int i=0; i<this.nbFishes; i++) {

                if (futurePos.get(i)[2] < getCurrentTime()) {
                    changeFuturePos(i);
                }

                this.remove(labels[i]);

                add(labels[i]);
                int currentx = interpolation(i, 0);
                int currenty = interpolation(i, 1);
                labels[i].setLocation(currentx, currenty);

                //System.out.print(fishNames[0] + " x = " + currentx + " y = " + currenty +"\n");
            }
            remove(bgLabel);
            add(bgLabel);


        }

    }

    private String getPathFish(String nameFish, int direction) {

        String extension;
        if (direction == 1) {
            extension = ".png";
        } else {
            extension = "_r.png";
        }
        int i = nameFish.length() - 1;
        while (i >= 0 && Character.isDigit(nameFish.charAt(i))) {
            i--;
        }
        String fishName  = nameFish.substring(0, i + 1);

        if (!fishName.equals("chacha") && !fishName.equals("chouchou") && !fishName.equals("sasa") && !fishName.equals("toutou")){
            System.out.print("fishname = "+fishName+"\n");
            fishName = "defaut";
        }
        return resources + "/" +  fishName + extension;
    }


}
