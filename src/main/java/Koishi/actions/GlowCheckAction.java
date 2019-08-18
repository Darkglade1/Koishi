package Koishi.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class GlowCheckAction extends AbstractGameAction {

    public GlowCheckAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        this.isDone = false;

        AbstractDungeon.player.hand.glowCheck();

        this.isDone = true;
        return;
    }
}


