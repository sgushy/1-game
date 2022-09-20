package pointpointy;

public enum Armament {
	UNARMED(null, "None", 100000, 0D, 4, 0.5F, 0, 1, "UNARMED"),
	//rifles and smgs
	Tz_SKS(Sounds.SKS, "VPL-901", 900, 3D, 20, 0.7F, 14, 1, "Tz_SKS"),
	Tz_MP40(Sounds.MP40, "VPK-974", 3000, 2D, 280, 0.35F, 4, 1, "Tz_MP40"),
	Tz_AS(Sounds.MP40, "VPL-990", 3000, 2.5D, 140, 0.55F, 12, 1, "Tz_AS"),
	Tz_VPL(Sounds.VPL, "VPL-979", 2400, 2.75D, 100, 0.65F, 19, 1, "Tz_VPL"),
	
	Pu_PM4(Sounds.SKS, "PM-4", 900, 3D, 20, 0.7F, 14, 1, "Tz_SKS"),
	Pu_Bow(Sounds.Bow, "Repeating Crossbow", 900, 2D, 30, 0.65F, 5, 1, "Pu_Bow"),
	Pu_Windbuchse(Sounds.SKS, "Air Rifle", 900, 3D, 45, 0.75F, 4, 1, "Pu_Windbuchse"),
	
	Tr_TR1(Sounds.DP2, "TR-1", 3000, 2D, 210, 0.5F, 4, 1, "Tr_TR1"),
	Tr_DP2(Sounds.DP2, "DP-2", 2400, 2.25D, 120, 0.5F, 14, 1, "Tr_DP2"),
	Tr_DP3(Sounds.DP3, "DP-3", 2400, 2.5D, 90, 0.65F, 16, 1, "Tr_DP3"),
	Tr_DP4(Sounds.DP3, "DP-4", 2400, 2.5D, 90, 0.70F, 24, 1, "Tr_DP4"),
	
	Fs_WITHA(Sounds.V_20M, "\"With-A\"", 750, 2D, 60, 0.3F, 50, 3, "Fs_WITHA"),	
	
	NATO_M4(Sounds.VPL, "M4 Carbine", 800, 2.5D, 90, 0.7F, 8, 1, "NATO_M4"),
	//snipers
	Tz_SVK(Sounds.V_30M, "PML-111", 100, 5D, 10, 0.75F, 34, 1, "Tz_SVK"),
	//lmgs
	Tz_SAW33HE(Sounds.SKS, "ZPZ-943", 1000, 3.25D, 150, 0.15F, 14, 1, "Tz_SAW33"),
	Tz_SAW34HE(Sounds.SKS, "ZPZ-963", 1000, 3.25D, 150, 0.15F, 17, 1, "TZ_SAW34"),
	Tz_SAW35HE(Sounds.VPL, "ZPZ-980", 1000, 3.25D, 120, 0.25F, 21, 1, "Tz_SAW35"),
	
	Tr_LMGHE(Sounds.DP3, "DS-3", 1000, 3.25D, 90, 0.3F, 20, 1, "Tr_LMG"),
	Tr_LMG2HE(Sounds.DP3, "DS-2", 1000, 3.25D, 90, 0.25F, 20, 1, "Tr_LMG2"),
	
	Fs_WITHT(Sounds.V_30M, "\"With-The\"", 450, 3D, 180, 0.2F, 50, 4, "Fs_WITHT"),	
	Fs_DB(Sounds.DP3, "\"Diamond-Belly\"", 900, 3D, 200, 0.5F, 40, 1, "Fs_DB"),	
	//rpgs
	Tz_Sgom(Sounds.Sgom, "SGOM-44", 24, 2.5D, 20, 0.3F, 400, 12, "Tz_Sgom"),
	Tz_Sgom2(Sounds.Sgom2, "SGOM-82", 24, 2.5D, 20, 0.5F, 800, 12, "Tz_Sgom2"),
	Tz_Sgom3(Sounds.Sgom3, "SGOM-99", 24, 2.5D, 20, 0.7F, 1200, 12, "Tz_Sgom3"),
	
	Tz_C_SgomHE(Sounds.Sgom3, "SGOM-99ZW", 8, 2.5D, 20, 0.75F, 400, 3, "Tz_Sgom3"),
	
	Tr_Sgom2(Sounds.Sgom, "T-82", 24, 2.5D, 20, 0.6F, 400, 12, "Tz_Sgom2"),
	Tr_T01(Sounds.Sgom3, "T-2001", 24, 2.75D, 20, 0.8F, 1100, 12, "Tr_T01"),
	Tr_C_T01HE(Sounds.Sgom3, "T-2001H", 24, 2.75D, 20, 0.8F, 500, 3, "Tr_T01"),
	
	Fs_Touncer(Sounds.Detonate, "Wershy Touncer", 30, 2.5D, 6, 0.3F, 1400, 22, "Tz_C_1998"),

	Fs_C_TouncerHE(Sounds.Detonate, "Wershy Touncer", 30, 2.5D, 6, 0.3F, 800, 4, "Tz_C_1998"),
	
	//autocannons
	Tz_VK44(Sounds.V_20M, "P-77 14mm", 1500, 3.25D, 120, 0.3F, 35, 2, "Tz_VK44"),
	Tz_V_20M(Sounds.V_20M, "P-75 20mm", 1000, 6D, 120, 0.45F, 50, 3, "Tz_V_20M"),
	Tz_V_FSM(Sounds.V_FSM, "P-80 44mm", 300, 8D, 60, 0.5F, 130, 4, "Tz_V_20M"),
	Tz_V_30M(Sounds.V_30M, "P-77 30mm", 600, 7D, 90, 0.7F, 130, 3, "Tz_V_20M"),
	
