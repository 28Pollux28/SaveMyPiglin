package eu.pollux28.savemypiglin.mixins;

import eu.pollux28.savemypiglin.SaveMyPiglin;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;


@Mixin(HoglinEntity.class)
public class MixinHoglinEntity {
    @Inject(method = "canConvert", at = @At("RETURN"), cancellable = true)
    public void protectHoglinInNetherBiome(CallbackInfoReturnable<Boolean> cir){
        HoglinEntity hoglin =((HoglinEntity)(Object)this);
        final Optional<Boolean> bool = SaveMyPiglin.containsBiome(SaveMyPiglin.dynamicRegistryManager.get(Registry.BIOME_KEY).getId(hoglin.world.getBiome(hoglin.getBlockPos())),1);
        boolean flag = bool.map(aBoolean -> !aBoolean).orElse(true);
        final Optional<Boolean> bool2 = SaveMyPiglin.containsDimension(SaveMyPiglin.dynamicRegistryManager.get(Registry.DIMENSION_TYPE_KEY).getId(hoglin.world.getDimension()),1);
        flag = flag && bool2.map(aBoolean -> !aBoolean).orElse(true);
        flag = flag && !hoglin.isAiDisabled();
        flag =flag && !((MixinHoglinEntityInvoker)hoglin).invokeSVPIsImmuneToZombification();
        cir.setReturnValue(flag);
    }

}
