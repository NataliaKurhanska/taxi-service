package taxi.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import taxi.dao.DriverDao;
import taxi.lib.Inject;
import taxi.lib.Service;
import taxi.model.Driver;

@Service
public class DriverServiceImpl implements DriverService {
    private static final Logger logger = LogManager.getLogger(DriverServiceImpl.class);
    @Inject
    private DriverDao driverDao;

    @Override
    public Driver create(Driver driver) {
        logger.info("create method was called. Params: "
                + "driver_name ={}, driver_license = {}, driver_login = {}",
                driver.getName(), driver.getLicenseNumber(), driver.getLogin());
        return driverDao.create(driver);
    }

    @Override
    public Driver get(Long id) {
        return driverDao.get(id).orElseThrow(() -> {
            logger.error("Can't get driver by id: " + id);
            return new NoSuchElementException("Can't get driver by id: " + id);
        });
    }

    @Override
    public List<Driver> getAll() {
        return driverDao.getAll();
    }

    @Override
    public Driver update(Driver driver) {
        logger.info("update method was called. Params: driver_id = {}", driver.getId());
        return driverDao.update(driver);
    }

    @Override
    public boolean delete(Long id) {
        logger.info("delete method was called. Params: driver_id = {}" + id);
        return driverDao.delete(id);
    }

    @Override
    public Optional<Driver> findByLogin(String login) {
        return driverDao.findByLogin(login);
    }
}
