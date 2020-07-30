package characters;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import items.equipment;
import items.equipment.EQUIPMENT_TYPES;
import mechanics.trait;

//parent class

public class character {
	
	protected int id;
	protected String name;
	
	protected float base_hp;
	protected float base_mag;
	protected float base_def;
	protected float base_crit_chance;
	protected float base_spd;
	protected float base_fire_res;
	protected float base_water_res;
	protected float base_earth_res;
	protected float base_air_res;
		
	protected float hp;
	protected float mag;
	protected float def;
	protected float crit_chance;
	protected float spd;
	protected float fire_res;
	protected float water_res;
	protected float earth_res;
	protected float air_res;
	protected float armor;
	protected float crit_mult;
	protected float armor_pen;
	
	protected equipment equips[];
	protected boolean slots_filled[];
	protected trait traits[];
	protected boolean slots_filled_traits[];
	
	public enum STAT_TYPES {
		HP(0),
		MAG(1),
		DEF(2),
		CRIT_CHANCE(3),
		SPD(4),
		FIRE_RES(5),
		WATER_RES(6),
		EARTH_RES(7),
		AIR_RES(8),
		ARMOR(9),
		CRIT_MULT(10),
		ARMOR_PEN(11);
		
		private final int value;
		private static Map map = new HashMap<>();
		
		private STAT_TYPES(int new_value) {
			this.value = new_value;
		}
		
		public int get_value() { return this.value; };
		
		static {
			for(STAT_TYPES stat_type : STAT_TYPES.values()) {
				map.put(stat_type.value, stat_type);
			}
		}
		
		public static STAT_TYPES value_of(int stat_type) {
			return (STAT_TYPES) map.get(stat_type);
		}
	}
	
	
	protected HashMap<STAT_TYPES, Consumer<Float>> equip_update_methods = new HashMap<STAT_TYPES, Consumer<Float>>() {
		private static final long serialVersionUID = 1L;
	{
		put(STAT_TYPES.HP, update_value -> set_hp( character.this.hp + update_value));
		put(STAT_TYPES.MAG, update_value -> set_mag(character.this.mag + update_value));
		put(STAT_TYPES.DEF, update_value -> set_def(character.this.def + update_value));
		put(STAT_TYPES.CRIT_CHANCE, update_value -> set_crit_chance(character.this.crit_chance + update_value));
		put(STAT_TYPES.SPD, update_value -> set_spd(character.this.spd + update_value));
		put(STAT_TYPES.FIRE_RES, update_value -> set_fire_res(character.this.fire_res + update_value));
		put(STAT_TYPES.WATER_RES, update_value -> set_water_res(character.this.water_res + update_value));
		put(STAT_TYPES.EARTH_RES, update_value -> set_earth_res(character.this.earth_res + update_value));
		put(STAT_TYPES.AIR_RES, update_value -> set_air_res(character.this.air_res + update_value));
		put(STAT_TYPES.ARMOR, update_value -> set_armor(character.this.armor + update_value));
		put(STAT_TYPES.CRIT_MULT, update_value -> set_crit_mult(character.this.crit_mult + update_value));
		put(STAT_TYPES.ARMOR_PEN, update_value -> set_armor_pen(character.this.armor_pen + update_value));
	}};
	//TODO trait update values should be 1.1 for example for a 10% increase, 0.9 for 10% decrease
	protected HashMap<STAT_TYPES, Consumer<Float>> trait_update_methods = new HashMap<STAT_TYPES, Consumer<Float>>() {
		private static final long serialVersionUID = 2L;
	{
		put(STAT_TYPES.HP, update_value -> set_hp( character.this.hp * update_value));
		put(STAT_TYPES.MAG, update_value -> set_mag(character.this.mag * update_value));
		put(STAT_TYPES.DEF, update_value -> set_def(character.this.def * update_value));
		put(STAT_TYPES.CRIT_CHANCE, update_value -> set_crit_chance(character.this.crit_chance * update_value));
		put(STAT_TYPES.SPD, update_value -> set_spd(character.this.spd * update_value));
		put(STAT_TYPES.FIRE_RES, update_value -> set_fire_res(character.this.fire_res * update_value));
		put(STAT_TYPES.WATER_RES, update_value -> set_water_res(character.this.water_res * update_value));
		put(STAT_TYPES.EARTH_RES, update_value -> set_earth_res(character.this.earth_res * update_value));
		put(STAT_TYPES.AIR_RES, update_value -> set_air_res(character.this.air_res * update_value));
		put(STAT_TYPES.ARMOR, update_value -> set_armor(character.this.armor * update_value));
		put(STAT_TYPES.CRIT_MULT, update_value -> set_crit_mult(character.this.crit_mult * update_value));
		put(STAT_TYPES.ARMOR_PEN, update_value -> set_armor_pen(character.this.armor_pen * update_value));
	}};



