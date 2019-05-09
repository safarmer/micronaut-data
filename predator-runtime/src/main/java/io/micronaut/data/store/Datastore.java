package io.micronaut.data.store;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.data.model.Pageable;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Common interface for datastore implementations to implement.
 *
 * @author graemerocher
 * @since 1.0
 */
public interface Datastore {

    /**
     * Find one by ID.
     *
     * @param type The type
     * @param id The id
     * @param <T> The generic type
     * @return A result or null
     */
    @Nullable <T> T findOne(@NonNull Class<T> type, @NonNull Serializable id);

    /**
     * Find one by Query.
     *
     * @param resultType The resultType
     * @param query The query to execute
     * @param parameters The parameters
     * @param <T> The generic resultType
     * @return A result or null
     */
    @Nullable <T> T findOne(
            @NonNull Class<T> resultType,
            @NonNull String query,
            @NonNull Map<String, Object> parameters);

    /**
     * Finds all results for the given query.
     * @param resultType The result type
     * @param query The query
     * @param parameterValues The parameter values
     * @param <T> The generic type
     * @return An iterable result
     */
    default @NonNull <T> Iterable<T> findAll(
            @NonNull Class<T> resultType,
            @NonNull String query,
            @NonNull Map<String, Object> parameterValues
    ) {
        return findAll(
                resultType,
                query,
                parameterValues,
                Pageable.unpaged()
        );
    }

    /**
     * Finds all results for the given query.
     * @param rootEntity The root entity
     * @param <T> The generic type
     * @return An iterable result
     */
    default @NonNull <T> Iterable<T> findAll(
            @NonNull Class<T> rootEntity
    ) {
        return findAll(
                rootEntity,
                Pageable.unpaged()
        );
    }

    /**
     * Finds all results for the given query.
     * @param rootEntity The root entity
     * @param pageable The pageable
     * @param <T> The generic type
     * @return An iterable result
     */
    @NonNull <T> Iterable<T> findAll(
            @NonNull Class<T> rootEntity,
            @NonNull Pageable pageable
    );

    /**
     * Counts all results for the given query.
     * @param rootEntity The root entity
     * @param <T> The generic type
     * @return An iterable result
     */
    default <T> long count(
            @NonNull Class<T> rootEntity
    ) {
        return count(
                rootEntity,
                Pageable.unpaged()
        );
    }

    /**
     * Counts all results for the given query.
     * @param rootEntity The root entity
     * @param pageable The pageable
     * @param <T> The generic type
     * @return An iterable result
     */
    <T> long count(
            @NonNull Class<T> rootEntity,
            @NonNull Pageable pageable
    );

    /**
     * Finds all results for the given query.
     * @param resultType The result type
     * @param query The query
     * @param parameterValues The parameter values
     * @param pageable The pageable
     * @param <T> The generic type
     * @return An iterable result
     */
    @NonNull <T> Iterable<T> findAll(
            @NonNull Class<T> resultType,
            @NonNull String query,
            @NonNull Map<String, Object> parameterValues,
            @NonNull Pageable pageable
            );

    /**
     * Persist the entity returning a possibly new entity.
     * @param entity The entity
     * @param <T> The generic type
     * @return The entity
     */
    @NonNull <T> T persist(@NonNull T entity);

    /**
     * Persist all the given entities.
     * @param entities The entities
     * @param <T> The generic type
     * @return The entities, possibly mutated
     */
    @NonNull <T> Iterable<T> persistAll(@NonNull Iterable<T> entities);

    /**
     * Executes an update for the given query and parameter values. If it is possible to
     * return the number of objects updated, then do so.
     * @param query The query
     * @param parameterValues the parameter values
     */
    @NonNull Optional<Number> executeUpdate(
            @NonNull String query,
            @NonNull Map<String, Object> parameterValues
    );

    /**
     * Deletes all the entities of the given type
     * @param entityType The entity type
     * @param entities The entities
     * @param <T> The generic type
     */
    <T> void deleteAll(@NonNull Class<T> entityType, @NonNull Iterable<? extends T> entities);

    /**
     * Deletes all the entities of the given type
     * @param entityType The entity type
     * @param <T> The generic type
     */
    <T> void deleteAll(@NonNull Class<T> entityType);

    /**
     * Finds a stream for the given arguments.
     * @param resultType The result type
     * @param query The query
     * @param parameterValues The parameter values
     * @param pageable The pageable
     * @param <T> The generic type
     * @return The stream
     */
    @NonNull <T> Stream<T> findStream(
            @NonNull Class<T> resultType,
            @NonNull String query,
            @NonNull Map<String, Object> parameterValues,
            @NonNull Pageable pageable);

    /**
     * Finds a stream for the given arguments.
     * @param entity The result type
     * @param pageable The pageable
     * @param <T> The generic type
     * @return The stream
     */
    @NonNull <T> Stream<T> findStream(
            @NonNull Class<T> entity,
            @NonNull Pageable pageable);

    /**
     * Finds a stream for the given arguments.
     * @param entity The result type
     * @param <T> The generic type
     * @return The stream
     */
    default @NonNull <T> Stream<T> findStream(
            @NonNull Class<T> entity) {
        return findStream(entity, Pageable.unpaged());
    };
}
