package Koishi.cards.Skills.Rare;

import Koishi.KoishiMod;
import Koishi.actions.FadingMemoryAction;
import Koishi.cards.AbstractIdCard;
import Koishi.characters.KoishiCharacter;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Koishi.KoishiMod.makeCardPath;

public class FadingMemory extends AbstractIdCard {

    public static final String ID = KoishiMod.makeID(FadingMemory.class.getSimpleName());
    public static final String IMG = makeCardPath("FadingMemory.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 0;

    private static final int EXHAUST = 1;

    private static final int DRAW = 2;
    private static final int UPGRADE_PLUS_DRAW = 1;

    public FadingMemory() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = EXHAUST;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = DRAW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("spellCall");
        AbstractDungeon.actionManager.addToBottom(new FadingMemoryAction(this));
    }

    @Override
    public float getTitleFontSize()
    {
        return 19;
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_DRAW);
            initializeDescription();
        }
    }
}
