package Koishi;

import Koishi.cards.AbstractIdCard;
import Koishi.cards.Attacks.Common.BasicStrike;
import Koishi.cards.Attacks.Common.DreadfulBlow;
import Koishi.cards.Attacks.Common.HauntingSlash;
import Koishi.cards.Attacks.Common.Jaunt;
import Koishi.cards.Attacks.Common.PhantomStrike;
import Koishi.cards.Attacks.Common.Prune;
import Koishi.cards.Attacks.Common.RacingHeart;
import Koishi.cards.Skills.Common.ReflexRadar;
import Koishi.cards.Attacks.Common.StingingMind;
import Koishi.cards.Attacks.Common.SubconsciousSweep;
import Koishi.cards.Skills.Common.SubterraneanRose;
import Koishi.cards.Attacks.Rare.MentalBreakdown;
import Koishi.cards.Attacks.Rare.RuptureMind;
import Koishi.cards.Attacks.Rare.SilentAndRestless;
import Koishi.cards.Attacks.Rare.SuperEgo;
import Koishi.cards.Attacks.Common.BloodcurdlingScream;
import Koishi.cards.Attacks.Uncommon.Catatonia;
import Koishi.cards.Attacks.Rare.GrowingPain;
import Koishi.cards.Attacks.Uncommon.HeartAttack;
import Koishi.cards.Attacks.Uncommon.LastRemote;
import Koishi.cards.Attacks.Uncommon.MindNumbingTerror;
import Koishi.cards.Attacks.Common.ParalyzingFear;
import Koishi.cards.Attacks.Common.SpiritedAway;
import Koishi.cards.Attacks.Uncommon.StrangeCloudFist;
import Koishi.cards.Attacks.Uncommon.TraumaticStroke;
import Koishi.cards.Attacks.Uncommon.TremblingHands;
import Koishi.cards.Attacks.Uncommon.UnseenTerror;
import Koishi.cards.Attacks.Uncommon.YoukaiPolygraph;
import Koishi.cards.Powers.Rare.ConfinedInnocent;
import Koishi.cards.Powers.Rare.Ego;
import Koishi.cards.Powers.Rare.FormlessExistence;
import Koishi.cards.Powers.Rare.HeartStoppingHorror;
import Koishi.cards.Powers.Rare.Heartbroken;
import Koishi.cards.Powers.Uncommon.Untouchable;
import Koishi.cards.Powers.Uncommon.BramblyRoseGarden;
import Koishi.cards.Powers.Uncommon.FidgetySnatcher;
import Koishi.cards.Powers.Uncommon.FreudianInstinct;
import Koishi.cards.Powers.Rare.TerrifyingSpectre;
import Koishi.cards.Powers.Uncommon.UnconsciousUrges;
import Koishi.cards.Powers.Uncommon.VengefulSpirit;
import Koishi.cards.Skills.Common.BasicDefend;
import Koishi.cards.Skills.Common.EmbryosDream;
import Koishi.cards.Skills.Common.FleetingPhantom;
import Koishi.cards.Skills.Common.HeartfeltFancy;
import Koishi.cards.Skills.Common.JumpScare;
import Koishi.cards.Skills.Common.PhantomBarrier;
import Koishi.cards.Skills.Common.Provoke;
import Koishi.cards.Skills.Uncommon.SprinkleStarAndHeart;
import Koishi.cards.Skills.Common.UnansweredLove;
import Koishi.cards.Skills.Common.UnconsciousUprising;
import Koishi.cards.Skills.Uncommon.Whimsy;
import Koishi.cards.Skills.Rare.ConditionedTeleport;
import Koishi.cards.Skills.Uncommon.DNAsFlaw;
import Koishi.cards.Skills.Rare.FadingMemory;
import Koishi.cards.Skills.Rare.ImGoingToCallYouNow;
import Koishi.cards.Skills.Rare.MindStellarRelief;
import Koishi.cards.Skills.Rare.PerfectMindControl;
import Koishi.cards.Skills.Rare.RollingInRichesHeart;
import Koishi.cards.Skills.Rare.RoseHell;
import Koishi.cards.Skills.Uncommon.ApparitionsStalkTheNight;
import Koishi.cards.Skills.Uncommon.Bloodlust;
import Koishi.cards.Skills.Common.CatchAndRose;
import Koishi.cards.Skills.Uncommon.DanmakuParanoia;
import Koishi.cards.Skills.Uncommon.EmbersOfLove;
import Koishi.cards.Skills.Uncommon.FourthEye;
import Koishi.cards.Skills.Uncommon.GeneticsOfTheUnconscious;
import Koishi.cards.Skills.Uncommon.GhostParty;
import Koishi.cards.Skills.Uncommon.IdleWhim;
import Koishi.cards.Skills.Uncommon.MassHysteria;
import Koishi.cards.Skills.Uncommon.PhilosophyOfTheDespised;
import Koishi.cards.Skills.Uncommon.Possession;
import Koishi.cards.Skills.Uncommon.PredatoryInstincts;
import Koishi.cards.Skills.Uncommon.ReleaseOfTheId;
import Koishi.cards.Skills.Uncommon.RorschachInDanmaku;
import Koishi.cards.Skills.Uncommon.Vanish;
import Koishi.characters.KoishiCharacter;
import Koishi.relics.AHoleWhereASisterShouldBe;
import Koishi.relics.BloodstainedWhiteRose;
import Koishi.relics.ColorfulDays;
import Koishi.relics.EchoesOfADeadGirl;
import Koishi.relics.ImaginaryFriend;
import Koishi.relics.ImperfectMetamorphosis;
import Koishi.relics.LettersToReimu;
import Koishi.relics.TeethAndClaws;
import Koishi.util.IDCheckDontTouchPls;
import Koishi.util.TextureLoader;
import Koishi.variables.DefaultSecondMagicNumber;
import basemod.BaseMod;
import basemod.ModPanel;
import basemod.abstracts.CustomCard;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.OnStartBattleSubscriber;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.PostPowerApplySubscriber;
import basemod.interfaces.PreMonsterTurnSubscriber;
import basemod.interfaces.SetUnlocksSubscriber;
import basemod.interfaces.StartGameSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@SpireInitializer
public class KoishiMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber,
        OnStartBattleSubscriber,
        PostBattleSubscriber,
        StartGameSubscriber,
        SetUnlocksSubscriber,
        PostPowerApplySubscriber,
        PreMonsterTurnSubscriber {
    // Make sure to implement the subscribers *you* are using (read basemod wiki). Editing cards? EditCardsSubscriber.
    // Making relics? EditRelicsSubscriber. etc., etc., for a full list and how to make your own, visit the basemod wiki.
    public static final Logger logger = LogManager.getLogger(KoishiMod.class.getName());
    private static String modID;

    //This is for the in-game mod settings panel.
    private static final String MODNAME = "Koishi";
    private static final String AUTHOR = "Darkglade";
    private static final String DESCRIPTION = "My hat is my friend. It helps me relax.";

    public static int intangibleCount = 0;
    public static int debuffCount = 0;
    public static boolean appliedDebuffThisTurn = false;
    
    // =============== INPUT TEXTURE LOCATION =================
    
    // Colors (RGB)
    // Character Color
    public static final Color DARK_GREEN = CardHelper.getColor(2, 40, 0);
  
    // Card backgrounds - The actual rectangular card.
    private static final String ATTACK_GREEN = "KoishiResources/images/512/bg_attack_green.png";
    private static final String SKILL_GREEN = "KoishiResources/images/512/bg_skill_green.png";
    private static final String POWER_GREEN = "KoishiResources/images/512/bg_power_green.png";
    
    private static final String ENERGY_ORB_DEFAULT_GRAY = "KoishiResources/images/512/card_default_gray_orb.png";
    private static final String CARD_ENERGY_ORB = "KoishiResources/images/512/card_small_orb.png";
    
    private static final String ATTACK_GREEN_PORTRAIT = "KoishiResources/images/1024/bg_attack_green.png";
    private static final String SKILL_GREEN_PORTRAIT = "KoishiResources/images/1024/bg_skill_green.png";
    private static final String POWER_GREEN_PORTRAIT = "KoishiResources/images/1024/bg_power_green.png";
    private static final String ENERGY_ORB_DEFAULT_GRAY_PORTRAIT = "KoishiResources/images/1024/card_orb.png";
    
    // Character assets
    private static final String THE_DEFAULT_BUTTON = "KoishiResources/images/charSelect/KoishiButton.png";
    private static final String THE_DEFAULT_PORTRAIT = "KoishiResources/images/charSelect/KoishiCharacterPortrait.png";
    public static final String THE_DEFAULT_SHOULDER_1 = "KoishiResources/images/char/defaultCharacter/shoulder.png";
    public static final String THE_DEFAULT_SHOULDER_2 = "KoishiResources/images/char/defaultCharacter/shoulder2.png";
    public static final String THE_DEFAULT_CORPSE = "KoishiResources/images/char/defaultCharacter/corpse.png";
    
    //Mod Badge - A small icon that appears in the mod settings menu next to your mod.
    public static final String BADGE_IMAGE = "KoishiResources/images/Badge.png";
    
    // =============== MAKE IMAGE PATHS =================
    
    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }
    
    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }
    
    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }
    
    public static String makeEffectPath(String resourcePath) {
        return getModID() + "Resources/images/effects/" + resourcePath;
    }
    
    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }
    
    // =============== /MAKE IMAGE PATHS/ =================
    
    // =============== /INPUT TEXTURE LOCATION/ =================
    
    
    // =============== SUBSCRIBE, CREATE THE COLOR_DARK_GREEN, INITIALIZE =================
    
    public KoishiMod() {
        logger.info("Subscribe to BaseMod hooks");
        
        BaseMod.subscribe(this);
      
        setModID("Koishi");
        
        logger.info("Done subscribing");
        
        logger.info("Creating the color " + KoishiCharacter.Enums.COLOR_DARK_GREEN.toString());
        
        BaseMod.addColor(KoishiCharacter.Enums.COLOR_DARK_GREEN, DARK_GREEN, DARK_GREEN, DARK_GREEN,
                DARK_GREEN, DARK_GREEN, DARK_GREEN, DARK_GREEN,
                ATTACK_GREEN, SKILL_GREEN, POWER_GREEN, ENERGY_ORB_DEFAULT_GRAY,
                ATTACK_GREEN_PORTRAIT, SKILL_GREEN_PORTRAIT, POWER_GREEN_PORTRAIT,
                ENERGY_ORB_DEFAULT_GRAY_PORTRAIT, CARD_ENERGY_ORB);
        
        logger.info("Done creating the color");
    }
    
    // ====== NO EDIT AREA ======
    // DON'T TOUCH THIS STUFF. IT IS HERE FOR STANDARDIZATION BETWEEN MODS AND TO ENSURE GOOD CODE PRACTICES.
    // IF YOU MODIFY THIS I WILL HUNT YOU DOWN AND DOWNVOTE YOUR MOD ON WORKSHOP
    
    public static void setModID(String ID) { // DON'T EDIT
        Gson coolG = new Gson(); // EY DON'T EDIT THIS
        //   String IDjson = Gdx.files.internal("IDCheckStringsDONT-EDIT-AT-ALL.json").readString(String.valueOf(StandardCharsets.UTF_8)); // i hate u Gdx.files
        InputStream in = KoishiMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json"); // DON'T EDIT THIS ETHER
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class); // OR THIS, DON'T EDIT IT
        logger.info("You are attempting to set your mod ID as: " + ID); // NO WHY
        if (ID.equals(EXCEPTION_STRINGS.DEFAULTID)) { // DO *NOT* CHANGE THIS ESPECIALLY, TO EDIT YOUR MOD ID, SCROLL UP JUST A LITTLE, IT'S JUST ABOVE
            throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION); // THIS ALSO DON'T EDIT
        } else if (ID.equals(EXCEPTION_STRINGS.DEVID)) { // NO
            modID = EXCEPTION_STRINGS.DEFAULTID; // DON'T
        } else { // NO EDIT AREA
            modID = ID; // DON'T WRITE OR CHANGE THINGS HERE NOT EVEN A LITTLE
        } // NO
        logger.info("Success! ID is " + modID); // WHY WOULD U WANT IT NOT TO LOG?? DON'T EDIT THIS.
    } // NO
    
    public static String getModID() { // NO
        return modID; // DOUBLE NO
    } // NU-UH
    
    private static void pathCheck() { // ALSO NO
        Gson coolG = new Gson(); // NNOPE DON'T EDIT THIS
        //   String IDjson = Gdx.files.internal("IDCheckStringsDONT-EDIT-AT-ALL.json").readString(String.valueOf(StandardCharsets.UTF_8)); // i still hate u btw Gdx.files
        InputStream in = KoishiMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json"); // DON'T EDIT THISSSSS
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class); // NAH, NO EDIT
        String packageName = KoishiMod.class.getPackage().getName(); // STILL NO EDIT ZONE
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources"); // PLEASE DON'T EDIT THINGS HERE, THANKS
        if (!modID.equals(EXCEPTION_STRINGS.DEVID)) { // LEAVE THIS EDIT-LESS
            if (!packageName.equals(getModID())) { // NOT HERE ETHER
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID()); // THIS IS A NO-NO
            } // WHY WOULD U EDIT THIS
            if (!resourcePathExists.exists()) { // DON'T CHANGE THIS
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources"); // NOT THIS
            }// NO
        }// NO
    }// NO
    
    // ====== YOU CAN EDIT AGAIN ======
    
    
    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("========================= Initializing Default Mod. Hi. =========================");
        KoishiMod defaultmod = new KoishiMod();
        logger.info("========================= /Default Mod Initialized. Hello World./ =========================");
    }
    
    // ============== /SUBSCRIBE, CREATE THE COLOR_DARK_GREEN, INITIALIZE/ =================
    
    
    // =============== LOAD THE CHARACTER =================
    
    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. " + "Add " + KoishiCharacter.Enums.KOISHI.toString());
        
        BaseMod.addCharacter(new KoishiCharacter("the Default", KoishiCharacter.Enums.KOISHI),
                THE_DEFAULT_BUTTON, THE_DEFAULT_PORTRAIT, KoishiCharacter.Enums.KOISHI);

        logger.info("Added " + KoishiCharacter.Enums.KOISHI.toString());
    }
    
    // =============== /LOAD THE CHARACTER/ =================
    
    
    // =============== POST-INITIALIZE =================
    
    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options");
        
        // Load the Mod Badge
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);
        
        // Create the Mod Menu
        ModPanel settingsPanel = new ModPanel();
        
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        logger.info("Done loading badge Image and mod options");
    }
    
    // =============== / POST-INITIALIZE/ =================
    
    

    
    
    // ================ ADD RELICS ===================
    
    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");
        
        // This adds a character specific relic. Only when you play with the mentioned color, will you get this relic.
        BaseMod.addRelicToCustomPool(new ImaginaryFriend(), KoishiCharacter.Enums.COLOR_DARK_GREEN);
        BaseMod.addRelicToCustomPool(new BloodstainedWhiteRose(), KoishiCharacter.Enums.COLOR_DARK_GREEN);
        BaseMod.addRelicToCustomPool(new AHoleWhereASisterShouldBe(), KoishiCharacter.Enums.COLOR_DARK_GREEN);
        BaseMod.addRelicToCustomPool(new LettersToReimu(), KoishiCharacter.Enums.COLOR_DARK_GREEN);
        BaseMod.addRelicToCustomPool(new TeethAndClaws(), KoishiCharacter.Enums.COLOR_DARK_GREEN);
        BaseMod.addRelicToCustomPool(new ImperfectMetamorphosis(), KoishiCharacter.Enums.COLOR_DARK_GREEN);
        BaseMod.addRelicToCustomPool(new EchoesOfADeadGirl(), KoishiCharacter.Enums.COLOR_DARK_GREEN);
        BaseMod.addRelicToCustomPool(new ColorfulDays(), KoishiCharacter.Enums.COLOR_DARK_GREEN);

        logger.info("Done adding relics!");
    }
    
    // ================ /ADD RELICS/ ===================
    
    
    // ================ ADD CARDS ===================
    
    @Override
    public void receiveEditCards() {
        logger.info("Adding variables");
        //Ignore this
        pathCheck();
        // Add the Custom Dynamic Variables
        logger.info("Add variabls");
        // Add the Custom Dynamic variabls
        BaseMod.addDynamicVariable(new DefaultSecondMagicNumber());
        
        logger.info("Adding cards");
        // Add the cards
        // Don't comment out/delete these cards (yet). You need 1 of each type and rarity (technically) for your game not to crash
        // when generating card rewards/shop screen items.

        //Attacks
        //Rares
        BaseMod.addCard(new MentalBreakdown());
        BaseMod.addCard(new SilentAndRestless());
        BaseMod.addCard(new SuperEgo());
        BaseMod.addCard(new RuptureMind());
        //Uncommons
        BaseMod.addCard(new LastRemote());
        BaseMod.addCard(new HeartAttack());
        BaseMod.addCard(new StrangeCloudFist());
        BaseMod.addCard(new TraumaticStroke());
        BaseMod.addCard(new MindNumbingTerror());
        BaseMod.addCard(new UnseenTerror());
        BaseMod.addCard(new Catatonia());
        BaseMod.addCard(new YoukaiPolygraph());
        BaseMod.addCard(new TremblingHands());
        BaseMod.addCard(new ParalyzingFear());
        BaseMod.addCard(new BloodcurdlingScream());
        BaseMod.addCard(new SpiritedAway());
        BaseMod.addCard(new GrowingPain());
        //Commons
        BaseMod.addCard(new Prune());
        BaseMod.addCard(new Jaunt());
        BaseMod.addCard(new RacingHeart());
        BaseMod.addCard(new SubterraneanRose());
        BaseMod.addCard(new StingingMind());
        BaseMod.addCard(new DreadfulBlow());
        BaseMod.addCard(new ReflexRadar());
        BaseMod.addCard(new PhantomStrike());
        BaseMod.addCard(new SubconsciousSweep());
        BaseMod.addCard(new HauntingSlash());
        BaseMod.addCard(new BasicStrike());

        //Skills
        //Rares
        BaseMod.addCard(new ConditionedTeleport());
        BaseMod.addCard(new RollingInRichesHeart());
        BaseMod.addCard(new FadingMemory());
        BaseMod.addCard(new RoseHell());
        BaseMod.addCard(new MindStellarRelief());
        BaseMod.addCard(new DNAsFlaw());
        BaseMod.addCard(new ImGoingToCallYouNow());
        BaseMod.addCard(new PerfectMindControl());
        //Uncommons
        BaseMod.addCard(new Possession());
        BaseMod.addCard(new ApparitionsStalkTheNight());
        BaseMod.addCard(new Vanish());
        BaseMod.addCard(new GhostParty());
        BaseMod.addCard(new DanmakuParanoia());
        BaseMod.addCard(new PhilosophyOfTheDespised());
        BaseMod.addCard(new EmbersOfLove());
        BaseMod.addCard(new MassHysteria());
        BaseMod.addCard(new FourthEye());
        BaseMod.addCard(new ReleaseOfTheId());
        BaseMod.addCard(new PredatoryInstincts());
        BaseMod.addCard(new IdleWhim());
        BaseMod.addCard(new Bloodlust());
        BaseMod.addCard(new GeneticsOfTheUnconscious());
        BaseMod.addCard(new RorschachInDanmaku());
        BaseMod.addCard(new CatchAndRose());
        //Commons
        BaseMod.addCard(new SprinkleStarAndHeart());
        BaseMod.addCard(new EmbryosDream());
        BaseMod.addCard(new JumpScare());
        BaseMod.addCard(new HeartfeltFancy());
        BaseMod.addCard(new UnansweredLove());
        BaseMod.addCard(new FleetingPhantom());
        BaseMod.addCard(new PhantomBarrier());
        BaseMod.addCard(new Whimsy());
        BaseMod.addCard(new UnconsciousUprising());
        BaseMod.addCard(new Provoke());
        BaseMod.addCard(new BasicDefend());

        //Powers
        //Rares
        BaseMod.addCard(new Untouchable());
        BaseMod.addCard(new HeartStoppingHorror());
        BaseMod.addCard(new Heartbroken());
        BaseMod.addCard(new Ego());
        BaseMod.addCard(new ConfinedInnocent());
        BaseMod.addCard(new FormlessExistence());
        //Uncommons
        BaseMod.addCard(new UnconsciousUrges());
        BaseMod.addCard(new FreudianInstinct());
        BaseMod.addCard(new BramblyRoseGarden());
        BaseMod.addCard(new TerrifyingSpectre());
        BaseMod.addCard(new VengefulSpirit());
        BaseMod.addCard(new FidgetySnatcher());
        
        logger.info("Making sure the cards are unlocked.");
        // Unlock the cards
        // This is so that they are all "seen" in the library, for people who like to look at the card list
        // before playing your mod.

//        UnlockTracker.unlockCard(MentalBreakdown.ID);
//        UnlockTracker.unlockCard(SilentAndRestless.ID);
//        UnlockTracker.unlockCard(SuperEgo.ID);
//        UnlockTracker.unlockCard(RuptureMind.ID);
//
//        UnlockTracker.unlockCard(LastRemote.ID);
//        UnlockTracker.unlockCard(HeartAttack.ID);
//        UnlockTracker.unlockCard(StrangeCloudFist.ID);
//        UnlockTracker.unlockCard(TraumaticStroke.ID);
//        UnlockTracker.unlockCard(MindNumbingTerror.ID);
//        UnlockTracker.unlockCard(UnseenTerror.ID);
//        UnlockTracker.unlockCard(Catatonia.ID);
//        UnlockTracker.unlockCard(YoukaiPolygraph.ID);
//        UnlockTracker.unlockCard(TremblingHands.ID);
//        UnlockTracker.unlockCard(ParalyzingFear.ID);
//        UnlockTracker.unlockCard(BloodcurdlingScream.ID);
//        UnlockTracker.unlockCard(SpiritedAway.ID);
//        UnlockTracker.unlockCard(GrowingPain.ID);
//
//        UnlockTracker.unlockCard(Prune.ID);
//        UnlockTracker.unlockCard(Jaunt.ID);
//        UnlockTracker.unlockCard(RacingHeart.ID);
//        UnlockTracker.unlockCard(SubterraneanRose.ID);
//        UnlockTracker.unlockCard(StingingMind.ID);
//        UnlockTracker.unlockCard(DreadfulBlow.ID);
//        UnlockTracker.unlockCard(ReflexRadar.ID);
//        UnlockTracker.unlockCard(PhantomStrike.ID);
//        UnlockTracker.unlockCard(SubconsciousSweep.ID);
//        UnlockTracker.unlockCard(HauntingSlash.ID);
//        UnlockTracker.unlockCard(BasicStrike.ID);
//
//        UnlockTracker.unlockCard(ConditionedTeleport.ID);
//        UnlockTracker.unlockCard(RollingInRichesHeart.ID);
//        UnlockTracker.unlockCard(FadingMemory.ID);
//        UnlockTracker.unlockCard(RoseHell.ID);
//        UnlockTracker.unlockCard(MindStellarRelief.ID);
//        UnlockTracker.unlockCard(DNAsFlaw.ID);
//        UnlockTracker.unlockCard(ImGoingToCallYouNow.ID);
//        UnlockTracker.unlockCard(PerfectMindControl.ID);
//
//        UnlockTracker.unlockCard(Possession.ID);
//        UnlockTracker.unlockCard(ApparitionsStalkTheNight.ID);
//        UnlockTracker.unlockCard(Vanish.ID);
//        UnlockTracker.unlockCard(GhostParty.ID);
//        UnlockTracker.unlockCard(DanmakuParanoia.ID);
//        UnlockTracker.unlockCard(PhilosophyOfTheDespised.ID);
//        UnlockTracker.unlockCard(EmbersOfLove.ID);
//        UnlockTracker.unlockCard(MassHysteria.ID);
//        UnlockTracker.unlockCard(FourthEye.ID);
//        UnlockTracker.unlockCard(ReleaseOfTheId.ID);
//        UnlockTracker.unlockCard(PredatoryInstincts.ID);
//        UnlockTracker.unlockCard(IdleWhim.ID);
//        UnlockTracker.unlockCard(Bloodlust.ID);
//        UnlockTracker.unlockCard(GeneticsOfTheUnconscious.ID);
//        UnlockTracker.unlockCard(RorschachInDanmaku.ID);
//        UnlockTracker.unlockCard(CatchAndRose.ID);
//
//        UnlockTracker.unlockCard(SprinkleStarAndHeart.ID);
//        UnlockTracker.unlockCard(EmbryosDream.ID);
//        UnlockTracker.unlockCard(JumpScare.ID);
//        UnlockTracker.unlockCard(HeartfeltFancy.ID);
//        UnlockTracker.unlockCard(UnansweredLove.ID);
//        UnlockTracker.unlockCard(FleetingPhantom.ID);
//        UnlockTracker.unlockCard(PhantomBarrier.ID);
//        UnlockTracker.unlockCard(Whimsy.ID);
//        UnlockTracker.unlockCard(UnconsciousUprising.ID);
//        UnlockTracker.unlockCard(Provoke.ID);
//        UnlockTracker.unlockCard(BasicDefend.ID);
//
//        UnlockTracker.unlockCard(Untouchable.ID);
//        UnlockTracker.unlockCard(HeartStoppingHorror.ID);
//        UnlockTracker.unlockCard(Heartbroken.ID);
//        UnlockTracker.unlockCard(Ego.ID);
//        UnlockTracker.unlockCard(ConfinedInnocent.ID);
//        UnlockTracker.unlockCard(FormlessExistence.ID);
//
//        UnlockTracker.unlockCard(UnconsciousUrges.ID);
//        UnlockTracker.unlockCard(FreudianInstinct.ID);
//        UnlockTracker.unlockCard(BramblyRoseGarden.ID);
//        UnlockTracker.unlockCard(TerrifyingSpectre.ID);
//        UnlockTracker.unlockCard(VengefulSpirit.ID);
//        UnlockTracker.unlockCard(FidgetySnatcher.ID);
        
        logger.info("Done adding cards!");
    }
    
    // There are better ways to do this than listing every single individual card, but I do not want to complicate things
    // in a "tutorial" mod. This will do and it's completely ok to use. If you ever want to clean up and
    // shorten all the imports, go look take a look at other mods, such as Hubris.
    
    // ================ /ADD CARDS/ ===================
    


    private static String makeLocPath(Settings.GameLanguage language, String filename)
    {
        String ret = "localization/";
        switch (language) {
            case RUS:
                ret += "rus/";
                break;
            case ZHS:
                ret += "zhs/";
                break;
            case KOR:
                ret += "kor/";
                break;
            default:
                ret += "eng/";
                break;
        }
        return getModID() + "Resources/" + (ret + filename + ".json");
    }

    private void loadLocFiles(Settings.GameLanguage language)
    {
        BaseMod.loadCustomStringsFile(CardStrings.class, makeLocPath(language, "KoishiMod-Card-Strings"));
        BaseMod.loadCustomStringsFile(RelicStrings.class, makeLocPath(language, "KoishiMod-Relic-Strings"));
        BaseMod.loadCustomStringsFile(PowerStrings.class, makeLocPath(language, "KoishiMod-Power-Strings"));
        BaseMod.loadCustomStringsFile(CharacterStrings.class, makeLocPath(language, "KoishiMod-Character-Strings"));
    }

    @Override
    public void receiveEditStrings()
    {
        loadLocFiles(Settings.GameLanguage.ENG);
        if (Settings.language != Settings.GameLanguage.ENG) {
            loadLocFiles(Settings.language);
        }
    }

    private void loadLocKeywords(Settings.GameLanguage language)
    {
        Gson gson = new Gson();
        String json = Gdx.files.internal(makeLocPath(language, "KoishiMod-Keyword-Strings")).readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receiveEditKeywords()
    {
        loadLocKeywords(Settings.GameLanguage.ENG);
        if (Settings.language != Settings.GameLanguage.ENG) {
            loadLocKeywords(Settings.language);
        }
    }


    // this adds "ModName:" before the ID of any card/relic/power etc.
    // in order to avoid conflicts if any other mod uses the same ID.
    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    //used to set background of Terror cards
    public static void setBackground(CustomCard card, int type) {
        switch (type) {
            case 0:
                card.setBackgroundTexture("KoishiResources/images/512/bg_attack_black.png", "KoishiResources/images/1024/bg_attack_black.png");
                break;
            case 1:
                card.setBackgroundTexture("KoishiResources/images/512/bg_skill_black.png", "KoishiResources/images/1024/bg_skill_black.png");
                break;
            case 2:
                card.setBackgroundTexture("KoishiResources/images/512/bg_power_black.png", "KoishiResources/images/1024/bg_power_black.png");
                break;
        }
    }

    @Override
    public void receivePostBattle(AbstractRoom room) {
        runAnimation("winB");
        intangibleCount = 0;
        debuffCount = 0;
        appliedDebuffThisTurn = false;
        AbstractIdCard.drewIdCardThisTurn = false;
        AbstractIdCard.idCardsDrawn = 0;
        AbstractIdCard.idEnabled = true;
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom var1) {
        intangibleCount = 0;
        debuffCount = 0;
        appliedDebuffThisTurn = false;
        AbstractIdCard.drewIdCardThisTurn = false;
        AbstractIdCard.idCardsDrawn = 0;
        AbstractIdCard.idEnabled = true;
    }

    @Override
    public void receiveStartGame() {
        intangibleCount = 0;
        debuffCount = 0;
        appliedDebuffThisTurn = false;
        AbstractIdCard.drewIdCardThisTurn = false;
        AbstractIdCard.idCardsDrawn = 0;
        AbstractIdCard.idEnabled = true;
    }

    @Override
    public void receiveSetUnlocks() {
        intangibleCount = 0;
        debuffCount = 0;
        appliedDebuffThisTurn = false;
        AbstractIdCard.drewIdCardThisTurn = false;
        AbstractIdCard.idCardsDrawn = 0;
        AbstractIdCard.idEnabled = true;
    }

    @Override
    public void receivePostPowerApplySubscriber(AbstractPower p, AbstractCreature target, AbstractCreature source) {
        if (target == AbstractDungeon.player) {
            if (p.ID.equals(IntangiblePlayerPower.POWER_ID) || p.ID.equals(IntangiblePower.POWER_ID)) {
                intangibleCount += p.amount;
            }
        }
        if (source == AbstractDungeon.player && target != AbstractDungeon.player && !target.hasPower(ArtifactPower.POWER_ID)) {
            if (p.type == AbstractPower.PowerType.DEBUFF) {
                appliedDebuffThisTurn = true;
                debuffCount++;
            }
        }
    }

    @Override
    public boolean receivePreMonsterTurn(AbstractMonster m) {
        appliedDebuffThisTurn = false;
        AbstractIdCard.drewIdCardThisTurn = false;
        return true;
    }

    //Checks to make sure player is playing this character before running animations
    public static void runAnimation(String anim) {
        if (AbstractDungeon.player instanceof KoishiCharacter) {
            ((KoishiCharacter)AbstractDungeon.player).runAnim(anim);
        }
    }
}
