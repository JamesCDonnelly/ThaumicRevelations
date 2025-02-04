package thaumrev.client.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import thaumrev.ThaumicRevelations;
import thaumrev.container.ContainerHammer;

public class GuiHandler implements IGuiHandler {

	public static void init() {
		NetworkRegistry.INSTANCE.registerGuiHandler(ThaumicRevelations.instance, new GuiHandler());
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case 0:
				return new ContainerHammer(player);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case 0:
				return new GuiWaslieHammer(player);
		}
		return null;
	}

}
