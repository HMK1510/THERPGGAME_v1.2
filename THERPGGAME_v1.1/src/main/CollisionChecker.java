package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker (GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity e) {
		
		int entityLeftWorldX = e.worldX + e.solidArea.x;
		int entityRightWorldX = e.worldX + e.solidArea.x + e.solidArea.width;
		int entityTopWorldY = e.worldY + e.solidArea.y;
		int entityBotWorldY = e.worldY + e.solidArea.y + e.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBotRow = entityBotWorldY/gp.tileSize;
		
		int tileNum1,tileNum2;
		
		//Use a semporal direction when it's being knockbacked
		String direction =e.direction;
		if(e.knockBack==true) {
			direction=e.knockBackDirection;
		}
		switch(direction) {
		case "up":
			entityTopRow = (entityTopWorldY-e.speed)/gp.tileSize;
			tileNum1 = gp.tileManager.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = gp.tileManager.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
			if(gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true) {
				e.collisionOn = true;
			}
			break;
		case "down":
			entityBotRow = (entityBotWorldY+e.speed)/gp.tileSize;
			tileNum1 = gp.tileManager.mapTileNum[gp.currentMap][entityLeftCol][entityBotRow];
			tileNum2 = gp.tileManager.mapTileNum[gp.currentMap][entityRightCol][entityBotRow];
			if(gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true) {
				e.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX-e.speed)/gp.tileSize;
			tileNum1 = gp.tileManager.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = gp.tileManager.mapTileNum[gp.currentMap][entityLeftCol][entityBotRow];
			if(gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true) {
				e.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX+e.speed)/gp.tileSize;
			tileNum1 = gp.tileManager.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
			tileNum2 = gp.tileManager.mapTileNum[gp.currentMap][entityRightCol][entityBotRow];
			if(gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true) {
				e.collisionOn = true;
			}
			break;
		}
	}
	
	public int checkObject(Entity e, boolean player) {
		
		int index = 999;
		
		for(int i = 0; i < gp.obj[1].length; i++) {
			
			if(gp.obj[gp.currentMap][i] != null) {
				
				//Get entity's solid area position
				e.solidArea.x = e.worldX + e.solidArea.x;
				e.solidArea.y = e.worldY + e.solidArea.y;
				
				//Get object's solid area position
				gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;
				gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;
				
				switch(e.direction) {
				case "up": e.solidArea.y -= e.speed; break;
				case "down": e.solidArea.y += e.speed; break;
				case "left": e.solidArea.x -= e.speed; break;
				case "right": e.solidArea.x += e.speed; break;
				}
				
				if(e.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
					if(gp.obj[gp.currentMap][i].collision == true) {
						e.collisionOn = true;
					}
					if(player == true) {
						index = i;
					}
				}
				
				e.solidArea.x = e.solidAreaDefaultX;
				e.solidArea.y = e.solidAreaDefaultY;
				gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
				gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;
			}			
		}
		
		return index;
	}
	
	//NPC or monster
	public int checkEntity(Entity e, Entity[][] target) {
		
		int index = 999;
		
		//Use a semporal direction when it's being knockbacked
				String direction =e.direction;
				if(e.knockBack==true) {
					direction=e.knockBackDirection;
				}
		
		for(int i = 0; i < target[1].length; i++) {
			
			if(target[gp.currentMap][i] != null) {
				
				//Get entity's solid area position
				e.solidArea.x = e.worldX + e.solidArea.x;
				e.solidArea.y = e.worldY + e.solidArea.y;
				
				//Get object's solid area position
				target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;
				target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;
				
				switch(direction) {
				case "up": e.solidArea.y -= e.speed; break;
				case "down": e.solidArea.y += e.speed; break;
				case "left": e.solidArea.x -= e.speed; break;
				case "right": e.solidArea.x += e.speed; break;
				}
				
				if(e.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
					
					if(target[gp.currentMap][i] != e) { //If intersect target = entity, collision doesn't happen (avoid this entity include itself as a collision target
						e.collisionOn = true;
						index = i;
					}	
				}
				
				e.solidArea.x = e.solidAreaDefaultX;
				e.solidArea.y = e.solidAreaDefaultY;
				target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
				target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
			}			
		}
		
		return index;
		
	}
	
	public boolean checkPlayer(Entity e) {
		
		boolean contactPlayer = false;
		
		//Get entity's solid area position
		e.solidArea.x = e.worldX + e.solidArea.x;
		e.solidArea.y = e.worldY + e.solidArea.y;
		
		//Get object's solid area position
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		
		switch(e.direction) {
		case "up": e.solidArea.y -= e.speed; break;
		case "down": e.solidArea.y += e.speed; break;
		case "left": e.solidArea.x -= e.speed; break;
		case "right": e.solidArea.x += e.speed; break;
		}
		
		if(e.solidArea.intersects(gp.player.solidArea)) {
			e.collisionOn = true;
			contactPlayer = true;
		}
		
		e.solidArea.x = e.solidAreaDefaultX;
		e.solidArea.y = e.solidAreaDefaultY;
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		
		return contactPlayer;
	}
	
}
