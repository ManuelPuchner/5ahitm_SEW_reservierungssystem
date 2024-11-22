package at.htlleonding.dto;

import java.time.LocalTime;
import java.util.Date;

public record ReservationDTO(Long id, LocalTime start, LocalTime end, Date timestamp, String field) {

}
