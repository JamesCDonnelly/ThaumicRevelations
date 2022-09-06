package thaumrev.lib.utils;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Keyboard;
import thaumrev.item.baubles.ItemWardenAmulet;
import thaumrev.lib.network.entities.CreatePacketClientSide;

import java.util.Objects;

public final class KeyHandler {

  private long lastPressKeyAmulet = 0L;
  public KeyBinding keyAmulet =
    new KeyBinding("key.amulet.desc", Keyboard.KEY_R, "key.thaumrev.category");

  public KeyHandler() {
    ClientRegistry.registerKeyBinding(this.keyAmulet);
  }

  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void playerTick(TickEvent.@NotNull PlayerTickEvent event) {
    if (event.side == Side.CLIENT &&
      FMLClientHandler.instance().getClient().inGameHasFocus &&
      !FMLClientHandler.instance().isGUIOpen(GuiChat.class)) {
      ItemStack amulet = ItemWardenAmulet.getAmulet(event.player);

      if (this.keyAmulet.getIsKeyPressed() && Objects.requireNonNull(amulet).getMetadata() == 0) {
        this.lastPressKeyAmulet = System.currentTimeMillis();
        CreatePacketClientSide.sendAmuletUsePacket(amulet.getMetadata());
      } else if (this.lastPressKeyAmulet + 10000 >= System.currentTimeMillis()) {
        ItemWardenAmulet.amuletParticles(event.player);
      }
    }
  }
}
