package com.datapack.database.DatapackDatabase.pack_parser;

import java.io.File;

import com.datapack.database.DatapackDatabase.datapack.Datapack;
import com.datapack.database.DatapackDatabase.datapack.ModelCache;

public class ParsePacks {
	
	//TODO: Return file location of merged packs.
	/**
	 * Parses and merges a set of datapacks given their file locations.
	 * @param read Array of datapack file locations.
	 * @return File location of merged datapacks.
	 */
	public static File parsePacks(String[] files) {
		File[] read = new File[files.length];
		for(int i = 0; i < files.length; i++) {
			read[i] = new File(files[i]);
		}
		Datapack[] packs = new Datapack[read.length];
		//read JSON of each datapack
		for(int e = 0; e < read.length; e++) {
			packs[e] = ParseJson.parse(read[e]);
		}
		
		//pack and item validation
		if(!ParseJson.verifyJson(packs)) {
			return null;
		}
		
		//sorts items & assigns model ids
		ModelCache models = new ModelCache();
		if(!models.sortModels(packs)) {
			System.out.println("Error occured while sorting models.");
			return null;
		}
		
		//TODO: parse & merge packs
		
		return new File("");
	}
	
	
}
