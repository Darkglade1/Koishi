//package Koishi.patches;
//
//import Koishi.powers.UnconsciousUrgesPower;
//import basemod.ReflectionHacks;
//import com.badlogic.gdx.graphics.Color;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.rooms.AbstractRoom;
//import com.megacrit.cardcrawl.vfx.cardManip.CardGlowBorder;
//
//@SpirePatch(clz = CardGlowBorder.class, method = "<ctor>")
//public class CardGlowBorderPatch {
//    @SpirePostfixPatch
//    public static void PostFix(CardGlowBorder __instance, AbstractCard c) {
//        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
//           if (AbstractDungeon.player.hasPower(UnconsciousUrgesPower.POWER_ID)) {
//                UnconsciousUrgesPower p = (UnconsciousUrgesPower)AbstractDungeon.player.getPower(UnconsciousUrgesPower.POWER_ID);
//                if (p.chosenCard != null && !p.triggered && p.chosenCard.cardID.equals(c.cardID)) {
//                    ReflectionHacks.setPrivate(__instance, com.megacrit.cardcrawl.vfx.AbstractGameEffect.class, "color", new Color(0.0F, 0.85F, 0.0F, 1.0F));
//                }
//            }
//        }
//    }
//}