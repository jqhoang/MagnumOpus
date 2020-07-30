package items;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import characters.character.STAT_TYPES;


public class equipment {
	
	
	public enum EQUIPMENT_TYPES {
		HEAD(0),
		BODY(1),
		ACCESSORY_1(2),
		ACCESSORY_2(3),
		WEAPON(4);
		
		private final int value;
		private static Map map = new HashMap<>();
		
		EQUIPMENT_TYPES(int new_value) {
			this.value = new_value;
		}
		
		static {
			for(EQUIPMENT_TYPES equipment_type : EQUIPMENT_TYPES.values()) {
				map.put(equipment_type.value, equipment_type);
			}
		}
		
		public int get_value() {
			return this.value;
		}
		
		public static EQUIPMENT_TYPES value_of(int equipment_type) {
			return (EQUIPMENT_TYPES) map.get(equipment_type);
		}
	}
	
	public static int cur_id = 0;
	
	protected int id;
	protected String name;
	protected EQUIPMENT_TYPES type;
	protected HashMap<STAT_TYPES, Float> update_values;
	

	equipment () {
		
	}
	
	public equipment (int id, String name, EQUIPMENT_TYPES type, HashMap<STAT_TYPES, Float> update_values) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.update_values = update_values;
	}
	
	public void print_all() {
		System.out.println("This equipments ID is: " + this.id + "\n" +
				"This equipments name is: " + this.name + "\n" +
				"This equipment belongs in slot: " + this.type + "\n");
		for(Entry<STAT_TYPES, Float> entry : this.update_values.entrySet())
			System.out.println("For stat: " + entry.getKey().get_value() + ", it has this value: " + entry.getValue());
	}
	
	////////////////////////////////////////////////////////GETTERS/SETTERS///////////////////////////////////////////////////////////////////
	
	public int get_id() {
		return id;
	}

	public void set_id(int id) {
		this.id = id;
	}

	public EQUIPMENT_TYPES get_type() {
		return type;
	}

	public void set_type(EQUIPMENT_TYPES type) {
		this.type = type;
	}
	
	public HashMap<STAT_TYPES, Float> get_update_values () {
		return update_values;
	}
	
	public void set_update_values(HashMap<STAT_TYPES,Float> update_values) {
		this.update_values = update_values;
	}

	public String get_name() {
		return name;
	}

	public void set_name(String name) {
		this.name = name;
	}


}
