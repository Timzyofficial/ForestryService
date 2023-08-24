package com.moringa.ranger;

import org.aspectj.lang.annotation.Before;
import org.junit.*;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.*;
import java.util.List;

import static org.mockito.Mockito.*;

public class SightingRepositoryTest {
    @Mock
    private DbConnection mockDbConnection;
    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private Statement mockStatement;
    @Mock
    private ResultSet mockResultSet;

    private SightingRepository sightingRepository;

    @Before("")
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        sightingRepository = new SightingRepository();
        when(mockDbConnection.getConnection()).thenReturn(mockConnection);
    }

    @Test
    public void testCreateSighting() throws SQLException {
        Sighting sighting = new Sighting(/* initialize sighting properties */);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        sightingRepository.createSighting(sighting);

        verify(mockPreparedStatement).setInt(eq(1), eq(sighting.getId()));
        verify(mockPreparedStatement).setInt(eq(2), eq(sighting.getAnimalId()));
        verify(mockPreparedStatement).setInt(eq(3), eq(sighting.getEndangeredAnimalId()));
        verify(mockPreparedStatement).setString(eq(4), eq(sighting.getLocation()));
        verify(mockPreparedStatement).setString(eq(5), eq(sighting.getRangerName()));
        verify(mockPreparedStatement).setTimestamp(eq(6), eq(sighting.getTimestamp()));
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testReadSighting() throws SQLException {
        int sightingId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("ID")).thenReturn(sightingId);

        Sighting sighting = sightingRepository.readSighting(sightingId);

        Assert.assertNotNull(sighting);
        Assert.assertEquals(sightingId, sighting.getId());
    }

    @Test
    public void testReadAllSightings() throws SQLException {
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // Simulate two rows

        List<Sighting> sightings = sightingRepository.readAllSightings();

        Assert.assertEquals(1, sightings.size()); // Assuming you expect 1 sighting in the mockResultSet
        // Assert other properties of the sightings if needed
    }

    // Similar tests for updateSighting and deleteSighting
}
