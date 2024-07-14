package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin extends Entity{

	GamePanel gp;
	
	public OBJ_Coin(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_pickUpOnly;
		name = "Coin";
		value = 1;
		down1 = setup("/objects/coin",gp.tileSize,gp.tileSize);
	}
	
	public boolean use(Entity e) {
		
		gp.playSE(1);
		gp.ui.addMessage("Coin +" + value);
		gp.player.coin += value;
		return true;
	}
}
