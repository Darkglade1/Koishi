package Koishi.cards.Skills.Rare;

import Koishi.KoishiMod;
import Koishi.cards.AbstractDefaultCard;
import Koishi.characters.KoishiCharacter;
import Koishi.powers.TerrorPower;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;

import static Koishi.KoishiMod.makeCardPath;
import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class SoAnswerThePhone extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(SoAnswerThePhone.class.getSimpleName());
    public static final String IMG = makeCardPath("SoAnswerThePhone.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 1;

    private static final int DEBUFF = 1;
    private static final int UPGRADE_PLUS_DEBUFF = 1;

    public SoAnswerThePhone() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DEBUFF;
        exhaust = true;
        KoishiMod.setBackground(this, 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower(TerrorPower.POWER_ID)) {
            KoishiMod.runAnimation("lastWordB");
            AbstractDungeon.actionManager.addToBottom(new SFXAction("MONSTER_COLLECTOR_DEBUFF"));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new CollectorCurseEffect(m.hb.cX, m.hb.cY), 2.0F));
            int extraAmount = m.getPower(TerrorPower.POWER_ID).amount * magicNumber;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new TerrorPower(m, p, extraAmount), extraAmount));
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
            upgradeMagicNumber(UPGRADE_PLUS_DEBUFF);
            rawDescription = languagePack.getCardStrings(cardID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
