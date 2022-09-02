/* Based on Witching Gadgets Utilities */

package thaumrev.util;

import java.util.UUID;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;

public abstract class AttributeHelper {
  public static void addAttributeModToLiving(EntityLivingBase living, IAttribute attr, UUID uuid, String tag, double val, int type) {
		IAttributeInstance attrIns = living.getAttributeMap().getAttributeInstance(attr);

		if (attrIns == null || attrIns.getModifier(uuid) != null) return;

		attrIns.applyModifier(
      new AttributeModifier(uuid, tag, val, type)
    );
	}

  public static void removeAttributeModFromLiving(EntityLivingBase living, IAttribute attr, UUID uuid, String tag, double val, int type) {
		IAttributeInstance attrIns = living.getAttributeMap().getAttributeInstance(attr);

		if (attrIns != null) {
			attrIns.removeModifier(new AttributeModifier(uuid, tag, val, type));
    }
	}
}
