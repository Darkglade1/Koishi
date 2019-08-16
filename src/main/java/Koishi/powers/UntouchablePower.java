package Koishi.powers;

import Koishi.KoishiMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class UntouchablePower extends AbstractPower {

    public static final String POWER_ID = KoishiMod.makeID("UntouchablePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public UntouchablePower(final AbstractCreature owner) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.loadRegion("noPain");

        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        ArrayList<AbstractCard> exhaustList = new ArrayList<>();
        AbstractPlayer p = AbstractDungeon.player;
        for (AbstractCard card : p.hand.group) {
            if (card.type == AbstractCard.CardType.STATUS || card.type == AbstractCard.CardType.CURSE) {
                exhaustList.add(card);
            }
        }
        for (AbstractCard card : exhaustList) {
            p.hand.moveToExhaustPile(card);
        }
        exhaustList.clear();

        for (AbstractCard card : p.drawPile.group) {
            if (card.type == AbstractCard.CardType.STATUS || card.type == AbstractCard.CardType.CURSE) {
                exhaustList.add(card);
            }
        }
        for (AbstractCard card : exhaustList) {
            p.drawPile.moveToExhaustPile(card);
        }
        exhaustList.clear();

        for (AbstractCard card : p.discardPile.group) {
            if (card.type == AbstractCard.CardType.STATUS || card.type == AbstractCard.CardType.CURSE) {
                exhaustList.add(card);
            }
        }
        for (AbstractCard card : exhaustList) {
            p.discardPile.moveToExhaustPile(card);
        }
        exhaustList.clear();
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
