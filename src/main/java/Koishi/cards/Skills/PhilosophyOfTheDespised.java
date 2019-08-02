package Koishi.cards.Skills;

import Koishi.KoishiMod;
import Koishi.actions.ForceIntentAction;
import Koishi.cards.AbstractIntentChangingCard;
import Koishi.characters.KoishiCharacter;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import java.util.Iterator;

import static Koishi.KoishiMod.makeCardPath;
import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class PhilosophyOfTheDespised extends AbstractIntentChangingCard {

    public static final String ID = KoishiMod.makeID(PhilosophyOfTheDespised.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("PhilosophyOfTheDespised.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 0;

    private static final int DEBUFF = 3;
    private static final int UPGRADE_PLUS_DEBUFF = 1;

    public PhilosophyOfTheDespised() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, IntentTypes.ATTACK);
        magicNumber = baseMagicNumber = DEBUFF;
        retain = true;
        exhaust = true;
    }

    @Override
    public void atTurnStart() {
        retain = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster mo) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new VulnerablePower(mo, magicNumber, false), magicNumber));
        Iterator iterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while (iterator.hasNext()) {
            AbstractMonster m = (AbstractMonster)iterator.next();
            AbstractDungeon.actionManager.addToTop(new ForceIntentAction(p, m, this.intentType));
            if (m.hasPower("Sleep"))
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, p, "Sleep"));
            if (m.hasPower("Stun"))
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, p, "Stun"));
        }

    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_DEBUFF);
            exhaust = false;
            rawDescription = languagePack.getCardStrings(cardID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
