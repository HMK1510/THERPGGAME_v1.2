package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity{
	
	GamePanel gp;
	
	public OBJ_Key(GamePanel gp) {
		
		super(gp);
		
		this.gp = gp;
		
		type = type_consumable;
		name = "Key";
		down1 = setup("/objects/key",gp.tileSize,gp.tileSize);
		description = name+":\n-It just a normal key.";
		stackable = true;
	}
	
	public boolean use(Entity e) {
		
		gp.gameState = gp.dialogueState;
		
		int objIndex = getDetected(e, gp.obj, "Door");
		
		if(objIndex != 999) {
			gp.ui.currentDialogue = "You use the " + name + " and open the door.";
			gp.playSE(3);
			gp.obj[gp.currentMap][objIndex] = null;
			return true;
		}
		else {
			gp.ui.currentDialogue = "What are you doing?";
			return false;
		}
	}
}
