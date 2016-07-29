package siwei.customaddview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import siwei.customaddview.addcommon.BaseParentViewsCommon;
import siwei.customaddview.games.GameA;
import siwei.customaddview.games.viewplugin.PluginA;

//把View进行模块化开发，动态添加View去实现胡曲的那个鬼项目。
public class GameActivity extends AppCompatActivity {

    private LinearLayout add_game_ll;
    private GameA mGameA;
    private BaseParentViewsCommon mBaseParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        add_game_ll= (LinearLayout) findViewById(R.id.add_game_ll);
        mGameA=new GameA();
        mBaseParent=new BaseParentViewsCommon(add_game_ll);
        mBaseParent.addChildViews(mBaseParent.getBindView(add_game_ll), mGameA);

        PluginA pluginA = (PluginA) mBaseParent.getChild(GameA.NAME).getChild(GameA.GAME_VIEW_PLUGINA);
        PluginA.PluginAHolder holder = pluginA.getViewHolder();
        System.out.println("=============这是plugin a view 里的gridview="+holder.plugina_gv);

        GameA.GameAViewHolder gameAViewHolder = (GameA.GameAViewHolder) mBaseParent.getChild(GameA.NAME).getViewHolder();
        gameAViewHolder.title_tv.append("  这条数据是在GameActivity里进行修改的");
    }
}
