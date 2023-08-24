package com.moringa.ranger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AnimalRepository {
    DbConnection dbConnection = new DbConnection();

    public void createAnimal(Animal animal, EndangeredAnimal endangeredAnimal) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO public.\"Animals\" (\"ID\", \"NAME\", \"AGE\", \"HEALTH\") VALUES (?, ?, ?, ?)"
             )) {
            statement.setInt(1, animal.getId());
            statement.setString(2, animal.getName());
            statement.setString(3, endangeredAnimal.getAge());
            statement.setString(4, endangeredAnimal.getHealth());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while processing this request" + e.getMessage() );

        }
    }

    public Animal readAnimal(int id) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM public.\"Animals\" WHERE \"ID\" = ?"
             )) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Animal animal = new Animal();
                // Populate animal object from resultSet
                return animal;
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while processing this request" + e.getMessage() );

        }
        return null;
    }

    public List<Animal> readAllAnimals() {
        List<Animal> animals = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM public.\"Animals\""
             )) {
            while (resultSet.next()) {
                Animal animal = new Animal();
                // Populate animal object from resultSet and add to the list
                animals.add(animal);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while processing this request" + e.getMessage() );

        }
        return animals;
    }

    public void updateAnimal(Animal animal, EndangeredAnimal endangeredAnimal) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE public.\"Animals\" SET \"NAME\" = ?, \"AGE\" = ?, \"HEALTH\" = ? WHERE \"ID\" = ?"
             )) {
            statement.setString(1, animal.getName());
            statement.setString(2, endangeredAnimal.getAge());
            statement.setString(3, endangeredAnimal.getHealth());
            statement.setInt(4, animal.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while processing this request" + e.getMessage() );

        }
    }

    public void deleteAnimal(int id) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM public.\"Animals\" WHERE \"ID\" = ?"
             )) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while processing this request" + e.getMessage() );

        }
    }
}


