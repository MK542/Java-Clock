import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class GUI extends JPanel{
	String app_name;
	int app_height;
	int app_width;
	String currentMode = "";
	GUI gui;
	Digital digital;
	Analog analog;
	JPanel panel;
	JRadioButton mode1 = new JRadioButton("Digital");
	JRadioButton mode2 = new JRadioButton("Analog");
	JRadioButton mode3 = new JRadioButton("Stopwatch");
	public GUI(String title, int height, int width) {
		app_name = title;
		app_height = height;
		app_width = width;
	}
	
	public void create(JLabel lab, GUI guiInst, Digital dig, JPanel pan) {
		JFrame frame = new JFrame(app_name);
		ButtonGroup modes = new ButtonGroup();
		
		this.setLayout(null);

		gui = guiInst;
		digital = dig;
		panel = pan;
		gui.add(panel);
		mode1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mode1Pressed();
			}
		});
		mode2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mode2Pressed();
			}
		});
		mode3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mode3Pressed();
			}
		});
		mode1.setBounds(50, 50, 70, 30);
		mode2.setBounds(50, 90, 70, 30);
		mode3.setBounds(50, 130, 90, 30);

		modes.add(mode1);
		modes.add(mode2);
		modes.add(mode3);
		gui.add(mode1);
		gui.add(mode2);
		gui.add(mode3);
		frame.setSize(app_height, app_width);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(gui);
		
		
	}
	public String getCurrentMode() {
		return currentMode;
	}
	public void mode1Pressed() {
		currentMode = "digital";
	}
	public void mode2Pressed() {
		currentMode = "analog";		
	}
	public void mode3Pressed() {
		currentMode = "stopwatch";
	}
	
	
	
	//future modes to develop after analog (which is currently in dev)
//	public void mode3Pressed() {
//		currentMode = "hourglass";
//		hourglass.show();
//	}
//	public void mode4Pressed() {
//		currentMode = "timer";
//		timer.show();
//	}

	
}
