package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ManaCrystal extends Entity{

	GamePanel gp;
	
	public OBJ_ManaCrystal(GamePanel gp) {
		super(gp);
		this.gp =gp;
		
		type = type_pickUpOnly;
		name = "Mana Crystal";
		value = 1;
		down1 = setup("/objects/manacrystal_full",gp.tileSize,gp.tileSize);
		image = setup("/objects/manacrystal_full",gp.tileSize,gp.tileSize);
		image2 = setup("/objects/manacrystal_blank",gp.tileSize,gp.tileSize);
	}
	
	public boolean use(Entity e) {
		gp.playSE(2);
		gp.ui.addMessage("Mana +" + value);
		e.mana += value;
		return true;
	}
}