package pointpointy;
import java.awt.Color;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

public class World {

	private MapGenerator mg;
	private ArrayList<Player> players;
	private ArrayList<Entity> entities;
	public ArrayList<Particle> particles;
	//private Tile[][] map;
	private EntityUnit activeSelection;
	public final Random wrng = new Random();
	
	ListIterator itr;
	
	private SMain main;
	private boolean isRandom;
	private int maxSizeX;
	private int maxSizeY;
	public int mapX;
	public int mapY;
	
	private Nations playerNation = null;
	
	public final int UFO_posx;
	public final int UFO_posy;
	
	public final int base_posx;
	public final int base_posy;
	
	private Sprite map;
	
	public World(SMain main, Sprite map, int mapsizex, int mapsizey, Nations playerNation)
	{
		entities = new ArrayList<Entity>();
		particles = new ArrayList<Particle>();
		players = new ArrayList<Player>();
		main.particles = this.particles;
		this.main = main;
		this.map = map;
		this.maxSizeX = mapsizex;
		this.maxSizeY = mapsizey;
		//map = new Tile[maxSizeX][maxSizeY];
		this.isRandom = isRandom;		
		itr = entities.listIterator();
		UFO_posx = wrng.nextInt(100)+300;
		UFO_posy = sMain().windowHeight-100;
		
		this.initPlayers(playerNation);

		base_posx = 50;
		base_posy = 50;
		
		this.mg = new MapGenerator(this.maxSizeX, this.maxSizeY);
		this.map.setImage(mg.generate(944));
	}
	
	 private void initPlayers(Nations playerNation)
     {
		 this.playerNation = playerNation;
    	 for(Nations n : Nations.values())
    	 {
    		 if(n != playerNation)
    		this.players.add(new Player(this, false, n));
    		 else
    			 this.players.add(new Player(this, true, n));
    	 }
     }
	 
	 public Nations playerNation()
	 {
		 return this.playerNation;
	 }
	 
	 public Player getPlayer(Nations nation)
	 {
		 for(Player p : this.players)
		 {
			 if(p.nation == nation)
			 {
				 return p;
			 }
    	 }
		 return null;
	 }
	
	public Entity loc(double x, double y)
	{
		for(Entity e : entities)
		{
			if(Math.abs(e.x()-x) <= 10 && Math.abs(e.y() -y) <=10)
				if(((EntityUnit)e).riding() == null)
					return e;
		}
		return null;
	}
	public Entity loc(Entity ez, double x, double y)
	{
		for(Entity e : entities)
		{
			if(Math.abs(e.x()-x) <= 10 && Math.abs(e.y() -y) <=10)
				if(((EntityUnit)e).riding() == null&&!((EntityUnit)e).equals(ez))
					return e;
		}
		return null;
	}
	/*public Entity createEntity(int x, int y)
	{
		Entity e = new Entity(this, x,y);
		return e;
	}*/
	
	/**
	 * @return next player controlled unit that needs orders
	 */
	public EntityUnit nextPlayerUnit(Nations nation)
	{
		itr = entities.listIterator();
		while(itr.hasNext())
		{
			EntityUnit imm = (EntityUnit)itr.next();
			if((imm.isPlayerUnit||imm.nation!=nation) && !imm.selected())
			{
				return imm;
			}
		}
		return null;
	}
	
	public EntityUnit locNear(double x, double y, double range)
	{
		itr = entities.listIterator();
		while(itr.hasNext())
		{
			EntityUnit imm = (EntityUnit)itr.next();
			if(this.calcPos(imm, x, y) <= range)
			{
				return imm;
			}
		}
		return null;
	}
	
	public EntityUnit locNear(Entity e, double x, double y, double range)
	{
		itr = entities.listIterator();
		while(itr.hasNext())
		{
			EntityUnit imm = (EntityUnit)itr.next();
			if(this.calcPos(imm, x, y) <= range && !((EntityUnit)e).equals(imm))
			{
				return imm;
			}
		}
		return null;
	}
	
