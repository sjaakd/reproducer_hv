package nl.reproducer.validation;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AlwaysInvalidDao {

    public String getState() {
        return "daoState";
    }
}
