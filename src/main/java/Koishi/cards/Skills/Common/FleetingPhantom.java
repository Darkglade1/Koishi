package Koishi.cards.Skills.Common;

import Koishi.KoishiMod;
import Koishi.cards.AbstractDefaultCard;
import Koishi.characters.KoishiCharacter;
import Koishi.powers.EphemeralPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Koishi.KoishiMod.makeCardPath;

public class FleetingPhantom extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(FleetingPhantom.class.getSimpleName());
    public static final String IMG = makeCardPath("FleetingPhantom.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 1;

    private static final int DRAW = 2;
    private static final int UPGRADE_PLUS_DRAW = 1;

    private static final int BUFF = 1;

    public FleetingPhantom() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DRAW;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = BUFF;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("spellCall");
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EphemeralPower(p, defaultBaseSecondMagicNumber), defaultBaseSecondMagicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_DRAW);
            initializeDescription();
        }
    }
}
