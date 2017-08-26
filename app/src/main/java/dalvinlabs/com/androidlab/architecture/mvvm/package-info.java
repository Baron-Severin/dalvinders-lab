/*
    Model : Business logic and responsible to get data. Same as MVC
    View : Activity and XML layout files.
        1. XML uses data binding to observer view model fields.
    ViewModel :
        1. Independent of Android and View code.
        Cons: Over the time View Model can accumulate business logic, so careful refactoring is recommended.

    Unit Testing
        1. No need to test view xml files
        2. View Model observable fields can be unit tested by mocking model.
        3. Model can be unit tested similar to MVP and MVC.
 */