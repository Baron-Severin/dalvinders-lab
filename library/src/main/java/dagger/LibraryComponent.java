package dagger;

import com.example.interfaces.Component;

import javax.inject.Singleton;

@Singleton
@dagger.Component(modules = {LibraryModule.class})
public interface LibraryComponent extends Component {

    LibraryApi libraryApi();

    // It essentially means inject dependencies into DaggerConsumer class if this method is invoked.
    void inject(LibraryActivity consumer);
}
