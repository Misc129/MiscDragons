package miscdragons.constants.paths;

import miscdragons.MiscDragons;
import miscdragons.util.Condition;
import miscdragons.util.Traversal;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

public enum ChaosState {
	TO_VARROCK,
	TO_GE_BANK,
	TO_HOLE,
	TO_DITCH,
	TO_RIFT,
	TO_PORTAL,
	TO_DRAGONS;

	public static final int VARROCK_TAB_ID = 8007;
	public static final int HOLE_ID = 26497;
	public static final int DITCH_ID = 65076;
	public static final int RIFT_ID = 65203;//or 65202
	public static final int CHAOS_PORTAL_ID = 77747;//28779

	public static final Tile GE_BANK_TILE = new Tile(3149,3501,0);
	public static final Tile DITCH_TILE = new Tile(3140,3520,0);
	public static final Tile RIFT_TILE = new Tile(3164,3562,0);
	public static final Tile CHAOS_PORTAL_TILE = new Tile(3289,5464,0);
	public static final Tile CHAOS_GREEN_CENTER_TILE = new Tile(3310,5451,0);

	public static final Tile[] VARROCK_CENTER_TO_GE = {new Tile(3149,3502,0),
		new Tile(3152,3497,0),new Tile(3155,3490,0),new Tile(3158,3482,0),
		new Tile(3162,3475,0),new Tile(3165,3467,0),new Tile(3169,3460,0),
		new Tile(3175,3453,0),new Tile(3183,3451,0),new Tile(3187,3451,0),
		new Tile(3193,3448,0),new Tile(3198,3444,0),new Tile(3202,3441,0),
		new Tile(3209,3436,0),new Tile(3212,3431,0),
	};

	public static final Area VARROCK_CENTER = new Area(new Tile(3206,3428,0),new Tile(3221,3417,0));
	public static final Area GE_BANK_AREA = new Area(new Tile(3132,3515,0),new Tile(3155,3500,0));
	public static final Area CHAOS_GREEN_AREA = new Area(new Tile(3298,3471,0),new Tile(3323,5441,0));

	@SuppressWarnings("incomplete-switch")
	public static void traverse(){
		if(MiscDragons.toBank){
			if(MiscDragons.chaosState == null)
				MiscDragons.chaosState = ChaosState.TO_VARROCK;
			if(VARROCK_CENTER.contains(Players.getLocal().getLocation())){
				MiscDragons.chaosState = ChaosState.TO_GE_BANK;
			}
			switch(MiscDragons.chaosState){
			case TO_VARROCK:{
				if(VARROCK_CENTER.contains(Players.getLocal())){
					MiscDragons.chaosState = ChaosState.TO_GE_BANK;
				}
				Inventory.getItem(VARROCK_TAB_ID).getWidgetChild().click(true);

				Traversal.waitFor(new Condition(){
					@Override
					public boolean accept() {
						return VARROCK_CENTER.contains(Players.getLocal());
					}}, 5000);

				break;
			}
			case TO_GE_BANK:{
				if(GE_BANK_AREA.contains(Players.getLocal().getLocation())){
					MiscDragons.toBank = MiscDragons.traverse = false;
					MiscDragons.banking = true;
					MiscDragons.chaosState = ChaosState.TO_HOLE;
				}
				if(Traversal.stepPath(VARROCK_CENTER_TO_GE,true))
					Task.sleep(2000);
				break;
			}
			}
		}
		if(MiscDragons.fromBank){
			if(MiscDragons.chaosState == null)
				MiscDragons.chaosState = ChaosState.TO_HOLE;
			switch(MiscDragons.chaosState){
			case TO_HOLE:{
				if(Players.getLocal().getLocation().getX() < 3139){
					MiscDragons.chaosState = ChaosState.TO_DITCH;
					break;
				}
				SceneObject hole = SceneEntities.getNearest(HOLE_ID);
				if(hole != null){
					if(Bank.isOpen()){
						Walking.walk(hole.getLocation());
					}
					if(hole.isOnScreen() && hole.interact("Climb-into")){
						Traversal.waitFor(new Condition(){
							@Override
							public boolean accept() {
								return Players.getLocal().getLocation().getX() < 3139;
							}}, 5000);
					}
					else{
						Camera.turnTo(hole);
						Walking.walk(hole.getLocation());
						Task.sleep(1000);
					}
				}
				break;
			}
			case TO_DITCH:{//3522
				if(Players.getLocal().getLocation().getY() > 3522){
					MiscDragons.chaosState = ChaosState.TO_RIFT;
					break;
				}
				SceneObject ditch = SceneEntities.getNearest(DITCH_ID);
				if(ditch != null){
					if(ditch.isOnScreen() && ditch.interact("Cross")){
						Traversal.waitFor(new Condition(){
							@Override
							public boolean accept() {
								return Players.getLocal().getLocation().getY() > 3522;
							}}, 5000);
						Walking.walk(RIFT_TILE);
					}
					else{
						Camera.turnTo(ditch);
						Walking.walk(DITCH_TILE);
						Task.sleep(1000);
					}
				}
				break;
			}
			case TO_RIFT:{
				if(Players.getLocal().getLocation().getY() > 4000){
					MiscDragons.chaosState = ChaosState.TO_PORTAL;
					break;
				}
				Walking.walk(RIFT_TILE);
				Task.sleep(2000);
				SceneObject rift = SceneEntities.getNearest(RIFT_ID);
				if(rift != null){
					if(Calculations.distanceTo(rift) > 8)
						break;
					if(rift.isOnScreen() && rift.interact("Enter")){
						Traversal.waitFor(new Condition(){
							@Override
							public boolean accept() {
								return Players.getLocal().getLocation().getY() > 4000;
							}}, 5000);
					}
					else
						Camera.turnTo(rift);
				}
				break;
			}
			case TO_PORTAL:{
				if(Players.getLocal().getLocation().getX() > 3298
						&& Players.getLocal().getLocation().getY() < 5471){
					Walking.walk(CHAOS_GREEN_CENTER_TILE);
					MiscDragons.traverse = MiscDragons.fromBank = false;
					MiscDragons.fighting = true;
					MiscDragons.chaosState = null;
					break;
				}
				Walking.walk(CHAOS_PORTAL_TILE);
				Task.sleep(1000);
				SceneObject portal = SceneEntities.getNearest(new Filter<SceneObject>(){

					@Override
					public boolean accept(SceneObject found) {
						return(found.getId() == CHAOS_PORTAL_ID
								&& found.getLocation().getY() == 5463
								&& found.getLocation().getX() == 3290);
					}
				});
				if(portal != null){
					if(portal.isOnScreen()){
						portal.interact("Enter");
						Task.sleep(2000);
					}
					else{
						Camera.turnTo(portal);
					}
				}
				break;
			}
			}
		}
	}
	
