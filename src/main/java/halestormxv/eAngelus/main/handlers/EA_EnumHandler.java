package halestormxv.eAngelus.main.handlers;


import net.minecraft.util.IStringSerializable;

/**
 * Created by Blaze on 7/19/2017.
 */
public class EA_EnumHandler
{
    public static enum CardEssences implements IStringSerializable
    {
        BLANK("empty", 0),
        CHARIOT("chariot", 1),
        KNIGHT("knight", 2),
        STRENGTH("strength", 3);

        private int ID;
        private String name;


        private CardEssences(String name, int ID)
        {
            this.ID = ID;
            this.name = name;
        }

        @Override
        public String getName()
        {
            return this.name;
        }

        public int getID()
        {
            return  ID;
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    public enum RuneStones implements IStringSerializable
    {
        BASE_RUNE( "basic", 0),
        FLAME_RUNE("flame", 1),
        FROST_RUNE("frost", 2),
        EARTH_RUNE("earth", 3),
        DEATH_RUNE("death", 4);

        private int ID;
        private String name;

        private RuneStones(String name, int ID)
        {
            this.ID = ID;
            this.name = name;
        }

        @Override
        public String getName()
        {
            return this.name;
        }

        public int getID()
        {
            return  ID;
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    public enum ModTalismans implements IStringSerializable
    {
        SIN_TALISMAN( "sintalisman", 0),
        VIRTUE_TALISMAN("virtuetalisman", 1);

        private int ID;
        private String name;

        private ModTalismans(String name, int ID)
        {
            this.ID = ID;
            this.name = name;
        }

        @Override
        public String getName()
        {
            return this.name;
        }

        public int getID()
        {
            return  ID;
        }

        @Override
        public String toString() {
            return getName();
        }
    }
}
