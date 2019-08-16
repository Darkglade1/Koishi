package Koishi.relics;

import Koishi.KoishiMod;
import Koishi.powers.TerrorPower;
import Koishi.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Koishi.KoishiMod.makeRelicOutlinePath;
import static Koishi.KoishiMod.makeRelicPath;

public class BloodstainedWhiteRose extends CustomRelic {

    public static final String ID = KoishiMod.makeID("BloodstainedWhiteRose");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("BloodstainedWhiteRose.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("BloodstainedWhiteRose.png"));

    private static final int TERROR = 3;

    public BloodstainedWhiteRose() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new TerrorPower(m, p, TERROR), TERROR));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + TERROR + DESCRIPTIONS[1];
    }

}
