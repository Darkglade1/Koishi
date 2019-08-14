package Koishi.cards.Skills.Rare;

import Koishi.cards.AbstractDefaultCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Koishi.KoishiMod;
import Koishi.characters.KoishiCharacter;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

import static Koishi.KoishiMod.makeCardPath;

public class MindStellarRelief extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(MindStellarRelief.class.getSimpleName());
    public static final String IMG = makeCardPath("MindStellarRelief.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 2;

    private static final int DAMAGE = 3;
    private static final int UPGRADE_PLUS_DMG = 2;

    public MindStellarRelief() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DAMAGE;
        retain = true;
        exhaust = true;
    }

    @Override
    public void atTurnStart() {
        retain = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractPower> debuffsToRemove = new ArrayList<>();
        int debuffCount = 0;
        for (AbstractPower power : m.powers) {
            if (power.type == AbstractPower.PowerType.DEBUFF) {
                debuffsToRemove.add(power);
                debuffCount += Math.abs(power.amount);
            }
        }
        for (AbstractPower power : debuffsToRemove) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, m, power.ID));
        }
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, debuffCount * magicNumber, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
