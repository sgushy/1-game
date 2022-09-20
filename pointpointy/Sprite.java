package pointpointy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Implementation of sprite that uses an OpenGL quad and a texture
 * to render a given image to the screen.
 */
public class Sprite extends ImageIcon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 635985410763007182L;
	/**
	 * 
	 */
	SMain s;
	private int faces = 1;
	//BufferedImage img = null;
    
	public Sprite(SMain s, String name)
	{
		super(SMain.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"pointpointy/resources/textures/"+name+".png");
		this.s = s;
		//File file = new File(SMain.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"/pointpointy/resources/audio/"+name+".wav");
	}
	
	

	@Deprecated
	public void draw(int x, int y) {
		/*try {
	        img = ImageIO.read(new File("BeachRoad.png"));
	    } catch (IOException e) {
	    }
	    Graphics g = img.getGraphics();*/
	 }
	public double getWidth()
	{
		return this.getIconWidth();
	}
	
	public double getHeight() {
		return this.getIconHeight();
	}
	public void setTexture(String string)
	{
		this.setImage(SMain.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"pointpointy/resources/textures/"+string+".png");
	}

	public void setImage(String string) {
		try{
			BufferedImage image = new BufferedImage((int)Math.ceil(this.getWidth()), (int)Math.ceil(this.getHeight()), BufferedImage.TYPE_INT_ARGB);
		
	    String loc = string;  
		try {
	    	  File file = new File(string);
	    	  loc = file.toURI().toString();
	    	  image = imageToBufferedImage(this.makeColorTransparent(imageToBufferedImage(ImageIO.read(file)), Color.WHITE));
			this.setImage(image);
		} catch (IOException e) {
			SMain.notifyConsole1(loc);
			e.printStackTrace();
		}
		}
		catch(IllegalArgumentException e)
		{
			System.out.println(string);
			e.printStackTrace();
		}
	}
	
	public void rescaleImg(int w, int h)
	{
		BufferedImage resizedImage = new BufferedImage(w, h, Image.SCALE_FAST);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(this.getImage(), 0, 0, w, h, null);
		g.dispose();
		this.setImage(resizedImage);
	}
	
	private BufferedImage imageToBufferedImage(Image image) 
	{

		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bufferedImage.createGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();

		return bufferedImage;

	}
	public Image makeColorTransparent(BufferedImage im, final Color color) 
	{
		ImageFilter filter = new RGBImageFilter() {

			// the color we are looking for... Alpha bits are set to opaque
			public int markerRGB = color.getRGB() | 0xFF000000;

			public final int filterRGB(int x, int y, int rgb) {
				if ((rgb | 0xFF000000) == markerRGB) {
					// Mark the alpha bits as zero - transparent
					return 0x00FFFFFF & rgb;
				} else {
					// nothing to do
					return rgb;
				}
			}
		};

		ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
		return Toolkit.getDefaultToolkit().createImage(ip);
	}
	
	public void flip()
	{
		BufferedImage sprite = this.imageToBufferedImage(this.getImage());
		BufferedImage img = new BufferedImage(sprite.getWidth(),sprite.getHeight(),BufferedImage.TYPE_INT_ARGB);
        for(int xx = sprite.getWidth()-1;xx>0;xx--){
            for(int yy = 0;yy < sprite.getHeight();yy++){
                img.setRGB(sprite.getWidth()-xx, yy, sprite.getRGB(xx, yy));
            }
        }
        this.setImage(img);
	}
	@Deprecated //no longer useful
	public boolean facesLeft()
	{
			return faces > 0;
	}
}