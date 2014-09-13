package miscdragons.constants.paths;

import miscdragons.MiscDragons;
import miscdragons.util.Condition;
import miscdragons.util.Traversal;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

public enum ZanarisState {
	TO_ZANARIS,
	TO_ZANARIS_BANK,
	TO_STATUE,
	TO_CAVE;

	public static final int ZANARIS_BOOTH_ID = 52589;
	public static final int PORTAL_ID = 12260;
	public static final int CHICKEN_ID = 2138;
	public static final int CHICKEN_STATUE_ID = 12093;

	public static final int[] ZANARIS_BLACK_IDS = {54,4674,4675,4676};

	public static final Tile PORTAL_TILE = new Tile(1565,4356,0);
	public static final Tile ZANARIS_BLACK_DRAGON_CENTER = new Tile(1564,4367,0);
	public static final Tile ZANARIS_BANK_TILE = new Tile(2382,4458,0);
	public static final Tile CHICKEN_STATUE_TILE = new Tile(2453,4466,0);//(2452,4476,0)

	public static final Area ZANARIS_AREA = new Area();//TODO
	public static final Area ZANARIS_BANK_AREA = new Area(new Tile(2377,4463,0),new Tile(2385,4453,0));
	public static final Area ZANARIS_BLACK_DRAGON_AREA = new Area(new Tile(1548,4389,0), new Tile(1580,4355,0));
	public static final Area STATUE_AREA = new Area(new Tile(2446,4478,0),new Tile(2461,4463,0));

	@SuppressWarnings("incomplete-switch")
	public static void traverse(){
		if(MiscDragons.zanarisState == null)
			MiscDragons.zanarisState = TO_ZANARIS;
		
		if(MiscDragons.toBank){
			switch(MiscDragons.zanarisState){
			case TO_ZANARIS:{
				if(STATUE_AREA.contains(Players.getLocal())){//TODO change to if(in zanaris)
					MiscDragons.zanarisState = TO_ZANARIS_BANK;
					break;
				}
				Walking.walk(PORTAL_TILE);
				Task.sleep(2000);
				SceneObject portal = SceneEntities.getNearest(PORTAL_ID);
				if(portal != null){
					if(portal.isOnScreen() && portal.click(true)){
						Traversal.waitFor(new Condition(){
							@Override
							public boolean accept() {
								return STATUE_AREA.contains(Players.getLocal());
							}}, 5000);
					}
					else{
						Camera.turnTo(portal);
					}
				}
				else{
					Walking.walk(PORTAL_TILE);
				}
				break;
			}
			case TO_ZANARIS_BANK:{
				if(ZanarisState.ZANARIS_BANK_AREA.contains(Players.getLocal())){
					MiscDragons.toBank = MiscDragons.traverse = false;
					MiscDragons.banking = true;
					MiscDragons.zanarisState = TO_STATUE;
				}
				Walking.walk(ZanarisState.ZANARIS_BANK_TILE);
				Task.sleep(2000);
				break;
			}
			}
		}
		if(MiscDragons.fromBank){
			switch(MiscDragons.zanarisState){
			case TO_STATUE:{
				if(ZanarisState.STATUE_AREA.contains(Players.getLocal())){
					System.out.println("statue area detected");
					MiscDragons.zanarisState = TO_CAVE;
					break;
				}
				Walking.walk(CHICKEN_STATUE_TILE);
				Task.sleep(2000);
				break;
			}
			case TO_CAVE:{
				if(ZANARIS_BLACK_DRAGON_AREA.contains(Players.getLocal())){
					MiscDragons.traverse = MiscDragons.fromBank = false;
					MiscDragons.fighting = true;
					MiscDragons.zanarisState = null;
					break;
				}
				SceneObject statue = SceneEntities.getNearest(CHICKEN_STATUE_ID);
				if(statue != null){
					if(statue.isOnScreen() && Inventory.getCount(CHICKEN_ID) > 0){
						Inventory.getItem(CHICKEN_ID).getWidgetChild().interact("Use");
						if(statue.click(true))
							Traversal.waitFor(new Condition(){
								@Override
								public boolean accept() {
									return ZANARIS_BLACK_DRAGON_AREA.contains(Players.getLocal());
								}}, 5000);
					}
					else{
						Camera.turnTo(statue);
					}
				}
				else{
					Walking.walk(CHICKEN_STATUE_TILE);
				}
				break;
			}
			}
		}
	}
}


   
    

