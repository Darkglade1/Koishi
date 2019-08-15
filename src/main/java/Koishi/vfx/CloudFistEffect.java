package Koishi.vfx;

import Koishi.KoishiMod;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class CloudFistEffect
        extends AbstractGameEffect {
    public static final String CLOUD_FIST = KoishiMod.makeEffectPath("Cloud Fist.png");
    private static final Texture CLOUD_FIST_TEXTURE = new Texture(CLOUD_FIST);
    private TextureRegion CLOUD_FIST_REGION;
    
    private static final float DURATION = 0.8f;

    private float x;
    private float y;
    private float offset;

    private float graphicsAnimation;
    private float scaleWidth;
    private float scaleHeight;

    public CloudFistEffect(float x, float y) {
        this.x = x;
        this.y = y;

        this.CLOUD_FIST_REGION = new TextureRegion(CLOUD_FIST_TEXTURE);

        this.color = Color.WHITE.cpy();
        this.color.a = 0.0F;
        this.duration = this.startingDuration = DURATION;
        this.graphicsAnimation = 0.0F;
        this.offset = this.CLOUD_FIST_REGION.getRegionHeight();

        this.scaleWidth = 1.0F * Settings.scale;
        this.scaleHeight = Settings.scale;
    }


    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        this.graphicsAnimation += Gdx.graphics.getDeltaTime();
        if (this.graphicsAnimation <= 0.5F) {

            this.color.a = this.graphicsAnimation * DURATION;
            if (this.color.a > 1.0F) {
                this.color.a = 1.0F;
            }
        }
        if (this.graphicsAnimation <= DURATION) {
            this.offset = Interpolation.fade.apply(this.CLOUD_FIST_REGION.getRegionHeight(), (0.0F - this.CLOUD_FIST_REGION.getRegionHeight()) / 4, (this.graphicsAnimation + 1.0F) / 1.5F);
        }
        if (this.duration <= 0.0F) {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(this.CLOUD_FIST_REGION, x - (this.CLOUD_FIST_REGION.getRegionWidth() * this.scaleWidth) / 2, y + this.offset * this.scaleHeight, 0.0F, 0.0F, this.CLOUD_FIST_REGION.getRegionWidth(), this.CLOUD_FIST_REGION.getRegionHeight(), this.scaleWidth, this.scaleHeight, 0.0F);
    }

    public void dispose() {
    }
}


