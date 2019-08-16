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

        ArrayList<AbstractCard> hand = AbstractDungeon.player.hand.group;
        if (hand.size() > 0) {
            power.chosenCard = hand.get(AbstractDungeon.cardRandomRng.random(hand.size() - 1));
            int count = 0;
            //Tries to avoid selecting unplayable cards like Status and Curse
            while ((power.chosenCard.type == AbstractCard.CardType.CURSE || power.chosenCard.type == AbstractCard.CardType.STATUS) && count < 10) {
                power.chosenCard = hand.get(AbstractDungeon.cardRandomRng.random(hand.size() - 1));
                count++;
            }
            if (power.chosenCard.type == AbstractCard.CardType.CURSE || power.chosenCard.type == AbstractCard.CardType.STATUS) {
                power.chosenCard = null;
            }
            if (power.chosenCard != null) {
                power.chosenCard.flash();
            }
        } else {
            power.chosenCard = null;
        }
        power.updateDescription();

        this.isDone = true;
        return;
    }
}


