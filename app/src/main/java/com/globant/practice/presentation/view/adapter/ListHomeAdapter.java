package com.globant.practice.presentation.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.globant.practice.R;
import com.globant.practice.domain.model.User;
import java.util.List;

/**
 * Adapter of the RecyclerView on HomeActivity
 * Created by jonathan.vargas on 17/04/2017.
 */
public class ListHomeAdapter extends RecyclerView.Adapter<ListHomeAdapter.ListHomeViewHolder>
        implements View.OnClickListener {
    private View.OnClickListener listener;
    private List<User> users;

    /**
     * Setter of the users list
     *
     * @param users List of user
     */
    public ListHomeAdapter(List<User> users) {
        this.users = users;
    }

    /**
     * Creates a new ViewHolder to represent a item
     *
     * @param parent   ViewGroup on the new View will be added
     * @param viewType The view type of the new View
     * @return New ViewHolder
     */
    @Override
    public ListHomeAdapter.ListHomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_home_item, parent, false);
        itemView.setOnClickListener(this);
        ListHomeViewHolder listHomeViewHolder = new ListHomeViewHolder(itemView);
        return listHomeViewHolder;
    }

    /**
     * Updates the data of item at the position given
     *
     * @param holder   ViewHolder of the RecyclerView
     * @param position Position of the item
     */
    @Override
    public void onBindViewHolder(ListHomeAdapter.ListHomeViewHolder holder, int position) {
        User user = users.get(position);
        holder.blindListHome(user);
    }

    /**
     * Returns the size of the list items
     *
     * @return Size of the list
     */
    @Override
    public int getItemCount() {
        return users.size();
    }

    /**
     * Setter of the RecyclerView click listener
     *
     * @param listener OnClickListener of the RecyclerView
     */
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    /**
     * Manages the actions when the user makes a click
     *
     * @param v View reference
     */
    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    /**
     * Inner class that manages the items of the view
     */
    public static class ListHomeViewHolder extends RecyclerView.ViewHolder {
        private TextView nickname;
        private ImageView avatar;

        /**
         * Construct method of the ListHomeHolder that initializes the view items
         *
         * @param itemView
         */
        public ListHomeViewHolder(View itemView) {
            super(itemView);
            nickname = (TextView) itemView.findViewById(R.id.userHomeTxt);
            avatar = (ImageView) itemView.findViewById(R.id.userHomeImg);
        }

        /**
         * Sets the data on the view item.
         *
         * @param user User information
         */
        public void blindListHome(User user) {
            nickname.setText(user.getLogin());
        }
    }
}
