package Koishi.relics;

import Koishi.KoishiMod;
import Koishi.cards.AbstractIdCard;
import Koishi.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static Koishi.KoishiMod.makeRelicOutlinePath;
import static Koishi.KoishiMod.makeRelicPath;

public class AHoleWhereASisterShouldBe extends CustomRelic {

    public static final String ID = KoishiMod.makeID("AHoleWhereASisterShouldBe");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("AHoleWhereASisterShouldBe.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("AHoleWhereASisterShouldBe.png"));

    private static final int EFFECT = 1;
    private boolean triggered = false;

    public AHoleWhereASisterShouldBe() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.SOLID);
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (!triggered && drawnCard instanceof AbstractIdCard) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(EFFECT));
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, EFFECT));
            triggered = true;
            this.pulse = false;
        }
    }

    @Override
    public void atTurnStart() {
        if (!triggered) {
            this.beginPulse();
            this.pulse = true;
        }
    }

    @Override
    public void onVictory() {
        triggered = false;
        this.pulse = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + EFFECT + DESCRIPTIONS[1];
    }

}
