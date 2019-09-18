package Koishi.actions;

import Koishi.cards.Attacks.Uncommon.HeartAttack;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HeartAttackAction extends AbstractGameAction {

    HeartAttack card;

    public HeartAttackAction(HeartAttack card) {
        this.actionType = ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FAST;
        this.card = card;
    }

    public void update() {
        this.isDone = false;
        AbstractPlayer p = AbstractDungeon.player;

        AbstractMonster mo = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        card.calculateCardDamage(mo);
        AbstractDungeon.actionManager.addToTop(new DamageAction(mo, new DamageInfo(p, card.damage, card.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));

        this.isDone = true;
        return;
    }
}


