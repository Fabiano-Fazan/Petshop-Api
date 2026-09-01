package com.petshop.api.domain.medicalAppointment;

import com.petshop.api.exception.BusinessException;
import com.petshop.api.model.entities.Animal;
import com.petshop.api.model.entities.Client;
import org.springframework.stereotype.Component;

@Component
public class AppointmentRelationshipValidator {

    public void validateAnimalBelongsToClient(
            Animal animal,
            Client client
    ) {
        if (animal == null
                || animal.getClient() == null
                || !animal.getClient().getId().equals(client.getId())) {

            throw new BusinessException(
                    "The animal does not belong to the informed client."
            );
        }
    }
}