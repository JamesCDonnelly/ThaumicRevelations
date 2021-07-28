package thaumrev.networking.entities;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.IOException;

import static thaumrev.ThaumicRevelations.*;

public class CreatePacketClientSide {
    public CreatePacketClientSide() {
    }

    public static FMLProxyPacket createAmuletUsePacket(int i) throws IOException {
        ByteBufOutputStream bbos = new ByteBufOutputStream(Unpooled.buffer());
        bbos.writeInt(PACKET_TYPE_AMULET_USE);
        bbos.writeInt(i);

        FMLProxyPacket thePacket = new FMLProxyPacket(bbos.buffer(), networkChannelName);

        bbos.close();

        return thePacket;
    }

    public static void sendToServer(FMLProxyPacket packet) {
        channel.sendToServer(packet);
    }

    public static void sendAmuletUsePacket(int i) {
        try {
            sendToServer(createAmuletUsePacket(i));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
