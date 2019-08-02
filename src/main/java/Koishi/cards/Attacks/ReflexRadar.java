package Koishi.cards.Attacks;

import Koishi.actions.ForceIntentAction;
import Koishi.cards.AbstractDefaultCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Koishi.KoishiMod;
import Koishi.characters.KoishiCharacter;

import static Koishi.KoishiMod.makeCardPath;

public class ReflexRadar extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(ReflexRadar.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("ReflexRadar.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 1;

    private static final int DAMAGE = 12;
    private static final int UPGRADE_PLUS_DMG = 4;

    public ReflexRadar() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        System.out.println("hello1");
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            System.out.println("hello2");
            if (!mo.isDeadOrEscaped() && !mo.halfDead) {
                System.out.println("hello3");
                if (ForceIntentAction.attackTest.test(mo)) {
                    System.out.println("hello4");
                    AbstractDungeon.actionManager.addToBottom(
                            new DamageAction(mo, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                }
            }
        }
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