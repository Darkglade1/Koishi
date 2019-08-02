package Koishi.actions;

import Koishi.cards.AbstractIntentChangingCard;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.Hexaghost;

import java.util.function.Predicate;

public class ForceIntentAction extends AbstractGameAction {

	private AbstractIntentChangingCard.IntentTypes intentType;
	private static Predicate<AbstractMonster> attackTest = (mo) -> mo.intent == AbstractMonster.Intent.ATTACK || mo.intent == AbstractMonster.Intent.ATTACK_DEFEND || mo.intent == AbstractMonster.Intent.ATTACK_DEBUFF || mo.intent == AbstractMonster.Intent.ATTACK_BUFF;
	private static Predicate<AbstractMonster> notAttackTest = (mo) -> !(mo.intent == AbstractMonster.Intent.ATTACK || mo.intent == AbstractMonster.Intent.ATTACK_DEFEND || mo.intent == AbstractMonster.Intent.ATTACK_DEBUFF || mo.intent == AbstractMonster.Intent.ATTACK_BUFF);

	private AbstractPlayer p;
	private AbstractMonster m;
	private boolean upgraded;
	private AbstractCard transformToCard;
  	private AbstractCard transformee;
  	public int seed = 0;
  	public static int StabbyMcStabs = 1;
  	public static boolean entangleReset = false;

  	public static boolean champThresholdReached;
  	public static int champNumTurns;
  	public static int forgeTimes;

	public ForceIntentAction(AbstractPlayer p, AbstractMonster m, AbstractIntentChangingCard.IntentTypes type) {
		this.p = p;
		this.m = m;
		this.intentType = type;
	}

	public void update() {
		this.isDone = this.newIntent(this.m, this.intentType);
	}

	public boolean newIntent(AbstractMonster m, AbstractIntentChangingCard.IntentTypes type) {
		Predicate<AbstractMonster> test;
		if (type == AbstractIntentChangingCard.IntentTypes.ATTACK) {
			test = attackTest;
		} else {
			test = notAttackTest;
		}
		if (test.test(m)) {
			return true;
		}
		int tries = 0;
		while (tries < 10) {
			m.rollMove();
			m.createIntent();
			if (test.test(m)) {
				return true;
			}
			tries++;
		}
		return true;
	}

	public static boolean previewNewIntent(AbstractMonster m, AbstractIntentChangingCard.IntentTypes type) {
		Predicate<AbstractMonster> test;
		if (type == AbstractIntentChangingCard.IntentTypes.ATTACK) {
			test = attackTest;
		} else {
			test = notAttackTest;
		}
		if (test.test(m)) {
			return true;
		}
		int tries = 0;
		while (tries < 10) {
			m.rollMove();
			m.createIntent();
			if (test.test(m)) {
				return true;
			}
			tries++;
		}
		return true;
	}

