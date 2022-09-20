package pointpointy;

public enum Tiles {
	plain(1.0F, 1.0F, 0),
	woods(0.5F, 0.9F, 10),
	urban(0.5F, 0.9F, 20),
	water(1.0F, 0.1F, 0),
	rocks(0.7f, 0.4f, 15),
	road(1.2F, 2.5F, 0)
	;
	
	public float camo;
	public float resistance;
	public int reduction;
	/**
	 * 
	 * @param camo = camo value (1.0 is low, 0 is very high)
	 * @param resistance = how much a unit is slowed (1 = none, 0 = impassable)
	 * @param reduction = damage reduction (higher is better)
	 */
	Tiles(float camo, float resistance, int reduction)
	{
		this.camo = camo;
		this.resistance = resistance;
		this.reduction = reduction;
	}
}
