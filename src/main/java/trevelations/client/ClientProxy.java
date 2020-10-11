package trevelations.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.Item;
import trevelations.client.render.RenderPurity;
import trevelations.common.CommonProxy;
import trevelations.entity.EntityPurity;

import java.util.HashMap;
import java.util.Map;

public class ClientProxy extends CommonProxy {

	public static final Map<Item, ModelBiped> armorModels = new HashMap<Item, ModelBiped>();

	@Override
	public void initRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityPurity.class, new RenderPurity());

		// ModelCrimsonHat crimsonHat = new ModelCrimsonHat();
		// armorModels.put(itemCrimsonHat, crimsonHat);
	}
}
