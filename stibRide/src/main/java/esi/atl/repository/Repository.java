package esi.atl.repository;

import esi.atl.dto.Dto;
import esi.atl.exception.RepositoryException;
import java.util.List;

/**
 * Repository pattern to manage a resource of the application: a file, a
 * database, a web service.
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 *
 * @param <K> key of an element.
 * @param <T> an element.
 */
public interface Repository<K, T extends Dto<K>> {

    /**
     * Returns all the elements of the repository.
     *
     * @return all the elements of the repository.
     *
     * @throws RepositoryException if the repository can't access to the
     * elements.
     */
    List<T> getAll() throws RepositoryException;

    /**
     * Return the element of the repository with the specific key.
     *
     * @param key key of the element.
     *
     * @return the element of the repository with the specific key.
     * @throws RepositoryException if the repository can't access to the
     * element.
     */
    T get(K key) throws RepositoryException;

}
