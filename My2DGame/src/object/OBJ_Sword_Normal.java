package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity{

	public OBJ_Sword_Normal(GamePanel gp) {
		super(gp);
		
		type = type_sword;
		name = "Normal Sword";
		down1 = setup("/objects/sword_normal",gp.tileSize,gp.tileSize);
		attackValue = 1;
		attackArea.width = 24;
		attackArea.height = 24;
		description = name + ":\n-An old sword maybe you \ncan use it to cut a tree.";
		price = 20;
		knockBackPower = 10;
	}
	
}
