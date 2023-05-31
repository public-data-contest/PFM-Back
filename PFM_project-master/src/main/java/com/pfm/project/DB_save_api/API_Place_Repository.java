package com.pfm.project.DB_save_api;

import com.pfm.project.domain.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface API_Place_Repository extends JpaRepository<Place, Long> {
}
