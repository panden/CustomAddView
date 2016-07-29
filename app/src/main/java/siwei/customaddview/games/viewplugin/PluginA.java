package siwei.customaddview.games.viewplugin;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

import siwei.customaddview.R;
import siwei.customaddview.addcommon.ViewsCommon;

/**
 * Created by siwei.zhao on 2016/7/29.
 */
public class PluginA extends ViewsCommon<PluginA.PluginAHolder>{

    private List<String> mDatas;
    private String mPluginName;
    private Context mContext;

    public PluginA(List<String> datas, String pluginName, Context context){
        this.mDatas=datas;
        this.mPluginName=pluginName;
        mContext=context;
    }


    @Override
    protected PluginAHolder onCreateChildsViewHolder(Context context) {
        return new PluginAHolder(View.inflate(context, R.layout.plugina_layout, null));
    }

    @Override
    public String getName() {
        return mPluginName;
    }

    public class PluginAHolder extends ViewsCommon.ViewHolder{

        public GridView plugina_gv;
        public PluginAAdapter mAdapter;

        public PluginAHolder(View view) {
            super(view);
        }

        @Override
        protected void initViews(View baseView) {
            plugina_gv= (GridView) baseView.findViewById(R.id.plugina_gv);
        }

        @Override
        protected void initEvent() {

        }

        @Override
        protected void initData() {
            mAdapter=new PluginAAdapter(PluginA.this.mContext, PluginA.this.mDatas);
            plugina_gv.setAdapter(mAdapter);
        }
    }

    private class PluginAAdapter extends BaseAdapter{

        private Context mContext;
        private List<String> mDatas;

        public PluginAAdapter(Context context, List<String> datas){
            mContext=context;
            mDatas=datas;
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                convertView=View.inflate(mContext, R.layout.plugina_item_layout, null);
                holder=new ViewHolder(convertView);
                convertView.setTag(holder);
            }
            holder= (ViewHolder) convertView.getTag();
            holder.show_num_tv.setText(String.valueOf(position+1));
            holder.show_name_tv.setText(mDatas.get(position));
            return convertView;
        }

        private class ViewHolder{
            TextView show_num_tv,show_name_tv;

            public ViewHolder(View view){
                show_name_tv= (TextView) view.findViewById(R.id.show_name_tv);
                show_num_tv= (TextView) view.findViewById(R.id.show_num_tv);
            }
        }
    }
}
