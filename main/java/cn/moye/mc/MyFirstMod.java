package cn.moye.mc;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cn.moye.mc.block.MyBlock;
import cn.moye.mc.event.MyKeyTyped;
import cn.moye.mc.item.MyItem;
import cn.moye.mc.itemtoll.MyItemTool;
import cn.moye.mc.material.MaterialFirstMod;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = MyFirstMod.MODID, name = MyFirstMod.NAME, version = MyFirstMod.VERSION)
public class MyFirstMod {

	public static final String MODID = "endleafmod";
	public static final String NAME = "EndLeafFirstMod";
	public static final String VERSION = "1.0";
	private MyBlock myBlock;
	private MyItem myItem;
	private MyItemTool myItemTool;
	private MaterialFirstMod firstModMaterial;

	@EventHandler
	private void preLoad(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new MyKeyTyped());
		//FMLCommonHandler.instance().bus().register(new MyKeyTyped());
		firstModMaterial = new MaterialFirstMod(MapColor.stoneColor);
		initBlock();
		initItem();
		initItemTool();
		GameRegistry.addSmelting(myBlock, new ItemStack(myItem), 100f);
	}

	@EventHandler
	private void load(FMLInitializationEvent event) {
		GameRegistry.addRecipe(new ItemStack(myItemTool, 1), new Object[] { "###", "#X#", " X ", '#', myItem, 'X', Items.stick });
	}

	@EventHandler
	private void postLoad(FMLPostInitializationEvent event) {

	}

	private void initItemTool() {
		myItemTool = new MyItemTool();
		myItemTool.setUnlocalizedName("myItemTool");
		myItemTool.setTextureName(MyFirstMod.MODID + ":myitemtool");
		myItemTool.setCreativeTab(CreativeTabs.tabTools);
		GameRegistry.registerItem(myItemTool, "myItemTool");
		myItemTool.setHarvestLevel("dirac", 4);
	}

	private void initBlock() {
		myBlock = new MyBlock(firstModMaterial);
		myBlock.setBlockName("MyBlock");
		myBlock.setBlockTextureName(MyFirstMod.MODID + ":myblock");
		myBlock.setHardness(10.0f);
		myBlock.setResistance(10.0f);
		myBlock.setLightLevel(0.0f);
		myBlock.setHarvestLevel("dirac", 4);
		myBlock.setStepSound(Block.soundTypeStone);
		myBlock.setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock(myBlock, "MyBlock");
	}

	private void initItem() {
		myItem = new MyItem();
		myItem.setUnlocalizedName("myItem");
		myItem.setTextureName(MyFirstMod.MODID + ":myitem");
		myItem.setMaxStackSize(64);
		myItem.setCreativeTab(CreativeTabs.tabMaterials);
		GameRegistry.registerItem(myItem, "myItem");
	}
}
