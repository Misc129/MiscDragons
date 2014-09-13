package miscdragons.constants.paths;

import miscdragons.MiscDragons;
import miscdragons.util.Condition;
import miscdragons.util.Traversal;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

public enum BrimhavenState {
	TO_ARDOUGNE,
	TO_ARDOUGNE_BANK,
	TO_ARDOUGNE_DOCKS,
	TO_KARMAJA,
	TO_DUNGEON_ENTRANCE,
	TO_DUNGEON,
	TO_VINES_1,
	TO_STEPPING_STONES,
	TO_VINES_2,
	TO_BLOCK_BALANCE,
	TO_DRAGONS;
	
	public static final int ARDOUGNE_TAB_ID = 8011;
	public static final int ARDOUGNE_CAPTAIN_ID = 381;
	public static final int SANIBOCH_ID = 1595;
	public static final int DUNGEON_ENTRANCE_ID = 5083;
	public static final int STEEL_HATCHET_ID = 1353;
	public static final int VINES_1_ID = 77371;
	public static final int STEPPING_STONES_ID = 77570;
	public static final int BLOCK_BALANCE_ID = 77573;
	public static final int VINES_2_ID = 77375;
	
	public static final Tile ARDOUGNE_BANK_TILE = new Tile(2653,3283,0);
	public static final Tile ARDOUGNE_DOCKS_TILE = new Tile(2680,3275,0);
	public static final Tile DUNGEON_ENTRANCE_TILE = new Tile(2747,3152,0);
	public static final Tile VINES_1_TILE = new Tile(0,0,0);//TODO
	public static final Tile BLOCK_BALANCE_TILE = new Tile(0,0,0);//TODO
	public static final Tile DRAGON_TILE = new Tile(0,0,0);//TODO
	
	public static final Tile[] STONES_TO_VINES_2 = {};//TODO
	
	public static final Area ARDOUGNE_BANK_AREA = new Area(
			new Tile(2649,3280,0), new Tile(2657,3288,0));
	public static final Area ARDOUGNE_DOCKS_AREA = new Area();//TODO
	public static final Area DUNGEON_OUTSIDE_AREA = new Area();//TODO
	public static final Area DRAGON_AREA = new Area();//TODO
	
	@SuppressWarnings("incomplete-switch")
	public static void traverse(){
		
		if(MiscDragons.toBank){
			switch(MiscDragons.brimhavenState){
			case TO_ARDOUGNE:{
				break;
			}
			case TO_ARDOUGNE_BANK:{
				break;
			}
			}
		}
		
		if(MiscDragons.fromBank){
			switch(MiscDragons.brimhavenState){
			case TO_ARDOUGNE_DOCKS:{
				if(ARDOUGNE_DOCKS_AREA.contains(Players.getLocal())){
					MiscDragons.brimhavenState = TO_KARMAJA;
					break;
				}
				Walking.walk(ARDOUGNE_DOCKS_TILE);
				Task.sleep(2000);
				break;
			}
			case TO_KARMAJA:{
				//TODO pay fare, dialogue menu
				break;
			}
			case TO_DUNGEON_ENTRANCE:{
				if(DUNGEON_OUTSIDE_AREA.contains(Players.getLocal())){
					MiscDragons.brimhavenState = TO_DUNGEON;
					break;
				}
				Walking.walk(DUNGEON_ENTRANCE_TILE);
				Task.sleep(2000);
				break;
			}
			case TO_DUNGEON:{
				//TODO pay saniboch, dialogue menu
				break;
			}
			case TO_VINES_1:{
				//TODO
				/*
				 * if(player coordinates past vines)
				 *  	state = TO_STEPPING_STONES
				 *  	break
				 */
				Walking.walk(VINES_1_TILE);
				Task.sleep(2000);
				SceneObject vines1 = SceneEntities.getNearest(new Filter<SceneObject>(){
					@Override
					public boolean accept(SceneObject object) {
						return object.getId() == VINES_1_ID;
					}});
				if(vines1 != null){
					if(Calculations.distanceTo(vines1) > 6)
						break;
					if(vines1.isOnScreen() && vines1.interact("Chop-down")){//TODO "Chop-down"?
						Traversal.waitFor(new Condition(){
							@Override
							public boolean accept() {
								// TODO Auto-generated method stub
								//return(player coordinates past vines)
								return false;
							}}, 5000);
					}
					else
						Camera.turnTo(vines1);
				}
				break;
			}
			case TO_STEPPING_STONES:{
				/*TODO
				 * if(player coordinates past stones)
				 * 		state = TO_VINES_2
				 * 		break
				 */
				Walking.walk(VINES_1_TILE);
				Task.sleep(2000);
				SceneObject stones = SceneEntities.getNearest(new Filter<SceneObject>(){
					@Override
					public boolean accept(SceneObject object) {
						return object.getId() == STEPPING_STONES_ID;
					}});
				if(stones != null){
					if(Calculations.distanceTo(stones) > 7)
						break;
					if(stones.isOnScreen() && stones.interact("Jump-across")){//TODO "Jump-across"?
						Traversal.waitFor(new Condition(){
							@Override
							public boolean accept() {
								// TODO Auto-generated method stub
								// return(player is jumping or is across stones)
								return false;
							}}, 5000);
					}
					else
						Camera.turnTo(stones);
				}
				break;
			}
			case TO_VINES_2:{
				/*TODO
				 * if(player is across vines)
				 * 		state = TO_BLOCK_BALANCE
				 * 		break
				 */
				Traversal.stepPath(STONES_TO_VINES_2, true);
				Task.sleep(2000);
				SceneObject vines2 = SceneEntities.getNearest(new Filter<SceneObject>(){
					@Override
					public boolean accept(SceneObject object) {
						return object.getId() == VINES_2_ID;
					}});
				if(vines2 != null){
					if(Calculations.distanceTo(vines2) > 7)
						break;
					if(vines2.isOnScreen() && vines2.interact("Chop-down")){
						Traversal.waitFor(new Condition(){
							@Override
							public boolean accept() {
								// TODO Auto-generated method stub
								// return (player coordinates past vines2)
								return false;
							}}, 5000);
					}
					else
						Camera.turnTo(vines2);
				}
				break;
			}
			case TO_BLOCK_BALANCE:{
				/*TODO
				 * if(player crossing balance)
				 * 		state = TO_DRAGONS
				 * 		break
				 */
				Walking.walk(BLOCK_BALANCE_TILE);
				Task.sleep(2000);
				SceneObject blockBalance = SceneEntities.getNearest(new Filter<SceneObject>(){
					@Override
					public boolean accept(SceneObject object) {
						return object.getId() == BLOCK_BALANCE_ID;
					}});
				if(blockBalance != null){
					if(Calculations.distanceTo(blockBalance) > 7)
						break;
					if(blockBalance.isOnScreen() && blockBalance.interact("Walk-across")){
						Traversal.waitFor(new Condition(){
							@Override
							public boolean accept() {
								// TODO Auto-generated method stub
								// return(player crossing/crossed balance
								return false;
							}}, 5000);
					}
					else
						Camera.turnTo(blockBalance);
				}
				break;
			}
			case TO_DRAGONS:{
				if(DRAGON_AREA.contains(Players.getLocal())){
					MiscDragons.traverse = MiscDragons.fromBank = false;
					MiscDragons.fighting = true;
					MiscDragons.brimhavenState = null;
				}
				Walking.walk(DRAGON_TILE);
				Task.sleep(2000);
				break;
			}
			}
		}
	}
}
