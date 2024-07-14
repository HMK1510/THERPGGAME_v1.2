package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Armor_Leather extends Entity{

	public OBJ_Armor_Leather(GamePanel gp) {
		super(gp);
		
		type = type_armor;
		name = "Leather Armor";
		down1 = setup("/objects/armor",gp.tileSize,gp.tileSize);
		defenseValue = 1;
		description = name+":\n-Made by leather.";
		price = 35;
	}

}
