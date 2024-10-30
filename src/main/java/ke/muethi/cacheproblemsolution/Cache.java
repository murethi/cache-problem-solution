package ke.muethi.cacheproblemsolution;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Cache (LocalDateTime ttl, Object value){

}
