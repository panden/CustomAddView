package siwei.customaddview.addcommon;

import android.content.Context;
import android.view.View;

/**
 * Created by siwei.zhao on 2016/7/22.
 */
public class BaseParentViewsCommon extends ViewsCommon<BaseParentViewsCommon.BaseParentViewHolder> {

    private View mBaseView;

    public BaseParentViewsCommon(View view){
        mBaseView=view;
    }

    @Override
    protected BaseParentViewHolder onCreateChildsViewHolder(Context context) {
        return new BaseParentViewHolder(mBaseView);
    }

    @Override
    public String getName() {
        return BASE_PARENT_VIEWS_COMMON;
    }

    public class BaseParentViewHolder extends ViewsCommon.ViewHolder{

        public BaseParentViewHolder(View view) {
            super(view);
        }

        @Override
        protected void initViews(View baseView) {}

        @Override
        protected void initEvent() {}

        @Override
        protected void initData() {}
    }
}
