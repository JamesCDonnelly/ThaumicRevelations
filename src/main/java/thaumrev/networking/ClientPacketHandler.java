package thaumrev.networking;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import thaumrev.networking.entities.ProcessPacketClientSide;

import java.io.IOException;

import static thaumrev.ThaumicRevelations.networkChannelName;

public class ClientPacketHandler extends ServerPacketHandler {
    public ClientPacketHandler() {
    }

    @SubscribeEvent
    public void onClientPacket(ClientCustomPacketEvent event) throws IOException {
        channelName = event.packet.channel();

        if (channelName.equals(networkChannelName)) {
            ProcessPacketClientSide.processPacketOnClient(event.packet.payload(), event.packet.getTarget());
        }
    }
}
