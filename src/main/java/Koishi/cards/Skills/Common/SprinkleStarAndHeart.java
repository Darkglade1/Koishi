package Koishi.cards.Skills.Common;

import Koishi.KoishiMod;
import Koishi.actions.SprinkleStarAndHeartAction;
import Koishi.cards.AbstractIdCard;
import Koishi.characters.KoishiCharacter;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Koishi.KoishiMod.makeCardPath;

public class SprinkleStarAndHeart extends AbstractIdCard {

    public static final String ID = KoishiMod.makeID(SprinkleStarAndHeart.class.getSimpleName());
    public static final String IMG = makeCardPath("SprinkleStarAndHeart.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 1;

    private static final int DEBUFF = 2;
    private static final int UPGRADE_PLUS_DEBUFF = 1;

    public SprinkleStarAndHeart() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DEBUFF;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("sprinkleStar");
        AbstractDungeon.actionManager.addToBottom(new SprinkleStarAndHeartAction(this));
    }

    @Override
    public float getTitleFontSize()
    {
        return 16;
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_DEBUFF);
            initializeDescription();
        }
    }
}
