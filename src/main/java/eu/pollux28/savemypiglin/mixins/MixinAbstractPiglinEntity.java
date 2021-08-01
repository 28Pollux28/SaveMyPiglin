package eu.pollux28.savemypiglin.mixins;

import eu.pollux28.savemypiglin.SaveMyPiglin;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(AbstractPiglinEntity.class)
public class MixinAbstractPiglinEntity {
    @Inject(method = "shouldZombify", at = @At("RETURN"), cancellable = true)
    public void protectPiglinInNetherBiome(CallbackInfoReturnable<Boolean> cir){
        AbstractPiglinEntity piglin =((AbstractPiglinEntity)(Object)this);
        final Optional<Boolean> bool = SaveMyPiglin.containsBiome(SaveMyPiglin.dynamicRegistryManager.get(Registry.BIOME_KEY).getId(piglin.world.getBiome(piglin.getBlockPos())), 0);
        boolean flag = bool.map(aBoolean -> !aBoolean).orElse(true);
        final Optional<Boolean> bool2 = SaveMyPiglin.containsDimension(SaveMyPiglin.dynamicRegistryManager.get(Registry.DIMENSION_TYPE_KEY).getId(piglin.world.getDimension()),0);
        flag = flag && bool2.map(aBoolean -> !aBoolean).orElse(true);
        flag = flag && !piglin.isAiDisabled();
        flag =flag && !((MixinAbstractPiglinEntityInvoker)piglin).invokeSVPIsImmuneToZombification();
        cir.setReturnValue(flag);
    }
}

