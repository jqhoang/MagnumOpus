package main;

import java.util.HashMap;

import characters.character;
import characters.character.STAT_TYPES;
import items.body;
import items.equipment;
import items.equipment.EQUIPMENT_TYPES;
import mechanics.trait;

public class snippets {
	
	/*
	//(int id, String name, int base_hp, int base_mag, int base_def, int base_crit_chance, int base_spd, int base_fire_res, int base_water_res,
	//int base_earth_res, int base_air_res
		}


	// TODO Auto-generated method stub
	/*
			character john = new character(0, "John", 10, 1, 1, 1, 1, 1, 1, 1, 1);
		HashMap<STAT_TYPES, Float> dev_murasama_map = new HashMap<STAT_TYPES, Float>();
		HashMap<STAT_TYPES, Float> dev_anti_murasama_map = new HashMap<STAT_TYPES, Float>();
		HashMap<STAT_TYPES, Float> dev_acc_1 = new HashMap<STAT_TYPES, Float>();
		HashMap<STAT_TYPES, Float> dev_acc_2 = new HashMap<STAT_TYPES, Float>();
		HashMap<STAT_TYPES, Float> dev_body = new HashMap<STAT_TYPES, Float>();
		for(int i = 0; i < 12; ++i)
			dev_murasama_map.put(STAT_TYPES.value_of(i), (float) 10.0);
		for(int i = 0; i < 12; ++i)
			dev_anti_murasama_map.put(STAT_TYPES.value_of(i), (float) 10);
		for(int i = 0; i < 12; ++i) {
			dev_acc_1.put(STAT_TYPES.value_of(i), (float) 3);
			dev_acc_2.put(STAT_TYPES.value_of(i), (float) 2);
		}
		dev_body.put(STAT_TYPES.value_of(0), (float) 7);
		dev_body.put(STAT_TYPES.MAG, (float) 2);
			
		
		equipment sword = new equipment(0, "Dev Murasama", EQUIPMENT_TYPES.WEAPON, dev_murasama_map);
		equipment anti_sword = new equipment(1, "Anti Dev Murasama", EQUIPMENT_TYPES.WEAPON, dev_anti_murasama_map);
		equipment acc_1 = new equipment(2, "Dev Acc 1", EQUIPMENT_TYPES.ACCESSORY_1, dev_acc_1);
		equipment acc_2 = new equipment(3, "Dev Acc 2", EQUIPMENT_TYPES.ACCESSORY_2, dev_acc_2);
		//equipment body = new equipment(4, "Dev Body", 1, dev_body);
		body body = new body("Dev Body", dev_body);
		int selected_slot = 4;
		john.add_equip(sword, selected_slot);
		john.print_all();
		john.add_equip(anti_sword, selected_slot);
		john.print_all();
		john.add_equip(acc_1, 2);
		john.print_all();	
		john.add_equip(acc_2, 3);
		john.print_all();
		john.add_equip(body, 1);
		john.print_all();
		
		
		HashMap<STAT_TYPES, Float> fire_demon_map = new HashMap<STAT_TYPES, Float>();
		HashMap<STAT_TYPES, Float> ice_king_map = new HashMap<STAT_TYPES, Float>();
		HashMap<STAT_TYPES, Float> air_spirit_map = new HashMap<STAT_TYPES, Float>();
		fire_demon_map.put(STAT_TYPES.FIRE_RES, (float) 1.1);
		fire_demon_map.put(STAT_TYPES.WATER_RES, (float) 0.9);
		ice_king_map.put(STAT_TYPES.FIRE_RES, (float) 0.9);
		ice_king_map.put(STAT_TYPES.WATER_RES, (float) 1.1);
		air_spirit_map.put(STAT_TYPES.AIR_RES, (float) 1.5);
		int test_slot = 0;
		int air_slot = 1;
		
		trait fire_demon = new trait("Fire Demon", fire_demon_map);
		trait ice_king = new trait("Ice King",ice_king_map);
		trait air_spirit = new trait("Air Spirit", air_spirit_map);
		john.add_trait(fire_demon, 0);
		john.print_all();
		john.add_trait(ice_king, 0);
		john.print_all();
		john.add_trait(air_spirit, air_slot);
		john.print_all();*/
	
	
	/*
	//String name,  int damage, int type, HashSet<Point> sign_pts, Point[] relative_critical_pts_for_area, ArrayList<Point> direction
	HashSet<Point> fb_sign_pts = new HashSet<Point>();
	fb_sign_pts.add(new Point(0,0));
	fb_sign_pts.add(new Point(0,1));
	fb_sign_pts.add(new Point(0,2));
	fb_sign_pts.add(new Point(1,0));
	fb_sign_pts.add(new Point(2,0));
	fb_sign_pts.add(new Point(2,1));
	fb_sign_pts.add(new Point(2,2));
	fb_sign_pts.add(new Point(1,2));
	Point[] relative_critical_pts_for_area = new Point[4];
	
	relative_critical_pts_for_area[0] = new Point(0,0);
	relative_critical_pts_for_area[1] = new Point(0,2);
	relative_critical_pts_for_area[2] = new Point(2,0);
	relative_critical_pts_for_area[3] = new Point(2,2);
	ArrayList<Point> direction = new ArrayList<>();
	direction.add(new Point(0,0));
	direction.add(new Point(2,2));
	
	skill stevens_asshole = new skill("Massive Leak", 10, 0, fb_sign_pts, relative_critical_pts_for_area);
	stevens_asshole.print_all();
	double radians = skill.calc_radians(direction);
	System.out.println(radians);
	HashSet<Point> area_1 = skill.final_area_affected(stevens_asshole.get_relative_sign_pts_w_err(), new Point(0,0), 45);
	for(Point p : area_1) {
		System.out.println("(" + p.x + "," + p.y + ")");
	}*/
	
}
