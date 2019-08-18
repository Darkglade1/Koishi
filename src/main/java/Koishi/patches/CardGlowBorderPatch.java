package Koishi.patches;

import Koishi.KoishiMod;
import Koishi.cards.AbstractIdCard;
import Koishi.cards.Attacks.Common.Prune;
import Koishi.cards.Attacks.Uncommon.LastRemote;
import Koishi.cards.Attacks.Uncommon.SpiritedAway;
import Koishi.cards.Attacks.Uncommon.StrangeCloudFist;
import Koishi.cards.Skills.Uncommon.Possession;
import Koishi.powers.UnconsciousUrgesPower;
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
            } else if (c instanceof SpiritedAway && (AbstractDungeon.player.hasPower(IntangiblePlayerPower.POWER_ID) || AbstractDungeon.player.hasPower(IntangiblePower.POWER_ID))) {
                ReflectionHacks.setPrivate(__instance, com.megacrit.cardcrawl.vfx.AbstractGameEffect.class, "color", new Color(0.93F, 0.85F, 0.0F, 1.0F));
            } else if (c instanceof StrangeCloudFist && (AbstractDungeon.player.hasPower(IntangiblePlayerPower.POWER_ID) || AbstractDungeon.player.hasPower(IntangiblePower.POWER_ID))) {
                ReflectionHacks.setPrivate(__instance, com.megacrit.cardcrawl.vfx.AbstractGameEffect.class, "color", new Color(0.93F, 0.85F, 0.0F, 1.0F));
            } else if (c instanceof LastRemote && AbstractIdCard.drewIdCardThisTurn) {
                ReflectionHacks.setPrivate(__instance, com.megacrit.cardcrawl.vfx.AbstractGameEffect.class, "color", new Color(0.93F, 0.85F, 0.0F, 1.0F));
            } else if (c instanceof Prune && AbstractIdCard.drewIdCardThisTurn) {
                ReflectionHacks.setPrivate(__instance, com.megacrit.cardcrawl.vfx.AbstractGameEffect.class, "color", new Color(0.93F, 0.85F, 0.0F, 1.0F));
            } else if (AbstractDungeon.player.hasPower(UnconsciousUrgesPower.POWER_ID)) {
                UnconsciousUrgesPower p = (UnconsciousUrgesPower)AbstractDungeon.player.getPower(UnconsciousUrgesPower.POWER_ID);
                if (p.chosenCard != null && !p.triggered && p.chosenCard.name.equals(c.name)) {
                    ReflectionHacks.setPrivate(__instance, com.megacrit.cardcrawl.vfx.AbstractGameEffect.class, "color", new Color(0.0F, 0.85F, 0.0F, 1.0F));
                }
            }
        }
    }
}