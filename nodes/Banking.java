package miscdragons.nodes;

import miscdragons.MiscDragons;
import miscdragons.util.Potions;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;

public class Banking extends Node {

	@Override
	public boolean activate() {
		return MiscDragons.banking;
	}

	@Override
	public void execute() {
		if(validateInventory()){
			MiscDragons.banking = false;
			MiscDragons.fromBank = MiscDragons.traverse = true;
			return;
		}
		if(!MiscDragons.bankArea.contains(Players.getLocal().getLocation())){//fail safe
			MiscDragons.banking = false;
			MiscDragons.toBank = true;
		}

		Bank.open();
		Task.sleep(3000);
		Bank.depositInventory();
		for(int itemId : MiscDragons.prepInventory){
			Bank.withdraw(itemId, 1);
			sleep(1000);
		}
		if(MiscDragons.praying){
			Bank.withdraw(Potions.Potion.PRAYER.maxDoseId(), MiscDragons.numPray);
			sleep(2000);
		}
		if(MiscDragons.foodId != 0){
			Bank.withdraw(MiscDragons.foodId, MiscDragons.numFood);
			sleep(3000);
		}
	}

	private boolean validateInventory(){
		for(int item : MiscDragons.prepInventory){
			if(Inventory.getCount(item) == 0)
				return false;
		}
		return(Inventory.getCount(MiscDragons.foodId) == MiscDragons.numFood
				&& Inventory.getCount(Potions.Potion.PRAYER.maxDoseId()) == MiscDragons.numPray);
	}
}
