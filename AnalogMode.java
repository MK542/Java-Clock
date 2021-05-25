import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AnalogMode extends JPanel{
	JFrame localFrame;
	JPanel localPanel;
	AnalogMode localMode;
	int hourCounter;
	int minuteCounter;
	int secondCounter;
	
	int pWidth;
	int pHeight;
	int centreX;
	int centreY;
	public AnalogMode(JFrame frame, int totalWidth, int totalHeight) {
		localFrame = frame;
		pWidth = totalWidth - 300;
		pHeight = totalHeight - 300;
		centreX = pWidth/2;
		centreY = pHeight/2;
		localFrame.setSize(pWidth+14, pHeight+35);
		localPanel = createPanel();
		localPanel.setBackground(Color.black);
		localFrame.add(localPanel);
		localFrame.setVisible(false);
//		System.out.println("Frame size (WxH): " + frame.getSize().getWidth() + " " + frame.getSize().getHeight());
//		System.out.println("localPanel size (WxH): " + localPanel.getSize().getWidth() + " " + localPanel.getSize().getHeight());
	}
	
	public void show(AnalogMode local) {
		localMode = local;
		String timeToDisplay = String.valueOf(LocalDateTime.now());
		timeToDisplay = timeToDisplay.substring(11, 19);
		hourCounter = Integer.parseInt(timeToDisplay.substring(0, 2));
		minuteCounter = Integer.parseInt(timeToDisplay.substring(3, 5));
		secondCounter = Integer.parseInt(timeToDisplay.substring(6, 8));
		localPanel.repaint();
		localFrame.setVisible(true);

	}
	public void vanish() {
		localFrame.setVisible(false);
	}
	public JPanel createPanel() {
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				drawFace(g);
				drawSec(g);
				drawMin(g);
				drawHour(g);
			}
		};
		
		panel.setSize(pWidth, pHeight);
		panel.setBackground(Color.white);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setLayout(new GridBagLayout());

		JLabel twelve = new JLabel("12");
		JLabel three = new JLabel("3");
		JLabel six = new JLabel("6");
		JLabel nine = new JLabel("9");
		
		twelve.setFont(new Font("Times New Roman", Font.BOLD, 32));
		three.setFont(new Font("Times New Roman", Font.BOLD, 32));
		six.setFont(new Font("Times New Roman", Font.BOLD, 32));
		nine.setFont(new Font("Times New Roman", Font.BOLD, 32));
		
		GridBagConstraints c = new GridBagConstraints();
			
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
			
//			System.out.println(panel.getPreferredSize());
//			System.out.println("Panel size (WxH): " + panel.getSize().getWidth() + " " + panel.getSize().getHeight());
//			System.out.println("Frame size (WxH): " + localFrame.getSize().getWidth() + " " + localFrame.getSize().getHeight());

			
			return panel;
	}
	
	
	
	
	
	public void drawFace(Graphics g) {
//		int oWidth = (3/4)*pWidth;
//		int oHeight = (3/4)*pHeight;
		int oWidth = pWidth;
		int oHeight = pHeight;
		g.setColor(Color.white);
		g.fillOval(centreX-oWidth/2, centreY-oHeight/2, oWidth, oHeight);
	}
	public void drawSec(Graphics g) {
		int thetaDeg = 6*secondCounter;//degrees
		double thetaRad = thetaDeg *Math.PI/180;//radians now
		int lineLength = (int) (0.4*pWidth);
		//System.out.println("SecondlineLength: " + lineLength);
		int x1 = centreX;
		int y1 = centreY;
		int x2 = (int) (x1 + lineLength *Math.sin(thetaRad));//sin and cos in this manner so 0 degrees (or 0 rad) is 
		//straight up
		int y2 = (int) (y1 + -1*lineLength *Math.cos(thetaRad));
		g.setColor(Color.black);
		g.drawLine(x1, y1, x2, y2);
		//System.out.println("In drawSec");
	}
	public void drawMin(Graphics g) {
		int thetaDeg = 6*minuteCounter;//degrees
		double thetaRad = thetaDeg *Math.PI/180;//radians now
		int lineLength = (int) (0.3*pWidth);
		//System.out.println("minuteLineLength: " + lineLength);

		int x1 = centreX;
		int y1 = centreY;
		int x2 = (int) (x1 + lineLength *Math.sin(thetaRad));//sin and cos in this manner so 0 degrees (or 0 rad) is 
		//straight up
		int y2 = (int) (y1 + -1*lineLength *Math.cos(thetaRad));
		g.setColor(Color.black);
		g.drawLine(x1, y1, x2, y2);
		//System.out.println("In drawMinHand");
	}
	public void drawHour(Graphics g) {
		int thetaDeg = 30*hourCounter;//degrees
		double thetaRad = thetaDeg *Math.PI/180;//radians now
		int lineLength = (int) (0.1*pWidth);
		//System.out.println("HourlineLength: " + lineLength);

		int x1 = centreX;
		int y1 = centreY;
		int x2 = (int) (x1 + lineLength *Math.sin(thetaRad));//sin and cos in this manner so 0 degrees (or 0 rad) is 
		//straight up
		int y2 = (int) (y1 + -1*lineLength *Math.cos(thetaRad));
		g.setColor(Color.BLACK);
		g.drawLine(x1, y1, x2, y2);
		//System.out.println("In drawHourHand");
	}
}
