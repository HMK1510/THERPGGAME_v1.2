package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bow_Normal extends Entity{

	public OBJ_Bow_Normal(GamePanel gp) {
		super(gp);
		
		type = type_bow;
		name = "Normal Bow";
		down1 = setup("/objects/bow_normal",gp.tileSize,gp.tileSize);
		attackValue = 1;
		attackArea.width = 24;
		attackArea.height = 24;
		description = name + ":\n-An normal bow.";
	}

}
