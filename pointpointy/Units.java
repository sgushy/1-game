package pointpointy;

public enum Units {
	//Pweeses
	Pw_Pwees("Alien Infantry", Armament.Pwees_LASER, Armament.Pwees_LASER3, Armament.UNARMED, 0.7F, 1.5F, 6, 3, 20, false, Nations.Pwees),
	Pw_PELITE("Alien Elite Infantry", Armament.Pwees_LASER2, Armament.Pwees_LASER3, Armament.UNARMED, 1.5F, 0.9F, 6, 3, 20, false, Nations.Pwees),
	Pw_PRAIDER("Alien Commandos", Armament.Pwees_LASER2, Armament.Pwees_LASER3, Armament.UNARMED, 1.1F, 0.6F, 6, 3, 20, false, Nations.Pwees),
	Pw_Mech_TK("Robot Tank", Armament.Pwees_LASER4, Armament.UNARMED, Armament.UNARMED, 1.1F, 1.4F, 20, 6, 300, true, Nations.Pwees),
	Pw_Hover_TK("Hover Tank", Armament.Pwees_LASER4, Armament.UNARMED, Armament.UNARMED, 1.0F, 1.2F, 20, 20, 900, true, Nations.Pwees),
	
	//Tunnelerland
	Tz_RPOL("Fer Sgazten", Armament.Tz_MP40, Armament.Tz_Sgom, Armament.Tz_SKS, 0.7F, 1.1F, 10, 2, 0, false, Nations.Tunzen),
	Tz_SPOL("Fer Sgazten", Armament.Tz_MP40, Armament.Tz_Sgom, Armament.Tz_SAW33HE, 0.7F, 1.0F, 10, 2, 3, false, Nations.Tunzen),
	Tz_SGUNZ("Ferzhten", Armament.Tz_VPL, Armament.Tz_Sgom2, Armament.Tz_SAW34HE, 1.1F, 0.8F, 8, 3, 4, false, Nations.Tunzen),
	Tz_PPT_SGUNZ("Pointpointy Sgazten", Armament.Tz_AS, Armament.Tz_Sgom2, Armament.Tz_VK44, 1.1F, 1.0F, 10, 3, 6, false, Nations.Tunzen),
	Tz_Susgush("Susgushten Sgunz", Armament.Tz_VPL, Armament.Tz_Sgom3, Armament.Tz_SAW35HE, 1.2F, 0.8F, 8, 3, 28, false, Nations.Tunzen),
	Tz_Toastzan("Toastzan Sgunz", Armament.Tz_AS, Armament.Tz_C_SgomHE, Armament.Tz_SAW35HE, 1.8F, 0.8F, 8, 3, 8, false, Nations.Tunzen),
	Tz_Sgusz("Sgusz", Armament.Tz_MP40, Armament.UNARMED, Armament.Tz_SVK, 1.8F, 0.4F, 2, 4, 0, false, Nations.Tunzen),
	Tz_Sgusz_Sg("Sguszten Sgunz", Armament.Tz_AS, Armament.Tz_Sgom3, Armament.Tz_SAW35HE, 1.6F, 0.5F, 8, 4, 5, false, Nations.Tunzen),
	
	Tz_Pillowsen("Pillowsen Sgunz (*)", Armament.Tz_MP40, Armament.UNARMED, Armament.Tz_SKS, 1.1F, 0.6F, 8, 3, 0, false, Nations.Tunzen),
	//Tz_IFV_Pointzt_CV("PVF-85 Pointzt (*)", Armament.Tz_V_FSM, Armament.UNARMED, Armament.Tz_SAW33HE, 0.7F, 1.3F, 10, 12, 300, true, Nations.Tunzen),
	//Tz_TK_C1980_CV("PVW-1980 (*)", Armament.Tz_C_1978, Armament.UNARMED, Armament.Tz_SAW33HE, 0.7F, 1.3F, 20, 8, 600, true, Nations.Tunzen),
		
	Tz_APC_Sgoz("PVF-1975 Sgoz", Armament.Tz_V_20M, Armament.UNARMED, Armament.UNARMED, 0.7F, 1.3F, 10, 12, 80, true, Nations.Tunzen),
	Tz_IFV_Pointzt("PVF-1985 Pointzt", Armament.Tz_V_FSM, Armament.UNARMED, Armament.Tz_VK44, 0.7F, 1.3F, 12, 8, 300, true, Nations.Tunzen),
	Tz_IFV_Pointus("KVW-2000 Pointus", Armament.Tz_C_1978, Armament.UNARMED, Armament.Tz_VK44, 1.2F, 0.9F, 8, 12, 100, true, Nations.Tunzen),
	
	Tz_SPG_Pillowius("PVK-2009 Pillowius", Armament.Tz_C_2009B, Armament.UNARMED, Armament.Tz_VK44, 1.2F, 0.9F, 16, 8, 400, true, Nations.Tunzen),

