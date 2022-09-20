package pointpointy;

import java.awt.image.BufferedImage;

public class MapGenerator {
	BufferedImage map;
	
	public MapGenerator(int width, int height)
	{
		map = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}
	
	/**
	 * Turns a 2D array into a map
	 * @param map
	 */
	public BufferedImage generate(int[][] map)
	{
		return this.map;
	}
	/**
	 * random map based on a seed
	 * @param seed
	 */
	public BufferedImage generate(int seed)
	{
		return map;
	}
	
	
}
