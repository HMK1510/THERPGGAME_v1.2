package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Staff_Normal extends Entity{

	public OBJ_Staff_Normal(GamePanel gp) {
		super(gp);
		
		type = type_staff;
		name = "Normal Staff";
		down1 = setup("/objects/staff",gp.tileSize,gp.tileSize);
		attackValue = 1;
		attackArea.width = 24;
		attackArea.height = 24;
		description = name + ":\n-An normal staff.";
		price = 75;
		knockBackPower = 4;
	}

}
