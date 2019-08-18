package Koishi.powers;

import Koishi.KoishiMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

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

    public int onAttackToChangeDamagePreBlock(DamageInfo info, int damageAmount) {
        if (info.owner == owner) {
            AbstractMonster newTarget = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            AbstractDungeon.actionManager.currentAction.target = newTarget;
            AbstractDungeon.actionManager.addToBottom(new DamageAction(newTarget, new DamageInfo(source, info.base, info.type), AbstractGameAction.AttackEffect.NONE));
            return 0; //makes the monster do 0 damage to the player
        }
        return damageAmount;
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (source == owner && power.type == PowerType.BUFF) {
            power.owner = this.source;
            AbstractDungeon.actionManager.currentAction.target = this.source;
        }
        else if (source == owner && power.type == PowerType.DEBUFF) {
            AbstractMonster newTarget = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            power.owner = newTarget;
            AbstractDungeon.actionManager.currentAction.target = newTarget;
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
