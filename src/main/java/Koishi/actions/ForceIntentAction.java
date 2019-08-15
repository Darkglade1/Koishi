package Koishi.actions;

import Koishi.cards.AbstractIntentChangingCard;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.monsters.beyond.GiantHead;
import com.megacrit.cardcrawl.monsters.beyond.Maw;
import com.megacrit.cardcrawl.monsters.city.BookOfStabbing;
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;
import com.megacrit.cardcrawl.monsters.exordium.Hexaghost;

import java.util.function.Predicate;

public class ForceIntentAction extends AbstractGameAction {

	private AbstractIntentChangingCard.IntentTypes intentType;
	public static Predicate<AbstractMonster> attackTest = (mo) -> mo.intent == AbstractMonster.Intent.ATTACK || mo.intent == AbstractMonster.Intent.ATTACK_DEFEND || mo.intent == AbstractMonster.Intent.ATTACK_DEBUFF || mo.intent == AbstractMonster.Intent.ATTACK_BUFF;
	public static Predicate<AbstractMonster> notAttackTest = (mo) -> !(mo.intent == AbstractMonster.Intent.ATTACK || mo.intent == AbstractMonster.Intent.ATTACK_DEFEND || mo.intent == AbstractMonster.Intent.ATTACK_DEBUFF || mo.intent == AbstractMonster.Intent.ATTACK_BUFF);

	private AbstractPlayer p;
	private AbstractMonster m;

	public ForceIntentAction(AbstractPlayer p, AbstractMonster m, AbstractIntentChangingCard.IntentTypes type) {
		this.p = p;
		this.m = m;
		this.intentType = type;
	}

	public void update() {
		this.isDone = this.newIntent(this.m, this.intentType);
	}

	public boolean newIntent(AbstractMonster m, AbstractIntentChangingCard.IntentTypes type) {
		//Doesn't change the intent of these monsters because doing so messes with their internal counters
		if (m.id.equals(GiantHead.ID) || m.id.equals(Maw.ID) || m.id.equals(BookOfStabbing.ID) || m.id.equals(CorruptHeart.ID)) {
			return true;
		}

		Predicate<AbstractMonster> test;
		if (type == AbstractIntentChangingCard.IntentTypes.ATTACK) {
			test = attackTest;
		} else {
			test = notAttackTest;
		}
		//Does nothing if the intent already matches
		if (test.test(m)) {
			return true;
		}
		//Stores the original move then tries to find another appropriate move
		EnemyMoveInfo originalMove = (EnemyMoveInfo) ReflectionHacks.getPrivate(m, AbstractMonster.class, "move");
		int tries = 0;
		while (tries < 10) {
			m.rollMove();
			m.createIntent();
			if (test.test(m)) {
				return true;
			}
			tries++;
		}
		//sets intent back to original move if no suitable moves were found
		m.setMove(originalMove.nextMove, originalMove.intent, originalMove.baseDamage, originalMove.multiplier, originalMove.isMultiDamage);
		m.createIntent();
		return true;
	}

	public static boolean previewNewIntent(AbstractMonster m, AbstractIntentChangingCard.IntentTypes type) {
		//Doesn't change the intent of these monsters because doing so messes with their internal counters
		if (m.id.equals(GiantHead.ID) || m.id.equals(Maw.ID) || m.id.equals(BookOfStabbing.ID) || m.id.equals(CorruptHeart.ID)) {
			return true;
		}

		Predicate<AbstractMonster> test;
		if (type == AbstractIntentChangingCard.IntentTypes.ATTACK) {
			test = attackTest;
		} else {
			test = notAttackTest;
		}
		//Does nothing if the intent already matches
		if (test.test(m)) {
			return true;
		}
		//Stores the original move then tries to find another appropriate move
		EnemyMoveInfo originalMove = (EnemyMoveInfo) ReflectionHacks.getPrivate(m, AbstractMonster.class, "move");
		int tries = 0;
		while (tries < 10) {
			m.rollMove();
			m.createIntent();
			if (test.test(m)) {
				return true;
			}
			tries++;
		}
		//sets intent back to original move if no suitable moves were found
		m.setMove(originalMove.nextMove, originalMove.intent, originalMove.baseDamage, originalMove.multiplier, originalMove.isMultiDamage);
		m.createIntent();
		return true;
	}
}