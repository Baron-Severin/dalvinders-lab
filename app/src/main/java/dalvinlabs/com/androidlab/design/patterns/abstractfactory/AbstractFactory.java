package dalvinlabs.com.androidlab.design.patterns.abstractfactory;

import dalvinlabs.com.androidlab.design.patterns.abstractfactory.concretefactory.AlphaFactory;
import dalvinlabs.com.androidlab.design.patterns.abstractfactory.concretefactory.BetaFactory;
import dalvinlabs.com.androidlab.design.patterns.abstractfactory.concretefactory.GammaFactory;

public abstract class AbstractFactory {
    public enum TYPE {
        ALPHA,
        BETA,
        GAMMA
    }

    public static AbstractFactory createFactory(TYPE type) {
        AbstractFactory abstractFactory = null;
        switch (type) {
            case ALPHA:
                abstractFactory = new AlphaFactory();
                break;
            case BETA:
                abstractFactory = new BetaFactory();
                break;
            case GAMMA:
                abstractFactory = new GammaFactory();
                break;
        }
        return abstractFactory;
    }

    public abstract AbstractCar createCar();
    public abstract AbstractTruck createTruck();
    public abstract AbstractVan createVan();
}
