package uz.rootec.appcovidserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.rootec.appcovidserver.entity.AnalisePlace;

import java.util.List;
import java.util.UUID;

/**
 * Created by Sherlock on 04.09.2021.
 */
public interface AnalisePlaceRepository extends JpaRepository<AnalisePlace, UUID> {
    List<AnalisePlace> findAllByOrderByCreatedAtDesc();
}
