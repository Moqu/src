package cn.moye.mc.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;
import cn.moye.mc.gui.MyScreen;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class MyKeyTyped {

	private MyScreen myScreen;
	private KeyBinding kb;
	
	private void initKeyBinding() {
		kb = new KeyBinding("key.show_cmd_vi", Keyboard.KEY_N, "key.categories.ui");
		myScreen = new MyScreen();
		ClientRegistry.registerKeyBinding(kb);
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
	}
	
	@SubscribeEvent
	public void inputKeyTyped(RenderGameOverlayEvent event) {
		if(kb==null){
			initKeyBinding();
		}
		if(kb.isPressed()){
			Minecraft.getMinecraft().displayGuiScreen(myScreen);
			KeyBinding.setKeyBindState(Keyboard.KEY_N, false);
		}
	}
}
