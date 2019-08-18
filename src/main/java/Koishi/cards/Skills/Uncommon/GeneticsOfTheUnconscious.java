package Koishi.cards.Skills.Uncommon;

import Koishi.KoishiMod;
import Koishi.cards.AbstractDefaultCard;
import Koishi.characters.KoishiCharacter;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Koishi.KoishiMod.makeCardPath;
import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class GeneticsOfTheUnconscious extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(GeneticsOfTheUnconscious.class.getSimpleName());
    public static final String IMG = makeCardPath("GeneticsOfTheUnconscious.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 0;

    private static final int ENERGY_LOSS = 1;

    private static final int DRAW = 2;

    private static final int EFFECT = 2;

    public GeneticsOfTheUnconscious() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DRAW;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = EFFECT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("spellCall");
        AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(ENERGY_LOSS));
        for (int i = 0; i < defaultSecondMagicNumber; i++) {
            AbstractDungeon.actionManager.addToBottom(new DiscardPileToTopOfDeckAction(p));
        }
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));
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
            AlwaysRetainField.alwaysRetain.set(this, true);
            rawDescription = languagePack.getCardStrings(cardID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
