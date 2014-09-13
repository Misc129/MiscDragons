package miscdragons.nodes;

import miscdragons.MiscDragons;
import miscdragons.constants.DragonType;
import miscdragons.constants.LootDef;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Prayer;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class Fighting extends Node{

	@Override
	public boolean activate() {
		if(dragonDying()){
			System.out.println("waiting for dragon to die");
			sleep(3000);
			return false;
		}
		
		return MiscDragons.fighting && !playerHasTarget();
	}

	@Override
	public void execute() {
		if(!Walking.isRunEnabled() && Walking.getEnergy() > 50)
			Walking.setRun(true);
		
		if(MiscDragons.praying){
			if(!Prayer.isQuickOn() && Prayer.getPoints() > 200){
				Prayer.toggleQuick(true);
				sleep(1000);
			}
		}

		NPC dragon = findDragon();
		if(dragon != null){
			if(MiscDragons.dragonType == DragonType.GREEN_CHAOS && dragon.getHpPercent() > 50){
				if(!dragon.isOnScreen()){
					Walking.walk(dragon.getLocation());
					Camera.turnTo(dragon);
				}
				if(dragon.interact("Attack"))
					sleep(500);
			}
			else if(!dragon.isInCombat() && Players.getLocal().getInteracting() == null){
				NPC aggressor = getAggressor();
				//if(Player is already being attacked by a different dragon)
				if(aggressor != null && (aggressor.getId() != dragon.getId())){
					if(aggressor.interact("Attack")){
						sleep(500);
					}
					return;
				}
				if(!dragon.isOnScreen()){
					Walking.walk(dragon.getLocation());
					Camera.turnTo(dragon);
				}
				if(dragon.interact("Attack")){
					sleep(500);
					while(Players.getLocal().isMoving()){
						sleep(400);
					}
				}
			}
		}
		//else if(nothing else to do) then, go back to center tile
		else if(Calculations.distanceTo(MiscDragons.centerTile) > 20
                                        && Players.getLocal().getInteracting() == null
                                        && LootDef.findLoot() == null){
			Walking.walk(MiscDragons.centerTile);
            sleep(1000);
		}
	}

	private NPC findDragon(){
		return(NPCs.getNearest(
                new Filter<NPC>(){
                        @Override
                        public boolean accept(NPC found) {
                                return(found.getName().contains(MiscDragons.dragonName)
                                                && !found.isInCombat());
                        }
                }));
	}

	private NPC getAggressor(){
		return(NPCs.getNearest(new Filter<NPC>(){

			@Override
			public boolean accept(NPC n) {
				if(n.getInteracting() == null)
					return false;
				else{
					return(n.getInteracting().getId() == Players.getLocal().getId());
				}
			}
		}));
	}
	
	private boolean dragonDying(){
            if(Players.getLocal().getInteracting() != null)
                    return(Players.getLocal().getInteracting().getHpPercent() == 0);
            return false;
	}
	
	private boolean playerHasTarget(){
		return (Players.getLocal().getInteracting() != null);
	}
}
