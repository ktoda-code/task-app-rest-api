package ktoda.dev.swiftly.exception.task;

import ktoda.dev.swiftly.exception.CustomException;

public class TaskNotFoundException extends CustomException {
    public TaskNotFoundException() {
        super();
    }

    public TaskNotFoundException(String message) {
        super(message);
    }

    public TaskNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
