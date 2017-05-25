package dalvinlabs.com.androidlab.design.patterns.abstractfactory.concretefactory;


import dalvinlabs.com.androidlab.design.patterns.abstractfactory.AbstractCar;
import dalvinlabs.com.androidlab.design.patterns.abstractfactory.AbstractFactory;
import dalvinlabs.com.androidlab.design.patterns.abstractfactory.AbstractTruck;
import dalvinlabs.com.androidlab.design.patterns.abstractfactory.AbstractVan;
import dalvinlabs.com.androidlab.design.patterns.abstractfactory.concreteproducts.BetaCar;
import dalvinlabs.com.androidlab.design.patterns.abstractfactory.concreteproducts.BetaTruck;
import dalvinlabs.com.androidlab.design.patterns.abstractfactory.concreteproducts.BetaVan;

public class BetaFactory extends AbstractFactory {
    @Override
    public AbstractCar createCar() {
        return new BetaCar();
    }

    @Override
    public AbstractTruck createTruck() {
        return new BetaTruck();
    }

    @Override
    public AbstractVan createVan() {
        return new BetaVan();
    }
}
