package Koishi.characters;

import Koishi.BetterSpriterAnimation;
import Koishi.KoishiMod;
import Koishi.KoishiPlayerListener;
import Koishi.cards.Attacks.Common.DreadfulBlow;
import Koishi.cards.Attacks.Common.HauntingSlash;
import Koishi.cards.Attacks.Common.PhantomStrike;
import Koishi.cards.Attacks.Common.RacingHeart;
import Koishi.cards.Attacks.Common.StingingMind;
import Koishi.cards.Attacks.Common.SubterraneanRose;
import Koishi.cards.Attacks.Uncommon.BloodcurdlingScream;
import Koishi.cards.Attacks.Uncommon.GrowingPain;
import Koishi.cards.Attacks.Uncommon.ParalyzingFear;
import Koishi.cards.Attacks.Uncommon.SpiritedAway;
import Koishi.cards.Attacks.Uncommon.TremblingHands;
import Koishi.cards.DefaultCommonAttack;
import Koishi.cards.Skills.Common.EmbryosDream;
import Koishi.cards.Skills.Common.FleetingPhantom;
import Koishi.cards.Skills.Common.Provoke;
import Koishi.cards.Skills.Common.SprinkleStarAndHeart;
import Koishi.cards.Skills.Common.UnansweredLove;
import Koishi.cards.Skills.Rare.DNAsFlaw;
import Koishi.cards.Skills.Rare.MindStellarRelief;
import Koishi.cards.Skills.Uncommon.ApparitionsStalkTheNight;
import Koishi.cards.Skills.Uncommon.Bloodlust;
import Koishi.cards.Skills.Uncommon.CatchAndRose;
import Koishi.cards.Skills.Common.HeartfeltFancy;
import Koishi.cards.Skills.Common.JumpScare;
import Koishi.cards.Skills.Rare.PerfectMindControl;
import Koishi.cards.Skills.Common.PhantomBarrier;
import Koishi.cards.Skills.Uncommon.DanmakuParanoia;
import Koishi.cards.Skills.Uncommon.FourthEye;
import Koishi.cards.Skills.Uncommon.GeneticsOfTheUnconscious;
import Koishi.cards.Skills.Uncommon.GhostParty;
import Koishi.cards.Skills.Uncommon.MassHysteria;
import Koishi.cards.Skills.Uncommon.ReleaseOfTheId;
import Koishi.cards.Skills.Uncommon.RorschachInDanmaku;
import Koishi.cards.Skills.Common.Whimsy;
import Koishi.cards.Skills.Uncommon.Vanish;
import Koishi.relics.ImaginaryFriend;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.brashmonkey.spriter.Player;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import static Koishi.KoishiMod.*;
import static Koishi.characters.KoishiCharacter.Enums.COLOR_DARK_GREEN;

//Wiki-page https://github.com/daviscook477/BaseMod/wiki/Custom-Characters
//and https://github.com/daviscook477/BaseMod/wiki/Migrating-to-5.0
//All text (starting description and loadout, anything labeled TEXT[]) can be found in KoishiMod-character-Strings.json in the resources

public class KoishiCharacter extends CustomPlayer {
    public static final Logger logger = LogManager.getLogger(KoishiMod.class.getName());

