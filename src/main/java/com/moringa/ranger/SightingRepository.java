package com.moringa.ranger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SightingRepository {
   DbConnection dbConnection = new DbConnection();

    public void createSighting(Sighting sighting) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO public.\"SIGHTINGS\" (\"ID\", \"ANIMAL_ID\", \"ENGANGERED_ANIMAL_ID\", \"LOCATION\", \"RANGER_NAME\", \"TIMESTAMP\") VALUES (?, ?, ?, ?, ?, ?)"
             )) {
            statement.setInt(1, sighting.getId());
            statement.setInt(2, sighting.getAnimalId());
            statement.setInt(3, sighting.getEndangeredAnimalId());
            statement.setString(4, sighting.getLocation());
            statement.setString(5, sighting.getRangerName());
            statement.setTimestamp(6, sighting.getTimestamp());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while processing this request" + e.getMessage() );
        }
    }

    public Sighting readSighting(int id) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM public.\"SIGHTINGS\" WHERE \"ID\" = ?"
             )) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Sighting sighting = new Sighting();
                // Populate sighting object from resultSet
                return sighting;
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while processing this request" + e.getMessage() );
        }
        return null;
    }

    public List<Sighting> readAllSightings() {
        List<Sighting> sightings = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM public.\"SIGHTINGS\""
             )) {
            while (resultSet.next()) {
                Sighting sighting = new Sighting();
                // Populate sighting object from resultSet and add to the list
                sightings.add(sighting);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while processing this request" + e.getMessage() );
        }
        return sightings;
    }

    public void updateSighting(Sighting sighting) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE public.\"SIGHTINGS\" SET \"ANIMAL_ID\" = ?, \"ENGANGERED_ANIMAL_ID\" = ?, \"LOCATION\" = ?, \"RANGER_NAME\" = ?, \"TIMESTAMP\" = ? WHERE \"ID\" = ?"
             )) {
            statement.setInt(1, sighting.getAnimalId());
            statement.setInt(2, sighting.getEndangeredAnimalId());
            statement.setString(3, sighting.getLocation());
            statement.setString(4, sighting.getRangerName());
            statement.setTimestamp(5, sighting.getTimestamp());
            statement.setInt(6, sighting.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while processing this request" + e.getMessage() );
        }
    }

    public void deleteSighting(int id) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM public.\"SIGHTINGS\" WHERE \"ID\" = ?"
             )) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while processing this request" + e.getMessage() );
        }
    }
}
