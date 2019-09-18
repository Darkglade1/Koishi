package Koishi.actions;

import Koishi.cards.Skills.Common.SubterraneanRose;
import Koishi.powers.LoseThornsPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ThornsPower;

public class SubterraneanRoseAction extends AbstractGameAction {

    SubterraneanRose card;

    public SubterraneanRoseAction(SubterraneanRose card) {
        this.actionType = ActionType.BLOCK;
        this.duration = Settings.ACTION_DUR_FAST;
        this.card = card;
    }

    public void update() {
        this.isDone = false;

        AbstractPlayer p = AbstractDungeon.player;
        card.calculateCardDamage(null);
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, card.block));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new ThornsPower(p, card.magicNumber), card.magicNumber));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new LoseThornsPower(p, card.magicNumber), card.magicNumber));

        this.isDone = true;
        return;
    }
}


