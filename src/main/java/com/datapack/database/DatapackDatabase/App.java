package com.datapack.database.DatapackDatabase;

import java.io.File;

import com.datapack.database.DatapackDatabase.datapack.Datapack;
import com.datapack.database.DatapackDatabase.datapack.ModelCache;

public class App {
	
    public static void main( String[] args ) {
		
    	//TODO: load files from database
		File[] read = {new File("example.json")};
		Datapack[] packs = new Datapack[read.length];
		
		
		//parse JSON of each datapack
		for(int e = 0; e < read.length; e++) {
			packs[e] = ParseJson.parse(read[e]);
		}
		
		
		
		//pack and item validation
		if(!ParseJson.verifyJson(packs)) {
			exit();
		}
		
		
		
		//sorts items & assigns model ids
		ModelCache models = new ModelCache();
		if(!models.sortModels(packs)) {
			System.out.println("Error occured while sorting models.");
			exit();
		}
		
		
		
		//TODO: add in-line parser stuff here. I recommend offloading as much as possible to an object to keep the code seperate.
		
		//TODO: offload parse results to server
		
    }
    
    /**
     * Call whenever a fatal error has occurred
     */
    private static void exit() {
    	System.out.println("Program terminated.");
    	System.exit(0);
    }
    
}
