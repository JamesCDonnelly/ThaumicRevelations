package thaumrev.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.Item;
import thaumrev.client.render.models.ModelCrimsonHat;
import thaumrev.common.CommonProxy;

import java.util.HashMap;
import java.util.Map;

import static thaumrev.ThaumRevLibrary.itemCrimsonHat;

public class ClientProxy extends CommonProxy {

	public static final Map<Item, ModelBiped> armorModels = new HashMap<Item, ModelBiped>();

	@Override
	public void initRenderers() {
		// RenderingRegistry.registerEntityRenderingHandler(EntityPurity.class, new RenderPurity());

		ModelCrimsonHat crimsonHat = new ModelCrimsonHat();
		armorModels.put(itemCrimsonHat, crimsonHat);
	}
}