	/**
	 * @return next player controlled unit that needs orders
	 */
	public EntityUnit prevPlayerUnit()
	{
		itr = entities.listIterator();
		while(itr.hasPrevious())
		{
			EntityUnit imm = (EntityUnit)itr.previous();
			if(imm.isPlayerUnit && !imm.selected())
			{
				return imm;
			}
		}
		return null;
	}
	
	public void selectUnit(EntityUnit e)
	{
		if(e!=null)
		{
			if (activeSelection == null)
			{
				if(e.isPlayerUnit)
				{
					e.playSound(e.nation.orderSound().soundID);
				}
			}
			else if(activeSelection != null)
			{
				if(activeSelection.equals(e))
				{

				}
				
				else
				{
					if(e.isPlayerUnit)
					{
						e.playSound(e.nation.orderSound().soundID);
					}
					activeSelection.selectUnit(false);
				}
			}
			activeSelection = e;
			activeSelection.selectUnit(true);
			sMain().notifyConsole("Selected unit "+activeSelection.identifier+" "+activeSelection.commander+ "x: "+this.activeSelection.x()+" y: "+this.activeSelection.y());
		}
	}
	
	public EntityUnit currSelection()
	{
		return activeSelection;
	}

	/*protected void spawnUnit(Tile tTile, Sprite sprite, Weapon[] weap)
	{
		EntityUnit entity = new EntityUnit(main, sprite., tTile.xpos, tTile.ypos);
	}*/
	/**Method for spawning units @deprecated*/
	/*protected void spawnUnit(Tile tTile, String texture, Weapon[] weap)
	{
		EntityUnit entity = new EntityUnit(main, texture, tTile.xpos, tTile.ypos);
		main.entities.add(entity);
	}
	/**When player uses mouse to select the unit
	protected void selectUnit(EntityUnit entity, int playerID, boolean isSelected)
	{
		if(entity.team == playerID)
		{
			entity.setSelected(isSelected, playerID);
		}
	}*/
		
	protected void spawnUnit(EntityUnit unit)
	{
		if(unit != null)
		entities.add(unit);
		System.out.println("Unit "+unit.identifier+"-"+ unit.commander+" spawned at ("+unit.x()+", "+unit.y()+")");
	}
	
	protected void spawnUnit(Units type, int x, int y, boolean team)
	{
		EntityUnit e = new EntityUnit(null, this, x, y, team, type);
		this.spawnUnit(e);
	}
	
	protected void spawnUnit(Units type, Units vhcl, int x, int y, boolean team)
	{
		EntityUnit e = new EntityUnit(null, this, x, y, team, type);
		this.spawnUnit(e);
		EntityUnit f = new EntityUnit(null, this, x, y, team, vhcl);
		this.spawnUnit(f);
		e.mountVehicle(f);
	}
	
	protected void spawnUnits(EntityUnit[] units)
	{
		for (EntityUnit u : units)
		{
			spawnUnit(u);
		}
	}
	
	/**Returns the map, in both tile list and array forms
	public Tile[][] map()
	{
		return this.map;
	}*/

	/**Called upon every tick to load map*/
	@Deprecated
	protected void drawMap()
	{
		map.draw(sMain().curXM, sMain().curYM);
	}
	
	
	public void kill(Entity deadman)
	{
		main.removeEntity(deadman);
	}
	public void update() {

		for(Player p : players)
		{
			p.update();
		}
		for(Entity e : entities)
		{
			e.update();	
		}
		//spawnRnd();
	}
	
