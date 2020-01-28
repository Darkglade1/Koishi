package Koishi.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(
        clz = DrawCardAction.class,
        method = SpirePatch.CONSTRUCTOR,
        paramtypez = {
                AbstractCreature.class,
                int.class,
                boolean.class
        }

)
// A patch to make draw happen one at a time
public class DrawCardActionPatch {
    @SpirePostfixPatch
    public static void DrawOneAtATime(DrawCardAction instance, AbstractCreature source, int amount, boolean endTurnDraw) {
        if (instance.amount > 1) {
            int remainder = instance.amount - 1;
            instance.amount = 1;
            for (int i = 0; i < remainder; i++) {
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(source, 1, false));
            }
        }
    }
}