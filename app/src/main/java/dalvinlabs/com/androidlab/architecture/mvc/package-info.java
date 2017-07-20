package dalvinlabs.com.androidlab.architecture.mvc;
/*
    Model : Business logic and responsible to get data.
    View : XML layout files
    Controller : Activity
        1. Setup view
        2. Get notified for view clicks
        3. Glue between View and Model
        Cons: Tightly coupled with view, if view gets changed so does controller. Can't be unit tested.
 */