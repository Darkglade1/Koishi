package Koishi.cards.Skills.Rare;

import Koishi.KoishiMod;
import Koishi.cards.AbstractDefaultCard;
import Koishi.characters.KoishiCharacter;
import Koishi.powers.TerrorPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Koishi.KoishiMod.makeCardPath;
import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class ImGoingToCallYouNow extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(ImGoingToCallYouNow.class.getSimpleName());
    public static final String IMG = makeCardPath("ImGoingToCallYouNow.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 1;

    private static final int DEBUFF = 3;
    private static final int UPGRADE_PLUS_DEBUFF = 2;

    public ImGoingToCallYouNow() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DEBUFF;
        exhaust = true;
        KoishiMod.setBackground(this, 1);
        this.cardsToPreview = new SoAnswerThePhone();
        tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("lastWord");
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new TerrorPower(m, p, magicNumber), magicNumber));
        AbstractCard c = new SoAnswerThePhone();
        if (upgraded) {
            c.upgrade();
        }
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, true));
    }

    @Override
    public float getTitleFontSize()
    {
        return 12;
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_DEBUFF);
            rawDescription = languagePack.getCardStrings(cardID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