	//TODO equips should it be parent equipment class into head/body/acc/acc/wep
	
	public character() {
		//TODO purpose?
	}
	
	public character (int id, String name, int base_hp, int base_mag, int base_def, int base_crit_chance, int base_spd, int base_fire_res, int base_water_res,
			int base_earth_res, int base_air_res) {
		this.id = id;
		this.name = name;
		this.base_hp = this.hp = base_hp;
		this.base_mag = this.mag = base_mag;
		this.base_def = this.def = base_def;
		this.base_crit_chance = this.crit_chance = base_crit_chance;
		this.base_spd = this.spd = base_spd;
		this.base_fire_res = this.fire_res = base_fire_res;
		this.base_water_res = this.water_res = base_water_res;
		this.base_earth_res = this.earth_res = base_earth_res;
		this.base_air_res = this.air_res = base_air_res;
		this.armor = this.armor_pen = 0;
		this.equips = new equipment[] {null, null, null, null, null};
		this.traits = new trait[] {null,null,null};
		this.slots_filled = new boolean[] {false,false,false,false,false};
		this.slots_filled_traits = new boolean[] {false, false, false};
	}
	
	public void add_equip(equipment new_equip, int selected_slot) {
		int new_equip_slot = new_equip.get_type().get_value();
		if(new_equip_slot == selected_slot) {
			if(!slots_filled[selected_slot]) {
				slots_filled[selected_slot] = true;
				update_values(new_equip);
				equips[selected_slot] = new_equip;
			} else {
				update_values(equips[selected_slot], new_equip);
				equips[selected_slot] = new_equip;
			}
		} else if (new_equip_slot == EQUIPMENT_TYPES.ACCESSORY_1.get_value() && selected_slot == EQUIPMENT_TYPES.ACCESSORY_2.get_value()) {
			if(!slots_filled[EQUIPMENT_TYPES.ACCESSORY_2.get_value()]) {
				slots_filled[EQUIPMENT_TYPES.ACCESSORY_2.get_value()] = true;
				update_values(new_equip);
			} else {
				update_values(equips[EQUIPMENT_TYPES.ACCESSORY_2.get_value()], new_equip);
			}
			equips[selected_slot] = new_equip;
		} else {
			System.out.println("This piece of equipment belongs in another slot.");
		}
	}
	
	//	protected trait traits[];
	//protected boolean slots_filled_traits[];
	public void add_trait(trait new_trait, int selected_slot) {
		if(!slots_filled_traits[selected_slot]) {
			slots_filled_traits[selected_slot] = true;
			update_values_trait(new_trait);
			traits[selected_slot] = new_trait;
		} else {
			update_values_trait(traits[selected_slot], new_trait);
			traits[selected_slot] = new_trait;
		}
	}
	
	private void update_values(equipment new_equip) {
		for(Map.Entry<STAT_TYPES, Float> entry : new_equip.get_update_values().entrySet()) {
			equip_update_methods.get(entry.getKey()).accept(entry.getValue());
		}
	}
	
	private void update_values_trait(trait new_trait) {
		for(Map.Entry<STAT_TYPES, Float> entry : new_trait.get_update_values().entrySet()) {
			trait_update_methods.get(entry.getKey()).accept(entry.getValue());
		}
	}
	
	private void update_values(equipment old_equip, equipment new_equip) {
		for(Map.Entry<STAT_TYPES, Float> entry : old_equip.get_update_values().entrySet()) {
			equip_update_methods.get(entry.getKey()).accept(-entry.getValue());
		}
		
		for(Map.Entry<STAT_TYPES, Float> entry : new_equip.get_update_values().entrySet()) {
			equip_update_methods.get(entry.getKey()).accept(entry.getValue());
		}
	}
	
