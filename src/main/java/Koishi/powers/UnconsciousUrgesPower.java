package Koishi.powers;

import Koishi.KoishiMod;
import Koishi.actions.UnconsciousUrgesAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class UnconsciousUrgesPower extends AbstractPower {

    public static final String POWER_ID = KoishiMod.makeID("UnconsciousUrgesPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public AbstractCard chosenCard;
    private boolean triggered = false;

    public UnconsciousUrgesPower(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.loadRegion("corruption");

        updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        triggered = false;
        AbstractDungeon.actionManager.addToBottom(new UnconsciousUrgesAction(this));
    }

    @Override
    public void onAfterCardPlayed(AbstractCard card) {
        if (!triggered && chosenCard != null && card.cardID.equals(chosenCard.cardID)) {
            this.flash();
            triggered = true;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new EnergyNextTurnPower(owner, amount), amount));
            updateDescription();
        }
    }

    @Override
    public void updateDescription() {
        if (triggered || chosenCard == null) {
            description = DESCRIPTIONS[0];
            for (int i = 0; i < amount; i++) {
                description += " [E]";
            }
            description += DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[2] + chosenCard.name + DESCRIPTIONS[3];
            for (int i = 0; i < amount; i++) {
                description += " [E]";
            }
            description += DESCRIPTIONS[1];
        }

    }
}
