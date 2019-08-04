package Koishi.cards.Skills.Common;

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

public class UnansweredLove extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(UnansweredLove.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("UnansweredLove.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 1;

    private static final int BUFF = 2;
    private static final int UPGRADE_PLUS_BUFF = 1;

    private static final int HP_LOSS = 1;

    public UnansweredLove() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = BUFF;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = HP_LOSS;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("spellB");
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, defaultSecondMagicNumber));
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
