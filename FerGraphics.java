import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import pointpointy.*;
public class FerGraphics {

	public static void main11(String[] args)
	{
		//addUnitIMG();
		addUnitIco();
	}

	/**
	 * Loads and saves all of the unit images
	 */
	private static void addUnitIMG() {
		Sprite OF = new SMain().getSprite("mapicons/select");
		System.out.print("Flip+Save Images");
		for(Units s : Units.values())
		{
			if(!s.vehicle)
			{
				OF.setTexture(s.nation.abbrev+"/"+s.toString()+"_m1");
				OF.flip();
				saveIMG(imageToBufferedImage(OF.getImage()), "/"+s.nation.abbrev+"/"+s.toString()+"_m1_r");
				OF.setTexture(s.nation.abbrev+"/"+s.toString()+"_fm");
				OF.flip();
				saveIMG(imageToBufferedImage(OF.getImage()), "/"+s.nation.abbrev+"/"+s.toString()+"_fm_r");
				OF.setTexture(s.nation.abbrev+"/"+s.toString()+"_m2");
				OF.flip();
				saveIMG(imageToBufferedImage(OF.getImage()), "/"+s.nation.abbrev+"/"+s.toString()+"_m2_r");
				OF.setTexture(s.nation.abbrev+"/"+s.toString()+"_f1");
				OF.flip();
				saveIMG(imageToBufferedImage(OF.getImage()), "/"+s.nation.abbrev+"/"+s.toString()+"_f1_r");
				OF.setTexture(s.nation.abbrev+"/"+s.toString());
				OF.flip();
				saveIMG(imageToBufferedImage(OF.getImage()), "/"+s.nation.abbrev+"/"+s.toString()+"_r");
			}
			else
			{
				OF.setTexture(s.nation.abbrev+"/"+s.toString()+"_f");
				OF.flip();
				saveIMG(imageToBufferedImage(OF.getImage()), "/"+s.nation.abbrev+"/"+s.toString()+"_f_r");
				OF.setTexture(s.nation.abbrev+"/"+s.toString());
				OF.flip();
				saveIMG(imageToBufferedImage(OF.getImage()), "/"+s.nation.abbrev+"/"+s.toString()+"_r");
			}
		}
	}	
	private static void addUnitIco() {
		Sprite OF = new SMain().getSprite("mapicons/select");
		System.out.print("Flip+Save Images");
		for(Units s : Units.values())
		{
			if(!s.vehicle)
			{
				OF.setTexture(s.nation.abbrev+"/"+s.toString()+"_m1");
				OF.rescaleImg(12, 12);
				saveIMG(imageToBufferedImage(OF.getImage()), "/"+s.nation.abbrev+"/"+s.toString()+"_ico_m1");
				OF.flip();
				saveIMG(imageToBufferedImage(OF.getImage()), "/"+s.nation.abbrev+"/"+s.toString()+"_ico_m1_r");
				
				OF.setTexture(s.nation.abbrev+"/"+s.toString()+"_fm");
				OF.rescaleImg(12, 12);
				saveIMG(imageToBufferedImage(OF.getImage()), "/"+s.nation.abbrev+"/"+s.toString()+"_ico_fm");
				OF.flip();
				saveIMG(imageToBufferedImage(OF.getImage()), "/"+s.nation.abbrev+"/"+s.toString()+"_ico_fm_r");
				
				OF.setTexture(s.nation.abbrev+"/"+s.toString()+"_m2");
				OF.rescaleImg(12, 12);
				saveIMG(imageToBufferedImage(OF.getImage()), "/"+s.nation.abbrev+"/"+s.toString()+"_ico_m2");
				OF.flip();
				saveIMG(imageToBufferedImage(OF.getImage()), "/"+s.nation.abbrev+"/"+s.toString()+"_ico_m2_r");
				
				OF.setTexture(s.nation.abbrev+"/"+s.toString()+"_f1");
				OF.rescaleImg(12, 12);
				saveIMG(imageToBufferedImage(OF.getImage()), "/"+s.nation.abbrev+"/"+s.toString()+"_ico_f1");
				OF.flip();
				saveIMG(imageToBufferedImage(OF.getImage()), "/"+s.nation.abbrev+"/"+s.toString()+"_ico_f1_r");
				
				OF.setTexture(s.nation.abbrev+"/"+s.toString());
				OF.rescaleImg(12, 12);
				saveIMG(imageToBufferedImage(OF.getImage()), "/"+s.nation.abbrev+"/"+s.toString()+"_ico");
				OF.flip();
				saveIMG(imageToBufferedImage(OF.getImage()), "/"+s.nation.abbrev+"/"+s.toString()+"_ico_r");
			}
			else
			{
				OF.setTexture(s.nation.abbrev+"/"+s.toString()+"_f");
				OF.rescaleImg(32, 32);
				saveIMG(imageToBufferedImage(OF.getImage()), "/"+s.nation.abbrev+"/"+s.toString()+"_ico_f");
				OF.flip();
				saveIMG(imageToBufferedImage(OF.getImage()), "/"+s.nation.abbrev+"/"+s.toString()+"_ico_f_r");
				
				OF.setTexture(s.nation.abbrev+"/"+s.toString());
				OF.rescaleImg(32, 32);
				saveIMG(imageToBufferedImage(OF.getImage()), "/"+s.nation.abbrev+"/"+s.toString()+"_ico");
				OF.flip();
				saveIMG(imageToBufferedImage(OF.getImage()), "/"+s.nation.abbrev+"/"+s.toString()+"_ico_r");
			}
		}
	}
	
	private static void saveIMG(BufferedImage b, String name)
	{
		try {
		    // retrieve image
		    BufferedImage bi = b;
		    File outputfile = new File(SMain.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"pointpointy/resources/textures/"+name+".png");
		    ImageIO.write(bi, "png", outputfile);
		    System.out.print("Added" + outputfile.getAbsolutePath());
		} catch (IOException e) {
		  
		}
	}
	private static BufferedImage imageToBufferedImage(Image image) 
	{

		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bufferedImage.createGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();

		return bufferedImage;

	}
}
