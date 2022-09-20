package pointpointy;

import java.awt.Component; 
import java.awt.event.*; 

/** 
 * Makes handling input a lot simpler 
 */ 

public class InputHandler implements KeyListener, MouseListener
{
	boolean[] keys = new boolean[256];
	int mouse = 0; 
	int mx = 0;
	int my = 0;
	boolean mouseClick = false;
	SMain main;
        /** 
         * Assigns the newly created InputHandler to a Component 
         * @param c Component to get input from 
         */ 
        public InputHandler(Component c) 
        { 
                c.addKeyListener(this); 
                ((SMain)c).gmap.addMouseListener(this);
                main = (SMain)c;
                //c.addMouseListener(this);
        } 
        
        /** 
         * Checks whether a specific key is down 
         * @param keyCode The key to check 
         * @return Whether the key is pressed or not 
         */ 
        public boolean isKeyDown(int keyCode) 
        { 
                if (keyCode > 0 && keyCode < 256) 
                { 
                        return keys[keyCode]; 
                } 
                
                return false; 
        } 
        
        public int mouseX()
        {
        	return mx;
        }
        
        public int mouseY()
        {
        	return my;
        }
        
        public int mouseButton()
        {
        	return this.mouse;
        }
        
        public boolean mouseClickR()
        {
        	return this.mouseClick;
        }
        
        /** 
         * Called when a key is pressed while the component is focused 
         * @param e KeyEvent sent by the component 
         */ 
        @Override
        public void keyPressed(KeyEvent e) 
        { 
                if (e.getKeyCode() > 0 && e.getKeyCode() < 256) 
                { 
                        keys[e.getKeyCode()] = true; 
                } 
        } 

        /** 
         * Called when a key is released while the component is focused 
         * @param e KeyEvent sent by the component 
         */ 

        @Override
        public void keyReleased(KeyEvent e) 
        { 
                if (e.getKeyCode() > 0 && e.getKeyCode() < 256) 
                { 
                        keys[e.getKeyCode()] = false; 
                } 
        } 

        /** 
         * Not used 
         */ 

        @Override
        public void keyTyped(KeyEvent e){}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			mouseClick = true;
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			mouseClick = true;
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			this.mouse = arg0.getButton();
			this.mx = arg0.getX();
			this.my = arg0.getY();
			SMain.notifyConsole1(this.mouse+" "+this.mx+" "+this.my);
			if(mouse == MouseEvent.BUTTON1)
			{
				EntityUnit ipp = main.world.locNear(mx, my, 40);
				if(main.world.currSelection()!= null)
				{
					ipp = main.world.locNear(main.world.currSelection(), mx, my, 40);
					if(ipp!=null)
					{
						main.world.selectUnit(ipp);
					}
				}
				else if(ipp != null)
				{
					main.world.selectUnit(ipp);
				}
				else
				{
					main.world.selectUnit(null);
				}
			}
			if(mouse == MouseEvent.BUTTON2)
			{
				if(main.world.currSelection()!= null)
				{
					if(main.world.currSelection().isPlayerUnit)
					{
						main.world.currSelection().cancelOrders();
					}
				}
			}
			if(mouse == MouseEvent.BUTTON3)
			{
				if(main.world.currSelection()!= null)
				{
					EntityUnit ptE =main.world.locNear(mx, my, 20);
					
				    if(main.world.currSelection().isPlayerUnit)
					{
				    	if(ptE != null)
						{
				    		if(!ptE.isPlayerUnit)
				    		{
				    			main.world.currSelection().setTarget(ptE, true);
				    		}
				    		else if(ptE.isPlayerUnit)
				    		{
				    			if(ptE.isVehicle && ptE.rider() == null && (ptE.type.name().contains("IFV")||(ptE.type.name().contains("APC"))))
				    			{
				    				main.world.currSelection().cancelOrders();
				    				ptE.setDestination(main.world.currSelection().x(), main.world.currSelection().y());
				    				ptE.setFlagged(main.world.currSelection());
				    				if(main.world.calcPos(ptE, main.world.currSelection()) <= 10)
					    			{
					    				main.world.currSelection().mountVehicle(ptE);
					    			}
				    			}
				    		}
						}
				    	else if(main.world.currSelection().riding()!=null)
				    	{
				    		main.world.currSelection().riding().setDestination(mx, my);
				    	}
				    	else
						main.world.currSelection().setDestination(mx, my);
					}
				}
			}
			mouseClick = false;
		}

		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseMoved(MouseEvent e) {
			mouseClick = false;
		}

		public void handleKeys() {
			 if (isKeyDown(KeyEvent.VK_W)) 
    		 { 
    			 main.notifyConsole("Pressed W");
    		 } 
			 if (isKeyDown(KeyEvent.VK_S)) 
    		 { 
    			 main.notifyConsole("Pressed S");
    		 } 
			 if (isKeyDown(KeyEvent.VK_A)) 
    		 { 
    			 main.notifyConsole("Pressed A");		 
    		 } 
			 if (isKeyDown(KeyEvent.VK_D)) 
    		 { 
    			 main.notifyConsole("Pressed D");			 
    		 } 
			 
			 if (isKeyDown(KeyEvent.VK_X)) 
    		 { 
    			 main.notifyConsole("Pressed X");
    			 EntityUnit newP = main.world.currSelection();
    			 if(newP != null && newP.isPlayerUnit)
    				 newP.cancelOrders();
    		 } 
			 
			 if (isKeyDown(KeyEvent.VK_SPACE)) 
    		 { 
    			 main.notifyConsole("Pressed SPACEBAR");
    			 EntityUnit newP = main.world.currSelection();
    			 if(newP != null && newP.isPlayerUnit)
    			 {
    				 if(newP.isVehicle)
    				 {
    					 if(newP.rider() != null)
    					 {
    						 main.world.selectUnit(newP.rider());
    						 newP.rider().dismountVehicle();
    						 newP.dismountVehicle();
    					 }
    				 }
    				 else if(newP.riding() != null)
    				 {
    					 main.world.selectUnit(newP);
						 newP.riding().dismountVehicle();
						 newP.dismountVehicle();
    				 }
    			 }
    		 } 
			 
    		 if (isKeyDown(KeyEvent.VK_S)) 
    		 { 
    			 main.notifyConsole("Pressed S");
    			 EntityUnit newP = main.world.prevPlayerUnit();
    			 if(newP != null)
    				 main.world.selectUnit(newP); 
    		 }     		 
		} 
} 