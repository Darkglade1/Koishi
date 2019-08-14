package Koishi.cards.Skills.Rare;

import Koishi.KoishiMod;
import Koishi.cards.AbstractDefaultCard;
import Koishi.characters.KoishiCharacter;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Koishi.KoishiMod.makeCardPath;

public class RollingInRichesHeart extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(RollingInRichesHeart.class.getSimpleName());
    public static final String IMG = makeCardPath("RollingInRichesHeart.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 1;

    private static final int GOLD_LOSS = 5;

    private static final int DAMAGE_BONUS = 3;
    private static final int UPGRADE_PLUS_DMG_BONUS = 2;

    public RollingInRichesHeart() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DAMAGE_BONUS;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = GOLD_LOSS;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.player.loseGold(defaultSecondMagicNumber);
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == AbstractCard.CardType.ATTACK) {
                c.baseDamage += magicNumber;
                c.applyPowers();
                c.flash();
            }
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_DMG_BONUS);
            initializeDescription();
        }
    }
}
