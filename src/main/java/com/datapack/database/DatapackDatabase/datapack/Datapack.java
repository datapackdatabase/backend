package com.datapack.database.DatapackDatabase.datapack;

public class Datapack {
	
	private String name;
	private String pack_version;
	private String minecraft_version;
	
	private ItemData[] models;
	
	
	
	public String getName() {
		return name;
	}
	
	public String getPackVersion() {
		return pack_version;
	}
	
	public String getMinecraftVersion() {
		return minecraft_version;
	}
	
	public ItemData[] getItems() {
		return models;
	}
	
}
