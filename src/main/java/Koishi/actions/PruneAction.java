package Koishi.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PruneAction extends AbstractGameAction {
    public static final String[] TEXT;
    private float startingDuration;
    private boolean anyNumber;

    public PruneAction(int numCards, boolean anyNumber) {
        this.amount = numCards;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        this.anyNumber = anyNumber;
    }

    public void update() {
        if (this.duration == this.startingDuration) {
            if (AbstractDungeon.player.drawPile.isEmpty()) {
                this.isDone = true;
                return;
            }
            CardGroup tmpGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (int i = 0; i < AbstractDungeon.player.drawPile.size(); i++) {
                tmpGroup.addToTop((AbstractCard) AbstractDungeon.player.drawPile.group
                        .get(AbstractDungeon.player.drawPile.size() - i - 1));
            }

            if (!this.anyNumber) {
                AbstractDungeon.gridSelectScreen.open(tmpGroup, this.amount, TEXT[0], false, false, this.anyNumber, false);
            } else {
                AbstractDungeon.gridSelectScreen.open(tmpGroup, this.amount, this.anyNumber, TEXT[0]);
            }

        } else if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                AbstractDungeon.player.drawPile.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
        tickDuration();
    }

    static {
        String[] text = {"Discard a card from your draw pile"};
        TEXT = text;
    }
}
