package ktoda.dev.swiftly.exception.tag;

import ktoda.dev.swiftly.exception.CustomException;

public class TagNotFoundException extends CustomException {
    public TagNotFoundException() {
        super();
    }

    public TagNotFoundException(String message) {
        super(message);
    }

    public TagNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
