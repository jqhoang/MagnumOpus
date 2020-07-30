package mechanics;

import java.util.HashMap;

import characters.character.STAT_TYPES;

public class trait {
	
	public static int cur_id = 0;
	
	protected int id;
	protected String name;
	protected HashMap<STAT_TYPES, Float> update_values;
	
	public trait(String name, HashMap<STAT_TYPES, Float> update_values) {
		this.name = name;
		this.update_values = update_values;
	}

	
	////////////////////////////////////////////////////////GETTERS/SETTERS///////////////////////////////////////////////////////////////////
	
	public int get_id() {
		return id;
	}

	public void set_id(int id) {
		this.id = id;
	}

	public String get_name() {
		return name;
	}

	public void set_name(String name) {
		this.name = name;
	}

	public HashMap<STAT_TYPES, Float> get_update_values() {
		return update_values;
	}

	public void set_update_values(HashMap<STAT_TYPES, Float> update_values) {
		this.update_values = update_values;
	}

	
	
}
