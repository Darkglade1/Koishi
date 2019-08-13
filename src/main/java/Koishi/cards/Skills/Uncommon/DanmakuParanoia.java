package Koishi.cards.Skills.Uncommon;

import Koishi.KoishiMod;
import Koishi.cards.AbstractDefaultCard;
import Koishi.characters.KoishiCharacter;
import Koishi.powers.TerrorPower;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

import static Koishi.KoishiMod.makeCardPath;

public class DanmakuParanoia extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(DanmakuParanoia.class.getSimpleName());
    public static final String IMG = makeCardPath("DanmakuParanoia.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 2;

    private static final int BLOCK = 10;
    private static final int UPGRADE_PLUS_BLOCK = 5;

    private static final int MULTIPLIER = 1;

    public DanmakuParanoia() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        magicNumber = baseMagicNumber = MULTIPLIER;
        KoishiMod.setBackground(this, 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseBlock = this.baseBlock;
        this.baseBlock += countTerror() * magicNumber;
        super.calculateCardDamage(mo);
        this.baseBlock = realBaseBlock;
        this.isBlockModified = this.block != this.baseBlock;
    }

    @Override
    public void applyPowers() {
        int realBaseBlock = this.baseBlock;
        this.baseBlock += countTerror() * magicNumber;
        super.applyPowers();
        this.baseBlock = realBaseBlock;
        this.isBlockModified = this.block != this.baseBlock;
    }

    public static int countTerror() {
        int count = 0;
        Iterator iterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while (iterator.hasNext()) {
            AbstractMonster m = (AbstractMonster)iterator.next();
            if (m.hasPower(TerrorPower.POWER_ID)) {
                count += m.getPower(TerrorPower.POWER_ID).amount;
            }
        }

        return count;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }
}
