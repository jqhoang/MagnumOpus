package mechanics;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import characters.character.STAT_TYPES;


public class skill {
	
	public static int MAXIMUM_SCREEN_X = 1920;
	
	protected String name;
	protected int damage;
	protected int type;
	//Need a container for aoe, this should only contain the minimum of points, like if its a cone do like 4 points the 2 corners, point of origin
	//and apex and anything in between gets damaged
	//Need a container for the skill sign, going to be using coordinates, so the container should account for that
	//Were going to be accessing and checking for a percentage correctness to determine damage amount as well as sign determination
	HashSet<Point> relative_sign_pts_w_err;
	Point[] relative_critical_pts_for_area;

	//TODO parameter that aids in aoe calcit is a unique property of the skill itself

	
	public enum ELEMENTAL_TYPES {
		FIRE(0),
		WATER(1),
		WIND(2),
		EARTH(3);
		
		private final int value;
		private static Map map = new HashMap<>();
		
		ELEMENTAL_TYPES(final int new_value){
			this.value = new_value;
		}
		
		static {
			for(ELEMENTAL_TYPES elemental_type : ELEMENTAL_TYPES.values()) {
				map.put(elemental_type.value, elemental_type);
			}
		}
		
		public int get_value() {return value;}
		
		public static ELEMENTAL_TYPES value_of(int elemental_type) {
			return (ELEMENTAL_TYPES) map.get(elemental_type);
		}
	}
	
	public int range_of_x_error = 1;
	public int range_of_y_error = 1;
	public int percent_threshold_for_skill_success = 50;
	
	
	public skill () {
		
	}
	
	public skill (String name,  int damage, ELEMENTAL_TYPES type, HashSet<Point> sign_pts, Point[] relative_critical_pts_for_area) {
		//Sign points must be relative to 0,0
		System.out.println("REMINDER: Ensure sign points are entered relative to {0,0}.");
		this.name = name;
		this.damage = damage;
		this.type = type.get_value();
		this.relative_critical_pts_for_area = relative_critical_pts_for_area;
		relative_sign_pts_w_err = new HashSet<Point>();
		for(Point point : sign_pts) {
			relative_sign_pts_w_err.add(point);
			for(int i = 0; i < range_of_x_error; ++i) {
				for(int k = 0; k < range_of_y_error; ++k) {
					relative_sign_pts_w_err.add(new Point(point.x + i, point.y + k));
					relative_sign_pts_w_err.add(new Point(point.x - i, point.y + k));
					relative_sign_pts_w_err.add(new Point(point.x + i, point.y - k));
					relative_sign_pts_w_err.add(new Point(point.x - i, point.y - k));
				}
			}
		}
	}

	private float percentage_check(HashSet<Point> relative_user_sign_pts, Point origin) {
		int total_user_points = relative_user_sign_pts.size();
		int total_sign_points = relative_sign_pts_w_err.size();
		int total_correct_points = 0;
		
		for(Point user_point : relative_user_sign_pts) {
			if(relative_sign_pts_w_err.contains(user_point));
		}
		//TODO Balance test for percentage
		return (float) total_correct_points/total_user_points * (total_sign_points/total_user_points);
	}
	
	public boolean skill_success(float percentage) {
		return percentage>=percent_threshold_for_skill_success;
	}
	
	//TODO should be only called if skill_success returns true
	//TODO needs direction, this will be whereever the player is currently facing
	//Some spells the point of origin wont be where the player is but instead where the sign is cast
	public Point[] area_affected(Point origin) {
		Point[] calculated_aoe = new Point[relative_critical_pts_for_area.length];
		for(int i = 0; i < calculated_aoe.length; ++i) {
			calculated_aoe[i] = new Point(relative_critical_pts_for_area[i].x + origin.x, relative_critical_pts_for_area[i].y + origin.y);
		}
		return calculated_aoe;
	}
	
	//Use positive portion of x-axis as the origin point
	static public double calc_radians (ArrayList<Point> direction) {
		/* 
        double numerator = P2Y*(P1X-P3X) + P1Y*(P3X-P2X) + P3Y*(P2X-P1X);
        double denominator = (P2X-P1X)*(P1X-P3X) + (P2Y-P1Y)*(P1Y-P3Y);
        double ratio = numerator/denominator;

        double angleRad = Math.Atan(ratio);
        double angleDeg = (angleRad*180)/Math.PI;

        if(angleDeg<0){
            angleDeg = 180+angleDeg;
        } */
		Point p1 = direction.get(0); // character/spell origin
		Point p2 = direction.get(1); // where a critical point of spell is
		Point p3 = new Point(MAXIMUM_SCREEN_X, 0);
		double numerator = p2.y * (p1.x - p3.x) + p1.y * (p3.x - p2.x) + p3.y * (p2.x - p1.x);
		double denominator = (p2.x - p1.x) * (p1.x - p3.x) + (p2.y - p1.y) * (p1.y - p3.y);
		double ratio = numerator/denominator;
		return(Math.atan(ratio));	
	}
	
	//TODO check if origin is necessary
	//TODO rotating pixels is not working as I would think figure out another way
	static public HashSet<Point> final_area_affected(HashSet<Point> non_rotated_area_affected, Point origin, double radians) {
		HashSet<Point> final_area_affected = new HashSet<Point>();
		for(Point p : non_rotated_area_affected) {
			//TODO check if flooring the points doesnt make it render
			double new_x = origin.x + (p.x - origin.x) * Math.cos(radians) - (p.y - origin.y) * Math.sin(radians);
			double new_y = origin.y + (p.x - origin.x) * Math.sin(radians) + (p.y - origin.y) * Math.cos(radians);
			final_area_affected.add(new Point((int) Math.floor(new_x), (int) Math.floor(new_y)));
		}
		return final_area_affected;
	}
	
	//String name,  int damage, int type, HashSet<Point> sign_pts, Point[] relative_critical_pts_for_area, ArrayList<Point> direction
	public void print_all() {
		System.out.println("The name of this skill is: " + this.name + ".\n"
				+ "The type of this skill is: " + this.type + ".\n"
				+ "The total set of sign points with acceptable error range are: ");
		int c = 0;
		for(Point p : this.relative_sign_pts_w_err) {
			System.out.println("C: " + ++c + "   (" + p.x + "," + p.y + ")");
		}
		System.out.println("The critical points for this skill are: ");
		for(int i = 0; i < relative_critical_pts_for_area.length; ++i) {
			System.out.println("I: " + i + "  (" + relative_critical_pts_for_area[i].x + "," + relative_critical_pts_for_area[i].y + ")");
		}
	}
	
	////////////////////////////////////////////////////////GETTERS/SETTERS///////////////////////////////////////////////////////////////////
	

	public String get_name() {
		return name;
	}

	public void set_name(String name) {
		this.name = name;
	}

	public int get_damage() {
		return damage;
	}

	public void set_damage(int damage) {
		this.damage = damage;
	}

	public int get_type() {
		return type;
	}

	public void set_type(int type) {
		this.type = type;
	}

	public HashSet<Point> get_relative_sign_pts_w_err() {
		return relative_sign_pts_w_err;
	}

	public void set_relative_sign_pts_w_err(HashSet<Point> relative_sign_pts_w_err) {
		this.relative_sign_pts_w_err = relative_sign_pts_w_err;
	}

	public Point[] get_relative_critical_pts_for_area() {
		return relative_critical_pts_for_area;
	}

	public void set_relative_critical_pts_for_area(Point[] relative_critical_pts_for_area) {
		this.relative_critical_pts_for_area = relative_critical_pts_for_area;
	}
}
