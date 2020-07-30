package items;

import java.util.HashMap;

import characters.character.STAT_TYPES;

public class wep extends equipment{
	
	public wep (String name, HashMap<STAT_TYPES, Float> update_values) {
		this.id = equipment.cur_id++;
		this.name = name;
		this.type = EQUIPMENT_TYPES.WEAPON;
		this.update_values = update_values;
	}

}
