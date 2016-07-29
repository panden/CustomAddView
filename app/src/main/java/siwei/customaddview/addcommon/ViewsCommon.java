package siwei.customaddview.addcommon;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by siwei.zhao on 2016/7/22.
 */
public abstract class ViewsCommon<VH extends ViewsCommon.ViewHolder> {

    protected VH mVH;
    protected List<ViewsCommon> mChilds=new ArrayList<>();
    private ViewsCommon mParentView;

    public static final String BASE_PARENT_VIEWS_COMMON="BaseParentViewsCommon";

    /**绑定到父布局中*/
    protected void bindParent(BindView bindView){
        mParentView=bindView.mCommon;
        mVH=onCreateChildsViewHolder(bindView.mBindView.getContext());
        mVH.initViews(mVH.baseView);
        mVH.initEvent();
        mVH.initData();
        bindView.mBindView.addView(mVH.baseView);
        onViewsCommonBind();
    }

    /**从父布局中移除当前布局*/
    protected void unBindParent(){
        View parentView=mParentView.getViewHolder().baseView;
        if(parentView instanceof ViewGroup){
            ViewGroup vp= (ViewGroup) parentView;
            vp.removeView(getViewHolder().baseView);
            onViewsCommonUnBind();
        }
    }

    //get bind view
    public BindView getBindView(ViewGroup bindView){
        return new BindView(bindView, ViewsCommon.this);
    }

    /**get parent views*/
    public ViewsCommon getParent(){
        return mParentView;
    }

    /**add views*/
    public boolean addChildViews(BindView bindView, ViewsCommon child){
        if(child==null)return false;
        child.bindParent(bindView);
        if(mChilds==null)mChilds=new ArrayList<ViewsCommon>();
        mChilds.add(child);
        return true;
    }

    /**remove views*/
    public void removeChildView(ViewsCommon child){
        child.unBindParent();
        if(mChilds!=null){
            if(mChilds.contains(child))mChilds.remove(child);
        }
    }

    public List<ViewsCommon> getChilds(){
        return mChilds;
    }

    //get child view by child name
    public ViewsCommon getChild(String childName){
        for(ViewsCommon child: mChilds){
            if(child.getName().equals(childName))return child;
        }
        return null;
    }

    public VH getViewHolder(){
        return mVH;
    }

    /**获取ViewsCommon的控件的根控件的ID*/
    public int getChildRootViewID(){
        if(mVH==null)return -1;
        return mVH.baseView.getId();
    }

    /**创建布局控件*/
    protected abstract VH onCreateChildsViewHolder(Context context);

    /**获取当前common的名称，以区分不同的common*/
    public abstract String getName();

    /**ChildView 布局处理*/
    public static abstract class ViewHolder {

        protected View baseView;

        public ViewHolder(View view){
            baseView=view;
        }

        /**初始化控件*/
        protected abstract void initViews(View baseView);

        /**初始化控件*/
        protected  abstract void initEvent();

        /**初始化数据*/
        protected abstract void initData();

    };

    //bind view
    public class BindView{

        private ViewGroup mBindView;
        private ViewsCommon mCommon;

        public BindView(ViewGroup bindView, ViewsCommon common){
            mBindView=bindView;
            mCommon=common;
        }
    }

    //当parent remove掉当前ViewCommon的时候
    protected void onViewsCommonUnBind(){}

    //当parent add当前ViewCommon的时候
    protected void onViewsCommonBind(){};

}
