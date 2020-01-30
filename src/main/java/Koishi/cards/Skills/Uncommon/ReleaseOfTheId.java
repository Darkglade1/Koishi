package Koishi.cards.Skills.Uncommon;

import Koishi.KoishiMod;
import Koishi.cards.AbstractDefaultCard;
import Koishi.cards.AbstractIdCard;
import Koishi.cards.Attacks.Common.SubconsciousSweep;
import Koishi.cards.Attacks.Uncommon.HeartAttack;
import Koishi.characters.KoishiCharacter;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Collections;

import static Koishi.KoishiMod.makeCardPath;
import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class ReleaseOfTheId extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(ReleaseOfTheId.class.getSimpleName());
    public static final String IMG = makeCardPath("ReleaseOfTheId.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 0;

    private static final int EFFECT = 3;

    public ReleaseOfTheId() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = EFFECT;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("spellCall");
        ArrayList<AbstractIdCard> cards = new ArrayList<>();
        for (int i = 0; i < magicNumber; i++) {
            AbstractIdCard card = AbstractIdCard.returnTrulyRandomIdCard();
            if (upgraded) {
                card.upgrade();
            }
            cards.add(card);
        }
        if (!hasDamageIdCard(cards)) {
            //Always give the player a damage Id card to avoid softlock scenarios
            ArrayList<AbstractIdCard> damageIdCards = new ArrayList<>();
            damageIdCards.add(new SubconsciousSweep());
            damageIdCards.add(new MassHysteria());
            damageIdCards.add(new HeartAttack());
            Collections.shuffle(damageIdCards, AbstractDungeon.cardRandomRng.random);
            cards.set(0, damageIdCards.get(0));
        }
        for (AbstractCard card : cards) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(card, 1, true, true));
        }
    }

    private boolean hasDamageIdCard(ArrayList<AbstractIdCard> cards) {
        for (AbstractIdCard card : cards) {
            if (card instanceof SubconsciousSweep ||
                card instanceof MassHysteria ||
                card instanceof HeartAttack) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = languagePack.getCardStrings(cardID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
