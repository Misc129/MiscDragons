package miscdragons.nodes;

import miscdragons.MiscDragons;
import miscdragons.util.Potions;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Prayer;
import org.powerbot.game.api.methods.tab.Skills;

public class Potion extends Node{

	@Override
	public boolean activate() {
		return MiscDragons.fighting;
	}

	@Override
	public void execute() {
		if(MiscDragons.praying){
			if(Prayer.getPoints() < Skills.getRealLevel(Skills.PRAYER) * 5){
				Potions.Potion.PRAYER.sip();
				sleep(1500);
			}
			
			if(!Prayer.isQuickOn() && Prayer.getPoints() > 100){
				Prayer.toggleQuick(true);
				sleep(1500);
			}
		}

		if(MiscDragons.boostAntifire && !MiscDragons.antifireResist){
			Potions.Potion.ANTIFIRE.sip();
			sleep(1500);
		}
		
		if(MiscDragons.boostAttackSuper && (Skills.getLevel(Skills.ATTACK) < MiscDragons.baseAttack + 6)){
			Potions.Potion.SUPER_ATTACK.sip();
			sleep(1500);
		}
		
		if(MiscDragons.boostStrengthSuper && (Skills.getLevel(Skills.STRENGTH) < MiscDragons.baseStrength + 6)){
			Potions.Potion.SUPER_STRENGTH.sip();
			sleep(1500);
		}
		
		if(MiscDragons.boostDefenceSuper && (Skills.getLevel(Skills.DEFENSE) < MiscDragons.baseDefence + 6)){
			Potions.Potion.SUPER_DEFENCE.sip();
			sleep(1500);
		}
		
		if(MiscDragons.boostAttackExtreme && (Skills.getLevel(Skills.ATTACK) < MiscDragons.baseAttack + 9)){
			Potions.Potion.EXTREME_ATTACK.sip();
			sleep(1500);
		}
		
		if(MiscDragons.boostStrengthExtreme && (Skills.getLevel(Skills.STRENGTH) < MiscDragons.baseStrength + 9)){
			Potions.Potion.EXTREME_STRENGTH.sip();
			sleep(1500);
		}
		
		if(MiscDragons.boostDefenceExtreme && (Skills.getLevel(Skills.DEFENSE) < MiscDragons.baseDefence + 9)){
			Potions.Potion.EXTREME_DEFENCE.sip();
			sleep(1500);
		}
	}
}

