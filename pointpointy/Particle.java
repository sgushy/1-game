package pointpointy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
 
public class Particle {
 
    private double x;
    private double y;
    private double dx;
    private double dy;
    private int size;
    private int life;
    private Color color;
	private boolean shouldUpdate;
	private boolean isBullet = false;
    /**
     * 
     * @param x
     * @param y
     * @param dx
     * @param dy
     * @param size
     * @param life
     * @param c
     */
    public Particle(double x, double y, double dx, double dy, int size, int life, Color c){
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.size = size;
        this.life = life;
        this.color = c;
        shouldUpdate = true;
    }
    
    public Particle(boolean bullet, double x, double y, double dx, double dy, int size, int life, Color c){
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.size = size;
        this.life = 1000;
        this.color = c;
        shouldUpdate = true;
        
        isBullet = bullet;
    }
    public void update(){

    	if(!isBullet)
    	{
    		if(life <= 0)
    			shouldUpdate = false;
    		x += dx;
    		y += dy;
    		if(Math.abs(dx) >= 1)
    		{
    			if(dx>0)
    				dx-=0.1;
    			else
    				dx+=0.1;
    		}
    		if(Math.abs(dy) >= 1)
    		{
    			if(dy>0)
    				dy-=0.1;
    			else
    				dy+=0.1;
    		}
    		if(this.color == Color.ORANGE&&this.life <=30)
    		{
    			if(Math.random() >= 0.5)
    				this.color = Color.GRAY;
    		}
    		life--;
    	}
    	else if(isBullet)
    	{
    		if(life <= 0)
    			shouldUpdate = false;
    		x += dx;
    		y += dy;
    		if(Math.abs(dx) >= 1)
    		{
    			if(dx>0)
    				dx-=0.1;
    			else
    				dx+=0.1;
    		}
    		if(Math.abs(dy) >= 1)
    		{
    			if(dy>0)
    				dy-=0.1;
    			else
    				dy+=0.1;
    		}
    		if(this.color == Color.ORANGE&&this.life <=30)
    		{
    			if(Math.random() >= 0.5)
    				this.color = Color.GRAY;
    		}
    		life--;
    	}
    }
    
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
 
        g2d.setColor(color);
        g2d.fillRect((int)(x-(size/2)), (int)(y-(size/2)), size, size);
 
        g2d.dispose();
    }
	public boolean shouldUpdate() {
		// TODO Auto-generated method stub
		return this.shouldUpdate;
	}
}