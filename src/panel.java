import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.util.Random;
import java.util.Arrays;


public class panel extends JPanel implements ActionListener
{
    static int width =1200;
    static int height=600;
    static int unit = 40;
    Random random;
    boolean flag=false;
    int score=0;
    Timer timer;
    int fx;
    int fy;
    int length=5;
    int xsnake[]=new int[450];
    int ysnake[]=new int[450];
    char dir='R';
    panel()
    {
        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(Color.black);
        this.addKeyListener(new key());
        this.setFocusable(true);
        random=new Random();
        gamestart();

    }
    public void gamestart()
    {
        spawnfood();
        flag=true;
        timer=new Timer(160,this);
        timer.start();
    }
    public void spawnfood()
    {
        fx=random.nextInt(width/unit)*unit;
        fx=random.nextInt(height/unit)*unit;


    }
    public void paintComponent(Graphics graphic)
    {
        super.paintComponent(graphic);
        draw(graphic);
    }
    public void draw(Graphics graphic)
    {
        if(flag)
        {
            graphic.setColor(Color.red);
            graphic.fillOval(fx,fy,30,30);
            for(int i=0;i<length;i++)
            {
                graphic.setColor(Color.green);
                graphic.fillRect(xsnake[i],ysnake[i],40,40);
            }
            // Score text
            graphic.setColor(Color.LIGHT_GRAY);
            graphic.setFont(new Font("Comic Sans",Font.BOLD,30));
            FontMetrics fm=getFontMetrics(graphic.getFont());
            graphic.drawString("Score:"+score, (width-fm.stringWidth("Score:"+score))/2, graphic.getFont().getSize());
            //graphic.drawString("Press R to replay", (width-fm.stringWidth("Press R to replay"))/2,3+height/4);

        }else
        {
            gameover(graphic);
        }

    }
    public void gameover(Graphics graphic)
    {
        graphic.setColor(Color.CYAN);
        graphic.setFont(new Font("Comic Sans",Font.BOLD,30));
        FontMetrics fm=getFontMetrics(graphic.getFont());
        graphic.drawString("Score:"+score, (width-fm.stringWidth("Score:"+score))/2, graphic.getFont().getSize());

        //game over text
        graphic.setColor(Color.red);
        graphic.setFont(new Font("Comic Sans",Font.BOLD,50));
        fm=getFontMetrics(graphic.getFont());
        graphic.drawString("GAME OVER",(width-fm.stringWidth("GAME OVER"))/2,height/2);

        graphic.setColor(Color.ORANGE);
        graphic.setFont(new Font("Comic Sans",Font.BOLD,30));
         fm=getFontMetrics(graphic.getFont());
        graphic.drawString("Press R to replay", (width-fm.stringWidth("Press R to replay"))/2,3+height/4);


    }
    public void eat()
    {
        //checking wheater the snake head is above the food particle
        if(fx==xsnake[0]&&fy==ysnake[0])
        {
            length++;
            score++;
            spawnfood();
        }
    }
    public void hit()
    {
        for(int i=length-1;i>0;i--)
        {
            if(xsnake[0]==xsnake[i]&&ysnake[0]==ysnake[i])
            {
                flag=false;
            }
        }
        if(xsnake[0]<0||xsnake[0]>width)
        {
            flag=false;
        }else
        {
            if(ysnake[0]<0||ysnake[0]>height)
            {
                flag=false;
            }
        }
        if(flag==false)
        {
            timer.stop();
        }
    }
    public void move()
    {
        //updating the bode of snake except the head;
        for(int i=length-1;i>0;i--)
        {
            xsnake[i]=xsnake[i-1];
            ysnake[i]=ysnake[i-1];


        }
        //updating the direction of head
        switch (dir)
        {
            case 'U':
                ysnake[0]=ysnake[0]-unit;
                break;
            case 'D':
                ysnake[0]=ysnake[0]+unit;
                break;
            case 'R':
                xsnake[0]=xsnake[0]+unit;
                break;
            case 'L':
                xsnake[0]=xsnake[0]-unit;
                break;



        }
    }
    //for assigning the values for the keys
    public class key extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                if(dir!='D')
                {
                    dir='U';
                }
                break;
                case KeyEvent.VK_DOWN:
                    if(dir!='U')
                    {
                        dir='D';
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if(dir!='R')
                    {
                        dir='L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(dir!='L')
                    {
                        dir='R';
                    }
                    break;
                case KeyEvent.VK_R:
                    if(flag==false) {
                        score = 0;
                        length = 3;
                        dir = 'R';
                        Arrays.fill(xsnake,0);
                        Arrays.fill(ysnake,0);
                        gamestart();
                    }
                    break;

            }
        }
    }







//for running the game
    public void actionPerformed(ActionEvent e)
    {
        if(flag)
        {
            move();
            hit();
            eat();


        }
        // this will repaint the panel for every 160 seconds

        repaint();

    }

}
