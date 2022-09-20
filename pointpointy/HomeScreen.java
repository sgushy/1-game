package pointpointy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;


public class HomeScreen extends JFrame 
{        
	private static final long serialVersionUID = -4050573484674564828L;
	private JLabel bg;
	private JLabel logo;
	private JButton start;
	private SMain main;
	public boolean startG = false;
	public static boolean runs;
	public HomeScreen(SMain pClient)
	{
		this.main = pClient;
		bg = new JLabel("", new ImageIcon(SMain.class.getProtectionDomain().getCodeSource().getLocation().getPath()+this.rScreen()), SwingConstants.CENTER);
		runs = true;
		this.init();
		this.run();
	}

	private String rScreen() {
		if(Math.random() > 0.5D)
		{
			return "pointpointy/resources/textures/FerMainScreen2.png";
		}
		else
			return "pointpointy/resources/textures/Loading12.png";
	}

	private void run() {
		while(runs)
		{
			
		}
	}

	private void init()
	{
		JFrame frame = new JFrame();
		frame.setSize(1860,960);
		frame.add(bg);


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		start = new JButton();
		start.setBackground(Color.black);
		start.setText("Start");
		Box box = Box.createVerticalBox();
		box.add(start);
		//box.add(options);
		box.add(Box.createVerticalGlue());

		frame.add(box, BorderLayout.EAST);
		
		start.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseReleased(MouseEvent arg0) 
			{
				frame.setVisible(false);
				frame.dispose();

				startG = true;
				runs = false;
			}
		});
	}
}
