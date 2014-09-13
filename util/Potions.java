package miscdragons.util;

import org.powerbot.game.api.methods.tab.Inventory;

public class Potions {
	/*
	 * TODO
	 * fill ids (extreme att/str)
	 */
	
	//single dose,...,max dose
	public enum Potion{
		SUPER_ATTACK(new int[]{149,147,145,2436}),
		EXTREME_ATTACK(new int[]{15311,15310,15309,15308}),
		SUPER_STRENGTH(new int[]{161,159,157,2440}),
		EXTREME_STRENGTH(new int[]{15315,15314,15313,15312}),
		SUPER_DEFENCE(new int[]{167,165,163,2442}),
		EXTREME_DEFENCE(new int[]{15319,15318,15317,15316}),
		
		RANGING(new int[]{173,171,169,2444}),
		EXTREME_RANGING(new int[]{15327,15326,15325,15324}),
		MAGIC(new int[]{3046,3044,3042,3040}),
		EXTREME_MAGIC(new int[]{15323,15322,15321,15320}),
		
		OVERLOAD(new int[]{}),
		
		PRAYER(new int[]{143,141,139,2434}),
		SUPER_RESTORE(new int[]{3030,3028,3026,3024}),
		PRAYER_RENEWAL(new int[]{21636,21634,21632,21630}),
		
		SARADOMIN_BREW(new int[]{6691,6689,6687,6685}),
		
		ANTIFIRE(new int[]{2458,2456,2454,2452});
		
		private int[] _ids;
		
		Potion(int[] ids){
			_ids = ids;
		}
		
		public int[] getIds(){
			return _ids;
		}
		
		public int maxDoseId(){
			return _ids[3];
		}
		
		public boolean sip(){
			for(int id : _ids){
				if(Inventory.getCount(id) > 0)
					return(Inventory.getItem(id).getWidgetChild().click(true));
			}
			return false;
		}
	}
	public enum Flask{
		SUPER_ATTACK(new int[]{}),
		EXTREME_ATTACK(new int[]{}),
		SUPER_STRENGTH(new int[]{}),
		EXTREME_STRENGTH(new int[]{}),
		SUPER_DEFENCE(new int[]{}),
		EXTREME_DEFENCE(new int[]{}),
		RANGING(new int[]{23313,23311,23309,23307,23305,23303}),
		EXTREME_RANGING(new int[]{23524,23523,23522,23521,23520,23519}),
		MAGIC(new int[]{23433,23431,23429,23427,23425,23423}),
		EXTREME_MAGIC(new int[]{23518,23517,23516,23515,23514,23513}),
		OVERLOAD(new int[]{23536,23535,23534,23533,23532,23531}),
		PRAYER(new int[]{23253,23251,23249,23247,23245,23243}),
		SUPER_RESTORE(new int[]{23409,23407,23405,23403,23401,23399}),
		PRAYER_RENEWAL(new int[]{23619,23617,23615,23613,23611,23609}),
		SARADOMIN_BREW(new int[]{23361,23359,23357,23355,23353,23351});
		
		private int[] _ids;
		
		Flask(int[] ids){
			_ids = ids;
		}
		
		public int[] getIds(){
			return _ids;
		} 
		
		public int maxDoseId(){
			return _ids[5];
		}
		
		public boolean sip(){
			for(int id : _ids){
				if(Inventory.getCount(id) > 0)
					return(Inventory.getItem(id).getWidgetChild().click(true));
			}
			return false;
		}
	}
}
