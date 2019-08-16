package Koishi.powers;

import Koishi.KoishiMod;
import Koishi.relics.ColorfulDays;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

public class EphemeralPower extends AbstractPower {

    public static final String POWER_ID = KoishiMod.makeID("EphemeralPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static final int ORIGINAL_THRESHOLD = 15;
    public static int threshold = ORIGINAL_THRESHOLD;
    public static int intangible = 1;

    public EphemeralPower(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.loadRegion("wraithForm");

        updateDescription();
        checkTrigger();
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        updateDescription();
        checkTrigger();
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(owner, owner, ID));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + threshold + DESCRIPTIONS[1] + intangible + DESCRIPTIONS[2];
    }

    public void checkTrigger() {
        if (AbstractDungeon.player.hasRelic(ColorfulDays.ID)) {
            threshold = ColorfulDays.EPHEMERAL_INCREASE;
        } else {
            threshold = ORIGINAL_THRESHOLD;
        }
        updateDescription();
        if ((amount / threshold) >= 1) {
            this.flash();
            stackPower(-threshold);
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new IntangiblePlayerPower(owner, intangible), intangible));
        }
    }
}
