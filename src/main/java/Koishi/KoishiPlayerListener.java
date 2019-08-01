package Koishi;

import Koishi.characters.KoishiCharacter;
import com.brashmonkey.spriter.Animation;
import com.brashmonkey.spriter.Player;

public class KoishiPlayerListener implements Player.PlayerListener {

    private KoishiCharacter character;

    public KoishiPlayerListener(KoishiCharacter character) {
        this.character = character;
    }
    public void animationFinished(Animation animation){
        if (!animation.name.equals("idle")) {
            character.resetAnimation();
        }
    }

    //UNUSED
    public void animationChanged(Animation var1, Animation var2){

    }

    //UNUSED
    public void preProcess(Player var1){

    }

    //UNUSED
    public void postProcess(Player var1){

    }

    //UNUSED
    public void mainlineKeyChanged(com.brashmonkey.spriter.Mainline.Key var1, com.brashmonkey.spriter.Mainline.Key var2){

    }
}
