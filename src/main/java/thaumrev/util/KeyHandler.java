package thaumrev.util;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import thaumrev.item.baubles.ItemWardenAmulet;


public class KeyHandler {

    public static long lastPressA = 0L;
    public KeyBinding keyA =
            new KeyBinding("key.amulet.desc", Keyboard.KEY_R, "key.thaumrev.category");
    private boolean keyPressedA = false;

    public KeyHandler() {
        ClientRegistry.registerKeyBinding(this.keyA);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.side != Side.SERVER &&
                FMLClientHandler.instance().getClient().inGameHasFocus &&
                !FMLClientHandler.instance().isGUIOpen(GuiChat.class)) {
            if (this.keyA.getIsKeyPressed()) {
                this.keyPressedA = true;
                lastPressA = System.currentTimeMillis();
                ItemWardenAmulet.useAmulet(event.player);
            } else {
                this.keyPressedA = false;
            }
        }
    }


}
