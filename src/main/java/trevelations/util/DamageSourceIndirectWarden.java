package trevelations.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSourceIndirect;

public class DamageSourceIndirectWarden extends EntityDamageSourceIndirect {
    public DamageSourceIndirectWarden(String name, Entity transmitter, Entity indirectSource) {
        super(name, transmitter, indirectSource);
        this.setDamageBypassesArmor();
    }
}
