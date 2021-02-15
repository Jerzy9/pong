package Main;

import java.awt.*;

public class Ball {

    int x=400,y=600,width=10,height=10;
    int addX = 2,addY = 2;
    boolean up = true,down = false,right,left;
    int speedOfBall = 110000;
    private Color whiteColor = new Color(255,255,255,200);
    Color color;

    public Ball(boolean right,boolean left,int x,int y) {
        this.right = right;
        this.left = left;
        this.x = x;
        this.y = y;
        color = whiteColor;
    }
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x,y,width,height);
    }
    void tick() {
        collision();
        if(up) up();
        if(down) down();
        if(right) right();
        if(left) left();
    }
    //Collision with Walls
    public void collision() {
        // collision with TOP BAR
        if(x<1000 && x>0 && y<=0) {
            down = true;
            up = false;
        }
        // collision with BOTTOM BAR
        if(x<1000 && x>0 && y>=750) {
            up = true;
            down = false;
        }
    }
    public void up() {
        y-=addY;
    }
    public void down() {
        y+=addY;
    }
    public void right() {
        x+=addX;
    }
    public void left() {
        x-=addX;
    }
}
