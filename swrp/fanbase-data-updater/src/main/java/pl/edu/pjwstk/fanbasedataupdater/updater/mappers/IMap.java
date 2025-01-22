package pl.edu.pjwstk.fanbasedataupdater.updater.mappers;

public interface IMap<TDto, TEntity> {

    TEntity toEntity(TDto dto);
}
