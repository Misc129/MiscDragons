package miscdragons.nodes;

import miscdragons.MiscDragons;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.Item;

public class Eat extends Node{

	@Override
	public boolean activate() {
		return Players.getLocal().getHpPercent() < 70;
	}

	@Override
	public void execute() {
		if(Inventory.getCount(MiscDragons.foodId) < 2){
			MiscDragons.fighting = false;
			MiscDragons.traverse = MiscDragons.toBank = true;
		}
		Item eat = Inventory.getItem(MiscDragons.foodId);
		if(eat != null && eat.getWidgetChild().click(true))
			sleep(1500);
	}
}
