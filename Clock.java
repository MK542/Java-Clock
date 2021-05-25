import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Clock {
	String title;
	int width;
	int height;
	String mode;
	JLabel digitalLabel = new JLabel();
	JPanel analogPanel = new JPanel();
	boolean isAlreadyRunning = false;
	Digital digitalInstance;
	JFrame digitalModeFrame = new JFrame();
	GUI guiInstance;
	
	
	AnalogMode analogModeInst;
	JFrame analogModeFrame = new JFrame();
	
	Stopwatch stopwatchInst;
	JFrame stopwatchFrame = new JFrame();
	int centreX;
	int centreY;
	public Clock(String appTitle, String clockMode, int x, int y) {
		
		title = appTitle;
		mode = clockMode;
		width = x;
		height = y;
		
		centreX = width/2;
		centreY = height/2;
	}
	
	public void begin() {
		if(isAlreadyRunning) {
			return;
		}else {
			isAlreadyRunning = true;
			initialize();
		}
	}
	
	public void initialize() {
		guiInstance = new GUI(title, width, height);
		digitalInstance = new Digital(digitalModeFrame, width, height);
//		analogInstance = new Analog();
		analogModeInst = new AnalogMode(analogModeFrame, width, height);
		stopwatchInst = new Stopwatch(stopwatchFrame, width, height);
		guiInstance.create(digitalLabel, guiInstance, digitalInstance, analogPanel);
		guiInstance.setBackground(Color.WHITE);
		digitalLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
		run();
	}
	public void run() {
		while(isAlreadyRunning) {
			tick();
		}
	}
	public void tick() {
		int delay = 200;
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			System.out.println("Failed to sleep for " + delay + " in tick() in class Clock");
			e.printStackTrace();
		}
		String mode = guiInstance.getCurrentMode();
		switch(mode) {
		case "digital":
			digitalInstance.show(digitalInstance);
			analogModeInst.vanish();
			stopwatchInst.vanish();
			break;
		case "analog":
			digitalInstance.vanish();
			analogModeInst.show(analogModeInst);
			stopwatchInst.vanish();
			break;
		case "stopwatch":
			digitalInstance.vanish();
			analogModeInst.vanish();
			stopwatchInst.show(stopwatchInst);
			break;
		default://what put here? can't put System.exit(0) since will close right after starting
		//	System.out.println("Default switch case");
		// maybe a general calendar thing? i.e. not a clock but still useful/related
			break;
	
	}
	
}
}
