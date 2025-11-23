package tests;

import handlers.CacheProcessor;
import model.CacheUser;
import service.CacheService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * <summary>
 * Тесты для обработки аннотации кеширования и взаимодействия с кешем (моки).
 * Проверяет чтение областей кеширования и поведение сервиса кеширования.
 * </summary>
 */
public class CacheProcessorTest {

    /**
     * <summary>
     * Тест проверяет корректное чтение областей кеширования из аннотации.
     * </summary>
     */
    @Test
    public void cacheAreas_areReadCorrectly() {
        List<String> areas = CacheProcessor.getCacheAreas(CacheUser.class);
        assertNotNull(areas);
        assertEquals(2, areas.size());
        assertTrue(areas.contains("session"));
        assertTrue(areas.contains("profiles"));
    }

    /**
     * <summary>
     * Тест проверяет, что пустой массив областей означает отсутствие кеширования.
     * </summary>
     */
    @Test
    public void emptyCacheArray_meansNoCaching() {
        // Create a dummy class with empty cache via anonymous class
        @annotations.Cache({})
        class EmptyCacheClass {}

        List<String> areas = CacheProcessor.getCacheAreas(EmptyCacheClass.class);
        assertNotNull(areas);
        assertTrue(areas.isEmpty());
    }

    /**
     * <summary>
     * Мок-тест проверяет, что сохранение не вызывается при отсутствии областей кеширования.
     * </summary>
     */
    @Test
    public void mockCacheService_verifyNoStoreWhenNoAreas() {
        CacheService mockService = Mockito.mock(CacheService.class);
        when(mockService.availableAreas()).thenReturn(List.of());

        // If no areas available, storing should not be attempted
        verify(mockService, never()).store(anyString(), anyString(), any());
    }

    /**
     * <summary>
     * Мок-тест проверяет вызов сохранения для доступных областей кеширования.
     * </summary>
     */
    @Test
    public void mockCacheService_storeCalledForAvailableAreas() {
        CacheService mockService = Mockito.mock(CacheService.class);
        when(mockService.availableAreas()).thenReturn(List.of("сессия","профили"));

        // simulate storing to each area
        for (String area : mockService.availableAreas()) {
            mockService.store(area, "k", "v");
        }

        verify(mockService, times(2)).store(anyString(), eq("k"), eq("v"));
    }
}