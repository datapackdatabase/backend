package com.datapack.database.DatapackDatabase;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

import com.datapack.database.DatapackDatabase.datapack.Datapack;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ParseJson {
	
	public static Datapack parse(File file) {        
		try {
			Reader reader = new FileReader(file);
			Gson gson = new GsonBuilder().create();
			Datapack pack = gson.fromJson(reader, Datapack.class);
			
			System.out.println("Loaded package.json for " + pack.getName());
			
			return pack;
		} catch (Exception e) {
			System.out.println("Failed to load package.json at " + file.getPath() + ".");
			return null;
		}
	}
	
	public static boolean verifyJson(Datapack[] packs) {
		boolean out = true;
		//pack and item validation
		for(Datapack pack:packs) {
			//verifies pack loaded correctly
			if(pack == null) {
				System.out.println("Error: Failed to load one or more Datapack's package.json. Verify json syntax.");
				out = false;
			}
			for(int e = 0; e < pack.getItems().length; e++) {	
				//verifies item syntax
				if(!pack.getItems()[e].validate(pack.getName())) {
					out = false;
				}
				//verifies unique item id
				for(int i = e + 1; i < pack.getItems().length; i++) {
					if(pack.getItems()[e].getItem().equals(pack.getItems()[i].getItem())) {
						System.out.println("Error: Failed to parse " + pack.getItems()[e].getItem() + " in " + pack.getName() + ". Item ID already exists in this datapack.");
						out = false;
					}
				}
			}
		}
		return out;
	}
	
}
