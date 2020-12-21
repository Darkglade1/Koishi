package Koishi.patches;

import Koishi.characters.KoishiCharacter;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(
        clz = DrawCardAction.class,
        method = "update"

)
// A patch to make draw happen one at a time
public class DrawCardActionPatch {
    @SpirePrefixPatch
    public static void DrawOneAtATime(DrawCardAction instance) {
        if (instance.amount > 1 && AbstractDungeon.player instanceof KoishiCharacter) {
            AbstractGameAction followUp = ReflectionHacks.getPrivate(instance, DrawCardAction.class, "followUpAction");
            if (followUp == null) { //avoid breaking actions with followUps
                int remainder = instance.amount - 1;
                instance.amount = 1;
                for (int i = 0; i < remainder; i++) {
                    AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, 1));
                }
            }
        }
    }
}