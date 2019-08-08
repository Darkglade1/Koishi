package Koishi.cards.Attacks.Rare;

import Koishi.cards.AbstractDefaultCard;
import Koishi.cards.AbstractIdCard;
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

public class SuperEgo extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(SuperEgo.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("SuperEgo.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 2;

    private static final int DAMAGE = 20;
    private static final int UPGRADE_PLUS_DMG = 5;

    private static final int EFFECT = 1;

    public SuperEgo() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = EFFECT;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        ArrayList<AbstractIdCard> cardsToRemove = new ArrayList<>();

        for (AbstractCard card : p.hand.group) {
            if (card instanceof AbstractIdCard) {
                cardsToRemove.add((AbstractIdCard)card);
            }
        }
        for (AbstractIdCard card : cardsToRemove) {
            p.hand.moveToExhaustPile(card);
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(magicNumber));
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));
        }
        cardsToRemove.clear();

        for (AbstractCard card : p.drawPile.group) {
            if (card instanceof AbstractIdCard) {
                cardsToRemove.add((AbstractIdCard)card);
            }
        }
        for (AbstractIdCard card : cardsToRemove) {
            p.drawPile.moveToExhaustPile(card);
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(magicNumber));
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));
        }
        cardsToRemove.clear();

        for (AbstractCard card : p.discardPile.group) {
            if (card instanceof AbstractIdCard) {
                cardsToRemove.add((AbstractIdCard)card);
            }
        }
        for (AbstractIdCard card : cardsToRemove) {
            p.discardPile.moveToExhaustPile(card);
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(magicNumber));
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));
        }
        cardsToRemove.clear();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
