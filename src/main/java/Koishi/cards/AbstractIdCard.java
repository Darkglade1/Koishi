package Koishi.cards;

import Koishi.cards.Attacks.Common.SubconsciousSweep;
import Koishi.cards.Attacks.Uncommon.HeartAttack;
import Koishi.cards.Skills.Common.SprinkleStarAndHeart;
import Koishi.cards.Skills.Common.UnconsciousUprising;
import Koishi.cards.Skills.Rare.FadingMemory;
import Koishi.cards.Skills.Uncommon.GhostParty;
import Koishi.cards.Skills.Uncommon.IdleWhim;
import Koishi.cards.Skills.Uncommon.PredatoryInstincts;
import Koishi.cards.Skills.Uncommon.RorschachInDanmaku;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class AbstractIdCard extends AbstractDefaultCard {

    public static int idCardsDrawn = 0;
    public static boolean drewIdCardThisTurn = false;
    public static boolean idEnabled = true;

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
        if (idEnabled) {
            if (this.target == CardTarget.ENEMY) {
                AbstractMonster mo = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
                calculateCardDamage(mo);
                use(AbstractDungeon.player, mo);
            } else {
                calculateCardDamage(null);
                use(AbstractDungeon.player, null);
            }

            if (!freeToPlayOnce) {
                AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(costForTurn));
            } else {
                freeToPlayOnce = false;
            }
        }
        idCardsDrawn++;
        drewIdCardThisTurn = true;
    }

    public static AbstractIdCard returnTrulyRandomIdCard() {
        ArrayList<AbstractCard> list = new ArrayList();
        list.add(new SubconsciousSweep());
        list.add(new UnconsciousUprising());
        list.add(new IdleWhim());
        list.add(new PredatoryInstincts());
        list.add(new RorschachInDanmaku());
        list.add(new HeartAttack());
        //list.add(new GhostParty());
        list.add(new SprinkleStarAndHeart());
        list.add(new FadingMemory());

        return (AbstractIdCard)list.get(AbstractDungeon.cardRandomRng.random(list.size() - 1));
    }
}
