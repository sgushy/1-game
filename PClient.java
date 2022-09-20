import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

import pointpointy.HomeScreen;
import pointpointy.SMain;

public class PClient {

	private JLabel logo = new JLabel("", new ImageIcon(SMain.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"pointpointy/resources/textures/SplashLogo.png"), SwingConstants.CENTER);
	private JLabel bg = new JLabel("", new ImageIcon(SMain.class.getProtectionDomain().getCodeSource().getLocation().getPath()+this.rScreen()), SwingConstants.CENTER);
	
	private SMain main = new SMain();
	private HomeScreen hs;
	public boolean shouldClose = false;
	public static void main(String[] args) {
		
		new PClient().run();
	}
	
	private String rScreen() {
		if(Math.random() > 0.5D)
		{
			return "pointpointy/resources/textures/FerMainScreen2.png";
		}
		else
			return "pointpointy/resources/textures/Loading12.png";
	}
	
	private void run()
	{
		//FerGraphics.main11(null);
		
		JWindow window = new JWindow();
		window.getContentPane().add(logo);

		window.setBounds(600, 300, 800, 450);
		window.setVisible(true);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		window.setVisible(false);

		//box.add(quit);
		main.run();
		//this.showHome();
	
		//window.dispose();
		
        //System.exit(0);
	}

	private void showHome() {
		JFrame frame = new JFrame();
		frame.setSize(1860,960);
		frame.add(bg);


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton start = new JButton();
		start.setBackground(Color.black);
		start.setText("Start");
		Box box = Box.createVerticalBox();
		box.add(start);
		//box.add(options);
		box.add(Box.createVerticalGlue());

		frame.add(box, BorderLayout.EAST);
		frame.setVisible(true);
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
				//frame.dispose();
				main.setVisible(true);
				main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				main.setAutoRequestFocus(true);
				main.run();
			}
		});
	}

}
