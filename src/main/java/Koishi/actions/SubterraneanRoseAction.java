package Koishi.actions;

import Koishi.cards.Attacks.Common.SubterraneanRose;
import Koishi.powers.LoseThornsPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;

public class SubterraneanRoseAction extends AbstractGameAction {

    SubterraneanRose card;

    public SubterraneanRoseAction(SubterraneanRose card) {
        this.actionType = ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FAST;
        this.card = card;
    }

    public void update() {
        this.isDone = false;

        AbstractPlayer p = AbstractDungeon.player;
        AbstractMonster mo = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        card.calculateCardDamage(mo);
        AbstractDungeon.actionManager.addToBottom(new DamageAction(mo, new DamageInfo(p, card.damage, card.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ThornsPower(p, card.magicNumber), card.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LoseThornsPower(p, card.magicNumber), card.magicNumber));

        this.isDone = true;
        return;
    }
}


