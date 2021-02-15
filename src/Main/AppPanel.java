package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class AppPanel extends JPanel implements Runnable, KeyListener {
    final static int WIDTH=1000,HEIGHT = WIDTH*9/12;

    private Thread thread;
    private JPanel jPanel;
    private Brick player1,player2;
    private Ball ball;
    private Collision collision;
    private Random r;

    private int ticks=0,ballTicks=0,keyP,keyR;

    private boolean running = false;

    public AppPanel() {
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setFocusable(true);
        setLayout(new BorderLayout());

        jPanel = new JPanel();
        //adding
        add(jPanel);
        addKeyListener(this);

        //creating players
        player1 = new Brick(1);
        player2 = new Brick(2);
        ball = new Ball(true,false,495,750/2);
        collision = new Collision(ball,player1,player2);
        r = new Random();

        //start thread
        start();
    }
    public void start() {
        try {
            thread = new Thread(this);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        thread.start();
        running = true;
    }
    public void stop() {
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.fillInStackTrace();
        }
    }
    public void tick() {
        if (ticks > 40000) {
            //steerage system
            player1.tick();
            player2.tick();

            ticks = 0;
        }
        ticks++;
    }
    public void ballTick() {
        if (ballTicks > ball.speedOfBall) {
            //ball moving and wall detecting
            ball.tick();

            //ball collision with gamers
            collision.collisionBetweenBallAndBricks();

            //counting points test version and creating new ball
            if (ball.x > 1000 || ball.x < 0) {
                if (ball.x > 1000) {
                    player1.points++;
                    //new ball
                    ball = new Ball(false, true,600,r.nextInt(15)*50);
                    collision.setBall(ball);
                }
                if (ball.x < 0) {
                    player2.points++;
                    //new ball
                    ball = new Ball(true, false,400,r.nextInt(15)*50);
                    collision.setBall(ball);
                }

                //break after goal
                try {
                    thread.sleep(2500);
                } catch (InterruptedException ex) {
                    System.out.println("Something goes wrong with thread.sleep");
                }
            }
            ballTicks=0;
        }
        ballTicks++;
    }

    @Override
    public void run() {
        while(running) {
            repaint();
            tick();
            ballTick();
        }
    }

    public void paint(Graphics g) {
        //background
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);
        //brick's player
        player1.draw(g);
        player2.draw(g);
        //ball
        ball.draw(g);
        //dotted thing
        g.setColor(Color.white);
        for (int i = 0; i < 15; i++) {
            g.fillRect(499,12+50*i,2,25);
        }
        //display points
        g.setFont(new Font("Arial", Font.BOLD, 75));
        g.drawString(player1.points +"",250,100);
        g.drawString(player2.points +"",750,100);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keyP = keyEvent.getKeyCode();
        //player1 down start
        if (keyP == 83) {
            player1.down = true;
            player1.up=false;
        }
        //player1 up start
        if (keyP == 87) {
            player1.up = true;
            player1.down = false;
        }
        //player2 down start
        if (keyP == 40) {
            player2.down = true;
            player2.up=false;
        }
        //player2 up start
        if (keyP == 38) {
            player2.up = true;
            player2.down = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keyR = keyEvent.getKeyCode();
        //player1 down stop
        if (keyR == 83 && keyP!=87) {
            player1.down = false;
            player1.up=false;
        }
        //player1 up stop
        if (keyR == 87 && keyP!=83) {
            player1.up = false;
            player1.down = false;
        }
        //player2 down stop
        if (keyR == 40 && keyP!=38) {
            player2.down = false;
            player2.up=false;
        }
        //player2 up stop
        if (keyR == 38 && keyP!=40) {
            player2.up = false;
            player2.down = false;
        }
    }
}
