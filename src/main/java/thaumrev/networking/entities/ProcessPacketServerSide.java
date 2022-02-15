package thaumrev.networking.entities;

import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.player.EntityPlayerMP;
import thaumrev.item.baubles.ItemWardenAmulet;

import java.io.IOException;

import static thaumrev.ThaumicRevelations.PACKET_TYPE_AMULET_USE;

public class ProcessPacketServerSide {
  public ProcessPacketServerSide() {
  }

  public static void processPacketOnServer(ByteBuf parBB, Side side, EntityPlayerMP player) throws IOException {
    if (side == Side.SERVER) {
      ByteBufInputStream bbis = new ByteBufInputStream(parBB);

      // World world = Minecraft.getMinecraft().theWorld;
      int packetTypeID = bbis.readInt();

      switch (packetTypeID) {
        case PACKET_TYPE_AMULET_USE: {
          int meta = bbis.readInt();
          if (meta == 0) ItemWardenAmulet.amuletEffect(player);
          break;
        }
      }

      bbis.close();
    }
  }
}
