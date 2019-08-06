package Koishi.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import Koishi.KoishiMod;
import Koishi.util.TextureLoader;

import static Koishi.KoishiMod.makePowerPath;


public class LoseThornsPower extends AbstractPower {

    public static final String POWER_ID = KoishiMod.makeID("LoseThornsPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("ThornsDown84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("ThornsDown32.png"));

    public LoseThornsPower(AbstractCreature owner, int newAmount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = newAmount;

        type = PowerType.DEBUFF;
        isTurnBased = false;

        //loads textures
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new ThornsPower(owner, -amount), -amount));
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ID));
    }

    @Override
    public void onRemove() {
        if ((this.owner.getPower(ThornsPower.POWER_ID)).amount <= 0)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, "Thorns"));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
