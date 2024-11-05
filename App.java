import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        int boardwidth = 600;
        int boardheight = 600;


         JFrame frame =  new JFrame("snake");
         frame.setVisible(true);
         frame.setSize(boardwidth, boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        snakegame Snakegame=new snakegame(boardwidth, boardheight);
        frame.add(Snakegame);
        frame.pack();
        Snakegame.requestFocus();
        }     
}
