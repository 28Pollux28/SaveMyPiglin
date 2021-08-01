package eu.pollux28.savemypiglin.api;

import eu.pollux28.savemypiglin.SaveMyPiglin;
import net.minecraft.util.Identifier;
import net.minecraft.world.dimension.DimensionType;

import java.util.Optional;

public final class SaveMyPiglinAPIBiomes {
    private SaveMyPiglinAPIBiomes() {
    }
    /**
     * Adds a biome in which the Piglins should not zombify
     * @param biomeID The biome's {@link Identifier}
     */
    public static void addPiglinBiome(Identifier biomeID){
        final Optional<Boolean> bool = SaveMyPiglin.containsBiome(biomeID, 0);
        if(bool.isPresent()&& !bool.get()){
            SaveMyPiglin.addBiome(biomeID,0);
        }
    }
    /**
     * Removes a biome from the list of protected biomes for Piglins
     * @param biomeID The biome's {@link Identifier}
     */
    public static void removePiglinBiome(Identifier biomeID){
        SaveMyPiglin.removeBiome(biomeID,0);
    }
    /**
     * Adds a biome in which the Hoglins should not zombify
     * @param biomeID The biome's {@link Identifier}
     */
    public static void addHoglinBiome(Identifier biomeID){
        final Optional<Boolean> bool = SaveMyPiglin.containsBiome(biomeID, 1);
        if(bool.isPresent() && bool.get()){
            SaveMyPiglin.addBiome(biomeID,1);
        }
    }

    /**
     * Removes a biome from the list of protected biomes for Hoglins
     * @param biomeID The biome's {@link Identifier}
     */
    public static void removeHoglinBiome(Identifier biomeID){
        SaveMyPiglin.removeBiome(biomeID,1);
    }
}
