package Koishi.cards.Attacks.Uncommon;

import Koishi.KoishiMod;
import Koishi.cards.AbstractDefaultCard;
import Koishi.characters.KoishiCharacter;
import Koishi.powers.TerrorPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

import java.util.Iterator;

import static Koishi.KoishiMod.makeCardPath;

public class BloodcurdlingScream extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(BloodcurdlingScream.class.getSimpleName());
    public static final String IMG = makeCardPath("BloodcurdlingScream.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 2;

    private static final int DAMAGE = 13;
    private static final int UPGRADE_PLUS_DMG = 5;

    private static final int DEBUFF = 2;
    private static final int UPGRADE_PLUS_DEBUFF = 1;

    public BloodcurdlingScream() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = DEBUFF;
        KoishiMod.setBackground(this, 0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("magicAttackForward");
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_PIERCING_WAIL"));
        if (Settings.FAST_MODE) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.3F));
        } else {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 1.5F));
        }
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        Iterator iterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(iterator.hasNext()) {
            AbstractMonster mo = (AbstractMonster)iterator.next();
            if (mo.hasPower(TerrorPower.POWER_ID)) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new VulnerablePower(mo, magicNumber, false), magicNumber, true, AbstractGameAction.AttackEffect.NONE));
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
