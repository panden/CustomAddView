package siwei.customaddview.addviewcommon;

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
    protected List<ViewsCommon> mChilds;
    private ViewsCommon mParentView;

    /**绑定到父布局中*/
    public void bindParent(ViewsCommon parentCommon){
        mParentView=parentCommon;
        mVH=onCreateChildsViewHolder(parentCommon.getViewHolder().baseView.getContext());
        mVH.initViews(mVH.baseView);
        mVH.initEvent();
        mVH.initData();
        View parentView=parentCommon.getViewHolder().baseView;
        if(parentView instanceof ViewGroup){
            ViewGroup vp= (ViewGroup) parentView;
            vp.addView(mVH.baseView);
        }
    }

    /**从父布局中移除当前布局*/
    public void unBindParent(){
        View parentView=mParentView.getViewHolder().baseView;
        if(parentView instanceof ViewGroup){
            ViewGroup vp= (ViewGroup) parentView;
            vp.removeView(getViewHolder().baseView);
        }
    }

    public ViewsCommon getParent(){
        return mParentView;
    }

    public boolean addChildViews(ViewsCommon child){
        if(child==null)return false;
        child.bindParent(ViewsCommon.this);
        if(mChilds==null)mChilds=new ArrayList<ViewsCommon>();
        mChilds.add(child);
        return true;
    }

    public void removeChildView(ViewsCommon child){
        child.unBindParent();
    }

    public List<ViewsCommon> getChilds(){
        return mChilds;
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



}
