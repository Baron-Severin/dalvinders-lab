package dagger;

import javax.inject.Singleton;

@Module
public class LibraryModule {

    @Provides
    @Singleton
    LibraryApi provideLibraryApi() {
        return new LibraryApi();
    }
}
