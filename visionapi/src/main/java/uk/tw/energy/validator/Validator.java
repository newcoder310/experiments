package uk.tw.energy.validator;

public interface Validator<T> {

    boolean isValid(T object);

    void validate(T object);

}
