package tacos.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import tacos.Ingredient;

/**
 * Raw implementation of {@link IngredientRepository} for comparison with
 * {@link JdbcIngredientRepository} to illustrate the power of using
 * {@link JdbcTemplate}.
 *
 * @author habuma
 */
public class RawJdbcIngredientRepository implements IngredientRepository {

    private final DataSource dataSource;

    public RawJdbcIngredientRepository(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        final List<Ingredient> ingredients = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("select id, name, type from Ingredient");
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                final Ingredient ingredient = new Ingredient(resultSet.getString("id"), resultSet.getString("name"),
                        Ingredient.Type.valueOf(resultSet.getString("type")));
                ingredients.add(ingredient);
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    // tag::rawfindOne[]
    @Override
    public Ingredient findById(final String id) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("select id, name, type from Ingredient");) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                Ingredient ingredient = null;
                if (resultSet.next()) {
                    ingredient = new Ingredient(resultSet.getString("id"), resultSet.getString("name"),
                            Ingredient.Type.valueOf(resultSet.getString("type")));
                }
                resultSet.close();
                return ingredient;
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // end::rawfindOne[]

    @Override
    public Ingredient save(final Ingredient ingredient) {
        // TODO: I only needed one method for comparison purposes, so
        // I've not bothered implementing this one (yet).
        return null;
    }

}
