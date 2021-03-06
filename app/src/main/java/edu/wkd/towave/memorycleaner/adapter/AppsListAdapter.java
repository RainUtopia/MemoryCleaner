package edu.wkd.towave.memorycleaner.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.wkd.towave.memorycleaner.R;
import edu.wkd.towave.memorycleaner.adapter.base.BaseRecyclerViewAdapter;
import edu.wkd.towave.memorycleaner.adapter.viewholder.ProcessItemViewHolder;
import edu.wkd.towave.memorycleaner.model.AppInfo;
import edu.wkd.towave.memorycleaner.tools.StorageUtil;
import java.util.List;

/**
 * Created by towave on 2016/5/21.
 */
public class AppsListAdapter extends BaseRecyclerViewAdapter<AppInfo> {
    private Context mContext;


    public AppsListAdapter(List<AppInfo> list) {
        super(list);
    }


    public AppsListAdapter(List<AppInfo> list, Context context) {
        super(list, context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        final View view = LayoutInflater.from(mContext)
                                        .inflate(R.layout.item_list_view,
                                                parent, false);
        return new ProcessItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        ProcessItemViewHolder holder = (ProcessItemViewHolder) viewHolder;
        AppInfo appInfo = list.get(position);
        if (appInfo == null) return;
        holder.setIcon(appInfo.getAppIcon());
        holder.setName(appInfo.getAppName());
        holder.setMemory(
                StorageUtil.convertStorage(appInfo.getPkgSize()));
        holder.setCheckBoxVisible(false);
        animate(viewHolder, position);
    }


    @Override protected Animator[] getAnimators(View view) {
        if (view.getMeasuredHeight() <= 0) {
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX",
                    1.05f, 1.0f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY",
                    1.05f, 1.0f);
            return new ObjectAnimator[] { scaleX, scaleY };
        }
        return new Animator[] {
                ObjectAnimator.ofFloat(view, "scaleX", 1.05f, 1.0f),
                ObjectAnimator.ofFloat(view, "scaleY", 1.05f, 1.0f), };
    }
}
