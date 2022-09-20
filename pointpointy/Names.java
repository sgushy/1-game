package pointpointy;

public enum Names {

	UNKNOWN(-1),
	Pwees(0),
	Pointus(1), Sgush(1), Pillowko (1), Susgus (1), Slus (1), Pointium (1), Sgonz (1), Toasten (1), Pfunz (1), Pillovz (1), Sgutz (1), Mushzto (1), Sgershinoviz (1), Slusken (1), Pfunzt (1), Tunzsgo (1), Toasto (1), Pillowtso (1), Sgum (1), Sguzl (1), Sgushovcz (1), Pillowstov(1),
	Torls (2), Terlwin(2), Bruss (2), Toaslin (2), Werton (2), Toastin (2), Toastlo (2), Bross (2), Brusster (2), Sox (2), Tervak (2), Fon (2), Ferzak (2), Ferling (2), Tors (2), Brussin (2), Tvak (2), Terlzan (2),
	FSGUSH (3),
	
	;
	
	//public final Nations nation;
	public final int nID;
	/**
	 * 
	 * @param ID of nationality, 0 for aliens, 1 for TZ, etc.
	 */
	Names(int national)
	{
		nID = national;
		//nation = Nations.getNation(national);
	}
}