	Tz_AIR_V_20M(Sounds.V_20M, "Z-64 20mm", 1800, 9D, 600, 0.4F, 35, 3, "Tz_V_20M"),
	Tz_AIR_V_30M(Sounds.V_30M, "Z-64 30mm", 900, 9D, 600, 0.5F, 70, 3, "Tz_V_20M"),
	
	Tr_V_25M(Sounds.V_20M, "R-5 25mm", 1000, 7D, 100, 0.5F, 50, 4, "Tz_V_20M"),
	
    //cannons
	Tz_C_1944(Sounds.C_1998, "C-1944 99-88mm", 150, 12D, 15, 0.5F, 400, 14, "Tz_C_1944"),
	Tz_C_1964(Sounds.C_1998, "C-1964 128-116mm", 120, 14D, 15, 0.55F, 550, 16, "Tz_C_1998"),
	
	Tz_C_1978(Sounds.C_1998, "C-1978 128mm", 120, 16D, 10, 0.6F, 750, 16, "Tz_C_1998"),
	Tz_C_1998(Sounds.C_1998, "C-1998 128mm", 120, 16D, 12, 0.75F, 1200, 16, "Tz_C_1998"),
	Tz_C_2009(Sounds.C_1998, "C-2009 128mm", 120, 16D, 12, 0.85F, 1400, 16, "Tz_C_1998"),
	
	Tz_C_2009B(Sounds.C_1998, "C-2009 162mm", 60, 18D, 8, 0.85F, 1600, 20, "Tz_C_1998"),


	Tr_C_ANTI3(Sounds.Detonate, "TVK-56 RG", 120, 30D, 5, 0.75F, 1600, 16, "Tr_C_ANTI"),
	Tr_C_ANTI2(Sounds.Detonate, "TVK-78 RG", 120, 30D, 5, 0.75F, 2000, 16, "Tr_C_ANTI"),
	Tr_C_ANTI(Sounds.Detonate, "TVK-90 RG", 120, 30D, 5, 0.85F, 2400, 16, "Tr_C_ANTI"),

	NATO_V_Bushmaster(Sounds.V_30M, "25mm Bushmaster", 1200, 6D, 120, 0.6F, 70, 4, "Tz_V_20M"),
	NATO_C_M256(Sounds.C_1998, "120mm M256", 120, 16D, 12, 0.75F, 725, 15, "Tz_C_1998"),
	NATO_C_M2562(Sounds.C_1998, "120mm M256E1", 120, 16D, 12, 0.75F, 850, 15, "Tz_C_1998"),


	Fs_C_Touncer(Sounds.Detonate, "Wershy Touncer Cannon", 900, 10D, 60, 0.3F, 1100, 16, "Tz_C_1998"),
	
	Pu_C_75m(Sounds.V_FSM, "Pu-5 75mm", 240, 12D, 12, 0.55F, 200, 14, "Tz_V_FSM"),
	Pu_C_2010(Sounds.C_1998, "P-140-9-12 140mm", 75, 16D, 8, 0.65F, 1200, 20, "Tz_C_1998"),

	//atgm
	//Tz_ATGMP(Sounds.Sgom3, "Pillowisan ATGM", 4, 12.5D, 2, 0.9F, 2400, 30),
	//plasma weapons
	Pwees_LASER(Sounds.Pwees_LASER, "Plasma Blaster", 1000, 2D, 120, 0.5F, 30, 2, "Pwees_LASER"),
	Pwees_LASER2(Sounds.Pwees_LASER, "Plasma Rifle", 1000, 2.5D, 60, 0.5F, 100, 3, "Pwees_LASER2"),
	Pwees_LASER3(Sounds.Pwees_LASER, "Portable Plasma Cannon", 400, 3D, 20, 0.65F, 1400, 6, "Pwees_LASER3"), 
	Pwees_LASER4(Sounds.Pwees_LASER, "Plasma Cannon", 400, 10D, 20, 0.75F, 1400, 16, "Pwees_LASER4"), 
	////
	;
	
	public final int range;
	public final int rof;
	public final int ap;
	public final int dmg;
	public final int ammo;
	public final float accuracy;
	public final String name;
	
	public final Sounds sound;
	
	public final String image;
	/**
	 * @param Name = weapon name
	 * @param ammo = ammo count before reload
	 * @param range = max range (in 100s of meters)
	 * @param rof = number of rounds fired per min
	 * @param accuracy = accuracy of weapon (1 is highest, 0 is lowest)
	 * @param armor_pierce = maximum armor penetration (in mm)
	 * @param damage = damage done to unarmored target
	 * */
	Armament(Sounds sound, String name, int ammo, double range, int rof, float accuracy, int armor_pierce, int damage, String imgLoc)
	{
		this.sound = sound;
		
		this.name = name;
		this.ammo = ammo;
		this.range = (int)range*50;
		this.rof = rof;
		this.accuracy = accuracy/10;
		this.ap = armor_pierce; 
		this.dmg = damage;
		this.image = imgLoc;
	}
	
	public int calcAP(double distance)
	{
		if(distance < this.range/4)
		{
			return (int)(this.ap*1.75);
		}
		else if(distance < this.range/3)
		{
			return (int)(this.ap*1.33);
		}
		else if(distance < this.range/3*2)
		{
			return (int)(this.ap*1.2);
		}
		else return this.ap;
		
	}
}
