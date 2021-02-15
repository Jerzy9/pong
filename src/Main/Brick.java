package Main;

import java.awt.*;

public class Brick {
    int x,y,width,height;
    boolean up= false,down = false;
    int points=0;


    public Brick(int number) {
        if(number == 1) {
            this.x = 50;
            this.y = 100;
            this.width = 10;
            this.height = 100;
        }
        if(number == 2) {
            this.x = 950;
            this.y = 100;
            this.width = 10;
            this.height = 100;
        }
    }
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x,y,width,height);

    }
    void tick() {
        //steerage system
        if (up) {
            up();
        }
        if (down) {
            down();
        }
    }
    public void up() {
        if (y>0) {
            y-=2;
        }
    }
    public void down() {
        if (y+height<750) {
            y+=2;
        }
    }
}
