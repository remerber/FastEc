package example.fastec.com.wang.latte.ui.laucher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by HP on 2017/8/5.
 */

public class LaucherHolderCreator implements CBViewHolderCreator<LaucherHolder> {
    @Override
    public LaucherHolder createHolder() {
        return new LaucherHolder();
    }
}
