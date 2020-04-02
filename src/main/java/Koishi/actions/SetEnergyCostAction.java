package Koishi.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class SetEnergyCostAction extends AbstractGameAction {

    AbstractCard card;

    public SetEnergyCostAction(AbstractCard card) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.card = card;
    }

    @Override
    public void update() {
        if (EnergyPanel.getCurrentEnergy() < card.costForTurn) {
            System.out.println("costForTurn: " + card.costForTurn);
            System.out.println("energy: " + EnergyPanel.getCurrentEnergy());
            card.setCostForTurn(EnergyPanel.getCurrentEnergy());
            System.out.println("costForTurnAfter: " + card.costForTurn);
        }
        this.isDone = true;

    }
}


