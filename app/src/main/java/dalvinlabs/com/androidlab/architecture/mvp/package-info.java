package dalvinlabs.com.androidlab.architecture.mvp;

/*
    Model : Business logic and responsible to get data. Same as MVC
    View : Activity and XML layout files. To keep View independent of presenters activity implements
    predefined view interface.
    Presenter :
        1. Independent of Android and View code.
        2. Defined as an interface.
        3. Concrete presenters implements Presenter interface.
        Cons: Over the time Presenter can accumulate business logic, so careful refactoring is recommended.

    Unit Testing
        1. View can be unit tested by mocking the presenter.
        2. Presenter can be unit tested as it does not use any android specific views or API.
        3. Model can be unit tested similar to MVC.
 */