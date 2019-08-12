package Koishi.powers;

import Koishi.KoishiMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Iterator;

public class HeartStoppingHorrorPower extends AbstractPower {

    public static final String POWER_ID = KoishiMod.makeID("HeartStoppingHorrorPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HeartStoppingHorrorPower(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.loadRegion("rupture");

        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        Iterator iterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while (iterator.hasNext()) {
            AbstractMonster mo = (AbstractMonster)iterator.next();
            if (mo.hasPower(TerrorPower.POWER_ID)) {
                this.flash();
                int damage = mo.getPower(TerrorPower.POWER_ID).amount;
                AbstractDungeon.actionManager.addToBottom(new DamageAction(mo, new DamageInfo(owner, damage * amount, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.NONE));
            }
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
