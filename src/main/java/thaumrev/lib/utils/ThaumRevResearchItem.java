package thaumrev.lib.utils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.api.research.ResearchPage.PageType;

public class ThaumRevResearchItem extends ResearchItem {

	public ThaumRevResearchItem(String key, String category, AspectList tags, int column, int row, int complexity, ItemStack icon) {
		super(key, category, tags, column, row, complexity, icon);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getName() {
		return StatCollector.translateToLocal("trvresearch.name." + key);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getText() {
		return StatCollector.translateToLocal("trvresearch.text." + key);
	}

	@Override
	public ResearchItem setPages(ResearchPage... pages) {
		for (ResearchPage Page : pages) {
			if (Page.type == PageType.TEXT) {
				Page.text = "trvresearch.page." + key + "." + Page.text;
			}
		}

		return super.setPages(pages);
	}
}
