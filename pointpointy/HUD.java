package pointpointy;

import java.awt.BorderLayout;

import javax.swing.*;

public class HUD extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7217291785811834658L;
	private JPanel bottom;
	private JPanel side;
	public HUD(SMain s)
	{
		bottom = new JPanel(new BorderLayout());
		side = new JPanel(new BorderLayout());
	}

}
