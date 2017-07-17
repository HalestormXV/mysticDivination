package halestormxv.eAngelus.main.handlers;

import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.Utils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

/**
 * Created by Blaze on 7/13/2017.
 */
public class EA_SoundHandler
{
    private static int size = 0;

    public static SoundEvent SIN_INCREASE_LEVEL;
    public static SoundEvent VIRTUE_INCREASE_LEVEL;
    public static SoundEvent PHANTOM_BREATH_SOUND;
    public static SoundEvent PHANTOM_HURT_SOUND;
    public static SoundEvent PHANTOM_DEATH_SOUND;
    public static SoundEvent GUNBLADE_FIRE;

    public static void init()
    {
        size = SoundEvent.REGISTRY.getKeys().size();

        SIN_INCREASE_LEVEL = register("sin_increase");
        VIRTUE_INCREASE_LEVEL = register("virtue_increase");
        PHANTOM_BREATH_SOUND = register("phantom_breathing");
        PHANTOM_HURT_SOUND = register("phantom_hurt");
        PHANTOM_DEATH_SOUND = register("phantom_death");
        GUNBLADE_FIRE = register("gunblade_fire");
    }

    public static SoundEvent register(String name)
    {
        ResourceLocation location = new ResourceLocation(Reference.MODID, name);
        SoundEvent e = new SoundEvent(location);

        SoundEvent.REGISTRY.register(size, location, e);
        size++;
        Utils.getLogger().info(("Registered sound: " + name));
        return e;
    }
}
