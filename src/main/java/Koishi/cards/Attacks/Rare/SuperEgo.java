package Koishi.cards.Attacks.Rare;

import Koishi.cards.AbstractDefaultCard;
import Koishi.cards.AbstractIdCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Koishi.KoishiMod;
import Koishi.characters.KoishiCharacter;

import java.util.ArrayList;

import static Koishi.KoishiMod.makeCardPath;
import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class SuperEgo extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(SuperEgo.class.getSimpleName());
    public static final String IMG = makeCardPath("SuperEgo.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 0;

    private static final int DAMAGE = 8;

    private static final int EFFECT = 1;

    public SuperEgo() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = EFFECT;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("dashAttackB");
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        ArrayList<AbstractIdCard> cardsToRemove = new ArrayList<>();
        int exhaustCounter = 0;
        for (AbstractCard card : p.hand.group) {
            if (card instanceof AbstractIdCard) {
                cardsToRemove.add((AbstractIdCard)card);
            }
        }
        for (AbstractIdCard card : cardsToRemove) {
            p.hand.moveToExhaustPile(card);
            exhaustCounter++;
        }
        cardsToRemove.clear();

        for (AbstractCard card : p.drawPile.group) {
            if (card instanceof AbstractIdCard) {
                cardsToRemove.add((AbstractIdCard)card);
            }
        }
        for (AbstractIdCard card : cardsToRemove) {
            p.drawPile.moveToExhaustPile(card);
            exhaustCounter++;
        }
        cardsToRemove.clear();

        for (AbstractCard card : p.discardPile.group) {
            if (card instanceof AbstractIdCard) {
                cardsToRemove.add((AbstractIdCard)card);
            }
        }
        for (AbstractIdCard card : cardsToRemove) {
            p.discardPile.moveToExhaustPile(card);
            exhaustCounter++;
        }
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(magicNumber * exhaustCounter));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber * exhaustCounter));
        cardsToRemove.clear();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            AlwaysRetainField.alwaysRetain.set(this, true);
            rawDescription = languagePack.getCardStrings(cardID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
