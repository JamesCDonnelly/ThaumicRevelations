package thaumrev.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

import thaumrev.entity.EntityPurity;
import thaumrev.tiles.TileKnowledgeReprocessor;
import thaumrev.client.renderers.TileKnowledgeReprocessorRenderer;
import thaumrev.client.renderers.RenderPurity;
import thaumrev.common.CommonProxy;
import thaumrev.networking.ClientPacketHandler;
import thaumrev.util.KeyHandler;

import static thaumrev.ThaumicRevelations.channel;
import static thaumrev.ThaumicRevelations.networkChannelName;

public class ClientProxy extends CommonProxy {
	@Override
	public void initRenderers() {
		entityRenderers();
		tileRenderers();
	}

	@Override
	public void keyBindings() {
		FMLCommonHandler.instance().bus().register(new KeyHandler());
	}

	@Override
	public void initNetwork() {
		channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(networkChannelName);
		channel.register(new ClientPacketHandler());
	}

	private void entityRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityPurity.class, new RenderPurity());
	}

	private void tileRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileKnowledgeReprocessor.class, new TileKnowledgeReprocessorRenderer());
	}
}
