package siwei.customaddview.games;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import siwei.customaddview.R;
import siwei.customaddview.addcommon.ViewsCommon;
import siwei.customaddview.games.viewplugin.PluginA;

/**
 * Created by siwei.zhao on 2016/7/22.
 * 这是游戏A的类,里面包含view和业务逻辑
 */
public class GameA extends ViewsCommon<GameA.GameAViewHolder> {

    public static final String NAME="GameA";
    public static final String GAME_VIEW_PLUGINA="game_view_plugina";

    @Override
    protected GameAViewHolder onCreateChildsViewHolder(Context context) {
        return new GameAViewHolder(View.inflate(context, R.layout.gamea_layout, null));
    }

    @Override
    public String getName() {
        return NAME;
    }

    public class GameAViewHolder extends ViewsCommon.ViewHolder{

        public TextView title_tv, game_rules_tv;
        public Button start_game_btn;
        public LinearLayout add_game_ll;

        public PluginA mPluginA;
        public List<String> mPluginADatas;

        public GameAViewHolder(View view) {
            super(view);
        }

        @Override
        protected void initViews(View baseView) {
            title_tv= (TextView) baseView.findViewById(R.id.title_tv);
            game_rules_tv= (TextView) baseView.findViewById(R.id.game_rules_tv);
            start_game_btn= (Button) baseView.findViewById(R.id.start_game_btn);
            add_game_ll= (LinearLayout) baseView.findViewById(R.id.add_game_ll);
        }

        @Override
        protected void initEvent() {
            start_game_btn.setOnClickListener(mClickListener);
        }

        @Override
        protected void initData() {
            game_rules_tv.setText("这是Game A的游戏规则！");
            BindView add_game_ll_bind=new BindView(add_game_ll, GameA.this);
            mPluginADatas=new ArrayList<>();
            mPluginADatas.add("aaaaa");
            mPluginADatas.add("bbbb");
            mPluginADatas.add("cccc");
            mPluginADatas.add("dddd");
            mPluginA=new PluginA(mPluginADatas, GAME_VIEW_PLUGINA, baseView.getContext());
            addChildViews(add_game_ll_bind, mPluginA);
        }

    }

    View.OnClickListener mClickListener=new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.start_game_btn:
                    getViewHolder().mPluginA.getViewHolder().plugina_gv.setVisibility(View.GONE);
                    getViewHolder().game_rules_tv.setText("游戏执行完成!!!");
                    break;
            }
        }
    };
}
