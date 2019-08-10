package Koishi.powers;

import Koishi.KoishiMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import java.util.Iterator;

public class VengefulSpiritPower extends AbstractPower {

    public static final String POWER_ID = KoishiMod.makeID("VengefulSpiritPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public VengefulSpiritPower(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.loadRegion("storm");

        updateDescription();
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (target == owner && (power instanceof IntangiblePlayerPower || power instanceof IntangiblePower)) {
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.flash();
                Iterator iterator = AbstractDungeon.getMonsters().monsters.iterator();

                while (iterator.hasNext()) {
                    AbstractMonster m = (AbstractMonster) iterator.next();
                    if (!m.isDead && !m.isDying) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, owner, new VulnerablePower(m, amount, false), amount));
                    }
                }
            }
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
