package com.example.boris.mamafoodjob.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.boris.mamafoodjob.Model.User;
import com.example.boris.mamafoodjob.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class recyclerAdapterMain extends RecyclerView.Adapter<recyclerAdapterMain.MyHolder> {

    List<User> users;
    Context context;
    public recyclerAdapterMain(List<User> user, Context context) {
        this.users = user;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int i) {
        holder.title.setText(users.get(i).getName());
        holder.description.setText(users.get(i).getDescription());
        holder.date.setText(users.get(i).getDate() + " " + users.get(i).getAdress());
        Picasso.with(context).load(users.get(i).getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title, description, date;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recycler_image);
            title = itemView.findViewById(R.id.recycler_title);
            description = itemView.findViewById(R.id.recycler_description);
            date = itemView.findViewById(R.id.dateOrder);

        }
    }

}
