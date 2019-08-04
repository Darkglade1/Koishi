package Koishi.cards;

import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Iterator;

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

    public static AbstractIdCard returnTrulyRandomIdCard() {
        ArrayList<AbstractCard> list = new ArrayList();
        Iterator iterator = AbstractDungeon.srcCommonCardPool.group.iterator();

        AbstractCard c;
        while(iterator.hasNext()) {
            c = (AbstractCard)iterator.next();
            if (c instanceof AbstractIdCard && !(list.contains(c))) {
                list.add(c);
            }
        }

        iterator = AbstractDungeon.srcUncommonCardPool.group.iterator();

        while(iterator.hasNext()) {
            c = (AbstractCard)iterator.next();
            if (c instanceof AbstractIdCard && !(list.contains(c))) {
                list.add(c);
            }
        }

        iterator = AbstractDungeon.srcRareCardPool.group.iterator();

        while(iterator.hasNext()) {
            c = (AbstractCard)iterator.next();
            if (c instanceof AbstractIdCard && !(list.contains(c))) {
                list.add(c);
            }
        }

        return (AbstractIdCard)list.get(AbstractDungeon.cardRandomRng.random(list.size() - 1));
    }
}
