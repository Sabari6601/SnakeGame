import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class snakegame extends JPanel implements ActionListener,KeyListener
{
    private class tile
    {
        int x;
        int y;

        tile (int x,int y)
        {
            this.x=x;
            this.y=y;
        }
    }

    int boardwidth ;
    int boardheight ;
    int tilesize = 25;
    //snake
    tile snakehead;
    ArrayList<tile> snakeBody;
    //food
    tile food;
    Random random;
    //gamelogic
    Timer gameloop;
    int velocityx;
    int velocityy;
    boolean gameover=false;
    
    snakegame(int boardwidth,int boardheight)
    {
        this.boardwidth=boardwidth;
        this.boardheight=boardheight;
        setPreferredSize(new Dimension(this.boardwidth,this.boardheight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

          //head
        snakehead = new tile(5,5);
        snakeBody=new ArrayList<tile>();
          //food
        food=new tile(10,10);
        random=new Random();
        placefood(); 

        velocityx=0;
        velocityy=1;
        //gameloop
        gameloop = new Timer(100, this);
        gameloop.start();

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g)
    { 
        //grid
  //    for(int i=0;i<boardwidth/tilesize;i++)
  //     { 
     //x1,y1,x2,y2
  //     g.drawLine(i*tilesize,0, i*tilesize, boardwidth);
  //     g.drawLine(0,i*tilesize ,boardwidth ,i*tilesize );
  //     }
    //food
    g.setColor(Color.red);
  //  g.fillRect(food.x*tilesize, food.y*tilesize, tilesize, tilesize);
    g.fill3DRect(food.x*tilesize, food.y*tilesize, tilesize, tilesize,true);

     //snake head
     g.setColor(Color.GREEN);
  //   g.fillRect(snakehead.x*tilesize, snakehead.y*tilesize, tilesize, tilesize);
     g.fill3DRect(snakehead.x*tilesize, snakehead.y*tilesize, tilesize, tilesize,true);

    //snakebody
    for(int i=0;i<snakeBody.size();i++)
    {
        tile snakepart =snakeBody.get(i);
  //      g.fillRect(snakepart.x*tilesize, snakepart.y*tilesize, tilesize, tilesize);
  g.fill3DRect(snakepart.x*tilesize, snakepart.y*tilesize, tilesize, tilesize,true);
} 
    //score
    g.setFont(new Font("Arial",Font.PLAIN,16));
    if(gameover)
    {
        g.setColor(Color.WHITE);
        g.drawString("U Messed Up:"+String.valueOf(snakeBody.size()),tilesize -16 ,tilesize );
    }
    else{
        g.setColor(Color.WHITE);
        g.drawString("SCORE:"+String.valueOf(snakeBody.size()),tilesize -16,tilesize);
    }

    }
    public void placefood()
    {
      food.x=random.nextInt(boardwidth/tilesize);
      food.y=random.nextInt(boardwidth/tilesize);
    }

    public boolean collision(tile tile1,tile tile2)
    {
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    public void move()
    {
        //eatfood
        if(collision(snakehead, food))
        {
            snakeBody.add(new tile(food.x,food.y));
            placefood();
        }
      //snakebody
        for(int i=snakeBody.size()-1;i>=0;i--)
        {
            tile snakepart = snakeBody.get(i);
            if(i==0)
            {
                snakepart.x = snakehead.x;
                snakepart.y = snakehead.y;
            }
            else
            {
                tile prevsnakepart = snakeBody.get(i-1);
                snakepart.x = prevsnakepart.x;
                snakepart.y = prevsnakepart.y;
            }
        }
        //snakehead
        snakehead.x += velocityx;
        snakehead.y += velocityy;
        //gameoverconditions
        for(int i=0;i< snakeBody.size();i++)
        {
           tile snakepart =snakeBody.get(i);
           //collide with the snake head
            if(collision(snakehead,snakepart))
            {
                gameover=true;
            } 
        }
        // collaid wall
        if(snakehead.x*tilesize<0 || snakehead.x*tilesize>boardwidth || snakehead.y*tilesize<0 || snakehead.y*tilesize >boardheight)
        {
            gameover=true;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        move();
        if(gameover)
        {
          gameloop.stop();  
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP && velocityy != 1)
        {
           velocityx=0;
           velocityy=-1;
        }
       else if(e.getKeyCode()==KeyEvent.VK_DOWN && velocityy != -1)
       {
           velocityx=0;
           velocityy=1;
       }
       else if(e.getKeyCode()==KeyEvent.VK_LEFT && velocityx != 1)
       {
           velocityx=-1;
           velocityy=0;
       }
       else if(e.getKeyCode()==KeyEvent.VK_RIGHT && velocityx != -1)
       {
           velocityx=1;
           velocityy=0;
       }
} 
    

           


//not need for this prg
@Override
public void keyTyped(KeyEvent e) {}
       
@Override
public void keyReleased(KeyEvent e) {}
  
    
}
