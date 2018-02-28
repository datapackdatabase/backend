package com.datapack.database.DatapackDatabase.datapack;

import java.util.ArrayList;

public class ModelCache {
	
	public ModelCache() {
		hoes = new int[]{1561, 250, 131, 32, 59};
		swords = new int[]{1561, 250, 131, 32, 59};
		shovels = new int[]{1561, 250, 131, 32, 59};
		axes = new int[]{1561, 250, 131, 32, 59};
		pickaxes = new int[]{1561, 250, 131, 32, 59};
		
		helmets = new int[]{363, 165, 165, 77, 55, 275};
		chestplates = new int[]{528, 240, 240, 112, 80};
		leggings = new int[]{495, 225, 225, 105, 75};
		boots = new int[]{429, 195, 195, 91, 64};
		
		elytra = 431;
		shears = 237;
		fishing_rod = 64;
		flint_and_steel = 64;
		shield = 336;
		bow = 384;
		trident = 250;
	}
	
	//diamond, iron, stone, gold, wood
	private int[] hoes;
	private int[] swords;
	private int[] shovels;
	private int[] axes;
	private int[] pickaxes;
	
	//diamond, iron, chainmail, gold, leather, turtle
	private int[] helmets;
	private int[] chestplates;
	private int[] leggings;
	private int[] boots;
	
	private int elytra;
	private int shears;
	private int fishing_rod;
	private int flint_and_steel;
	private int shield;
	private int bow;
	private int trident;
	
	private int carrot_on_a_stick;
	
	
	/**
	 * Returns if specified item has available slots left for models.
	 */
	public boolean isItemAvailable(String item) {
		return getRemainingSlots(item) > 0;
	}
	
	/**
	 * Gets the preferred item for filling in models. Starts with hoes, then works down the list (order of variable declarations).
	 */
	public String getPreferedItem() {
		String out = null;
		
		if(sum(hoes) > 0) {
			if(hoes[4] > 0) out = "wooden_hoe";
			if(hoes[3] > 0) out = "golden_hoe";
			if(hoes[2] > 0) out = "stone_hoe";
			if(hoes[1] > 0) out = "iron_hoe";
			if(hoes[0] > 0) out = "diamond_hoe";
			
		} else if(sum(swords) > 0) {
			if(swords[4] > 0) out = "wooden_sword";
			if(swords[3] > 0) out = "golden_sword";
			if(swords[2] > 0) out = "stone_sword";
			if(swords[1] > 0) out = "iron_sword";
			if(swords[0] > 0) out = "diamond_sword";
			
		} else if(sum(shovels) > 0) {
			if(shovels[4] > 0) out = "wooden_shovel";
			if(shovels[3] > 0) out = "golden_shovel";
			if(shovels[2] > 0) out = "stone_shovel";
			if(shovels[1] > 0) out = "iron_shovel";
			if(shovels[0] > 0) out = "diamond_shovel";
			
		} else if(sum(axes) > 0) {
			if(axes[4] > 0) out = "wooden_axe";
			if(axes[3] > 0) out = "golden_axe";
			if(axes[2] > 0) out = "stone_axe";
			if(axes[1] > 0) out = "iron_axe";
			if(axes[0] > 0) out = "diamond_axe";
			
		} else if(sum(pickaxes) > 0) {
			if(pickaxes[4] > 0) out = "wooden_pickaxe";
			if(pickaxes[3] > 0) out = "golden_pickaxe";
			if(pickaxes[2] > 0) out = "stone_pickaxe";
			if(pickaxes[1] > 0) out = "iron_pickaxe";
			if(pickaxes[0] > 0) out = "diamond_pickaxe";
			
		} else if(sum(helmets) > 0) {
			if(helmets[5] > 0) out = "turtle_helmet";
			if(helmets[4] > 0) out = "leather_helmet";
			if(helmets[3] > 0) out = "golden_helmet";
			if(helmets[2] > 0) out = "chainmail_helmet";
			if(helmets[1] > 0) out = "iron_helmet";
			if(helmets[0] > 0) out = "diamond_helmet";
			
		} else if(sum(chestplates) > 0) {
			if(chestplates[4] > 0) out = "leather_chestplate";
			if(chestplates[3] > 0) out = "golden_chestplate";
			if(chestplates[2] > 0) out = "chainmail_chestplate";
			if(chestplates[1] > 0) out = "iron_chestplate";
			if(chestplates[0] > 0) out = "diamond_chestplate";
			
		} else if(sum(leggings) > 0) {
			if(leggings[4] > 0) out = "leather_leggings";
			if(leggings[3] > 0) out = "golden_leggings";
			if(leggings[2] > 0) out = "chainmail_leggings";
			if(leggings[1] > 0) out = "iron_leggings";
			if(leggings[0] > 0) out = "diamond_leggings";
			
		} else if(sum(boots) > 0) {
			if(boots[4] > 0) out = "leather_boots";
			if(boots[3] > 0) out = "golden_boots";
			if(boots[2] > 0) out = "chainmail_boots";
			if(boots[1] > 0) out = "iron_boots";
			if(boots[0] > 0) out = "diamond_boots";
			
		} else {
			if(elytra > 0) 			out = "elytra";
			if(shears > 0) 			out = "shears";
			if(fishing_rod > 0) 	out = "fishing_rod";
			if(flint_and_steel > 0) out = "flint_and_steel";
			if(shield > 0) 			out = "shield";
			if(bow > 0) 			out = "bow";
			if(trident > 0) 		out = "trident";
		}
		
		
		return out;
	}
	
