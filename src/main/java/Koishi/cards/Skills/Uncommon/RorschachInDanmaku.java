package Koishi.cards.Skills.Uncommon;

import Koishi.KoishiMod;
import Koishi.actions.RorshachInDanmakuAction;
import Koishi.cards.AbstractIdCard;
import Koishi.characters.KoishiCharacter;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Koishi.KoishiMod.makeCardPath;
import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class RorschachInDanmaku extends AbstractIdCard {

    public static final String ID = KoishiMod.makeID(RorschachInDanmaku.class.getSimpleName());
    public static final String IMG = makeCardPath("RorschachInDanmaku.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 2;

    private static final int BONUS_BLOCK = 5;

    public RorschachInDanmaku() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = BONUS_BLOCK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("spellA");
        if (upgraded) {
            AbstractDungeon.actionManager.addToBottom(new RorshachInDanmakuAction(magicNumber));
        } else {
            AbstractDungeon.actionManager.addToBottom(new RorshachInDanmakuAction(0));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = languagePack.getCardStrings(cardID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
