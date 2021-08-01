package eu.pollux28.savemypiglin;


import eu.pollux28.savemypiglin.api.SaveMyPiglinAPIBiomes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.dimension.DimensionType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SaveMyPiglin implements ModInitializer {
    public static Logger LOGGER = LogManager.getLogger("savemypiglin");
    public static final String VERSION = "1.0";
    private static List<Identifier> protectedPiglinBiomes;
    private static List<Identifier> protectedHoglinBiomes;
    private static List<Identifier> protectedPiglinDimensions;
    private static List<Identifier> protectedHoglinDimensions;
    public static DynamicRegistryManager dynamicRegistryManager;
    @Override
    public void onInitialize() {
        ServerWorldEvents.LOAD.register((server, world) -> dynamicRegistryManager = world.getRegistryManager());
        protectedPiglinBiomes= new ArrayList<>();
        protectedHoglinBiomes= new ArrayList<>();
        protectedPiglinDimensions = new ArrayList<>();
        protectedHoglinDimensions = new ArrayList<>();
        addDefaultBiomes();
        addDefaultDimensions();
    }


    private static void addDefaultBiomes(){
        addBiome(BiomeKeys.NETHER_WASTES.getValue(),0);
        addBiome(BiomeKeys.NETHER_WASTES.getValue(),1);
        addBiome(BiomeKeys.CRIMSON_FOREST.getValue(),0);
        addBiome(BiomeKeys.CRIMSON_FOREST.getValue(),1);
        addBiome(BiomeKeys.WARPED_FOREST.getValue(),0);
        addBiome(BiomeKeys.WARPED_FOREST.getValue(),1);
        addBiome(BiomeKeys.SOUL_SAND_VALLEY.getValue(),0);
        addBiome(BiomeKeys.SOUL_SAND_VALLEY.getValue(),1);
        addBiome(BiomeKeys.BASALT_DELTAS.getValue(),0);
        addBiome(BiomeKeys.BASALT_DELTAS.getValue(),1);
        SaveMyPiglinAPIBiomes.removeHoglinBiome(BiomeKeys.CRIMSON_FOREST.getValue());
    }

    /**
     * Adds a biome to the list of protected one for an entity
     * @param biomeID The {@link net.minecraft.util.Identifier} of the biome you want the entity to be protected in.
     * @param index The entity index (piglin = 0, hoglin = 1)
     */
    public static void addBiome(Identifier biomeID, int index){
        if (index == 0) {
            protectedPiglinBiomes.add(biomeID);
        }else if(index==1){
            protectedHoglinBiomes.add(biomeID);
        }else{
            LOGGER.error("Index {} is not associated with an entity !", index);
        }
    }
    /**
     * Removes a biome of the list of protected one for an entity
     * @param biomeID The {@link net.minecraft.util.Identifier} of the biome you want the entity to be protected in.
     * @param index The entity index (piglin = 0, hoglin = 1)
     */
    public static void removeBiome(Identifier biomeID, int index){
        if (index == 0) {
            protectedPiglinBiomes.remove(biomeID);
        }else if(index==1){
            protectedHoglinBiomes.remove(biomeID);
        }else{
            LOGGER.error("Index {} is not associated with an entity !", index);
        }
    }
    public static Optional<Boolean> containsBiome(Identifier biomeID, int index){
        if (index == 0) {
            return Optional.of(protectedPiglinBiomes.contains(biomeID));
        }else if(index==1){
            return Optional.of(protectedHoglinBiomes.contains(biomeID));
        }else{
            LOGGER.error("Index {} is not associated with an entity !", index);
            return Optional.empty();
        }
    }

    private void addDefaultDimensions() {
        addDimension(DimensionType.THE_NETHER_ID,0);
        addDimension(DimensionType.THE_NETHER_ID,1);
    }
    /**
     * Adds a dimension to the list of protected one for an entity
     * @param dimensionID The {@link net.minecraft.util.Identifier} of the dimension you want the entity to be protected in.
     * @param index The entity index (piglin = 0, hoglin = 1)
     */
    public static void addDimension(Identifier dimensionID, int index){
        if (index == 0) {
            protectedPiglinDimensions.add(dimensionID);
        }else if(index==1){
            protectedHoglinDimensions.add(dimensionID);
        }else{
            LOGGER.error("Index {} is not associated with an entity !", index);
        }
    }
    /**
     * Adds a dimension of the list of protected one for an entity
     * @param dimensionID The {@link net.minecraft.util.Identifier} of the dimension you want the entity to be protected in.
     * @param index The entity index (piglin = 0, hoglin = 1)
     */
    public static void removeDimension(Identifier dimensionID, int index){
        if (index == 0) {
            protectedPiglinDimensions.remove(dimensionID);
        }else if(index==1){
            protectedHoglinDimensions.remove(dimensionID);
        }else{
            LOGGER.error("Index {} is not associated with an entity !", index);
        }
    }
    public static Optional<Boolean> containsDimension(Identifier dimensionID, int index){
        if (index == 0) {
            return Optional.of(protectedPiglinDimensions.contains(dimensionID));
        }else if(index==1){
            return Optional.of(protectedHoglinDimensions.contains(dimensionID));
        }else{
            LOGGER.error("Index {} is not associated with an entity !", index);
            return Optional.empty();
        }
    }

    public static List<Identifier> getProtectedPiglinBiomes() {
        return protectedPiglinBiomes;
    }
    public static List<Identifier> getProtectedHolinBiomes() {
        return protectedHoglinBiomes;
    }

    public static List<Identifier> getProtectedPiglinDimensions() {
        return protectedPiglinDimensions;
    }
    public static List<Identifier> getProtectedHoglinDimensions() {
        return protectedHoglinDimensions;
    }
}
