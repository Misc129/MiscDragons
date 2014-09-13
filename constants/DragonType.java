package miscdragons.constants;

public enum DragonType {
	BLACK_ZANARIS(new int[]{54,4674,4675,4676}),
	BLUE_TAVERLY(new int[]{4683,4682,55}),
	BLUE_TAVERLY_SHORTCUT(new int[]{4683,4682,55}),
	BLUE_TAVERLY_RESOURCE(new int[]{4683,4682,55}),
	BLUE_TAVERLY_RESOURCE_SHORTCUT(new int[]{4683,4682,55}),
	GREEN_CHAOS(new int[]{}),
	RED_BRIMHAVEN(new int[]{});
	
	DragonType(int[] ids){
		_ids = ids;
	}
	
	private int[] _ids;
	
	public int[] getIds(){
		return _ids;
	}
}
