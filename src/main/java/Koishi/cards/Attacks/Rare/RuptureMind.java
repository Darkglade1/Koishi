package Koishi.cards.Attacks.Rare;

import Koishi.KoishiMod;
import Koishi.cards.AbstractDefaultCard;
import Koishi.characters.KoishiCharacter;
import Koishi.tags.Tags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Koishi.KoishiMod.makeCardPath;

public class RuptureMind extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(RuptureMind.class.getSimpleName());
    public static final String IMG = makeCardPath("RuptureMind.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 1;

    private static final int DAMAGE = 12;
    private static final int UPGRADE_PLUS_DMG = 3;

    private static final int DEBUFF = 2;
    private static final int UPGRADE_PLUS_DEBUFF = 1;

    public RuptureMind() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = DEBUFF;
        tags.add(Tags.DEBUFF_THIS_TURN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("occultAttack");
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        if (KoishiMod.appliedDebuffThisTurn) {
            for (AbstractPower debuff : m.powers) {
                if (debuff.type == AbstractPower.PowerType.DEBUFF) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, debuff, magicNumber));
                }
            }
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_PLUS_DEBUFF);
            initializeDescription();
        }
    }
}
