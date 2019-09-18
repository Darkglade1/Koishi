package Koishi.actions;

import Koishi.cards.Skills.Rare.FadingMemory;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;

public class FadingMemoryAction extends AbstractGameAction {

    FadingMemory card;

    public FadingMemoryAction(FadingMemory card) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.card = card;
    }

    public void update() {
        this.isDone = false;
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hand.size() != 0) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, card.magicNumber));
        }
        AbstractDungeon.actionManager.addToTop(new ExhaustAction(p, p, card.magicNumber, false));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, card.defaultSecondMagicNumber), card.defaultSecondMagicNumber));

        this.isDone = true;
        return;
    }
}


