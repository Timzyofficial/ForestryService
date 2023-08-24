package com.moringa.ranger;

import org.aspectj.lang.annotation.Before;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.List;


import static org.mockito.Mockito.*;

public class AnimalRepositoryTest {
    @Mock
    private DbConnection mockDbConnection = new DbConnection();;
    @Mock
    private Connection mockConnection;

    {
        try {
            mockConnection = mockDbConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private Statement mockStatement;
    @Mock
    private ResultSet mockResultSet;

    private AnimalRepository animalRepository;



    private SightingRepository sightingRepository;

    @Before("")
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this); // Initialize mock objects
        sightingRepository = new SightingRepository();
        when(mockDbConnection.getConnection()).thenReturn(mockConnection); // Set up expected behavior
    }
    @Test
    public void testCreateAnimal() throws SQLException {
        Animal animal = new Animal(1, "lion");
        EndangeredAnimal endangeredAnimal = new EndangeredAnimal(1, "lion", "ill", "young");

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        animalRepository.createAnimal(animal, endangeredAnimal);

        verify(mockPreparedStatement).setInt(eq(1), eq(animal.getId()));
        verify(mockPreparedStatement).setString(eq(2), eq(animal.getName()));
        verify(mockPreparedStatement).setString(eq(3), eq(endangeredAnimal.getAge()));
        verify(mockPreparedStatement).setString(eq(4), eq(endangeredAnimal.getHealth()));
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testReadAnimal() throws SQLException {
        int animalId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("ID")).thenReturn(animalId);
        when(mockResultSet.getString("NAME")).thenReturn("Lion");

        Animal animal = animalRepository.readAnimal(animalId);

        Assert.assertNotNull(animal);
        Assert.assertEquals(animalId, animal.getId());
        Assert.assertEquals("Lion", animal.getName());
    }

    @Test
    public void testReadAllAnimals() throws SQLException {
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // Simulate two rows

        List<Animal> animals = animalRepository.readAllAnimals();

        Assert.assertEquals(1, animals.size()); // Assuming you expect 1 animal in the mockResultSet
        // Assert other properties of the animals if needed
    }

    // Similar tests for updateAnimal and deleteAnimal
}
