package pointpointy;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;






import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
//import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem; 
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class SMain extends JFrame 
{        
	private static final long serialVersionUID = -4050573484674564828L;
	 
	 public static Random RNG = new Random();
	 boolean isRunning = true; 
     int fps = 30; 
     int windowWidth = 1860; 
     int windowHeight = 960; 
     
     BufferedImage backBuffer; 
     Insets insets; 
     InputHandler input; 
     
     SoundManager music = new SoundManager();

	 FMapPanel gmap;
	 
	 Sprite map1;
	 
	 MapPanel VictoryScreen;
	 
	 MapPanel unitIcoB;
	 MapPanel unitStatsB;
	 
	 JMenuBar troopMenu;
	 JMenu menu, submenu;
	 JMenuItem menuItem;
	 JRadioButtonMenuItem rbMenuItem;
	 JCheckBoxMenuItem cbMenuItem;
	 
	 JLabel label = new JLabel("Text");
	 Sprite victory = this.getSprite("VictoryScreen");
	 
	 Sprite select = this.getSprite("mapicons/select");
	 Sprite go = this.getSprite("mapicons/goto");
	 Sprite explode_s = this.getSprite("explode_1");
	 Sprite explode_m = this.getSprite("explode_2");
	 
	 Sprite pallet = this.getSprite("pallet");
	 
	 int money = 100;

	 private ArrayList<Entity> dead = new ArrayList<Entity>();
     
     int x = 0;
	public int curXM = 0; 
	public int curYM = 0;
	World world;
	private int beginX = 0;
	private int beginY = 0;
	private BufferedImage backBuffer2;
	
	ArrayList<Particle> particles;
	private Sprite ufo = this.getSprite("UFO");
	private boolean shouldDraw = true;
	
	private long frames = 0L;
	long time = 0;

	private ArrayList<Particle> removeParticles = new ArrayList<Particle>();

     public SMain()
     {
    	
     }
     /**
      * 
      * @return Time elapsed in milliseconds
      */
     public double getTime()
     {
    	time += (1000 / fps);
    	//this.notifyConsole(time+"ms");
    	return time;
     }
     
     /** 
      * This method starts the game and runs it in a loop 
      */ 
     public void run() 
     { 
        //mainScreen();    
    	initialize(); 
             
             while(isRunning) 
             { 
                     long time = System.currentTimeMillis(); 
                     
                     if(shouldDraw)
                     {
                    	 update(); 
                    	 draw(); 
                    	 frames++;
                    	 fps = 30;
                     }
                    
                     else
                     {
                    	 Graphics bbg = backBuffer.getGraphics();
                    	 backBuffer.getGraphics().fillRect(0, 0, windowWidth, windowHeight);
                    	 bbg.drawImage(victory.getImage(), 0, 0, this);
                    	 
                    	 this.getGraphics().drawImage(backBuffer,  insets.left, insets.top, this);
                     }
                     
                     //  delay for each frame  -   time it took for one frame 
                     //time = (1000 / fps) - (System.currentTimeMillis() - time); 
                     time = (1000/fps);
                     
                     if (time > 0) 
                     { 
                             try 
                             { 
                                     Thread.sleep(time); 
                             } 
                             catch(Exception e){} 
                     } 
             } 
             
             setVisible(false); 
     } 
     
     /** 
      * This method will set up everything need for the game to run 
      */ 
     void initialize() 
     { 
    	 shouldDraw = true;
    	 setTitle("Pointpointy-Device"); 
    	 setSize(windowWidth, windowHeight); 
    	 setResizable(false); 
    	 setDefaultCloseOperation(EXIT_ON_CLOSE); 
    	 setVisible(true); 
    	 
    	 //initMenus();

    	 insets = getInsets(); 
    	 setSize(insets.left + windowWidth + insets.right, 
    			 insets.top + windowHeight + insets.bottom); 
    	
    	 backBuffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB); 
    	 backBuffer2 = new BufferedImage(300, windowHeight, BufferedImage.TYPE_INT_RGB);
    	 
    	 gmap = new FMapPanel(this);
    	 gmap.setBounds(beginX, beginY, this.windowWidth - 300, this.windowHeight);
    	 gmap.setTextureImage(getSprite("Map1").getImage());
    	 gmap.setVisible(true);
    	 this.add(gmap);
    	 
    	 /*this.unitIcoB = new MapPanel(this, Color.RED, Color.RED);
    	 unitIcoB.setBounds(this.windowWidth-30, beginY, this.windowWidth, 250);
    	 unitIcoB.setTextureImage(getSprite("RPOL").getImage());
    	 unitIcoB.setVisible(true);
    	 this.add(unitIcoB);
    		*/	 
    	 /*this.unitStatsB = new MapPanel(this, Color.GRAY, Color.GRAY);
    	 unitStatsB.setBounds(this.windowWidth-30, beginY, this.windowWidth, 250);
    	 unitStatsB.setTextureImage(getSprite("SPOL").getImage());
    	 unitStatsB.setVisible(true);
    	 this.add(unitStatsB);
    	 */
    	 input = new InputHandler(this);
    	 
    	 Sprite mapSprite = getSprite("Map1");
    	 world = new World(this, mapSprite, mapSprite.getIconWidth(), mapSprite.getIconHeight(), Nations.Tunzen);
    	 this.draw(mapSprite, 0, 0);
    	 
    	 initEntities();
     } 
     
     
     private void initEntities() {
    	   // world.spawnUnit(Units.Tr_TK_C1999T, 800, 600, true);
    	 
    	 world.spawnUnit(Units.Tz_TK_C1999, 100, 100, true);
    	 world.spawnUnit(Units.Tz_IFV_Pointus, 120, 100, true);
    	 world.spawnUnit(Units.Tz_IFV_Pointus, 140, 100, true);
    	 world.spawnUnit(Units.Tz_Toastzan, 320, 300, true);
    	 world.spawnUnit(Units.Tz_Toastzan, Units.Tz_IFV_Pointzt, 160, 100, true);
    	 world.spawnUnit(Units.Tz_Sgusz_Sg, Units.Tz_IFV_Pointzt, 260, 100, true);
    	 world.spawnUnit(Units.Tz_RPOL, Units.Tz_APC_Sgoz, 210, 100, true);
    	 world.spawnUnit(Units.Tz_SGUNZ, Units.Tz_IFV_Pointzt, 220, 100, true);
    	 world.spawnUnit(Units.Tz_SGUNZ, Units.Tz_IFV_Pointzt, 230, 100, true);
    	 
    	 //world.spawnUnit(Units.Tz_SPOL, 320, 250, true);
    	// world.spawnUnit(Units.Tz_SPOL, 430, 290, true);
    	// world.spawnUnit(Units.Tz_Sgusz, 140, 180, true);
    	// world.spawnUnit(Units.Tz_Sgusz, 300, 380, true);
    	// world.spawnUnit(Units.Tz_Sgusz, 310, 380, true);
    	// world.spawnUnit(Units.Tz_SPOL, 320, 380, true);
    	 
    	// world.spawnUnit(Units.Tz_RPOL, Units.Tz_APC_Sgoz, 350, 580, true);
    	// world.spawnUnit(Units.Tz_TK_C1999M, 300, 680, true);
    	// world.spawnUnit(Units.Tz_TK_C1999M, 320, 680, true);
    	// world.spawnUnit(Units.Tz_TK_C1999M, 340, 680, true);
        // world.spawnUnit(Units.Tz_TK_C1999M, 360, 680, true);
    	 //world.spawnUnit(Units.Tz_SPG_Pillowius, 300, 680, true);
    	 //world.spawnUnit(Units.Tz_SGUNZ, Units.Tz_APC_Sgoz, 400, 580, true);

    	 //world.spawnUnit(Units.Tz_SGUNZ, 500, 580, true);
    	 //world.spawnUnit(Units.Tz_SGUNZ, 550, 580, true);
    	 //world.spawnUnit(Units.Tz_SGUNZ, 600, 580, true);
    	 //world.spawnUnit(Units.Tz_SGUNZ, 650, 580, true);
    		
    	 //world.spawnUnit(Units.Tr_SPOL, 340, 100, true);
    	// world.spawnUnit(Units.Pw_Pwees, 900, 100, true);
    	// world.spawnUnit(Units.Pw_Pwees, 920, 100, true);
    	 world.spawnUnit(Units.Pw_PELITE, 300, 500, true);
    	 world.spawnUnit(Units.Pw_PELITE, 320, 500, true);
    	 world.spawnUnit(Units.Pw_PELITE, 340, 500, true);
    	 world.spawnUnit(Units.Pw_PELITE, 360, 500, true);
    	 world.spawnUnit(Units.Pw_PELITE, 380, 500, true);
    	 world.spawnUnit(Units.Pw_PELITE, 420, 500, true);
    	 world.spawnUnit(Units.Pw_Mech_TK, 440, 500, true);
    	 world.spawnUnit(Units.Pw_Hover_TK, 460, 500, true);
    	 world.spawnUnit(Units.Pw_Hover_TK, 480, 500, true);
    	 
    	// world.spawnUnit(Units.Pw_PELITE, Units.Pw_Hover_TK, 350, 100, true);
    	
    	// world.spawnUnit(Units.Pw_Hover_TK, 320, 100, true);
    	 //world.spawnUnit(Units.Pw_Hover_TK, 340, 100, true);
    	// world.spawnUnit(Units.Pw_Hover_TK, 360, 100, true);
    	 //world.spawnUnit(Units.Pu_Windpus, 400, 100, true);
    	//world.spawnUnit(Units.Pu_Windpus, 450, 100, true);
    	// world.spawnUnit(Units.Pu_Sturmpus, 500, 100, true);
    	// world.spawnUnit(Units.Pu_Sturmpus, 550, 100, true);
    	// world.spawnUnit(Units.Pu_Sturmpus, 600, 100, true);
    	// world.spawnUnit(Units.Pu_Sturmpus, 650, 100, true);
    	// world.spawnUnit(Units.Pu_Sturmpus, 700, 100, true);
    	 
    	 
    	//world.spawnUnit(Units.Pu_Pulupus, 300, 100, true);
    	// world.spawnUnit(Units.Pu_Pulupus, 360, 100, true);
    	// world.spawnUnit(Units.Pu_RPOL, 300, 100, true);
    	// world.spawnUnit(Units.Pu_SPOL, 360, 100, true);
	}
     
	private void initMenus() {
    	 troopMenu = new JMenuBar();
    	 menu = new JMenu("A Menu");
    	 menu.setMnemonic(KeyEvent.VK_A);
    	 menu.getAccessibleContext().setAccessibleDescription(
    	         "The only menu in this program that has menu items");
    	 troopMenu.add(menu);

    	 //a group of JMenuItems
    	 menuItem = new JMenuItem("A text-only menu item",
    	                          KeyEvent.VK_T);
    	
    	 menuItem.getAccessibleContext().setAccessibleDescription(
    	         "This doesn't really do anything");
    	 menu.add(menuItem);
    	 troopMenu.add(menu);
    	 setJMenuBar(troopMenu);
	}
	
	/** 
      * This method will check for input, move things 
      * around and check for win conditions, etc 
      */ 
     void update() 
     {   		 
    		 
    	 input.handleKeys();
    	 
    	 if (this.world.currSelection() != null)
    	 {
    		 
    	 }
    	 if(!dead.isEmpty())
    	 {
             for(Entity e : dead)
             {
            	 world.entitiesList().remove(e);
             }
    	 }
    	 if(!removeParticles.isEmpty())
    	 {
             for(Particle p: removeParticles)
             {
            	 world.particles.remove(p);
             }
    	 }
             world.update();
     } 
     
     /** 
      * This method will draw everything 
      */ 
     void draw() 
     {               
             Graphics g = getGraphics(); 
             Graphics bbg = backBuffer.getGraphics(); 
             
      
             
            // bbg.setColor(Color.BLACK); 
            // bbg.drawOval(x, 10, 20, 20); 
             bbg.drawImage(gmap.getTexture(), beginX, beginY, this);
             
             bbg.drawImage(ufo.getImage(), world.UFO_posx-64, world.UFO_posy-64, this);
             bbg.drawImage(go.getImage(), world.UFO_posx, world.UFO_posy, this);
            
             bbg.setColor(Color.WHITE);

             drawEntities();

             renderParticles(bbg);

             drawHUD(bbg);
             bbg.setColor(Color.WHITE);

             //gmap.repaint();
             //bbg.drawImage(backBuffer2, this.windowWidth-300, 0, this);
             g.drawImage(backBuffer, insets.left, insets.top, this); 
             
     }
     
     private void drawEntities() {
    	 Graphics bbg = backBuffer.getGraphics();
    	 for(Entity e : world.entitiesList())
    	 {
    		 if(true)
    		 {
    			 if(!((EntityUnit)e).isVehicle && ((EntityUnit)e).riding() == null)
    			 {
    				 for(int i = 0; i< ((EntityUnit)e).unitSize(); i++)
    					 bbg.drawImage(((EntityUnit)e).mapICO().getImage(), (int)e.x()+2*i, (int)e.y()+2*(i/2), this.gmap);
    			 }
    			 else if(((EntityUnit)e).isVehicle)
    			 {
    				 bbg.drawImage(((EntityUnit)e).mapICO().getImage(), (int)e.x(), (int)e.y()-16, this.gmap);
    			 }
    			 if(world.currSelection()!= null && world.currSelection().equals(e))
    			 {
    				 if(((EntityUnit)world.currSelection()).hasDest() && ((EntityUnit)e).isPlayerUnit)
    				 {
    					 bbg.drawImage(go.getImage(), (int)((EntityUnit)e).dest(0), ((EntityUnit)e).dest(1), this.gmap);
    				 }
    				 
    				 if(((EntityUnit)world.currSelection()).target() != null && world.currSelection().isPlayerUnit)
    				 {
    					 EntityUnit e1 = (EntityUnit)world.currSelection();
    					 EntityUnit t1 = e1.target();
    					 double dist = world.calcPos(e1, t1);
    					 Color c;
    					 if(dist <= e1.getWeap(1).range)
    					 {
    						 c = Color.GREEN;
    					 }
    					 else if(dist <= e1.getWeap(2).range)
    					 {
    						 c = Color.YELLOW;
    					 }
    					 else if(dist <= e1.getWeap(3).range)
    					 {
    						 c = Color.RED;
    					 }
    					 else
    					 {
    						 c = Color.BLUE;
    					 }
    					 bbg.setColor(c);
    					 bbg.drawLine((int)e1.x(), (int)e1.y(), (int)t1.x(), (int)t1.y());

    					 bbg.drawString(""+((int)world.calcPos(e1, t1))*3+"m", (int)world.avg2(e1.x(), t1.x()), (int)world.avg2(e1.y(), t1.y()));
    				 }
    				 bbg.setColor(Color.WHITE);
    				 bbg.drawImage(select.getImage(), (int)e.x()-15, (int)e.y()-20, this.gmap);
    				 if(((EntityUnit)e).riding() != null)
        			 {
        				 bbg.drawString("(1) "+((EntityUnit)e).identifier+" "+((EntityUnit)e).unitSize(), (int)e.x()-15, (int)e.y()+50);
        				 if(((EntityUnit)e).isPlayerUnit)
        					 bbg.drawString("[SPACEBAR]: Exit", (int)e.x()-15, (int)e.y()+75);
        			 }
        			 else
        			 {
        				 bbg.drawString(((EntityUnit)e).identifier+" "+((EntityUnit)e).unitSize(), (int)e.x()-15, (int)e.y()+35);
        			 }
    			 }
    		 }
    	 }
     }

	private void drawHUD(Graphics bbg) {
		Graphics g = backBuffer2.getGraphics();
		//bbg.drawImage(unitIcoB.getTexture(), this.unitIcoB.getX(), this.unitIcoB.getY(), this);
		//bbg.drawImage(unitStatsB.getTexture(), this.unitStatsB.getX(), this.unitStatsB.getY(), this);
		bbg.setColor(Color.WHITE);
		bbg.fillRect(this.windowWidth-300, 0, 300, this.windowHeight);
		drawHUDText(bbg);
     }
    
     
	private void drawHUDText(Graphics bbg) {
		if(world.currSelection()!=null)
		{
			Sprite sp = world.currSelection().largeICO();
			if(!world.currSelection().isVehicle)
			{
				for(int i = 0; i< world.currSelection().unitSize(); i++)
				{
						sp = world.currSelection().largeICO();
						
						if(i < 4)
						{
							bbg.drawImage(sp.getImage(), (int)(this.windowWidth-280+2*i*sp.getWidth()/5), (int)(0), this);
					
						}
						
						if(i >= 4 && i<8)
						{
							bbg.drawImage(sp.getImage(), (int)(this.windowWidth-290+2*(i-4)*sp.getWidth()/5), (int)(40), this);
						}
						
						if(i >= 8)
						{
							bbg.drawImage(sp.getImage(), (int)(this.windowWidth-280+2*(i-8)*sp.getWidth()/5), (int)(80), this);
						}
				}
				bbg.setColor(Color.BLACK);
				bbg.drawString(world.currSelection().identifier, this.windowWidth-290, (int) (this.windowHeight+sp.getHeight()+50));
				bbg.drawImage(label.createImage(label.WIDTH, label.HEIGHT),  this.windowWidth-290, (int) (this.windowHeight+sp.getHeight()+50), this);
				bbg.drawString(world.currSelection().commander, this.windowWidth-290, (int) (this.windowHeight+sp.getHeight()+100));		
			}

			else if(world.currSelection().isVehicle)
			{
				bbg.drawImage(sp.getImage(), (int)(this.windowWidth-290), (int)(-100), this);

				bbg.setColor(Color.BLACK);
				bbg.drawString(world.currSelection().identifier, this.windowWidth-290, (int) (this.windowHeight+sp.getHeight()+50));
				bbg.drawString(world.currSelection().commander, this.windowWidth-290, (int) (this.windowHeight+sp.getHeight()+100));		
				bbg.drawImage(label.createImage(label.WIDTH, label.HEIGHT),  this.windowWidth-290, (int) (this.windowHeight+sp.getHeight()+50), this);
			}    	
			Sprite sm = getSprite("/Armament/UNARMED");
			Sprite sz = getSprite("/Armament/UNARMED");
			Sprite sw = getSprite("/Armament/UNARMED");
			Sprite rico = getSprite("");
			if(world.currSelection().getWeap(1)!= null && world.currSelection().getWeap(1) != Armament.UNARMED)
			{
				sm.setTexture("Armament/"+world.currSelection().getWeap(1).image);
				if(world.currSelection().currWeap()!= null && world.currSelection().getWeap(1) == world.currSelection().currWeap())
				{
					bbg.drawRect(this.windowWidth-290, 300, (int)sm.getWidth(), (int)sm.getHeight());
					rico = this.determineReloadSprite(world.currSelection());
					bbg.drawImage(rico.getImage(), (int)(this.windowWidth-280+sm.getWidth()), (int)(300), this);
				}
			}
			if(world.currSelection().getWeap(2)!= null && world.currSelection().getWeap(2) != Armament.UNARMED)
			{
				
				sz.setTexture("Armament/"+world.currSelection().getWeap(2).image);
				if(world.currSelection().currWeap()!= null && world.currSelection().getWeap(2) == world.currSelection().currWeap())
				{
					bbg.drawRect(this.windowWidth-290, 300+(int)sm.getHeight()+15, (int)sz.getWidth(), (int)sz.getHeight());
					rico = this.determineReloadSprite(world.currSelection());
					bbg.drawImage(rico.getImage(), (int)(this.windowWidth-280+sm.getWidth()), (int)(300+(int)sm.getHeight())+15, this);
				}
			}
			if(world.currSelection().getWeap(3)!= null && world.currSelection().getWeap(3) != Armament.UNARMED)
			{
				
				sw.setTexture("Armament/"+world.currSelection().getWeap(3).image);
				if(world.currSelection().currWeap()!= null && world.currSelection().getWeap(3) == world.currSelection().currWeap())
				{
					bbg.drawRect(this.windowWidth-290, 300+(int)sm.getHeight()+(int)sz.getHeight()+30, (int)sw.getWidth(), (int)sw.getHeight());
					rico = this.determineReloadSprite(world.currSelection());
					bbg.drawImage(rico.getImage(), (int)(this.windowWidth-280+sm.getWidth()), (int)(300+(int)sm.getHeight()+(int)sz.getHeight())+30, this);
				}
			}
			bbg.drawImage(sm.getImage(), (int)(this.windowWidth-290), (int)(300), this);
			bbg.drawImage(sz.getImage(), (int)(this.windowWidth-290), (int)(300+(int)sm.getHeight())+15, this);
			bbg.drawImage(sw.getImage(), (int)(this.windowWidth-290), (int)(300+(int)sm.getHeight()+(int)sz.getHeight())+30, this);
		}
		
		//bbg.drawString(world.currSelection().identifier, this.windowWidth-290, (int) (this.windowHeight+sp.getHeight()+50));
		//bbg.drawString(world.currSelection().commander, this.windowWidth-290, (int) (this.windowHeight+sp.getHeight()+100));		
	}

	private Sprite determineReloadSprite(EntityUnit e) {
		Sprite sm = getSprite("/Armament/UNARMED");
		if(e.fireCounter() > 60000/e.currWeap().rof -((60000/e.currWeap().rof)/7))
		{
			sm.setTexture("mapicons/reload1");
		}
		else if(e.fireCounter() > 60000/e.currWeap().rof -((60000/e.currWeap().rof)/7*2))
		{
			sm.setTexture("mapicons/reload2");
		}
		else if(e.fireCounter() > 60000/e.currWeap().rof -((60000/e.currWeap().rof)/7*3))
		{
			sm.setTexture("mapicons/reload3");
		}

		else if(e.fireCounter() > 60000/e.currWeap().rof -((60000/e.currWeap().rof)/7*4))
		{
			sm.setTexture("mapicons/reload4");
		}

		else if(e.fireCounter() > 60000/e.currWeap().rof -((60000/e.currWeap().rof)/7*5))
		{
			sm.setTexture("mapicons/reload5");
		}

		else if(e.fireCounter() > 60000/e.currWeap().rof -((60000/e.currWeap().rof)/7*6))
		{
			sm.setTexture("mapicons/reload6");
		}

		else 
		{
			sm.setTexture("mapicons/reload7");
		}


		return sm;
	}
	public void renderExplosion(int size, double x, double y)
	{
		Graphics bbg = this.backBuffer.getGraphics();
		if(size == 0)
			bbg.drawImage(this.explode_s.getImage(), (int)x, (int)y, this);
		else
			bbg.drawImage(this.explode_m.getImage(), (int)x, (int)y, this);

		long time = 0;
		time = (1000 / fps) - (System.currentTimeMillis() - time);

		if(time > 150)
		{
			bbg.drawImage(this.explode_s.getImage(), (int)x, (int)y, null);
		}
	}

	@Deprecated
	public void draw(Sprite e, int x, int y)
	{
		e.draw((int)(x-e.getWidth())/2, (int)(y-e.getHeight())/2);  		
	}
	public void playSound(int soundID) {
		music.play(Sounds.findSound(soundID).filePath);
	}

	public void playSound(String path) {
		music.play(path);
	} 
	public static void notifyConsole1(String string) {
		System.out.println(string);
	}
	public void notifyConsole(String string) {
		System.out.println(string);
	}

	public Sprite getSprite(String name)
	{
		return new Sprite(this, name);
	}

	public void removeEntity(Entity deadman) {

		dead.add(deadman);
	}
 
    public void renderParticles(Graphics g){
        for(int i = 0; i <= particles.size() - 1;i++){
        	if(particles.get(i).shouldUpdate())
        	{
        		particles.get(i).render(g);
        		particles.get(i).update();
        	}
        	else
        	{
        		removeParticle(particles.get(i));
        	}
        }
    }

	private void removeParticle(Particle particle) {
		removeParticles.add(particle);
	}

	public void setGameWon() {
		playSound(Sounds.PPT_Soldier.soundID);
		
		showWinGUI();
		shouldDraw = false;
	}

	private void showWinGUI() {
		this.gmap.setVisible(false);
		 VictoryScreen = new MapPanel(this, getSprite("VictoryScreen").getImage());
		 VictoryScreen.setBounds(beginX, beginY, this.windowWidth, this.windowHeight);
		 VictoryScreen.setTextureImage(getSprite("VictoryScreen").getImage());
		 VictoryScreen.setVisible(true);
    	 this.add(VictoryScreen);
    	 
    	 this.shouldDraw = false;
    	 backBuffer.getGraphics().fillRect(0, 0, windowWidth, windowHeight);
    	 backBuffer.getGraphics().drawImage(victory.getImage(), insets.left, insets.top, this);
    	 this.getGraphics().drawImage(backBuffer,  insets.left, insets.top, this);
	}	
} 
