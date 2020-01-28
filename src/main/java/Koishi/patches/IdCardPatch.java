package Koishi.patches;

import Koishi.cards.AbstractIdCard;
import basemod.BaseMod;
import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import javassist.CtBehavior;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "draw",
        paramtypez={
                int.class,
        }

)
// A patch to make Id cards discard properly when triggered
public class IdCardPatch {

    @SpireInsertPatch(locator = Locator.class, localvars = "c")
    public static void FixDiscardPatch(AbstractPlayer instance, int drawAmount, AbstractCard c) {
        if (c instanceof AbstractIdCard && AbstractIdCard.idEnabled) {
            instance.hand.removeCard(c);
            instance.discardPile.addToTop(c);
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(CardGroup.class, "removeTopCard");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}