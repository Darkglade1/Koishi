package Koishi.cards.Attacks.Uncommon;

import Koishi.cards.AbstractDefaultCard;
import Koishi.cards.AbstractIdCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Koishi.KoishiMod;
import Koishi.characters.KoishiCharacter;

import static Koishi.KoishiMod.makeCardPath;

public class LastRemote extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(LastRemote.class.getSimpleName());
    public static final String IMG = makeCardPath("LastRemote.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 1;

    private static final int DAMAGE = 5;
    private static final int UPGRADE_PLUS_DMG = 3;

    private static final int ENERGY = 1;

    public LastRemote() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = ENERGY;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("kick");
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(magicNumber));
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (AbstractIdCard.drewIdCardThisTurn) {
            freeToPlayOnce = true;
        } else {
            freeToPlayOnce = false;
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
