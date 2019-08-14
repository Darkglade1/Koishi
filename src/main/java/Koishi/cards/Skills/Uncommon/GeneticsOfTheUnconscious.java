package Koishi.cards.Skills.Uncommon;

import Koishi.KoishiMod;
import Koishi.cards.AbstractDefaultCard;
import Koishi.characters.KoishiCharacter;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Koishi.KoishiMod.makeCardPath;

public class GeneticsOfTheUnconscious extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(GeneticsOfTheUnconscious.class.getSimpleName());
    public static final String IMG = makeCardPath("GeneticsOfTheUnconscious.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 1;

    private static final int DRAW = 2;
    private static final int UPGRADE_PLUS_DRAW = 1;

    private static final int EFFECT = 2;
    private static final int UPGRADE_PLUS_EFFECT = 1;

    public GeneticsOfTheUnconscious() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DRAW;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = EFFECT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("spellCall");
        for (int i = 0; i < defaultSecondMagicNumber; i++) {
            AbstractDungeon.actionManager.addToBottom(new DiscardPileToTopOfDeckAction(p));
        }
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_DRAW);
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_EFFECT);
            initializeDescription();
        }
    }
}
