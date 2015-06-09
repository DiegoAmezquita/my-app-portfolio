package com.diegoamezquita.portfolio.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.diegoamezquita.portfolio.R;
import com.diegoamezquita.portfolio.models.App;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private ArrayList<App> arrayApps;
    private HomeItemListener homeItemListener;

    public HomeAdapter(@NonNull ArrayList<App> arrayApps) {
        this.arrayApps = arrayApps;
    }

    public void setHomeItemListener(HomeItemListener homeItemListener) {
        this.homeItemListener = homeItemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_home, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final App app = arrayApps.get(position);
        viewHolder.setImage(app.getDrawable());
        viewHolder.setTitle(app.getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (homeItemListener != null) {
                    homeItemListener.onHomeItemClicked(app);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayApps.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewIcon;
        private TextView textViewTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView_icon);
            textViewTitle = (TextView) itemView.findViewById(R.id.textView_title);

        }

        public void setTitle(@NonNull String title) {
            textViewTitle.setText(title);
        }

        public void setImage(int drawable) {
            imageViewIcon.setImageResource(drawable);
        }
    }

    public interface HomeItemListener {

        public void onHomeItemClicked(App appClicked);
    }

}
