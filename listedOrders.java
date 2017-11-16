package com.example.user.juneyipuser.HandyMan;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by User on 11/5/2016.
 */

public class listedOrders extends allListedOrdersListFragment {
    public listedOrders() {}

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // [START recent_Orders_query]
        // Last 100 Orders, these are automatically the 100 most recent
        // due to sorting by push() keys
        Query recentOrdersQuery = databaseReference.child("all-orders").limitToFirst(30);
        // [END recent_Orders_query]

        return recentOrdersQuery;
    }
}