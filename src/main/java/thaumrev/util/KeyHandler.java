package thaumrev.util;

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
import thaumrev.networking.entities.CreatePacketClientSide;

import java.util.Objects;

public class KeyHandler {

  private long lastPressA = 0L;
  public KeyBinding keyA =
    new KeyBinding("key.amulet.desc", Keyboard.KEY_R, "key.thaumrev.category");
  private boolean keyPressedA = false;

  public KeyHandler() {
    ClientRegistry.registerKeyBinding(this.keyA);
  }

  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void playerTick(TickEvent.@NotNull PlayerTickEvent event) {
    if (event.side != Side.SERVER &&
      FMLClientHandler.instance().getClient().inGameHasFocus &&
      !FMLClientHandler.instance().isGUIOpen(GuiChat.class)) {
      ItemStack amulet = ItemWardenAmulet.getAmulet(event.player);

      if (this.keyA.getIsKeyPressed() && !this.keyPressedA && Objects.requireNonNull(amulet).getMetadata() == 0) {
        this.keyPressedA = true;
        this.lastPressA = System.currentTimeMillis();
        CreatePacketClientSide.sendAmuletUsePacket(amulet.getMetadata());
        event.player.worldObj.playSoundAtEntity(event.player, "thaumrev:compramos", 1, 1);
      } else if (this.keyPressedA && this.lastPressA + 1000 >= System.currentTimeMillis()) {
        ItemWardenAmulet.amuletParticles(event.player);
      } else {
        this.keyPressedA = false;
      }
    }
  }
}
