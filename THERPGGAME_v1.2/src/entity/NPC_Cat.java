package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Cat extends Entity{
	public NPC_Cat(GamePanel gp) {
		
		super(gp);
		
		direction = "down";
		speed = 1;
		
		solidArea = new Rectangle();
		solidArea.x = 10;
		solidArea.y = 24;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 28;
		solidArea.height = 20;
		getImage();
		setDialogue();
	}
	
	public void getImage() {
		
		up1 = setup("/npc/cat_up_1",gp.tileSize,gp.tileSize);
		up2 = setup("/npc/cat_up_2",gp.tileSize,gp.tileSize);
		down1 = setup("/npc/cat_down_1",gp.tileSize,gp.tileSize);
		down2 = setup("/npc/cat_down_2",gp.tileSize,gp.tileSize);
		left1 = setup("/npc/cat_left_1",gp.tileSize,gp.tileSize);
		left2 = setup("/npc/cat_left_2",gp.tileSize,gp.tileSize);
		right1 = setup("/npc/cat_right_1",gp.tileSize,gp.tileSize);
		right2 = setup("/npc/cat_right_2",gp.tileSize,gp.tileSize);
		
	}
	
	public void setDialogue() {
		
		dialogues[0] = "meow meow";
		dialogues[1] = "owf owf";
		dialogues[2] = "meo meo";
		dialogues[3] = "Yum yum nigg";
		
	}
	
	public void setAction() {
		
		actionLockCounter++;
		
		if(actionLockCounter == 30) {
			
			Random random = new Random();
			int i = random.nextInt(100)+1; // pick a random number from 1 to 100
			
			if(i <= 25) {
				direction = "up";
			}
			if(i > 25 && i <= 50) {
				direction = "left";
			}
			if(i > 50 && i <= 75) {
				direction = "down";
			}
			if(i > 75 && i <= 100) {
				direction = "right";
			}
			
			actionLockCounter = 0;
		}
	}
	
	public void speak() {
		
		super.speak();
	}
}
