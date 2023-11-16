package ktoda.dev.swiftly.exception;

import java.time.ZonedDateTime;

public record Exception(String message, int status, ZonedDateTime timestamp) {
}
