package pointpointy;

public enum Sounds {
	//UNARMED(0, "Unarmed"),
	
	/*
	 * Weapons sounds
	 */
	SKS(2, "SKS"),
	MP40(3, "MP40"),
	AS(4, "AS"),
	VPL(5, "VPL"),
	Sgom(6, "Sgom"),
	Sgom2(7, "Sgom2"),
	Sgom3(8, "Sgom3"),
	V_20M(9, "V_20M"),
	V_FSM(10, "V_FSM"),
	V_30M(11, "V_30M"),
	C_1998(12, "C_1998"),
	Pwees_LASER(13, "Pwees_LASER"),
	DP2(14, "DP2"),
	DP3(15, "DP3"),
	Bow(16, "Bow"),
	Silenced(17, "Silenced"),
	/*
	 * Miscellaneous sound effects
	 */
	Tank_Engine(20, "Tank_Engine"),
	Vhcl_move(21, "Vhcl_move"),
	Detonate(22, "Detonate"),
	Static(23, "Static"),
	
	/*
	 * Radio speech
	 */
	Tz_yes1(30, "Tz_yes1"),
	Tz_order(31, "Tz_order"),
	Tz_dmg(32, "Tz_dmg"),
	Tz_no1(33, "Tz_no1"),
	Tz_no2(34, "Tz_no2"),
	Tz_contact(35, "Tz_contact"),
	Tz_tag(36, "Tz_tag"),
	Tz_sitn(37, "Tz_sitn"),
	Tz_yes2(38, "Tz_yes2"),
	Tz_yes3(39, "Tz_yes3"),
	Tz_attack(40, "Tz_attack"),
	Tz_Unit_Down(41, "Tz_Unit_Down"),
	
	Tr_yes1(50, "Tr_yes1"),
	Tr_order(51, "Tr_order"),
	Tr_dmg(52, "Tr_dmg"),
	Tr_no1(53, "Tr_no1"),
	Tr_no2(54, "Tr_no2"),
	Tr_contact(55, "Tr_contact"),
	Tr_tag(56, "Tr_tag"),
	Tr_sitn(57, "Tr_sitn"),
	Tr_yes2(58, "Tr_yes2"),
	Tr_yes3(59, "Tr_yes3"),
	Tr_attack(60, "Tr_attack"),
	Tr_Unit_Down(61, "Tr_Unit_Down"),
	
	FSGUSH(62, "FSGUSH"),
	
	Pw_1(70, "Pw_1"),
	Pw_2(71, "Pw_2"),
	Pw_3(72, "Pw_3"),
	Pw_4(73, "Pw_4"),
	Pw_5(74, "Pw_5"),
	Pw_6(75, "Pw_6"),
	/*
	 * Soundtracks
	 */
	PPT_Police(200, "PPT_Police"),
	PPT_Soldier(201, "PPT_Soldiers"),
	;
	public int soundID;
	public String filePath;
	
	Sounds(int id, String path)
	{
		this.soundID = id;
		this.filePath = path;
	}
	
	public static Sounds findSound(int id)
	{
		for(Sounds s : Sounds.values())
		{
			if(s.soundID == id)
			{
				return s;
			}
		}
		
		return null;
	}
}