	private void spawnRnd() {
			if(wrng.nextInt(1000) ==4)
			{
				EntityUnit d8 = new EntityUnit(sMain().getSprite(Units.Pw_Pwees.toString()), this, this.UFO_posx, this.UFO_posy, false, Units.Pw_Pwees);
				spawnUnit(d8);
			}
			else if(wrng.nextInt(1200) ==4)
			{
				EntityUnit d8 = new EntityUnit(sMain().getSprite(Units.Pw_PELITE.toString()), this, this.UFO_posx, this.UFO_posy, false, Units.Pw_PELITE);
				spawnUnit(d8);
			}
			else if(wrng.nextInt(2000) == 4)
			{
				EntityUnit d8 = new EntityUnit(sMain().getSprite(Units.Pw_PRAIDER.toString()), this, this.UFO_posx, this.UFO_posy, false, Units.Pw_PRAIDER);
				spawnUnit(d8);
			}
			else if(wrng.nextInt(20000) < 4)
			{
				EntityUnit d8 = new EntityUnit(sMain().getSprite(Units.Pw_Mech_TK.toString()), this, this.UFO_posx, this.UFO_posy, false, Units.Pw_Mech_TK);
				spawnUnit(d8);
			}
	}

	public void draw()
	{
		this.drawMap();
		for(Entity e : entities)
		{
			sMain().draw(e.sprite, (int)e.x(), (int)e.y());
		}
	}
	
	protected ArrayList<Entity> entitiesList()
	{
		return this.entities;
	}
	
	public SMain sMain()
	{
		return this.main;
	}
	
	public double calcPos(double x1, double y1, double x, double y)
	{
		return Math.sqrt(Math.abs((x1-x)*(x1-x)+(y1-y)*(y1-y)));
	}
	
	public double calcPos(Entity e1, double x, double y)
	{
		return calcPos(e1.x(), e1.y(), x, y);
	}
	
	public double calcPos(Entity e1, Entity e2)
	{
		return calcPos(e1, e2.x(), e2.y());
	}
	
	public double avg2(double d, double e)
	{
		return (d+e)/2;
	}
	
	public Particle addParticle(int x, int y, int vel, double heading, int size, int lifetime, Color color)
	{
		int dx = (int)(Math.cos(heading)*vel);
		int dy = (int)(Math.sin(heading)*vel);
		Particle p = new Particle(x, y, dx, dy, size, lifetime, color);
		this.particles.add(p);
		return p;
	}
	
	public void addParticle(Particle p)
	{
		this.particles.add(p);
	}
	
	public void explode(int size, int x, int y)
	{
		for(int i = 0; i< 20*size; i++)
		{
			double speed = size*Math.random()*0.25;
			
			double dx = (speed*Math.cos(Math.random()*Math.PI*2));
			double dy = (speed*Math.sin(Math.random()*Math.PI*2));
			Particle pm = new Particle(x, y, dx, dy, 2, 60, Color.ORANGE);
			addParticle(pm);
		}
		sMain().playSound(Sounds.Detonate.filePath);
	}
	public void explodeNoSound(int size, int x, int y)
	{
		for(int i = 0; i< 20*size; i++)
		{
			double speed = size*Math.random()*0.25;
			
			double dx = (speed*Math.cos(Math.random()*Math.PI*2));
			double dy = (speed*Math.sin(Math.random()*Math.PI*2));
			Particle pm = new Particle(x, y, dx, dy, 2, 60, Color.ORANGE);
			addParticle(pm);
		}
		//sMain().playSound(Sounds.Detonate.filePath);
	}
	public void shootBullet(int velocity, int startx, int starty, int finx, int finy)
	{
		double heading = Math.atan2(finy-starty, finx-startx);
		double dx = velocity*Math.cos(heading);
		double dy = velocity*Math.sin(heading);
		
		double dist = calcPos(startx, starty, finx, finy);
		double time = dist/velocity;
		
		Particle p = new Particle(startx, starty, dx, dy, 2, (int)time, Color.YELLOW);
		addParticle(p);
	}
	public void shootBullet(int velocity, EntityUnit shooter, EntityUnit target)
	{
		this.shootBullet(velocity, (int)shooter.x(), (int)shooter.y(), (int)target.x(), (int)target.y());
	}
}
