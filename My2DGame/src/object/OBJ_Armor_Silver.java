package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Armor_Silver extends Entity{

	public OBJ_Armor_Silver(GamePanel gp) {
		super(gp);
		
		type = type_armor;
		name = "Silver Armor";
		down1 = setup("/objects/armor_silver",gp.tileSize,gp.tileSize);
		defenseValue = 2;
		description = name+":\n-Made by silver.";
		price = 100;
	}

}
