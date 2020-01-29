package Koishi.cards;

import Koishi.actions.PlayIdCardAction;
import Koishi.cards.Attacks.Common.SubconsciousSweep;
import Koishi.cards.Attacks.Uncommon.HeartAttack;
import Koishi.cards.Skills.Common.SubterraneanRose;
import Koishi.cards.Skills.Common.UnconsciousUprising;
import Koishi.cards.Skills.Rare.FadingMemory;
import Koishi.cards.Skills.Uncommon.IdleWhim;
import Koishi.cards.Skills.Uncommon.MassHysteria;
import Koishi.cards.Skills.Uncommon.RorschachInDanmaku;
import Koishi.cards.Skills.Uncommon.SprinkleStarAndHeart;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

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
            if (!freeToPlayOnce) {
                if (EnergyPanel.getCurrentEnergy() > 0 && this.canUse(AbstractDungeon.player, null)) {
                    AbstractDungeon.player.energy.use(this.costForTurn);
                }
            } else {
                freeToPlayOnce = false;
            }
            AbstractDungeon.actionManager.addToBottom(new PlayIdCardAction(this, AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng), false));
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
