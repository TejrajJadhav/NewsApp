package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.newsapp.R;

import java.util.ArrayList;
import java.util.List;

import Entity.MainSourceData;

/**
 * Created by Tejraj on 18-Mar-19.
 */

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.SourceViewHolder> {
    private Context mContex;
    private List<MainSourceData> sourceListData = new ArrayList<>();
    private static SourceAdapter.OnItemClickListener itemClickListener;

    public SourceAdapter(Context mContex, List<MainSourceData> sourceListData, OnItemClickListener onItemClickListener) {
        this.mContex = mContex;
        this.sourceListData = sourceListData;
        this.itemClickListener = onItemClickListener;

        Log.e("lisrt : ","- "+new Gson().toJson(sourceListData));
    }

    @Override
    public SourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.source_home_item, parent, false);
        return new SourceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SourceViewHolder holder, final int position) {
        MainSourceData mainSourceData = sourceListData.get(position);

        Log.e("name data: "," - "+mainSourceData.name);

        holder.tv_name.setText(mainSourceData.name);
        holder.tv_desc.setText(mainSourceData.description);
        holder.tv_category.setText(mainSourceData.category);
        holder.tv_lang.setText(mainSourceData.language);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               itemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {

        Log.e("sourceListData size: "," - "+sourceListData.size());
        return sourceListData.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class SourceViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name, tv_desc, tv_category, tv_lang;

        public SourceViewHolder(View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            tv_category = itemView.findViewById(R.id.tv_category);
            tv_lang = itemView.findViewById(R.id.tv_lang);

        }
    }
}
