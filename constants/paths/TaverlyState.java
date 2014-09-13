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
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

public enum TaverlyState {
	TO_FALADOR,
	TO_FALADOR_BANK,
	TO_WALL,
	TO_TAVERLY_CAVE,
	TO_PIPE,
	TO_KNIGHT_GATE,
	TO_CAULDRON_GATE,
	TO_DUSTY_DOOR,
	TO_RESOURCE,
	TO_DRAGONS,
	TO_RESOURCE_DRAGONS;
	
	public static final int FALADOR_BOOTH_ID = 11758;
	public static final int CRUMBLING_WALL_ID = 11844;
    public static final int TAVERLY_CAVE_ID = 66991;
    public static final int BAT_GATE_ID = 77086;
    public static final int DUSTY_DOOR_ID = 2623;
    public static final int DUSTY_KEY_ID = 1590;
    public static final int PIPE_ID = 9293;
    public static final int FALADOR_TAB_ID = 8009;
    public static final int BLUE_MYSTERIOUS_ENTRANCE = 52852;
    public static final int RESOURCE_MYSTERIOUS_ENTRANCE = 52865;//52865
    
    public static final Tile FALADOR_BANK_TILE = new Tile(2945,3370,0);
    public static final Tile CRUMBLING_WALL_TILE = new Tile(2940,3355,0);
    public static final Tile TAVERLY_CAVE_TILE = new Tile(2886,3395,0);
    public static final Tile KNIGHT_GATE_TILE = new Tile(2885,9827,0);
    public static final Tile DUSTY_DOOR_TILE = new Tile(2924,9803,0);
    public static final Tile BLUE_DRAGON_TILE = new Tile(2902,9802,0);
    public static final Tile BLUE_RESOURCE_ENTRANCE = new Tile(2912,9810,0);
    public static final Tile PIPE_TILE = new Tile(2886,9799,0);
    public static final Tile BLUE_DRAGON_RESOURCE_TILE = new Tile(995,4499,0);

    public static final Tile[] WALL_TO_CAVE_PATH = {new Tile(2887,3395,0),new Tile(2890,3391,0),
    	new Tile(2894,3387,0),new Tile(2898,3384,0), new Tile(2903,3382,0),new Tile(2906,3380,0),
    	new Tile(2909,3377,0),new Tile(2911,3373,0),new Tile(2915,3372,0),new Tile(2919,3370,0),
    	new Tile(2924,3369,0),new Tile(2928,3369,0),new Tile(2931,3369,0),new Tile(2933,3366,0),
    	new Tile(2933,3362,0),  new Tile(2934,3358,0)};

    public static final Tile[] GATE_TO_DOOR_PATH = {new Tile(2924,9803,0),new Tile(2929,9803,0),
    	new Tile(2931,9799,0),new Tile(2933,9795,0),new Tile(2935,9791,0),new Tile(2936,9787,0),
    	new Tile(2933,9784,0),new Tile(2931,9780,0),new Tile(2930,9775,0),new Tile(2928,9771,0),
    	new Tile(2925,9768,0),new Tile(2924,9764,0),new Tile(2923,9760,0),new Tile(2923,9756,0),
    	new Tile(2928,9756,0),new Tile(2933,9757,0),new Tile(2935,9761,0),new Tile(2934,9766,0),
    	new Tile(2935,9771,0),new Tile(2935,9776,0),new Tile(2940,9777,0),new Tile(2944,9774,0),
    	new Tile(2949,9774,0),new Tile(2951,9778,0),new Tile(2952,9782,0),new Tile(2952,9787,0),
    	new Tile(2951,9792,0),new Tile(2949,9795,0),new Tile(2945,9797,0),new Tile(2943,9800,0),
    	new Tile(2941,9804,0),new Tile(2939,2908,0),new Tile(2938,9812,0),new Tile(2938,9817,0),
    	new Tile(2938,9821,0),new Tile(2937,9825,0),new Tile(2933,9826,0),new Tile(2931,9823,0),
    	new Tile(2927,9820,0),new Tile(2923,9818,0),new Tile(2919,9818,0),new Tile(2914,9819,0),
    	new Tile(2909,9819,0),new Tile(2904,9819,0),new Tile(2899,9819,0),new Tile(2895,9821,0)};

    public static final Area FALADOR_CENTER = new Area(new Tile(2957,3384,0), new Tile(2973,3372,0));
    public static final Area FALADOR_BANK_AREA = new Area(new Tile(2942,3374,0), new Tile(2950,3366,0));
    public static final Area TAVERLY_CAVE_ENTRANCE = new Area(new Tile(2882,9799,0),new Tile(2888,9794,0));
    public static final Area DUSTY_DOOR_AREA = new Area(new Tile(2918,9806,0),new Tile(2924,9800,0));
    public static final Area TAVERLY_BLUE_AREA = new Area(new Tile(2892,9813,0),new Tile(2913,9791,0));
    public static final Area BLUE_RESOURCE_DUNGEON = new Area(new Tile(961,4538,0),new Tile(1018,4485,0));

