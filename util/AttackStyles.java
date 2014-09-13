package miscdragons.util;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;

public class AttackStyles {
	public static int AUTOCAST_SETTING = 108;
	public static int SPELLBOOK_WIDGET = 192;
	public static int COMBAT_SORT_BUTTON = 19;
	enum Spells{
		MAGIC_DART(56),
		WIND_SURGE(84),
		WATER_SURGE(87),
		EARTH_SURGE(89),
		FIRE_SURGE(91);
		
		private int _widgetchild;
		
		private Spells(int widgetchild){
			_widgetchild = widgetchild;
		}
		
		public static boolean isAutoCasting(){
			return Settings.get(43,4) == 4;
		}
		
		public void setAutoCast(){
			Tabs.MAGIC.open();
			Task.sleep(700);
			Widgets.get(SPELLBOOK_WIDGET,_widgetchild).click(true);
			Task.sleep(700);
			Tabs.INVENTORY.open();
			Task.sleep(700);
		}
		
		public static void sortByCombat(){
			Tabs.MAGIC.open();
			Task.sleep(700);
			if(!Widgets.get(SPELLBOOK_WIDGET).validate()){
				sortByCombat();
				return;
			}
			Mouse.click(Widgets.get(SPELLBOOK_WIDGET, COMBAT_SORT_BUTTON).getCentralPoint(),true);
			Task.sleep(700);
			Tabs.INVENTORY.open();
			Task.sleep(700);
		}
		
		public enum Rune{
			AIR(556),WATER(555),EARTH(557),FIRE(554),MIND(558),CHAOS(562),DEATH(560),BLOOD(565);
			
			private int _id;
			
			private Rune(int id){
				_id = id;
			}
			
			public int getId(){
				return _id;
			}
		}
	}
	
	public static void setRetaliate(boolean on){
		Tabs.ATTACK.open();
		Task.sleep(700);
		if(Widgets.get(884).validate() 
				&& !Widgets.get(884).getChild(13).getText().contains(on?"On":"Off")){
			Widgets.get(884).getChild(13).click(true);
			Task.sleep(500);
		}
		Tabs.INVENTORY.open();
		Task.sleep(700);
	}

}