    // =============== CHARACTER ENUMERATORS =================
    // These are enums for your Characters color (both general color and for the card library) as well as
    // an enum for the name of the player class - IRONCLAD, THE_SILENT, DEFECT, YOUR_CLASS ...
    // These are all necessary for creating a character. If you want to find out where and how exactly they are used
    // in the basegame (for fun and education) Ctrl+click on the PlayerClass, CardColor and/or LibraryType below and go down the
    // Ctrl+click rabbit hole

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass KOISHI;
        @SpireEnum(name = "DARK_GREEN_COLOR") // These two HAVE to have the same absolutely identical name.
        public static AbstractCard.CardColor COLOR_DARK_GREEN;
        @SpireEnum(name = "DARK_GREEN_COLOR") @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }

    // =============== CHARACTER ENUMERATORS  =================


    // =============== BASE STATS =================

    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 60;
    public static final int MAX_HP = 60;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 5;
    public static final int ORB_SLOTS = 0;

    // =============== /BASE STATS/ =================


    // =============== STRINGS =================

    private static final String ID = makeID("Koishi");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;

    // =============== /STRINGS/ =================


    // =============== TEXTURES OF BIG ENERGY ORB ===============

    public static final String[] orbTextures = {
            "KoishiResources/images/char/defaultCharacter/orb/layer1.png",
            "KoishiResources/images/char/defaultCharacter/orb/layer2.png",
            "KoishiResources/images/char/defaultCharacter/orb/layer3.png",
            "KoishiResources/images/char/defaultCharacter/orb/layer4.png",
            "KoishiResources/images/char/defaultCharacter/orb/layer5.png",
            "KoishiResources/images/char/defaultCharacter/orb/layer6.png",
            "KoishiResources/images/char/defaultCharacter/orb/layer1d.png",
            "KoishiResources/images/char/defaultCharacter/orb/layer2d.png",
            "KoishiResources/images/char/defaultCharacter/orb/layer3d.png",
            "KoishiResources/images/char/defaultCharacter/orb/layer4d.png",
            "KoishiResources/images/char/defaultCharacter/orb/layer5d.png",};

    // =============== /TEXTURES OF BIG ENERGY ORB/ ===============

    // =============== CHARACTER CLASS START =================

    public KoishiCharacter(String name, PlayerClass setClass) {
        super(name, setClass, orbTextures,
                "KoishiResources/images/char/defaultCharacter/orb/vfx.png", null, new BetterSpriterAnimation(
                        "KoishiResources/images/char/defaultCharacter/Spriter/KoishiAnimation.scml"));

        Player.PlayerListener listener = new KoishiPlayerListener(this);
        ((BetterSpriterAnimation)this.animation).myPlayer.addListener(listener);

        // =============== TEXTURES, ENERGY, LOADOUT =================  

        initializeClass(null, // required call to load textures and setup energy/loadout.
                // I left these in KoishiMod.java (Ctrl+click them to see where they are, Ctrl+hover to see what they read.)
                THE_DEFAULT_SHOULDER_1, // campfire pose
                THE_DEFAULT_SHOULDER_2, // another campfire pose
                THE_DEFAULT_CORPSE, // dead corpse
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN)); // energy manager

        // =============== /TEXTURES, ENERGY, LOADOUT/ =================


//        loadAnimation("KoishiResources/images/char/defaultCharacter/Koishi.atlas", "KoishiResources/images/char/defaultCharacter/Koishi.json", 1.0F);
//        AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
//        e.setTime(e.getEndTime() * MathUtils.random());


        // =============== TEXT BUBBLE LOCATION =================

        dialogX = (drawX + 0.0F * Settings.scale); // set location for text bubbles
        dialogY = (drawY + 220.0F * Settings.scale); // you can just copy these values

        // =============== /TEXT BUBBLE LOCATION/ =================

    }

    // =============== /CHARACTER CLASS END/ =================

    // Starting description and loadout
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    // Starting Deck
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();

        logger.info("Begin loading starter Deck Strings");

//        retVal.add(DefaultCommonAttack.ID);
        //retVal.add(PhantomStrike.ID);
        //retVal.add(ReflexRadar.ID);
        //retVal.add(ReflexRadar.ID);
        //retVal.add(PhantomStrike.ID);
        //retVal.add(ImGoingToCallYouNow.ID);
        //retVal.add(PerfectMindControl.ID);
       // retVal.add(PerfectMindControl.ID);
        //retVal.add(PerfectMindControl.ID);
        //retVal.add(ImGoingToCallYouNow.ID);
        //retVal.add(PhilosophyOfTheDespised.ID);
//        retVal.add(PhilosophyOfTheDespised.ID);
//        retVal.add(EmbersOfLove.ID);
        //retVal.add(EmbersOfLove.ID);
//        retVal.add(ReleaseOfTheId.ID);
//        retVal.add(FleetingPhantom.ID);
//        retVal.add(UnansweredLove.ID);
//        retVal.add(GeneticsOfTheUnconscious.ID);
//        retVal.add(Bloodlust.ID);
//        retVal.add(RorschachInDanmaku.ID);
//        retVal.add(CatchAndRose.ID);
//        retVal.add(JumpScare.ID);
//        retVal.add(HeartfeltFancy.ID);
//        retVal.add(Whimsy.ID);
//        retVal.add(PhantomBarrier.ID);
//        retVal.add(UnconsciousUprising.ID);
//        retVal.add(UnconsciousUprising.ID);
//        retVal.add(PredatoryInstincts.ID);
//        retVal.add(SubconsciousSweep.ID);
//        retVal.add(SubconsciousSweep.ID);
//          retVal.add(HauntingSlash.ID);
//          retVal.add(Provoke.ID);
          retVal.add(DreadfulBlow.ID);
