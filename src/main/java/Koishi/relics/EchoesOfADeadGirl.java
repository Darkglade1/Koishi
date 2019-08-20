package Koishi.relics;

import Koishi.KoishiMod;
import Koishi.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static Koishi.KoishiMod.makeRelicOutlinePath;
import static Koishi.KoishiMod.makeRelicPath;

public class EchoesOfADeadGirl extends CustomRelic {

    public static final String ID = KoishiMod.makeID("EchoesOfADeadGirl");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("EchoesOfADeadGirl.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("EchoesOfADeadGirl.png"));

    private static final int ENERGY = 1;

    public EchoesOfADeadGirl() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public void onTrigger(AbstractCreature target) {
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(ENERGY));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
