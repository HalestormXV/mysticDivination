package halestormxv.eAngelus.mobs;

import halestormxv.eAngelus.main.EAMain;
import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.init.eAngelusItems;
import halestormxv.eAngelus.mobs.entitys.EntityCelestialBolt;
import halestormxv.eAngelus.mobs.entitys.EntityPhantom;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;


/**
 * Created by Blaze on 7/15/2017.
 */
public class MobRegistry
{
    private static final Biome[] phantomSpawns = new Biome[] {Biomes.MUSHROOM_ISLAND, Biomes.HELL, Biomes.FOREST, Biomes.MUTATED_SWAMPLAND, Biomes.DESERT, Biomes.PLAINS, Biomes.TAIGA};

    public static void register()
    {

        MobRegistry.registerSpawnable(EntityPhantom.class, "phantom", 1, 0x33CCFFAA, 0x660000AA);
        MobRegistry.registerSpawnable(EntityCelestialBolt.class, "cel_bolt", 2, 0x33CC66AA, 0x66F400AA);
    }

    public static void registerSpawnable(Class entityClass, String name, int id, int mainColor, int subColor) {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, name), entityClass, name, id, EAMain.instance, 64, 3, true, mainColor, subColor);
        EntityRegistry.addSpawn(EntityPhantom.class, 10, 1, 4, EnumCreatureType.MONSTER, phantomSpawns);
    }
}

