package Koishi.relics;

import Koishi.KoishiMod;
import Koishi.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static Koishi.KoishiMod.makeRelicOutlinePath;
import static Koishi.KoishiMod.makeRelicPath;

public class TeethAndClaws extends CustomRelic {

    public static final String ID = KoishiMod.makeID("TeethAndClaws");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("TeethAndClaws.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("TeethAndClaws.png"));

    private static final int BLOCK = 6;
    private boolean triggered = false;

    public TeethAndClaws() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.HEAVY);
        this.pulse = false;
    }

    @Override
    public void onTrigger(AbstractCreature target) {
        if (!triggered) {
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(target, target, BLOCK));
            this.pulse = false;
            triggered = true;
        }
    }

    @Override
    public void atTurnStart() {
        this.beginPulse();
        this.pulse = true;
        triggered = false;
    }

    @Override
    public void onVictory() {
        triggered = false;
        this.pulse = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + BLOCK + DESCRIPTIONS[1];
    }

}
