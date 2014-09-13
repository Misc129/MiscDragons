package miscdragons.nodes;

import miscdragons.MiscDragons;
import miscdragons.constants.paths.ChaosState;
import miscdragons.constants.paths.TaverlyState;
import miscdragons.constants.paths.ZanarisState;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.tab.Prayer;

public class Traverse extends Node {

	@Override
	public boolean activate() {
		return MiscDragons.traverse;
	}

	@Override
	public void execute() {
		if(!Walking.isRunEnabled() && Walking.getEnergy() > 50)
			Walking.setRun(true);
		
		if(Prayer.isQuickOn())
			Prayer.toggleQuick(false);
		
		MiscDragons.numInvBones = MiscDragons.numInvHides = 0;
		
		switch(MiscDragons.dragonType){
		case BLACK_ZANARIS:{
			ZanarisState.traverse();
			break;
		}
		case BLUE_TAVERLY:{
			TaverlyState.traverse(false, false);
			break;
		}
		case BLUE_TAVERLY_SHORTCUT:{
			TaverlyState.traverse(false, true);
			break;
		}
		case BLUE_TAVERLY_RESOURCE:{
			TaverlyState.traverse(true, false);
			break;
		}
		case BLUE_TAVERLY_RESOURCE_SHORTCUT:{
			TaverlyState.traverse(true, true);
			break;
		}
		case GREEN_CHAOS:{
			ChaosState.traverse();
			break;
		}
		case RED_BRIMHAVEN:{
			//TODO
			break;
		}
		}
	}

}
