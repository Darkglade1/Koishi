package Koishi.cards.Skills.Uncommon;

import Koishi.KoishiMod;
import Koishi.actions.ForceIntentAction;
import Koishi.cards.AbstractDefaultCard;
import Koishi.characters.KoishiCharacter;
import Koishi.powers.EphemeralPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Koishi.KoishiMod.makeCardPath;

public class DNAsFlaw extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(DNAsFlaw.class.getSimpleName());
    public static final String IMG = makeCardPath("DNAsFlaw.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 0;

    private static final int BUFF = 2;
    private static final int UPGRADE_PLUS_BUFF = 1;

    public DNAsFlaw() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = BUFF;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("spellB");
        if (ForceIntentAction.attackTest.test(m)) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EphemeralPower(p, magicNumber), magicNumber));
        }
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
