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
import com.globant.practice.domain.model.Profile;
import com.globant.practice.domain.model.Repository;
import java.util.List;

/**
 * Adapter for the SubscriberDetailsRecyclerView
 * Created by jonathan.vargas on 27/04/2017.
 */

public class SubscriberDetailsAdapter extends RecyclerView.Adapter<SubscriberDetailsAdapter.SubscriberDetailsViewHolder> {

    /**
     * Provides the two different types of clicks that the RecyclerView can manage.
     */
    public interface OnUserClickListener {
        /**
         * Manages the actions when the user makes click on the name of the subscriber
         *
         * @param htmlUrl contains the url to the GitHub profile
         */
        void onProfileNameClick(String htmlUrl);

        /**
         * Manages the actions when the user makes click on a repository item
         *
         * @param htmlUrl contains the url to the GitHub repository
         */
        void onRepositoryClick(String htmlUrl);
    }

    private List<Repository> subscriberRepositories;
    private Profile profile;
    private SubscriberDetailsAdapter.OnUserClickListener onUserClickListener;
    private Context context;

    /**
     * Construct method of the SubscriberAdapter
     *
     * @param profile                profile of the subscriber
     * @param subscriberRepositories repository list of the subscriber
     * @param onUserClickListener    manager of the user clicks
     */
    public SubscriberDetailsAdapter(Profile profile, List<Repository> subscriberRepositories, SubscriberDetailsAdapter.OnUserClickListener onUserClickListener) {
        this.subscriberRepositories = subscriberRepositories;
        this.profile = profile;
        this.onUserClickListener = onUserClickListener;
    }

    /**
     * Sets and update the subscriber details date on the RecyclerView
     *
     * @param profile                subscriber profile
     * @param subscriberRepositories subscriber repository list
     */
    public void setSubscriberDetails(Profile profile, List<Repository> subscriberRepositories) {
        this.subscriberRepositories = subscriberRepositories;
        this.profile = profile;
        notifyDataSetChanged();
    }

    /**
     * Returns a new instance of the view depending of the viewType
     *
     * @param parent   ViewGroup instance
     * @param viewType viewType value
     * @return new view instance
     */
    @Override
    public SubscriberDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        if (viewType == 0) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.subscriber_profile_item, parent, false);
            SubscriberDetailsViewHolder subscriberDetailsViewHolder = new SubscriberDetailsAdapter.SubscriberDetailsViewHolder(itemView);
            return subscriberDetailsViewHolder;
        } else {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.subscriber_repositories_item, parent, false);
            SubscriberDetailsViewHolder subscriberDetailsViewHolder = new SubscriberDetailsAdapter.SubscriberDetailsViewHolder(itemView);
            return subscriberDetailsViewHolder;
        }
    }

    /**
     * Calls the blindSubscriberDetails depending of the position of the SubscriberDetailsViewHolder
     *
     * @param holder   SubscriberDetailsViewHolder
     * @param position position of the SubscriberDetailsViewHolder
     */
    @Override
    public void onBindViewHolder(SubscriberDetailsViewHolder holder, int position) {
        if (position == 0) {
            holder.blindSubscriberDetails();
        } else {
            holder.blindSubscriberDetails(subscriberRepositories.get(position - 1));
        }
    }

    /**
     * Returns the number of the items that will be displayed on the RecyclerView
     *
     * @return number of items
     */
    @Override
    public int getItemCount() {
        return subscriberRepositories == null ? 0 : subscriberRepositories.size() + 1;
    }

    /**
     * Returns the ViewType depending of the position of the view
     *
     * @param position position of the view
     * @return 0 for the subscriber profile item and 1 for the subscriber repository item
     */
    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 0 : 1;
    }

    /**
     * Inner class that manages the items of the view
     */
    public class SubscriberDetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private TextView location;
        private TextView company;
        private TextView followers;
        private TextView following;
        private TextView publicRepos;
        private ImageView avatar;
        private Repository repository;

        /**
         * Construct method of the SubscriberDetailsViewHolder, initializes the view items depending of
         * the tag of the view
         *
         * @param itemView view item
         */
        public SubscriberDetailsViewHolder(View itemView) {
            super(itemView);
            if (itemView.getTag().equals(R.string.subscriber_details_profile_layout_tag)) {
                avatar = (ImageView) itemView.findViewById(R.id.subscriber_profile_avatar);
                name = (TextView) itemView.findViewById(R.id.subscriber_details_name);
                location = (TextView) itemView.findViewById(R.id.subscriber_details_location);
                company = (TextView) itemView.findViewById(R.id.subscriber_details_company);
                followers = (TextView) itemView.findViewById(R.id.subscriber_details_followers);
                following = (TextView) itemView.findViewById(R.id.subscriber_details_following);
                publicRepos = (TextView) itemView.findViewById(R.id.subscriber_details_publicrepos);
            } else {
                name = (TextView) itemView.findViewById(R.id.subscriber_details_repo_name);
                itemView.setOnClickListener(this);
            }
        }

        /**
         * Sets the subscriber profile data on the view items
         */
        public void blindSubscriberDetails() {
            Glide.with(context).load(profile.getAvatarUrl()).centerCrop()
                    .placeholder(R.drawable.ic_account_circle_black_24dp).crossFade().into(avatar);
            if (profile.getName().isEmpty()) {
                name.setText(profile.getLogin());
            } else {
                name.setText(profile.getName());
            }
            location.setText(profile.getLocation());
            company.setText(profile.getCompany());
            followers.setText(String.valueOf(profile.getFollowers()));
            following.setText(String.valueOf(profile.getFollowing()));
            publicRepos.setText(String.valueOf(profile.getPublicRepos()));
            name.setOnClickListener(this);
        }

        /**
         * Sets the subscriber repository data on the view items
         *
         * @param repository
         */
        public void blindSubscriberDetails(Repository repository) {
            this.repository = repository;
            name.setText(repository.getName());
        }

        /**
         * Manages the user clicks making on the RecyclerView
         *
         * @param view view item on the user makes click
         */
        @Override
        public void onClick(View view) {
            if (view.getTag().equals(R.string.subscriber_details_layout_tag)) {
                if (onUserClickListener != null) {
                    onUserClickListener.onRepositoryClick(repository.getHtmlUrl());
                }
            } else if (view.getTag().equals(R.string.subscriber_details_name_txt_tag)) {
                if (onUserClickListener != null) {
                    onUserClickListener.onProfileNameClick(profile.getHtmlUrl());
                }
            }
        }
    }
}
