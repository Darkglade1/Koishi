package Koishi.relics;

import Koishi.KoishiMod;
import Koishi.cards.AbstractIdCard;
import Koishi.powers.EphemeralPower;
import Koishi.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static Koishi.KoishiMod.makeRelicOutlinePath;
import static Koishi.KoishiMod.makeRelicPath;

public class ImaginaryFriend extends CustomRelic {

    public static final String ID = KoishiMod.makeID("ImaginaryFriend");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ImaginaryFriend.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ImaginaryFriend.png"));

    private static final int EPHEMERAL = 1;

    public ImaginaryFriend() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        this.flash();
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EphemeralPower(p, EPHEMERAL), EPHEMERAL));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + EPHEMERAL + DESCRIPTIONS[1];
    }

}
