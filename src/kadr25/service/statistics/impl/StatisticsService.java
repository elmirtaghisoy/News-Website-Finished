package kadr25.service.statistics.impl;

import kadr25.model.Statistics;

import java.util.List;

public interface StatisticsService {
    List<Statistics> getGeneralAuthorsStatistics() throws Exception;
    List<Statistics> getStatisticsByMonth(Integer month, Integer id) throws Exception;
}
