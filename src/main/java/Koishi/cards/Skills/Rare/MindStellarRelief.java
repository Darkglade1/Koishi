package Koishi.cards.Skills.Rare;

import Koishi.KoishiMod;
import Koishi.cards.AbstractDefaultCard;
import Koishi.characters.KoishiCharacter;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Koishi.KoishiMod.makeCardPath;

public class MindStellarRelief extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(MindStellarRelief.class.getSimpleName());
    public static final String IMG = makeCardPath("MindStellarRelief.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 2;

    private static final int DAMAGE = 15;
    private static final int UPGRADE_PLUS_DMG = 3;

    private static final int BONUS_DAMAGE = 3;
    private static final int UPGRADE_PLUS_BONUS_DAMAGE = 2;

    public MindStellarRelief() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DAMAGE;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = BONUS_DAMAGE;
        AlwaysRetainField.alwaysRetain.set(this, true);
        exhaust = true;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int realMagicNumber = this.baseMagicNumber;
        this.baseMagicNumber += KoishiMod.debuffCount * defaultSecondMagicNumber;
        this.magicNumber = baseMagicNumber;
        super.calculateCardDamage(mo);
        this.baseMagicNumber = realMagicNumber;
        this.isMagicNumberModified = this.magicNumber != this.baseMagicNumber;
    }

    @Override
    public void applyPowers() {
        int realMagicNumber = this.baseMagicNumber;
        this.baseMagicNumber += KoishiMod.debuffCount * defaultSecondMagicNumber;
        this.magicNumber = baseMagicNumber;
        super.applyPowers();
        this.baseMagicNumber = realMagicNumber;
        this.isMagicNumberModified = this.magicNumber != this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("mindStellarRelief");
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, magicNumber, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.FIRE));
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
            upgradeMagicNumber(UPGRADE_PLUS_DMG);
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_BONUS_DAMAGE);
            initializeDescription();
        }
    }
}
