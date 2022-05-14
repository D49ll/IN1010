import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class SnakeGUI{
    public static void main(String[] arg){
        GUI gui = new GUI();
        
    }
}

class GUI{
    private SnakeControl control;
    private JFrame window;
    private JPanel panel, userInterface, snakeGrid, buttons;
    private JLabel snakeLength;
    private JButton up, down, left, right, end;

    GUI(){
        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }catch (Exception e) { System.exit(1); }
        
        window = new JFrame("Slangespillet");
        window.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));

    

        /*Main panel for all other components*/
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        window.add(panel);
        
        
        /* Upper panel for buttons and snake length.
         * Should be placed at top of window */
        userInterface = new JPanel();
        userInterface.setLayout(new BorderLayout());
        panel.add(userInterface, BorderLayout.NORTH);


        /* JLabel to keep track of snake length
         * Should be placed left in userInterface*/
        snakeLength = new JLabel("Lengde");
        userInterface.add(snakeLength, BorderLayout.WEST);

        /* Buttons for controlling snake
         * Should be placed in center of userInterface */
        buttons = new JPanel();
        buttons.setLayout(new BorderLayout());

        up = new JButton("Opp");
        down = new JButton("Ned");
        left = new JButton("Venstre");
        right = new JButton("Høyre");

        buttons.add(up, BorderLayout.NORTH);
        buttons.add(down, BorderLayout.SOUTH);
        buttons.add(left, BorderLayout.WEST);
        buttons.add(right, BorderLayout.EAST);

        userInterface.add(buttons, BorderLayout.CENTER);

        /* Exit button for ending program 
         * Should be placed to the right in userInterface*/
        end = new JButton("Slutt");
        userInterface.add(end, BorderLayout.EAST);


        /* Panel for snake and treasures
         * Should be placed at bottom of window */
        snakeGrid = new JPanel();
        snakeGrid.setLayout(new GridLayout(12,12));

        for(int row = 0; row < 12; row++){
            for(int col = 0; col < 12; col++){
                JButton w = new JButton();
                w.setBackground(Color.WHITE);
                snakeGrid.add(w);
            }
        }

        panel.add(snakeGrid, BorderLayout.SOUTH);
        

        window.pack();
        window.setVisible(true);
    }
}
