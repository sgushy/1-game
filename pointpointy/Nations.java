package pointpointy;

public enum Nations {
	Pwees("Aliens", "Pw", 0),
	
	Tunzen("Tunzen","Tz", 1),

	Tervakseyr("Tervakseyr","Tr", 2),

	FSgush("Ferwershy Sgush","Fs", 3),
	
	USA("NATO", "US", 4),
	
	Pusmania("Pusmania", "Pu", 5),
	
	Ferwersh("Ferwershland", "Fw", 6),
	
	
	;
	public String name; 
	public String abbrev;
	public int ID;
	Nations(String name, String abbrev, int id)
	{
		this.name = name;
		this.abbrev = abbrev;
		this.ID = id;
	}

	/*public static Nations getNation(int national) {

		for(int i = 0; i< Nations.values().length; i++)
		{
			if(Nations.values()[i].ID == national)
			{
				return Nations.values()[i];
			}
		}
		return Nations.Pwees;
	}*/
	
	public Names getNameRand()
	{
		Names[] n2 = new Names[Names.values().length];
		int index = 0;
		for(int n = 0; n < Names.values().length; n++)
		{
			if(Names.values()[n].nID == this.ID)
			{
				n2[index] = Names.values()[n];
				index++;
			}
		}
		for(int n = 0; n< n2.length; n++)
		{
			int p =SMain.RNG.nextInt(n2.length);
			Names name = Names.values()[p];
			if(name.nID == this.ID)
				return Names.values()[p];
		}
		return Names.UNKNOWN;
	}
	
	public Sounds affirmSound()
	{
		if(this.abbrev == "Tz")
		{
			if(Math.random() < 0.33)
			{
				return Sounds.Tz_yes1;
			}
			else if(Math.random() <0.67)
			{
				return Sounds.Tz_yes2;
			}
			else
			{
				return Sounds.Tz_yes3;
			}
		}
		else if(this.abbrev == "Tr")
		{
			if(Math.random() < 0.33)
			{
				return Sounds.Tr_yes1;
			}
			else if(Math.random() <0.67)
			{
				return Sounds.Tr_yes2;
			}
			else
			{
				return Sounds.Tr_yes3;
			}
		}
		else if(this.abbrev == "Pw")
		{
			if(Math.random() < 0.33)
			{
				return Sounds.Pw_1;
			}
			else
			{
				return Sounds.Pw_3;
			}
		}
		else
			return Sounds.Static;
	}
	public Sounds deathSound()
	{
		if(this.abbrev == "Tz")
		{
			return Sounds.Tz_Unit_Down;
		}
		else if(this.abbrev == "Tr")
		{
			return Sounds.Tr_Unit_Down;
		}
		else if(this.abbrev == "Pw")
		{
			if(Math.random() < 0.5)
			{
				return Sounds.Pw_5;
			}
			else
			{
				return Sounds.Pw_6;
			}
		}
		else
			return Sounds.Static;
	}
	public Sounds orderSound()
	{
		if(this.abbrev == "Tz")
		{
			if(Math.random() < 0.33)
			{
				return Sounds.Tz_order;
			}
			else
			{
				return Sounds.Tz_sitn;
			}
		}
		else if(this.abbrev == "Tr")
		{
			if(Math.random() < 0.33)
			{
				return Sounds.Tr_order;
			}
			else
			{
				return Sounds.Tr_sitn;
			}
		}
		else if(this.abbrev == "Pw")
		{
			if(Math.random() < 0.33)
			{
				return Sounds.Pw_3;
			}
			else
			{
				return Sounds.Pw_2;
			}
		}
		else
			return Sounds.Static;
	}
	public Sounds noSound()
	{
		if(this.abbrev == "Tz")
		{
			if(Math.random() < 0.33)
			{
				return Sounds.Tz_no1;
			}
			else if(Math.random() <0.67)
			{
				return Sounds.Tz_no2;
			}
		}
		else if(this.abbrev == "Tr")
		{
			if(Math.random() < 0.33)
			{
				return Sounds.Tr_no1;
			}
			else
			{
				return Sounds.Tr_no2;
			}
		}
		else if(this.abbrev == "Pw")
		{
			if(Math.random() < 0.33)
			{
				return Sounds.Pw_2;
			}
			else
			{
				return Sounds.Pw_4;
			}
		} 
		return  Sounds.Static;
	}

	public Sounds tagSound() {
		if(this.abbrev == "Tz")
		{
			return Sounds.Tz_tag;
		}
		else if(this.abbrev == "Tr")
		{
			return Sounds.Tr_tag;
		}
		else if(this.abbrev == "Pw")
		{
			return Sounds.Pw_5;
		}
		else
			return Sounds.Static;
	}

}
