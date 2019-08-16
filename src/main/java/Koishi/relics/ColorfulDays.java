package Koishi.relics;

import Koishi.KoishiMod;
import Koishi.powers.EphemeralPower;
import Koishi.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static Koishi.KoishiMod.makeRelicOutlinePath;
import static Koishi.KoishiMod.makeRelicPath;

public class ColorfulDays extends CustomRelic {

    public static final String ID = KoishiMod.makeID("ColorfulDays");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ColorfulDays.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ColorfulDays.png"));

    private static final int EPHEMERAL_INCREASE = 5;

    public ColorfulDays() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
        EphemeralPower.threshold += EPHEMERAL_INCREASE;
    }

    @Override
    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
        EphemeralPower.threshold -= EPHEMERAL_INCREASE;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
