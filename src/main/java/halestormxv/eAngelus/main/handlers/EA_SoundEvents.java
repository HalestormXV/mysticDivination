package halestormxv.eAngelus.main.handlers;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

/**
 * Use these to play Botania sounds.
 * Do not access this class before Botania preinits!
 */
public final class EA_SoundEvents
{
    public static final SoundEvent mavismusic = getRegisteredSoundEvent("music.mavismusic");
    public static final SoundEvent motomiyaflute = getRegisteredSoundEvent("music.motomiyaflute");



    private static SoundEvent getRegisteredSoundEvent(String name)
    {
        return SoundEvent.REGISTRY.getObject(new ResourceLocation("eangel", name));

    }

    private EA_SoundEvents() {}
}
