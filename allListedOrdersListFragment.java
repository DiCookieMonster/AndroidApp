
package com.example.user.juneyipuser.HandyMan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.juneyipuser.Order.Order;
import com.example.user.juneyipuser.Order.OrderViewHolder;
import com.example.user.juneyipuser.Order.showOrderSum;
import com.example.user.juneyipuser.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * Created by User on 11/5/2016.
 */

public abstract class allListedOrdersListFragment extends Fragment {


    // [START define_database_reference]
    private DatabaseReference mRootDB;
    // [END define_database_reference]

    private FirebaseRecyclerAdapter<Order, OrderViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    public allListedOrdersListFragment() {}

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.all_orders, container, false);

        // [START create_database_reference]
        mRootDB = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        mRecycler = (RecyclerView) rootView.findViewById(R.id.messages_list);
        mRecycler.setHasFixedSize(true);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up Layout Manager, reverse layout
        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query orderQuery = getQuery(mRootDB);
        mAdapter = new FirebaseRecyclerAdapter<Order, OrderViewHolder>(Order.class, R.layout.item_order,
                OrderViewHolder.class, orderQuery) {
            @Override
            protected void populateViewHolder(final OrderViewHolder viewHolder, final Order model, final int position) {
                final DatabaseReference orderRef = getRef(position);

                // Set click listener for the whole order view
                final String postKey = orderRef.getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Launch orderDetailActivity
                        Intent intent = new Intent(getActivity(), showOrderSum.class);
                        intent.putExtra(showOrderSum.EXTRA_POST_KEY, postKey);
                        startActivity(intent);
                    }
                });

            }
        };
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }

    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public abstract Query getQuery(DatabaseReference databaseReference);

}
