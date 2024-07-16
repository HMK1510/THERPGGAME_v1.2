package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tent extends Entity{
	
	GamePanel gp;
	
	public OBJ_Tent(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_consumable;
		name = "Tent";
		down1 = setup("/objects/tent",gp.tileSize,gp.tileSize);
		description = name + ":\n-You can sleep until \nnext morning.";
		price = 300;
		stackable = true;
	}
	
	public boolean use(Entity entity) {
		
		gp.gameState = gp.dialogueState;
		
		if(gp.eManager.lighting.dayState == gp.eManager.lighting.night) {
			
			gp.gameState = gp.sleepState;
			gp.playSE(14);
			gp.player.life = gp.player.maxLife;
			gp.player.mana = gp.player.maxMana;
			gp.player.getSleepingImage(down1);
			return true;
		}
		else {
			gp.ui.currentDialogue = "You can't sleep now.";
			return false;
		}
	}
}
