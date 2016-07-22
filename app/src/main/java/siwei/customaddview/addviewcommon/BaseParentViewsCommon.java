package siwei.customaddview.addviewcommon;

import android.view.View;

/**
 * Created by siwei.zhao on 2016/7/22.
 */
public class BaseParentViewsCommon extends ViewsCommon<BaseParentViewsCommon.BaseParentViewHolder>{

    private View mBaseView;

    public BaseParentViewsCommon(View view){
        mBaseView=view;
    }

    @Override
    protected BaseParentViewHolder onCreateChildsViewHolder() {
        return new BaseParentViewHolder(mBaseView);
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