package com.example.user.juneyipuser.Customer;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by User on 10/12/2016.
 */

public class MyOrders extends userClosedOrdersListFragment {

    public MyOrders() {}

    public Query getQuery(DatabaseReference databaseReference) {
        // [START recent_Orders_query]
        // Last 100 Orders, these are automatically the 100 most recent
        // due to sorting by push() keys

        Query recentOrdersQuery = databaseReference.child("all-orders").limitToFirst(20);
        // [END recent_Orders_query]

        return recentOrdersQuery;
    }
}