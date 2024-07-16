package main;

import entity.NPC;
import entity.NPC_Cat;
import entity.NPC_Merchant;
import monster.MON_Orc;
import monster.MON_Slime;
import object.OBJ_Armor_Silver;
import object.OBJ_Boots;
import object.OBJ_Bow_Normal;
import object.OBJ_Chest;
import object.OBJ_Coin;
import object.OBJ_Door;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion_Red;
import object.OBJ_Staff_Normal;
import object.OBJ_Tent;
import tile_interactive.IT_DryTree;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	/*public void setObject() {
		
		gp.obj[0] = new OBJ_Key(gp);
		gp.obj[0].worldX = 23 * gp.tileSize;
		gp.obj[0].worldY = 7 * gp.tileSize; 
		
		gp.obj[1] = new OBJ_Key(gp);
		gp.obj[1].worldX = 23 * gp.tileSize;
		gp.obj[1].worldY = 40 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Key(gp);
		gp.obj[2].worldX = 38 * gp.tileSize;
		gp.obj[2].worldY = 8 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Door(gp);
		gp.obj[3].worldX = 10 * gp.tileSize;
		gp.obj[3].worldY = 12 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Door(gp);
		gp.obj[4].worldX = 8 * gp.tileSize;
		gp.obj[4].worldY = 28 * gp.tileSize;
	
		gp.obj[5] = new OBJ_Door(gp);
		gp.obj[5].worldX = 12 * gp.tileSize;
		gp.obj[5].worldY = 23 * gp.tileSize;
		
		gp.obj[6] = new OBJ_Chest(gp);
		gp.obj[6].worldX = 10 * gp.tileSize;
		gp.obj[6].worldY = 8 * gp.tileSize;
		
		gp.obj[7] = new OBJ_Boots(gp);
		gp.obj[7].worldX = 36 * gp.tileSize;
		gp.obj[7].worldY = 40 * gp.tileSize;
	}*/
	
	public void setObject() {
		
		int mapNum = 0;
		int i = 0;
		gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 23 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Lantern(gp);
		gp.obj[mapNum][i].worldX = 21 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Tent(gp);
		gp.obj[mapNum][i].worldX = 26 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 21 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Staff_Normal(gp);
		gp.obj[mapNum][i].worldX = 33 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 21 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Armor_Silver(gp);
		gp.obj[mapNum][i].worldX = 36 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 18 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX = 22 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 27 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Heart(gp);
		gp.obj[mapNum][i].worldX = 22 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 29 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_ManaCrystal(gp);
		gp.obj[mapNum][i].worldX = 22 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 31 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Bow_Normal(gp);
		gp.obj[mapNum][i].worldX = 38 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 36 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = 14 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 28 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = 12 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 12 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Key(gp));
		gp.obj[mapNum][i].worldX = 12 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
		i++;
	}
	
	public void setNPC() {
		
		int i = 0;
		int mapNum = 0;
		
		//Map 0
		gp.npc[mapNum][i] = new NPC(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*21;
		gp.npc[mapNum][i].worldY = gp.tileSize*21;
		i++;
		
		gp.npc[mapNum][i] = new NPC_Cat(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*26;
		gp.npc[mapNum][i].worldY = gp.tileSize*21;
		i++;
		
		//Map1
		
		i = 0;
		mapNum = 1;
		
		gp.npc[mapNum][i] = new NPC_Merchant(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*12;
		gp.npc[mapNum][i].worldY = gp.tileSize*7;
	}
	
	public void setMonster() {
		
		int mapNum = 0;
		int i = 0;
		gp.monster[mapNum][i] = new MON_Slime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*21;
		gp.monster[mapNum][i].worldY = gp.tileSize*38;
		i++;
		gp.monster[mapNum][i] = new MON_Slime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*23;
		gp.monster[mapNum][i].worldY = gp.tileSize*42;
		i++;
		gp.monster[mapNum][i] = new MON_Slime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*24;
		gp.monster[mapNum][i].worldY = gp.tileSize*37;
		i++;
		gp.monster[mapNum][i] = new MON_Slime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*34;
		gp.monster[mapNum][i].worldY = gp.tileSize*42;
		i++;
		gp.monster[mapNum][i] = new MON_Slime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*38;
		gp.monster[mapNum][i].worldY = gp.tileSize*42;
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*12;
		gp.monster[mapNum][i].worldY = gp.tileSize*33;
		i++;
	}
	
	public void setInteractiveTile() {
		
		int mapNum = 0;
		int i = 0;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,27,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,28,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,29,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,30,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,31,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,32,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,33,12); i++;		
		gp.iTile[mapNum][i] = new IT_DryTree(gp,31,21); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,18,40); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,17,40); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,16,40); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,15,40); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,14,40); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,13,40); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,13,41); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,12,41); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,11,41); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,10,41); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,10,40); i++;
	}
}
