package miscdragons.constants;

import java.util.ArrayList;

import miscdragons.MiscDragons;

import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.node.GroundItem;

public enum LootDef {
	
	//TODO find some way to differentiate loot
	
	GREEN(1753),
	BLUE(1751),
	RED(1749),
	BLACK(1747),
	IRON(-1),
	STEEL(-1);

	LootDef(int hideId){
		_hideId = hideId;
	}

	private int _hideId;

	public static final int BONES_ID = 536;
	public static final int VIAL_ID = 229;
	
	public static final int[] CHARMS = {12158,12159,12160,12163};
	public static final int[] RARE_LOOT = {1617,1631,1615,987,985,1247,1249,
		1216,1201,2366,1149,892,9342,2362,452,258,2999,3001,270,6686,
		5315,5316,5289,5300,5304,1516,1392,574,570,20667,7937,561,
		560,565,1441,1443,372,533,446,454,450};
	public static final int[] BLACK_LOOT = {536,1747,11286,
		1123,1303,1185,1373,1319,565,563,560,561,562,9431};
	public static final int[] RED_LOOT = {};
	public static final int[] EFFEGIES = {};

	public int getHideId(){
		return _hideId;
	}
	
	public static ArrayList<Integer> getLoot(){
		switch(MiscDragons.dragonType){
		case BLACK_ZANARIS:{
			return lootList(BLACK_LOOT,RARE_LOOT);
		}
		case BLUE_TAVERLY:{
			return lootList(RARE_LOOT);
		}
		case BLUE_TAVERLY_RESOURCE:{
			return lootList(RARE_LOOT);
		}
		case BLUE_TAVERLY_RESOURCE_SHORTCUT:{
			return lootList(RARE_LOOT);
		}
		case BLUE_TAVERLY_SHORTCUT:{
			return lootList(RARE_LOOT);
		}
		case GREEN_CHAOS:{
			return lootList(RARE_LOOT);
		}
		case RED_BRIMHAVEN:{
			return lootList(RARE_LOOT);
		}
		default:{
			return lootList(RARE_LOOT);
		}
		}
	}
	
	private static ArrayList<Integer> lootList(int[]... lists){
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int[] list : lists){
			for(int id : list){
				result.add(id);
			}
		}
		return result;
	}

	public static GroundItem findLoot(){
		GroundItem found = GroundItems.getNearest(BONES_ID);
		if(found != null)
			return found;
		found = GroundItems.getNearest(MiscDragons.hideId);
		if(found != null)
			return found;
		found = GroundItems.getNearest(
				new Filter<GroundItem>(){
					@Override
					public boolean accept(GroundItem g) {
						for(final int r : MiscDragons.loot){//rare drop table
							if(g.getId() == r && Calculations.distanceTo(g) < 15)
								return true;
						}
						return false;
					}
				});
		return found;
	}
}
