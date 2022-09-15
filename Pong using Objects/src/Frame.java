import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements KeyListener, ActionListener{
	//github token code  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 
	Ball ball = new Ball(375, 250, 50);
	Paddle p1 = new Paddle(25, 250);
	Paddle p2 = new Paddle(750, 250);
	Rectangle upper = new Rectangle(0, 60, 800, 1);
	Rectangle lower = new Rectangle(0, 570, 800, 1);
	
	Rectangle background = new Rectangle(0, 0, 800, 600);
	int score1 = 0;
	int score2 = 0; 
	/* paint is getting called roughly 60x per second */
	public void paint(Graphics g) {
		super.paintComponent(g);
		p1.paint(g);
		p2.paint(g);
		ball.paint(g);
		//ball collision with walls
		if(ball.getX() >= 740 || ball.getX() <= 0) {
			int currentX = ball.getVx();  
		
			ball.setVelocityX(-currentX);
		}
		if(ball.getY() <= 60 || ball.getY() >= 520) {
			int currentY = ball.getVy();
			ball.setVelocityY(-currentY);
		}
		if(ball.getY() >= p2.getY() && ball.getY() <= (p2.getY() + 125) &&
				ball.getX() == p2.getX()){
			int currentY = ball.getVy();
			ball.setVelocityY(-currentY);
			int currentX = ball.getVx();
			ball.setVelocityX(-currentX);
		}
		Rectangle rBall = new Rectangle(ball.getX(), ball.getY(), ball.getWidth(), ball.getWidth());
		Rectangle rP1 = new Rectangle(p1.getX(), p1.getY(), p1.getWidth(), p1.getHeight());
		Rectangle rP2 = new Rectangle(p2.getX(), p2.getY(), p2.getWidth(), p2.getHeight());
		Rectangle left = new Rectangle(0, 60, 1, 600);
		Rectangle right = new Rectangle(790, 60, 1, 600);
		if (rBall.intersects(rP1)) {
			int currentY = ball.getVy();
			ball.setVelocityY(-currentY);
			int currentX = ball.getVx();
			ball.setVelocityX(-currentX);
		} 
		if (rBall.intersects(rP2)) {
			int currentY = ball.getVy();
			ball.setVelocityY(-currentY);
			int currentX = ball.getVx();
			ball.setVelocityX(-currentX);
		}
		
		if(rBall.intersects(left)) {
			score2++; 
			ball.setX(375);
			ball.setY(250);
		}
		Font font = new Font("Monospaced", Font.BOLD, 20);
		g.setFont(font);
		g.drawString("player 1 score: " + score1, 50, 50);
		
		if(rBall.intersects(right)) {
			score1++; 
			ball.setX(375);
			ball.setY(250);
		}
		g.setFont(font);
		g.drawString("player 2 score: " + score2, 530, 50);
		



	}
	
	
	public static void main(String[] arg) {
		Frame f = new Frame();
		f.setBackground(Color.black);
	}	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		//information about the key that is pressed lives in the
		//key events object called arg0
		//arg0 can be renamed to anything
//		System.out.println(arg0.getKeyCode());
		//87 is the w key
		//83 is the s key
		//38 is the up arrow
		//40 is the down arrow
			//switch statements
		Rectangle rP1 = new Rectangle(p1.getX(), p1.getY(), 20, 125);
		Rectangle rP2 = new Rectangle(p2.getX(), p2.getY(), 20, 125);
		switch(arg0.getKeyCode()) {
			//look for special values (cases)
			case 87: //w key
				p1.setVy(-5);
				break; //prevent bleeding into toher cases
			case 83: //s key
				p1.setVy(5);
				break; 
			}
		switch(arg0.getKeyCode()) {
			case 38: //up arrow
				p2.setVy(-5);
				break;
			case 40: //down arrow
				p2.setVy(5);
		}
		if(arg0.getKeyCode() == 87 && rP1.intersects(upper)) {
			p1.setVy(0);
		}
		if(arg0.getKeyCode() == 83 && rP1.intersects(lower)) {
			p1.setVy(0);
		}
		if(arg0.getKeyCode() == 38 && rP2.intersects(upper)) {
			p2.setVy(0);
		}
		if(arg0.getKeyCode() == 40 && rP2.intersects(lower)) {
			p2.setVy(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == 87 || arg0.getKeyCode() == 83) {
				p1.setVy(0);
			}
		if(arg0.getKeyCode() == 38 || arg0.getKeyCode() == 40) {
				p2.setVy(0);
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}
	
	Timer t;
	
	public Frame() {
		JFrame f = new JFrame("Pong");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800,600);
		f.add(this);
		f.addKeyListener(this);
		
		ball.setVelocityX(6);
		ball.setVelocityY(6);
		t = new Timer(16, this);
		t.start();
		f.setVisible(true);
		
	}
}
