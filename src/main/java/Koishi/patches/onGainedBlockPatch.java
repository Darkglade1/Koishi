package Koishi.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Iterator;

@SpirePatch(
        clz = AbstractCreature.class,
        method = "addBlock",
        paramtypez={
                int.class,
        }

)
// A patch to make onGainBlock trigger when monsters gain block
public class onGainedBlockPatch {
    @SpirePostfixPatch
    public static void TriggerOnGainedBlock(AbstractCreature instance, int blockAmount) {
        if (!instance.isPlayer) {
            float tmp = (float)blockAmount;
            if (tmp > 0.0F) {
                Iterator iterator = instance.powers.iterator();
                while(iterator.hasNext()) {
                    AbstractPower p = (AbstractPower)iterator.next();
                    p.onGainedBlock(tmp);
                }
            }
        }
    }
}