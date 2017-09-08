package example.fastec.com.wang.latte.ec.main.sort.content;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import example.fastec.com.wang.latte.ec.R;

/**
 * Created by HP on 2017/8/15.
 */

public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean, BaseViewHolder> {

    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SectionBean item) {
        helper.setText(R.id.header, item.header);
        helper.setVisible(R.id.more, item.ismIsMore());
        helper.addOnClickListener(R.id.more);
    }

    @Override
    protected void convert(BaseViewHolder helper, SectionBean item) {
        //item.t返回SectionBean类型
        final String thumb = item.t.getGoodsThumb();
        final String name = item.t.getGoodsName();
        final int googsId = item.t.getmGoodsId();

        final SectionContentItemEntity entity = item.t;
        helper.setText(R.id.tv, name);
        final AppCompatImageView  goodsImageView = helper.getView(R.id.iv);
        Glide.with(mContext).load(thumb).diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate().into(goodsImageView);
    }
}
