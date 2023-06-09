package taxi.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import taxi.dao.CarDao;
import taxi.lib.Inject;
import taxi.lib.Service;
import taxi.model.Car;
import taxi.model.Driver;

@Service
public class CarServiceImpl implements CarService {
    private static final Logger logger = LogManager.getLogger(CarServiceImpl.class);
    @Inject
    private CarDao carDao;

    @Override
    public void addDriverToCar(Driver driver, Car car) {
        logger.info("addDriverToCar method was called. Params: driver_id = {}, car_id = {}",
                driver.getId(), car.getId());
        car.getDrivers().add(driver);
        carDao.update(car);
    }

    @Override
    public void removeDriverFromCar(Driver driver, Car car) {
        logger.info("removeDriverFromCar was called. Params: driver_id = {}, car_id = {}",
                driver.getId(), car.getId());
        car.getDrivers().remove(driver);
        carDao.update(car);
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        return carDao.getAllByDriver(driverId);
    }

    @Override
    public Car create(Car car) {
        logger.info("create method was called. Params: model = {}, manufacturer_id = {}",
                car.getModel(), car.getManufacturer().getId());
        return carDao.create(car);
    }

    @Override
    public Car get(Long id) {
        return carDao.get(id).orElseThrow(() -> {
            logger.error("Can't find car by id: " + id);
            return new NoSuchElementException("Can't get car by id: " + id);
        });
    }

    @Override
    public List<Car> getAll() {
        return carDao.getAll();
    }

    @Override
    public Car update(Car car) {
        logger.info("update method was called. Params: car_id = {}", car.getId());
        return carDao.update(car);
    }

    @Override
    public boolean delete(Long id) {
        logger.info("delete method was called. Params: car_id = {}", id);
        return carDao.delete(id);
    }
}
