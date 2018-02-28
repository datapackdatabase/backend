package com.datapack.database.DatapackDatabase.datapack;

import java.util.ArrayList;

public class ItemData {
	
	private String id;
	
	private String model;
	
	private String[] types;
	private boolean keepObjectAttributes;
	private boolean blacklist;
	
	private int damage;
	
	private String assignedItem;
	private int assignedDamage;
	
	
	
	public String getItem() {
		return id;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public String getModel() {
		if(model != null)
			return model;
		else
			return "item/" + id;
	}
	
	public boolean isLocked() {
		return types != null && types.length > 0;
	}
	
	public String[] getLocks() {
		return types;
	}
	
	public int amountOfLocks() {
		return types.length;
	}
	
	public boolean getFlatlined() {
		return !keepObjectAttributes;
	}
	
	public boolean getBlacklist() {
		return blacklist;
	}
	
	
	
	public void setAssignedItem(String item) {
		assignedItem = item;
	}
	
	public void setAssignedDamage(int damage) {
		assignedDamage = damage;
	}
	
	public String getAssignedItem() {
		return assignedItem;
	}
	
	public int getAssignedDamage() {
		return assignedDamage;
	}
	
	
	/**
	 * Verifies json syntax for required fields.
	 */
	public boolean validate(String datapack) {
		boolean out = true;
		if(id == null) {
			out = false;
			System.out.println("Error: Failed to parse an item in " + datapack + "'s package.json. Appears to be missing an id.");
		}
		if(damage > 0 && !isLocked()) {
			System.out.println("Error: Failed to parse + id + in " + datapack + "'s package.json. Damage is specified, but required field types is not specified.");
		}
		return out;
	}
	
	
	
	/**
	 * Converts special type cases to standard minecraft ids
	 */
	public void processTypes() {
		if(!isLocked())
			return;
		
		ArrayList<String> types = new ArrayList<String>();
		for(String type: this.types) {
			if(type.contains("minecraft:")) {
				type = type.substring(10);
			}
			
			if(type.contains("hoe")) {
				addTypesTools(type, "hoe", types);
				
			} else if(type.contains("sword")) {
				addTypesTools(type, "sword", types);
				
			} else if(type.contains("shovel")) {
				addTypesTools(type, "shovel", types);
				
			} else if(type.contains("axe")) {
				addTypesTools(type, "axe", types);
				
			} else if(type.contains("pickaxe")) {
				addTypesTools(type, "pickaxe", types);
				
			} else if(type.contains("helmet")) {
				addTypesArmor(type, "helmet", types);
				
			} else if(type.contains("chestplate")) {
				addTypesArmor(type, "chestplate", types);
				
			} else if(type.contains("leggings")) {
				addTypesArmor(type, "leggings", types);
				
			} else if(type.contains("boots")) {
				addTypesArmor(type, "boots", types);
				
			} else {
				switch (type) {
					case "elytra": 				types.add("elytra");			break;
					case "shears": 				types.add("shears"); 			break;
					case "fishing_rod":			types.add("fishing_rod"); 		break;
					case "flint_and_steel": 	types.add("flint_and_steel"); 	break;
					case "shield": 				types.add("shield");			break;
					case "bow":					types.add("bow"); 				break;
					case "trident": 			types.add("trident"); 			break;
					case "carrot_on_a_stick": 	types.add("carrot_on_a_stick");	break;
					default: 					types.add(type); 				break;
				}
			}
		}
		
		if(blacklist)
			types = invertTypes(types);
		else
			types = removeDuplicates(types);
		
		this.types = new String[types.size()];		
		for(int i = 0; i < types.size(); i++) {
			this.types[i] = types.get(i);
		}
	}
	
	
	
	private void addTypesTools(String item, String type,  ArrayList<String> list) {		
		if(item.equals(type + ".diamond")) list.add("diamond_" + type);
		else if(item.equals(type + ".iron")) list.add("iron_" + type);
		else if(item.equals(type + ".stone")) list.add("stone_" + type);
		else if(item.equals(type + ".gold")) list.add("golden_" + type);
		else if(item.equals(type + ".wood")) list.add("wooden_" + type);
		
		else if(item.equals(type + ".*")) { list.add("diamond_" + type); list.add("iron_" + type); list.add("stone_" + type); list.add("golden_" + type); list.add("wooden_" + type); }
		else if(item.equals(type)) { 		list.add("diamond_" + type); list.add("iron_" + type); list.add("stone_" + type); list.add("golden_" + type); list.add("wooden_" + type); }
		
		else list.add(item);
	}
	
	private void addTypesArmor(String item, String type,  ArrayList<String> list) {		
		if(item.equals(type + ".diamond")) list.add("diamond_" + type);
		else if(item.equals(type + ".iron")) list.add("iron_" + type);
		else if(item.equals(type + ".chain")) list.add("chainmail_" + type);
		else if(item.equals(type + ".gold")) list.add("golden_" + type);
		else if(item.equals(type + ".leather")) list.add("leather_" + type);
		else if(item.equals(type + ".turtle")) list.add("turtle_" + type);
		
		else if(item.equals(type + ".*")) { list.add("diamond_" + type); list.add("iron_" + type); list.add("chainmail_" + type); list.add("golden_" + type); list.add("leather_" + type); }
		else if(item.equals(type)) { 		list.add("diamond_" + type); list.add("iron_" + type); list.add("chainmail_" + type); list.add("golden_" + type); list.add("leather_" + type); }
		
		else list.add(item);
	}
	
	private ArrayList<String> invertTypes(ArrayList<String> list) {
		ArrayList<String> out = new ArrayList<String>();
		out.add("diamond_hoe"); out.add("iron_hoe"); out.add("stone_hoe"); out.add("golden_hoe"); out.add("wooden_hoe");
		out.add("diamond_sword"); out.add("iron_sword"); out.add("stone_sword"); out.add("golden_sword"); out.add("wooden_sword");
		out.add("diamond_shovel"); out.add("iron_shovel"); out.add("stone_shovel"); out.add("golden_shovel"); out.add("wooden_shovel");
		out.add("diamond_axe"); out.add("iron_axe"); out.add("stone_axe"); out.add("golden_axe"); out.add("wooden_axe");
		out.add("diamond_pickaxe"); out.add("iron_pickaxe"); out.add("stone_pickaxe"); out.add("golden_pickaxe"); out.add("wooden_pickaxe");
		
		out.add("diamond_helmet"); out.add("iron_helmet"); out.add("chainmail_helmet"); out.add("golden_helmet"); out.add("leather_helmet"); out.add("turtle_helmet");
		out.add("diamond_chestplate"); out.add("iron_chestplate"); out.add("chainmail_chestplate"); out.add("golden_chestplate"); out.add("leather_chestplate");
		out.add("diamond_leggings"); out.add("iron_leggings"); out.add("chainmail_leggings"); out.add("golden_leggings"); out.add("leather_leggings");
		out.add("diamond_boots"); out.add("iron_boots"); out.add("chainmail_boots"); out.add("golden_boots"); out.add("leather_boots");
		
		out.add("elytra");
		out.add("shears");
		out.add("fishing_rod");
		out.add("flint_and_steel");
		out.add("shield");
		out.add("bow");
		out.add("trident");
		out.add("carrot_on_a_stick");
		
		for(String e:list) {
			for(int i = 0; i < out.size(); i++) {
				if(out.get(i).equals(e)) {
					out.remove(i);
				}
			}
		}
		return out;
	}
	
	private ArrayList<String> removeDuplicates(ArrayList<String> list) {
		ArrayList<String> out = new ArrayList<String>();
		for(String e:list) {
			boolean hasType = false;
			for(String i:out) {
				if(e.equals(i)) {
					hasType = true;
				}
			}
			if(!hasType) {
				out.add(e);
			}
		}
		return out;
	}
	
	
}
