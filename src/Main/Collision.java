package Main;

import java.awt.*;

public class Collision {
    Color redColor = new Color(255,25,25,172);
    Color whiteColor = new Color(255,255,255,200);
    Ball ball;
    Brick player1;
    Brick player2;

    public Collision(Ball ball, Brick player1, Brick player2) {
        this.ball = ball;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void collisionBetweenBallAndBricks() {
        for (int i = 0; i < player1.height; i++) {
            if (player1.x+player1.width == ball.x && player1.y+i == ball.y || player1.x+player1.width == ball.x && player1.y+i == ball.y+ball.height || player1.x == ball.x+ball.width && player1.y+i == ball.y || player1.x == ball.x+ball.width && player1.y+i == ball.y+ball.height) {
                if (ball.left) {
                    ball.right = true;
                    ball.left = false;
                    //wyrownanie
                    superModeOFF();
                    // english a ball (podkręcanie lol)
                        if (player1.up && ball.down) {
                            ball.up = true;
                            ball.down = false;
                            //superMODE
                            superModeON();
                        }
                        if (player1.down && ball.up) {
                            ball.down = true;
                            ball.up = false;
                            //superMODE
                            superModeON();
                        }
                    //increase speed of ball
                    ball.speedOfBall-=5000;
                }
            }
            if (player2.x == ball.x+ball.width && player2.y+i == ball.y || player2.x == ball.x+ball.width && player2.y+i == ball.y+ball.height || player2.x+player2.width == ball.x && player2.y+i == ball.y || player2.x+player2.width == ball.x && player2.y+i == ball.y+ball.height) {
                if (ball.right) {
                    ball.left = true;
                    ball.right = false;
                    //wyrównanie
                    superModeOFF();
                    // english a ball (podkręcanie lol)
                    if (player2.up && ball.down) {
                        ball.up = true;
                        ball.down = false;
                        //superMODE
                        superModeON();
                    }
                    if (player2.down && ball.up) {
                        ball.down = true;
                        ball.up = false;
                        //superMODE
                        superModeON();
                    }
                    //increase speed of ball
                    ball.speedOfBall-=5000;
                }
            }
        }
    }
    public void superModeON() {
        if (ball.addX < 3) {
            ball.addX++;
            ball.addY--;
            ball.color = redColor;
            ball.speedOfBall = ball.speedOfBall - 10000;
        }
    }
    public void superModeOFF() {
        if (ball.addX == 3) {
            ball.addX--;
            ball.addY++;
            ball.color = whiteColor;
            ball.speedOfBall = ball.speedOfBall + 10000;
        }
    }
    public void setBall(Ball ball) {
        this.ball = ball;

    }
}
