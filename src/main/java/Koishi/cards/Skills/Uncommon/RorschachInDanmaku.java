package Koishi.cards.Skills.Uncommon;

import Koishi.KoishiMod;
import Koishi.actions.ForceIntentAction;
import Koishi.cards.AbstractIdCard;
import Koishi.characters.KoishiCharacter;
import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

import static Koishi.KoishiMod.makeCardPath;
import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class RorschachInDanmaku extends AbstractIdCard {

    public static final String ID = KoishiMod.makeID(RorschachInDanmaku.class.getSimpleName());
    public static final String IMG = makeCardPath("RorschachInDanmaku.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 2;

    private static final int BONUS_BLOCK = 5;

    public RorschachInDanmaku() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = BONUS_BLOCK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("spellB");
        int totalBlock = 0;
        Iterator iterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(iterator.hasNext()) {
            AbstractMonster mo = (AbstractMonster)iterator.next();
            if (!mo.isDeadOrEscaped()) {
                if (ForceIntentAction.attackTest.test(mo)) {
                    int moDamage = (Integer) ReflectionHacks.getPrivate(mo, AbstractMonster.class, "intentDmg");
                    if ((Boolean) ReflectionHacks.getPrivate(mo, AbstractMonster.class, "isMultiDmg")) {
                        moDamage *= (Integer) ReflectionHacks.getPrivate(mo, AbstractMonster.class, "intentMultiAmt");
                    }
                    totalBlock += moDamage;
                }
            }
        }
        if (upgraded) {
            totalBlock += magicNumber;
        }
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, totalBlock));
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