    @SuppressWarnings("incomplete-switch")
	public static void traverse(boolean resource, boolean shortcut){
    	if(MiscDragons.taverlyState == null)
    		MiscDragons.taverlyState = TaverlyState.TO_FALADOR;

    	if(MiscDragons.toBank){
    		if(FALADOR_CENTER.contains(Players.getLocal().getLocation()))
    			MiscDragons.taverlyState = TaverlyState.TO_FALADOR_BANK;
    		switch(MiscDragons.taverlyState){
    		case TO_FALADOR:{
    			if(FALADOR_CENTER.contains(Players.getLocal().getLocation())){
    				MiscDragons.taverlyState = TaverlyState.TO_FALADOR_BANK;
    				break;
    			}
    			if(Inventory.getCount(FALADOR_TAB_ID) > 0 
    					&& Inventory.getItem(FALADOR_TAB_ID).getWidgetChild().click(true)){
    				Traversal.waitFor(new Condition(){
						@Override
						public boolean accept() {
							return FALADOR_CENTER.contains(Players.getLocal().getLocation());
						}}, 5000);
    			}
    			break;
    		}
    		case TO_FALADOR_BANK:{
    			if(FALADOR_BANK_AREA.contains(Players.getLocal().getLocation())){
    				MiscDragons.toBank = MiscDragons.traverse = false;
    				MiscDragons.banking = true;
    				MiscDragons.taverlyState = TaverlyState.TO_WALL;
    				break;
    			}
    			Walking.walk(FALADOR_BANK_TILE);
    			Task.sleep(3000);
    			break;
    		}
    		}
    	}
    	if(MiscDragons.fromBank){
    		switch(MiscDragons.taverlyState){
    		case TO_WALL:{
    			if(Players.getLocal().getLocation().getX() < 2936){//we are over the wall
    				MiscDragons.taverlyState = TaverlyState.TO_TAVERLY_CAVE;
    				break;
    			}
    			Walking.walk(CRUMBLING_WALL_TILE);
    			Task.sleep(3000);
    			SceneObject wall = SceneEntities.getNearest(CRUMBLING_WALL_ID);
    			if(wall != null){
    				if(wall.isOnScreen() && wall.interact("Climb-over")){
    					Traversal.waitFor(new Condition(){
    						@Override
    						public boolean accept() {
    							return Players.getLocal().getLocation().getX() < 2936;
    						}}, 5000);
    				}
    				else{
    					Camera.turnTo(wall);
    				}
    			}
    			break;
    		}
    		case TO_TAVERLY_CAVE:{
    			if(TAVERLY_CAVE_ENTRANCE.contains(Players.getLocal())){
    				MiscDragons.taverlyState = shortcut? TaverlyState.TO_PIPE : TaverlyState.TO_KNIGHT_GATE;
    				break;
    			}
    			Traversal.stepPath(WALL_TO_CAVE_PATH, true);
    			Task.sleep(3000);
    			if(TAVERLY_CAVE_TILE.isOnMap()){
    				SceneObject cave = SceneEntities.getNearest(TAVERLY_CAVE_ID);
    				if(cave != null){
    					if(cave.isOnScreen() && cave.interact("Climb-down")){
    						Traversal.waitFor(new Condition(){
    							@Override
    							public boolean accept() {
    								return TAVERLY_CAVE_ENTRANCE.contains(Players.getLocal());
    							}}, 5000);
    					}
    					else
    						Camera.turnTo(cave);
    				}
    			}
    			break;
    		}
    		case TO_PIPE:{
    			if(Players.getLocal().getLocation().getX() > 2887){//past the pipe
    				MiscDragons.taverlyState = resource ? TaverlyState.TO_RESOURCE : TaverlyState.TO_DRAGONS;
    				break;
    			}
    			SceneObject pipe = SceneEntities.getNearest(PIPE_ID);
    			
    			if(pipe != null){
    				if(pipe.isOnScreen() && pipe.interact("Squeeze-through")){
    					Traversal.waitFor(new Condition(){
    						@Override
    						public boolean accept() {
    							return Players.getLocal().getLocation().getX() > 2887;
    						}}, 5000);
    				}
    				else{
    					Camera.turnTo(pipe);
    				}
    			}
    			Walking.walk(PIPE_TILE);
    			Camera.turnTo(pipe);
    			Task.sleep(2000);
    			break;
    		}
    		case TO_KNIGHT_GATE:{
    			if(Players.getLocal().getLocation().getX() > 2888){
    				MiscDragons.taverlyState = TaverlyState.TO_CAULDRON_GATE;
    				break;
    			}
    			SceneObject gate = SceneEntities.getNearest(new Filter<SceneObject>(){
    				@Override
    				public boolean accept(SceneObject gate) {
    					return (gate.getId() == BAT_GATE_ID
    							&& gate.getLocation().getX() == 2888);
    				}
    			});
    			if(gate != null){
    				if(gate.isOnScreen() && gate.interact("Open")){
    					Traversal.waitFor(new Condition(){
    						@Override
    						public boolean accept() {
    							return Players.getLocal().getLocation().getX() > 2888;
    						}}, 5000);
    				}
    				else{
    					Camera.turnTo(gate);
    				}
    			}
    			Walking.walk(KNIGHT_GATE_TILE);
    			Task.sleep(3000);
    			break;
    		}
    		case TO_CAULDRON_GATE:{
    			if(Players.getLocal().getLocation().getY() < 9826
    					&& Players.getLocal().getLocation().getX() > 2890){
    				MiscDragons.taverlyState = TaverlyState.TO_DUSTY_DOOR;
    				break;
    			}
    			SceneObject gate2 = SceneEntities.getNearest(new Filter<SceneObject>(){
    				@Override
    				public boolean accept(SceneObject gate2) {
    					//gates  have same id, using Y-coordinate to differentiate them
    					return(gate2.getId() == BAT_GATE_ID
    							&& gate2.getLocation().getY() == 9825);
    				}
    			});
    			if(gate2 != null){
    				if(gate2.isOnScreen() && gate2.interact("Open")){
    					Traversal.waitFor(new Condition(){
    						@Override
    						public boolean accept() {
    							return Players.getLocal().getLocation().getY() < 9826
    			    					&& Players.getLocal().getLocation().getX() > 2890;
    						}}, 6000);
    				}else{
    					Camera.turnTo(gate2);
    				}
    				Traversal.stepPath(GATE_TO_DOOR_PATH, false);
    			}
    			break;
    		}
    		case TO_DUSTY_DOOR:{
    			if(DUSTY_DOOR_AREA.contains(Players.getLocal().getLocation())){
    				MiscDragons.taverlyState = resource ? TaverlyState.TO_RESOURCE : TaverlyState.TO_DRAGONS;
    				break;
    			}
    			if(Calculations.distanceTo(DUSTY_DOOR_TILE) < 10){
    				SceneObject door = SceneEntities.getNearest(DUSTY_DOOR_ID);
    				if(door != null){
    					if(door.isOnScreen()){
    						Inventory.getItem(DUSTY_KEY_ID).getWidgetChild().interact("Use");
    						Task.sleep(1500);
    						door.click(true);
    						Traversal.waitFor(new Condition(){
        						@Override
        						public boolean accept() {
        							return DUSTY_DOOR_AREA.contains(Players.getLocal().getLocation());
        						}}, 5000);
    						Walking.walk(BLUE_DRAGON_TILE);
    					}
    				}
    			}
    			Traversal.stepPath(GATE_TO_DOOR_PATH, false);
    			Task.sleep(2000);
    			break;
    		}
    		case TO_DRAGONS:{
    			if(TAVERLY_BLUE_AREA.contains(Players.getLocal().getLocation())){
    				MiscDragons.fromBank = MiscDragons.traverse = false;
    				MiscDragons.fighting = true;
    				MiscDragons.taverlyState = null;
    				break;
    			}
    			Walking.walk(BLUE_DRAGON_TILE);
    			Task.sleep(2000);
    			break;
    		}
    		case TO_RESOURCE:{
    			if(SceneEntities.getNearest(RESOURCE_MYSTERIOUS_ENTRANCE) != null){
    				MiscDragons.taverlyState = TaverlyState.TO_RESOURCE_DRAGONS;
    				break;
    			}
    			Walking.walk(BLUE_RESOURCE_ENTRANCE);
    			Task.sleep(2000);
    			SceneObject entrance = SceneEntities.getNearest(BLUE_MYSTERIOUS_ENTRANCE);
    			if(entrance != null){
    				entrance.interact("Enter");
    				Traversal.waitFor(new Condition(){
						@Override
						public boolean accept() {
							return SceneEntities.getNearest(RESOURCE_MYSTERIOUS_ENTRANCE) != null;
						}}, 5000);
    			}
    			break;
    		}
    		case TO_RESOURCE_DRAGONS:{
    			if(Calculations.distanceTo(BLUE_DRAGON_RESOURCE_TILE) < 8){
    				MiscDragons.fromBank =  MiscDragons.traverse = false;
    				MiscDragons.fighting = true;
    				MiscDragons.taverlyState = null;
    				break;
    			}
    			Walking.walk(BLUE_DRAGON_RESOURCE_TILE);
    			Task.sleep(2000);
    			break;
    		}
    		}

    	}
    }
}
