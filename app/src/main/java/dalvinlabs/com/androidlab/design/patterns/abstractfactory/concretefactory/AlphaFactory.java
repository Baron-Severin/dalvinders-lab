package dalvinlabs.com.androidlab.design.patterns.abstractfactory.concretefactory;


import dalvinlabs.com.androidlab.design.patterns.abstractfactory.AbstractCar;
import dalvinlabs.com.androidlab.design.patterns.abstractfactory.AbstractFactory;
import dalvinlabs.com.androidlab.design.patterns.abstractfactory.AbstractTruck;
import dalvinlabs.com.androidlab.design.patterns.abstractfactory.AbstractVan;
import dalvinlabs.com.androidlab.design.patterns.abstractfactory.concreteproducts.AlphaCar;
import dalvinlabs.com.androidlab.design.patterns.abstractfactory.concreteproducts.AlphaTruck;
import dalvinlabs.com.androidlab.design.patterns.abstractfactory.concreteproducts.AlphaVan;

public class AlphaFactory extends AbstractFactory {
    @Override
    public AbstractCar createCar() {
        return new AlphaCar();
    }

    @Override
    public AbstractTruck createTruck() {
        return new AlphaTruck();
    }

    @Override
    public AbstractVan createVan() {
        return new AlphaVan();
    }
}
