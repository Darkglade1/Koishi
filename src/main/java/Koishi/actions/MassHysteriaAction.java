package Koishi.actions;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.OfferingEffect;

import java.util.Iterator;

public class MassHysteriaAction extends AbstractGameAction {

    public MassHysteriaAction() {
        this.actionType = ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        this.isDone = false;

        int totalDamage = 0;
        Iterator iterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(iterator.hasNext()) {
            AbstractMonster mo = (AbstractMonster)iterator.next();
            if (!mo.isDeadOrEscaped()) {
                mo.createIntent(); //To address the edge case where this card is drawn before enemy intents are properly initialized
                if (ForceIntentAction.attackTest.test(mo)) {
                    int moDamage = (Integer) ReflectionHacks.getPrivate(mo, AbstractMonster.class, "intentDmg");
                    if ((Boolean) ReflectionHacks.getPrivate(mo, AbstractMonster.class, "isMultiDmg")) {
                        moDamage *= (Integer) ReflectionHacks.getPrivate(mo, AbstractMonster.class, "intentMultiAmt");
                    }
                    totalDamage += moDamage;
                }
            }
        }
        if (totalDamage > 0) {
            int[] newMultiDamage = new int[AbstractDungeon.getCurrRoom().monsters.monsters.size()];
            for (int i = 0; i < newMultiDamage.length; i++) {
                newMultiDamage[i] = totalDamage;
            }
            if (Settings.FAST_MODE) {
                AbstractDungeon.actionManager.addToTop(new VFXAction(new OfferingEffect(), 0.1F));
            } else {
                AbstractDungeon.actionManager.addToTop(new VFXAction(new OfferingEffect(), 0.5F));
            }
            AbstractDungeon.actionManager.addToTop(new DamageAllEnemiesAction(AbstractDungeon.player, newMultiDamage, DamageInfo.DamageType.HP_LOSS, AbstractGameAction.AttackEffect.NONE));
        }

        this.isDone = true;
        return;
    }
}


