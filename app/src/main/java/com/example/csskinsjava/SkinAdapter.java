package com.example.csskinsjava;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class SkinAdapter extends RecyclerView.Adapter<SkinAdapter.SkinViewHolder> {
    private List<Skin> skinList;

    public SkinAdapter(List<Skin> skinList) {
        this.skinList = skinList;
    }

    @NonNull
    @Override
    public SkinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_skin, parent, false);
        return new SkinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkinViewHolder holder, int position) {
        Skin skin = skinList.get(position);
        holder.textViewName.setText(skin.getName()); // Use textViewName here
    }

    @Override
    public int getItemCount() {
        return skinList.size();
    }

    public static class SkinViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName; // Correct the variable name

        public SkinViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName); // Correct the ID here
        }
    }
}
