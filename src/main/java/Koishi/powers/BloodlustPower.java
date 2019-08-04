package Koishi.powers;

import Koishi.KoishiMod;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class BloodlustPower extends AbstractPower {

    public static final String POWER_ID = KoishiMod.makeID("BloodlustPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BloodlustPower(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.DEBUFF;
        isTurnBased = false;

        // We load those textures here.
        this.loadRegion("brutality");

        updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(amount));
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ID));
    }

    // Update the description when you apply this power.
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
        for (int i = 0; i < amount; i++) {
            description += " [E]";
        }
        description += DESCRIPTIONS[1];
    }
}
