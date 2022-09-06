package thaumrev.lib.network;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import thaumrev.lib.network.entities.ProcessPacketServerSide;

import java.io.IOException;

import static thaumrev.ThaumicRevelations.networkChannelName;

public class ServerPacketHandler {
    protected String channelName;
    protected EntityPlayerMP player;

    public ServerPacketHandler() {
    }

    @SubscribeEvent
    public void onServerPacket(ServerCustomPacketEvent event) throws IOException {
        channelName = event.packet.channel();

        NetHandlerPlayServer theNetHandlerPlayServer = (NetHandlerPlayServer) event.handler;
        player = theNetHandlerPlayServer.playerEntity;

        if (channelName.equals(networkChannelName)) {
            ProcessPacketServerSide.processPacketOnServer(event.packet.payload(), event.packet.getTarget(), player);
        }
    }
}
