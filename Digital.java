import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Digital {
	JLabel label = new JLabel();
	Digital localMode;
	JFrame localFrame;
	JPanel localPanel;
	int pWidth;
	int pHeight;
	int centreX;
	int centreY;
	public Digital(JFrame frame, int totalWidth, int totalHeight) {
		System.out.println("Beginning of constructor in Digital class");
		localFrame = frame;
		pWidth = totalWidth - 300;
		pHeight = totalHeight - 300;
		localFrame.setSize(pWidth+14, pHeight+35);
		centreX = pWidth/2;
		centreY = pHeight/2;
		localPanel = createPanel();
	}
	public JPanel createPanel() {
		JPanel panel = new JPanel();
		
		panel.setSize(pWidth, pHeight);
		panel.setBackground(Color.white);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		panel.add(label, c);
		
		return panel;
	}
	public void show(Digital local) {
		localMode = local;
		String timeToDisplay = String.valueOf(LocalDateTime.now());
		timeToDisplay = timeToDisplay.substring(11, 19);
		//System.out.println(timeToDisplay);
		label.setText(timeToDisplay);
		label.setFont(new Font("Times New Roman", Font.BOLD, 32));
		localPanel.add(label);
		localFrame.add(localPanel);
		localFrame.setVisible(true);
		
	}
	public void vanish() {
		localFrame.setVisible(false);
	}
}
