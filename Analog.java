import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Analog extends JPanel{
	int hourCounter;
	int minuteCounter;
	int secondCounter;
	
	int width;
	int height;
	int centreX;
	int centreY;
	
	int pWidth;
	int pHeight;
	JPanel panel;
	
	Analog analogInst;
	public Analog() {
		System.out.println("Empty Analog Constructor");
	}
	
	public void show(JPanel pan, GUI myGUI, int centX, int centY, Analog analInst) {
		String timeToDisplay = String.valueOf(LocalDateTime.now());
		timeToDisplay = timeToDisplay.substring(11, 19);
		hourCounter = Integer.parseInt(timeToDisplay.substring(0, 2));
		minuteCounter = Integer.parseInt(timeToDisplay.substring(3, 5));
		secondCounter = Integer.parseInt(timeToDisplay.substring(6, 8));
		
		panel = pan;
		analogInst = analInst;
				
		centreX = centX;
		centreY = centY;
		width = centreX*2;
		height = centreY*2;
		
	
		//analogInst.add(panel);
//		analogInst.setLocation(centreX-pWidth/2, centreY-pHeight/2);
		
		
		
		panel.setLayout(new GridBagLayout());
		pWidth = width-300;
		pHeight = height-300;
		panel.setSize(pWidth, pHeight);
		
		//centre-pDim/2 to align centre of panel w/ centre
		panel.setLocation(centreX-pWidth/2, centreY-pHeight/2);
		
		
		
		panel.setBackground(Color.white);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel twelve = new JLabel("12");
		JLabel three = new JLabel("3");
		JLabel six = new JLabel("6");
		JLabel nine = new JLabel("9");
		
		twelve.setFont(new Font("Times New Roman", Font.BOLD, 32));
		three.setFont(new Font("Times New Roman", Font.BOLD, 32));
		six.setFont(new Font("Times New Roman", Font.BOLD, 32));
		nine.setFont(new Font("Times New Roman", Font.BOLD, 32));

		
GridBagConstraints c = new GridBagConstraints();
	analogInst.setLayout(new GridBagLayout());	
		
		
		c.weightx = 0.5;
		c.weighty = 0.5;
		
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.PAGE_START;
		panel.add(twelve, c);
		
		c.gridx = 2;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_END;
		panel.add(three, c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.PAGE_END;
		panel.add(six, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_START;
		panel.add(nine, c);
		
		analogInst.setSize(pWidth, pHeight);
		analogInst.setBackground(Color.yellow);
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		panel.add(analogInst, c);
//		System.out.println("Width: " + analogInst.getSize().getWidth());
//		System.out.println("Height: " + analogInst.getSize().getHeight());
		
		repaint();

	}
	public void vanish() {
		
	}
	//@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.drawString("24", centreX, (int) (centreY-pHeight/2*0.8));
		g.drawString("30", centreX, (int) (centreY-0.5*pHeight/2));
		System.out.println("here");
		drawSecHand(g);
		drawMinHand(g);
		drawHourHand(g);
	}
//	public void secHand(int theta) {
//		theta*=6;//in degrees
//		theta = (int) (theta*Math.PI/180);//now in radians
//	}
//	
	public void drawSecHand(Graphics g){//MUST MAKE secondCounter public along with centreX and centreY
		int theta = 6*secondCounter;//degrees
		theta = (int) (theta *Math.PI/180);//radians now
		int lineLength = (int) (0.8*pWidth);
		System.out.println("SecondlineLength: " + lineLength);
		int x1 = centreX;
		int y1 = centreY;
		int x2 = (int) (x1 + lineLength *Math.sin(theta));//sin and cos in this manner so 0 degrees (or 0 rad) is 
		//straight up
		int y2 = (int) (y1 + lineLength *Math.cos(theta));
		g.setColor(Color.orange);
		g.drawLine(x1, y1, x2, y2);
		System.out.println("In drawSecHand");
	}
	public void drawMinHand(Graphics g){
		int theta = 6*minuteCounter;//degrees
		theta = (int) (theta *Math.PI/180);//radians now
		int lineLength = (int) (0.5*pWidth);
		System.out.println("minuteLineLength: " + lineLength);

		int x1 = centreX;
		int y1 = centreY;
		int x2 = (int) (x1 + lineLength *Math.sin(theta));//sin and cos in this manner so 0 degrees (or 0 rad) is 
		//straight up
		int y2 = (int) (y1 + lineLength *Math.cos(theta));
		g.setColor(Color.green);
		g.drawLine(x1, y1, x2, y2);
		System.out.println("In drawMinHand");
	}
	public void drawHourHand(Graphics g){
		int theta = 30*hourCounter;//degrees
		theta = (int) (theta *Math.PI/180);//radians now
		int lineLength = (int) (0.2*pWidth);
		System.out.println("HourlineLength: " + lineLength);

		int x1 = centreX;
		int y1 = centreY;
		int x2 = (int) (x1 + lineLength *Math.sin(theta));//sin and cos in this manner so 0 degrees (or 0 rad) is 
		//straight up
		int y2 = (int) (y1 + lineLength *Math.cos(theta));
		g.setColor(Color.BLACK);
		g.drawLine(x1, y1, x2, y2);
		System.out.println("In drawHourHand");
}
}
