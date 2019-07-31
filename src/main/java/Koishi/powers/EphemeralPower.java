package Koishi.powers;

import Koishi.KoishiMod;
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

    public static int threshold = 15;
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
        if ((amount / threshold) >= 1) {
            stackPower(-threshold);
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new IntangiblePlayerPower(owner, intangible), intangible));
        }
    }
}
