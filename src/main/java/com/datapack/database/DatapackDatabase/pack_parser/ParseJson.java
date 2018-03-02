package com.datapack.database.DatapackDatabase.pack_parser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.datapack.database.DatapackDatabase.datapack.Datapack;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ParseJson {
	
	/**
	 * Loads package.json from specified .zip file.
	 */
	public static Datapack parse(File file) {
		Datapack pack = null;
		
		try {
			FileInputStream fis = new FileInputStream(file);
			ZipInputStream zin = new ZipInputStream( new BufferedInputStream(fis) );
			
			ZipEntry packJson = zin.getNextEntry();
			System.out.println("name: " + packJson.getName());
			while(packJson != null && !packJson.getName().equals("package.json")) {
				packJson = zin.getNextEntry();
			}
			
			if(packJson != null) {
				Reader reader = new InputStreamReader(zin, "UTF-8");
				Gson gson = new GsonBuilder().create();
				pack = gson.fromJson(reader, Datapack.class);
			
				System.out.println("Loaded package.json for " + pack.getName());
			} else {
				System.out.println("Failed to load package.json at " + file.getName() + ".");
			}
			fis.close();
			
		} catch (Exception e) {
			System.out.println("Failed to load package.json at " + file.getName() + ".");
			return null;
		}
		
		return pack;
	}
	
	
	/**
	 * Verifies package.json structure and insures everything is loaded correctly.
	 */
	public static boolean verifyJson(Datapack[] packs) {
		boolean out = true;
		//pack and item validation
		for(Datapack pack:packs) {
			//verifies pack loaded correctly
			if(pack == null) {
				System.out.println("Error: Failed to load one or more Datapack's package.json. Verify json syntax.");
				out = false;
			} else {
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
		}
		return out;
	}
	
}
