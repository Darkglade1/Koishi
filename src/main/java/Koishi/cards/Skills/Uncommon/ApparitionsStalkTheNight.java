package Koishi.cards.Skills.Uncommon;

import Koishi.KoishiMod;
import Koishi.cards.AbstractDefaultCard;
import Koishi.characters.KoishiCharacter;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;

import static Koishi.KoishiMod.makeCardPath;

public class ApparitionsStalkTheNight extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(ApparitionsStalkTheNight.class.getSimpleName());
    public static final String IMG = makeCardPath("ApparitionsStalkTheNight.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    private static final int BUFF = 1;

    private static final int HP_LOSS = 5;

    public ApparitionsStalkTheNight() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = BUFF;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = HP_LOSS;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, defaultSecondMagicNumber));
        if (!p.hasPower(IntangiblePlayerPower.POWER_ID) && !p.hasPower(IntangiblePower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, magicNumber), magicNumber));
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
