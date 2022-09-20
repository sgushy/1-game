package pointpointy;

import java.awt.Image;

//import javax.swing.ImageIcon;

public class EntityUnit extends Entity
{
	public final boolean isPlayerUnit;
	private boolean selected = false;
	private boolean spotted = false;
	
	final float optics;
	final float stealth;
	final String identifier;
	/**
	 * Assault rifle or small arms
	 */
	private Armament weap1 = Armament.UNARMED;
	/**
	 * IMPORTANT: Second weapons slot must always be an AT weapon
	 */
	private Armament weap2 = Armament.UNARMED;
	/**
	 * Support machine gun
	 */
	private Armament weap3 = Armament.UNARMED;
	
	private Armament activeWeap = null;
	private int unitSize = 5;
	private int unitArmor = 0;
	public final boolean isVehicle;
	
	public final Nations nation;
	
	public final boolean isAircraft;
	
	
	/**
	 * x and y destination coordinates
	 */
	private int destx;
	private int desty;
	private double heading;
	private boolean shouldMovetoDest = false;
	private boolean doAttack = false;
	private Entity target = null;
	
	private int fireCooldown;
	private double fireCounter;
	/**
	 * the vehicle this is riding, only applies if this is NOT a vehicle
	 */
	private EntityUnit riding = null;
	public final Units type;
	/**
	 * Defines OF image
	 */
	String mapico = "";
	double health;
	
	/**
	 * only usable if this is a vehicle
	 */
	private EntityUnit rider = null;
	
	
	protected Sprite unitICO;
	/**
	 * Icon on map
	 */
	protected Sprite OF;
	
	private boolean firing = false;
	
	public final String commander;
	
	int ammo1;
	int ammo2;
	int ammo3;
	private boolean hasRider = false;
	private boolean flagged = false;
	/**
	 * Icon of unit on sidebar...
	 */
	public Sprite unitICO2;
	private boolean hasFiredThisTurn = false;
	
	/**
	 * Checks to make sure the soldier hasn't already said ok to an order
	 */
	private boolean affirmCheck = true;
	
