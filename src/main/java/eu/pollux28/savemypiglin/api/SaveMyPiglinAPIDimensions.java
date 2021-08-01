package eu.pollux28.savemypiglin.api;

import eu.pollux28.savemypiglin.SaveMyPiglin;
import net.minecraft.util.Identifier;
import net.minecraft.world.dimension.DimensionType;

import java.util.Optional;

public final class SaveMyPiglinAPIDimensions {
    private SaveMyPiglinAPIDimensions() {
    }

    /**
     * Adds a dimension in which the Piglins should not zombify
     * @param dimensionID The dimension's {@link Identifier}
     */
    public static void addPiglinDimension(Identifier dimensionID){
        final Optional<Boolean> bool = SaveMyPiglin.containsDimension(dimensionID, 0);
        if(bool.isPresent() && !bool.get()){
            SaveMyPiglin.addDimension(dimensionID, 0);
        }
    }
    /**
     * Removes a dimension from the list of protected dimensions for Piglins
     * @param dimensionID The dimension's {@link Identifier}
     */
    public static void removePiglinDimension(Identifier dimensionID){
        SaveMyPiglin.removeDimension(dimensionID,0);
    }
    /**
     * Adds a dimension in which the Hoglins should not zombify
     * @param dimensionID The dimension's {@link Identifier}
     */
    public static void addHoglinDimension(Identifier dimensionID){
        final Optional<Boolean> bool = SaveMyPiglin.containsDimension(dimensionID, 1);
        if(bool.isPresent() && !bool.get()){
            SaveMyPiglin.addDimension(dimensionID, 1);
        }
    }
    /**
     * Removes a dimension from the list of protected dimensions for Hoglins
     * @param dimensionID The dimension's {@link Identifier}
     */
    public static void removeHoglinDimension(Identifier dimensionID){
        SaveMyPiglin.removeDimension(dimensionID,1);
    }
}