	Tz_TK_C1944M4("PVW-1944 C-1969", Armament.Tz_C_1964, Armament.UNARMED, Armament.Tz_VK44, 0.8F, 1.5F, 20, 7, 400, true, Nations.Tunzen),
	Tz_TK_C1968("PVW-1968 C-1968", Armament.Tz_C_1964, Armament.UNARMED, Armament.Tz_VK44, 0.9F, 1.5F, 20, 8, 550, true, Nations.Tunzen),
	Tz_TK_C1980("PVW-1980", Armament.Tz_C_1978, Armament.UNARMED, Armament.Tz_VK44, 0.9F, 1.5F, 20, 8, 600, true, Nations.Tunzen),
	Tz_TK_C1980M3("PVW-1980 C-1998", Armament.Tz_C_1998, Armament.UNARMED, Armament.Tz_V_FSM, 1.2F, 1.5F, 20, 10, 750, true, Nations.Tunzen),
	
	Tz_TK_C1999("PVW-1999", Armament.Tz_C_1998, Armament.UNARMED, Armament.Tz_V_20M, 1.2F, 1.5F, 20, 9, 900, true, Nations.Tunzen),
	Tz_TK_C1999M("PVW-1999 C-2005", Armament.Tz_C_1998, Armament.UNARMED, Armament.Tz_V_30M, 1.2F, 1.5F, 20, 10, 1000, true, Nations.Tunzen),
	
	Tz_TK_C2010("PVW-2010", Armament.Tz_C_2009, Armament.UNARMED, Armament.Tz_V_30M, 1.3F, 1.0F, 20, 9, 1200, true, Nations.Tunzen),

	Tz_AIR_Ferwerz3("Ferwerz-3", Armament.UNARMED, Armament.UNARMED, Armament.UNARMED, 1.9F, 0.5F, 15, 28, 30, true, Nations.Tunzen),
	Tz_AIR_Sgush15("Sgusz-15", Armament.UNARMED, Armament.UNARMED, Armament.UNARMED, 1.9F, 0.8F, 15, 30, 30, true, Nations.Tunzen),
	Tz_AIR_Ferwerz("Ferwerz-1", Armament.UNARMED, Armament.UNARMED, Armament.UNARMED, 1.9F, 0.7F, 15, 26, 30, true, Nations.Tunzen),
	
	Tz_AIR_Sgush14C("Sgusz-14C", Armament.UNARMED, Armament.UNARMED, Armament.UNARMED, 1.9F, 0.85F, 15, 24, 30, true, Nations.Tunzen),
	Tz_AIR_Sgush14B("Sgusz-14B", Armament.UNARMED, Armament.UNARMED, Armament.UNARMED, 1.9F, 0.85F, 15, 24, 30, true, Nations.Tunzen),
	
	Tz_AIR_Sgush8("Sgusz-8", Armament.UNARMED, Armament.UNARMED, Armament.UNARMED, 1.9F, 0.9F, 15, 22, 30, true, Nations.Tunzen),
	
	//Terraland
	Tr_RPOL("Tervakseyr Poliz", Armament.Tr_TR1, Armament.Tr_Sgom2, Armament.Tr_DP2, 0.7F, 1.0F, 10, 2, 0, false, Nations.Tervakseyr),
	Tr_SPOL("Brüss Poliz", Armament.Tr_DP2, Armament.Tr_Sgom2, Armament.Tr_LMG2HE, 0.7F, 1.0F, 10, 2, 3, false, Nations.Tervakseyr),
	Tr_Terroler("Terrolers", Armament.Tr_DP3, Armament.Tr_Sgom2, Armament.Tr_LMG2HE, 1.1F, 0.8F, 8, 3, 3, false, Nations.Tervakseyr),
	Tr_Brussmont("Brüssmonts", Armament.Tr_DP3, Armament.Tr_Sgom2, Armament.Tr_LMGHE, 1.2F, 0.8F, 8, 3, 6, false, Nations.Tervakseyr),
	
	//Tr_Antiinf("Antipartikelers", Armament.Tr_DP2, Armament.Tr_C_ANTI3, Armament.UNARMED, 1.2F, 0.7F, 3, 1, 1, false, Nations.Tervakseyr),
	
	Tr_Skii("Brüss Tusslung", Armament.Tr_DP4, Armament.Tr_Sgom2, Armament.Tz_SVK, 1.3F, 0.75F, 8, 4, 4, false, Nations.Tervakseyr),
	
	Tr_Tusslung2("Tervakseyr Terrolers", Armament.Tr_DP4, Armament.Tr_T01, Armament.Tr_LMGHE, 1.4F, 0.8F, 8, 3, 20, false, Nations.Tervakseyr), //change texture to brussmont
	Tr_Tusslung("Tervakseyr Garza", Armament.Tr_DP4, Armament.Tr_T01, Armament.Tr_LMGHE, 1.5F, 0.9F, 8, 3, 10, false, Nations.Tervakseyr),
	
