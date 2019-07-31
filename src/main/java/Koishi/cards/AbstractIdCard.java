package Koishi.cards;

import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public abstract class AbstractIdCard extends AbstractDefaultCard {
    public AbstractIdCard(final String id,
                          final String img,
                          final int cost,
                          final CardType type,
                          final CardColor color,
                          final CardRarity rarity,
                          final CardTarget target) {

        super(id, img, cost, type, color, rarity, target);
    }

    @Override
    public void triggerWhenDrawn() {
        calculateCardDamage(null);
        use(AbstractDungeon.player, null);
        if (!freeToPlayOnce) {
            AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(costForTurn));
        } else {
            freeToPlayOnce = false;
        }
    }
}
