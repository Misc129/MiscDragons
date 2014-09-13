package miscdragons;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import miscdragons.constants.DragonType;
import miscdragons.constants.LootDef;
import miscdragons.constants.paths.BrimhavenState;
import miscdragons.constants.paths.ChaosState;
import miscdragons.constants.paths.TaverlyState;
import miscdragons.constants.paths.ZanarisState;
import miscdragons.util.Potions;

public class GUI extends JFrame{
		JLabel foodIdLabel;
		JLabel foodNumLabel;
		JLabel attPotionLabel;
		JLabel strPotionLabel;
		JLabel defPotionLabel;
		JLabel prayPotionLabel;
		JLabel antifireLabel;
		JTextField foodIdField;
		JTextField numFoodField;
		JTextField prayPotionField;
		JComboBox<String> attPotions;
		JComboBox<String> strPotions;
		JComboBox<String> defPotions;
		JComboBox<String> antifirePotions;

		JLabel dragonLabel;
		JComboBox<String> dragonOptions;;
		JTextArea typeDescription;
		JButton startButton;
		private static final long serialVersionUID = 1L;
		public GUI(){
			setSize(320, 230);
			setTitle("MiscDragons");
			setResizable(false);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			createContents();
			setVisible(true);
		}

		private void createContents(){
			setLayout(new BorderLayout());
			JPanel east = new JPanel();
			east.setPreferredSize(new Dimension(170,220));
			east.setLayout(new GridLayout(8,2));

			JPanel center = new JPanel();
			center.setPreferredSize(new Dimension(150,200));
			center.setLayout(new FlowLayout());

			String[] dragonChoices = {"","Black-Zanaris","Blue-Taverly","Blue-Taverly(S)",
					"Blue-Taverly-Resource","Blue-Taverly-Resource(S)","Green-Chaos-Tunnels",
					"Red-Brimhaven"};
			dragonLabel = new JLabel("Dragon Type:");
			dragonOptions = new JComboBox<String>(dragonChoices);
			dragonOptions.setSize(new Dimension(100,dragonOptions.getHeight()));
			typeDescription = new JTextArea();
			typeDescription.setPreferredSize(new Dimension(140,120));
			typeDescription.setBackground(Color.LIGHT_GRAY);
			typeDescription.setLineWrap(true);
			typeDescription.setWrapStyleWord(true);
			typeDescription.setEditable(false);
			startButton = new JButton("Start");
			startButton.setEnabled(false);
			center.add(dragonLabel);
			center.add(dragonOptions);
			center.add(typeDescription);
			center.add(startButton);

			foodIdLabel = new JLabel("Food ID:",SwingConstants.RIGHT);
			foodNumLabel = new JLabel("Food Qty:",SwingConstants.RIGHT);
			attPotionLabel = new JLabel("Att Potion:",SwingConstants.RIGHT);
			strPotionLabel = new JLabel("Str Potion:",SwingConstants.RIGHT);
			defPotionLabel = new JLabel("Def Potion:",SwingConstants.RIGHT);
			prayPotionLabel = new JLabel("Pray Potions:",SwingConstants.RIGHT);
			antifireLabel = new JLabel("Antifire:",SwingConstants.RIGHT);
			foodIdField = new JTextField("0");
			numFoodField = new JTextField("0");
			prayPotionField = new JTextField("0");

			foodIdField.setBackground(Color.LIGHT_GRAY);
			numFoodField.setBackground(Color.LIGHT_GRAY);
			prayPotionField.setBackground(Color.LIGHT_GRAY);
			String[] potionOptions = {"None","Super","Extreme"};
			String[] antifireOptions = {"None","Regular"};
			attPotions = new JComboBox<String>(potionOptions);
			strPotions = new JComboBox<String>(potionOptions);
			defPotions = new JComboBox<String>(potionOptions);
			antifirePotions = new JComboBox<String>(antifireOptions);

			east.add(foodIdLabel);
			east.add(foodIdField);
			east.add(foodNumLabel);
			east.add(numFoodField);
			east.add(prayPotionLabel);
			east.add(prayPotionField);
			east.add(attPotionLabel);
			east.add(attPotions);
			east.add(strPotionLabel);
			east.add(strPotions);
			east.add(defPotionLabel);
			east.add(defPotions);
			east.add(antifireLabel);
			east.add(antifirePotions);

			MyActionListener listener = new MyActionListener();
			foodIdField.addActionListener(listener);
			numFoodField.addActionListener(listener);
			attPotions.addActionListener(listener);
			strPotions.addActionListener(listener);
			defPotions.addActionListener(listener);
			dragonOptions.addActionListener(listener);
			startButton.addActionListener(listener);

			add(east,BorderLayout.EAST);
			add(center,BorderLayout.CENTER);	
		}
		private class MyActionListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent action) {
				if(action.getSource() == dragonOptions){
					if(dragonOptions.getSelectedItem().equals("")){
						typeDescription.setText("");
						startButton.setEnabled(false);
						MiscDragons.dragonType = null;
					}
					if(dragonOptions.getSelectedItem().equals("Black-Zanaris")){
						typeDescription.setText("Start in zanaris bank. Reqs: raw chickens.");
						startButton.setEnabled(true);
					}
					if(dragonOptions.getSelectedItem().equals("Blue-Taverly")){
						typeDescription.setText("Start in falador bank. Reqs: falador tabs, dusty key.");
						startButton.setEnabled(true);
					}
					if(dragonOptions.getSelectedItem().equals("Blue-Taverly(S)")){
						typeDescription.setText("Agility shortcut. Start in falador bank. Reqs: falador tabs.");
						startButton.setEnabled(true);
					}
					if(dragonOptions.getSelectedItem().equals("Blue-Taverly-Resource")){
						typeDescription.setText("Start in falador bank. Reqs: falador tabs, dusty key, 60 dungeoneering.");
						startButton.setEnabled(true);
					}
					if(dragonOptions.getSelectedItem().equals("Blue-Taverly-Resource(S)")){
						typeDescription.setText("Agility shortcut. Start falador bank. Reqs: falador tabs, 60 dungeoneering.");
						startButton.setEnabled(true);
					}
					if(dragonOptions.getSelectedItem().equals("Green-Chaos-Tunnels")){
						typeDescription.setText("Start NW GE. Reqs: varrock tabs, 21 agility.");
						startButton.setEnabled(true);
					}
					if(dragonOptions.getSelectedItem().equals("Red-Brimhaven")){
						//TODO
						typeDescription.setText("in development");
					}
				}
				if(action.getSource() == startButton){
					if(dragonOptions.getSelectedItem().equals("Black-Zanaris")){
						MiscDragons.dragonType = DragonType.BLACK_ZANARIS;
						MiscDragons.dragonName = "Black";
						MiscDragons.dragonTitle = "Zanaris Black";
						MiscDragons.prepInventory.add(ZanarisState.CHICKEN_ID);
						MiscDragons.hideId = LootDef.BLACK.getHideId();
						MiscDragons.bankArea = ZanarisState.ZANARIS_BANK_AREA;
						MiscDragons.centerTile = ZanarisState.ZANARIS_BLACK_DRAGON_CENTER;
						MiscDragons.zanarisState = ZanarisState.TO_ZANARIS_BANK;			
					}
					if(dragonOptions.getSelectedItem().equals("Blue-Taverly")){
						MiscDragons.dragonType = DragonType.BLUE_TAVERLY;
						MiscDragons.dragonName = "Blue";
						MiscDragons.dragonTitle = "Taverly Blue";
						MiscDragons.prepInventory.add(TaverlyState.FALADOR_TAB_ID);
						MiscDragons.prepInventory.add(TaverlyState.DUSTY_KEY_ID);
						MiscDragons.hideId = LootDef.BLUE.getHideId();
						MiscDragons.bankArea = TaverlyState.FALADOR_BANK_AREA;
						MiscDragons.centerTile = TaverlyState.BLUE_DRAGON_TILE;
						MiscDragons.taverlyState = TaverlyState.TO_FALADOR_BANK;
					}
					if(dragonOptions.getSelectedItem().equals("Blue-Taverly(S)")){
						MiscDragons.dragonType = DragonType.BLUE_TAVERLY_SHORTCUT;
						MiscDragons.dragonName = "Blue";
						MiscDragons.dragonTitle = "Taverly Blue(Shortcut)";
						MiscDragons.prepInventory.add(TaverlyState.FALADOR_TAB_ID);
						MiscDragons.hideId = LootDef.BLUE.getHideId();
						MiscDragons.bankArea = TaverlyState.FALADOR_BANK_AREA;
						MiscDragons.centerTile = TaverlyState.BLUE_DRAGON_TILE;
						MiscDragons.taverlyState = TaverlyState.TO_FALADOR_BANK;
					}
					if(dragonOptions.getSelectedItem().equals("Blue-Taverly-Resource")){
						MiscDragons.dragonType = DragonType.BLUE_TAVERLY_RESOURCE;
						MiscDragons.dragonName = "Blue";
						MiscDragons.dragonTitle = "Taverly Resource Blue";
						MiscDragons.prepInventory.add(TaverlyState.FALADOR_TAB_ID);
						MiscDragons.prepInventory.add(TaverlyState.DUSTY_KEY_ID);
						MiscDragons.hideId = LootDef.BLUE.getHideId();
						MiscDragons.bankArea = TaverlyState.FALADOR_BANK_AREA;
						MiscDragons.centerTile = TaverlyState.BLUE_DRAGON_RESOURCE_TILE;
						MiscDragons.taverlyState = TaverlyState.TO_FALADOR_BANK;
					}
					if(dragonOptions.getSelectedItem().equals("Blue-Taverly-Resource(S)")){
						MiscDragons.dragonType = DragonType.BLUE_TAVERLY_RESOURCE_SHORTCUT;
						MiscDragons.dragonName = "Blue";
						MiscDragons.dragonTitle = "Taverly Resource Blue(Shortcut)";
						MiscDragons.prepInventory.add(TaverlyState.FALADOR_TAB_ID);
						MiscDragons.hideId = LootDef.BLUE.getHideId();
						MiscDragons.bankArea = TaverlyState.FALADOR_BANK_AREA;
						MiscDragons.centerTile = TaverlyState.BLUE_DRAGON_RESOURCE_TILE;
						MiscDragons.taverlyState = TaverlyState.TO_FALADOR_BANK;
					}
					if(dragonOptions.getSelectedItem().equals("Green-Chaos-Tunnels")){
						MiscDragons.dragonType = DragonType.GREEN_CHAOS;
						MiscDragons.dragonName = "Green";
						MiscDragons.dragonTitle = "Chaos Tunnels Green";
						MiscDragons.prepInventory.add(ChaosState.VARROCK_TAB_ID);
						MiscDragons.hideId = LootDef.GREEN.getHideId();
						MiscDragons.bankArea = ChaosState.GE_BANK_AREA;
						MiscDragons.centerTile = ChaosState.CHAOS_GREEN_CENTER_TILE;
						MiscDragons.chaosState = ChaosState.TO_GE_BANK;
					}
					if(dragonOptions.getSelectedItem().equals("Red-Brimhaven")){
						MiscDragons.dragonType = DragonType.RED_BRIMHAVEN;
						MiscDragons.dragonName = "Red";
						MiscDragons.dragonTitle = "Red Brimhaven";
						MiscDragons.prepInventory.add(BrimhavenState.STEEL_HATCHET_ID);
						MiscDragons.hideId = LootDef.RED.getHideId();
						MiscDragons.bankArea = BrimhavenState.ARDOUGNE_BANK_AREA;
						MiscDragons.centerTile = BrimhavenState.DRAGON_TILE;
						MiscDragons.brimhavenState = BrimhavenState.TO_ARDOUGNE_BANK;
					}
					MiscDragons.loot = LootDef.getLoot();
					if(attPotions.getSelectedItem().equals("Super")){
						MiscDragons.boostAttackSuper = true;
						MiscDragons.prepInventory.add(Potions.Potion.SUPER_ATTACK.maxDoseId());
					}
					if(strPotions.getSelectedItem().equals("Super")){
						MiscDragons.boostStrengthSuper = true;
						MiscDragons.prepInventory.add(Potions.Potion.SUPER_STRENGTH.maxDoseId());
					}
					if(defPotions.getSelectedItem().equals("Super")){
						MiscDragons.boostDefenceSuper = true;
						MiscDragons.prepInventory.add(Potions.Potion.SUPER_DEFENCE.maxDoseId());
					}
					if(attPotions.getSelectedItem().equals("Extreme")){
						MiscDragons.boostAttackExtreme = true;
						MiscDragons.prepInventory.add(Potions.Potion.EXTREME_ATTACK.maxDoseId());
					}
					if(strPotions.getSelectedItem().equals("Extreme")){
						MiscDragons.boostStrengthExtreme = true;
						MiscDragons.prepInventory.add(Potions.Potion.EXTREME_STRENGTH.maxDoseId());
					}
					if(defPotions.getSelectedItem().equals("Extreme")){
						MiscDragons.boostDefenceExtreme = true;
						MiscDragons.prepInventory.add(Potions.Potion.EXTREME_DEFENCE.maxDoseId());
					}
					if(antifirePotions.getSelectedItem().equals("Regular")){
						MiscDragons.boostAntifire = true;
						MiscDragons.prepInventory.add(Potions.Potion.ANTIFIRE.maxDoseId());
					}
					try{
						MiscDragons.foodId = Integer.parseInt(foodIdField.getText());
						MiscDragons.numFood = Integer.parseInt(numFoodField.getText());
						MiscDragons.numPray = Integer.parseInt(prayPotionField.getText());
					}catch(Exception e){
						System.out.println("non integer field input");
					}
					if(MiscDragons.numPray > 0){
						MiscDragons.praying = true;
					}
					if(MiscDragons.numFood > (28 - MiscDragons.prepInventory.size()))//someone can't do basic math
						MiscDragons.numFood = 28 - MiscDragons.prepInventory.size();
					MiscDragons.start = true;
						setVisible(false);
						dispose();
					}
				}

			}
		}
