package siwei.customaddview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import siwei.customaddview.addcommon.Add1;
import siwei.customaddview.addcommon.Add2;
import siwei.customaddview.addcommon.AddViewHolderCommon;

public class MainActivity extends AppCompatActivity {

    private LinearLayout parent_view;
    private Button change_data_btn, load_btn1, load_btn2, remove_load_btn;
    private AddViewHolderCommon mAddView;
    private AddViewHolderCommon.BindParentView mBindParentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        parent_view= (LinearLayout) findViewById(R.id.parent_view);
        load_btn1= (Button) findViewById(R.id.load_btn1);
        load_btn2= (Button) findViewById(R.id.load_btn2);
        change_data_btn= (Button) findViewById(R.id.change_data_btn);
        remove_load_btn= (Button) findViewById(R.id.remove_load_btn);

        mBindParentView=new AddViewHolderCommon.BindParentView(parent_view);
        load_btn1.setOnClickListener(mClickListener);
        load_btn2.setOnClickListener(mClickListener);
        change_data_btn.setOnClickListener(mClickListener);
        remove_load_btn.setOnClickListener(mClickListener);

    }

    AddViewHolderCommon.OnChildDataChangeListener mChangeListener=new AddViewHolderCommon.OnChildDataChangeListener() {
        @Override
        public void onChildDataChange(int tag, Object changeData) {
            switch (tag){
                case Add1.TAG_EDITTEXT_DATA_CHANGE:
                    System.out.println("=====activity OnChildDataChangeListener Add1 data="+changeData.toString());
                    break;
                case Add2.TAG_EDITTEXT_DATA_CHANGE:
                    System.out.println("=====activity OnChildDataChangeListener Add2 data="+changeData.toString());
                    break;
            }
        }
    };

    View.OnClickListener mClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.load_btn1:
                    mAddView=new Add1();
                    mAddView.setOnChildDataChangeListener(mChangeListener);
                    mAddView.bindParentView(mBindParentView);
                    break;
                case R.id.load_btn2:
                    mAddView=new Add2();
                    mAddView.setOnChildDataChangeListener(mChangeListener);
                    mAddView.bindParentView(mBindParentView);
                    break;
                case R.id.change_data_btn:
                    mAddView.setViewData(mAddView instanceof Add1?Add1.TAG_CHANGE_EDITTEXT_TEXT:Add2.TAG_CHANGE_EDITTEXT_TEXT, "Activity里修改数据");
                    break;
                case R.id.remove_load_btn:
                    //removeview这样调用，才会执行AddViewHolderCommon中的onParentRemoveViewAfter、onParentRemoveViewBefore
                    mBindParentView.removeAllChildViews();
                    break;
            }
        }
    };

    

}
