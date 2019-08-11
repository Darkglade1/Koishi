package Koishi.powers;

import Koishi.KoishiMod;
import Koishi.cards.Powers.Rare.ConfinedInnocent;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ConfinedInnocentPower extends AbstractPower {
    private int HP_LOSS = ConfinedInnocent.HP_LOSS;

    public static final String POWER_ID = KoishiMod.makeID("ConfinedInnocentPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ConfinedInnocentPower(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.loadRegion("juggernaut");

        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        HP_LOSS += ConfinedInnocent.HP_LOSS;
        super.stackPower(stackAmount);
    }

    @Override
    public void reducePower(int reduceAmount) {
        HP_LOSS -= ConfinedInnocent.HP_LOSS;
        super.reducePower(reduceAmount);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        for (int i = 0; i < HP_LOSS; i++) {
            AbstractDungeon.actionManager.addToBottom(new LoseHPAction(owner, owner, 1));
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new EphemeralPower(owner, amount), amount));
    }

    @Override
    public void updateDescription() {
        if (HP_LOSS == 1) {
            description = DESCRIPTIONS[0] + HP_LOSS + DESCRIPTIONS[1] + DESCRIPTIONS[3] + amount + DESCRIPTIONS[4];
        } else {
            description = DESCRIPTIONS[0] + HP_LOSS + DESCRIPTIONS[2] + DESCRIPTIONS[3] + amount + DESCRIPTIONS[4];
        }
    }
}
