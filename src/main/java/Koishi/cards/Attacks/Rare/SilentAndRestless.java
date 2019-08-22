package Koishi.cards.Attacks.Rare;

import Koishi.KoishiMod;
import Koishi.actions.SilentAndRestlessAction;
import Koishi.cards.AbstractDefaultCard;
import Koishi.characters.KoishiCharacter;
import Koishi.powers.EphemeralPower;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Koishi.KoishiMod.makeCardPath;

public class SilentAndRestless extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(SilentAndRestless.class.getSimpleName());
    public static final String IMG = makeCardPath("SilentAndRestless.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 1;

    private static final int DAMAGE = 13;
    private static final int UPGRADE_PLUS_DAMAGE = 3;

    private static final int BUFF = 2;

    private static final int KILL_BONUS = 2;
    private static final int UPGRADE_PLUS_KILL_BONUS = 1;

    public SilentAndRestless() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        misc = BUFF;
        magicNumber = baseMagicNumber = misc;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = KILL_BONUS;
        exhaust = true;
        AlwaysRetainField.alwaysRetain.set(this, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("dashAttackA");
        AbstractDungeon.actionManager.addToBottom(new SilentAndRestlessAction(m, new DamageInfo(p, damage, damageTypeForTurn), defaultSecondMagicNumber, uuid));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EphemeralPower(p, baseMagicNumber), baseMagicNumber));
    }

    @Override
    public void applyPowers() {
        magicNumber = baseMagicNumber = misc;
        super.applyPowers();
        this.initializeDescription();
    }

    @Override
    public float getTitleFontSize()
    {
        return 20;
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DAMAGE);
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_KILL_BONUS);
            initializeDescription();
        }
    }
}