	private int sum(int[] array) {
		int out = 0;
		for(int e:array)
			out += e;
		return out;
	}

	/**
	 * Gets the number of remaining model slots for specified item.
	 */
	public int getRemainingSlots(String item) {
		int out = Integer.MIN_VALUE;
		
		if(item.contains("minecraft:")) {
			item = item.substring(10);
		}
		
		if(item.contains("_hoe")) {
			switch (item) {
				case "diamond_hoe": out = hoes[0]; break;
				case "iron_hoe": 	out = hoes[1]; break;
				case "stone_hoe": 	out = hoes[2]; break;
				case "golden_hoe": 	out = hoes[3]; break;
				case "wooden_hoe": 	out = hoes[4]; break;
			}
		} else if(item.contains("_sword")) {
			switch (item) {
				case "diamond_sword": 	out = swords[0]; break;
				case "iron_sword": 		out = swords[1]; break;
				case "stone_sword": 	out = swords[2]; break;
				case "golden_sword": 	out = swords[3]; break;
				case "wooden_sword": 	out = swords[4]; break;
			}
		} else if(item.contains("_shovel")) {
			switch (item) {
				case "diamond_shovel": 	out = shovels[0]; break;
				case "iron_shovel": 	out = shovels[1]; break;
				case "stone_shovel": 	out = shovels[2]; break;
				case "golden_shovel": 	out = shovels[3]; break;
				case "wooden_shovel": 	out = shovels[4]; break;
			}
		} else if(item.contains("_axe")) {
			switch (item) {
				case "diamond_axe": out = axes[0]; break;
				case "iron_axe": 	out = axes[1]; break;
				case "stone_axe": 	out = axes[2]; break;
				case "golden_axe": 	out = axes[3]; break;
				case "wooden_axe": 	out = axes[4]; break;
			}
		} else if(item.contains("_pickaxe")) {
			switch (item) {
				case "diamond_pickaxe": out = pickaxes[0]; break;
				case "iron_pickaxe": 	out = pickaxes[1]; break;
				case "stone_pickaxe": 	out = pickaxes[2]; break;
				case "golden_pickaxe": 	out = pickaxes[3]; break;
				case "wooden_pickaxe": 	out = pickaxes[4]; break;
			}
		} else if(item.contains("_helmet")) {
			switch (item) {
				case "diamond_helmet": 	out = helmets[0]; break;
				case "iron_helmet": 	out = helmets[1]; break;
				case "chainmail_helmet":out = helmets[2]; break;
				case "golden_helmet": 	out = helmets[3]; break;
				case "leather_helmet":	out = helmets[4]; break;
				case "turtle_helmet":	out = helmets[5]; break;
			}
		} else if(item.contains("_chestplate")) {
			switch (item) {
				case "diamond_chestplate": 	out = chestplates[0]; break;
				case "iron_chestplate": 	out = chestplates[1]; break;
				case "chainmail_chestplate":out = chestplates[2]; break;
				case "golden_chestplate": 	out = chestplates[3]; break;
				case "leather_chestplate": 	out = chestplates[4]; break;
			}
		} else if(item.contains("_leggings")) {
			switch (item) {
				case "diamond_leggings": 	out = leggings[0]; break;
				case "iron_leggings": 		out = leggings[1]; break;
				case "chainmail_leggings": 	out = leggings[2]; break;
				case "golden_leggings": 	out = leggings[3]; break;
				case "leather_leggings": 	out = leggings[4]; break;
			}
		} else if(item.contains("_boots")) {
			switch (item) {
				case "diamond_boots": 	out = boots[0]; break;
				case "iron_boots": 		out = boots[1]; break;
				case "chainmail_boots": out = boots[2]; break;
				case "golden_boots": 	out = boots[3]; break;
				case "leather_boots": 	out = boots[4]; break;
			}
		} else {
			switch (item) {
				case "elytra": 				out = elytra; 			break;
				case "shears": 				out = shears; 			break;
				case "fishing_rod":			out = fishing_rod; 		break;
				case "flint_and_steel":		out = flint_and_steel; 	break;
				case "shield": 				out = shield; 			break;
				case "bow": 				out = bow; 				break;
				case "trident": 			out = trident; 			break;
				case "carrot_on_a_stick": 	out = carrot_on_a_stick;break;
			}
		}
		return out;
	}
	
	
	/**
	 * Increments number of used model slots by 'amount.'
	 * @return if the item was processed succesfully.
	 */
	public boolean addItemCount(String item, int amount) {
		if(item.contains("minecraft:")) {
			item = item.substring(10);
		}
		
		if(getRemainingSlots(item) <= 0) {
			return false;
		}
		
		if(item.contains("_hoe")) {
			switch (item) {
				case "diamond_hoe": hoes[0] -= amount; break;
				case "iron_hoe": 	hoes[1] -= amount; break;
				case "stone_hoe": 	hoes[2] -= amount; break;
				case "golden_hoe": 	hoes[3] -= amount; break;
				case "wooden_hoe": 	hoes[4] -= amount; break;
			}
		} else if(item.contains("_sword")) {
			switch (item) {
				case "diamond_sword": 	swords[0] -= amount; break;
				case "iron_sword": 		swords[1] -= amount; break;
				case "stone_sword": 	swords[2] -= amount; break;
				case "golden_sword": 	swords[3] -= amount; break;
				case "wooden_sword": 	swords[4] -= amount; break;
			}
		} else if(item.contains("_shovel")) {
			switch (item) {
				case "diamond_shovel": 	shovels[0] -= amount; break;
				case "iron_shovel": 	shovels[1] -= amount; break;
				case "stone_shovel": 	shovels[2] -= amount; break;
				case "golden_shovel": 	shovels[3] -= amount; break;
				case "wooden_shovel": 	shovels[4] -= amount; break;
			}
		} else if(item.contains("_axe")) {
			switch (item) {
				case "diamond_axe": axes[0] -= amount; break;
				case "iron_axe": 	axes[1] -= amount; break;
				case "stone_axe": 	axes[2] -= amount; break;
				case "golden_axe": 	axes[3] -= amount; break;
				case "wooden_axe": 	axes[4] -= amount; break;
			}
		} else if(item.contains("_pickaxe")) {
			switch (item) {
				case "diamond_pickaxe": pickaxes[0] -= amount; break;
				case "iron_pickaxe": 	pickaxes[1] -= amount; break;
				case "stone_pickaxe": 	pickaxes[2] -= amount; break;
				case "golden_pickaxe": 	pickaxes[3] -= amount; break;
				case "wooden_pickaxe": 	pickaxes[4] -= amount; break;
			}
		} else if(item.contains("_helmet")) {
			switch (item) {
				case "diamond_helmet": 	helmets[0] -= amount; break;
				case "iron_helmet": 	helmets[1] -= amount; break;
				case "chainmail_helmet":helmets[2] -= amount; break;
				case "golden_helmet": 	helmets[3] -= amount; break;
				case "leather_helmet": 	helmets[4] -= amount; break;
				case "turtle_helmet": 	helmets[5] -= amount; break;
			}
		} else if(item.contains("_chestplate")) {
			switch (item) {
				case "diamond_chestplate": 	chestplates[0] -= amount; break;
				case "iron_chestplate": 	chestplates[1] -= amount; break;
				case "chainmail_chestplate":chestplates[2] -= amount; break;
				case "golden_chestplate": 	chestplates[3] -= amount; break;
				case "leather_chestplate": 	chestplates[4] -= amount; break;
			}
		} else if(item.contains("_leggings")) {
			switch (item) {
				case "diamond_leggings": 	leggings[0] -= amount; break;
				case "iron_leggings": 		leggings[1] -= amount; break;
				case "chainmail_leggings": 	leggings[2] -= amount; break;
				case "golden_leggings": 	leggings[3] -= amount; break;
				case "leather_leggings": 	leggings[4] -= amount; break;
			}
		} else if(item.contains("_boots")) {
			switch (item) {
				case "diamond_boots": 	boots[0] -= amount; break;
				case "iron_boots": 		boots[1] -= amount; break;
				case "chainmail_boots": boots[2] -= amount; break;
				case "golden_boots": 	boots[3] -= amount; break;
				case "leather_boots": 	boots[4] -= amount; break;
			}
		} else {
			switch (item) {
				case "elytra": 				elytra -= amount; 			break;
				case "shears": 				shears -= amount; 			break;
				case "fishing_rod":			fishing_rod -= amount; 		break;
				case "flint_and_steel": 	flint_and_steel -= amount; 	break;
				case "shield": 				shield -= amount; 			break;
				case "bow":					bow -= amount; 				break;
				case "trident": 			trident += amount; 			break;
				case "carrot_on_a_stick": 	carrot_on_a_stick -= amount;break;
			}
		}
		return true;
	}
	
	
	/**
	 * Sorts items into model slots.
	 * @param packs
	 * @return
	 */
	public boolean sortModels(Datapack[] packs) {
		long totalModels = 0;
		
		ArrayList<ItemData> unlocked = new ArrayList<ItemData>();
		ArrayList<ItemData> locked1 = new ArrayList<ItemData>();
		ArrayList<ItemData> locked2 = new ArrayList<ItemData>();
		ArrayList<ItemData> locked3 = new ArrayList<ItemData>();
		ArrayList<ItemData> lockedOther = new ArrayList<ItemData>();
		ArrayList<ItemData> lockedDamage = new ArrayList<ItemData>();
		
		for(Datapack pack:packs) {
			for(ItemData item: pack.getItems()) {
				totalModels++;
				
				if(item.isLocked())
					item.processTypes();
				
				if(item.getDamage() > 0) {
					lockedDamage.add(item);
				} else if(!item.isLocked()) {
					unlocked.add(item);
				} else if(item.amountOfLocks() == 1) {
					locked1.add(item);
				} else if(item.amountOfLocks() == 2) {
					locked2.add(item);
				} else if(item.amountOfLocks() == 3) {
					locked3.add(item);
				} else {
					lockedOther.add(item);
				}
			}
		}
		
		System.out.println("Total items located: " + totalModels);
		
		
		long errorNoModelAvaiable = 0;
		
		//TODO improve damage locked items. Messy and not very good.
		for(ItemData item: lockedDamage) {
			item.setAssignedDamage(item.getDamage());
			item.setAssignedItem((item.getLocks()[0]));
		}
		for(int i = 0; i < lockedDamage.size(); i++) {
			for(int j = i+1; j < lockedDamage.size(); j++) {
				if(lockedDamage.get(i).getAssignedItem().equals(lockedDamage.get(j).getAssignedItem()) && lockedDamage.get(i).getAssignedDamage() == lockedDamage.get(j).getAssignedDamage()) {
					System.out.println("Warning: Conflicting damage lock on " + lockedDamage.get(i).getItem() + ". Using default model.");
				}
			}
		}
		
		
		
		errorNoModelAvaiable += sortItems(locked1,lockedDamage);
		errorNoModelAvaiable += sortItems(locked2,lockedDamage);
		errorNoModelAvaiable += sortItems(locked3,lockedDamage);
		errorNoModelAvaiable += sortItems(lockedOther,lockedDamage);
		
		for(ItemData item: unlocked) {
			while(item.getAssignedItem() == null) {
				String lock = getPreferedItem();
				if(lock != null) {
					setItem(item, lock, lockedDamage);
				} else {
					errorNoModelAvaiable++;
					System.out.println("Warning: no model slots available for " + item.getItem() + ". Using default model.");
					item.setAssignedItem("diamond_hoe");
					item.setAssignedDamage(0);
				}
			}
		}
		
		
		
		if(errorNoModelAvaiable > 0) {
			System.out.println("Warning: failed to load " + errorNoModelAvaiable + " items. Using default models.");
		}
		
		System.out.println("Total models loaded: " + (totalModels - errorNoModelAvaiable));
		
		return true;
	}
	
