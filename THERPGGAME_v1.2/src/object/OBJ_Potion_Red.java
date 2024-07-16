package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity{

	GamePanel gp;
	
	public OBJ_Potion_Red(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		type = type_consumable;
		name = "Red Potion";
		value = 5;
		down1 = setup("/objects/potion",gp.tileSize,gp.tileSize);
		description = name + ":\nHeals your life by " + value + ".";
		price = 25;
		stackable = true;
	}
	
	public boolean use(Entity e) {
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "You drink the " + name + "!\n" + "Your life has been recovered by " + value + ".";
		e.life += value;
		gp.playSE(2);
		return true;
	}
}
