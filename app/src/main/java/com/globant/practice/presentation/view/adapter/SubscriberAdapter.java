package com.globant.practice.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.globant.practice.R;
import com.globant.practice.domain.model.User;
import java.util.List;

/**
 * Adapter of the RecyclerView on HomeActivity
 * Created by jonathan.vargas on 17/04/2017.
 */
public class SubscriberAdapter extends RecyclerView.Adapter<SubscriberAdapter.SubscriberViewHolder> {

    /**
     * Provides the manager of the user clicks on the RecyclerView
     */
    public interface OnUserClickListener {

        /**
         * Manages the user clicks
         *
         * @param user User instance of the object that the user makes click
         */
        void onUserClick(User user);
    }

    private List<User> users;
    private OnUserClickListener onUserClickListener;
    private Context context;

    /**
     * Construct method of the SubscriberAdapter
     *
     * @param users               user list instance
     * @param onUserClickListener OnUserClickListener instance
     */
    public SubscriberAdapter(List<User> users, OnUserClickListener onUserClickListener) {
        this.users = users;
        this.onUserClickListener = onUserClickListener;
    }

    /**
     * Set the values of the user list
     *
     * @param users user list
     */
    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    /**
     * Creates a new ViewHolder to represent a item
     *
     * @param parent   ViewGroup on the new View will be added
     * @param viewType The view type of the new View
     * @return New ViewHolder
     */
    @Override
    public SubscriberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subscriber_list_item, parent, false);
        SubscriberViewHolder listHomeViewHolder = new SubscriberViewHolder(itemView);
        context = parent.getContext();
        return listHomeViewHolder;
    }

    /**
     * Updates the data of item at the position given
     *
     * @param holder   ViewHolder of the RecyclerView
     * @param position Position of the item
     */
    @Override
    public void onBindViewHolder(SubscriberViewHolder holder, int position) {
        User user = users.get(position);
        holder.blindSubscriberList(user);
    }

    /**
     * Returns the size of the list items
     *
     * @return Size of the list
     */
    @Override
    public int getItemCount() {
        return users == null ? 0 : users.size();
    }

    /**
     * Inner class that manages the items of the view
     */
    public class SubscriberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nickname;
        private ImageView avatar;
        private User user;

        /**
         * Construct method of the SubscriberViewHolder that initializes the view items
         *
         * @param itemView
         */
        public SubscriberViewHolder(View itemView) {
            super(itemView);
            nickname = (TextView) itemView.findViewById(R.id.userHomeTxt);
            avatar = (ImageView) itemView.findViewById(R.id.userHomeImg);
            itemView.setOnClickListener(this);
        }

        /**
         * Sets the data on the view item.
         *
         * @param user User information
         */
        public void blindSubscriberList(final User user) {
            nickname.setText(user.getLogin());
            this.user = user;
            Glide.with(context).load(user.getAvatarUrl()).centerCrop()
                    .placeholder(R.drawable.ic_account_circle_black_24dp).crossFade().into(avatar);
        }

        /**
         * Manages the user clicks
         *
         * @param view view instance of the object that the user makes click
         */
        @Override
        public void onClick(View view) {
            if (onUserClickListener != null) {
                onUserClickListener.onUserClick(user);
            }
        }
    }
}
