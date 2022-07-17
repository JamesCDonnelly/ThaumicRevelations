package thaumrev.networking.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;

import java.io.IOException;

import static thaumrev.ThaumicRevelations.PACKET_TYPE_S2C_TEST;

public class ProcessPacketClientSide {
  public ProcessPacketClientSide() {
  }

  @SideOnly(Side.CLIENT)
  public static void processPacketOnClient(ByteBuf parBB, Side parSide) throws IOException {
    if (parSide == Side.CLIENT) {
      ByteBufInputStream bbis = new ByteBufInputStream(parBB);

      int packetTypeID = bbis.readInt();

      switch (packetTypeID) {
        case PACKET_TYPE_S2C_TEST: {
          int testVal = bbis.readInt();
          System.out.println(testVal);
          break;
        }
      }

      bbis.close();
    }
  }
}
