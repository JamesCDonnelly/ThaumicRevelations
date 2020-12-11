package thaumrev.util;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;

public class KeyEventHandler {
    public static void init() {
        MinecraftForge.EVENT_BUS.register(new KeyEventHandler());
    }

    public static final int AMULET_KEY = 0;
    private static final String[] desc = {"key.amulet.desc"};
    private static final int[] keyValues = {Keyboard.KEY_R};
    private final KeyBinding[] keys;

    public KeyEventHandler() {
        super();
        keys = new KeyBinding[desc.length];
        for (int i = 0; i < desc.length; ++i) {
            keys[i] = new KeyBinding(desc[i], keyValues[i], "key.thaumrev.category");
            ClientRegistry.registerKeyBinding(keys[i]);
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (!FMLClientHandler.instance().isGUIOpen(GuiChat.class)) {
            if (keys[AMULET_KEY].isPressed()) {

            }
        }
    }
}