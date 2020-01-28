package Koishi.cards;

import Koishi.cards.Attacks.Common.SubconsciousSweep;
import Koishi.cards.Attacks.Uncommon.HeartAttack;
import Koishi.cards.Skills.Common.SubterraneanRose;
import Koishi.cards.Skills.Common.UnconsciousUprising;
import Koishi.cards.Skills.Rare.FadingMemory;
import Koishi.cards.Skills.Uncommon.IdleWhim;
import Koishi.cards.Skills.Uncommon.MassHysteria;
import Koishi.cards.Skills.Uncommon.RorschachInDanmaku;
import Koishi.cards.Skills.Uncommon.SprinkleStarAndHeart;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;

import java.util.ArrayList;

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
            AbstractCard tmp = this.makeSameInstanceOf();
            AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(tmp));
            AbstractDungeon.player.discardPile.removeCard(tmp);

            if (!freeToPlayOnce) {
                AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(costForTurn));
            } else {
                freeToPlayOnce = false;
            }

            GameActionManager.queueExtraCard(this, null);
        }
        idCardsDrawn++;
        drewIdCardThisTurn = true;
    }

    public static AbstractIdCard returnTrulyRandomIdCard() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new SubconsciousSweep());
        list.add(new UnconsciousUprising());
        list.add(new SprinkleStarAndHeart());
        list.add(new SubterraneanRose());
        list.add(new IdleWhim());
        //list.add(new PredatoryInstincts()); //No longer an Id card
        list.add(new RorschachInDanmaku());
        list.add(new HeartAttack());
        list.add(new MassHysteria());
        //list.add(new GhostParty()); //Avoids randomly giving out Ghost Party since it's a pain to deal with when unexpected
        list.add(new FadingMemory());

        return (AbstractIdCard)list.get(AbstractDungeon.cardRandomRng.random(list.size() - 1));
    }
}
