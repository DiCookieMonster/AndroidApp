package com.example.user.juneyipuser.HandyMan;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by User on 10/24/2016.
 */
public class ClosedPerformedJobs extends hmClosedOrdersListFragment   {

        public ClosedPerformedJobs() {}

        @Override
        public Query getQuery(DatabaseReference databaseReference) {
        // [START recent_Orders_query]
        // Last 100 Orders, these are automatically the 100 most recent
        // due to sorting by push() keys
            Query recentOrdersQuery = databaseReference.child("hm").child(getUid()).child("closed-orders").limitToFirst(20);
            //Query recentOrdersQuery = databaseReference.child("hm").child(getUid()).child("closed-orders").limitToFirst(20);
        // [END recent_Orders_query]

        return recentOrdersQuery;
    }
    }