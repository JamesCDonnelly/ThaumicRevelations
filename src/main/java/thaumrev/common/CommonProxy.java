package thaumrev.common;

import cpw.mods.fml.common.network.NetworkRegistry;
import thaumrev.networking.ServerPacketHandler;

import static thaumrev.ThaumicRevelations.channel;
import static thaumrev.ThaumicRevelations.networkChannelName;

public class CommonProxy {

    public void initRenderers() {
    }

    public void keyBindings() {
    }

    public void initNetwork() {
        channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(networkChannelName);
        channel.register((new ServerPacketHandler()));
    }
}