	private void update_values_trait(trait old_trait, trait new_trait) {
		for(Map.Entry<STAT_TYPES, Float> entry : old_trait.get_update_values().entrySet()) {
			trait_update_methods.get(entry.getKey()).accept(1/entry.getValue());
		}
		
		for(Map.Entry<STAT_TYPES, Float> entry : new_trait.get_update_values().entrySet()) {
			trait_update_methods.get(entry.getKey()).accept(entry.getValue());
		}
	}
	
	
	public void print_all () {
		System.out.println("This characters name: " + this.name +"\n" + 
							"This characters id: " + this.id + "\n" + 
							"base HP: " + this.base_hp + "\n" +
							"base Mag Attack: " + this.base_mag + "\n" +
							"base Def: " + this.base_def + "\n" +
							"base Crit Chance " + this.base_crit_chance + "\n" +
							"base Spd: " + this.base_spd + "\n" +
							"base FRes: " + this.base_fire_res + "\n" +
							"base WRes: " + this.base_water_res + "\n" +
							"base ERes " + this.base_earth_res + "\n" +
							"base ARes: " + this.base_air_res + "\n" +
							"HP: " + this.hp + "\n" +
							"Mag: " + this.mag + "\n" +
							"Def: " + this.def + "\n" +
							"Crit Chance: " + this.crit_chance + "\n" +
							"Spd: " + this.spd + "\n" +
							"FRes: " + this.fire_res + "\n" +
							"WRes: " + this.water_res + "\n" +
							"ERes: " + this.earth_res + "\n" +
							"ARes: " + this.air_res + "\n" +
							"Armor: " + this.armor + "\n" +
							"Crit Multiplier: " + this.crit_mult + "\n" +
							"Armor Pen: " + this.armor_pen);
		for(int i = 0; i < equips.length; ++i) {
			if(equips[i] == null) {
				System.out.println("Equipment does not exist for slot " + i + ".");
			} else {
				System.out.println("Equipment name: " + equips[i].get_name() + " Equipment type: " + equips[i].get_type());
			}
		}
		for(int i = 0; i < slots_filled.length; ++i) {
			System.out.println("Slot " + i + " is" + (slots_filled[i]? " filled." : " not filled."));
		}
		for(int i = 0; i < traits.length; ++i) {
			if(traits[i] == null) {
				System.out.println("Trait does not exist for slot " + i + ".");
			} else {
				System.out.println("Trait name: " + traits[i].get_name());
			}
		}
		for(int i = 0; i < slots_filled_traits.length; ++i) {
			System.out.println("Trait Slot " +  i + " is " + (slots_filled_traits[i] ? "filled." : "not filled."));
		}
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

	public float get_base_hp() {
		return base_hp;
	}

	public void set_base_hp(float base_hp) {
		this.base_hp = base_hp;
	}

	public float get_base_mag() {
		return base_mag;
	}

	public void set_base_mag(float base_mag) {
		this.base_mag = base_mag;
	}

	public float get_base_def() {
		return base_def;
	}

	public void set_base_def(float base_def) {
		this.base_def = base_def;
	}

	public float get_base_crit_chance() {
		return base_crit_chance;
	}

	public void set_base_crit_chance(float base_crit_chance) {
		this.base_crit_chance = base_crit_chance;
	}

	public float get_base_spd() {
		return base_spd;
	}

	public void set_base_spd(float base_spd) {
		this.base_spd = base_spd;
	}

	public float get_base_fire_res() {
		return base_fire_res;
	}

	public void set_base_fire_res(float base_fire_res) {
		this.base_fire_res = base_fire_res;
	}

	public float get_base_water_res() {
		return base_water_res;
	}

	public void set_base_water_res(float base_water_res) {
		this.base_water_res = base_water_res;
	}

	public float get_base_earth_res() {
		return base_earth_res;
	}

	public void set_base_earth_res(float base_earth_res) {
		this.base_earth_res = base_earth_res;
	}

	public float get_base_air_res() {
		return base_air_res;
	}

	public void set_base_air_res(float base_air_res) {
		this.base_air_res = base_air_res;
	}

	public float get_armor() {
		return armor;
	}

	public void set_armor(float armor) {
		this.armor = armor;
	}


	public float get_armor_pen() {
		return armor_pen;
	}

	public void set_armor_pen(float armor_pen) {
		this.armor_pen = armor_pen;
	}

	public float get_hp() {
		return hp;
	}

	public void set_hp(float f) {
		this.hp = f;
	}

	public float get_mag() {
		return mag;
	}

	public void set_mag(float mag) {
		this.mag = mag;
	}

	public float get_def() {
		return def;
	}

	public void set_def(float def) {
		this.def = def;
	}

	public float get_crit_chance() {
		return crit_chance;
	}

	public void set_crit_chance(float crit_chance) {
		this.crit_chance = crit_chance;
	}
	
	public float get_crit_mult() {
		return this.crit_mult;
	}
	
	public void set_crit_mult(float crit_mult) {
		this.crit_mult = crit_mult;
	}

	public float get_spd() {
		return spd;
	}

	public void set_spd(float spd) {
		this.spd = spd;
	}

	public float get_fire_res() {
		return fire_res;
	}

	public void set_fire_res(float fire_res) {
		this.fire_res = fire_res;
	}

	public float get_water_res() {
		return water_res;
	}

	public void set_water_res(float water_res) {
		this.water_res = water_res;
	}

	public float get_earth_res() {
		return earth_res;
	}

	public void set_earth_res(float earth_res) {
		this.earth_res = earth_res;
	}

	public float get_air_res() {
		return air_res;
	}

	public void set_air_res(float air_res) {
		this.air_res = air_res;
	}
	
}
