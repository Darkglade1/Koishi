package Koishi.patches;

import Koishi.powers.MindControlPower;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import javassist.CtBehavior;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "damage",
        paramtypez={
                DamageInfo.class,
        }

)
// A patch to make mind controlled enemies not break block
public class MindControlDamagePatch {
    @SpireInsertPatch(locator = MindControlDamagePatch.Locator.class, localvars = {"damageAmount"})
    public static void CheckDamage(AbstractPlayer instance, DamageInfo info, @ByRef int[] damageAmount) {
        if (info.owner != null) {
            if (info.owner.hasPower(MindControlPower.POWER_ID)) {
                MindControlPower p = (MindControlPower)info.owner.getPower(MindControlPower.POWER_ID);
                damageAmount[0] = p.onAttackToChangeDamagePreBlock(info, damageAmount[0]);
            }
        }
    }
    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "decrementBlock");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}