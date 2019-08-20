package Koishi.patches;

import Koishi.relics.EchoesOfADeadGirl;
import Koishi.relics.TeethAndClaws;
import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import javassist.CtBehavior;

@SpirePatch(
        clz = ApplyPowerAction.class,
        method = "update"

)
// A patch to make powers trigger relics
public class ApplyPowerRelicPatch {

    @SpireInsertPatch(locator = Locator.class, localvars = "powerToApply")
    public static void CheckPowers(ApplyPowerAction instance, AbstractPower powerToApply) {
        if (AbstractDungeon.player.hasRelic(TeethAndClaws.ID) && instance.source != null && instance.source.isPlayer && instance.target != instance.source && powerToApply.type == AbstractPower.PowerType.DEBUFF && !instance.target.hasPower(ArtifactPower.POWER_ID)) {
            AbstractDungeon.player.getRelic(TeethAndClaws.ID).onTrigger(AbstractDungeon.player);
        }
        if (AbstractDungeon.player.hasRelic(EchoesOfADeadGirl.ID) && instance.target != null && instance.target.isPlayer && (powerToApply instanceof IntangiblePlayerPower || powerToApply instanceof IntangiblePower)) {
            AbstractDungeon.player.getRelic(EchoesOfADeadGirl.ID).onTrigger(AbstractDungeon.player);
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "hasRelic");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}