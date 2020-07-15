package com.deadshotmdf.EnderChestVault.Utils;

import java.util.HashMap;
import java.util.UUID;

public class Maps {
	
	public static HashMap<UUID,Integer> currentOpenLevel = new HashMap<UUID,Integer>();
	
	public static HashMap<UUID,Integer> getCurrentOpenLevel(){
		return currentOpenLevel;
	}

}
