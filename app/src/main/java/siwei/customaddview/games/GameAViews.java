package siwei.customaddview.games;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import siwei.customaddview.R;
import siwei.customaddview.addviewcommon.ViewsCommon;

/**
 * Created by siwei.zhao on 2016/7/22.
 */
public class GameAViews extends ViewsCommon<GameAViews.GameAViewHolder> {

    @Override
    protected GameAViewHolder onCreateChildsViewHolder(Context context) {
        return new GameAViewHolder(View.inflate(context, R.layout.gamea_layout, null));
    }

    public class GameAViewHolder extends ViewsCommon.ViewHolder{

        public TextView title_tv, game_rules_tv;
        public Button start_game_btn;


        public GameAViewHolder(View view) {
            super(view);
        }

        @Override
        protected void initViews(View baseView) {
            title_tv= (TextView) baseView.findViewById(R.id.title_tv);
            game_rules_tv= (TextView) baseView.findViewById(R.id.game_rules_tv);
            start_game_btn= (Button) baseView.findViewById(R.id.start_game_btn);
        }

        @Override
        protected void initEvent() {

        }

        @Override
        protected void initData() {
            title_tv.setText("这是Game A！");
        }

    }
}
