package cn.moye.mc.itemtoll;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class MyItemTool extends ItemTool {

	public static final HashSet<String> HARVEXTABLE = Sets.newHashSet("pickaxe","shovel","axe");
	
	public MyItemTool() {
		super(100f,ToolMaterial.EMERALD,new HashSet());
	}
	
	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta) {
		if(HARVEXTABLE.contains(block.getHarvestTool(meta))){
			return efficiencyOnProperMaterial;
		} else {
			return super.getDigSpeed(stack, block, meta);
		}
	}
	
	@Override
	public boolean canHarvestBlock(Block par1Block, ItemStack itemStack) {
		if(HARVEXTABLE.contains(par1Block.getHarvestTool(0))){
			return true;
		} else {
			return super.canHarvestBlock(par1Block, itemStack);
		}
	}

}
