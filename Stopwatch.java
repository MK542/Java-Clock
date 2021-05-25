import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Stopwatch {
	JFrame localFrame;
	JPanel localPanel;
	JLabel toDisplay = new JLabel();
	Stopwatch localMode;
	int hourCounter;
	int minuteCounter;
	int secondCounter;
	
	int pWidth;
	int pHeight;
	int centreX;
	int centreY;
	
	boolean isTiming = false;
	boolean isResuming = false;
	long tempStart = 0;
	long tempEnd = 0;
	
	long timeDiff;
	long startTime;
	long endTime;
	
	
	public Stopwatch(JFrame frame, int totalWidth, int totalHeight) {
		localFrame = frame;
		pWidth = totalWidth - 300;
		pHeight = totalHeight - 300;
		centreX = pWidth/2;
		centreY = pHeight/2;
		timeDiff = 0;
		startTime = 0;
		endTime = 0;
		localFrame.setSize(pWidth+14, pHeight+35);
		localPanel = createPanel();
		localFrame.add(localPanel);
		localFrame.setVisible(false);
	}
	public JPanel createPanel() {
		JPanel panel = new JPanel();
		
		panel.setSize(pWidth, pHeight);
		panel.setBackground(Color.white);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setLayout(new GridBagLayout());
		
		JButton start = new JButton("Start");
		JButton stop = new JButton ("Stop");
		JButton reset = new JButton("Reset");
		
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isResuming) {
					startTime = System.currentTimeMillis();
				}else {
					tempEnd = System.currentTimeMillis();
					isResuming = false;
				}
				isTiming = true;
			}
		});
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempStart = System.currentTimeMillis();
				isTiming = false;
				isResuming = true;
			}
		});
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isTiming = false;
				isResuming = false;
				timeDiff = 0;
				tempStart = 0;
				tempEnd = 0;
			}
		});
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.gridy = 2;
		
		c.gridx = 0;
		panel.add(start, c);
		c.anchor = GridBagConstraints.PAGE_END;
		c.gridx = 1;
		panel.add(stop, c);
		c.anchor = GridBagConstraints.LAST_LINE_END;
		c.gridx = 2;
		panel.add(reset, c);
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.PAGE_START;
		toDisplay.setFont(new Font("Times New Roman", Font.BOLD, 32));
		panel.add(toDisplay, c);
		return panel;
	}
	public void show(Stopwatch local) {
		localMode = local;
		localFrame.setVisible(true);
		if(isTiming) {
			timeDiff = System.currentTimeMillis() - startTime - (tempEnd - tempStart);
		}
		toDisplay.setText(String.valueOf(timeDiff));
		localFrame.setVisible(true);
	}
	
	public void vanish() {
		localFrame.setVisible(false);
	}
}
