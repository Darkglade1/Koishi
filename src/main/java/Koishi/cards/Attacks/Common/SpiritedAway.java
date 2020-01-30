package Koishi.cards.Attacks.Common;

import Koishi.KoishiMod;
import Koishi.cards.AbstractDefaultCard;
import Koishi.characters.KoishiCharacter;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;

import static Koishi.KoishiMod.makeCardPath;

public class SpiritedAway extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(SpiritedAway.class.getSimpleName());
    public static final String IMG = makeCardPath("SpiritedAway.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 2;

    private static final int DAMAGE = 12;
    private static final int UPGRADE_PLUS_DMG = 4;

    private static final int DRAW = 1;

    private boolean fromIntangible = false;

    public SpiritedAway() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = DRAW;
        AlwaysRetainField.alwaysRetain.set(this, true);
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.player.hasPower(IntangiblePlayerPower.POWER_ID) || AbstractDungeon.player.hasPower(IntangiblePower.POWER_ID)) {
            setCostForTurn(0);
            fromIntangible = true;
        } else {
            if (fromIntangible) {
                setCostForTurn(this.cost);
                this.isCostModifiedForTurn = false;
                fromIntangible = false;
            }
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("magicAttackForward");
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (AbstractDungeon.player.hasPower(IntangiblePlayerPower.POWER_ID) || AbstractDungeon.player.hasPower(IntangiblePower.POWER_ID)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
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
