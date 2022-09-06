package thaumrev.api.damagesource;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;

public class DamageSourceWarden extends EntityDamageSource {

	public DamageSourceWarden(String name, Entity entity) {
		super(name, entity);
		setDamageBypassesArmor();
		setDamageIsAbsolute();
	}
}