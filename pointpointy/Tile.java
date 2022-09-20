package pointpointy;
/**
 * NO longeR IN uSE
 * @Deprecated
 *
 */
public class Tile {
	final World world;
	final int x;
	final int y;
	private Entity occupier = null;
	
	/**reduction of visibility by tile, 1 is none, 0 is completely invisible even from adjacent tiles*/
	final float camo; 
	/**movement penalty by moving through tile*/
	final float resistance; 
	final int reduction;
	
	
	protected Sprite sprite;
	
	
	public Tile(World world, int x, int y, float camo, float resistance, int reduction)
	{
		this.world = world;
		this.x = x;
		this.y = y;
		this.camo = camo;
		this.resistance = resistance;
		this.reduction = reduction;
	}
	
	public Tile(World world, int x, int y, Tiles type)
	{
		this.world = world;
		this.x = x;
		this.y = y;		
		this.camo = type.camo;
		this.resistance = type.resistance;
		this.reduction = type.reduction;
	}
	
	public void notifyEntry(Entity e, boolean f)
	{
		if(f)
		{
			occupier = e;
		}
		else
		{
			occupier = null;
		}
	}
	
	public Entity occupier()
	{
		return this.occupier;
	}

	public void draw() {
		
	}
}
