package Koishi.powers;

import Koishi.KoishiMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;

public class FormlessExistencePower extends AbstractPower {

    public static final String POWER_ID = KoishiMod.makeID("FormlessExistencePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private boolean active = true;

    public FormlessExistencePower(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.loadRegion("blur");

        updateDescription();
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (target == owner && (power instanceof IntangiblePlayerPower || power instanceof IntangiblePower)) {
            if(active) {
                active = false;
                this.addToTop(new AbstractGameAction() {
                    @Override
                    public void update() {
                        this.isDone = true;
                        active = true;
                    }
                });
                this.flash();
                int bonusAmount = power.amount * this.amount;
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(owner, owner, new IntangiblePlayerPower(owner, bonusAmount), bonusAmount));
            }
        }
    }

    @Override
    public int onHeal(int healAmount) {
        this.flash();
        return 0;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + (amount + 1) + DESCRIPTIONS[1];
    }
}
