package Koishi.relics;

import Koishi.KoishiMod;
import Koishi.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static Koishi.KoishiMod.makeRelicOutlinePath;
import static Koishi.KoishiMod.makeRelicPath;

public class LettersToReimu extends CustomRelic {

    public static final String ID = KoishiMod.makeID("LettersToReimu");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("LettersToReimu.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("LettersToReimu.png"));

    private static final int TEMP_HP = 2;

    public LettersToReimu() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public void onExhaust(AbstractCard card) {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new AddTemporaryHPAction(p, p, TEMP_HP));
    }
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + TEMP_HP + DESCRIPTIONS[1];
    }

}
