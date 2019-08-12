package Koishi.powers;

import Koishi.KoishiMod;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class EnergyNextTurnPower extends AbstractPower {

    public static final String POWER_ID = KoishiMod.makeID("EnergyNextTurnPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public EnergyNextTurnPower(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        //loads textures
        this.loadRegion("energized_green");

        updateDescription();
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

    @Override
    public void onEnergyRecharge() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(amount));
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, this.ID));
    }
}
