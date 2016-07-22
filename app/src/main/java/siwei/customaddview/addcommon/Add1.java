package siwei.customaddview.addcommon;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import siwei.customaddview.R;

/**
 * Created by siwei.zhao on 2016/7/11.
 */
public class Add1 extends AddViewHolderCommon {

    public static final int TAG_CHANGE_EDITTEXT_TEXT=1;//修改edittext的内容

    public static final int TAG_GET_EDITTEXT_TEXT=2;//获取edittext的内容

    public static final int TAG_EDITTEXT_DATA_CHANGE=3;//edittext的内容改变

    public EditText et;
    public Button btn;

    @Override
    public View getAddView(Context context, ViewGroup parentView) {
        return View.inflate(context, R.layout.layout1, null);
    }

    @Override
    public void initView(View baseView) {
        et= (EditText) baseView.findViewById(R.id.et);
        btn= (Button) baseView.findViewById(R.id.btn);
    }

    @Override
    public void initData() {
        et.setText("Add1 extends AddViewHolderCommon");
        onChildDataChange(TAG_CHANGE_EDITTEXT_TEXT, et.getText().toString());
    }

    @Override
    public void initEvent() {
        btn.setOnClickListener(mClickListener);
    }

    @Override
    public Object getViewData(int tag) {
        Object data=null;
        switch (tag){
            case TAG_GET_EDITTEXT_TEXT:
                data=et.getText().toString();
                break;
        }
        return data;
    }

    @Override
    public void setViewData(int tag, Object data) {
        switch (tag){
            case TAG_CHANGE_EDITTEXT_TEXT:
                et.setText(data.toString());
                break;
        }
    }

    View.OnClickListener mClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn:
                    System.out.println("===========btn click");
                    break;
            }
        }
    };

    @Override
    public void onBindParentViewAfter(ViewGroup parentView) {
        super.onBindParentViewAfter(parentView);
        System.out.println("=========================Add1 onBindParentViewAfter");
    }

    @Override
    public void onBindParentViewBefore(ViewGroup parentView, boolean parentRemoveChildView) {
        super.onBindParentViewBefore(parentView, parentRemoveChildView);
        System.out.println("=========================Add1 onBindParentViewBefore");
    }

    @Override
    public void onParentRemoveViewAfter(ViewGroup parentView) {
        super.onParentRemoveViewAfter(parentView);
        System.out.println("=========================Add1 onParentRemoveViewAfter");
    }

    @Override
    public void onParentRemoveViewBefore(ViewGroup parentView) {
        super.onParentRemoveViewBefore(parentView);
        System.out.println("=========================Add1 onParentRemoveViewBefore");
    }

    @Override
    public void bindViewClickListener(int viewid, Object viewListener) {
        super.bindViewClickListener(viewid, viewListener);
        if(viewListener instanceof View.OnClickListener)mBaseView.findViewById(viewid).setOnClickListener((View.OnClickListener) viewListener);
    }
}
