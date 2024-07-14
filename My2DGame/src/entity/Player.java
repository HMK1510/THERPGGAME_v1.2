package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.OBJ_Armor_Leather;
import object.OBJ_Armor_Silver;
import object.OBJ_Arrow;
import object.OBJ_Bow_Normal;
import object.OBJ_Fireball;
import object.OBJ_Key;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Wood;
import object.OBJ_Staff_Normal;
import object.OBJ_Sword_Normal;

public class Player extends Entity{
	
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
//	public int hasKey = 0;
	int standCounter = 0;
	public boolean attackCanceled = false;
	public boolean lightUpdated = false;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		//Player collision
		solidArea = new Rectangle();
		solidArea.x = 10;
		solidArea.y = 24;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 28;
		solidArea.height = 20;
		
		//Attack area
		attackArea.width = 24;
		attackArea.height = 24; 
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
		setItems();
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
//		worldX = gp.tileSize * 12;
//		worldY = gp.tileSize * 11;
		gp.currentMap = 0;
		defaultSpeed = 3; 
		speed = defaultSpeed;
		direction = "down";
		
		//Player status
		level = 1;
		maxLife = 6;
		life = maxLife;
		maxMana = 4;
		mana = maxMana;
		ammo = 10;
		strength = 1; //The more strength the character have, the more damage he gives.
		dexterity = 1; //The more dexterity the character have, the less damage he receive.
		exp = 0;
		nextLevelExp = 5;
		coin = 500;
		currentWeapon = new OBJ_Sword_Normal(gp);
		currentShield = new OBJ_Shield_Wood(gp);
		currentArmor = new OBJ_Armor_Leather(gp);
		projectile = new OBJ_Fireball(gp);
		projectile1 = new OBJ_Arrow(gp);
		attack = getAttack(); //The total attack value is decided by strength and weapon
		defense = getDefense(); //The total defense value is decided by dexterity and shield
	}
	
	public void setDefaultPositions() {
		
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		direction = "down";
	}
	
	public void restoreLifeMana() {
		
		life = maxLife;
		mana = maxMana;
		invincible = false;
	}
	
	public void setItems() {
		
		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentArmor);
		inventory.add(new OBJ_Key(gp));
		inventory.add(new OBJ_Staff_Normal(gp));
		inventory.add(new OBJ_Bow_Normal(gp));
		inventory.add(new OBJ_Armor_Silver(gp));
		inventory.add(new OBJ_Potion_Red(gp));
	}
	
	public int getAttack() {
		attackArea = currentWeapon.attackArea;
		return attack = strength * currentWeapon.attackValue;
	}
	
	public int getDefense() {
		
		return defense = dexterity * currentArmor.defenseValue;
	}
	
	public void getSleepingImage(BufferedImage image) {
		
		up1 = image;
		up2 = image;
		down1 = image;
		down2 = image;
		left1 = image;
		left2 = image;
		right1 = image;
		right2 = image;
	}
	
	public void getPlayerImage() {
		
		up1 = setup("/player/human_up_1",gp.tileSize,gp.tileSize);
		up2 = setup("/player/human_up_2",gp.tileSize,gp.tileSize);
		down1 = setup("/player/human_down_1",gp.tileSize,gp.tileSize);
		down2 = setup("/player/human_down_2",gp.tileSize,gp.tileSize);
		left1 = setup("/player/human_left_1",gp.tileSize,gp.tileSize);
		left2 = setup("/player/human_left_2",gp.tileSize,gp.tileSize);
		right1 = setup("/player/human_right_1",gp.tileSize,gp.tileSize);
		right2 = setup("/player/human_right_2",gp.tileSize,gp.tileSize);
		
	}
	
	public void getPlayerAttackImage() {
		
		if(currentWeapon.type == type_bow) {
			attackUp1 = setup("/player/human_bow_up_1",gp.tileSize,gp.tileSize*2);
			attackUp2 = setup("/player/human_bow_up_2",gp.tileSize,gp.tileSize*2);
			attackDown1 = setup("/player/human_bow_down_1",gp.tileSize,gp.tileSize*2);
			attackDown2 = setup("/player/human_bow_down_2",gp.tileSize,gp.tileSize*2);
			attackLeft1 = setup("/player/human_bow_left_1",gp.tileSize*2,gp.tileSize);
			attackLeft2 = setup("/player/human_bow_left_2",gp.tileSize*2,gp.tileSize);
			attackRight1 = setup("/player/human_bow_right_1",gp.tileSize*2,gp.tileSize);
			attackRight2 = setup("/player/human_bow_right_2",gp.tileSize*2,gp.tileSize);
		}
		
		if(currentWeapon.type == type_sword) {
			attackUp1 = setup("/player/human_attack_up_1",gp.tileSize,gp.tileSize*2);
			attackUp2 = setup("/player/human_attack_up_2",gp.tileSize,gp.tileSize*2);
			attackDown1 = setup("/player/human_attack_down_1",gp.tileSize,gp.tileSize*2);
			attackDown2 = setup("/player/human_attack_down_2",gp.tileSize,gp.tileSize*2);
			attackLeft1 = setup("/player/human_attack_left_1",gp.tileSize*2,gp.tileSize);
			attackLeft2 = setup("/player/human_attack_left_2",gp.tileSize*2,gp.tileSize);
			attackRight1 = setup("/player/human_attack_right_1",gp.tileSize*2,gp.tileSize);
			attackRight2 = setup("/player/human_attack_right_2",gp.tileSize*2,gp.tileSize);
		}
		
		if(currentWeapon.type == type_staff) {
			attackUp1 = setup("/player/human_staff_up_1",gp.tileSize,gp.tileSize*2);
			attackUp2 = setup("/player/human_staff_up_2",gp.tileSize,gp.tileSize*2);
			attackDown1 = setup("/player/human_staff_down_1",gp.tileSize,gp.tileSize*2);
			attackDown2 = setup("/player/human_staff_down_2",gp.tileSize,gp.tileSize*2);
			attackLeft1 = setup("/player/human_staff_left_1",gp.tileSize*2,gp.tileSize);
			attackLeft2 = setup("/player/human_staff_left_2",gp.tileSize*2,gp.tileSize);
			attackRight1 = setup("/player/human_staff_right_1",gp.tileSize*2,gp.tileSize);
			attackRight2 = setup("/player/human_staff_right_2",gp.tileSize*2,gp.tileSize);
		}
	}
	
	public void update() {
		
		if(attacking == true) {
			attacking();
		}
		else if(fakeAttacking == true) {
			fakeAttacking();
		}
		else if(keyH.upPressed == true||keyH.downPressed == true||keyH.leftPressed == true||keyH.rightPressed == true || keyH.spacePressed == true) {
			
			if(keyH.upPressed == true) {
				direction = "up";				
			}else if(keyH.downPressed == true) {
				direction = "down";				
			}else if(keyH.leftPressed == true) {
				direction ="left";				
			}else if(keyH.rightPressed == true) {
				direction ="right";				
			}
			
			
			//Check tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			//Check object collision
			int objIndex = gp.cChecker.checkObject(this,true);
			pickUpObject(objIndex);
			
			//Check NPC collision
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			//Check monster collision
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			
			//Check interactive tile collision
			gp.cChecker.checkEntity(this, gp.iTile);
			
			//Check event
			gp.eHandler.checkEvent();
			
			//If collision is false, player can move
			if(collisionOn == false && keyH.spacePressed == false) {
				switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
			}
			
			if(keyH.spacePressed == true && attackCanceled == false) {
				
				gp.playSE(7);
				attacking = true;
				spriteCounter = 0;
			}
			
			attackCanceled = false;
			
			gp.keyHandler.spacePressed = false;
			
			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNum  == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		else {
			standCounter++;
			
			if(standCounter == 20) {
				spriteNum = 1;
				standCounter = 0;
			}
		}
		
		if(gp.keyHandler.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30 
				&& currentWeapon.type == type_staff && projectile.haveResource(this) == true) {
			
			fakeAttacking = true;
			
			//Set default coordinates, direction, and user 
			projectile.set(worldX, worldY, direction, true, this);
				
			//Subtract the cost mana, ammo etc)
			projectile.subtractResource(this);
			
			//Check vacancy
			for(int i = 0; i < gp.projectile[1].length; i++) {
				if(gp.projectile[gp.currentMap][i] == null) {
					gp.projectile[gp.currentMap][i] = projectile;
					break;
				}
			}
			
			
			shotAvailableCounter = 0;
				
			gp.playSE(10);
				
		}
		
		if(gp.keyHandler.shotKeyPressed == true && projectile1.alive == false && shotAvailableCounter == 30 
				&& currentWeapon.type == type_bow && projectile1.haveResource(this) == true) {
			
			fakeAttacking = true;	
				
			//Set default coordinates, direction, and user 
			projectile1.set(worldX, worldY, direction, true, this);
				
			//Subtract the cost mana, ammo etc)
			projectile1.subtractResource(this);
			
			//Check vacancy
			for(int i = 0; i < gp.projectile[1].length; i++) {
				if(gp.projectile[gp.currentMap][i] == null) {
					gp.projectile[gp.currentMap][i] = projectile1;
					break;
				}
			}
				
			shotAvailableCounter = 0;
				
			gp.playSE(7);
				
		}
		
		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		
		if(shotAvailableCounter  < 30) {
			shotAvailableCounter++;
		}
		
		if(life > maxLife) {
			life = maxLife; 
		}
		
		if(mana > maxMana) {
			mana = maxMana; 
		}
		
		if(life <= 0) {
			gp.gameState = gp.gameOverState;
			gp.ui.commandNum = -1;
			gp.stopMusic();
			gp.playSE(12);
		}
	}
	
	//Treasure Hunter game
	/*public void pickUpObject(int i) {
		if(i != 999) {
			String objName = gp.obj[i].name;
			
			switch(objName) {
			case "Key":
				gp.playSE(1);
				hasKey ++;
				gp.obj[i] = null;
				gp.ui.showMessage("+ 1 key !");
				break;
			case "Door":
				if(hasKey > 0) {
					gp.playSE(3);
					gp.obj[i] = null;
					hasKey --;
					gp.ui.showMessage("You opened the door!");
				}
				else {
					gp.ui.showMessage("Go find a key!");
				}
				break;
			case "Boots":
				gp.playSE(2);
				speed += 2;
				gp.obj[i] = null;
				gp.ui.showMessage("+ 2 speed");
				break;
			case "Chest":
				gp.ui.gameFinished = true;
				gp.stopMusic();
				gp.playSE(4);
				break;
			}
		}
	}*/
	
	public void attacking() {
		
		spriteCounter++;
		
		if(spriteCounter <= 5) { 
			spriteNum = 1;
		}
		if(spriteCounter > 5 && spriteCounter <= 25) { 
			spriteNum = 2;
			
			//Save the current worldX, worldY, solidArea
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			//Adjust player worldX, worldY for attackArea
			switch(direction) {
			case "up": worldY -= attackArea.height; break;
			case "down": worldY += attackArea.height; break;
			case "left": worldX -= attackArea.width; break;
			case "right": worldX += attackArea.width; break;
			}
			
			//Attack area become solid area
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			
			//Check monster collision with the updated worldX/Y and solidArea
			int monsterIndex = gp.cChecker.checkEntity(this,gp.monster);
			damageMonster(monsterIndex, attack, currentWeapon.knockBackPower);
			
			//Check interactive tile collision
			int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
			damageInteractiveTile(iTileIndex);
			
			int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
			damageProjectile(projectileIndex);
			
			//After checking collision, restore the original data
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		if(spriteCounter > 25) { 
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	
	public void fakeAttacking() {
		
		spriteCounter++;
		
		if(spriteCounter <= 5) { 
			spriteNum = 1;
		}
		if(spriteCounter > 5 && spriteCounter <= 25) { 
			spriteNum = 2;
		}
		if(spriteCounter > 25) { 
			spriteNum = 1;
			spriteCounter = 0;
			fakeAttacking = false;
		}
	}
	
	public void pickUpObject(int i) {
		
		if(i != 999) {
			
			//Pick up only items
			if(gp.obj[gp.currentMap][i].type == type_pickUpOnly) {
				
				gp.obj[gp.currentMap][i].use(this);
				gp.obj[gp.currentMap][i] = null;
			}
			
			//Obstacle
			else if(gp.obj[gp.currentMap][i].type == type_obstacle) {				
				if(keyH.spacePressed == true) {
					attackCanceled = true;
					gp.obj[gp.currentMap][i].interact();
				}
			}
			
			//Inventory items
			else {
				
				String txt;
				
				if(canObtainItem(gp.obj[gp.currentMap][i]) == true) {

					gp.playSE(1);
					txt = "Got a " + gp.obj[gp.currentMap][i].name + "!";
				}
				else {
					txt = "Your bag is full!";
				}
				
				gp.ui.addMessage(txt);
				gp.obj[gp.currentMap][i] = null;
			}	
		}
	}
	
	public void interactNPC(int i) {
		
		if(gp.keyHandler.spacePressed == true) {
			
			if(i != 999) {
				attackCanceled = true;
				gp.gameState = gp.dialogueState;
				gp.npc[gp.currentMap][i].speak();
			}
		}
	}
	
	public void contactMonster(int i) {
		
		if(i != 999) {
			
			if(invincible == false && gp.monster[gp.currentMap][i].dying == false) {
				gp.playSE(6);
				
				int damage = gp.monster[gp.currentMap][i].attack - defense;
				if(damage < 0) {
					damage = 0;
				}
				
				life -= damage;
				invincible = true;
			}
			
		}
	}
	
	public void damageMonster(int i, int attack, int knockBackPower) {
		
		if(i != 999) {
			
			if(gp.monster[gp.currentMap][i].invincible == false) {
				
				gp.playSE(5);
				
				if(knockBackPower > 0) {
					
					knockBack(gp.monster[gp.currentMap][i], knockBackPower);
				}
				
				int damage = attack - gp.monster[gp.currentMap][i].defense;
				if(damage < 0) {
					damage = 0;
				}
				
				gp.monster[gp.currentMap][i].life -= damage;
				gp.ui.addMessage(damage + " damage!");
				
				gp.monster[gp.currentMap][i].invincible = true;
				gp.monster[gp.currentMap][i].damageReaction();
				
				if(gp.monster[gp.currentMap][i].life <= 0) {
					gp.monster[gp.currentMap][i].life = 0;
					gp.monster[gp.currentMap][i].dying = true;
					gp.ui.addMessage("You killed the " + gp.monster[gp.currentMap][i].name + "!");
					gp.ui.addMessage("Exp + " + gp.monster[gp.currentMap][i].exp);
					exp += gp.monster[gp.currentMap][i].exp;
					checkLevelUp();
				}
			}
		}
	}
	
	public void knockBack(Entity e, int knockBackPower) {
		
		e.direction = direction;
		e.speed += knockBackPower;
		e.knockBack = true;
	}
	
	public void damageInteractiveTile(int i) {
		
		if(i != 999 && gp.iTile[gp.currentMap][i].destructible == true && gp.iTile[gp.currentMap][i].isCorrectItem(this) == true && gp.iTile[gp.currentMap][i].invincible == false) {
			
			gp.iTile[gp.currentMap][i].playSE();
			gp.iTile[gp.currentMap][i].life--;
			gp.iTile[gp.currentMap][i].invincible = true;
			
			//Generate particle
			generateParticle(gp.iTile[gp.currentMap][i],gp.iTile[gp.currentMap][i]);
			
			if(gp.iTile[gp.currentMap][i].life == 0) {
				gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
			}
		}
	}
	
	public void damageProjectile(int i) {
		
		if(i != 999) {
			
			Entity projectile = gp.projectile[gp.currentMap][i];
			projectile.alive = false;
			generateParticle(projectile,projectile);
		}
	}
	
	public void checkLevelUp() {
		
		if(exp >= nextLevelExp) {
			
			level++;
			nextLevelExp = nextLevelExp * 3;
			maxLife += 2;
			life = maxLife;
			mana = maxMana;
			strength ++;
			dexterity++;
			attack = getAttack();
			defense = getDefense();
			
			gp.playSE(8);
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialogue = "You are level " + level + " now!\n" + "You feel stronger!";
		}
	}
	
	public void selectItem() {
		
		int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol,gp.ui.playerSlotRow);
		
		if(itemIndex < inventory.size()) {
			
			Entity selectedItem = inventory.get(itemIndex);
			
			if(selectedItem.type == type_sword || selectedItem.type == type_staff || selectedItem.type == type_bow) {
				
				currentWeapon = selectedItem;
				attack = getAttack();
				getPlayerAttackImage();
			}
			if(selectedItem.type == type_armor) {
				
				currentArmor = selectedItem;
				defense = getDefense();
			}
			if(selectedItem.type == type_light) {
				
				if(currentLight == selectedItem) {
					currentLight = null;
				}
				else {
					currentLight = selectedItem;
				}
				lightUpdated = true;
			}
			if(selectedItem.type == type_consumable) {
				
				if(selectedItem.use(this) == true) {
					if(selectedItem.amount > 1) {
						selectedItem.amount--;
					}
					else {
						inventory.remove(itemIndex);
					}
				}
			}
		}
	}
	
	public int searchItemInInventory(String itemName) {
		
		int itemIndex = 999;
		
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).name.equals(itemName)) {
				itemIndex = i;
				break;
			}
		}
		return itemIndex;
	}
	
	public boolean canObtainItem(Entity item) {
		
		boolean canObtain = false;
		
		//Check if STACKABLE
		if(item.stackable == true) {
			
			int index = searchItemInInventory(item.name);
			
			if(index != 999) {
				inventory.get(index).amount++;
				canObtain = true; 
			}
			else { //New item so need to check vacancy
				if(inventory.size() != maxInventorySize) {
					inventory.add(item);
					canObtain = true;
				}
			}
		}
		else { //Not STACKABLE so check vacancy
			if(inventory.size() != maxInventorySize) {
				inventory.add(item);
				canObtain = true;
			}
		}
		return canObtain;
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY; 
		
		switch(direction) {
		case "up":
			if(attacking == false) {
				if(spriteNum == 1) {image = up1;}
				if(spriteNum == 2) {image = up2;}
			}
			if(attacking == true || fakeAttacking == true) {
				tempScreenY = screenY - gp.tileSize;
				if(spriteNum == 1) {image = attackUp1;}
				if(spriteNum == 2) {image = attackUp2;}
			}
			break;
		case "down":
			if(attacking == false) {
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}
			}
			if(attacking == true || fakeAttacking == true) {
				if(spriteNum == 1) {image = attackDown1;}
				if(spriteNum == 2) {image = attackDown2;}
			}
			break;
		case "left":
			if(attacking == false) {
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
			}
			if(attacking == true || fakeAttacking == true) {
				tempScreenX = screenX - gp.tileSize;
				if(spriteNum == 1) {image = attackLeft1;}
				if(spriteNum == 2) {image = attackLeft2;}
			}
			break;
		case "right":
			if(attacking == false) {
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
			}
			if(attacking == true || fakeAttacking == true) {
				if(spriteNum == 1) {image = attackRight1;}
				if(spriteNum == 2) {image = attackRight2;}
			}
			break;
		}
		
		if(invincible == true) {
			
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		}
		
		g2.drawImage(image,tempScreenX,tempScreenY,null);
		
		//Reset alpha
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		// Display the collision of player
		//g2.setColor(Color.red);
		//g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
		
		//Debug (player invincible time)
//		g2.setFont(new Font("Arial",Font.PLAIN,26));
//		g2.setColor(Color.WHITE);
//		g2.drawString("Invincible: " + invincibleCounter, 10, 400);
	}
}