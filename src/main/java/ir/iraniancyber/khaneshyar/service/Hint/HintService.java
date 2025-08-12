package ir.iraniancyber.khaneshyar.service.Hint;

import ir.iraniancyber.khaneshyar.model.Hint;

public interface HintService {
    void save(Hint hint);

    void delete(int id);

    void update(Hint hint);
}
