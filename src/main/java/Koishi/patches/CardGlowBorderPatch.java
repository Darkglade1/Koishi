package Koishi.patches;

import Koishi.KoishiMod;
import Koishi.cards.Skills.Uncommon.Possession;
import Koishi.tags.Tags;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.CardGlowBorder;

@SpirePatch(clz = CardGlowBorder.class, method = "<ctor>")
public class CardGlowBorderPatch {
    @SpirePostfixPatch
    public static void PostFix(CardGlowBorder __instance, AbstractCard c) {
        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
            if (c.hasTag(Tags.DEBUFF_THIS_TURN) && KoishiMod.appliedDebuffThisTurn) {
                ReflectionHacks.setPrivate(__instance, com.megacrit.cardcrawl.vfx.AbstractGameEffect.class, "color", new Color(0.93F, 0.85F, 0.0F, 1.0F));
            } else if (c instanceof Possession && (AbstractDungeon.player.hasPower(IntangiblePlayerPower.POWER_ID) || AbstractDungeon.player.hasPower(IntangiblePower.POWER_ID))) {
                ReflectionHacks.setPrivate(__instance, com.megacrit.cardcrawl.vfx.AbstractGameEffect.class, "color", new Color(0.93F, 0.85F, 0.0F, 1.0F));
            }
        }
    }
}