//          retVal.add(StingingMind.ID);
        //retVal.add(GhostParty.ID);
//        retVal.add(MassHysteria.ID);
//        retVal.add(DanmakuParanoia.ID);
//        retVal.add(EmbryosDream.ID);
//        retVal.add(FourthEye.ID);
        retVal.add(JumpScare.ID);
        //retVal.add(JumpScare.ID);
//        retVal.add(Vanish.ID);
//        retVal.add(ApparitionsStalkTheNight.ID);
       // retVal.add(DNAsFlaw.ID);
//        retVal.add(MindStellarRelief.ID);
//        retVal.add(SubterraneanRose.ID);
//        retVal.add(GrowingPain.ID);
//        retVal.add(SpiritedAway.ID);
        retVal.add(BloodcurdlingScream.ID);
        retVal.add(TremblingHands.ID);
        retVal.add(ParalyzingFear.ID);
        retVal.add(RacingHeart.ID);
        retVal.add(SprinkleStarAndHeart.ID);

        return retVal;
    }

    // Starting Relics	
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();

        retVal.add(ImaginaryFriend.ID);

        return retVal;
    }

    // character Select screen effect
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_DAGGER_1", 1.25f); // Sound Effect
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false); // Screen Effect
    }

    // character Select on-button-press sound effect
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_DAGGER_1";
    }

    // Should return how much HP your maximum HP reduces by when starting a run at
    // Ascension 14 or higher. (ironclad loses 5, defect and silent lose 4 hp respectively)
    @Override
    public int getAscensionMaxHPLoss() {
        return 3;
    }

    // Should return the card color enum to be associated with your character.
    @Override
    public AbstractCard.CardColor getCardColor() {
        return COLOR_DARK_GREEN;
    }

    // Should return a color object to be used to color the trail of moving cards
    @Override
    public Color getCardTrailColor() {
        return KoishiMod.DARK_GREEN;
    }

    // Should return a BitmapFont object that you can use to customize how your
    // energy is displayed from within the energy orb.
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    // Should return class name as it appears in run history screen.
    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    //Which card should be obtainable from the Match and Keep event?
    @Override
    public AbstractCard getStartCardForEvent() {
        return new DefaultCommonAttack();
    }

    // The class name as it appears next to your player name in-game
    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    // Should return a new instance of your character, sending name as its name parameter.
    @Override
    public AbstractPlayer newInstance() {
        return new KoishiCharacter(name, chosenClass);
    }

    // Should return a Color object to be used to color the miniature card images in run history.
    @Override
    public Color getCardRenderColor() {
        return KoishiMod.DARK_GREEN;
    }

    // Should return a Color object to be used as screen tint effect when your
    // character attacks the heart.
    @Override
    public Color getSlashAttackColor() {
        return KoishiMod.DARK_GREEN;
    }

    // Should return an AttackEffect array of any size greater than 0. These effects
    // will be played in sequence as your character's finishing combo on the heart.
    // Attack effects are the same as used in DamageAction and the like.
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }

    // Should return a string containing what text is shown when your character is
    // about to attack the heart. For example, the defect is "NL You charge your
    // core to its maximum..."
    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    // The vampire events refer to the base game characters as "brother", "sister",
    // and "broken one" respectively.This method should return a String containing
    // the full text that will be displayed as the first screen of the vampires event.
    @Override
    public String getVampireText() {
        return TEXT[2];
    }

    @Override
    public void damage(DamageInfo info) {
        int thisHealth = this.currentHealth;
        super.damage(info);
        int trueDamage = thisHealth - this.currentHealth;
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && trueDamage > 0) {
            if (this.isDead) {
                KoishiMod.runAnimation("downed");
            } else {
                KoishiMod.runAnimation("hit");
            }
        }
    }

    //Stops the corpse img from overwriting the SpriterAnimation
    @Override
    public void playDeathAnimation() {
        return;
    }

    //Runs a specific animation
    public void runAnim(String animation) {
        ((BetterSpriterAnimation)this.animation).myPlayer.setAnimation(animation);
    }

    //Resets character back to idle animation
    public void resetAnimation() {
        ((BetterSpriterAnimation)this.animation).myPlayer.setAnimation("idle");
    }

    //Prevents any further animation once the death animation is finished
    public void stopAnimation() {
        int time = ((BetterSpriterAnimation)this.animation).myPlayer.getAnimation().length;
        ((BetterSpriterAnimation)this.animation).myPlayer.setTime(time);
        ((BetterSpriterAnimation)this.animation).myPlayer.speed = 0;
    }


}
