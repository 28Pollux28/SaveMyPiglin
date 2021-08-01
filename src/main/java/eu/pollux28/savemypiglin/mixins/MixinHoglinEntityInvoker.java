package eu.pollux28.savemypiglin.mixins;

import net.minecraft.entity.mob.HoglinEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(HoglinEntity.class)
public interface MixinHoglinEntityInvoker {
    @Invoker("isImmuneToZombification")
    boolean invokeSVPIsImmuneToZombification();
}
