package Koishi.actions;

import Koishi.powers.UnconsciousUrgesPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class UnconsciousUrgesAction extends AbstractGameAction {
    private UnconsciousUrgesPower power;

    public UnconsciousUrgesAction(UnconsciousUrgesPower owner) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        power = owner;
    }

    public void update() {
        this.isDone = false;

        ArrayList<AbstractCard> hand = new ArrayList<>();
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card.type != AbstractCard.CardType.STATUS && card.type != AbstractCard.CardType.CURSE) {
                hand.add(card);
            }
        }
        if (hand.size() > 0) {
            power.chosenCard = hand.get(AbstractDungeon.cardRandomRng.random(hand.size() - 1));

            for (AbstractCard card : AbstractDungeon.player.hand.group) {
                if (card.cardID.equals(power.chosenCard.cardID)) {
                    card.flash();
                }
            }

        } else {
            power.chosenCard = null;
        }
        power.updateDescription();

        this.isDone = true;
        return;
    }
}


