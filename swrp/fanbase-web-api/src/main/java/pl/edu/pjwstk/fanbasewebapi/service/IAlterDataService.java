package pl.edu.pjwstk.fanbasewebapi.service;

public interface IAlterDataService <T>{
    T delete(Long id);
    T update(Long id,T dtoEntity);
    Long save(T dtoEntity);
}
