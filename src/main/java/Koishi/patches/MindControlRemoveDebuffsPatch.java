package Koishi.patches;

import Koishi.powers.MindControlPower;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;

import java.util.ArrayList;

@SpirePatch(
        clz = RemoveDebuffsAction.class,
        method = "update"

)
// A patch to make mind controlled enemies purge player debuffs instead of their own
public class MindControlRemoveDebuffsPatch {
    @SpireInsertPatch(locator = MindControlRemoveDebuffsPatch.Locator.class, localvars = {"c"})
    public static void ChangeTarget(RemoveDebuffsAction instance, @ByRef AbstractCreature[] c) {
       if (c[0].hasPower(MindControlPower.POWER_ID)) {
           c[0] = AbstractDungeon.player;
       }
    }
    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(ArrayList.class, "iterator");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}