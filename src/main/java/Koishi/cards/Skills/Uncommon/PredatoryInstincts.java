package Koishi.cards.Skills.Uncommon;

import Koishi.KoishiMod;
import Koishi.cards.AbstractDefaultCard;
import Koishi.characters.KoishiCharacter;
import Koishi.powers.TerrorPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

import static Koishi.KoishiMod.makeCardPath;

public class PredatoryInstincts extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(PredatoryInstincts.class.getSimpleName());
    public static final String IMG = makeCardPath("PredatoryInstincts.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 1;

    private static final int BLOCK = 5;
    private static final int UPGRADE_PLUS_BLOCK = 2;

    private static final int DEBUFF = 4;
    private static final int UPGRADE_PLUS_DEBUFF = 2;

    public PredatoryInstincts() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DEBUFF;
        baseBlock = BLOCK;
        exhaust = true;
        KoishiMod.setBackground(this, 1);
        tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("occultAttack");
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, this.block));
        Iterator iterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while (iterator.hasNext()) {
            AbstractMonster mo = (AbstractMonster)iterator.next();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new TerrorPower(mo, p, magicNumber), magicNumber));
        }
    }

    @Override
    public float getTitleFontSize()
    {
        return 18;
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeMagicNumber(UPGRADE_PLUS_DEBUFF);
            initializeDescription();
        }
    }
}
