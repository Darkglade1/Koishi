package Koishi.powers;

import Koishi.KoishiMod;
import Koishi.cards.AbstractIdCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FreudianInstinctPower extends AbstractPower {

    public static final String POWER_ID = KoishiMod.makeID("FreudianInstinctPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FreudianInstinctPower(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.loadRegion("afterImage");

        updateDescription();
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (card instanceof AbstractIdCard) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(owner, owner, amount));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
