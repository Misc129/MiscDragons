package miscdragons.util;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.Tile;

public class Traversal {
	
	public static boolean stepPath(Tile[] revpath, boolean linear){
		if(linear){
			for(Tile t : revpath){
				if(t.isOnMap()){
					return(Walking.walk(t));
				}
			}
		}
		else{
			int lag = 0;
			for(int i = 0; i < revpath.length; lag = i, i++){
				if(revpath[i].distance(Players.getLocal().getLocation()) < 8){
					return (Walking.walk(revpath[lag]));
				}
			}
		}
		return false;
	}
	
	public static void waitFor(Condition condition, int timeout){
		for(int i = 0; i < timeout; i += 200){
			if(condition.accept())
				return;
			Task.sleep(200);
		}
	}
}