	Tr_APC_T32("TAP-33", Armament.Tr_LMGHE, Armament.UNARMED, Armament.Tr_LMGHE, 0.7F, 1.3F, 10, 12, 80, true, Nations.Tervakseyr),
	Tr_APC_T82("TAP-82", Armament.Tz_C_1944, Armament.UNARMED, Armament.Tr_LMGHE, 0.7F, 1.3F, 10, 8, 250, true, Nations.Tervakseyr),
	
	Tr_TK_T222("T-222T", Armament.Tr_C_ANTI2, Armament.UNARMED, Armament.Tz_VK44, 1.1F, 1.5F, 20, 9, 200, true, Nations.Tervakseyr),
	Tr_TK_T444("T-222T3", Armament.Tr_C_ANTI2, Armament.UNARMED, Armament.Tz_VK44, 1.1F, 1.2F, 20, 9, 400, true, Nations.Tervakseyr),
	
	Tr_TK_C1980("T-1980M3", Armament.Tz_C_1998, Armament.UNARMED, Armament.Tz_VK44, 1.0F, 1.5F, 20, 10, 650, true, Nations.Tervakseyr),
	Tr_TK_C1999("T-1999", Armament.Tz_C_1998, Armament.UNARMED, Armament.Tz_VK44, 1.2F, 1.5F, 20, 9, 900, true, Nations.Tervakseyr),
	Tr_TK_C1999T("T-1999T", Armament.Tr_C_ANTI2, Armament.UNARMED, Armament.Tz_VK44, 1.2F, 1.5F, 20, 9, 950, true, Nations.Tervakseyr),
	Tr_SPG_Torls("T-10", Armament.Tr_C_ANTI, Armament.UNARMED, Armament.UNARMED, 1.0F, 1.5F, 20, 6, 500, true, Nations.Tervakseyr),

	//Tr_CInf("Torr (*)", Armament.Tr_TR1, Armament.UNARMED, Armament.Tr_LMG2HE, 1.1F, 0.6F, 8, 3, 0, false, Nations.Tervakseyr),
	//Tr_IFV_T82_CV("T-82 (*)", Armament.Tz_C_1944, Armament.UNARMED, Armament.Tr_LMGHE, 0.7F, 1.3F, 10, 12, 300, true, Nations.Tervakseyr),
	//Tr_TK_C1980_CV("T-1980 (*)", Armament.Tz_C_1978, Armament.UNARMED, Armament.Tr_LMGHE, 0.7F, 1.3F, 20, 8, 600, true, Nations.Tervakseyr),
	
	//Pusmania
	Pu_Pulupus("Pulujilu Püse", Armament.NATO_M4, Armament.Tz_Sgom, Armament.Tz_SAW33HE, 0.7F, 0.9F, 10, 4, 2, false, Nations.Pusmania),
	Pu_RPOL("Püse", Armament.Pu_Bow, Armament.Tz_Sgom, Armament.Pu_PM4, 0.8F, 0.9F, 12, 4, 0, false, Nations.Pusmania),
	Pu_SPOL("Pusmania Püse", Armament.Pu_PM4, Armament.Tz_Sgom, Armament.Tz_SAW33HE, 0.8F, 0.9F, 12, 4, 0, false, Nations.Pusmania),
	Pu_Windpus("Plahjli Püse", Armament.Pu_Windbuchse, Armament.Tz_Sgom2, Armament.Tr_LMG2HE, 1.6F, 0.8F, 8, 4, 2, false, Nations.Pusmania),
	Pu_Sturmpus("Pusjotky Püse", Armament.Tz_VPL, Armament.Tz_Sgom2, Armament.Tz_SAW34HE, 1.4F, 0.8F, 8, 4, 10, false, Nations.Pusmania),
	Pu_Pwees("Pwëëse", Armament.Pwees_LASER, Armament.Tz_Sgom2, Armament.UNARMED, 1.2F, 0.8F, 8, 4, 10, false, Nations.Pusmania),
	
	Pu_APC_Pusbus("P-1 Pus Bus", Armament.Tz_SAW33HE, Armament.UNARMED, Armament.UNARMED, 0.7F, 1.3F, 20, 12, 30, true, Nations.Pusmania),
	//Pu_APC_P9("Pushumvee", Armament.Tz_SAW33HE, Armament.UNARMED, Armament.UNARMED, 0.8F, 1.3F, 20, 12, 15, true, Nations.Pusmania),
	
	//Pu_IFV_M2("M2(P) Pusbradley", Armament.NATO_V_Bushmaster, Armament.UNARMED, Armament.Tz_SAW33HE, 0.8F, 1.3F, 10, 8, 70, true, Nations.Pusmania),
	
