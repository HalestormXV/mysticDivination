package halestormxv.eAngelus.main.handlers;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Blaze on 7/9/2017.
 */
public final class ModSounds {

    public static void init() {
        String[] sounds = {

                "eangel:music.mavismusic",
                "eangel:music.motomiyaflute"
        };

        for (String s : sounds) {
            ResourceLocation loc = new ResourceLocation(s);
            GameRegistry.register(new SoundEvent(loc), loc);
        }
    }

    private ModSounds() {}

}