	private long sortItems(ArrayList<ItemData> list, ArrayList<ItemData> lockedDamage) {
		long errorNoModelAvaiable = 0;
		
		for(ItemData item: list) {
			boolean success = false;
			for(String lock: item.getLocks()) {
				if(!success) {
					if(setItem(item, lock, lockedDamage)) {
						success = true;
					}
				}
			}
			
			if(!success && getRemainingSlots(item.getLocks()[0]) == Integer.MIN_VALUE) {
				errorNoModelAvaiable++;
				System.out.println("Warning: invalid type(s) found on " + item.getItem() + ". Using default model.");
				item.setAssignedItem("diamond_hoe");
				item.setAssignedDamage(0);
			} else if(!success) {
				errorNoModelAvaiable++;
				System.out.println("Warning: no model slots available for " + item.getItem() + ". Using default model.");
				item.setAssignedItem(item.getLocks()[0]);
				item.setAssignedDamage(0);
			}
		}
		return errorNoModelAvaiable;
	}
	
	private boolean setItem(ItemData item, String lock, ArrayList<ItemData> lockedDamage) {
		boolean out = false;
		while(out) {
			out = false;
			for(ItemData lockItem: lockedDamage) {
				if(lock.equals(lockItem.getAssignedItem())) {
					if(getRemainingSlots(lock) == lockItem.getAssignedDamage()) {
						out = true;
						addItemCount(lock,1);
					}
				}
			}
		}
		out = false;
		if(addItemCount(lock,1)) {
			item.setAssignedItem(lock);
			item.setAssignedDamage(getRemainingSlots(lock) + 1);
			out = true;
		}
		return out;
	}
}
