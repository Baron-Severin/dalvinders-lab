package dalvinlabs.com.androidlab.design.patterns.abstractfactory.concretefactory;


import dalvinlabs.com.androidlab.design.patterns.abstractfactory.AbstractCar;
import dalvinlabs.com.androidlab.design.patterns.abstractfactory.AbstractFactory;
import dalvinlabs.com.androidlab.design.patterns.abstractfactory.AbstractTruck;
import dalvinlabs.com.androidlab.design.patterns.abstractfactory.AbstractVan;
import dalvinlabs.com.androidlab.design.patterns.abstractfactory.concreteproducts.GammaCar;
import dalvinlabs.com.androidlab.design.patterns.abstractfactory.concreteproducts.GammaTruck;
import dalvinlabs.com.androidlab.design.patterns.abstractfactory.concreteproducts.GammaVan;

public class GammaFactory extends AbstractFactory {

    @Override
    public AbstractCar createCar() {
        return new GammaCar();
    }

    @Override
    public AbstractTruck createTruck() {
        return new GammaTruck();
    }

    @Override
    public AbstractVan createVan() {
        return new GammaVan();
    }
}
