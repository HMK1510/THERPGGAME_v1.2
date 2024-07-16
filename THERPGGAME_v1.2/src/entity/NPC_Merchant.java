package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.OBJ_Armor_Silver;
import object.OBJ_Potion_Red;
import object.OBJ_Staff_Normal;

public class NPC_Merchant extends Entity{
	
public NPC_Merchant(GamePanel gp) {
		
		super(gp);
		
		direction = "down";
		speed = 0;
		
		solidArea = new Rectangle();
		solidArea.x = 10;
		solidArea.y = 24;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 28;
		solidArea.height = 20;
		getImage();
		setDialogue();
		setItems();
	}
	
	public void getImage() {
		
		up1 = setup("/npc/merchant_down_1",gp.tileSize,gp.tileSize);
		up2 = setup("/npc/merchant_down_2",gp.tileSize,gp.tileSize);
		down1 = setup("/npc/merchant_down_1",gp.tileSize,gp.tileSize);
		down2 = setup("/npc/merchant_down_2",gp.tileSize,gp.tileSize);
		left1 = setup("/npc/merchant_down_1",gp.tileSize,gp.tileSize);
		left2 = setup("/npc/merchant_down_2",gp.tileSize,gp.tileSize);
		right1 = setup("/npc/merchant_down_1",gp.tileSize,gp.tileSize);
		right2 = setup("/npc/merchant_down_2",gp.tileSize,gp.tileSize);
		
	}
	
	public void setDialogue() {
		
		dialogues[0] = "Wassup brother, so you found me.\nI have some good stuff.\nDo you want to trade?";
	}
	
	public void setItems() {
		
		inventory.add(new OBJ_Potion_Red(gp));
		inventory.add(new OBJ_Staff_Normal(gp));
		inventory.add(new OBJ_Armor_Silver(gp));
	}
	
	public void speak() {
		
		super.speak();
		gp.gameState = gp.tradeState;
		gp.ui.npc = this; //to easy access to NPC inventory and other stuff
	}
}
