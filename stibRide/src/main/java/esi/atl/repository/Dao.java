package esi.atl.repository;

import esi.atl.dto.Dto;
import esi.atl.exception.RepositoryException;
import java.util.List;

/**
 * Data access object of a resource (file, database, web service).
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 *
 * @param <K> key of an item.
 * @param <T> item of the resource.
 */
public interface Dao<K, T extends Dto<K>> {

    /**
     * Returns all the elements of the resource. This method can be long.
     *
     * @return all the elements of the resource.
     * @throws RepositoryException if the resource can't be accessed.
     */
    List<T> selectAll() throws RepositoryException;

    /**
     * Returns an element based on its key.
     *
     * @param key key of the element to select.
     * @return an element based on its key.
     * @throws RepositoryException if the resource can't be accessed.
     */
    T select(K key) throws RepositoryException;

    /**
     * Inserts an element into the resource.
     *
     * @param dto the element to insert.
     * @return the element's key, usefull when the key is auto-generated.
     * @throws RepositoryException if the resource can't be accessed.
     */
    K insert(T dto) throws RepositoryException;

    /**
     * Update an element of the resource.The search of the element is based on
     * its key.
     *
     * @param dto element to update.
     * @return the element's key, usefull when the key is auto-generated.
     * @throws RepositoryException if the resource can't be accessed.
     */
    void update(T dto) throws RepositoryException;

    /**
     * Deletes the item of the specific key from the resource.
     *
     * @param key key of the element to delete.
     * @return the element's key, usefull when the key is auto-generated.
     * @throws RepositoryException if the resource can't be accessed.
     */
    void delete(K key) throws RepositoryException;
}
