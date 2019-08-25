package Koishi.variables;

import Koishi.cards.AbstractDefaultCard;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class DefaultSecondMagicNumber extends DynamicVariable {

    @Override
    public String key() {
        return "Koishi:SecondMagic";
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractDefaultCard) card).isDefaultSecondMagicNumberModified;

    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractDefaultCard) card).defaultSecondMagicNumber;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractDefaultCard) card).defaultBaseSecondMagicNumber;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractDefaultCard) card).upgradedDefaultSecondMagicNumber;
    }
}