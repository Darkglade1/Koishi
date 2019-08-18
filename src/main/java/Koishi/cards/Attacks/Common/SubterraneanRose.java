package Koishi.cards.Attacks.Common;

import Koishi.KoishiMod;
import Koishi.actions.SubterraneanRoseAction;
import Koishi.cards.AbstractIdCard;
import Koishi.characters.KoishiCharacter;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Koishi.KoishiMod.makeCardPath;

public class SubterraneanRose extends AbstractIdCard {

    public static final String ID = KoishiMod.makeID(SubterraneanRose.class.getSimpleName());
    public static final String IMG = makeCardPath("SubterraneanRose.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 1;

    private static final int DAMAGE = 6;
    private static final int UPGRADE_PLUS_DMG = 1;

    private static final int THORNS = 5;
    private static final int UPGRADE_PLUS_THORNS = 2;

    public SubterraneanRose() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = THORNS;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("kick");
        AbstractDungeon.actionManager.addToBottom(new SubterraneanRoseAction(this));
    }

    @Override
    public float getTitleFontSize()
    {
        return 18;
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_PLUS_THORNS);
            initializeDescription();
        }
    }
}
