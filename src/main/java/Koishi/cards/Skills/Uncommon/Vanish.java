package Koishi.cards.Skills.Uncommon;

import Koishi.KoishiMod;
import Koishi.cards.AbstractDefaultCard;
import Koishi.characters.KoishiCharacter;
import Koishi.powers.EphemeralPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Koishi.KoishiMod.makeCardPath;

public class Vanish extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(Vanish.class.getSimpleName());
    public static final String IMG = makeCardPath("Vanish.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 1;

    private static final int BUFF = 7;
    private static final int UPGRADE_PLUS_BUFF = 2;

    private static final int HP_LOSS = 3;

    public Vanish() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = BUFF;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = HP_LOSS;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < defaultSecondMagicNumber; i++) {
            AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, 1));
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EphemeralPower(p, magicNumber), magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_BUFF);
            initializeDescription();
        }
    }
}
