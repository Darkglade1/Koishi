package Koishi.powers;

import Koishi.KoishiMod;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PerfectMindControlPower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = KoishiMod.makeID("PerfectMindControlPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PerfectMindControlPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
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
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (info.owner == owner) {
            target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            AbstractDungeon.actionManager.currentAction.target = target;
            //AbstractDungeon.actionManager.addToTop(new DamageAction(target, new DamageInfo(source, damageAmount, info.type), AbstractGameAction.AttackEffect.NONE));
        }
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        power.owner = this.source;
        if (source == owner && power.type == PowerType.BUFF) {
            AbstractDungeon.actionManager.currentAction.target = this.source;
        }
        else if (source == owner && power.type == PowerType.DEBUFF) {
            target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            AbstractDungeon.actionManager.currentAction.target = target;
        }
    }

    @Override
    public void onGainedBlock(float blockAmount) {
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(source, source, (int)blockAmount));
    }

    @Override
    public int onHeal(int healAmount) {
        AbstractDungeon.actionManager.addToTop(new HealAction(source, source, healAmount));
        return healAmount;
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
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
