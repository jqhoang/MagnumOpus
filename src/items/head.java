package items;

import java.util.HashMap;

import characters.character.STAT_TYPES;

public class head extends equipment{
	
	public head (String name, HashMap<STAT_TYPES, Float> update_values) {
		this.id = equipment.cur_id++;
		this.name = name;
		this.type = EQUIPMENT_TYPES.HEAD;
		this.update_values = update_values;
	}

}
