package cn.moye.mc.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

import org.lwjgl.input.Keyboard;

import cn.moye.mc.util.TextUtil;

public class MyScreen extends GuiScreen {

	private boolean sendFlag=true;
	private static final int SEND = 10000,SELECTION = 0;
	private GuiTextField firstTextField,secondTextField,thirdTextField;
	private int screenStartPointX,screenStartPointY,screenEndPointX,screenEndPointY,screenWidth,screenHeigth,rowWidth,rowHeigth;
	private MyGuiButton myButton,sendButton;
	
	@Override
	public void initGui() {
		super.initGui();
		initScreenSize();
		Keyboard.enableRepeatEvents(true);
		initScreen(screenStartPointX, screenStartPointY);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	private void initScreen(int x,int y) {
		buttonList.clear();
		int interval = (int)((rowHeigth - 20)/2);
		
		myButton = new MyGuiButton(SELECTION, x+5, y+interval, rowWidth-10, 20, TextUtil.SELECTION_COMMAND);
		myButton.packedFGColour = 0xff00ff00;
		
		buttonList.add(myButton);
		
		for (int i = 1; i < 9; i++) {
			for (int j = 0; j < 5; j++) {
				buttonList.add(new MyGuiButton(5*i-4+j, 2+x+50*j, (i-1)*22+2+2*y, 50, 20, TextUtil.BLOCK_NAME[j+5*(i-1)]));
				//buttonList.add(new MyGuiButton(i+1, 2+x+50, (i+1)*y, 50, 20, TextUtil.BLOCK_NAME[i+1]));
				//buttonList.add(new MyGuiButton(i+2, 2+x+50*2, (i+1)*y, 50, 20, TextUtil.BLOCK_NAME[i+2]));
				//buttonList.add(new MyGuiButton(i+3, 2+x+50*3, (i+1)*y, 50, 20, TextUtil.BLOCK_NAME[i+3]));
				//buttonList.add(new MyGuiButton(i+4, 2+x+50*4, (i+1)*y, 50, 20, TextUtil.BLOCK_NAME[i+4]));
			}
		}
		
		sendButton = new MyGuiButton( SEND, x+4*rowWidth+5, y+interval, rowWidth-10, 20, TextUtil.SEND);
		sendButton.packedFGColour = 0xff00ff00;
		buttonList.add(sendButton);
		
		firstTextField = new GuiTextField(this.fontRendererObj, x+rowWidth+5, y+interval, rowWidth-10, 20);
		firstTextField.setText(TextUtil.TEXT_FIELD);
		firstTextField.setTextColor(0xff00ff00);
		
		secondTextField = new GuiTextField(this.fontRendererObj, x+2*rowWidth+5, y+interval, rowWidth-10, 20);
		secondTextField.setText(TextUtil.TEXT_FIELD);
		secondTextField.setTextColor(0xff00ff00);
		
		thirdTextField = new GuiTextField(this.fontRendererObj, x+3*rowWidth+5, y+interval, rowWidth-10, 20);
		thirdTextField.setText(TextUtil.TEXT_FIELD);
		thirdTextField.setTextColor(0xff00ff00);
		
		buttonPage(false);
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		
		drawRect(screenStartPointX, screenStartPointY, screenEndPointX, screenEndPointY, 0x99ffffff);
		drawHorizontalLine(screenStartPointX, screenEndPointX, screenStartPointY, 0xffff0000);
		drawHorizontalLine(screenStartPointX, screenEndPointX, 2*screenStartPointY, 0xffff0000);

		drawHorizontalLine(screenStartPointX, screenEndPointX, screenEndPointY, 0xffff0000);
		drawVerticalLine(screenStartPointX, screenStartPointY, screenEndPointY, 0xffff0000);
		drawVerticalLine(screenEndPointX, screenStartPointY, screenEndPointY, 0xffff0000);
		super.drawScreen(par1, par2, par3);
		firstTextField.drawTextBox();
		secondTextField.drawTextBox();
		thirdTextField.drawTextBox();
	}
	
	@Override
	protected void keyTyped(char par1, int par2) {
		if(firstTextField.textboxKeyTyped(par1, par2)||secondTextField.textboxKeyTyped(par1, par2)||thirdTextField.textboxKeyTyped(par1, par2)){
			return;
		}
		super.keyTyped(par1, par2);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		super.actionPerformed(button);
		switch (button.id) {
		case SELECTION:
			myButton.displayString = "/give ";
			break;
		case 1:
			String commd = myButton.displayString + firstTextField.getText() +" "+secondTextField.getText()+" "+thirdTextField.getText();
			mc.thePlayer.sendChatMessage(commd);
			this.mc.displayGuiScreen(null);
			break;
		case 2:
				buttonPage(false);
				//firstTextField.setText("2");
		case SEND:
			buttonPage(sendFlag);
			sendFlag=!sendFlag;
			break;
		}
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseKey) {
		firstTextField.mouseClicked(mouseX, mouseY, mouseKey);
		secondTextField.mouseClicked(mouseX, mouseY, mouseKey);
		thirdTextField.mouseClicked(mouseX, mouseY, mouseKey);
		super.mouseClicked(mouseX, mouseY, mouseKey);
		if(firstTextField.isFocused()){
			firstTextField.setText("");
			buttonPage(true);
		}
		if(secondTextField.isFocused()){
			secondTextField.setText("");
			buttonPage(false);
		}
		if(thirdTextField.isFocused()){
			thirdTextField.setText("");
		}
	}
	
	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}
	
	private void initScreenSize(){
		screenStartPointX = (int) (width * 0.1);
		screenStartPointY = (int) (height * 0.1);
		screenEndPointX = (int) (width * 0.9);
		screenEndPointY = (int) (height * 0.9);

		screenWidth = screenEndPointX - screenStartPointX;
		screenHeigth = screenEndPointY - screenStartPointY;
		
		rowHeigth = (int) (height * 0.1);
		rowWidth = (int) (screenWidth/5);
	}
	
	private void buttonPage(boolean flag) {
		for (int i = 1; i <= 40; i++) {
			MyGuiButton button = (MyGuiButton) buttonList.get(i);
			button.visible = flag;
		}
	}
}