	Pu_Tk_C1980MP("P-1980M(P) Pwees Entity", Armament.Pu_C_2010, Armament.UNARMED, Armament.Tz_VK44, 1.1F, 1.5F, 20, 9, 600, true, Nations.Pusmania),
	Pu_Tk_C1980M("P-1980M Pusha", Armament.Tz_C_1978, Armament.UNARMED, Armament.Tz_VK44, 1.0F, 1.5F, 20, 9, 600, true, Nations.Pusmania),
	Pu_Tk_C1980("P-1980 Pweesha", Armament.Tz_C_1978, Armament.UNARMED, Armament.Tz_VK44, 0.9F, 1.5F, 20, 10, 550, true, Nations.Pusmania),
	Pu_Tk_C1944M4("P-1944M4 Pulajie", Armament.Tz_C_1964, Armament.UNARMED, Armament.Tz_VK44, 0.9F, 1.5F, 20, 8, 350, true, Nations.Pusmania),
	Pu_Tk_C1944("P-1944 Pouha", Armament.Tz_C_1944, Armament.UNARMED, Armament.Tz_VK44, 0.7F, 1.5F, 20, 8, 300, true, Nations.Pusmania),
	
	//Pu_IFV_C1929("P3B Pusma", Armament.Pu_C_75m, Armament.UNARMED, Armament.Tz_SAW33HE, 0.7F, 1.1F, 20, 12, 150, true, Nations.Pusmania),
	
	//Pu_Tk_M1A1("M1A1HA(P) Pusabrams (*)", Armament.NATO_C_M256, Armament.Tz_SAW33HE, Armament.Tz_VK44, 1.3F, 1.5F, 20, 10, 700, true, Nations.Pusmania),
	//Pu_Tk_P1A1("P1A1 Pusabrams", Armament.Tz_C_1978, Armament.Tz_SAW33HE, Armament.Tz_VK44, 1.3F, 1.5F, 20, 10, 700, true, Nations.Pusmania),
	
	//FSGUSH
	Fs_Clones("Ferwersh Clones", Armament.Fs_WITHA, Armament.Fs_Touncer, Armament.Fs_WITHT, 1.0F, 1.2F, 8, 3, 8, false, Nations.FSgush),
	//Fs_Heavy("Heavy Ferwersh Clones", Armament.Fs_WITHA, Armament.Fs_Touncer, Armament.Fs_WITHT, 1.0F, 1.2F, 8, 2, 30, false, Nations.FSgush),
	//Fs_Originals("Original Ferwershs", Armament.Fs_WITHA, Armament.Fs_Touncer, Armament.Fs_DB, 3.0F, 0.2F, 2, 5, 60, false, Nations.FSgush),
	//Fs_IFV_Machine("Wershy Touncer Machine", Armament.Fs_C_Touncer, Armament.UNARMED, Armament.Fs_WITHT, 1.0F, 1.2F, 75, 6, 400, true, Nations.FSgush), 
	
	//NATO
	//USA_M1A2("M1A2 Abrams TUSK", Armament.NATO_C_M2562, Armament.UNARMED, Armament.UNARMED, 1.4F, 1.5F, 20, 10, 900, true, Nations.USA),
	//USA_M1A1HC("M1A1HC Abrams", Armament.NATO_C_M256, Armament.UNARMED, Armament.UNARMED, 1.3F, 1.5F, 20, 10, 750, true, Nations.USA),
	//USA_M1A1("M1A1 Abrams", Armament.NATO_C_M256, Armament.UNARMED, Armament.UNARMED, 1.3F, 1.5F, 20, 10, 550, true, Nations.USA),
	
	;
	public float camo; 
	public float optics;
	public final int armor_value;
	public final int unit_size;
	public final Armament stdWeap;
	public final Armament stdAT;
	public final Armament stdSAW;
	public final String name;
	public final boolean vehicle;
	public final double movement;
	
	public final Nations nation;
	
	/**
	 * @param Name = unit name
	 * @param Armament = base armament to be equipped to unit
	 * @param optics = optics
	 * @param camo = stealth value
	 * @param unit_size = how many soldiers at start
	 * @param armor_value = effective front armor of unit (in mm) 
	 * @param vehicle = is this a vehicle?
	 * */
	Units(String name, Armament a, Armament b, Armament c, float optics, float camo, int unit_size, int speed, int armor_value, boolean vehicle, Nations nation)
	{
		this.name = name;
		this.stdWeap = a;
		this.stdAT = b;
		this.stdSAW = c;
		this.optics = optics;
		this.armor_value = armor_value;
		this.unit_size = unit_size;
		this.camo = camo;
		this.vehicle = vehicle;
		this.movement = speed*1.25;
		
		this.nation = nation;
	}
}
