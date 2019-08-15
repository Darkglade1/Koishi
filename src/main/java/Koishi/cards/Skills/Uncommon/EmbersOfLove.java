package Koishi.cards.Skills.Uncommon;

import Koishi.KoishiMod;
import Koishi.actions.ForceIntentAction;
import Koishi.cards.AbstractIntentChangingCard;
import Koishi.characters.KoishiCharacter;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

import static Koishi.KoishiMod.makeCardPath;
import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class EmbersOfLove extends AbstractIntentChangingCard {

    public static final String ID = KoishiMod.makeID(EmbersOfLove.class.getSimpleName());
    public static final String IMG = makeCardPath("EmbersOfLove.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 0;

    public EmbersOfLove() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, IntentTypes.NOT_ATTACK);
        AlwaysRetainField.alwaysRetain.set(this, true);
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster mo) {
        KoishiMod.runAnimation("spellCall");
        Iterator iterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while (iterator.hasNext()) {
            AbstractMonster m = (AbstractMonster)iterator.next();
            AbstractDungeon.actionManager.addToTop(new ForceIntentAction(p, m, this.intentType));
        }

    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            exhaust = false;
            rawDescription = languagePack.getCardStrings(cardID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
