package Koishi.cards.Skills;

import Koishi.KoishiMod;
import Koishi.actions.ForceIntentAction;
import Koishi.cards.AbstractIntentChangingCard;
import Koishi.characters.KoishiCharacter;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

import static Koishi.KoishiMod.makeCardPath;

public class EmbersOfLove extends AbstractIntentChangingCard {

    public static final String ID = KoishiMod.makeID(EmbersOfLove.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("PhilosophyOfTheDespised.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    public EmbersOfLove() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, IntentTypes.NOT_ATTACK);
        retain = true;
        exhaust = true;
    }

    @Override
    public void atTurnStart() {
        retain = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster mo) {
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
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
