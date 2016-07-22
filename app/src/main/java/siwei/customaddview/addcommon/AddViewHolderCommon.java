package siwei.customaddview.addcommon;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by siwei.zhao on 2016/7/11.
 * 动态addView的Common；
 */
public abstract class AddViewHolderCommon {

    protected View mBaseView;
    protected ViewGroup mParentView;
    protected OnChildDataChangeListener mChangeListener;
    protected Context mContext;

    /**获取add的parentview，只有在进行bindParentView之后才能获取到*/
    public ViewGroup getParentView(){
        return mParentView;
    }

    /**获取add的view，只有在进行bindParentView之后才能获取到*/
    public View getBaseView() {
        return mBaseView;
    }

    /**
     *addview到parentview中,parentview同时removeAllViews,parentview没变更就不要重复new BindParentView();
     * */
    public void bindParentView(BindParentView parentView){
        bindParentView(parentView, true);
    }

    /**
     * addview到parentview中,parentview没变更就不要重复new BindParentView();
     * @param  parentView addview的parentview
     * @param  parentRemoveChildView parentview在addview的时候是否同时removeAllViews
     * */
    public void bindParentView(BindParentView parentView, boolean parentRemoveChildView){
        mParentView=parentView.mParentView;
        mContext=mParentView.getContext();
        if(parentRemoveChildView){
            if(parentView.bindView!=null)parentView.bindView.onParentRemoveViewBefore(parentView.mParentView);
            parentView.mParentView.removeAllViews();
            if(parentView.bindView!=null)parentView.bindView.onParentRemoveViewAfter(parentView.mParentView);
        }
        mBaseView=getAddView(mContext, mParentView);
        initView(mBaseView);
        initData();
        initEvent();
        onBindParentViewBefore(parentView.getParentView(), parentRemoveChildView);
        mParentView.addView(mBaseView);
        onBindParentViewAfter(parentView.getParentView());
        parentView.bindView=this;
    }

    /**获取parentview需要add的view*/
    public abstract View getAddView(Context context, ViewGroup parentView);

    /**初始化控件,初始化先后顺序：控件-数据-事件*/
    public abstract void initView(View baseView);

    /**初始化数据,初始化先后顺序：控件-数据-事件*/
    public abstract void initData();

    /**初始化点击事件,初始化先后顺序：控件-数据-事件*/
    public abstract void initEvent();

    /**根据不同的tag去返回不同的数据*/
    public abstract Object getViewData(int tag);

    /**根据给出的tag和数据对view进行更新*/
    public abstract void setViewData(int tag, Object data);

    /**在绑定view之前执行*/
    public void onBindParentViewBefore(ViewGroup parentView, boolean parentRemoveChildView){};

    /**在绑定view之后执行*/
    public void onBindParentViewAfter(ViewGroup parentView){};

    /**在移除view之前执行*/
    public void onParentRemoveViewBefore(ViewGroup parentView){};

    /**在移除view之后执行*/
    public void onParentRemoveViewAfter(ViewGroup parentView){};

    /**在AddViewHolderCommon外绑定AddViewHolderCommon内view的监听事件，能在Activity或者Fragment中处理AddViewHolderCommon内的view事件*/
    public void bindViewClickListener(int viewid, Object viewListener){};

    /**设置childview数据发生改变的事件*/
    public void setOnChildDataChangeListener(OnChildDataChangeListener listener){
        mChangeListener=listener;
    }

    /***/
    protected void onChildDataChange(int tag, Object datas){
        if(mChangeListener!=null)mChangeListener.onChildDataChange(tag, datas);
    }


    public static class BindParentView{

        protected ViewGroup mParentView;
        protected AddViewHolderCommon bindView;

        public BindParentView(ViewGroup parentView){
            mParentView=parentView;
        };

        public ViewGroup getParentView() {
            return mParentView;
        }

        public AddViewHolderCommon getBindView() {
            return bindView;
        }

        public void removeAllChildViews(){
            if(bindView!=null)bindView.onParentRemoveViewBefore(mParentView);
            mParentView.removeAllViews();
            if(bindView!=null)bindView.onParentRemoveViewAfter(mParentView);
        }
    }

    /**当childview数据发生改变时候的回调*/
    public static interface OnChildDataChangeListener{

        void onChildDataChange(int tag, Object changeData);
    }

}
