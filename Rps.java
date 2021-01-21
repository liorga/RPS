package ex4_RPS;

import com.sun.org.apache.xml.internal.dtm.Axis;
import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Rps implements ActionListener {
    private static final int ZERO = 0;


    private JFrame game_frame ;

    private JPanel game_panel;
    private JPanel button_panel;
    private JPanel result_panel;
    private JPanel display_panel;

    private JButton rock_button;
    private JButton paper_button;
    private JButton scissors_button;

    private JLabel computer_choice;
    private JLabel computer_logo;
    private JLabel human_choice;
    private JLabel human_logo;
    private JLabel human_wins;
    private JLabel human_wins_label;
    private JLabel computer_wins;
    private JLabel computer_wins_label;
    private JLabel draws;
    private JLabel draws_label;
    private JLabel result;
    private JLabel games_num;
    private JLabel games_num_label;

    // numbers to be displayed...changing while game is running
    private Integer human_wins_number;
    private Integer computer_wins_number  ;
    private Integer draw_number;
    private Integer games_played;

    //switch case options
    private final int ROCK = 1;
    private final int PAPER = 2;
    private final int SCISSORS = 3;
    private final int ONE = 1;
    private final int THREE = 3;




    public Rps(){


        human_wins_number = ZERO;
        computer_wins_number = ZERO;
        draw_number = ZERO;
        games_played = ZERO;


        /**
         * building the jframe of the game
         */
        game_frame = new JFrame("Rock Paper Scissors");
        game_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container gf = game_frame.getContentPane();
        gf.setLayout(new BoxLayout(gf,BoxLayout.Y_AXIS));
        gf.setPreferredSize(new Dimension(1200,800));

        /**
         * creating and adding the game pannel to the frame
         */
        game_panel = new JPanel();
        game_panel.setLayout(new GridLayout());


        /**
         * creating butttons for the buttons panel
         */
        rock_button = new JButton(new ImageIcon("Rock.jpg"));
        rock_button.setBorder(BorderFactory.createLineBorder(Color.YELLOW,2));
        rock_button.addActionListener(this);

        paper_button = new JButton(new ImageIcon("Paper.jpg"));
        paper_button.setBorder(BorderFactory.createLineBorder(Color.YELLOW,2));
        paper_button.addActionListener(this);

        scissors_button = new JButton(new ImageIcon("Scissors.jpg"));
        scissors_button.setBorder(BorderFactory.createLineBorder(Color.YELLOW,2));
        scissors_button.addActionListener(this);

        /**
         * creating button panel and add it to the game panel
         */
        JLabel button_option = new JLabel("choose your move: ");
        button_panel = new JPanel();
        button_panel.setLayout(new GridLayout());
        button_panel.setBorder(BorderFactory.createLineBorder(Color.BLUE,5));
        button_panel.add(button_option);
        button_panel.add(rock_button);
        button_panel.add(paper_button);
        button_panel.add(scissors_button);


        /**
         * creating and adding display panel to game panel
         */
        display_panel = new JPanel();
        display_panel.setLayout(new GridLayout());
        display_panel.setBorder(BorderFactory.createLineBorder(Color.BLUE,5));


        //creating labels for the display panel
        computer_choice = new JLabel("computer choice");
        computer_logo = new JLabel(new ImageIcon("computer.jpeg"));
        human_choice = new JLabel("human choice");
        human_logo = new JLabel(new ImageIcon("human.jpeg"));

        //adding labels for display panel
        display_panel.add(computer_logo);
        display_panel.add(computer_choice);

        display_panel.add(human_logo);
        display_panel.add(human_choice);

        // adding button panel and display panel to game panel
        game_panel.add(button_panel);
        game_panel.add(display_panel);


        //creating and adding the result pannel to the frame
        result_panel = new JPanel();
        result_panel.setLayout(new BoxLayout(result_panel,BoxLayout.X_AXIS));
        result_panel.setBorder(BorderFactory.createLineBorder(Color.BLUE,5));
        result_panel.setPreferredSize(new Dimension(100,100));

        /**
         * creating labels to the result panel for the updating of the game data and numbers
         */
        human_wins_label = new JLabel("Human:");
        computer_wins_label = new JLabel("Computer:");
        draws_label = new JLabel("Draws:");
        games_num_label = new JLabel("games played");
        human_wins = new JLabel(human_wins_number.toString());
        computer_wins = new JLabel(computer_wins_number.toString());
        draws = new JLabel(draw_number.toString());
        games_num = new JLabel(games_played.toString());
        result = new JLabel();

        // sets the labels text font to ariel and bold the text to 30
        Font ariel = new Font("Ariel", Font.PLAIN,30);
        result.setFont(ariel);
        draws.setFont(ariel);
        human_wins.setFont(ariel);
        computer_wins.setFont(ariel);
        human_wins_label.setFont(ariel);
        computer_wins_label.setFont(ariel);
        draws_label.setFont(ariel);
        games_num_label.setFont(ariel);
        games_num.setFont(ariel);


        result_panel.add(human_wins_label);
        result_panel.add(Box.createHorizontalStrut(10));
        result_panel.add(human_wins);
        result_panel.add(Box.createGlue());
        result_panel.add(draws_label);
        result_panel.add(Box.createHorizontalStrut(10));
        result_panel.add(draws);
        result_panel.add(Box.createGlue());
        result_panel.add(computer_wins_label);
        result_panel.add(Box.createHorizontalStrut(10));
        result_panel.add(computer_wins);
        result_panel.add(Box.createGlue());
        result_panel.add(games_num_label);
        result_panel.add(Box.createHorizontalStrut(10));
        result_panel.add(games_num);
        result_panel.add(Box.createGlue());
        result_panel.add(result);
        result_panel.add(Box.createGlue());


        /**
         * adding the game panel and result panel to the main game frame
         */
        game_frame.add(game_panel);
        game_frame.add(result_panel);


    }

    /**
     * method to start the game will be called from the Main class
     */
    public void play(){
        game_frame.setVisible(true);
        game_frame.pack();
    }


    /**
     * 3 options which effected by the button that the user has clicked
     *  then for each evant from the user computer random select his choice
     *  then entered to a switch case win or drew or lose.
     *  for each case making the needed updates for the game.
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {


        if(actionEvent.getSource() == this.rock_button){
            Random random = new Random();
            int choice = (random.nextInt(THREE)) + ONE;
            human_choice.setIcon(new ImageIcon("Rock.jpg"));
            switch (choice){
                case ROCK:
                    computer_choice.setIcon(new ImageIcon("Rock.jpg"));
                    result.setText("Draw");
                    result.setForeground(Color.GREEN);
                    draw_number++;
                    draws.setText(draw_number.toString());
                    result.setVisible(true);
                    break;
                case PAPER:
                    computer_choice.setIcon(new ImageIcon("Paper.jpg"));
                    result.setText("You Lose");
                    result.setForeground(Color.RED);
                    computer_wins_number++;
                    computer_wins.setText(computer_wins_number.toString());
                    result.setVisible(true);
                    break;
                case SCISSORS:
                    computer_choice.setIcon(new ImageIcon("Scissors.jpg"));
                    result.setText("You Win");
                    result.setForeground(Color.BLUE);
                    human_wins_number++;
                    human_wins.setText(human_wins_number.toString());
                    result.setVisible(true);
                break;

                default:
                    break;

            }
            games_played++;
            games_num.setText(games_played.toString());
        }


        if(actionEvent.getSource() == this.paper_button){
            Random random = new Random();
            int choice = (random.nextInt(THREE)) + ONE;
            human_choice.setIcon(new ImageIcon("Paper.jpg"));
            switch (choice){
                case ROCK:
                    computer_choice.setIcon(new ImageIcon("Rock.jpg"));
                    result.setText("You Win");
                    result.setForeground(Color.BLUE);
                    human_wins_number++;
                    human_wins.setText(human_wins_number.toString());
                    result.setVisible(true);
                    break;
                case PAPER:
                    computer_choice.setIcon(new ImageIcon("Paper.jpg"));
                    result.setText("Draw");
                    result.setForeground(Color.GREEN);
                    draw_number++;
                    draws.setText(draw_number.toString());
                    result.setVisible(true);
                    break;
                case SCISSORS:
                    computer_choice.setIcon(new ImageIcon("Scissors.jpg"));
                    result.setText("You Lose");
                    result.setForeground(Color.RED);
                    computer_wins_number++;
                    computer_wins.setText(computer_wins_number.toString());
                    result.setVisible(true);
                    break;
                default:
                    break;

            }
            games_played++;
            games_num.setText(games_played.toString());
        }

        if(actionEvent.getSource() == this.scissors_button){
            Random random = new Random();
            int choice = (random.nextInt(THREE)) + ONE;
            human_choice.setIcon(new ImageIcon("Scissors.jpg"));
            switch (choice){
                case ROCK:
                    computer_choice.setIcon(new ImageIcon("Rock.jpg"));
                    result.setText("You Lose");
                    result.setForeground(Color.RED);
                    computer_wins_number++;
                    computer_wins.setText(computer_wins_number.toString());
                    result.setVisible(true);
                    break;
                case PAPER:
                    computer_choice.setIcon(new ImageIcon("Paper.jpg"));
                    result.setText("You Win");
                    result.setForeground(Color.BLUE);
                    human_wins_number++;
                    human_wins.setText(human_wins_number.toString());
                    result.setVisible(true);
                    break;

                case SCISSORS:
                    computer_choice.setIcon(new ImageIcon("Scissors.jpg"));
                    result.setText("Draw");
                    result.setForeground(Color.GREEN);
                    draw_number++;
                    draws.setText(draw_number.toString());
                    result.setVisible(true);
                    break;
                default:
                    break;

            }
            games_played++;
            games_num.setText(games_played.toString());
        }


    }

}
