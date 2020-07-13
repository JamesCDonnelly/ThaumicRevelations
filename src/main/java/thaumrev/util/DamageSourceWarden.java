package thaumrev.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;

public class DamageSourceWarden extends EntityDamageSource {

	public DamageSourceWarden(String par1Str, Entity entity) {
		super(par1Str, entity);
		setDamageBypassesArmor();
		setDamageIsAbsolute();
	}
}