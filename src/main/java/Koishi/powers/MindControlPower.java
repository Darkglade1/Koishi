package Koishi.powers;

import Koishi.KoishiMod;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BeatOfDeathPower;
import com.megacrit.cardcrawl.powers.HexPower;
import com.megacrit.cardcrawl.powers.SharpHidePower;
import com.megacrit.cardcrawl.powers.StasisPower;

public class MindControlPower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = KoishiMod.makeID("MindControlPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MindControlPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.DEBUFF;
        isTurnBased = true;

        this.loadRegion("confusion");

        updateDescription();
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (source == owner && power.type == PowerType.BUFF) {
            power.owner = this.source;
            AbstractDungeon.actionManager.currentAction.target = this.source;
            if (power instanceof SharpHidePower || power instanceof BeatOfDeathPower || power instanceof StasisPower) {
                if (power instanceof  StasisPower) {
                    power.onDeath();
                }
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.source, this.source, power.ID));
            }
        }
        else if (source == owner && power.type == PowerType.DEBUFF) {
            AbstractMonster newTarget = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            power.owner = newTarget;
            AbstractDungeon.actionManager.currentAction.target = newTarget;
            if (power instanceof HexPower) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(newTarget, newTarget, power.ID));
            }
        }
    }

    public int onBlock(float blockAmount) {
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(source, source, (int)blockAmount));
        return 0;
    }

    @Override
    public int onHeal(int healAmount) {
        AbstractDungeon.actionManager.addToTop(new HealAction(source, source, healAmount));
        return 0;
    }

    @Override
    public void atEndOfRound() {
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }
}
