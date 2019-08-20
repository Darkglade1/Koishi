package Koishi.actions;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

public class RorshachInDanmakuAction extends AbstractGameAction {

    int magicNumber;

    public RorshachInDanmakuAction(int magicNumber) {
        this.actionType = ActionType.BLOCK;
        this.duration = Settings.ACTION_DUR_FAST;
        this.magicNumber = magicNumber;
    }

    public void update() {
        this.isDone = false;
        System.out.println(AbstractDungeon.actionManager.actions);
        AbstractPlayer p = AbstractDungeon.player;
        int totalBlock = 0;
        Iterator iterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while (iterator.hasNext()) {
            AbstractMonster mo = (AbstractMonster) iterator.next();
            if (!mo.isDeadOrEscaped()) {
                System.out.println(mo.name + "'s intent is " + mo.intent);
                mo.createIntent(); //To address the edge case where this card is drawn before enemy intents are properly initialized
                if (ForceIntentAction.attackTest.test(mo)) {
                    int moDamage = (Integer) ReflectionHacks.getPrivate(mo, AbstractMonster.class, "intentDmg");
                    if ((Boolean) ReflectionHacks.getPrivate(mo, AbstractMonster.class, "isMultiDmg")) {
                        moDamage *= (Integer) ReflectionHacks.getPrivate(mo, AbstractMonster.class, "intentMultiAmt");
                    }
                    System.out.println(mo.name + " is attacking for " + moDamage);
                    totalBlock += moDamage;
                }
            }
        }

        totalBlock += magicNumber;
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, totalBlock));

        this.isDone = true;
        return;
    }
}


