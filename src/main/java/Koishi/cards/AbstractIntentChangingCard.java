package Koishi.cards;

import Koishi.actions.ForceIntentAction;
import Koishi.vfx.ForceIntentChangePreviewEffect;
import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class AbstractIntentChangingCard extends AbstractDefaultCard {

    public enum IntentTypes {
        ATTACK,
        NOT_ATTACK
    }

    public IntentTypes intentType;

    public ArrayList<EnemyMoveInfo> enemyMoves = new ArrayList<>();
    public EnemyMoveInfo move;
    public EnemyMoveInfo nextMove;
    public AbstractMonster newTarget;
    public boolean intentRevert = false;
    public static long songID = 0L;

    public AbstractIntentChangingCard(final String id,
                                      final String img,
                                      final int cost,
                                      final CardType type,
                                      final CardColor color,
                                      final CardRarity rarity,
                                      final CardTarget target,
                                      final IntentTypes intentType) {

        super(id, img, cost, type, color, rarity, target);
        this.intentType = intentType;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if (this.newTarget == null) {
            CardCrawlGame.sound.stop("EVENT_SHINING");
            songID = CardCrawlGame.sound.playA("EVENT_SHINING", -0.5F);

            ArrayList monsters = AbstractDungeon.getCurrRoom().monsters.monsters;
            for (int i = 0; i < monsters.size(); i++) {
                AbstractMonster m = (AbstractMonster)monsters.get(i);
                enemyMoves.add((EnemyMoveInfo) ReflectionHacks.getPrivate(m, AbstractMonster.class, "move"));
                if (!m.isDeadOrEscaped() && !m.halfDead) {
                    this.newTarget = m;
                    //this.move = (EnemyMoveInfo) ReflectionHacks.getPrivate(m, AbstractMonster.class, "move");

                    int counter = AbstractDungeon.aiRng.counter;
                    long seed0 = ((Long) ReflectionHacks.getPrivate(AbstractDungeon.aiRng.random, com.badlogic.gdx.math.RandomXS128.class, "seed0")).longValue();
                    long seed1 = ((Long) ReflectionHacks.getPrivate(AbstractDungeon.aiRng.random, com.badlogic.gdx.math.RandomXS128.class, "seed1")).longValue();

                    ForceIntentAction.previewNewIntent(this.newTarget, intentType);

                    AbstractDungeon.aiRng.counter = counter;
                    AbstractDungeon.aiRng.random.setState(seed0, seed1);
                }
            }
        }

        this.intentRevert = false;
    }

    @Override
    public void update() {
        super.update();
        if (newTarget != null && !this.intentRevert) {
            Iterator iterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
            while (iterator.hasNext()) {
                AbstractMonster m = (AbstractMonster) iterator.next();
                if (!m.isDeadOrEscaped() && !m.halfDead) {
                    newTarget = m;
                    AbstractDungeon.effectsQueue.add(new ForceIntentChangePreviewEffect(this.newTarget.intentHb.cX, this.newTarget.intentHb.cY, 0.75F, 1.75F));
                }
            }
        }

        if (newTarget != null && this.intentRevert) {
            CardCrawlGame.sound.stop("EVENT_SHINING");

            ArrayList monsters = AbstractDungeon.getCurrRoom().monsters.monsters;
            for (int i = 0; i < monsters.size(); i++) {
                AbstractMonster m = (AbstractMonster)monsters.get(i);
                if (!m.isDeadOrEscaped() && !m.halfDead) {
                    newTarget = m;
                    this.newTarget.moveHistory.remove(this.newTarget.moveHistory.size() - 1);
                    if (this.newTarget.moveHistory.size() > 0) {
                        this.newTarget.moveHistory.remove(this.newTarget.moveHistory.size() - 1);
                    }

                    EnemyMoveInfo move = enemyMoves.get(i);
                    this.newTarget.setMove(move.nextMove, move.intent, move.baseDamage, move.multiplier, move.isMultiDamage);
                    this.newTarget.createIntent();

                    this.newTarget = null;
                }
            }
            enemyMoves.clear();
        }

        this.intentRevert = true;
    }
}
    

