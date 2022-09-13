import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
	Paddle p1 = new Paddle(50, 250);
	Paddle p2 = new Paddle(750, 250);
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
		if(ball.getY() <= 0 || ball.getY() >= 520) {
			int currentY = ball.getVy();
			ball.setVelocityY(-currentY);
		}
		//ball collision with paddles
		if(ball.getY() >= p1.getY() && ball.getY() <= (p1.getY() + 125) &&
				ball.getX() >= p1.getX() && ball.getX() <= (p1.getX()+1)) {
			int currentY = ball.getVy();
			ball.setVelocityY(-currentY);
			int currentX = ball.getVx();
			ball.setVelocityX(-currentX);
		} 
		//paddle
//		p2.setVy(5); 
		if(p1.getY() == 0) {
			p1.setVy(0);
			}
		if(p1.getY() == 450) {
			p1.setVy(0);
			}	
		if(p2.getY() == 0) {
			p2.setVy(0);
			}
		if(p2.getY() == 450) {
			p2.setVy(0);
			}	
	



	}
	
	
	public static void main(String[] arg) {
		Frame f = new Frame();
		
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
			//w key
			if(arg0.getKeyCode()==87) {
			//update the y position of the right paddle
			p1.setVy(-5);
			}
			//s key
			if(arg0.getKeyCode()==83) {
			p1.setVy(5);
			//up key
			if(arg0.getKeyCode()==38) {
				//update the y position of the right paddle
				p2.setVy(-5);
			}
			//down key
			if(arg0.getKeyCode()==40) {
				p2.setVy(5);
			}
			}
		}
	

	@Override
	public void keyReleased(KeyEvent arg0) {
//		if(arg0.) {
				
//			}
		
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
