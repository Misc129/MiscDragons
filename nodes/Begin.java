package miscdragons.nodes;

import java.io.IOException;

import miscdragons.GUI;
import miscdragons.MiscDragons;
import miscdragons.constants.LootDef;
import miscdragons.util.Price;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.input.Mouse.Speed;
import org.powerbot.game.api.methods.tab.Attack;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Camera;

public class Begin extends Node {

	@Override
	public boolean activate() {
		return !MiscDragons.start;
	}

	@Override
	public void execute() {
		MiscDragons.start = false;
		
		//test();
		
		GUI gui = null;
        try{
            gui = new GUI();
        }catch(Exception e){
        	System.out.println(e.getMessage());
        	System.out.println(e.getStackTrace());
        }
        
        if(gui == null)
        	return;
        
        while(!MiscDragons.start && gui.isVisible())
        	MiscDragons.sleep(100);
        
        MiscDragons.baseAttack = Skills.getRealLevel(Skills.ATTACK);
        MiscDragons.baseStrength = Skills.getRealLevel(Skills.STRENGTH);
        MiscDragons.baseDefence = Skills.getRealLevel(Skills.DEFENSE);
        MiscDragons.startTime = System.currentTimeMillis();
        MiscDragons.startExp = Skills.getExperience(Skills.ATTACK)
        		+Skills.getExperience(Skills.STRENGTH)
        		+Skills.getExperience(Skills.DEFENSE);
        try {
			MiscDragons.hidePrice = Price.getPrice(MiscDragons.hideId);
			MiscDragons.bonePrice = Price.getPrice(LootDef.BONES_ID);
			System.out.println("hide price:"+MiscDragons.hidePrice+"\nbone price:"+MiscDragons.bonePrice);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        Mouse.setSpeed(Speed.FAST);
        Attack.setAutoRetaliate(true);
        Camera.setPitch(25);
        
        MiscDragons.traverse = MiscDragons.toBank = true;
	}

	public void test(){
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
