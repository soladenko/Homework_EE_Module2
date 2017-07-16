package Impl;

import Model.Skill;

/**
 * Created by SO on 15.06.2017.
 */
public interface DAO <T> {

    void create(T value);

    T read(int id);

    T update(T value);

    void delete(int id);



}
