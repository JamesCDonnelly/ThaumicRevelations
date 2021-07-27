package thaumrev.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import thaumrev.client.renderers.RenderPurity;
import thaumrev.common.CommonProxy;
import thaumrev.entity.EntityPurity;
import thaumrev.util.KeyHandler;

public class ClientProxy extends CommonProxy {
	@Override
	public void initRenderers() {
		entityRenderers();
	}

	public void keyBindings() {
		FMLCommonHandler.instance().bus().register(new KeyHandler());
	}

	private void entityRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityPurity.class, new RenderPurity());
	}
}
