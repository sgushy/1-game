package pointpointy;

public abstract class Entity {
final World world;
private double x;
private double y;

private double dx;
private double dy;

/** The sprite that represents this unit */
protected Sprite sprite;
/** The sprite that represents the unit as a unit icon in the GUI */


private Tile occupiedTile = null;
public final double mmovement;

private double curMovement;


	public Entity(Sprite sprite, World world, int x, int y, double movement)
	{
		this.sprite = sprite;
		this.world = world;
		this.x = x;
		this.y = y;
		mmovement = movement;
		curMovement = mmovement;
		//this.occupyTile(x, y);
	}
	
	public void update()
	{
		this.x += dx/20;
		this.y += dy/20;
	}
	/**
	 * 
	 * @return x
	 */
	public double x()
	{
		return x;
	}
	/**
	 * 
	 * @return y
	 */
	public double y()
	{
		return y;
	}
	
	public void playSound(int index)
	{
		world.sMain().playSound(Sounds.findSound(index).name());
	}

	protected void moveInDir(double heading) {
		this.dx = this.mmovement * Math.cos(heading);
		this.dy = this.mmovement * Math.sin(heading);
	}

	public void cancelOrders() {
		this.dx = 0;
		this.dy = 0;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public boolean facesLeft()
	{
		return dx <= 0;
	}
}
