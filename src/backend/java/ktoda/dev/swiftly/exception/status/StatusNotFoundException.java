package ktoda.dev.swiftly.exception.status;

import ktoda.dev.swiftly.exception.CustomException;

public class StatusNotFoundException extends CustomException {
    public StatusNotFoundException() {
        super();
    }

    public StatusNotFoundException(String message) {
        super(message);
    }

    public StatusNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
