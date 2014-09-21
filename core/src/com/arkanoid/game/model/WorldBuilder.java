package com.arkanoid.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class WorldBuilder implements Constants {

public class TObject {
		
		private String name;
		private int x;
		private int y;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
	}

	public void readJson() {
		try {	
			Json json = new Json();
			
			TObject o = new TObject();
			o.setName("Test");
			o.setX(0);
			o.setY(0);
			
			if (Gdx.files.isLocalStorageAvailable()) {
				FileHandle writeFile = Gdx.files.local("data/game_data.json");
				writeFile.writeString(json.toJson(o), false);
				FileHandle readFile = Gdx.files.local("data/game_data.json");
				String obj = readFile.readString();				
				JsonValue root = new JsonReader().parse(obj);
				System.out.println(obj);				
			}
			//InputStream fin = Gdx.files.local("data/game_data.json").read();
			//JsonReader jr = new JsonReader();
			//JsonValue jsn = jr.parse(fin);
			//fin.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public WorldBuilder(int level) {
		readJson();
		
	}
	
	

}
