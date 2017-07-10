package dalvinlabs.com.androidlab.architecture.mvp;

/*
    Model : Business logic and responsible to get data. Same as MVC
    View : Activity and XML layout files. To keep View independent of presenters activity implements pre
        defined view interface.
    Presenter :
        1. Independent of Android and View code.
        2. Defined as an interface.
        3. Concrete presenters implements presenter interface.
        Cons: Over the time presenter can accumulate busines logic, so careful refactoring is recommended.
 */