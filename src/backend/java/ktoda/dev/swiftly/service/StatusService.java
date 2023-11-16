package ktoda.dev.swiftly.service;

import ktoda.dev.swiftly.model.Status;

public interface StatusService extends Service<Status, Long> {
    Status findByName(String statusName);

}
