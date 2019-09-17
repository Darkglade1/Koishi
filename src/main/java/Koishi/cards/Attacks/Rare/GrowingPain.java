package Koishi.cards.Attacks.Rare;

import Koishi.cards.AbstractDefaultCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Koishi.KoishiMod;
import Koishi.characters.KoishiCharacter;

import static Koishi.KoishiMod.intangibleCount;
import static Koishi.KoishiMod.makeCardPath;

public class GrowingPain extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(GrowingPain.class.getSimpleName());
    public static final String IMG = makeCardPath("GrowingPain.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 1;

    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DMG = 2;

    private static int HIT_COUNT = 1;

    public GrowingPain() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = HIT_COUNT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("specialAttackC");
        for (int i = 0; i < magicNumber; i++) {
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int realMagicNumber = this.baseMagicNumber;
        this.baseMagicNumber += intangibleCount;
        this.magicNumber = baseMagicNumber;
        super.calculateCardDamage(mo);
        this.baseMagicNumber = realMagicNumber;
        this.isMagicNumberModified = this.magicNumber != this.baseMagicNumber;
    }

    @Override
    public void applyPowers() {
        int realMagicNumber = this.baseMagicNumber;
        this.baseMagicNumber += intangibleCount;
        this.magicNumber = baseMagicNumber;
        super.applyPowers();
        this.baseMagicNumber = realMagicNumber;
        this.isMagicNumberModified = this.magicNumber != this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
