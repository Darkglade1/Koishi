package Koishi.cards.Skills.Common;

import Koishi.cards.AbstractDefaultCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Koishi.KoishiMod;
import Koishi.characters.KoishiCharacter;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static Koishi.KoishiMod.makeCardPath;

public class HeartfeltFancy extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(HeartfeltFancy.class.getSimpleName());
    public static final String IMG = makeCardPath("HeartfeltFancy.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 0;

    private static final int ENERGY_LOSS = 1;

    private static final int BLOCK = 8;
    private static final int UPGRADE_PLUS_BLOCK = 3;

    public HeartfeltFancy() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        KoishiMod.runAnimation("spellA");
        AbstractDungeon.actionManager.addToBottom(new DiscardPileToTopOfDeckAction(p));
        if (EnergyPanel.totalCount > 0) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        }
        AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(ENERGY_LOSS));
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
