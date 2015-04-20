package cn.moye.mc.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cn.moye.mc.MyFirstMod;
import cn.moye.mc.block.MyBlock;
import cn.moye.mc.item.MyItem;
import cn.moye.mc.itemtoll.MyItemTool;
import cn.moye.mc.material.MaterialFirstMod;
import cpw.mods.fml.common.registry.GameRegistry;

public class InitBlockItem {

	private MyBlock myBlock;
	private MyItem myItem;
	private MyItemTool myItemTool;
	private Material firstModMaterial;
	
	

	public InitBlockItem() {
		firstModMaterial = new MaterialFirstMod(MapColor.stoneColor);
		initBlock();
		initItem();
		initItemTool();
		GameRegistry.addSmelting(myBlock, new ItemStack(myItem), 100f);
		
	}
	
	private void initItemTool() {
		myItemTool = new MyItemTool();
		myItemTool.setUnlocalizedName("myItemTool");
		myItemTool.setTextureName(MyFirstMod.MODID+":myitemtool");
		myItemTool.setCreativeTab(CreativeTabs.tabTools);
		GameRegistry.registerItem(myItemTool, "myItemTool");
	}

	private void initBlock(){
		myBlock = new MyBlock(firstModMaterial);
		myBlock.setBlockName("MyBlock");
		myBlock.setBlockTextureName(MyFirstMod.MODID+":myblock");
		myBlock.setHardness(1.5f);
		myBlock.setResistance(10.0f);
		myBlock.setLightLevel(0.0f);
		myBlock.setStepSound(Block.soundTypeStone);
		myBlock.setCreativeTab(CreativeTabs.tabBlock);
		myBlock.setHarvestLevel("pickaxe", 4);
		GameRegistry.registerBlock(myBlock, "MyBlock");
	}
	
	private void initItem() {
		myItem = new MyItem();
		myItem.setUnlocalizedName("myItem");
		myItem.setTextureName(MyFirstMod.MODID+":myitem");
		myItem.setMaxStackSize(64);
		myItem.setCreativeTab(CreativeTabs.tabMaterials);
		GameRegistry.registerItem(myItem, "myItem");
	}
}
