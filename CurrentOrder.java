package com.example.user.juneyipuser.Customer;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by User on 11/3/2016.
 */

public class CurrentOrder extends userOrderListFragment {
    public CurrentOrder() {}

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // [START recent_Orders_query]
        // Last 100 Orders, these are automatically the 100 most recent
        // due to sorting by push() keys
        Query recentOrdersQuery = databaseReference.child("users").child(getUid())
                .limitToFirst(1);
        // [END recent_Orders_query]

        return recentOrdersQuery;
    }
}
