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

import java.util.ArrayList;
import java.util.Iterator;

import static Koishi.KoishiMod.makeCardPath;
import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class SoAnswerThePhone extends AbstractDefaultCard {

    public static final String ID = KoishiMod.makeID(SoAnswerThePhone.class.getSimpleName());
    public static final String IMG = makeCardPath("SoAnswerThePhone.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KoishiCharacter.Enums.COLOR_DARK_GREEN;

    private static final int COST = 2;

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
        KoishiMod.runAnimation("lastWordB");
        ArrayList<AbstractMonster> notDeadMonsters = new ArrayList<>();
        Iterator iterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while (iterator.hasNext()) {
            AbstractMonster mo = (AbstractMonster) iterator.next();
            if (!mo.isDeadOrEscaped()) {
                notDeadMonsters.add(mo);
            }
        }

        for (int i = 0; i < notDeadMonsters.size(); i++) {
            AbstractMonster mo = notDeadMonsters.get(i);
            //makes the special effects appear all at once for multiple monsters instead of one-by-one
            AbstractDungeon.actionManager.addToBottom(new SFXAction("MONSTER_COLLECTOR_DEBUFF"));
            if (i == notDeadMonsters.size() - 1) {
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new CollectorCurseEffect(mo.hb.cX, mo.hb.cY), 2.0F));
            } else {
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new CollectorCurseEffect(mo.hb.cX, mo.hb.cY)));
            }
        }

        for (int i = 0; i < notDeadMonsters.size(); i++) {
            AbstractMonster mo = notDeadMonsters.get(i);
            if (mo.hasPower(TerrorPower.POWER_ID)) {
                int extraAmount = mo.getPower(TerrorPower.POWER_ID).amount * magicNumber;
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new TerrorPower(mo, p, extraAmount), extraAmount));
            }
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
