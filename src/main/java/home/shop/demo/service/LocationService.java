package home.shop.demo.service;

import home.shop.demo.domain.Location;
import home.shop.demo.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocation(String locationId) {
        return locationRepository.findOne(locationId);
    }

    public void addLocation(Location location) {
        locationRepository.save(location);
    }

    public void deleteLocation(String locationId) {
        locationRepository.delete(locationId);
    }


}