	public EntityUnit(Sprite sprite, World world, int x, int y, @Deprecated boolean isPlayerUnit, Units type) {
		super(sprite, world, x, y, type.movement);
		
		this.optics = type.optics;
		this.stealth = type.camo;
		this.identifier = type.name;
		this.setSize(type.unit_size);
		this.unitArmor = type.armor_value;
		this.isVehicle = type.vehicle;
		
		this.type = type;
		this.nation = type.nation;
		this.isPlayerUnit = (this.nation == world.playerNation());
		//this.unitICO = world.sMain().getSprite(type.toString()+"_ico");
		this.unitICO2 = world.sMain().getSprite("/"+this.nation.abbrev+"/"+type.toString());
		
		
		/*if(!this.isVehicle)
		{
			mapico = "/mapicons/"+this.nation.abbrev+"_inf";
		}
		else
		{
			if(type.toString().contains("TK")||type.toString().contains("Tk"))
			{
				mapico = "/mapicons/"+this.nation.abbrev+"_TK";
			}
			else if(type.toString().contains("IFV")||type.toString().contains("APC")||type.toString().contains("SPG"))
			{
				mapico = "/mapicons/"+this.nation.abbrev+"_IFV";
			}
		
			else
			{
				mapico = "";
			}
		}*/
		
		mapico = "/"+this.nation.abbrev+"/"+this.type.toString();

		//OF = world.sMain().getSprite(mapico);
		OF = world.sMain().getSprite(mapico);
		if(!this.isVehicle)
		{
			OF.setImage(unitICO2.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
		}
		else
		{
			OF.setImage(unitICO2.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
		}
		//OF.setImage(getImage().getScaledInstance(arg0, arg1, arg2));
		
		equip(type.stdWeap, 1);
		equip(type.stdAT, 2);
		equip(type.stdSAW, 3);
		
		Names name = this.nation.getNameRand();
		commander = name.toString();
	
		fireCooldown = 60000/type.stdWeap.rof;
		fireCounter = fireCooldown;
		
		this.health = unitSize;
		ammo1 = weap1.ammo;
		ammo2 = weap2.ammo;
		ammo3 = weap3.ammo;
		
		if(this.type.toString().contains("AIR"))
		{
			this.isAircraft = true;
		}
		else
		{
			this.isAircraft = false;
		}
	}

	public EntityUnit(Sprite sprite, World world, int x, int y, boolean isPlayerUnit, Units type, int unitSize) {
		this(sprite, world, x, y, isPlayerUnit, type);
		this.unitSize = unitSize;
	}

	public void update()
	{
		//this.updateIMG();
		if(activeWeap!=null)
		fireCooldown = 60000/this.activeWeap.rof;
		if(target!=null)
		fireCounter -= 1000/world.sMain().fps;
		
		if(fireCounter < 0)
		{
			fireCounter = this.fireCooldown;
		}
		else
		{
			hasFiredThisTurn = true;
		}
		if(doAttack && target != null&&this.selected)
		{
			//world.sMain().notifyConsole(fireCounter+" ");
			//this.attack(target());			
		}
		if(target() != null && target().unitSize() <=0)
		{ 
			target = null;
		}
		
		if(unitSize <= 0)
		{
			this.setDead();
		}
		
		if(this.isVehicle && flagged && rider!= null)
		{
			if(world.calcPos(this, rider) <= 20)
			{
				flagged = false;
				rider.mountVehicle(this);
				this.setRiding(rider);
				world.sMain().notifyConsole(rider.identifier+" riding "+this.identifier);
			}
			
			else if(world.calcPos(this, rider) > 20)
			{
				this.setDestination(rider.x(), rider.y());
			}
		}
		
		if(this.isVehicle && flagged && rider!= null)
		{
			if(world.calcPos(this, rider) > 20)
			{
				this.setDestination(rider.x(), rider.y());
			}
			else if(world.calcPos(this, rider) <= 20)
			{
				rider.mountVehicle(this);
				this.setRiding(rider);
				flagged = false;
				world.sMain().notifyConsole(rider.identifier+" riding "+this.identifier);
			}
		}
		if(!this.isVehicle && this.riding != null)
		{
			this.SyncXY(riding);
		}
		if(this.riding == null)
		{
			this.draw();

			if(!this.isPlayerUnit)
			{
				AIMove();
			}
			else
			{
				playerMove();
			}
			super.update();
		}
	}
	
	public Sprite largeICO()
	{
		String icot = "";
		if(!this.isVehicle)
		{
			if(this.isShooting() && this.moving() && this.fireCounter() < 100&&!this.isVehicle)
			{
				if(world.wrng.nextDouble() < 0.33)
				{
					icot = this.nation.abbrev+"/"+this.type.toString()+"_m"+(world.wrng.nextInt(2)+1);
				}
				else if(world.wrng.nextDouble() < 0.67)
				{
					icot = this.nation.abbrev+"/"+this.type.toString()+"_fm";
				}
				else
				{
					icot = this.nation.abbrev+"/"+this.type.toString()+"_m"+(world.wrng.nextInt(2)+1);
				}
			}
			else if(this.isShooting() && this.fireCounter() < 200&&!this.isVehicle)
			{
				icot = this.nation.abbrev+"/"+this.type.toString()+"_f"+(world.wrng.nextInt(2)+1);
			}
			else if(this.moving()&&!this.isVehicle)
			{
				icot = this.nation.abbrev+"/"+this.type.toString()+"_m"+(world.wrng.nextInt(2)+1);
			}
			else
			{
				icot = this.nation.abbrev+"/"+this.type.toString();
			}
		}
		if(this.isVehicle)
		{
			if(this.isShooting() && this.fireCounter() < 200)
			{
				icot = this.nation.abbrev+"/"+this.type.toString()+"_f";
			}
			else
			{
				icot = this.nation.abbrev+"/"+this.type.toString();
			}
		}
		if(!this.facesLeft())
		{
			icot+="_r";
		}
		unitICO2.setTexture(icot);
		return unitICO2;
	}
	
	public Sprite mapICO()
	{
		String icot = "";
		if(!this.isVehicle)
		{
			if(this.isShooting() && this.moving() && this.fireCounter() < 100&&!this.isVehicle)
			{
				if(world.wrng.nextDouble() < 0.33)
				{
					icot = this.nation.abbrev+"/"+this.type.toString()+"_ico_m"+(world.wrng.nextInt(2)+1);
				}
				else if(world.wrng.nextDouble() < 0.67)
				{
					icot = this.nation.abbrev+"/"+this.type.toString()+"_ico_fm";
				}
				else
				{
					icot = this.nation.abbrev+"/"+this.type.toString()+"_ico_m"+(world.wrng.nextInt(2)+1);
				}
			}
			else if(this.isShooting() && this.fireCounter() < 200&&!this.isVehicle)
			{
				if(world.wrng.nextDouble() < 0.33)
				icot = this.nation.abbrev+"/"+this.type.toString()+"_ico_f1";
				else
					icot = this.nation.abbrev+"/"+this.type.toString()+"_ico";
			}
			else if(this.moving()&&!this.isVehicle)
			{
				icot = this.nation.abbrev+"/"+this.type.toString()+"_ico_m"+(world.wrng.nextInt(2)+1);
			}
			else
			{
				icot = this.nation.abbrev+"/"+this.type.toString()+"_ico";
			}
		}
		if(this.isVehicle)
		{
			if(this.isShooting() && this.fireCounter() < 200)
			{
				icot = this.nation.abbrev+"/"+this.type.toString()+"_ico_f";
			}
			else
			{
				icot = this.nation.abbrev+"/"+this.type.toString()+"_ico";
			}
		}
		
		if(!this.facesLeft())
		{
			icot+="_r";
		}

		OF.setTexture(icot);
		//if(this.isVehicle)
		//	OF.rescaleImg(32, 32);
		//else
		//	OF.rescaleImg(12, 12);

		return OF;
	}

	/**
	 * Synchronizes the x-y pos of 2 entities
	 * @param riding2
	 */
	private void SyncXY(EntityUnit riding2) {
		setX(riding2.x());
		setY(riding2.y());
	}

	private void playerMove()
	{
		if(this.world.calcPos(this, world.UFO_posx, world.UFO_posy) < 10)
		{
			world.sMain().setGameWon();
		}
		/*if(this.target != null && this.activeWeap != null && this.activeWeap.range < world.calcPos(this, target))
		{
			doAttack = false;
			firing = false;
		}
		if(this.target != null && this.activeWeap != null && this.activeWeap.range < world.calcPos(this, target))
		{
			doAttack = true;
		}*/
		if(this.target == null||this.target().unitSize <=0)
		{
			doAttack = false;
			firing = false;
		}
		if(this.unitSize() <= 0)
		{
			this.setDead();
			if(this.isPlayerUnit)
			{
				world.sMain().notifyConsole("Lost contact with "+ identifier);
			}
		}

		if(this.doAttack && target != null)
		{
			this.attack((EntityUnit) target);

			if(((EntityUnit) target).unitSize() <= 0)
			{
				doAttack = false;
				target = null;
				firing = false;
				if(this.isPlayerUnit)
				{
					world.sMain().notifyConsole("Enemy Unit destroyed");
					world.sMain().playSound(this.nation.tagSound().filePath);
				}
				cancelOrders();
			}
		}
		if(this.shouldMovetoDest)
		{
			this.heading = Math.atan2(desty-this.y(), destx-x());
			this.moveInDir(heading);
			if(world.calcPos(this, destx, desty) <= 5)
			{
				this.destx = (int)x();
				this.desty = (int)y();
				world.sMain().notifyConsole(identifier+ ": Arrived at destination x:"+ this.x()+" y: "+this.y());
				cancelOrders();
			}
			
		}
	}
	protected void moveInDir(double heading)
	{
		//world.sMain().notifyConsole(this.identifier+" x: "+this.x()+" y: "+this.y() + "Unit size: "+ unitSize());
		if(this.isVehicle)
		{
			if(world.wrng.nextInt(2) == 1)
			{
				if(world.wrng.nextInt(1000) < 35)
				{
					if(this.type.name.contains("TK"))
						this.playSound(Sounds.Tank_Engine.soundID);
					else if (this.type.name.contains("IFV")||this.type.name.contains("APC")||this.type.name.contains("SPG"))
					{
						this.playSound(Sounds.Vhcl_move.soundID);
					}
				}
			}
		}
		super.moveInDir(heading);
	}
	
	public void selectUnit(boolean tf)
	{
		if(this.riding==null)
		selected = tf; 
		else
			this.riding.selected = tf;
	}

	public void attack(EntityUnit e)
	{
		float ind = (float) (Math.random()*0.6+0.4);
		double dx = Math.abs(this.x()-e.x());
		double dy = Math.abs(this.y()-e.y());

		double damageToDo = 0D;

		double distance = world.calcPos(this, e);
		
		int rof1 = weap1.rof*30/1000;
		int rof2 = weap2.rof*30/1000;
		int rof3 = weap3.rof*30/1000;
		
		int realAP;
		
		if(weap2 != null&& weap2!= Armament.UNARMED&&ammo2>0&&distance <= weap2.range&& (target().isVehicle||this.isVehicle||weap2.name().contains("HE")))
		{
			if(distance <= weap2.range)
			{
				activeWeap = weap2;
				if((fireCounter < 90))//&&!hasFiredThisTurn)
				{
					firing = true;
					if(e.unitArmor < weap2.ap + 200){	
						world.sMain().notifyConsole(this.identifier+" fired "+weap2.name+" at "+fireCounter+" seconds, ammo = "+ammo2/3);
						ammo2--;
						playSound(weap2.sound.soundID);
						world.shootBullet(35, this, target());
						if(weap2.toString().contains("_C_"))
						{
							damageToDo = weap1.dmg*weap1.accuracy*this.optics*2*((EntityUnit)this.target).stealth*7;
						}
						else
						{
							damageToDo = weap2.dmg*weap1.accuracy*this.optics/4*((EntityUnit)this.target).stealth*this.weap2.ap/this.target().unitArmor;
							damageToDo *= incFactor(weap2, target());
						}
					}
				}
			}
		}
		
		else if(weap1 != null&& weap1!= Armament.UNARMED&&ammo1>0 && distance <= weap1.range&& (e.unitArmor < weap2.ap + 75||this.isVehicle))
		{
			if(distance <= weap1.range)
			{
				activeWeap = weap1;
				if(fireCounter <90)// && !hasFiredThisTurn)
				{
					firing = true;
					if(e.unitArmor < weap1.calcAP(distance)+100){	
						playSound(weap1.sound.soundID);
						world.shootBullet(35, this, target());
						world.sMain().notifyConsole(this.identifier+" fired "+weap1.name+" at "+fireCounter+" seconds, ammo = "+ammo1/3);
						ammo1--;
						
						damageToDo = weap1.dmg*weap1.accuracy*this.optics/4*((EntityUnit)this.target).stealth;
						double weapAcc = weap1.accuracy*1000;
						int pAcc = (int)(Math.random()*100);
						world.sMain().notifyConsole(pAcc+"/"+(weapAcc));
						if(pAcc <= (weapAcc))
						{
							if(!target().isVehicle)
							{
								if(this.weap1.ap > target().unitArmor)
								{
									damageToDo = weap1.dmg*weap1.accuracy*this.optics*2*((EntityUnit)this.target).stealth/6;
									world.sMain().notifyConsole("C");
								}
								else if(world.wrng.nextInt(50)>25)
								{
									damageToDo = weap1.dmg*weap1.accuracy*this.optics*2*((EntityUnit)this.target).stealth/8;
									world.sMain().notifyConsole("C1");
								}
								else
								{
									damageToDo = 0;
									world.sMain().notifyConsole("No penetration-I");
								}
							}
							
							else if(target().isVehicle)
							{
								if(this.weap1.ap > target().unitArmor+800)
								{
									damageToDo = weap1.dmg/3D*4D;
									world.sMain().notifyConsole("A");
								}
								else if(this.weap1.ap > target().unitArmor+500)
								{
									damageToDo = weap1.dmg/3D;
									world.sMain().notifyConsole("B");
								}
								else if(this.weap1.ap > target().unitArmor+200)
								{
									damageToDo = weap1.dmg/6D;
									world.sMain().notifyConsole(damageToDo+"");
									world.sMain().notifyConsole("B1");
								}
								else if(this.weap1.ap > target().unitArmor)
								{
									damageToDo = weap1.dmg*weap1.accuracy*this.optics*2*((EntityUnit)this.target).stealth;
									world.sMain().notifyConsole("C");
								}
								else if(world.wrng.nextInt(50)>25)
								{
									damageToDo = weap1.dmg*weap1.accuracy*this.optics*2*((EntityUnit)this.target).stealth;
									world.sMain().notifyConsole("C1");
								}
								else
								{
									damageToDo = 0;
									world.sMain().notifyConsole("No penetration");
								}
							}
							if(!this.isVehicle)
							{
								damageToDo *= (double)this.unitSize/4D;
								world.sMain().notifyConsole("E");
							}
						}
						else
						{
							if(target().isVehicle)
							{
								/*if(this.weap1.ap > target().unitArmor+500)
								damageToDo = weap1.dmg;
								else
								damageToDo = weap1.dmg*(weap1.calcAP(distance)/target().unitArmor)/100;
								*/
								world.sMain().notifyConsole("Missed");
							}
							
							if(!this.isVehicle)
								world.sMain().notifyConsole("Missed");
								//damageToDo *= this.unitSize/2;
							else
							{
								damageToDo = 0;
								world.sMain().notifyConsole("Missed");
							}
						}
						if(!target().isVehicle)
						damageToDo *= incFactor(weap1, target());
					}
					else
					{
						playSound(weap1.sound.soundID);
						world.shootBullet(35, this, target());
						world.sMain().notifyConsole("No penetration");
						world.sMain().notifyConsole(this.identifier+" fired "+weap1.name+" at "+fireCounter+" seconds");
						ammo1--;
					}
				}
			}
		}
		else if(weap3 != null && weap3 != Armament.UNARMED&&ammo3>0 && e.unitArmor < weap3.ap + 200)
		{
			if(distance <= weap3.range)
			{
				activeWeap = weap3;
				if((fireCounter < 90))// && !hasFiredThisTurn)
				{
					playSound(weap3.sound.soundID);
					world.shootBullet(35, this, target());
					firing = true;
					double weapAcc = weap3.accuracy*1000;
					int pAcc = (int)(Math.random()*100);
					world.sMain().notifyConsole(pAcc+"/"+(weapAcc));
					if(pAcc <= (weapAcc))
					{
						if(!target().isVehicle)
						{
							if(this.weap3.ap > target().unitArmor)
							{
								damageToDo = weap3.dmg*weap3.accuracy*this.optics*2*((EntityUnit)this.target).stealth;
								world.sMain().notifyConsole("C");
							}
							else if(world.wrng.nextInt(50)>25)
							{
								damageToDo = weap3.dmg*weap3.accuracy*this.optics*2*((EntityUnit)this.target).stealth;
								world.sMain().notifyConsole("C1");
							}
							else
							{
								damageToDo = 0;
								world.sMain().notifyConsole("No penetration-I");
							}
						}
						
						else if(target().isVehicle)
						{
							if(this.weap3.ap > target().unitArmor+800)
							{
								damageToDo = weap3.dmg/3D*4D;
								world.sMain().notifyConsole("A");
							}
							else if(this.weap3.ap > target().unitArmor+600)
							{
								damageToDo = weap3.dmg/3D*2D;
								world.sMain().notifyConsole("B");
							}
							else if(this.weap3.ap > target().unitArmor+400)
							{
								damageToDo = weap3.dmg/3D;
								world.sMain().notifyConsole(damageToDo+"");
								world.sMain().notifyConsole("B1");
							}
							else if(this.weap3.ap > target().unitArmor+200)
							{
								damageToDo = weap3.dmg/6D;
								world.sMain().notifyConsole(damageToDo+"");
								world.sMain().notifyConsole("Z");
							}
							else if(this.weap3.ap > target().unitArmor)
							{
								damageToDo = weap3.dmg*weap3.accuracy*this.optics*2*((EntityUnit)this.target).stealth;
								world.sMain().notifyConsole("C");
							}
							else if(world.wrng.nextInt(50)>25)
							{
								damageToDo = weap3.dmg*weap3.accuracy*this.optics*2*((EntityUnit)this.target).stealth;
								world.sMain().notifyConsole("C1");
							}
							else
							{
								damageToDo = 0;
								world.sMain().notifyConsole("No penetration");
							}
						}
						if(!this.isVehicle)
						{
							damageToDo *= (double)this.unitSize/4D;
							world.sMain().notifyConsole("E");
						}
					}
					else
					{
						if(target().isVehicle)
						{
							/*if(this.weap3.ap > target().unitArmor+500)
							damageToDo = weap3.dmg;
							else
							damageToDo = weap3.dmg*(weap3.calcAP(distance)/target().unitArmor)/100;
							*/
							world.sMain().notifyConsole("Missed");
						}
						
						if(!this.isVehicle)
							world.sMain().notifyConsole("Missed");
							//damageToDo *= this.unitSize/2;
						else
						{
							damageToDo = 0;
							world.sMain().notifyConsole("Missed");
						}
					}
					if(!target().isVehicle)
					damageToDo *= incFactor(weap3, target());
				
					world.sMain().notifyConsole(this.identifier+" fired "+weap3.name+" at "+fireCounter+" seconds, ammo = "+ammo3/3);
					ammo3--;
				
			}
		}
		}
		
	
		if(damageToDo > 0)
		{
			world.sMain().notifyConsole("Damage done on "+e.identifier+" by "+this.identifier+": "+damageToDo);
			hasFiredThisTurn = true;
		}
		if(e.riding() == null)
			e.damage(this, damageToDo);
		else
			e.riding().damage(this, damageToDo);
		/*if(weap1 == Armament.Pwees_LASER3 || weap1 == Armament.Tz_C_1998)
		{

			if(weap1 != null && ammo1 > 0)
			{
				if((weap1 == Armament.Pwees_LASER3 || weap1 == Armament.Tz_C_1998) && fireCounter % 250 ==0)
				{
					if(Math.sqrt(dx*dx+dy*dy) <= weap1.range)
					{
						activeWeap = weap1;
						firing = true;


						damageToDo = weap1.dmg*weap1.accuracy*this.optics/4*(1/((EntityUnit)this.target).stealth);
						if(e.unitArmor - weap1.calcAP(distance) > 1)
						{
							if(target().isVehicle)
							{
								if(activeWeap.ap-target().unitArmor > 0)
								damageToDo*=(activeWeap.ap-target().unitArmor);
							}
						}
						else if(e.unitArmor > weap1.calcAP(distance))
						{
							damageToDo *= 0.1;
						}
						world.sMain().notifyConsole(identifier+": Firing!");
						ammo1--;

						if(activeWeap != null)
						{
							if(target !=null && world.calcPos(this, target) > activeWeap.range*3/4)
							{
								damageToDo*=4;

							}
							else if(target !=null && world.calcPos(this, target) > activeWeap.range*1/2)
							{
								damageToDo*=8;
							}

							else if(target !=null && world.calcPos(this, target) > activeWeap.range*1/2)
							{
								damageToDo*=12;
							}

							else
							{
								damageToDo*=16;
							}
							if(firing && doAttack)
							playSound(activeWeap.sound.soundID);
						}
						world.sMain().notifyConsole("Damage: "+damageToDo);
						e.damage(this, damageToDo);
					}

					else
					{
						if(fireCounter % 60 <= 5)
						{
							if(weap1 != null && ammo1 > 0)
							{
									if(Math.sqrt(dx*dx+dy*dy) <= weap1.range)
									{
										activeWeap = weap1;
										firing = true;

										damageToDo = weap1.dmg*weap1.accuracy*this.optics/4*(1/((EntityUnit)this.target).stealth);
										if(e.unitArmor - weap1.calcAP(distance) > 1)
										{
											damageToDo /= (e.unitArmor-weap1.calcAP(distance));
										}
										else if(e.unitArmor > weap1.calcAP(distance))
										{
											damageToDo *= 0.1;
										}
										world.sMain().notifyConsole(identifier+": Firing!");
										ammo1--;
										if(activeWeap != null)
										{
											if(target !=null && world.calcPos(this, target) > activeWeap.range*3/4)
											{
												damageToDo*=4;

											}
											else if(target !=null && world.calcPos(this, target) > activeWeap.range*1/2)
											{
												damageToDo*=8;
											}

											else if(target !=null && world.calcPos(this, target) > activeWeap.range*1/2)
											{
												damageToDo*=12;
											}

											else
											{
												damageToDo*=16;
											}

											if(firing && doAttack)
											playSound(activeWeap.sound.soundID);
										}
										world.sMain().notifyConsole("Damage: "+damageToDo);
										e.damage(this, damageToDo);
									
								}

							}
						}
					}
				}
			}
		}*/
							/*else
							{


								if(fireCounter % 60 <= 5)
								{
									if(weap1 != null && ammo1 > 0)
									{
										if(Math.sqrt(dx*dx+dy*dy) <= weap1.range)
										{
											activeWeap = weap1;
											firing = true;


											damageToDo = weap1.dmg*weap1.accuracy*this.optics/4*(1/((EntityUnit)this.target).stealth);
											if(e.unitArmor - weap1.calcAP(distance) > 1)
											{
												damageToDo /= (e.unitArmor-weap1.calcAP(distance));
											}
											else if(e.unitArmor > weap1.calcAP(distance))
											{
												damageToDo *= 0.1;
											}
											world.sMain().notifyConsole(identifier+": Firing!");
											ammo1--;


										}
										else if(weap2 != null && ammo2 > 0 && Math.sqrt(dx*dx+dy*dy) <= weap2.range)
										{
											activeWeap = weap2;
											firing = true;

											damageToDo = weap2.dmg*weap2.accuracy*this.optics/4*(1/((EntityUnit)this.target).stealth);
											if(e.unitArmor - weap1.calcAP(distance) > 1)
											{
												damageToDo /= (e.unitArmor-weap1.calcAP(distance));
											}
											else if(e.unitArmor > weap1.calcAP(distance))
											{
												damageToDo *= 0.1;
											}
											ammo2--;


										}
										else if(weap3 != null && ammo3 > 0 && Math.sqrt(dx*dx+dy*dy) <= weap3.range)
										{
											activeWeap = weap3;

											firing = true;
											damageToDo = weap3.dmg*weap3.accuracy*this.optics/4*(1/((EntityUnit)this.target).stealth);
											if(e.unitArmor - weap1.calcAP(distance) > 1)
											{
												damageToDo /= (e.unitArmor-weap1.calcAP(distance));
											}
											else 
											{
												damageToDo *= Math.abs((e.unitArmor-weap1.calcAP(distance))/1.5);
											}
											ammo3 --;
										}
										//damageToDo*=this.optics/4;
										//damageToDo*=this.unitSize;
										if(activeWeap != null)
										{
											if(target !=null && world.calcPos(this, target) > activeWeap.range*3/4)
											{
												damageToDo*=4;

											}
											else if(target !=null && world.calcPos(this, target) > activeWeap.range*1/2)
											{
												damageToDo*=8;
											}

											else if(target !=null && world.calcPos(this, target) > activeWeap.range*1/2)
											{
												damageToDo*=12;
											}

											else
											{
												damageToDo*=16;
											}

											if(firing && doAttack)
											playSound(activeWeap.sound.soundID);
										}
										world.sMain().notifyConsole("Damage: "+damageToDo);
										e.damage(this, damageToDo);
									} 
								}

							}*/
	}
				
			
	/**Use this instead*/
	public void damage(Entity attacker, double damage)
	{
		health -= damage;
		//playSound(Sounds.Tz_dmg.soundID);
		if(Math.ceil(health) < unitSize)
		this.unitSize--;
		if(unitSize() == 0)
		{
			this.setDead();
			if(this.isPlayerUnit)
			playSound(nation.deathSound().soundID);
		}
		
		if (this.unitSize < this.type.unit_size/2)
		{
			if(isPlayerUnit)
			{
				//this.playSound(Sounds.Tz_dmg.soundID);
			}
		}
		
		if(this.isVehicle &&damage > 0.5)
		{
			world.explodeNoSound((int) (2*damage), (int)x(), (int)y());
		}
		if(!isPlayerUnit)
		setTarget(attacker, false);
		
		if(isPlayerUnit)
		{
			if((this.target == null||this.target().unitSize <= 0)&&(damage > 0.0F))
			{
				playSound(nation.orderSound().soundID);
				target = attacker;
				doAttack = true;
			}
		}
		else
		{
			
		}
	}
	
	private float incFactor(Armament weap, EntityUnit target) {
		if(target !=null && world.calcPos(this, target) > weap.range*3/4)
		{
			return 1F;

		}
		else if(target !=null && world.calcPos(this, target) > weap.range*1/2)
		{
			return 2F;
		}

		else if(target !=null && world.calcPos(this, target) > weap.range*1/2)
		{
			return 3F;
		}

		else
		{
			return 4F;
		}
	}
	
	public void setDestination (double d, double e)
	{
		this.destx = (int) d;
		this.desty = (int) e;
		this.shouldMovetoDest = true;
		if(this.isPlayerUnit&&affirmCheck)
		{
			playSound(this.nation.affirmSound().soundID);
			affirmCheck = false;
		}
	}
	
	public void cancelOrders()
	{
		super.cancelOrders();
		shouldMovetoDest = false;
		affirmCheck = true;
	}
	
	public void setTarget(Entity e, boolean speaks)
	{
		if(speaks)
		this.setDestination(e.x(), e.y());
		else
		{
			this.destx = (int) e.x();
			this.desty = (int) e.y();
			this.shouldMovetoDest = true;
		}
		this.target = e;
		doAttack = true;
	}
	
	private void AIMove()
	{
		if(this.unitSize() == 0)
		{
			this.setDead();
			if(this.isPlayerUnit)
			{
				world.sMain().notifyConsole("Lost contact with "+ identifier);
			}
		}


		if(this.doAttack && target != null)
		{

			 if(world.calcPos(this, target) <= ((EntityUnit)target).weap1.range*2)
				{
					this.dismountVehicle();
				}
			 
			this.attack((EntityUnit) target);
			

			if(target!=null && world.calcPos(this, target) > this.weap1.range/10)
			{
				this.destx = (int) target.x();
				this.desty = (int) target.y();
			}
			
			else if(((EntityUnit) target).unitSize() <= 0)
			{
				doAttack = false;
				target = null;
				firing = false;
				if(this.isPlayerUnit)
				{
					world.sMain().notifyConsole("Enemy Unit destroyed");
					world.sMain().playSound(this.nation.tagSound().soundID);
				}
				cancelOrders();
			}
			
			
		}
		
		if(this.shouldMovetoDest)
		{
			if((int)this.x() == (int)this.destx && this.y() == this.desty)
			{
				cancelOrders();
				this.destx = (int)x();
				this.desty = (int)y();
				world.sMain().notifyConsole(identifier+ ": Arrived at destination x:"+ this.x()+" y: "+this.y());
			}
			this.heading = Math.atan2(desty-this.y(), destx-x());
			this.moveInDir(heading);
		}

		
		if(this.target == null || ((EntityUnit)this.target).unitSize <= 0)
		{
			EntityUnit em = world.nextPlayerUnit(this.nation);
			if(em != null)
			this.setTarget(em, false);
			else if(em==null)
			{
				em = world.nextPlayerUnit(this.nation);
				if(em!=null)
				{
					this.setTarget(em, false);
				}
				else
				{
					this.setDestination(world.base_posx, world.base_posy);
				}
			}
		}
	}
	
	
	public void setDead()
	{
		if(this !=null && this.isVehicle && target!= null)
		{
			float dmgDiff = target().type.stdWeap.dmg;
			if(((EntityUnit)target).activeWeap!=null)
			dmgDiff = (float)((EntityUnit)target).activeWeap.dmg/20F*(float)target().activeWeap.ap/((float)this.unitArmor);
			
			if(this.rider != null)
			{
				rider.dismountVehicle();
				rider.damage(this, dmgDiff);
				world.sMain().notifyConsole("Damage done on "+rider.identifier+" by exploding vehicle: "+dmgDiff);
			}
			world.explode((int)(dmgDiff), (int)this.x(), (int)this.y());
		}
		world.kill(this);
	}
	
	public void equip(Armament a, int slot)
	{
		if(slot == 1){
			weap1 = a;
			ammo1 = weap1.ammo;
		}
		else if(slot == 2){
			weap2 = a;
			ammo2 = weap2.ammo;
		}
		else if(slot == 3){
			weap3 = a;
			ammo3 = weap3.ammo;
		}
	}	

	
	public int unitSize() {
		return unitSize;
	}

	public void setSize(int unitSize) {
		this.unitSize = unitSize;
	}

	public void mountVehicle(EntityUnit e)
	{
		if(e.isVehicle&& !this.isVehicle)
		{
			e.rider = this;
			e.hasRider = true;
			e.flagged = false;
			this.riding = e;
		}
	}
	
	public void dismountVehicle()
	{
		if(this.isVehicle && this.rider != null)
		{
			rider.dismountVehicle();
			this.rider = null;
			this.hasRider = false;
		}
		else if (this.riding != null)
		{	
			EntityUnit e = this.riding;
			e.rider = this;
			e.hasRider = true;
			e.flagged = false;
			this.riding = null;
		}
		hasRider = false;
		flagged = false;
	}
	
	public Armament getEquipped(int slot)
	{
		if(slot == 1)
			return weap1;
		else if(slot == 2)
			return weap2;
		else 
			return weap3;
	}
	
	public boolean selected()
	{
		return (this.selected);
	}
	
	public void draw()
	{
		if(!this.isVehicle)
		{
			if(this.firing && this.fireCounter <100)
			{
				/*Texture tm;
				try {
					tm = world.sMain().textureLoader.getTexture("pointpointy/resources/textures/" + this.mapico+"_f"+(world.wrng.nextInt(2)+1));
					OF.setTexture(tm);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				OF.setImage(SMain.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"/pointpointy/resources/textures/"+this.mapico+"_f"+(world.wrng.nextInt(2)+1)+".png");
				
			}
			
			else if(this.shouldMovetoDest)
			{
				/* Texture tm;
				try {
					tm = world.sMain().textureLoader.getTexture("pointpointy/resources/textures/" + this.mapico+"_m"+(world.wrng.nextInt(2)+1));
					OF.setTexture(tm);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
				OF.setImage(SMain.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"/pointpointy/resources/textures/"+this.mapico+"_m"+(world.wrng.nextInt(2)+1)+".png");
			}
			
		}
		else if(isVehicle)
		{
			if(this.firing && fireCounter < 100)
			{
				OF.setImage(SMain.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"/pointpointy/resources/textures/"+this.mapico+"_f.png");
			}
			else
			{
				OF.setImage(SMain.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"/pointpointy/resources/textures/"+this.mapico+".png");
			}
		}
	}

	public boolean hasDest() {
		return this.shouldMovetoDest;
	}
	
	public int dest(int xy)
	{
		if(xy == 0)
			return this.destx;
		if(xy == 1)
			return this.desty;
		else
			return -111;
	}

	public EntityUnit target() {
		// TODO Auto-generated method stub
		return (EntityUnit) this.target;
	}

	public Armament getWeap(int i) {
		// TODO Auto-generated method stub
		switch(i){
		case 1: return weap1;
		case 2: return weap2;
		case 3: return weap3;
		}
		return Armament.UNARMED;
	}

	public EntityUnit rider() {
		return rider;
	}

	public void setFlagged(EntityUnit currSelection) {
		this.flagged = true;
		this.hasRider = true;
		this.rider = currSelection;
	}

	public EntityUnit riding() {
		return riding;
	}
	/**
	 * allows an entity to mount this vehicle
	 * @param riding = the entity that will be riding this vehicle
	 */
	public void setRiding(EntityUnit riding) {
		this.flagged  = true;
		this.rider = riding;
	}

	public boolean isShooting() {
		// TODO Auto-generated method stub
		return this.firing;
	}
	
	public boolean moving()
	{
		return this.shouldMovetoDest;
	}

	public double fireCounter() {
		return fireCounter;
	}
	/**
	 * @return the weapon being fired currently by the unit
	 */
	public Armament currWeap()
	{
		return this.activeWeap;
	}
	/**
	 * @param p = the player
	 * @return whether the unit has been spotted by the player's units
	 */
	public boolean spotted(Player p)
	{
		for(Entity e: world.entitiesList())
		{
			EntityUnit pweesha = ((EntityUnit)e);
			if(pweesha.nation == p.nation && (world.calcPos(e, this) < pweesha.optics*1000*this.stealth))
			{
				return true;
			}
		}
		return false;
		
	}
	
	public boolean facesLeft()
	{
		if(this.target() != null)
		{
			if(this.x() - target.x() < 0)
				return false;
			else if(this.x() - target.x() > 0)
				return true;
		}
			return super.facesLeft();
	}
}
