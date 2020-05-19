package thaumrev.client;

import cpw.mods.fml.client.registry.RenderingRegistry;

import thaumrev.client.render.RenderPurity;
import thaumrev.common.CommonProxy;
import thaumrev.entity.EntityPurity;

public class ClientProxy extends CommonProxy {

	@Override
	public void initRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityPurity.class, new RenderPurity());
	}
}
