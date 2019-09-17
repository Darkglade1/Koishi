package Koishi.actions;

import Koishi.cards.Skills.Uncommon.GhostParty;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;

import java.util.Iterator;

public class GhostPartyAction extends AbstractGameAction {

    GhostParty card;

    public GhostPartyAction(GhostParty card) {
        this.actionType = ActionType.POWER;
        this.duration = Settings.ACTION_DUR_FAST;
        this.card = card;
    }

    public void update() {
        this.isDone = false;

        AbstractPlayer p = AbstractDungeon.player;
        if (!p.hasPower(IntangiblePlayerPower.POWER_ID) && !p.hasPower(IntangiblePower.POWER_ID)) {
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, card.magicNumber), card.magicNumber));
            Iterator iterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
            while (iterator.hasNext()) {
                AbstractMonster mo = (AbstractMonster)iterator.next();
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(mo, p, new IntangiblePlayerPower(mo, card.magicNumber), card.magicNumber));
            }
        }

        this.isDone = true;
        return;
    }
}


