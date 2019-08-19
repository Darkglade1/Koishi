package Koishi.relics;

import Koishi.KoishiMod;
import Koishi.cards.AbstractIdCard;
import Koishi.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;
import java.util.Iterator;

import static Koishi.KoishiMod.makeRelicOutlinePath;
import static Koishi.KoishiMod.makeRelicPath;

public class ImperfectMetamorphosis extends CustomRelic {

    public static final String ID = KoishiMod.makeID("ImperfectMetamorphosis");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ImperfectMetamorphosis.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ImperfectMetamorphosis.png"));

    private static final int TRANSFORM_NUM = 2;
    private boolean cardsSelected = true;

    public ImperfectMetamorphosis() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.HEAVY);
    }

    @Override
    public void onEquip() {
        this.cardsSelected = false;
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        Iterator var2 = AbstractDungeon.player.masterDeck.group.iterator();

        while(var2.hasNext()) {
            AbstractCard card = (AbstractCard)var2.next();
            if (!card.cardID.equals("Necronomicurse") && !card.cardID.equals("AscendersBane")) {
                tmp.addToTop(card);
            }
        }

        if (tmp.group.isEmpty()) {
            this.cardsSelected = true;
        } else {
            if (tmp.group.size() <= TRANSFORM_NUM) {
                this.giveCards(tmp.group);
            } else if (!AbstractDungeon.isScreenUp) {
                AbstractDungeon.gridSelectScreen.open(tmp, TRANSFORM_NUM, "Choose 2 cards for " + this.name + LocalizedStrings.PERIOD, false, false, false, false);
            } else {
                AbstractDungeon.dynamicBanner.hide();
                AbstractDungeon.previousScreen = AbstractDungeon.screen;
                AbstractDungeon.gridSelectScreen.open(tmp, TRANSFORM_NUM, "Choose 2 cards for " + this.name + LocalizedStrings.PERIOD, false, false, false, false);
            }

        }
    }

    @Override
    public void update() {
        super.update();
        if (!this.cardsSelected && AbstractDungeon.gridSelectScreen.selectedCards.size() == TRANSFORM_NUM) {
            this.giveCards(AbstractDungeon.gridSelectScreen.selectedCards);
        }

    }

    public void giveCards(ArrayList<AbstractCard> group) {
        this.cardsSelected = true;
        float displayCount = 0.0F;
        Iterator i = group.iterator();

        while(i.hasNext()) {
            AbstractCard card = (AbstractCard)i.next();
            card.untip();
            card.unhover();
            AbstractDungeon.player.masterDeck.removeCard(card);

            AbstractCard transformedCard;
            Random rng = AbstractDungeon.miscRng;
            switch(card.color) {
                case CURSE:
                    transformedCard = CardLibrary.getCurse(card, rng).makeCopy();
                    break;
                default:
                    transformedCard = AbstractIdCard.returnTrulyRandomIdCard().makeCopy();
            }

            UnlockTracker.markCardAsSeen(transformedCard.cardID);

            if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.TRANSFORM) {
                AbstractDungeon.topLevelEffectsQueue.add(new ShowCardAndObtainEffect(transformedCard, (float) Settings.WIDTH / 3.0F + displayCount, (float)Settings.HEIGHT / 2.0F, false));
                displayCount += (float)Settings.WIDTH / 6.0F;
            }
        }

        AbstractDungeon.gridSelectScreen.selectedCards.clear();
        AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.25F;
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + TRANSFORM_NUM + DESCRIPTIONS[1];
    }

}
