package org.experimenter.web.model;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.experimenter.repository.entity.Entity;

/**
 * Parent model for all entity models. It works like {@link LoadableDetachableModel} but is enhanced by storing the id
 * of the entity.<br>
 * The id is then used to reload the entity when requested. On detach, the id is stored and the entity itself is
 * detached.<br>
 * The only exception is when the entity is not yet persisted (it's id is null). In that case, the entity is not
 * detached.<br>
 * Moreover, the model is prepared to be injected by @SpringBean annotations, so the child classes can be injected with
 * repository services to retrieve the entities.<br>
 * 
 * @author jfaryad
 * 
 * @param <T>
 *            the specific entity this model works with
 */
public abstract class EntityModel<T extends Entity> extends LoadableDetachableModel<T> {

    private static final long serialVersionUID = -1039464884750961390L;

    private Integer id;
    private T entity;

    public EntityModel(T entity) {
        Injector.get().inject(this);

        id = entity.getId();
        this.entity = entity;
    }

    public EntityModel(Integer id) {
        Injector.get().inject(this);

        this.id = id;
    }

    @Override
    public T load() {
        if (entity == null) {
            if (id != null) {
                entity = loadForId(id);
                if (entity == null) {
                    throw new EntityNotFoundException("Entity model " + getClass() + " could not find entity with id "
                            + id);
                }
            }
        }
        return entity;
    }

    @Override
    public void onDetach() {
        if (entity != null) {
            if (entity.getId() != null) {
                id = entity.getId();
                entity = null;
            }
        }
    }

    /**
     * Loads the entity with the specified id.
     * 
     * @param id
     *            the id of the entity to load
     * @return the loaded entity
     */
    protected abstract T loadForId(Integer id);

    @Override
    public void setObject(T object) {
        super.setObject(object);
        entity = object;
        if (entity != null) {
            id = entity.getId();
        }
    }
}
