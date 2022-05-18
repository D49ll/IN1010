import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI{
    private Controller controller;
    private JFrame window;
    private JPanel panel, userInterface, snakeGrid, buttons;
    private int colums, rows;
    private JLabel[][] blocks ;
    
    private JLabel length;
    private JButton up, down, left, right, end;

    public GUI(Controller controller, int rows, int colums){
        this.controller = controller;
        this.rows = rows;
        this.colums = colums;
        blocks = new JLabel[rows][colums];
        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }catch (Exception e) { System.exit(1); }
        
        window = new JFrame("Slangespillet");
        window.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
    
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        userInterface = userInterface();
        snakeGrid = createGrid();

        panel.add(userInterface, BorderLayout.NORTH);
        panel.add(snakeGrid, BorderLayout.SOUTH);
        
        window.addKeyListener(new arrowControl());
        window.setFocusable(true);
        window.add(panel);
        window.pack();
        window.setVisible(true);
    }

    public class arrowControl implements KeyListener{

        @Override
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT) {
                controller.setHeading(Heading.WEST);
            }
            if(key == KeyEvent.VK_UP) {
                controller.setHeading(Heading.NORTH);

            }
            if(key == KeyEvent.VK_DOWN) {
                controller.setHeading(Heading.SOUTH);

            }
            if(key == KeyEvent.VK_RIGHT) {
                controller.setHeading(Heading.EAST);
            
            }
        }    
            @Override
            public void keyReleased(KeyEvent e) {}
    
            @Override
            public void keyTyped(KeyEvent e) {}
    }

    private JPanel userInterface(){
        userInterface = new JPanel();
        userInterface.setLayout(new BorderLayout());
        
        userInterface.add(snakeLength(), BorderLayout.WEST);
        userInterface.add(controlButtons(), BorderLayout.CENTER);
        userInterface.add(endButton(), BorderLayout.EAST);

        return userInterface;
    }

    private void activateArrows(){
        window.setFocusable(true);
        window.add(panel);
        window.pack();
        window.setVisible(true);
    }

    private JButton endButton(){
        end = new JButton("Stopp");
        
        class EndAction implements ActionListener {
            @Override
            public void actionPerformed (ActionEvent e) {
                controller.exitGame();
            }
        }
        end.addActionListener(new EndAction());

        return end;
    }

    public void changeTextStopButton(){
        end.setText("Slutt");
    }

    private JLabel snakeLength(){
        length = new JLabel("Lengde: 1");
        return length;
    }

    public void currentLength(int len){
        length.setText("Lengde: "+len);
    }



    private JPanel controlButtons(){
        buttons = new JPanel();
        buttons.setLayout(new BorderLayout());

        up = new JButton("Opp");
        down = new JButton("Ned");
        left = new JButton("Venstre");
        right = new JButton("Høyre");

        class ChangeHeading implements ActionListener{
            @Override
            public void actionPerformed (ActionEvent e){
                if(e.getSource() == up){
                    controller.setHeading(Heading.NORTH);

                }else if(e.getSource() == down){
                    controller.setHeading(Heading.SOUTH);

                }else if(e.getSource() == left){
                    controller.setHeading(Heading.WEST);

                }else if(e.getSource() == right){
                    controller.setHeading(Heading.EAST);

                }
                activateArrows();
            }
        }

        up.addActionListener(new ChangeHeading());
        down.addActionListener(new ChangeHeading());
        left.addActionListener(new ChangeHeading());
        right.addActionListener(new ChangeHeading());

        buttons.add(up, BorderLayout.NORTH);
        buttons.add(down, BorderLayout.SOUTH);
        buttons.add(left, BorderLayout.WEST);
        buttons.add(right, BorderLayout.EAST);

        return buttons;
    }

    private JPanel createGrid(){
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(rows,colums));
        for(int x = 0; x < rows; x++){
            for(int y = 0; y < colums; y++){
                JLabel block = createBlock();
                grid.add(block);
                blocks[x][y] = block;
                grid.add(block);
            }
        }
        return grid;
    }

    private JLabel createBlock(){
        JLabel r = new JLabel(" ", SwingConstants.CENTER);
        r.setPreferredSize(new Dimension(30,30));
        r.setOpaque(true);
        r.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        r.setBackground(Color.WHITE);
        return r;
    }

    public void drawSnake(int x, int y, String c){
        JLabel block = blocks[x][y];
        block.setText(c);
        block.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        block.setBackground(Color.GREEN);
        block.setForeground(Color.BLACK);
    }

    public void drawTreasure(int x, int y){
        JLabel block = blocks[x][y];
        block.setText("$");
        block.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        block.setForeground(Color.RED);
    }    

    public void hasVisit(int x, int y){
        JLabel block = blocks[x][y];
        block.setText(" ");
        block.setBackground(Color.WHITE);
    } 

    
}
