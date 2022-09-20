package pointpointy;
import java.awt.Point;
import java.util.ArrayList;

public class Player {
	public final boolean human;
	private World world;
	public final Nations nation;
	private ArrayList<EntityUnit> ownedUnits = new ArrayList<EntityUnit>();
	private ArrayList<EntityUnit> currTargeted = new ArrayList<EntityUnit>();
	private ArrayList<Nations> kameraden = new ArrayList<Nations>();
	private ArrayList<Point> objectives = new ArrayList<Point>();;
	
	public Player(World world, boolean player, Nations nation)
	{
		this.world = world;
		this.human = player;
		this.nation = nation;
	}
	public void update()
	{
		for(int i = ownedUnits.size()-1; i >= 0; i--)
		{
			if(ownedUnits.get(i).unitSize() <=0)
			{
				ownedUnits.remove(i);
			}
		}
		for(int i = currTargeted.size()-1; i >= 0; i--)
		{
			if(currTargeted.get(i).unitSize() <=0)
			{
				currTargeted.remove(i);
			}
		}
		for(Entity e: world.entitiesList())
		{
			EntityUnit f = (EntityUnit)e;
			if(f.nation == this.nation && !ownedUnits.contains(f))
			{
				ownedUnits.add(f);
			}
		}
		if(!this.human)
		{
			this.findAndTarget();
		}
	}
	public void ally(Nations nation)
	{
		this.kameraden.add(nation);
	}
	private void findAndTarget()
	{
		for(EntityUnit p: this.ownedUnits)
		{
			EntityUnit target = this.nextUnTargetedUnit();
			if((p.target()==null || p.target().unitSize() <= 0) && target != null)
			{
				p.setTarget(target, false);
				this.currTargeted.add(target);
			}
			else if(p.target() != null && p.riding() != null && p.world.calcPos(p, p.target())< p.getWeap(1).range/4*3)
			{
				p.dismountVehicle();
			}
			else if(p.target() != null && p.rider() != null && p.world.calcPos(p, p.target())< p.rider().getWeap(1).range/4*3)
			{
				p.dismountVehicle();
			}
			else if(p.target() != null && p.world.calcPos(p, p.target())< p.getWeap(1).range/8)
			{
				p.cancelOrders();
				p.setDestination(p.x(), p.y());
			}
		}
	}

	private EntityUnit nextUnTargetedUnit()
	{
		for(Entity e : world.entitiesList())
		{
			EntityUnit f = (EntityUnit)e;
			if(f.nation != this.nation && f.spotted(this) && !this.kameraden.contains(f.nation) && !this.currTargeted.contains(f))
			{
				return f;
			}
		}
		return null;
	}
	
	public void addObjective(int x, int y)
	{
		this.objectives.add(new Point(x, y));
	}
	
	public void cancelObjectives()
	{
		this.objectives = new ArrayList<Point>();
	}
}
