package miscdragons.nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.Item;

import miscdragons.MiscDragons;
import miscdragons.constants.LootDef;

public class Loot extends Node{

	@Override
	public boolean activate() {
		if(readyToBank()){
			MiscDragons.fighting = false;
			MiscDragons.toBank = MiscDragons.traverse = true;
		}
		
		return MiscDragons.fighting && LootDef.findLoot() != null;
	}

	@Override
	public void execute() {
		GroundItem g = LootDef.findLoot();
		if(g != null){
			if(dropVial())
				sleep(1000);                  
			if(Inventory.getCount() == 28 && eat())
				sleep(1000);
			if(!g.isOnScreen()){
				Walking.walk(g.getLocation());
				Camera.turnTo(g);
			}
			if(g.interact("Take",g.getGroundItem().getName()))
				sleep(2500);
			updateCount();
		}
	}

	private boolean dropVial(){
		Item vial = Inventory.getItem(LootDef.VIAL_ID);
		if(vial == null)
			return false;
		return (vial.getWidgetChild().interact("Drop"));
	}
	
	private boolean eat(){
		Item food = Inventory.getItem(MiscDragons.foodId);
		if(food == null)
			return false;
		return (food.getWidgetChild().click(true));
	}

	private void updateCount(){
		if(MiscDragons.numInvHides < Inventory.getCount(MiscDragons.hideId)){
			int h = Inventory.getCount(MiscDragons.hideId) - MiscDragons.numInvHides;
			MiscDragons.numHides += h;
			MiscDragons.profit += h * MiscDragons.hidePrice;
			MiscDragons.numInvHides = Inventory.getCount(MiscDragons.hideId);
		}
		if(MiscDragons.numInvBones < Inventory.getCount(LootDef.BONES_ID)){
			int b = Inventory.getCount(LootDef.BONES_ID) - MiscDragons.numInvBones;
			MiscDragons.numBones += b;
			MiscDragons.profit += b * MiscDragons.bonePrice;
			MiscDragons.numInvBones = Inventory.getCount(LootDef.BONES_ID);
		}
	}

	private boolean readyToBank(){
		return (Inventory.getCount() == 28 && Inventory.getCount(MiscDragons.foodId) == 0);
	}
}
