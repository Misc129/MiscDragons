package miscdragons;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import miscdragons.constants.DragonType;
import miscdragons.constants.paths.*;
import miscdragons.nodes.*;

import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;

@Manifest(name = "MiscDragons", description = "Kills dragons.", version = 0.0, authors = { "Misc" })
public class MiscDragons extends ActiveScript implements PaintListener, MessageListener {
	/*
	 * 			TODO
	 * -messagelistener (antifire)
	 * -BrimHavenState
	 * 
	 * 			implement later
	 * -warning pop system for missing equipment and other errors
	 * -options for charms, effigies,etc
	 * 
	 * 			bugs:
	 * -takes wrong portal in chaos tunnels? or gets randomly teleported (messagelistener then teleport)
	 */
	public static boolean start,traverse,toBank,fromBank,banking,fighting,looting,boostAttackSuper,
	boostStrengthSuper,boostDefenceSuper,boostAttackExtreme,boostStrengthExtreme,boostDefenceExtreme,
	antifireResist,praying,boostAntifire,hidePaint;
	
	public static int foodId,numFood,numPray,hideId,numHides,submitHides,numBones,numInvHides,numInvBones,
    hidePrice,bonePrice,profit,profitHour,submitProfit,baseAttack,baseStrength,baseDefence,startExp,
    expGained,expHour;
	
	public static long startTime,millis,hours,minutes,seconds,last,updateMinute;
	
	public static ArrayList<Integer> prepInventory = new ArrayList<Integer>();
	public static ArrayList<Integer> loot = new ArrayList<Integer>();
	
	public static String dragonName;
	public static String dragonTitle;
	
	public static DragonType dragonType;
	
	public static TaverlyState taverlyState;
	public static ChaosState chaosState;
	public static ZanarisState zanarisState;
	public static BrimhavenState brimhavenState;
	
	public static Area bankArea;
	public static Tile centerTile;
	
	private static List<Node> jobList = Collections.synchronizedList(new ArrayList<Node>());
	private static Tree container = null;
	
	private BufferedImage paint;
	
	@Override
	public void onStart(){
		startTime = System.currentTimeMillis();
		try {
			paint = ImageIO.read(new URL("http://i.imgur.com/mnb9r.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Node begin = new Begin();
		getContainer().submit(begin);
		begin.join();

		if(!start)//gui closed
			stop();
		
		provide(new Eat(), new Loot(), new Fighting(), new Potion(), new Traverse(), new Banking());
	}
	
	@Override
	public int loop() {
		if (container != null) {
			final Node job = container.state();
			if (job != null) {
				container.set(job);
				getContainer().submit(job);
				job.join();
			}
		}
		return 0;
	}
	
	public void provide(Node... jobs){
		for(Node n : jobs){
			if(!jobList.contains(n)){
				jobList.add(n);
			}
		}
		container = new Tree(jobList.toArray(new Node[jobList.size()]));
	}

	@Override
	public void messageReceived(MessageEvent m) {
		// TODO Auto-generated method stub
		/*
		 * if(m.contains(antifire ran out)
		 * 		antifireResist = false;
		 * if(m.contains(you are now protected)
		 * 		antifireResist = true;
		 */
	}
	
	@Override
	public void onRepaint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
        millis = System.currentTimeMillis() - startTime;
        if(millis != 0){
        	profitHour = (int) ((profit * 3600D) / millis);
        }
        hours = millis / (1000 * 60 * 60);
        millis -= hours * (1000 * 60 * 60);
        minutes = millis / (1000 * 60);
        millis -= minutes * (1000 * 60);
        seconds = millis / 1000;
       
        g.setFont(new Font(Font.SERIF,Font.BOLD,14));
        g.setColor(Color.BLACK);
        g.drawImage(paint, 0, 0, null);
        
        //g.drawString("x:"+Mouse.getX()+", y:"+Mouse.getY(), 100, 300);
        
        g.drawString("" + hours +":"+ minutes + ":" + seconds, 227, 19);
        g.drawString("" + profit+"("+profitHour+"k/hr)", 227, 42);  
        g.drawString("" + numHides, 400, 19);
        g.drawString("" + numBones, 400, 42);
//        g.setColor(Color.BLACK);
//        g.fillRect(0, 0, 300, 50);
//        g.setColor(Color.WHITE);
//        g.drawString("Runtime:" + hours +":"+ minutes + ":" + seconds, 5, 15);
//        g.drawString("Hides:" + numHides + " Bones:" + numBones, 5, 30);
//        g.drawString("nHides:"+numInvHides+" nBones:"+numInvBones, 150, 30);
//        g.drawString("Profit:" + profit+"("+profitHour+"k/hr)", 5, 45);   
	}
}
