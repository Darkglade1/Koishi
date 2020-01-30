package Koishi.cards.Skills.Uncommon;

import Koishi.KoishiMod;
import Koishi.cards.AbstractDefaultCard;
import Koishi.characters.KoishiCharacter;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Koishi.KoishiMod.makeCardPath;
import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class Whimsy extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(Whimsy.class.getSimpleName());
    public static final String IMG = makeCardPath("Whimsy.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 0;

    private static final int DRAW = 1;
    private static final int UPGRADE_DRAW = 1;
    private static final int SCRY = 2;
    private static final int UPGRADE_SCRY = 1;

    public Whimsy() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DRAW;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = SCRY;
        exhaust = true;
        AlwaysRetainField.alwaysRetain.set(this, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("spellCall");
        AbstractDungeon.actionManager.addToBottom(new ScryAction(defaultSecondMagicNumber));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_DRAW);
            upgradeDefaultSecondMagicNumber(UPGRADE_SCRY);
            rawDescription = languagePack.getCardStrings(cardID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
