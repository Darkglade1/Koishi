package Koishi.patches;

import Koishi.cards.AbstractIntentChangingCard;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "renderHand",
        paramtypez = SpriteBatch.class

)
// A patch to make AoE intent changing cards properly show their preview when selected
public class IntentChangePatch {

    @SpirePrefixPatch
    public static void FixCalculateCardDamage(AbstractPlayer instance) {
        AbstractCard hoveredCard = instance.hoveredCard;
        if (hoveredCard != null && instance.isDraggingCard && !instance.inSingleTargetMode) {
            if (hoveredCard instanceof AbstractIntentChangingCard && hoveredCard.target == AbstractCard.CardTarget.ALL_ENEMY) {
                hoveredCard.calculateCardDamage(null);
            }
        }
    }
}