	public static boolean restorePreviewedSpecialCases(AbstractMonster m) {
		int count;
		int turnCount;
		int slashCount;
		boolean isAttacking;

		switch(m.id) {

			case "Dagger":
				m.tint.changeColor(Color.WHITE.cpy(), 0.6F);
      			break;
			// Donu and deca need to swap
			case "Deca":
				isAttacking = (boolean)ReflectionHacks.getPrivate(m, m.getClass(), "isAttacking");
				ReflectionHacks.setPrivate(m, m.getClass(), "isAttacking", !isAttacking);
				break;
			case "Donu":
				isAttacking = (boolean)ReflectionHacks.getPrivate(m, m.getClass(), "isAttacking");
				ReflectionHacks.setPrivate(m, m.getClass(), "isAttacking", !isAttacking);
				break;

			case "Exploder":
				m.tint.changeColor(Color.WHITE.cpy(), 0.6F);
				turnCount = (int)ReflectionHacks.getPrivate(m, m.getClass(), "turnCount");
				ReflectionHacks.setPrivate(m, m.getClass(), "turnCount", turnCount - 1);
				break;

			case "GiantHead":
				count = (int)ReflectionHacks.getPrivate(m, m.getClass(), "count");
				ReflectionHacks.setPrivate(m, m.getClass(), "count", count + 1);
				break;

			case "Transient":
				count = (int)ReflectionHacks.getPrivate(m, m.getClass(), "count");
				ReflectionHacks.setPrivate(m, m.getClass(), "count", count - 1);
				m.tint.changeColor(Color.WHITE.cpy(), 0.6F);
				break;

			case "BronzeAutomaton":
				count = (int)ReflectionHacks.getPrivate(m, m.getClass(), "numTurns");
				if (count <= 0) { count = 4; }
				ReflectionHacks.setPrivate(m, m.getClass(), "numTurns", count);
				break;

			case "SlaverRed":
				if (entangleReset) {
					ReflectionHacks.setPrivate(m, m.getClass(), "usedEntangle", false);
					entangleReset = false;
				}
				break;

			case "Looter":
			case "Mugger":
				m.tint.changeColor(Color.WHITE.cpy(), 0.6F);
				break;

			// Champ has a bunch of hardcoded things since A19
			case "Champ":
				ReflectionHacks.setPrivate(m, m.getClass(), "numTurns", champNumTurns);
				ReflectionHacks.setPrivate(m, m.getClass(), "forgeTimes", forgeTimes);
				ReflectionHacks.setPrivate(m, m.getClass(), "thresholdReached", champThresholdReached);
				break;

			case "BookOfStabbing":
				ReflectionHacks.setPrivate(m, m.getClass(), "stabCount", StabbyMcStabs);
				break;

			case "TheCollector":
				int turnsTaken = (int)ReflectionHacks.getPrivate(m, m.getClass(), "turnsTaken");
				ReflectionHacks.setPrivate(m, m.getClass(), "turnsTaken", turnsTaken - 1);
				break;

      		case "Lagavulin":
      			switch (m.nextMove) {
      				case 5: // sleep
						int idleCount = (int)ReflectionHacks.getPrivate(m, m.getClass(), "idleCount");
						ReflectionHacks.setPrivate(m, m.getClass(), "idleCount", idleCount - 1);
						break;
					case 1:
						ReflectionHacks.setPrivate(m, m.getClass(), "debuffTurnCount", 2);
						break;
					case 3:
						int debuffTurnCount = (int)ReflectionHacks.getPrivate(m, m.getClass(), "debuffTurnCount");
						ReflectionHacks.setPrivate(m, m.getClass(), "debuffTurnCount", debuffTurnCount - 1);
						break;
				}
            	break;
			case "Chosen":
				if (m.nextMove == 4) {
					ReflectionHacks.setPrivate(m, m.getClass(), "usedHex", false); }
				break;
			case "SphericGuardian":
				if (m.nextMove == 4) {
					ReflectionHacks.setPrivate(m, m.getClass(), "secondMove", true); }
				break;
			case "Maw":
				turnCount = (int)ReflectionHacks.getPrivate(m, m.getClass(), "turnCount");
				ReflectionHacks.setPrivate(m, m.getClass(), "turnCount", turnCount - 1);
				break;
			case "Hexaghost":
      			Hexaghost hexa = (Hexaghost)m;
				int orbActiveCount = (int)ReflectionHacks.getPrivate(hexa, Hexaghost.class, "orbActiveCount");
				if (orbActiveCount == 0) {
					ReflectionHacks.setPrivate(hexa, Hexaghost.class, "orbActiveCount", 6);
				} else {
					ReflectionHacks.setPrivate(hexa, Hexaghost.class, "orbActiveCount", orbActiveCount-1);
				}
				break;

			case "CorruptHeart":
				turnCount = (int)ReflectionHacks.getPrivate(m, m.getClass(), "moveCount");
				ReflectionHacks.setPrivate(m, m.getClass(), "moveCount", turnCount - 1);
				break;

			case "SpireShield":
				turnCount = (int)ReflectionHacks.getPrivate(m, m.getClass(), "moveCount");
				ReflectionHacks.setPrivate(m, m.getClass(), "moveCount", turnCount - 1);
				break;
			case "SpireSpear":
				turnCount = (int)ReflectionHacks.getPrivate(m, m.getClass(), "moveCount");
				ReflectionHacks.setPrivate(m, m.getClass(), "moveCount", turnCount - 1);
				break;
            }

        return true;
	}
}