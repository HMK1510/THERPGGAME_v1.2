package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class NPC extends Entity{
	
	public NPC(GamePanel gp) {
		
		super(gp);
		
		direction = "down";
		speed = 2;
		
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
		
		up1 = setup("/npc/mage_up_1",gp.tileSize,gp.tileSize);
		up2 = setup("/npc/mage_up_2",gp.tileSize,gp.tileSize);
		down1 = setup("/npc/mage_down_1",gp.tileSize,gp.tileSize);
		down2 = setup("/npc/mage_down_2",gp.tileSize,gp.tileSize);
		left1 = setup("/npc/mage_left_1",gp.tileSize,gp.tileSize);
		left2 = setup("/npc/mage_left_2",gp.tileSize,gp.tileSize);
		right1 = setup("/npc/mage_right_1",gp.tileSize,gp.tileSize);
		right2 = setup("/npc/mage_right_2",gp.tileSize,gp.tileSize);
		
	}
	
	public void setDialogue() {
		
		dialogues[0] = "Hello, lad.";
		dialogues[1] = "So you've come to this island to \nfind the treasure?";
		dialogues[2] = "I used to be a great wizard but now ... \nI'm just a NPC";
		dialogues[3] = "Well, good luck on you.";
		
	}
	
	public void setAction() {
		
		if(onPath == true) {
			
//			int goalCol = 12;
//			int goalRow = 9;
			
			int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
			int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
			
			searchPath(goalCol, goalRow);
			
		}
		else {
			
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
	}
	
	public void speak() {
		
		super.speak();
		
		onPath = true;
	}
	
}
