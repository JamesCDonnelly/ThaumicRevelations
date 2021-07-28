package thaumrev.networking.entities;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.IOException;

import static thaumrev.ThaumicRevelations.*;

public class CreatePacketServerSide {
    public CreatePacketServerSide() {
    }

    public static FMLProxyPacket createStringPacket() throws IOException {
        ByteBufOutputStream bbos = new ByteBufOutputStream(Unpooled.buffer());
        bbos.writeInt(PACKET_TYPE_S2C_TEST);

        FMLProxyPacket packet = new FMLProxyPacket(bbos.buffer(), networkChannelName);

        bbos.close();

        return packet;
    }

    public static void sendToAll(FMLProxyPacket parPacket) {
        channel.sendToAll(parPacket);
    }

    public static void sendS2CTest() {
        try {
            sendToAll(createStringPacket());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