	/*
	 * public static void traverse(){
		if(MiscDragons.toBank){
			if(MiscDragons.chaosState == null)
				MiscDragons.chaosState = ChaosState.TO_VARROCK;
			if(VARROCK_CENTER.contains(Players.getLocal().getLocation())){
				MiscDragons.chaosState = ChaosState.TO_GE_BANK;
			}
			switch(MiscDragons.chaosState){
			case TO_VARROCK:{
				if(VARROCK_CENTER.contains(Players.getLocal())){
					MiscDragons.chaosState = ChaosState.TO_GE_BANK;
				}
				Inventory.getItem(VARROCK_TAB_ID).getWidgetChild().click(true);
				Traversal.waitFor(new Condition(){
					@Override
					public boolean accept() {
						return VARROCK_CENTER.contains(Players.getLocal());
					}}, 5000);
				break;
			}
			case TO_GE_BANK:{
				if(GE_BANK_AREA.contains(Players.getLocal().getLocation())){
					MiscDragons.toBank = MiscDragons.traverse = false;
					MiscDragons.banking = true;
					MiscDragons.chaosState = ChaosState.TO_HOLE;
				}
				if(Traversal.stepPath(VARROCK_CENTER_TO_GE,true))
					Task.sleep(2000);
				break;
			}
			}
		}
		if(MiscDragons.fromBank){
			if(MiscDragons.chaosState == null)
				MiscDragons.chaosState = ChaosState.TO_HOLE;
			switch(MiscDragons.chaosState){
			case TO_HOLE:{
				if(Players.getLocal().getLocation().getX() < 3139){
					MiscDragons.chaosState = ChaosState.TO_DITCH;
					break;
				}
				SceneObject hole = SceneEntities.getNearest(HOLE_ID);
				if(hole != null){
					if(Bank.isOpen()){
						Walking.walk(hole.getLocation());
					}
					if(hole.isOnScreen()){
						hole.interact("Climb-into");
						Task.sleep(2000);
					}
					else{
						Camera.turnTo(hole);
						Walking.walk(hole.getLocation());
						Task.sleep(1000);
					}
				}
				break;
			}
			case TO_DITCH:{//3522
				if(Players.getLocal().getLocation().getY() > 3522){
					MiscDragons.chaosState = ChaosState.TO_RIFT;
					break;
				}
				SceneObject ditch = SceneEntities.getNearest(DITCH_ID);
				if(ditch != null){
					if(ditch.isOnScreen()){
						ditch.interact("Cross");
						Task.sleep(4000);
						Walking.walk(RIFT_TILE);
					}
					else{
						Camera.turnTo(ditch);
						Walking.walk(DITCH_TILE);
						Task.sleep(1000);
					}
				}
				break;
			}
			case TO_RIFT:{
				if(Players.getLocal().getLocation().getY() > 4000){
					MiscDragons.chaosState = ChaosState.TO_PORTAL;
					break;
				}
				Walking.walk(RIFT_TILE);
				Task.sleep(2000);
				SceneObject rift = SceneEntities.getNearest(RIFT_ID);
				if(rift != null){
					if(rift.isOnScreen()){
						rift.interact("Enter");
						Task.sleep(2000);
					}
					else{
						Camera.turnTo(rift);
					}
				}
				break;
			}
			case TO_PORTAL:{
				if(Players.getLocal().getLocation().getX() > 3298
						&& Players.getLocal().getLocation().getY() < 5471){
					Walking.walk(CHAOS_GREEN_CENTER_TILE);
					MiscDragons.traverse = MiscDragons.fromBank = false;
					MiscDragons.fighting = true;
					MiscDragons.chaosState = null;
					break;
				}
				Walking.walk(CHAOS_PORTAL_TILE);
				Task.sleep(1000);
				SceneObject portal = SceneEntities.getNearest(new Filter<SceneObject>(){

					@Override
					public boolean accept(SceneObject found) {
						return(found.getId() == CHAOS_PORTAL_ID
								&& found.getLocation().getY() == 5463
								&& found.getLocation().getX() == 3290);
					}
				});
				if(portal != null){
					if(portal.isOnScreen()){
						portal.interact("Enter");
						Task.sleep(2000);
					}
					else{
						Camera.turnTo(portal);
					}
				}
				break;
			}
			}
		}
	}
	 */
	
}
