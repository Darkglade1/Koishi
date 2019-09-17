package Koishi.cards.Attacks.Common;

import Koishi.KoishiMod;
import Koishi.cards.AbstractDefaultCard;
import Koishi.cards.AbstractIdCard;
import Koishi.characters.KoishiCharacter;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.ReprogramAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Koishi.KoishiMod.makeCardPath;

public class Prune extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(Prune.class.getSimpleName());
    public static final String IMG = makeCardPath("Prune.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 1;

    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DMG = 2;

    private static final int SCRY = 2;
    private static final int UPGRADE_PLUS_SCRY = 1;

    private boolean fromIdDraw = false;


    public Prune() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = SCRY;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("downAttack");
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractDungeon.actionManager.addToBottom(new ReprogramAction(magicNumber));
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (AbstractIdCard.drewIdCardThisTurn) {
            setCostForTurn(0);
            fromIdDraw = true;
        } else {
            if (fromIdDraw) {
                setCostForTurn(this.cost);
                this.isCostModifiedForTurn = false;
                fromIdDraw = false;
            }
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_PLUS_SCRY);
            initializeDescription();
        }
    }
}
