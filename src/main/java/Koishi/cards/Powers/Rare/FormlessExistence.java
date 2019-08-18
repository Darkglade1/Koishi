package Koishi.cards.Powers.Rare;

import Koishi.KoishiMod;
import Koishi.cards.AbstractDefaultCard;
import Koishi.characters.KoishiCharacter;
import Koishi.powers.FormlessExistencePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Koishi.KoishiMod.makeCardPath;

public class FormlessExistence extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(FormlessExistence.class.getSimpleName());
    public static final String IMG = makeCardPath("FormlessExistence.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 3;
    private static final int UPGRADED_COST = 2;

    private static final int EFFECT = 1;

    public FormlessExistence() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = EFFECT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("spellB");
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FormlessExistencePower(p, magicNumber), magicNumber));
    }

    @Override
    public float getTitleFontSize()
    {
        return 20;
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
